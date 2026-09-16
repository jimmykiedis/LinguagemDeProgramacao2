package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import persistência.BD;

public class Peça {

    public enum MarcaPeça {
        OEM("oem"), ORIGINAL("original"), GENUINA("genuina");

        private final String texto;

        MarcaPeça(String texto) { this.texto = texto; }

        public static MarcaPeça fromTexto(String texto) {
            if (texto == null) throw new IllegalArgumentException("Marca da peca nao informada");
            String valor = texto.trim().toLowerCase(Locale.ROOT);
            for (MarcaPeça marca : values()) {
                if (marca.texto.equals(valor)) return marca;
            }
            throw new IllegalArgumentException("Marca da peca invalida: " + texto);
        }

        @Override
        public String toString() { return texto; }
    }

    protected int codigo;
    protected String nome;
    protected MarcaPeça marca;
    protected double preco;
    protected boolean mao_obra_propria;
    protected Integer dias_garantia;
    protected String cor;

    public Peça(int codigo, String nome, MarcaPeça marca, double preco,
            boolean mao_obra_propria, Integer dias_garantia, String cor) {
        this.codigo = codigo;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.mao_obra_propria = mao_obra_propria;
        this.dias_garantia = dias_garantia;
        this.cor = cor;
    }

    public Peça() { this(0, null, null, 0.0, false, null, null); }

    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public MarcaPeça getMarca() { return marca; }
    public MarcaPeça getCategoria() { return marca; }
    public double getPreco() { return preco; }
    public boolean getMaoObraPropria() { return mao_obra_propria; }
    public boolean isMaoObraPropria() { return mao_obra_propria; }
    public boolean getMaoDeObra() { return mao_obra_propria; }
    public Integer getDiasGarantia() { return dias_garantia; }
    public String getCor() { return cor; }

    public void setCodigo(int codigo) { this.codigo = codigo; }
    public void setNome(String nome) { this.nome = nome; }
    public void setMarca(MarcaPeça marca) { this.marca = marca; }
    public void setCategoria(MarcaPeça marca) { this.marca = marca; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setMaoObraPropria(boolean valor) { this.mao_obra_propria = valor; }
    public void setMaoDeObra(boolean valor) { this.mao_obra_propria = valor; }
    public void setDiasGarantia(Integer valor) { this.dias_garantia = valor; }
    public void setCor(String cor) { this.cor = cor; }

    public Peça getVisao() { return this; }

    @Override
    public String toString() { return codigo + " - " + nome + " [" + marca + "]"; }

    private static Peça criarVisao(ResultSet resultado) throws SQLException {
        int codigo = resultado.getInt("codigo");
        String nome = resultado.getString("nome");
        MarcaPeça marca = MarcaPeça.fromTexto(resultado.getString("marca"));
        double preco = resultado.getDouble("preco");
        boolean mao_obra_propria = resultado.getBoolean("mao_obra_propria");
        Integer dias_garantia = resultado.getObject("dias_garantia") == null
                ? null : resultado.getInt("dias_garantia");
        String cor = resultado.getString("cor");

        return new Peça(codigo, nome, marca, preco, mao_obra_propria,
                dias_garantia, cor);
    }

    private static final String COLUNAS =
            "codigo, nome, marca, preco, mao_obra_propria, dias_garantia, cor";

    public static Peça[] getVisoes() {
        ArrayList<Peça> visoes = new ArrayList<>();
        try (PreparedStatement comando = BD.conexao.prepareStatement(
                "SELECT " + COLUNAS + " FROM peças");
             ResultSet resultados = comando.executeQuery()) {
            while (resultados.next()) visoes.add(criarVisao(resultados));
        } catch (SQLException | IllegalArgumentException excecao) {
            excecao.printStackTrace();
        }
        return visoes.toArray(new Peça[0]);
    }

    public static Peça buscarPeças(int codigo) {
        try (PreparedStatement comando = BD.conexao.prepareStatement(
                "SELECT " + COLUNAS + " FROM peças WHERE codigo = ?")) {
            comando.setInt(1, codigo);
            try (ResultSet resultados = comando.executeQuery()) {
                return resultados.next() ? criarVisao(resultados) : null;
            }
        } catch (SQLException | IllegalArgumentException excecao) {
            excecao.printStackTrace();
            return null;
        }
    }

    public static Peça buscarPeças(String nome) {
        try (PreparedStatement comando = BD.conexao.prepareStatement(
                "SELECT " + COLUNAS + " FROM peças WHERE nome = ?")) {
            comando.setString(1, nome);
            try (ResultSet resultados = comando.executeQuery()) {
                return resultados.next() ? criarVisao(resultados) : null;
            }
        } catch (SQLException | IllegalArgumentException excecao) {
            excecao.printStackTrace();
            return null;
        }
    }

    public static Peça[] buscarPeçasPorSinistro(int sinistroId) {
        return PeçaSinistro.buscarPeçasPorSinistro(sinistroId);
    }

    private static void preencher(PreparedStatement comando, Peça peca) throws SQLException {
        comando.setInt(1, peca.codigo);
        comando.setString(2, peca.nome);
        comando.setString(3, peca.marca.toString());
        comando.setDouble(4, peca.preco);
        comando.setBoolean(5, peca.mao_obra_propria);
        if (peca.dias_garantia == null) comando.setNull(6, java.sql.Types.INTEGER);
        else comando.setInt(6, peca.dias_garantia);
        comando.setString(7, peca.cor);
    }

    public static String inserirPeças(Peça peca) {
        String sql = "INSERT INTO peças (" + COLUNAS + ") VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement comando = BD.conexao.prepareStatement(sql)) {
            preencher(comando, peca);
            comando.executeUpdate();
            return null;
        } catch (SQLException | NullPointerException excecao) {
            excecao.printStackTrace();
            return "Erro na insercao da peca no BD";
        }
    }

    public static String alterarPeças(Peça peca) {
        String sql = "UPDATE peças SET nome=?, marca=?, preco=?, mao_obra_propria=?,"
                + " dias_garantia=?, cor=?"
                + " WHERE codigo=?";
        try (PreparedStatement comando = BD.conexao.prepareStatement(sql)) {
            comando.setString(1, peca.nome);
            comando.setString(2, peca.marca.toString());
            comando.setDouble(3, peca.preco);
            comando.setBoolean(4, peca.mao_obra_propria);
            if (peca.dias_garantia == null) comando.setNull(5, java.sql.Types.INTEGER);
            else comando.setInt(5, peca.dias_garantia);
            comando.setString(6, peca.cor);
            comando.setInt(7, peca.codigo);
            comando.executeUpdate();
            return null;
        } catch (SQLException | NullPointerException excecao) {
            excecao.printStackTrace();
            return "Erro na alteracao da peca no BD";
        }
    }

    public static String removerPeças(int codigo) {
        try (PreparedStatement comando = BD.conexao.prepareStatement(
                "DELETE FROM peças WHERE codigo = ?")) {
            comando.setInt(1, codigo);
            comando.executeUpdate();
            return null;
        } catch (SQLException excecao) {
            excecao.printStackTrace();
            return "Erro na remocao da peca no BD";
        }
    }

    public static String removerPeças(String nome) {
        try (PreparedStatement comando = BD.conexao.prepareStatement(
                "DELETE FROM peças WHERE nome = ?")) {
            comando.setString(1, nome);
            comando.executeUpdate();
            return null;
        } catch (SQLException excecao) {
            excecao.printStackTrace();
            return "Erro na remocao da peca no BD";
        }
    }
}
