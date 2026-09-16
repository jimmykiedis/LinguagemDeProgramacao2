package controles;

import entidades.Peca;
import entidades.PecaSinistro;
import entidades.Sinistro;
import interfaces.JanelaCadastroPecasSinistros;
import java.awt.Frame;

public class ControladorCadastroPecasSinistros {

    public ControladorCadastroPecasSinistros() {
        this(null, null);
    }

    public ControladorCadastroPecasSinistros(Frame owner, Sinistro sinistro) {
        JanelaCadastroPecasSinistros janela =
                new JanelaCadastroPecasSinistros(this, sinistro, owner);
        janela.setVisible(true);
        janela.toFront();
        janela.requestFocus();
    }

    public String inserirPecasSinistros(Peca peca, Sinistro sinistro) {
        Sinistro sinistro_buscado =
                sinistro != null ? Sinistro.buscarSinistro(sinistro.getId()) : null;
        if (sinistro_buscado == null) return "Sinistro nao cadastrado";

        Peca peca_buscada =
                peca != null ? Peca.buscarPecas(peca.getCodigo()) : null;
        if (peca_buscada == null) return "Peca nao cadastrada";

        if (PecaSinistro.existePecasSinistros(peca.getCodigo(), sinistro.getId())) {
            return "Peca ja associada ao sinistro";
        }

        return PecaSinistro.inserirPecasSinistros(peca_buscada, sinistro_buscado);
    }

    public String removerPecasSinistros(Peca peca, Sinistro sinistro) {
        Sinistro sinistro_buscado =
                sinistro != null ? Sinistro.buscarSinistro(sinistro.getId()) : null;
        if (sinistro_buscado == null) return "Sinistro nao cadastrado";

        Peca peca_buscada =
                peca != null ? Peca.buscarPecas(peca.getCodigo()) : null;
        if (peca_buscada == null) return "Peca nao cadastrada";

        if (!PecaSinistro.existePecasSinistros(peca.getCodigo(), sinistro.getId())) {
            return "Peca nao associada ao sinistro";
        }

        return PecaSinistro.removerPecasSinistros(peca_buscada, sinistro_buscado);
    }
}
