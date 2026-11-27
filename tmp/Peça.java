/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author jimmy
 */

public class Peça {
    public enum MarcaPeça { 
        original, genuína, OEM
    };
    
    private int codigo;
    private String nome;
    private MarcaPeça marca;
    private double preco;
    private Integer dias_garantia;
    private String cor;
    private boolean sob_encomenda;
}
