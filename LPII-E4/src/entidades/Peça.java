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
    protected Integer diasGarantia;
    protected String cor;
    protected boolean sobEncomenda;

    public Peça(int codigo, String nome, MarcaPeça marca, double preco,
            Integer diasGarantia, String cor) {
        this(codigo, nome, marca, preco, diasGarantia, cor, false);
    }
    public Peça(int codigo, String nome, MarcaPeça marca, double preco,
            Integer diasGarantia, String cor, boolean sobEncomenda) {
        this.codigo=codigo; this.nome=nome; this.marca=marca; this.preco=preco;
        this.diasGarantia=diasGarantia; this.cor=cor; this.sobEncomenda=sobEncomenda;
    }
    public Peça() { this(0,null,null,0.0,null,null); }
    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public MarcaPeça getMarca() { return marca; }
    public MarcaPeça getCategoria() { return marca; }
    public double getPreco() { return preco; }
    public Integer getDiasGarantia() { return diasGarantia; }
    public String getCor() { return cor; }
    public boolean isSobEncomenda() { return sobEncomenda; }
    public void setCodigo(int v) { codigo=v; }
    public void setNome(String v) { nome=v; }
    public void setMarca(MarcaPeça v) { marca=v; }
    public void setCategoria(MarcaPeça v) { marca=v; }
    public void setPreco(double v) { preco=v; }
    public void setDiasGarantia(Integer v) { diasGarantia=v; }
    public void setCor(String v) { cor=v; }
    public void setSobEncomenda(boolean v) { sobEncomenda=v; }
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
        MarcaPeça marca=MarcaPeça.fromTexto(rs.getString("marca")); double preco=rs.getDouble("preço");
        boolean sobEncomenda=rs.getBoolean("sob_encomenda");
        String catMec=rs.getString("categoria_mecânica");
        if (catMec != null) return new PeçaMecânica(id,nome,marca,preco,
                PeçaMecânica.CategoriaMecânica.fromTexto(catMec),(Integer)rs.getObject("prazo_garantia"),rs.getBoolean("mecanico_proprio"),sobEncomenda);
        String catAcessorio=rs.getString("categoria_acessorio");
        if (catAcessorio != null) return new PeçaAcessório(id,nome,marca,preco,
                PeçaAcessório.CategoriaAcessório.fromTexto(catAcessorio),rs.getString("cor"),rs.getBoolean("funileiro_proprio"),sobEncomenda);
        return new Peça(id,nome,marca,preco,null,null,sobEncomenda);
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
            ps.setInt(1,codigo); ps.setString(2,nome); ps.setString(3,marca.toString()); ps.setDouble(4,preco); ps.setBoolean(5,sobEncomenda); ps.executeUpdate(); return null;
        } catch(SQLException|NullPointerException e) { e.printStackTrace(); return "Erro ao inserir peça"; }
    }
    public static String inserirPeças(Peça p) { return p.inserir(); }
    public String alterar() {
        try(PreparedStatement ps=BD.conexao.prepareStatement("UPDATE peças SET nome=?,marca=?,`preço`=?,sob_encomenda=? WHERE codigo=?")) {
            ps.setString(1,nome); ps.setString(2,marca.toString()); ps.setDouble(3,preco); ps.setBoolean(4,sobEncomenda); ps.setInt(5,codigo); ps.executeUpdate(); return null;
        } catch(SQLException|NullPointerException e) { e.printStackTrace(); return "Erro ao alterar peça"; }
    }
    public static String alterarPeças(Peça p) { return p.alterar(); }
    public static String removerPeças(int codigo) {
        try(PreparedStatement ps=BD.conexao.prepareStatement("DELETE FROM peças WHERE codigo=?")) { ps.setInt(1,codigo); ps.executeUpdate(); return null; }
        catch(SQLException e) { e.printStackTrace(); return "Erro ao remover peça"; }
    }
    public static String removerPeças(String nome) {
        try(PreparedStatement ps=BD.conexao.prepareStatement("DELETE FROM peças WHERE nome=?")) { ps.setString(1,nome); ps.executeUpdate(); return null; }
        catch(SQLException e) { e.printStackTrace(); return "Erro ao remover peça"; }
    }
}
