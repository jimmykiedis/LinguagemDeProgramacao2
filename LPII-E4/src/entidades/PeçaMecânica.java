package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import persistência.BD;

public class PeçaMecânica extends Peça {
    public enum CategoriaMecânica {
        MOTOR("motor"), TRANSMISSÃO("transmissão"), ARREFECIMENTO("arrefecimento"),
        SUSPENSÃO("suspensão"), DIREÇÃO("direção");
        private final String texto;
        CategoriaMecânica(String texto) { this.texto = texto; }
        public static CategoriaMecânica fromTexto(String texto) {
            if (texto == null) return null;
            String normalizado = texto.trim().toLowerCase(Locale.ROOT);
            for (CategoriaMecânica valor : values()) if (valor.texto.equals(normalizado)) return valor;
            throw new IllegalArgumentException("Categoria mecânica inválida: " + texto);
        }
        @Override public String toString() { return texto; }
    }

    private CategoriaMecânica categoria_mecânica;
    private Integer prazo_garantia;
    private boolean mecanico_proprio;

    public PeçaMecânica(int codigo, String nome, MarcaPeça marca, double preco,
            CategoriaMecânica categoria_mecânica, Integer prazo_garantia, boolean mecanico_proprio) {
        this(codigo, nome, marca, preco, categoria_mecânica, prazo_garantia, mecanico_proprio, false);
    }

    public PeçaMecânica(int codigo, String nome, MarcaPeça marca, double preco,
            CategoriaMecânica categoria_mecânica, Integer prazo_garantia, boolean mecanico_proprio, boolean sob_encomenda) {
        super(codigo, nome, marca, preco, prazo_garantia, null);
        setSobEncomenda(sob_encomenda);
        this.categoria_mecânica = categoria_mecânica;
        this.prazo_garantia = prazo_garantia;
        this.mecanico_proprio = mecanico_proprio;
    }

    public PeçaMecânica(int codigo, String nome, MarcaPeça marca, double preco,
            String categoria_mecânica, Integer prazo_garantia) {
        this(codigo, nome, marca, preco, CategoriaMecânica.fromTexto(categoria_mecânica), prazo_garantia, false);
    }

    public PeçaMecânica() { this(0, null, null, 0.0, (CategoriaMecânica) null, null, false); }
    public CategoriaMecânica getCategoriaMecânica() { return categoria_mecânica; }
    public Integer getPrazoGarantia() { return prazo_garantia; }
    public boolean isMecanicoProprio() { return mecanico_proprio; }
    public void setCategoriaMecânica(CategoriaMecânica valor) { categoria_mecânica = valor; }
    public void setPrazoGarantia(Integer valor) { prazo_garantia = valor; }
    public void setMecanicoProprio(boolean valor) { mecanico_proprio = valor; }

    public static PeçaMecânica buscar(int codigo) {
        String sql = "SELECT p.codigo,p.nome,p.marca,p.`preço`,p.sob_encomenda,m.categoria_mecânica,m.prazo_garantia,m.mecanico_proprio FROM peças p JOIN peça_mecânica m ON m.codigoId=p.codigo WHERE p.codigo=?";
        try (PreparedStatement ps = BD.conexao.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return new PeçaMecânica(rs.getInt("codigo"), rs.getString("nome"),
                        MarcaPeça.values()[rs.getInt("marca")], rs.getDouble("preço"),
                        CategoriaMecânica.values()[rs.getInt("categoria_mecânica")],
                        (Integer) rs.getObject("prazo_garantia"), rs.getBoolean("mecanico_proprio"), rs.getBoolean("sob_encomenda"));
            }
        } catch (SQLException e) { e.printStackTrace(); return null; }
    }

    @Override public String inserir() {
        boolean auto_commit;
        try {
            auto_commit = BD.conexao.getAutoCommit();
            BD.conexao.setAutoCommit(false);
            String erro = super.inserir();
            if (erro != null) throw new SQLException(erro);
            try (PreparedStatement sub = BD.conexao.prepareStatement(
                    "INSERT INTO peça_mecânica(categoria_mecânica,prazo_garantia,mecanico_proprio,codigoId) VALUES(?,?,?,?)")) {
                sub.setInt(1, categoria_mecânica.ordinal());
                if (prazo_garantia == null) sub.setNull(2, java.sql.Types.INTEGER); else sub.setInt(2, prazo_garantia);
                sub.setBoolean(3, mecanico_proprio); sub.setInt(4, codigo); sub.executeUpdate();
            }
            BD.conexao.commit(); BD.conexao.setAutoCommit(auto_commit); return null;
        } catch (SQLException | NullPointerException e) {
            try { BD.conexao.rollback(); BD.conexao.setAutoCommit(true); } catch (SQLException rollback) { e.addSuppressed(rollback); }
            e.printStackTrace(); return "Erro ao inserir peça mecânica";
        }
    }

    @Override public String alterar() {
        String erro = super.alterar(); if (erro != null) return erro;
        try (PreparedStatement ps = BD.conexao.prepareStatement(
                "INSERT INTO peça_mecânica(categoria_mecânica,prazo_garantia,mecanico_proprio,codigoId) VALUES(?,?,?,?) "
                + "ON DUPLICATE KEY UPDATE categoria_mecânica=VALUES(categoria_mecânica),prazo_garantia=VALUES(prazo_garantia),mecanico_proprio=VALUES(mecanico_proprio)")) {
            ps.setInt(1, categoria_mecânica.ordinal());
            if (prazo_garantia == null) ps.setNull(2, java.sql.Types.INTEGER); else ps.setInt(2, prazo_garantia);
            ps.setBoolean(3, mecanico_proprio); ps.setInt(4, codigo); ps.executeUpdate();
            try (PreparedStatement remover = BD.conexao.prepareStatement("DELETE FROM peça_acessórios WHERE codigoId=?")) {
                remover.setInt(1, codigo); remover.executeUpdate();
            }
            return null;
        } catch (SQLException | NullPointerException e) { e.printStackTrace(); return "Erro ao alterar peça mecânica"; }
    }
}
