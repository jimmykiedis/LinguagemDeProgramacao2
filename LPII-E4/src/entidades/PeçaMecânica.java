package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import persistência.BD;

public class PeçaMecânica extends Peça {
    public enum CategoriaMecânica {
        motor, transmissão, arrefecimento, suspensão, direção
    }

    private CategoriaMecânica categoria_mecânica;
    private Integer prazo_garantia;
    private boolean mecânico_próprio;

    public PeçaMecânica(int codigo, String nome, MarcaPeça marca, double preco,
            CategoriaMecânica categoria_mecânica, Integer prazo_garantia, boolean mecânico_próprio) {
        this(codigo, nome, marca, preco, categoria_mecânica, prazo_garantia, mecânico_próprio, false);
    }

    public PeçaMecânica(int codigo, String nome, MarcaPeça marca, double preco,
            CategoriaMecânica categoria_mecânica, Integer prazo_garantia, boolean mecânico_próprio, boolean sob_encomenda) {
        super(codigo, nome, marca, preco, prazo_garantia, null);
        setSobEncomenda(sob_encomenda);
        this.categoria_mecânica = categoria_mecânica;
        this.prazo_garantia = prazo_garantia;
        this.mecânico_próprio = mecânico_próprio;
    }

    public PeçaMecânica() { this(0, null, null, 0.0, (CategoriaMecânica) null, null, false); }
    public CategoriaMecânica getCategoriaMecânica() { return categoria_mecânica; }
    public Integer getPrazoGarantia() { return prazo_garantia; }
    public boolean getMecânicoPróprio() { return mecânico_próprio; }
    public void setCategoriaMecânica(CategoriaMecânica valor) { categoria_mecânica = valor; }
    public void setPrazoGarantia(Integer valor) { prazo_garantia = valor; }
    public void setMecânicoPróprio(boolean valor) { mecânico_próprio = valor; }

    @Override
    public String toStringFull() {
        String str = "Peça mecânica - " + super.toStringFull()
                + " - " + categoria_mecânica
                + " - " + prazo_garantia + " dias - mecânico próprio: "
                + (mecânico_próprio ? "sim" : "não");
        return str;
    }

    public static PeçaMecânica buscar(int codigo) {
        String sql = "SELECT p.codigo,p.nome,p.marca,p.`preço`,p.sob_encomenda,m.categoria_mecânica,m.prazo_garantia,m.mecânico_próprio FROM peças p JOIN peça_mecânica m ON m.codigoId=p.codigo WHERE p.codigo=?";
        PeçaMecânica peça = null;
        ResultSet resultados = null;
        try {
            PreparedStatement ps = BD.conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            resultados = ps.executeQuery();
            if (resultados.next()) {
                peça = new PeçaMecânica(resultados.getInt("codigo"), resultados.getString("nome"),
                        MarcaPeça.values()[resultados.getInt("marca")], resultados.getDouble("preço"),
                        CategoriaMecânica.values()[resultados.getInt("categoria_mecânica")],
                        (Integer) resultados.getObject("prazo_garantia"),
                        resultados.getBoolean("mecânico_próprio"), resultados.getBoolean("sob_encomenda"));
            }
            resultados.close();
            ps.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return peça;
    }

    @Override public String inserir() {
        boolean auto_commit;
        try {
            auto_commit = BD.conexao.getAutoCommit();
            BD.conexao.setAutoCommit(false);
            String erro = super.inserir();
            if (erro != null) throw new SQLException(erro);
            PreparedStatement sub = BD.conexao.prepareStatement(
                    "INSERT INTO peça_mecânica(categoria_mecânica,prazo_garantia,mecânico_próprio,codigoId) VALUES(?,?,?,?)");
            sub.setInt(1, categoria_mecânica.ordinal());
            if (prazo_garantia == null) sub.setNull(2, java.sql.Types.INTEGER); else sub.setInt(2, prazo_garantia);
            sub.setBoolean(3, mecânico_próprio);
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
            e.printStackTrace(); return "Erro ao inserir peça mecânica";
        }
    }

    @Override public String alterar() {
        String erro = super.alterar(); if (erro != null) return erro;
        try {
            PreparedStatement ps = BD.conexao.prepareStatement(
                    "UPDATE peça_mecânica SET categoria_mecânica=?,prazo_garantia=?,mecânico_próprio=? WHERE codigoId=?");
            ps.setInt(1, categoria_mecânica.ordinal());
            if (prazo_garantia == null) ps.setNull(2, java.sql.Types.INTEGER); else ps.setInt(2, prazo_garantia);
            ps.setBoolean(3, mecânico_próprio);
            ps.setInt(4, codigo);
            ps.executeUpdate();
            ps.close();

            PreparedStatement remover = BD.conexao.prepareStatement(
                    "DELETE FROM peça_acessórios WHERE codigoId=?");
            remover.setInt(1, codigo);
            remover.executeUpdate();
            remover.close();
            return null;
        } catch (SQLException | NullPointerException e) { e.printStackTrace(); return "Erro ao alterar peça mecânica"; }
    }
}
