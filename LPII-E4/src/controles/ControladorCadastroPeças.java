/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controles;

import entidades.Peça;
import interfaces.JanelaCadastroPeças;

/**
 *
 * @author jimmy
 */
public class ControladorCadastroPeças {
    
    public String inserirPeça(Peça peça) {
        Peça peça_cadastrada = Peça.buscarPeça(peça.getCódigo());

        if (peça_cadastrada == null)
            return Peça.inserirPeça(peça);
        else
            return "Código de peça já cadastrado";
    }
    
    public ControladorCadastroPeças() {
        new JanelaCadastroPeças(this).setVisible(true);
    }
}
