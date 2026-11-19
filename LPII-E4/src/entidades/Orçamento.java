package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Locale;
import persistência.BD;

/** Orçamento identificado pela associação entre um sinistro e uma seguradora. */
public class Orçamento {

    public enum Status {
        aprovado("aprovado"), pendente("pendente"), recusado("recusado");
        private final String texto;
        Status(String texto) { this.texto = texto; }
        public static Status fromTexto(String texto_status) {
            if (texto_status != null) for (Status status : values())
                if (status.texto.equalsIgnoreCase(texto_status.trim())) return status;
            throw new IllegalArgumentException("Status de orçamento inválido: " + texto_status);
        }
        @Override public String toString() { return texto; }
    }

    private Status status;
    private Timestamp data;
    private Sinistro sinistro;
    private Seguradora seguradora;
    private double valor_total;
    private boolean possui_danos_ocultos;

    public Orçamento(Status status, Timestamp data, Sinistro sinistro, Seguradora seguradora,
            double valor_total, boolean possui_danos_ocultos) {
        this.status = status;
        this.data = data;
        this.sinistro = sinistro;
        this.seguradora = seguradora;
        this.valor_total = valor_total;
        this.possui_danos_ocultos = possui_danos_ocultos;
    }

    public Status getStatus() { return status; }
    public Timestamp getData() { return data; }
    public Sinistro getSinistro() { return sinistro; }
    public Seguradora getSeguradora() { return seguradora; }
    public double getValorTotal() { return valor_total; }
    public boolean getPossuiDanosOcultos() { return possui_danos_ocultos; }
    public boolean isPossuiDanosOcultos() { return possui_danos_ocultos; }
    public void setStatus(Status status) { this.status = status; }
    public void setData(Timestamp data) { this.data = data; }
    public void setValorTotal(double valor_total) { this.valor_total = valor_total; }
    public void setPossuiDanosOcultos(boolean possui_danos_ocultos) { this.possui_danos_ocultos = possui_danos_ocultos; }

    public Orçamento getVisao() {
        return new Orçamento(status, data, sinistro, seguradora, valor_total, possui_danos_ocultos);
    }

    @Override public String toString() {
        return "[" + sinistro.getId() + "] " + sinistro.getSegurado()
                + " [" + sinistro.getGrauMonta() + "] - " + seguradora.getNome()
                + " - " + status;
    }

    public String toStringFull() {
        StringBuilder texto = new StringBuilder();
        texto.append(seguradora.getNome()).append(" [").append(seguradora.getCidade()).append("] - ")
                .append(seguradora.getCoberturaPercentual()).append("% - ")
                .append("atendimento 24h: ").append(seguradora.isPossuiAtendimento24h() ? "sim" : "não").append(" - ")
                .append(seguradora.getFormaPagamentoPreferencial()).append(" -- ")
                .append(sinistro.getSegurado()).append(" [").append(sinistro.getGrauMonta()).append("] - ")
                .append(sinistro.getCidade()).append(" - ")
                .append(sinistro.getTelefone()).append(" - ")
                .append(sinistro.getGrauMonta()).append(" - perda total: ")
                .append(sinistro.isPerdaTotal() ? "sim" : "não");

        Peça[] pecas = sinistro.getPeças();
        if (pecas.length == 0) {
            texto.append("\n\t-- sem peças");
        } else {
            for (Peça peca : pecas) {
                texto.append("\n\t-- ").append(formatarPeça(peca));
            }
        }
        texto.append("\n\tstatus: ").append(status)
                .append(" - valor: R$ ")
                .append(String.format(Locale.forLanguageTag("pt-BR"), "%.2f", valor_total))
                .append(" - data: ").append(formatarData(data))
                .append(" - danos ocultos: ")
                .append(possui_danos_ocultos ? "sim" : "não");
        return texto.toString().trim();
    }

    private String formatarPeça(Peça peca) {
        StringBuilder texto = new StringBuilder();
        String subtipo = peca instanceof PeçaMecânica ? "Peça mecânica"
                : peca instanceof PeçaAcessório ? "Peça acessórios" : "Peça";
        texto.append(subtipo).append(" - ")
                .append(peca.getNome()).append(" [").append(peca.getMarca()).append("] - ");

        if (peca instanceof PeçaMecânica) {
            PeçaMecânica mecânica = (PeçaMecânica) peca;
            texto.append(mecânica.getCategoriaMecânica()).append(" - R$ ")
                    .append(String.format(java.util.Locale.forLanguageTag("pt-BR"), "%.2f", peca.getPreco()))
                    .append(" - sob encomenda: ").append(peca.isSobEncomenda() ? "sim" : "não")
                    .append(" - ").append(mecânica.getPrazoGarantia()).append(" dias - mecânico próprio: ")
                    .append(mecânica.isMecanicoProprio() ? "sim" : "não");
        } else if (peca instanceof PeçaAcessório) {
            PeçaAcessório acessório = (PeçaAcessório) peca;
            texto.append(acessório.getCategoriaAcessório()).append(" - R$ ")
                    .append(String.format(java.util.Locale.forLanguageTag("pt-BR"), "%.2f", peca.getPreco()))
                    .append(" - sob encomenda: ").append(peca.isSobEncomenda() ? "sim" : "não")
                    .append(" - ").append(acessório.getCor()).append(" - funileiro próprio: ")
                    .append(acessório.isFunileiroProprio() ? "sim" : "não");
        } else {
            texto.append("- R$ ")
                    .append(String.format(java.util.Locale.forLanguageTag("pt-BR"), "%.2f", peca.getPreco()))
                    .append(" - sob encomenda: ").append(peca.isSobEncomenda() ? "sim" : "não");
        }
        return texto.toString();
    }

    private String formatarData(Timestamp valor) {
        if (valor == null) return "";
        return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(valor);
    }

    private static Orçamento criar(ResultSet lista_resultados) throws SQLException {
        int id_sinistro = lista_resultados.getInt("sinistro");
        String nome_seguradora = lista_resultados.getString("seguradora");
        return new Orçamento(Status.fromTexto(lista_resultados.getString("status")),
                lista_resultados.getTimestamp("data"),
                Sinistro.buscarSinistro(id_sinistro), Seguradora.buscarSeguradora(nome_seguradora),
                lista_resultados.getDouble("valor_total"),
                lista_resultados.getBoolean("possui_danos_ocultos"));
    }

    public static Orçamento[] getVisoes() {
        ArrayList<Orçamento> visoes = new ArrayList<>();
        String sql = "SELECT data, sinistro, seguradora, status, valor_total, possui_danos_ocultos FROM Orçamentos ORDER BY data, sinistro, seguradora";
        ResultSet lista_resultados = null;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                visoes.add(criar(lista_resultados));
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException | IllegalArgumentException excecao_sql) {
            excecao_sql.printStackTrace();
        }
        return visoes.toArray(new Orçamento[visoes.size()]);
    }

    public static Orçamento buscarOrçamento(int id_sinistro, String nome_seguradora) {
        String sql = "SELECT data, sinistro, seguradora, status, valor_total, possui_danos_ocultos FROM Orçamentos WHERE sinistro = ? AND seguradora = ?";
        ResultSet lista_resultados = null;
        Orçamento orçamento = null;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setInt(1, id_sinistro);
            comando.setString(2, nome_seguradora);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                orçamento = criar(lista_resultados);
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException | IllegalArgumentException excecao_sql) {
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

    public static String inserirOrçamento(Orçamento orçamento) {
        String sql = "INSERT INTO Orçamentos (data, sinistro, seguradora, status, valor_total, possui_danos_ocultos) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setTimestamp(1, orçamento.data);
            comando.setInt(2, orçamento.sinistro.getId());
            comando.setString(3, orçamento.seguradora.getNome());
            comando.setString(4, orçamento.status.toString());
            comando.setDouble(5, orçamento.valor_total);
            comando.setBoolean(6, orçamento.possui_danos_ocultos);
            comando.executeUpdate();
            comando.close();
            return null;
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
            return "Erro ao inserir orçamento no banco de dados";
        }
    }

    public static String alterarOrçamento(Orçamento orçamento) {
        String sql = "UPDATE Orçamentos SET data = ?, status = ?, valor_total = ?, possui_danos_ocultos = ? WHERE sinistro = ? AND seguradora = ?";
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setTimestamp(1, orçamento.data);
            comando.setString(2, orçamento.status.toString());
            comando.setDouble(3, orçamento.valor_total);
            comando.setBoolean(4, orçamento.possui_danos_ocultos);
            comando.setInt(5, orçamento.sinistro.getId());
            comando.setString(6, orçamento.seguradora.getNome());
            int linhas_alteradas = comando.executeUpdate();
            comando.close();
            return linhas_alteradas == 1 ? null : "Orçamento não cadastrado";
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
            return "Erro ao alterar orçamento no banco de dados";
        }
    }

    public static String removerOrçamento(int id_sinistro, String nome_seguradora) {
        String sql = "DELETE FROM Orçamentos WHERE sinistro = ? AND seguradora = ?";
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setInt(1, id_sinistro);
            comando.setString(2, nome_seguradora);
            int linhas_removidas = comando.executeUpdate();
            comando.close();
            return linhas_removidas == 1 ? null : "Orçamento não cadastrado";
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
            return "Erro ao remover orçamento do banco de dados";
        }
    }

    public static ArrayList<Orçamento> pesquisarOrçamentos(Timestamp data_mínima,
            Peça.MarcaPeça marca, Boolean atendimento_24h, Sinistro.GrauMonta grau_monta,
            Integer prazo_garantia_mínimo, String cor_acessório, boolean todos_objetos) {
        String sql = "SELECT O.sinistro, O.seguradora "
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
            if (grau_monta != null) comando.setString(++indice, grau_monta.toString());

            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                Orçamento orçamento_pesquisado = buscarOrçamento(
                        lista_resultados.getInt("sinistro"),
                        lista_resultados.getString("seguradora"));
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
        boolean filtro_cor_informado = cor_acessório != null && !cor_acessório.trim().isEmpty();
        String cor_procurada = filtro_cor_informado
                ? cor_acessório.trim().toLowerCase(Locale.ROOT) : "";

        int peças_com_marca_atendida = 0;
        int peças_com_marca_não_atendida = 0;
        int peças_mecânicas = 0;
        int peças_mecânicas_com_prazo_atendido = 0;
        int peças_mecânicas_com_prazo_não_atendido = 0;
        int peças_acessórias = 0;
        int peças_acessórias_com_cor_atendida = 0;
        int peças_acessórias_com_cor_não_atendida = 0;

        for (Peça peça : peças_sinistro) {
            if (marca != null) {
                if (peça.getMarca() == marca) peças_com_marca_atendida++;
                else peças_com_marca_não_atendida++;
            }

            if (prazo_garantia_mínimo != null && peça instanceof PeçaMecânica) {
                peças_mecânicas++;
                PeçaMecânica peça_mecânica = (PeçaMecânica) peça;
                Integer prazo_garantia = peça_mecânica.getPrazoGarantia();
                if (prazo_garantia != null && prazo_garantia >= prazo_garantia_mínimo) {
                    peças_mecânicas_com_prazo_atendido++;
                } else {
                    peças_mecânicas_com_prazo_não_atendido++;
                }
            }

            if (filtro_cor_informado && peça instanceof PeçaAcessório) {
                peças_acessórias++;
                PeçaAcessório peça_acessória = (PeçaAcessório) peça;
                String cor_peça = peça_acessória.getCor();
                if (cor_peça != null && cor_peça.toLowerCase(Locale.ROOT).contains(cor_procurada)) {
                    peças_acessórias_com_cor_atendida++;
                } else {
                    peças_acessórias_com_cor_não_atendida++;
                }
            }
        }

        if (marca != null) {
            if (peças_com_marca_atendida == 0) return false;
            if (todos_objetos && peças_com_marca_não_atendida > 0) return false;
        }
        if (prazo_garantia_mínimo != null) {
            if (peças_mecânicas == 0 || peças_mecânicas_com_prazo_atendido == 0) return false;
            if (todos_objetos && peças_mecânicas_com_prazo_não_atendido > 0) return false;
        }
        if (filtro_cor_informado) {
            if (peças_acessórias == 0 || peças_acessórias_com_cor_atendida == 0) return false;
            if (todos_objetos && peças_acessórias_com_cor_não_atendida > 0) return false;
        }
        return true;
    }
}
