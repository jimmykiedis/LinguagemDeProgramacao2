package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import persistência.BD;

public class PeçaAcessórios extends Peça {
    public enum CategoriaAcessório {
        CARROCERIA("carroceria"), FAROL_LANTERNA("farol_lanterna"),
        PARABRISAS_VIGIA("parabrisas_vigia"), PAINEL("painel"), PEDAL("pedal"),
        ASSENTO("assento"), ASSOALHO("assoalho");
        private final String texto;
        CategoriaAcessório(String texto) { this.texto = texto; }
        public static CategoriaAcessório fromTexto(String texto) {
            if (texto == null) return null;
            String normalizado = texto.trim().toLowerCase(Locale.ROOT);
            for (CategoriaAcessório valor : values()) if (valor.texto.equals(normalizado)) return valor;
            throw new IllegalArgumentException("Categoria do acessório inválida: " + texto);
        }
        @Override public String toString() { return texto; }
    }

    private CategoriaAcessório categoriaAcessório;
    private String cor;
    private boolean funileiroProprio;

    public PeçaAcessórios(int codigo, String nome, MarcaPeça marca, double preco,
            CategoriaAcessório categoriaAcessório, String cor, boolean funileiroProprio) {
        this(codigo, nome, marca, preco, categoriaAcessório, cor, funileiroProprio, false);
    }

    public PeçaAcessórios(int codigo, String nome, MarcaPeça marca, double preco,
            CategoriaAcessório categoriaAcessório, String cor, boolean funileiroProprio, boolean sobEncomenda) {
        super(codigo, nome, marca, preco, null, cor);
        setSobEncomenda(sobEncomenda);
        this.categoriaAcessório = categoriaAcessório;
        this.cor = cor;
        this.funileiroProprio = funileiroProprio;
    }

    public PeçaAcessórios(int codigo, String nome, MarcaPeça marca, double preco,
            String categoriaAcessório, String cor) {
        this(codigo, nome, marca, preco, CategoriaAcessório.fromTexto(categoriaAcessório), cor, false);
    }

    public PeçaAcessórios() { this(0, null, null, 0.0, (CategoriaAcessório) null, null, false); }
    public CategoriaAcessório getCategoriaAcessório() { return categoriaAcessório; }
    @Override public String getCor() { return cor; }
    public boolean isFunileiroProprio() { return funileiroProprio; }
    public void setCategoriaAcessório(CategoriaAcessório valor) { categoriaAcessório = valor; }
    @Override public void setCor(String valor) { cor = valor; super.setCor(valor); }
    public void setFunileiroProprio(boolean valor) { funileiroProprio = valor; }

    public static PeçaAcessórios buscar(int codigo) {
        String sql = "SELECT p.codigo,p.nome,p.marca,p.`preço`,p.sob_encomenda,a.categoria_acessorio,a.cor,a.funileiro_proprio FROM peças p JOIN peça_acessórios a ON a.codigoId=p.codigo WHERE p.codigo=?";
        try (PreparedStatement ps = BD.conexao.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return new PeçaAcessórios(rs.getInt("codigo"), rs.getString("nome"),
                        MarcaPeça.fromTexto(rs.getString("marca")), rs.getDouble("preço"),
                        CategoriaAcessório.fromTexto(rs.getString("categoria_acessorio")),
                        rs.getString("cor"), rs.getBoolean("funileiro_proprio"), rs.getBoolean("sob_encomenda"));
            }
        } catch (SQLException e) { e.printStackTrace(); return null; }
    }

    @Override public String inserir() {
        boolean autoCommit;
        try {
            autoCommit = BD.conexao.getAutoCommit(); BD.conexao.setAutoCommit(false);
            String erro = super.inserir(); if (erro != null) throw new SQLException(erro);
            try (PreparedStatement sub = BD.conexao.prepareStatement(
                    "INSERT INTO peça_acessórios(categoria_acessorio,cor,funileiro_proprio,codigoId) VALUES(?,?,?,?)")) {
                sub.setString(1, categoriaAcessório.toString()); sub.setString(2, cor);
                sub.setBoolean(3, funileiroProprio); sub.setInt(4, codigo); sub.executeUpdate();
            }
            BD.conexao.commit(); BD.conexao.setAutoCommit(autoCommit); return null;
        } catch (SQLException | NullPointerException e) {
            try { BD.conexao.rollback(); BD.conexao.setAutoCommit(true); } catch (SQLException rollback) { e.addSuppressed(rollback); }
            e.printStackTrace(); return "Erro ao inserir peça de acessórios";
        }
    }

    @Override public String alterar() {
        String erro = super.alterar(); if (erro != null) return erro;
        try (PreparedStatement ps = BD.conexao.prepareStatement(
                "INSERT INTO peça_acessórios(categoria_acessorio,cor,funileiro_proprio,codigoId) VALUES(?,?,?,?) "
                + "ON DUPLICATE KEY UPDATE categoria_acessorio=VALUES(categoria_acessorio),cor=VALUES(cor),funileiro_proprio=VALUES(funileiro_proprio)")) {
            ps.setString(1, categoriaAcessório.toString()); ps.setString(2, cor);
            ps.setBoolean(3, funileiroProprio); ps.setInt(4, codigo); ps.executeUpdate();
            try (PreparedStatement remover = BD.conexao.prepareStatement("DELETE FROM peça_mecânica WHERE codigoId=?")) {
                remover.setInt(1, codigo); remover.executeUpdate();
            }
            return null;
        } catch (SQLException | NullPointerException e) { e.printStackTrace(); return "Erro ao alterar peça de acessórios"; }
    }
}
