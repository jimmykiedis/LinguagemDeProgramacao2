/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controles;

import entidades.Seguradora;
import interfaces.JanelaCadastroSeguradoras;
/**
 *
 * @author jimmy
 */
public class ControladorCadastroSeguradoras {
    
    public ControladorCadastroSeguradoras() {
        new JanelaCadastroSeguradoras(this).setVisible(true);
    }
    
    public String inserirSeguradora(Seguradora seguradora) {
        Seguradora seguradora1 =
                Seguradora.buscarSeguradora(seguradora.getNome());

        if (seguradora1 == null)
            return Seguradora.inserirSeguradora(seguradora);
        else
            return "Nome de Seguradora já cadastrado";
    }

    public String alterarSeguradora(Seguradora seguradora) {
        Seguradora seguradora1 =
                Seguradora.buscarSeguradora(seguradora.getNome());

        if (seguradora1 != null)
            return Seguradora.alterarSeguradora(seguradora);
        else
            return "Nome de Seguradora não cadastrado";
    }

}
