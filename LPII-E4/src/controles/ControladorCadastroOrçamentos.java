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

    public String alterarOrçamento(Orçamento orçamento, int id_sinistro_original, String seguradora_original) {
        if (orçamento.getSinistro().getId() != id_sinistro_original
                || !orçamento.getSeguradora().getNome().equals(seguradora_original)) {
            return "Alteração não permitida: sinistro e seguradora identificam o orçamento";
        }
        if (!Orçamento.existeOrçamento(id_sinistro_original, seguradora_original)) {
            return "Orçamento não cadastrado";
        }
        return Orçamento.alterarOrçamento(orçamento);
    }

    public String removerOrçamento(int id_sinistro, String nome_seguradora) {
        if (!Orçamento.existeOrçamento(id_sinistro, nome_seguradora)) {
            return "Orçamento não cadastrado";
        }
        return Orçamento.removerOrçamento(id_sinistro, nome_seguradora);
    }
}
