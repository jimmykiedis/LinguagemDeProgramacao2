package entidades;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import persistência.BD;

/** Orçamento identificado pela associação entre um sinistro e uma seguradora. */
public class Orçamento {

    public enum Status {
        aprovado("aprovado"), pendente("pendente"), recusado("recusado");
        private final String texto;
        Status(String texto) { this.texto = texto; }
        public static Status fromTexto(String texto) {
            if (texto != null) for (Status status : values())
                if (status.texto.equalsIgnoreCase(texto.trim())) return status;
            throw new IllegalArgumentException("Status de orçamento inválido: " + texto);
        }
        @Override public String toString() { return texto; }
    }

    private Status status;
    private Date data;
    private Sinistro sinistro;
    private Seguradora seguradora;
    private double valorTotal;
    private boolean possuiDanosOcultos;

    public Orçamento(Status status, Date data, Sinistro sinistro, Seguradora seguradora,
            double valorTotal, boolean possuiDanosOcultos) {
        this.status = status;
        this.data = data;
        this.sinistro = sinistro;
        this.seguradora = seguradora;
        this.valorTotal = valorTotal;
        this.possuiDanosOcultos = possuiDanosOcultos;
    }

    public Status getStatus() { return status; }
    public Date getData() { return data; }
    public Sinistro getSinistro() { return sinistro; }
    public Seguradora getSeguradora() { return seguradora; }
    public double getValorTotal() { return valorTotal; }
    public boolean getPossuiDanosOcultos() { return possuiDanosOcultos; }
    public boolean isPossuiDanosOcultos() { return possuiDanosOcultos; }
    public void setStatus(Status status) { this.status = status; }
    public void setData(Date data) { this.data = data; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
    public void setPossuiDanosOcultos(boolean possuiDanosOcultos) { this.possuiDanosOcultos = possuiDanosOcultos; }

    public Orçamento getVisao() {
        return new Orçamento(status, data, sinistro, seguradora, valorTotal, possuiDanosOcultos);
    }

    @Override public String toString() {
        return data + " - Sinistro #" + sinistro.getId() + " - " + seguradora.getNome() + " (" + status + ")";
    }

    public String toStringFull() {
        return "Orçamento " + status + " em " + data + "\n"
                + "  Sinistro: " + sinistro + "\n"
                + "  Seguradora: " + seguradora + "\n"
                + "  Valor total: R$ " + String.format(java.util.Locale.forLanguageTag("pt-BR"), "%.2f", valorTotal) + "\n"
                + "  Possui danos ocultos: " + (possuiDanosOcultos ? "sim" : "não");
    }

    private static Orçamento criar(ResultSet rs) throws SQLException {
        int idSinistro = rs.getInt("sinistro");
        String nomeSeguradora = rs.getString("seguradora");
        return new Orçamento(Status.fromTexto(rs.getString("status")), rs.getDate("data"),
                Sinistro.buscarSinistro(idSinistro), Seguradora.buscarSeguradora(nomeSeguradora),
                rs.getDouble("valor_total"), rs.getBoolean("possui_danos_ocultos"));
    }

    public static Orçamento[] getVisoes() {
        ArrayList<Orçamento> itens = new ArrayList<>();
        String sql = "SELECT data, sinistro, seguradora, status, valor_total, possui_danos_ocultos FROM Orçamentos ORDER BY data, sinistro, seguradora";
        try (PreparedStatement ps = BD.conexao.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) itens.add(criar(rs));
        } catch (SQLException | IllegalArgumentException e) { e.printStackTrace(); }
        return itens.toArray(new Orçamento[0]);
    }

    public static Orçamento buscarOrçamento(int idSinistro, String nomeSeguradora) {
        String sql = "SELECT data, sinistro, seguradora, status, valor_total, possui_danos_ocultos FROM Orçamentos WHERE sinistro = ? AND seguradora = ?";
        try (PreparedStatement ps = BD.conexao.prepareStatement(sql)) {
            ps.setInt(1, idSinistro);
            ps.setString(2, nomeSeguradora);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? criar(rs) : null; }
        } catch (SQLException | IllegalArgumentException e) { e.printStackTrace(); return null; }
    }

    public static boolean existeOrçamento(int idSinistro, String nomeSeguradora) {
        try (PreparedStatement ps = BD.conexao.prepareStatement(
                "SELECT 1 FROM Orçamentos WHERE sinistro = ? AND seguradora = ?")) {
            ps.setInt(1, idSinistro);
            ps.setString(2, nomeSeguradora);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static String inserirOrçamento(Orçamento o) {
        String sql = "INSERT INTO Orçamentos (data, sinistro, seguradora, status, valor_total, possui_danos_ocultos) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = BD.conexao.prepareStatement(sql)) {
            ps.setDate(1, o.data); ps.setInt(2, o.sinistro.getId()); ps.setString(3, o.seguradora.getNome());
            ps.setString(4, o.status.toString()); ps.setDouble(5, o.valorTotal); ps.setBoolean(6, o.possuiDanosOcultos);
            ps.executeUpdate(); return null;
        } catch (SQLException e) { e.printStackTrace(); return "Erro ao inserir orçamento no banco de dados"; }
    }

    public static String alterarOrçamento(Orçamento o) {
        String sql = "UPDATE Orçamentos SET data = ?, status = ?, valor_total = ?, possui_danos_ocultos = ? WHERE sinistro = ? AND seguradora = ?";
        try (PreparedStatement ps = BD.conexao.prepareStatement(sql)) {
            ps.setDate(1, o.data); ps.setString(2, o.status.toString()); ps.setDouble(3, o.valorTotal);
            ps.setBoolean(4, o.possuiDanosOcultos); ps.setInt(5, o.sinistro.getId()); ps.setString(6, o.seguradora.getNome());
            return ps.executeUpdate() == 1 ? null : "Orçamento não cadastrado";
        } catch (SQLException e) { e.printStackTrace(); return "Erro ao alterar orçamento no banco de dados"; }
    }

    public static String removerOrçamento(int idSinistro, String nomeSeguradora) {
        try (PreparedStatement ps = BD.conexao.prepareStatement("DELETE FROM Orçamentos WHERE sinistro = ? AND seguradora = ?")) {
            ps.setInt(1, idSinistro); ps.setString(2, nomeSeguradora);
            return ps.executeUpdate() == 1 ? null : "Orçamento não cadastrado";
        } catch (SQLException e) { e.printStackTrace(); return "Erro ao remover orçamento do banco de dados"; }
    }

    /** Pesquisa atributos do orçamento e de suas entidades relacionadas, inclusive peças do sinistro. */
    public static ArrayList<Orçamento> pesquisarOrçamentos(Status status, Date dataMinima,
            Boolean danosOcultos, String cidadeSeguradora, Sinistro.GrauMonta grauMonta,
            String cidadeSinistro, Boolean perdaTotal, Peça.MarcaPeça marcaPeça) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT o.sinistro, o.seguradora FROM Orçamentos o JOIN sinistros s ON s.id=o.sinistro JOIN seguradoras g ON g.nome=o.seguradora WHERE 1=1");
        if (status != null) sql.append(" AND o.status = ?");
        if (dataMinima != null) sql.append(" AND o.data >= ?");
        if (danosOcultos != null) sql.append(" AND o.possui_danos_ocultos = ?");
        if (cidadeSeguradora != null && !cidadeSeguradora.trim().isEmpty()) sql.append(" AND g.cidade LIKE ?");
        if (grauMonta != null) sql.append(" AND s.grau_monta = ?");
        if (cidadeSinistro != null && !cidadeSinistro.trim().isEmpty()) sql.append(" AND s.cidade LIKE ?");
        if (perdaTotal != null) sql.append(" AND s.perda_total = ?");
        if (marcaPeça != null) sql.append(" AND EXISTS (SELECT 1 FROM peças_sinistros ps JOIN peças p ON p.codigo=ps.peça_codigo WHERE ps.sinistro_id=s.id AND p.marca=?)");
        sql.append(" ORDER BY o.data, o.sinistro, o.seguradora");
        ArrayList<Orçamento> resultado = new ArrayList<>();
        try (PreparedStatement ps = BD.conexao.prepareStatement(sql.toString())) {
            int i = 0;
            if (status != null) ps.setString(++i, status.toString());
            if (dataMinima != null) ps.setDate(++i, dataMinima);
            if (danosOcultos != null) ps.setBoolean(++i, danosOcultos);
            if (cidadeSeguradora != null && !cidadeSeguradora.trim().isEmpty()) ps.setString(++i, "%" + cidadeSeguradora.trim() + "%");
            if (grauMonta != null) ps.setString(++i, grauMonta.toString());
            if (cidadeSinistro != null && !cidadeSinistro.trim().isEmpty()) ps.setString(++i, "%" + cidadeSinistro.trim() + "%");
            if (perdaTotal != null) ps.setBoolean(++i, perdaTotal);
            if (marcaPeça != null) ps.setString(++i, marcaPeça.toString());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Orçamento o = buscarOrçamento(rs.getInt("sinistro"), rs.getString("seguradora"));
                    if (o != null) resultado.add(o);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return resultado;
    }
}
