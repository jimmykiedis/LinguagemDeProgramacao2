package controles;

import entidades.Orçamento;
import interfaces.JanelaCadastroOrçamentos;

public class ControladorCadastroOrçamentos {

    public ControladorCadastroOrçamentos() {
        JanelaCadastroOrçamentos janela = new JanelaCadastroOrçamentos(this);
        janela.setVisible(true);
        janela.toFront();
        janela.requestFocus();
    }

    public String inserirOrçamento(Orçamento orçamento) {
        if (!Orçamento.existeOrçamento(orçamento.getSinistro().getId(), orçamento.getSeguradora().getNome())) {
            return Orçamento.inserirOrçamento(orçamento);
        } else return "Já existe orçamento para este sinistro e seguradora";
    }

    public String alterarOrçamento(Orçamento orçamento) {
        Orçamento orçamento_cadastrado = Orçamento.buscarOrçamento(orçamento.getSequencial());
        if (orçamento_cadastrado != null
                && orçamento.getSinistro().getId() == orçamento_cadastrado.getSinistro().getId()
                && orçamento.getSeguradora().getNome().equals(orçamento_cadastrado.getSeguradora().getNome())) {
            return Orçamento.alterarOrçamento(orçamento);
        } else return "Alteração não permitida: orçamento não cadastrado ou sinistro/seguradora alterados";
    }

    public String removerOrçamento(int sequencial) {
        if (Orçamento.existeOrçamento(sequencial)) return Orçamento.removerOrçamento(sequencial);
        else return "Sequencial não corresponde a nenhum orçamento cadastrado";
    }
}
