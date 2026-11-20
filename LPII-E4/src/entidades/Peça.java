package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import persistência.BD;

public class Peça {
    public enum MarcaPeça {
        oem, original, genuína
    }

    protected int codigo;
    protected String nome;
    protected MarcaPeça marca;
    protected double preco;
    protected Integer dias_garantia;
    protected String cor;
    protected boolean sob_encomenda;

    public Peça(int codigo, String nome, MarcaPeça marca, double preco,
            Integer dias_garantia, String cor) {
        this(codigo, nome, marca, preco, dias_garantia, cor, false);
    }
    public Peça(int codigo, String nome, MarcaPeça marca, double preco,
            Integer dias_garantia, String cor, boolean sob_encomenda) {
        this.codigo=codigo; this.nome=nome; this.marca=marca; this.preco=preco;
        this.dias_garantia=dias_garantia; this.cor=cor; this.sob_encomenda=sob_encomenda;
    }
    public Peça() { this(0,null,null,0.0,null,null); }
    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public MarcaPeça getMarca() { return marca; }
    public double getPreco() { return preco; }
    public Integer getDiasGarantia() { return dias_garantia; }
    public String getCor() { return cor; }
    public boolean getSobEncomenda() { return sob_encomenda; }
    public void setCodigo(int v) { codigo=v; }
    public void setNome(String v) { nome=v; }
    public void setMarca(MarcaPeça v) { marca=v; }
    public void setPreco(double v) { preco=v; }
    public void setDiasGarantia(Integer v) { dias_garantia=v; }
    public void setCor(String v) { cor=v; }
    public void setSobEncomenda(boolean v) { sob_encomenda=v; }
    public Peça getVisao() { return this; }
    @Override public String toString() {
        String subtipo = this instanceof PeçaMecânica ? "Peça Mecânica"
                : this instanceof PeçaAcessório ? "Peça Acessórios" : "Peça";
        return "[" + codigo + "] " + subtipo + ": " + nome + " [" + marca + "]";
    }

    public String toStringFull() {
        String str = "[" + codigo + "] " + nome + " [" + marca + "] - R$ "
                + String.format(Locale.forLanguageTag("pt-BR"), "%.2f", preco)
                + " - sob encomenda: " + (sob_encomenda ? "sim" : "não");
        return str;
    }

    static String colunas() {
        return "p.codigo,p.nome,p.marca,p.`preço`,p.sob_encomenda,m.categoria_mecânica,m.prazo_garantia,m.mecânico_próprio,a.categoria_acessorio,a.cor,a.funileiro_próprio";
    }
    static Peça criarVisao(ResultSet rs) throws SQLException {
        int id=rs.getInt("codigo"); String nome=rs.getString("nome");
        MarcaPeça marca=MarcaPeça.values()[rs.getInt("marca")]; double preco=rs.getDouble("preço");
        boolean sob_encomenda=rs.getBoolean("sob_encomenda");
        if (rs.getObject("categoria_mecânica") != null) return new PeçaMecânica(id,nome,marca,preco,
                PeçaMecânica.CategoriaMecânica.values()[rs.getInt("categoria_mecânica")],(Integer)rs.getObject("prazo_garantia"),rs.getBoolean("mecânico_próprio"),sob_encomenda);
        if (rs.getObject("categoria_acessorio") != null) return new PeçaAcessório(id,nome,marca,preco,
                PeçaAcessório.CategoriaAcessório.values()[rs.getInt("categoria_acessorio")],rs.getString("cor"),rs.getBoolean("funileiro_próprio"),sob_encomenda);
        return new Peça(id,nome,marca,preco,null,null,sob_encomenda);
    }
    static String consultaBase() {
        return " FROM peças p LEFT JOIN peça_mecânica m ON m.codigoId=p.codigo LEFT JOIN peça_acessórios a ON a.codigoId=p.codigo";
    }
    public static Peça[] getVisoes() {
        ArrayList<Peça> visoes = new ArrayList<>();
        ResultSet lista_resultados = null;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(
                    "SELECT " + colunas() + consultaBase() + " ORDER BY p.codigo");
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                visoes.add(criarVisao(lista_resultados));
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException | IllegalArgumentException excecao) {
            excecao.printStackTrace();
        }
        return visoes.toArray(new Peça[visoes.size()]);
    }
    public static Peça buscarPeças(int codigo) { return buscarPor("p.codigo=?", codigo); }
    private static Peça buscarPor(String filtro, int valor) {
        Peça peça = null;
        ResultSet lista_resultados = null;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(
                    "SELECT " + colunas() + consultaBase() + " WHERE " + filtro);
            comando.setInt(1, valor);
            lista_resultados = comando.executeQuery();
            if (lista_resultados.next()) {
                peça = criarVisao(lista_resultados);
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException | IllegalArgumentException excecao) {
            excecao.printStackTrace();
        }
        return peça;
    }
    public static Peça buscarPeças(String nome) {
        Peça peça = null;
        ResultSet lista_resultados = null;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(
                    "SELECT " + colunas() + consultaBase() + " WHERE p.nome=?");
            comando.setString(1, nome);
            lista_resultados = comando.executeQuery();
            if (lista_resultados.next()) {
                peça = criarVisao(lista_resultados);
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException | IllegalArgumentException excecao) {
            excecao.printStackTrace();
        }
        return peça;
    }
    public static Peça[] buscarPeçasPorSinistro(int id) { return PeçaSinistro.buscarPeçasPorSinistro(id); }
    public String inserir() {
        String erro = null;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(
                    "INSERT INTO peças(codigo,nome,marca,`preço`,sob_encomenda) VALUES(?,?,?,?,?)");
            comando.setInt(1, codigo);
            comando.setString(2, nome);
            comando.setInt(3, marca.ordinal());
            comando.setDouble(4, preco);
            comando.setBoolean(5, sob_encomenda);
            comando.executeUpdate();
            comando.close();
        } catch (SQLException | NullPointerException excecao) {
            excecao.printStackTrace();
            erro = "Erro ao inserir peça";
        }
        return erro;
    }
    public static String inserirPeças(Peça p) { return p.inserir(); }
    public String alterar() {
        String erro = null;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(
                    "UPDATE peças SET nome=?,marca=?,`preço`=?,sob_encomenda=? WHERE codigo=?");
            comando.setString(1, nome);
            comando.setInt(2, marca.ordinal());
            comando.setDouble(3, preco);
            comando.setBoolean(4, sob_encomenda);
            comando.setInt(5, codigo);
            comando.executeUpdate();
            comando.close();
        } catch (SQLException | NullPointerException excecao) {
            excecao.printStackTrace();
            erro = "Erro ao alterar peça";
        }
        return erro;
    }
    public static String alterarPeças(Peça p) { return p.alterar(); }
    public static String removerPeças(int codigo) {
        return removerPeçasPor("codigo", codigo);
    }
    public static String removerPeças(String nome) {
        return removerPeçasPor("nome", nome);
    }

    /** Remove uma peça e seus vínculos em uma única transação. */
    private static String removerPeçasPor(String coluna, Object valor) {
        Connection conexao = BD.conexao;
        boolean auto_commit_anterior = true;
        boolean transacao_iniciada = false;
        ArrayList<Integer> codigos = new ArrayList<>();

        try {
            auto_commit_anterior = conexao.getAutoCommit();
            conexao.setAutoCommit(false);
            transacao_iniciada = true;

            PreparedStatement consulta = conexao.prepareStatement(
                    "SELECT codigo FROM peças WHERE " + coluna + " = ?");
            consulta.setObject(1, valor);
            ResultSet resultados = consulta.executeQuery();
            while (resultados.next()) codigos.add(resultados.getInt("codigo"));
            resultados.close();
            consulta.close();

            if (codigos.isEmpty()) {
                conexao.rollback();
                return "Peça não cadastrada";
            }

            for (int codigo : codigos) {
                PreparedStatement remover_vinculos = conexao.prepareStatement(
                        "DELETE FROM peças_sinistros WHERE peça_codigo = ?");
                remover_vinculos.setInt(1, codigo);
                remover_vinculos.executeUpdate();
                remover_vinculos.close();

                PreparedStatement remover_mecanica = conexao.prepareStatement(
                        "DELETE FROM `peça_mecânica` WHERE codigoId = ?");
                remover_mecanica.setInt(1, codigo);
                remover_mecanica.executeUpdate();
                remover_mecanica.close();

                PreparedStatement remover_acessorios = conexao.prepareStatement(
                        "DELETE FROM `peça_acessórios` WHERE codigoId = ?");
                remover_acessorios.setInt(1, codigo);
                remover_acessorios.executeUpdate();
                remover_acessorios.close();

                PreparedStatement remover_peca = conexao.prepareStatement(
                        "DELETE FROM peças WHERE codigo = ?");
                remover_peca.setInt(1, codigo);
                if (remover_peca.executeUpdate() != 1) {
                    throw new SQLException("A peça " + codigo + " não foi removida");
                }
                remover_peca.close();
            }

            conexao.commit();
            return null;
        } catch (SQLException | RuntimeException e) {
            if (transacao_iniciada) {
                try {
                    conexao.rollback();
                } catch (SQLException rollback) {
                    rollback.printStackTrace();
                }
            }
            e.printStackTrace();
            return "Erro ao remover peça";
        } finally {
            if (transacao_iniciada) {
                try {
                    conexao.setAutoCommit(auto_commit_anterior);
                } catch (SQLException restaura_auto_commit) {
                    restaura_auto_commit.printStackTrace();
                }
            }
        }
    }
}
