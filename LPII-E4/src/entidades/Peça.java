/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import persistência.BD;
import java.util.ArrayList;

/**
 *
 * @author jimmy
 */
public class Peça {
    public enum Marca {
    original, genuína, OEM
    }

    private int código;
    private String nome;
    private Marca marca;
    private double preço;
    private boolean sob_encomenda;

    public String getNome() {
        return nome;
    }
        
    public Marca getMarca() {
        return marca;
    }
    
    public double getPreço() {
        return preço;
    }   
    
    public boolean isSobEncomenda() {
        return sob_encomenda;
    }
    
    public Peça(int código, String nome, Marca marca, double preço,
            boolean sob_encomenda) {
        this.código = código;
        this.nome = nome;
        this.marca = marca;
        this.preço = preço;
        this.sob_encomenda = sob_encomenda;
    }

    public Peça(int código, String nome) {
        this.código = código;
        this.nome = nome;
    }
    
    public int getCódigo() {
        return código;
    }

    public static Peça[] getVisões() {
        String sql = "SELECT codigo, nome FROM peças";
        ResultSet lista_resultados = null;
        ArrayList<Peça> visões = new ArrayList<>();

        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            lista_resultados = comando.executeQuery();

            while (lista_resultados.next()) {
                visões.add(new Peça(
                        lista_resultados.getInt("codigo"),
                        lista_resultados.getString("nome")));
            }

            lista_resultados.close();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
        }

        return visões.toArray(new Peça[visões.size()]);
    }
    
    public Peça getVisão() {
        return new Peça(código, nome);
    }
    
    public static Peça buscarPeça(int código) {
        String sql = "SELECT * FROM peças WHERE codigo = ?";
        ResultSet lista_resultados = null;
        Peça peça = null;

        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, código);
            lista_resultados = comando.executeQuery();

            while (lista_resultados.next()) {
                int código_encontrado = lista_resultados.getInt("codigo");
                String nome = lista_resultados.getString("nome");
                int marca = lista_resultados.getInt("marca");
                Marca marca_peça = lista_resultados.wasNull()
                        ? null
                        : Marca.values()[marca];
                double preço = lista_resultados.getDouble("preço");
                boolean sob_encomenda = lista_resultados.getBoolean("sob_encomenda");
                peça = new Peça(código_encontrado, nome, marca_peça, preço, sob_encomenda);
            }

            lista_resultados.close();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            peça = null;
        }

        return peça;
    }

    public static String inserirPeça(Peça peça) {
        String sql = "INSERT INTO peças "
                + "(codigo, nome, marca, preço, sob_encomenda) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, peça.código);
            comando.setString(2, peça.nome);
            comando.setInt(3, peça.marca.ordinal());
            comando.setDouble(4, peça.preço);
            comando.setBoolean(5, peça.sob_encomenda);
            comando.executeUpdate();
            comando.close();
            return null;
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro na inserção da peça no BD";
        }
    }
}
