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
        Sinistro sinistroBuscado =
                sinistro != null ? Sinistro.buscarSinistro(sinistro.getId()) : null;
        if (sinistroBuscado == null) return "Sinistro nao cadastrado";

        Peça pecaBuscada =
                peca != null ? Peça.buscarPeças(peca.getCodigo()) : null;
        if (pecaBuscada == null) return "Peça nao cadastrada";

        if (PeçaSinistro.existePeçasSinistros(peca.getCodigo(), sinistro.getId())) {
            return "Peça ja associada ao sinistro";
        }

        return PeçaSinistro.inserirPeçasSinistros(pecaBuscada, sinistroBuscado);
    }

    public String removerPeçasSinistros(Peça peca, Sinistro sinistro) {
        Sinistro sinistroBuscado =
                sinistro != null ? Sinistro.buscarSinistro(sinistro.getId()) : null;
        if (sinistroBuscado == null) return "Sinistro nao cadastrado";

        Peça pecaBuscada =
                peca != null ? Peça.buscarPeças(peca.getCodigo()) : null;
        if (pecaBuscada == null) return "Peça nao cadastrada";

        if (!PeçaSinistro.existePeçasSinistros(peca.getCodigo(), sinistro.getId())) {
            return "Peça nao associada ao sinistro";
        }

        return PeçaSinistro.removerPeçasSinistros(pecaBuscada, sinistroBuscado);
    }
}
