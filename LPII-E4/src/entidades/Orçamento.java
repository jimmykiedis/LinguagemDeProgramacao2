package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Locale;
import persistência.BD;

public class Orçamento {

    public enum Status {
        aprovado, pendente, recusado
    }

    private int sequencial;
    private Status status;
    private Timestamp data;
    private Sinistro sinistro;
    private Seguradora seguradora;
    private double valor_total;
    private boolean possui_danos_ocultos;

    public Orçamento(Status status, Timestamp data, Sinistro sinistro, Seguradora seguradora,
            double valor_total, boolean possui_danos_ocultos) {
        this(0, status, data, sinistro, seguradora, valor_total, possui_danos_ocultos);
    }

    public Orçamento(int sequencial, Status status, Timestamp data, Sinistro sinistro, Seguradora seguradora,
            double valor_total, boolean possui_danos_ocultos) {
        this.sequencial = sequencial;
        this.status = status;
        this.data = data;
        this.sinistro = sinistro;
        this.seguradora = seguradora;
        this.valor_total = valor_total;
        this.possui_danos_ocultos = possui_danos_ocultos;
    }

    public Status getStatus() { return status; }
    public int getSequencial() { return sequencial; }
    public Timestamp getData() { return data; }
    public Sinistro getSinistro() { return sinistro; }
    public Seguradora getSeguradora() { return seguradora; }
    public double getValorTotal() { return valor_total; }
    public boolean getPossuiDanosOcultos() { return possui_danos_ocultos; }
    public void setStatus(Status status) { this.status = status; }
    public void setSequencial(int sequencial) { this.sequencial = sequencial; }
    public void setData(Timestamp data) { this.data = data; }
    public void setValorTotal(double valor_total) { this.valor_total = valor_total; }
    public void setPossuiDanosOcultos(boolean possui_danos_ocultos) { this.possui_danos_ocultos = possui_danos_ocultos; }

    public Orçamento getVisao() {
        return new Orçamento(sequencial, status, data, sinistro, seguradora, valor_total, possui_danos_ocultos);
    }

    @Override public String toString() {
        return "[" + sequencial + "] " + sinistro.getSegurado()
                + " [" + sinistro.getGrauMonta() + "] - " + seguradora.getNome()
                + " - " + status + " (sinistro #" + sinistro.getId() + ")";
    }

    public String toStringFull() {
        String str = seguradora.toStringFull() + " -- " + sinistro.toStringFull();

        Peça[] pecas = sinistro.getPeças();
        if (pecas.length == 0) {
            str += "\n\t-- sem peças";
        } else {
            for (Peça peca : pecas) {
                str += "\n\t-- " + peca.toStringFull();
            }
        }
        str += "\n\tstatus: " + status
                + " - valor: R$ "
                + String.format(Locale.forLanguageTag("pt-BR"), "%.2f", valor_total)
                + " - data: " + formatarDataHora(data)
                + " - danos ocultos: "
                + (possui_danos_ocultos ? "sim" : "não");
        return str.trim();
    }

    /* meu código trata de maneira diferente a data e hora por escolha */
    private String formatarDataHora(Timestamp valor) {
        if (valor == null) return "";
        return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(valor);
    }

    public static Orçamento[] getVisoes() {
        ArrayList<Orçamento> visoes = new ArrayList<>();
        String sql = "SELECT sequencial, sinistro, seguradora, status FROM Orçamentos ORDER BY data, sinistro, seguradora";
        ResultSet lista_resultados = null;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                int id_sinistro = lista_resultados.getInt("sinistro");
                String nome_seguradora = lista_resultados.getString("seguradora");
                visoes.add(new Orçamento(lista_resultados.getInt("sequencial"),
                        Status.values()[lista_resultados.getInt("status")],
                        null,
                        Sinistro.buscarSinistro(id_sinistro).getVisao(),
                        Seguradora.buscarSeguradora(nome_seguradora).getVisao(),
                        0,
                        false));
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
        }
        return visoes.toArray(new Orçamento[visoes.size()]);
    }

    public static int últimoSequencial() {
        String sql = "SELECT MAX(sequencial) AS sequencial FROM Orçamentos";
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();
            int sequencial = resultado.next() ? resultado.getInt("sequencial") : 0;
            resultado.close();
            comando.close();
            return sequencial;
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
            return 0;
        }
    }

    public static Orçamento buscarOrçamento(int sequencial) {
        String sql = "SELECT * FROM Orçamentos WHERE sequencial = ?";
        ResultSet lista_resultados = null;
        Orçamento orçamento = null;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setInt(1, sequencial);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                orçamento = new Orçamento(sequencial,
                        Status.values()[lista_resultados.getInt("status")],
                        lista_resultados.getTimestamp("data"),
                        Sinistro.buscarSinistro(lista_resultados.getInt("sinistro")),
                        Seguradora.buscarSeguradora(lista_resultados.getString("seguradora")),
                        lista_resultados.getDouble("valor_total"),
                        lista_resultados.getBoolean("possui_danos_ocultos"));
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
            orçamento = null;
        }
        return orçamento;
    }

    public static boolean existeOrçamento(int id_sinistro, String nome_seguradora) {
        String sql = "SELECT sinistro FROM Orçamentos WHERE sinistro = ? AND seguradora = ?";
        ResultSet lista_resultados = null;
        boolean existe = false;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setInt(1, id_sinistro);
            comando.setString(2, nome_seguradora);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                existe = true;
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
        }
        return existe;
    }

    public static boolean existeOrçamento(int sequencial) {
        String sql = "SELECT sequencial FROM Orçamentos WHERE sequencial = ?";
        ResultSet lista_resultados = null;
        boolean existe = false;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setInt(1, sequencial);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                existe = true;
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
        }
        return existe;
    }

    public static String inserirOrçamento(Orçamento orçamento) {
        String sql = "INSERT INTO Orçamentos (data, sinistro, seguradora, status, valor_total, possui_danos_ocultos)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setTimestamp(1, orçamento.getData());
            comando.setInt(2, orçamento.getSinistro().getId());
            comando.setString(3, orçamento.getSeguradora().getNome());
            comando.setInt(4, orçamento.getStatus().ordinal());
            comando.setDouble(5, orçamento.getValorTotal());
            comando.setBoolean(6, orçamento.getPossuiDanosOcultos());
            comando.executeUpdate();
            comando.close();
            return null;
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace ();
            return "Erro ao inserir orçamento no banco de dados";
        }
    }

    public static String alterarOrçamento(Orçamento orçamento) {
        String sql = "UPDATE Orçamentos SET status = ?, valor_total = ?,"
                + " possui_danos_ocultos = ? WHERE sequencial = ?";
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setInt(1, orçamento.getStatus().ordinal());
            comando.setDouble(2, orçamento.getValorTotal());
            comando.setBoolean(3, orçamento.getPossuiDanosOcultos());
            comando.setInt(4, orçamento.getSequencial());
            comando.executeUpdate();
            comando.close();
            return null;
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace ();
            return "Erro na alteração do orçamento no BD";
        }
    }

    public static String removerOrçamento(int sequencial) {
        String sql = "DELETE FROM Orçamentos WHERE sequencial = ?";
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setInt(1, sequencial);
            comando.executeUpdate();
            comando.close();
            return null;
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace ();
            return "Erro na Remoção do Orçamento no BD";
        }
    }

    public static ArrayList<Orçamento> pesquisarOrçamentos(Timestamp data_mínima,
            Peça.MarcaPeça marca, Boolean atendimento_24h, Sinistro.GrauMonta grau_monta,
            Integer prazo_garantia_mínimo, String cor_acessório, boolean todos_objetos) {
        String sql = "SELECT O.sequencial "
                + "FROM Orçamentos O, sinistros S, seguradoras G "
                + "WHERE O.sinistro = S.id AND O.seguradora = G.nome";
        if (data_mínima != null) sql += " AND O.data >= ?";
        if (atendimento_24h != null) sql += " AND G.possui_atendimento_24h = ?";
        if (grau_monta != null) sql += " AND S.grau_monta = ?";
        sql += " ORDER BY O.data, O.sinistro, O.seguradora";

        ResultSet lista_resultados = null;
        ArrayList<Orçamento> orçamentos_selecionados = new ArrayList<>();
        int indice = 0;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            if (data_mínima != null) comando.setTimestamp(++indice, data_mínima);
            if (atendimento_24h != null) comando.setBoolean(++indice, atendimento_24h);
            if (grau_monta != null) comando.setInt(++indice, grau_monta.ordinal());

            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                Orçamento orçamento_pesquisado = buscarOrçamento(lista_resultados.getInt("sequencial"));
                if (orçamento_pesquisado == null) continue;

                Peça[] peças_sinistro = orçamento_pesquisado.getSinistro().getPeças();
                if (peçasSinistroAtendemFiltros(peças_sinistro, marca,
                        prazo_garantia_mínimo, cor_acessório, todos_objetos)) {
                    orçamentos_selecionados.add(orçamento_pesquisado);
                }
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
        }
        return orçamentos_selecionados;
    }

    private static boolean peçasSinistroAtendemFiltros(Peça[] peças_sinistro,
            Peça.MarcaPeça marca, Integer prazo_garantia_mínimo,
            String cor_acessório, boolean todos_objetos) {
        int total_peças_não_atendem_filtros = 0;
        for (Peça peça : peças_sinistro) {
            if ((marca != null && peça.getMarca() != marca)
                    || !isOkPesquisaEmPeçasMecânicas(peça, prazo_garantia_mínimo)
                    || !isOkPesquisaEmPeçasAcessórias(peça, cor_acessório)) {
                total_peças_não_atendem_filtros++;
                if (todos_objetos) return false;
            }
        }
        if (total_peças_não_atendem_filtros == 0) return true;
        if (todos_objetos || total_peças_não_atendem_filtros == peças_sinistro.length) return false;
        return true;
    }

    private static boolean isOkPesquisaEmPeçasMecânicas(Peça peça, Integer prazo_garantia_mínimo) {
        if (prazo_garantia_mínimo == null) return true;

        boolean pesquisa_ok = false;
        if (peça instanceof PeçaMecânica) {
            PeçaMecânica peça_mecânica = (PeçaMecânica) peça;
            Integer prazo_garantia = peça_mecânica.getPrazoGarantia();
            if (prazo_garantia != null && prazo_garantia >= prazo_garantia_mínimo) {
                pesquisa_ok = true;
            }
        }
        return pesquisa_ok;
    }

    private static boolean isOkPesquisaEmPeçasAcessórias(Peça peça, String cor_acessório) {
        if (cor_acessório == null || cor_acessório.trim().isEmpty()) return true;

        boolean pesquisa_ok = false;
        if (peça instanceof PeçaAcessório) {
            PeçaAcessório peça_acessória = (PeçaAcessório) peça;
            String cor_peça = peça_acessória.getCor();
            String cor_procurada = cor_acessório.trim().toLowerCase(Locale.ROOT);
            if (cor_peça != null && cor_peça.toLowerCase(Locale.ROOT).contains(cor_procurada)) {
                pesquisa_ok = true;
            }
        }
        return pesquisa_ok;
    }
}
