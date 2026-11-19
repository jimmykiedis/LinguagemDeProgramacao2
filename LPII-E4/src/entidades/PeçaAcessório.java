package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import persistência.BD;

public class PeçaAcessório extends Peça {
    public enum CategoriaAcessório {
        carroceria, farol_lanterna, parabrisas_vigia, painel, pedal, assento, assoalho
    }

    private CategoriaAcessório categoria_acessório;
    private String cor;
    private boolean funileiro_próprio;

    public PeçaAcessório(int codigo, String nome, MarcaPeça marca, double preco,
            CategoriaAcessório categoria_acessório, String cor, boolean funileiro_próprio) {
        this(codigo, nome, marca, preco, categoria_acessório, cor, funileiro_próprio, false);
    }

    public PeçaAcessório(int codigo, String nome, MarcaPeça marca, double preco,
            CategoriaAcessório categoria_acessório, String cor, boolean funileiro_próprio, boolean sob_encomenda) {
        super(codigo, nome, marca, preco, null, cor);
        setSobEncomenda(sob_encomenda);
        this.categoria_acessório = categoria_acessório;
        this.cor = cor;
        this.funileiro_próprio = funileiro_próprio;
    }

    public PeçaAcessório() { this(0, null, null, 0.0, (CategoriaAcessório) null, null, false); }
    public CategoriaAcessório getCategoriaAcessório() { return categoria_acessório; }
    @Override public String getCor() { return cor; }
    public boolean getFunileiroPróprio() { return funileiro_próprio; }
    public void setCategoriaAcessório(CategoriaAcessório valor) { categoria_acessório = valor; }
    @Override public void setCor(String valor) { cor = valor; super.setCor(valor); }
    public void setFunileiroPróprio(boolean valor) { funileiro_próprio = valor; }

    public static PeçaAcessório buscar(int codigo) {
        String sql = "SELECT p.codigo,p.nome,p.marca,p.`preço`,p.sob_encomenda,a.categoria_acessorio,a.cor,a.funileiro_próprio FROM peças p JOIN peça_acessórios a ON a.codigoId=p.codigo WHERE p.codigo=?";
        PeçaAcessório peça = null;
        ResultSet resultados = null;
        try {
            PreparedStatement ps = BD.conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            resultados = ps.executeQuery();
            if (resultados.next()) {
                peça = new PeçaAcessório(resultados.getInt("codigo"), resultados.getString("nome"),
                        MarcaPeça.values()[resultados.getInt("marca")], resultados.getDouble("preço"),
                        CategoriaAcessório.values()[resultados.getInt("categoria_acessorio")],
                        resultados.getString("cor"), resultados.getBoolean("funileiro_próprio"),
                        resultados.getBoolean("sob_encomenda"));
            }
            resultados.close();
            ps.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return peça;
    }

    @Override public String inserir() {
        boolean auto_commit;
        try {
            auto_commit = BD.conexao.getAutoCommit(); BD.conexao.setAutoCommit(false);
            String erro = super.inserir(); if (erro != null) throw new SQLException(erro);
            PreparedStatement sub = BD.conexao.prepareStatement(
                    "INSERT INTO peça_acessórios(categoria_acessorio,cor,funileiro_próprio,codigoId) VALUES(?,?,?,?)");
            sub.setInt(1, categoria_acessório.ordinal());
            sub.setString(2, cor);
            sub.setBoolean(3, funileiro_próprio);
            sub.setInt(4, codigo);
            sub.executeUpdate();
            sub.close();
            BD.conexao.commit(); BD.conexao.setAutoCommit(auto_commit); return null;
        } catch (SQLException | NullPointerException e) {
            try {
                BD.conexao.rollback();
                BD.conexao.setAutoCommit(true);
            } catch (SQLException rollback) {
                rollback.printStackTrace();
            }
            e.printStackTrace(); return "Erro ao inserir peça de acessórios";
        }
    }

    @Override public String alterar() {
        String erro = super.alterar(); if (erro != null) return erro;
        try {
            PreparedStatement ps = BD.conexao.prepareStatement(
                    "INSERT INTO peça_acessórios(categoria_acessorio,cor,funileiro_próprio,codigoId) VALUES(?,?,?,?) "
                    + "ON DUPLICATE KEY UPDATE categoria_acessorio=VALUES(categoria_acessorio),cor=VALUES(cor),funileiro_próprio=VALUES(funileiro_próprio)");
            ps.setInt(1, categoria_acessório.ordinal());
            ps.setString(2, cor);
            ps.setBoolean(3, funileiro_próprio);
            ps.setInt(4, codigo);
            ps.executeUpdate();
            ps.close();

            PreparedStatement remover = BD.conexao.prepareStatement(
                    "DELETE FROM peça_mecânica WHERE codigoId=?");
            remover.setInt(1, codigo);
            remover.executeUpdate();
            remover.close();
            return null;
        } catch (SQLException | NullPointerException e) { e.printStackTrace(); return "Erro ao alterar peça de acessórios"; }
    }
}
