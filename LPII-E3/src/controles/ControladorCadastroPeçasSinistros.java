package controles;

import entidades.Peça;
import entidades.PeçaSinistro;
import entidades.Sinistro;
import interfaces.JanelaCadastroPeçasSinistros;
import java.awt.Frame;

public class ControladorCadastroPeçasSinistros {

    public ControladorCadastroPeçasSinistros() {
        this(null, null);
    }

    public ControladorCadastroPeçasSinistros(Frame owner, Sinistro sinistro) {
        JanelaCadastroPeçasSinistros janela =
                new JanelaCadastroPeçasSinistros(this, sinistro, owner);
        janela.setVisible(true);
        janela.toFront();
        janela.requestFocus();
    }

    public String inserirPeçasSinistros(Peça peca, Sinistro sinistro) {
        Sinistro sinistro_buscado =
                sinistro != null ? Sinistro.buscarSinistro(sinistro.getId()) : null;
        if (sinistro_buscado == null) return "Sinistro nao cadastrado";

        Peça peca_buscada =
                peca != null ? Peça.buscarPeças(peca.getCodigo()) : null;
        if (peca_buscada == null) return "Peça nao cadastrada";

        if (PeçaSinistro.existePeçasSinistros(peca.getCodigo(), sinistro.getId())) {
            return "Peça ja associada ao sinistro";
        }

        return PeçaSinistro.inserirPeçasSinistros(peca_buscada, sinistro_buscado);
    }

    public String removerPeçasSinistros(Peça peca, Sinistro sinistro) {
        Sinistro sinistro_buscado =
                sinistro != null ? Sinistro.buscarSinistro(sinistro.getId()) : null;
        if (sinistro_buscado == null) return "Sinistro nao cadastrado";

        Peça peca_buscada =
                peca != null ? Peça.buscarPeças(peca.getCodigo()) : null;
        if (peca_buscada == null) return "Peça nao cadastrada";

        if (!PeçaSinistro.existePeçasSinistros(peca.getCodigo(), sinistro.getId())) {
            return "Peça nao associada ao sinistro";
        }

        return PeçaSinistro.removerPeçasSinistros(peca_buscada, sinistro_buscado);
    }
}
