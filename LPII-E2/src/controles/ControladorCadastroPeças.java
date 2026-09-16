package controles;

import entidades.Peca;
import interfaces.JanelaCadastroPecas;
import java.awt.Frame;

public class ControladorCadastroPecas {
    
    public ControladorCadastroPecas() {
        this(null);
    }

    public ControladorCadastroPecas(Frame owner) {
        JanelaCadastroPecas janela = new JanelaCadastroPecas(this, owner);
        janela.setVisible(true);
        janela.toFront();
        janela.requestFocus();
    }
       
    public String inserirPecas(Peca pecas) {
        Peca peca_buscada = Peca.buscarPecas(pecas.getCodigo());
        if (peca_buscada == null) return Peca.inserirPecas(pecas);
        else return "Código de Peça já cadastrado";
    }
    
    public String alterarPecas(Peca pecas) {
        Peca peca_buscada = Peca.buscarPecas(pecas.getCodigo());
        if (peca_buscada != null) return Peca.alterarPecas(pecas);
        else return "Código de Peça não cadastrado";
    }
    
    public String removerPecas(int codigo) {
        Peca peca_buscada = Peca.buscarPecas(codigo);
        if (peca_buscada != null) return Peca.removerPecas(codigo);
        else return "Código de Peça não cadastrado";
    }
}
