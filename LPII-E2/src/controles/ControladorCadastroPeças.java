package controles;

import entidades.Peça;
import interfaces.JanelaCadastroPeças;
import java.awt.Frame;

public class ControladorCadastroPeças {
    
    public ControladorCadastroPeças() {
        this(null);
    }

    public ControladorCadastroPeças(Frame owner) {
        JanelaCadastroPeças janela = new JanelaCadastroPeças(this, owner);
        janela.setVisible(true);
        janela.toFront();
        janela.requestFocus();
    }
       
    public String inserirPeças(Peça pecas) {
        Peça peca_buscada = Peça.buscarPeças(pecas.getCodigo());
        if (peca_buscada == null) return Peça.inserirPeças(pecas);
        else return "Código de Peça já cadastrado";
    }
    
    public String alterarPeças(Peça pecas) {
        Peça peca_buscada = Peça.buscarPeças(pecas.getCodigo());
        if (peca_buscada != null) return Peça.alterarPeças(pecas);
        else return "Código de Peça não cadastrado";
    }
    
    public String removerPeças(int codigo) {
        Peça peca_buscada = Peça.buscarPeças(codigo);
        if (peca_buscada != null) return Peça.removerPeças(codigo);
        else return "Código de Peça não cadastrado";
    }
}
