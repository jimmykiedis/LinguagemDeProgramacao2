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
        if (Orçamento.existeOrçamento(orçamento.getSinistro().getId(), orçamento.getSeguradora().getNome())) {
            return "Já existe orçamento para este sinistro e seguradora";
        }
        return Orçamento.inserirOrçamento(orçamento);
    }

    public String alterarOrçamento(Orçamento orçamento, int idSinistroOriginal, String seguradoraOriginal) {
        if (orçamento.getSinistro().getId() != idSinistroOriginal
                || !orçamento.getSeguradora().getNome().equals(seguradoraOriginal)) {
            return "Alteração não permitida: sinistro e seguradora identificam o orçamento";
        }
        if (!Orçamento.existeOrçamento(idSinistroOriginal, seguradoraOriginal)) {
            return "Orçamento não cadastrado";
        }
        return Orçamento.alterarOrçamento(orçamento);
    }

    public String removerOrçamento(int idSinistro, String nomeSeguradora) {
        if (!Orçamento.existeOrçamento(idSinistro, nomeSeguradora)) {
            return "Orçamento não cadastrado";
        }
        return Orçamento.removerOrçamento(idSinistro, nomeSeguradora);
    }
}
