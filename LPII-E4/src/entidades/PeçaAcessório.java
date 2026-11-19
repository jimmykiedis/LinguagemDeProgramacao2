package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import persistência.BD;

public class PeçaAcessório extends Peça {
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

    private CategoriaAcessório categoria_acessório;
    private String cor;
    private boolean funileiro_proprio;

    public PeçaAcessório(int codigo, String nome, MarcaPeça marca, double preco,
            CategoriaAcessório categoria_acessório, String cor, boolean funileiro_proprio) {
        this(codigo, nome, marca, preco, categoria_acessório, cor, funileiro_proprio, false);
    }

    public PeçaAcessório(int codigo, String nome, MarcaPeça marca, double preco,
            CategoriaAcessório categoria_acessório, String cor, boolean funileiro_proprio, boolean sob_encomenda) {
        super(codigo, nome, marca, preco, null, cor);
        setSobEncomenda(sob_encomenda);
        this.categoria_acessório = categoria_acessório;
        this.cor = cor;
        this.funileiro_proprio = funileiro_proprio;
    }

    public PeçaAcessório(int codigo, String nome, MarcaPeça marca, double preco,
            String categoria_acessório, String cor) {
        this(codigo, nome, marca, preco, CategoriaAcessório.fromTexto(categoria_acessório), cor, false);
    }

    public PeçaAcessório() { this(0, null, null, 0.0, (CategoriaAcessório) null, null, false); }
    public CategoriaAcessório getCategoriaAcessório() { return categoria_acessório; }
    @Override public String getCor() { return cor; }
    public boolean isFunileiroProprio() { return funileiro_proprio; }
    public void setCategoriaAcessório(CategoriaAcessório valor) { categoria_acessório = valor; }
    @Override public void setCor(String valor) { cor = valor; super.setCor(valor); }
    public void setFunileiroProprio(boolean valor) { funileiro_proprio = valor; }

    public static PeçaAcessório buscar(int codigo) {
        String sql = "SELECT p.codigo,p.nome,p.marca,p.`preço`,p.sob_encomenda,a.categoria_acessorio,a.cor,a.funileiro_proprio FROM peças p JOIN peça_acessórios a ON a.codigoId=p.codigo WHERE p.codigo=?";
        try (PreparedStatement ps = BD.conexao.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return new PeçaAcessório(rs.getInt("codigo"), rs.getString("nome"),
                        MarcaPeça.values()[rs.getInt("marca")], rs.getDouble("preço"),
                        CategoriaAcessório.values()[rs.getInt("categoria_acessorio")],
                        rs.getString("cor"), rs.getBoolean("funileiro_proprio"), rs.getBoolean("sob_encomenda"));
            }
        } catch (SQLException e) { e.printStackTrace(); return null; }
    }

    @Override public String inserir() {
        boolean auto_commit;
        try {
            auto_commit = BD.conexao.getAutoCommit(); BD.conexao.setAutoCommit(false);
            String erro = super.inserir(); if (erro != null) throw new SQLException(erro);
            try (PreparedStatement sub = BD.conexao.prepareStatement(
                    "INSERT INTO peça_acessórios(categoria_acessorio,cor,funileiro_proprio,codigoId) VALUES(?,?,?,?)")) {
                sub.setInt(1, categoria_acessório.ordinal()); sub.setString(2, cor);
                sub.setBoolean(3, funileiro_proprio); sub.setInt(4, codigo); sub.executeUpdate();
            }
            BD.conexao.commit(); BD.conexao.setAutoCommit(auto_commit); return null;
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
            ps.setInt(1, categoria_acessório.ordinal()); ps.setString(2, cor);
            ps.setBoolean(3, funileiro_proprio); ps.setInt(4, codigo); ps.executeUpdate();
            try (PreparedStatement remover = BD.conexao.prepareStatement("DELETE FROM peça_mecânica WHERE codigoId=?")) {
                remover.setInt(1, codigo); remover.executeUpdate();
            }
            return null;
        } catch (SQLException | NullPointerException e) { e.printStackTrace(); return "Erro ao alterar peça de acessórios"; }
    }
}
