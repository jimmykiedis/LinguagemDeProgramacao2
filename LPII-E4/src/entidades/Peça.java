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
        OEM("oem"), ORIGINAL("original"), GENUINA("genuina");
        private final String texto;
        MarcaPeça(String texto) { this.texto = texto; }
        public static MarcaPeça fromTexto(String texto) {
            if (texto == null) throw new IllegalArgumentException("Marca não informada");
            String valor = texto.trim().toLowerCase(Locale.ROOT);
            for (MarcaPeça marca : values()) if (marca.texto.equals(valor)) return marca;
            throw new IllegalArgumentException("Marca inválida: " + texto);
        }
        @Override public String toString() { return texto; }
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
    public MarcaPeça getCategoria() { return marca; }
    public double getPreco() { return preco; }
    public Integer getDiasGarantia() { return dias_garantia; }
    public String getCor() { return cor; }
    public boolean isSobEncomenda() { return sob_encomenda; }
    public void setCodigo(int v) { codigo=v; }
    public void setNome(String v) { nome=v; }
    public void setMarca(MarcaPeça v) { marca=v; }
    public void setCategoria(MarcaPeça v) { marca=v; }
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

    static String colunas() {
        return "p.codigo,p.nome,p.marca,p.`preço`,p.sob_encomenda,m.categoria_mecânica,m.prazo_garantia,m.mecanico_proprio,a.categoria_acessorio,a.cor,a.funileiro_proprio";
    }
    static Peça criarVisao(ResultSet rs) throws SQLException {
        int id=rs.getInt("codigo"); String nome=rs.getString("nome");
        MarcaPeça marca=MarcaPeça.values()[rs.getInt("marca")]; double preco=rs.getDouble("preço");
        boolean sob_encomenda=rs.getBoolean("sob_encomenda");
        if (rs.getObject("categoria_mecânica") != null) return new PeçaMecânica(id,nome,marca,preco,
                PeçaMecânica.CategoriaMecânica.values()[rs.getInt("categoria_mecânica")],(Integer)rs.getObject("prazo_garantia"),rs.getBoolean("mecanico_proprio"),sob_encomenda);
        if (rs.getObject("categoria_acessorio") != null) return new PeçaAcessório(id,nome,marca,preco,
                PeçaAcessório.CategoriaAcessório.values()[rs.getInt("categoria_acessorio")],rs.getString("cor"),rs.getBoolean("funileiro_proprio"),sob_encomenda);
        return new Peça(id,nome,marca,preco,null,null,sob_encomenda);
    }
    static String consultaBase() {
        return " FROM peças p LEFT JOIN peça_mecânica m ON m.codigoId=p.codigo LEFT JOIN peça_acessórios a ON a.codigoId=p.codigo";
    }
    public static Peça[] getVisoes() {
        ArrayList<Peça> lista=new ArrayList<>();
        try(PreparedStatement ps=BD.conexao.prepareStatement("SELECT "+colunas()+consultaBase()+" ORDER BY p.codigo"); ResultSet rs=ps.executeQuery()) {
            while(rs.next()) lista.add(criarVisao(rs));
        } catch(SQLException|IllegalArgumentException e) { e.printStackTrace(); }
        return lista.toArray(new Peça[0]);
    }
    public static Peça buscarPeças(int codigo) { return buscarPor("p.codigo=?", codigo); }
    private static Peça buscarPor(String filtro,int valor) {
        try(PreparedStatement ps=BD.conexao.prepareStatement("SELECT "+colunas()+consultaBase()+" WHERE "+filtro)) {
            ps.setInt(1,valor); try(ResultSet rs=ps.executeQuery()) { return rs.next()?criarVisao(rs):null; }
        } catch(SQLException|IllegalArgumentException e) { e.printStackTrace(); return null; }
    }
    public static Peça buscarPeças(String nome) {
        try(PreparedStatement ps=BD.conexao.prepareStatement("SELECT "+colunas()+consultaBase()+" WHERE p.nome=?")) {
            ps.setString(1,nome); try(ResultSet rs=ps.executeQuery()) { return rs.next()?criarVisao(rs):null; }
        } catch(SQLException|IllegalArgumentException e) { e.printStackTrace(); return null; }
    }
    public static Peça[] buscarPeçasPorSinistro(int id) { return PeçaSinistro.buscarPeçasPorSinistro(id); }
    public String inserir() {
        try(PreparedStatement ps=BD.conexao.prepareStatement("INSERT INTO peças(codigo,nome,marca,`preço`,sob_encomenda) VALUES(?,?,?,?,?)")) {
            ps.setInt(1,codigo); ps.setString(2,nome); ps.setInt(3,marca.ordinal()); ps.setDouble(4,preco); ps.setBoolean(5,sob_encomenda); ps.executeUpdate(); return null;
        } catch(SQLException|NullPointerException e) { e.printStackTrace(); return "Erro ao inserir peça"; }
    }
    public static String inserirPeças(Peça p) { return p.inserir(); }
    public String alterar() {
        try(PreparedStatement ps=BD.conexao.prepareStatement("UPDATE peças SET nome=?,marca=?,`preço`=?,sob_encomenda=? WHERE codigo=?")) {
            ps.setString(1,nome); ps.setInt(2,marca.ordinal()); ps.setDouble(3,preco); ps.setBoolean(4,sob_encomenda); ps.setInt(5,codigo); ps.executeUpdate(); return null;
        } catch(SQLException|NullPointerException e) { e.printStackTrace(); return "Erro ao alterar peça"; }
    }
    public static String alterarPeças(Peça p) { return p.alterar(); }
    public static String removerPeças(int codigo) {
        return removerPeçasPor("codigo", codigo);
    }
    public static String removerPeças(String nome) {
        return removerPeçasPor("nome", nome);
    }

    /**
     * Remove uma peça e todos os seus vínculos em uma única transação.
     * A remoção explícita do lado dependente mantém o código correto mesmo
     * quando o banco foi criado com uma versão antiga do esquema, sem
     * ON DELETE CASCADE.
     */
    private static String removerPeçasPor(String coluna, Object valor) {
        Connection conexao = BD.conexao;
        boolean auto_commit_anterior = true;
        boolean transacao_iniciada = false;
        ArrayList<Integer> codigos = new ArrayList<>();

        try {
            auto_commit_anterior = conexao.getAutoCommit();
            conexao.setAutoCommit(false);
            transacao_iniciada = true;

            try (PreparedStatement consulta = conexao.prepareStatement(
                    "SELECT codigo FROM peças WHERE " + coluna + " = ?")) {
                consulta.setObject(1, valor);
                try (ResultSet resultados = consulta.executeQuery()) {
                    while (resultados.next()) codigos.add(resultados.getInt("codigo"));
                }
            }

            if (codigos.isEmpty()) {
                conexao.rollback();
                return "Peça não cadastrada";
            }

            for (int codigo : codigos) {
                try (PreparedStatement remover_vinculos = conexao.prepareStatement(
                        "DELETE FROM peças_sinistros WHERE peça_codigo = ?")) {
                    remover_vinculos.setInt(1, codigo);
                    remover_vinculos.executeUpdate();
                }
                try (PreparedStatement remover_mecanica = conexao.prepareStatement(
                        "DELETE FROM `peça_mecânica` WHERE codigoId = ?")) {
                    remover_mecanica.setInt(1, codigo);
                    remover_mecanica.executeUpdate();
                }
                try (PreparedStatement remover_acessorios = conexao.prepareStatement(
                        "DELETE FROM `peça_acessórios` WHERE codigoId = ?")) {
                    remover_acessorios.setInt(1, codigo);
                    remover_acessorios.executeUpdate();
                }
                try (PreparedStatement remover_peca = conexao.prepareStatement(
                        "DELETE FROM peças WHERE codigo = ?")) {
                    remover_peca.setInt(1, codigo);
                    if (remover_peca.executeUpdate() != 1) {
                        throw new SQLException("A peça " + codigo + " não foi removida");
                    }
                }
            }

            conexao.commit();
            return null;
        } catch (SQLException | RuntimeException e) {
            if (transacao_iniciada) {
                try {
                    conexao.rollback();
                } catch (SQLException rollback) {
                    e.addSuppressed(rollback);
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
