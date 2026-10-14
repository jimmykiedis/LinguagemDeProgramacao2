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

    private CategoriaMecânica categoriaMecânica;
    private Integer prazoGarantia;
    private boolean mecanicoProprio;

    public PeçaMecânica(int codigo, String nome, MarcaPeça marca, double preco,
            CategoriaMecânica categoriaMecânica, Integer prazoGarantia, boolean mecanicoProprio) {
        super(codigo, nome, marca, preco, prazoGarantia, null);
        this.categoriaMecânica = categoriaMecânica;
        this.prazoGarantia = prazoGarantia;
        this.mecanicoProprio = mecanicoProprio;
    }

    public PeçaMecânica(int codigo, String nome, MarcaPeça marca, double preco,
            String categoriaMecânica, Integer prazoGarantia) {
        this(codigo, nome, marca, preco, CategoriaMecânica.fromTexto(categoriaMecânica), prazoGarantia, false);
    }

    public PeçaMecânica() { this(0, null, null, 0.0, (CategoriaMecânica) null, null, false); }
    public CategoriaMecânica getCategoriaMecânica() { return categoriaMecânica; }
    public Integer getPrazoGarantia() { return prazoGarantia; }
    public boolean isMecanicoProprio() { return mecanicoProprio; }
    public void setCategoriaMecânica(CategoriaMecânica valor) { categoriaMecânica = valor; }
    public void setPrazoGarantia(Integer valor) { prazoGarantia = valor; }
    public void setMecanicoProprio(boolean valor) { mecanicoProprio = valor; }

    public static PeçaMecânica buscar(int codigo) {
        String sql = "SELECT p.codigo,p.nome,p.marca,p.`preço`,m.categoria_mecânica,m.prazo_garantia,m.mecanico_proprio FROM peças p JOIN peça_mecânica m ON m.codigoId=p.codigo WHERE p.codigo=?";
        try (PreparedStatement ps = BD.conexao.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return new PeçaMecânica(rs.getInt("codigo"), rs.getString("nome"),
                        MarcaPeça.fromTexto(rs.getString("marca")), rs.getDouble("preço"),
                        CategoriaMecânica.fromTexto(rs.getString("categoria_mecânica")),
                        (Integer) rs.getObject("prazo_garantia"), rs.getBoolean("mecanico_proprio"));
            }
        } catch (SQLException e) { e.printStackTrace(); return null; }
    }

    @Override public String inserir() {
        boolean autoCommit;
        try {
            autoCommit = BD.conexao.getAutoCommit();
            BD.conexao.setAutoCommit(false);
            String erro = super.inserir();
            if (erro != null) throw new SQLException(erro);
            try (PreparedStatement sub = BD.conexao.prepareStatement(
                    "INSERT INTO peça_mecânica(categoria_mecânica,prazo_garantia,mecanico_proprio,codigoId) VALUES(?,?,?,?)")) {
                sub.setString(1, categoriaMecânica.toString());
                if (prazoGarantia == null) sub.setNull(2, java.sql.Types.INTEGER); else sub.setInt(2, prazoGarantia);
                sub.setBoolean(3, mecanicoProprio); sub.setInt(4, codigo); sub.executeUpdate();
            }
            BD.conexao.commit(); BD.conexao.setAutoCommit(autoCommit); return null;
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
            ps.setString(1, categoriaMecânica.toString());
            if (prazoGarantia == null) ps.setNull(2, java.sql.Types.INTEGER); else ps.setInt(2, prazoGarantia);
            ps.setBoolean(3, mecanicoProprio); ps.setInt(4, codigo); ps.executeUpdate();
            try (PreparedStatement remover = BD.conexao.prepareStatement("DELETE FROM peça_acessórios WHERE codigoId=?")) {
                remover.setInt(1, codigo); remover.executeUpdate();
            }
            return null;
        } catch (SQLException | NullPointerException e) { e.printStackTrace(); return "Erro ao alterar peça mecânica"; }
    }
}
