package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import persistência.BD;

public class Seguradora {

    public enum FormaPagamentoPreferencial {
        BOLETO("boleto"),
        CARTAO("cartao"),
        PIX("pix"),
        DEBITO_AUTOMATICO("debito_automatico");

        private final String texto;

        FormaPagamentoPreferencial(String texto) {
            this.texto = texto;
        }

        public static FormaPagamentoPreferencial fromTexto(String texto) {
            if (texto == null) {
                throw new IllegalArgumentException("Forma de pagamento não informada");
            }

            String normalizado = texto.trim().toLowerCase(Locale.ROOT);

            if (normalizado.equals("boleto")) return BOLETO;
            if (normalizado.equals("cartao") || normalizado.equals("cartão")) return CARTAO;
            if (normalizado.equals("pix")) return PIX;
            if (normalizado.equals("debito_automatico")
                    || normalizado.equals("debito automatico")
                    || normalizado.equals("débito_automático")
                    || normalizado.equals("débito automático")) {
                return DEBITO_AUTOMATICO;
            }

            throw new IllegalArgumentException("Forma de pagamento inválida: " + texto);
        }

        @Override
        public String toString() {
            return texto;
        }
    }

    private String nome;
    private String cidade;
    private double coberturaPercentual;
    private boolean possuiAtendimento24h;
    private FormaPagamentoPreferencial formaPagamentoPreferencial;

    public String getNome() { return nome; }
    public String getCidade() { return cidade; }
    public double getCoberturaPercentual() { return coberturaPercentual; }
    public boolean getPossuiAtendimento24h() { return possuiAtendimento24h; }
    public boolean isPossuiAtendimento24h() { return possuiAtendimento24h; }
    public FormaPagamentoPreferencial getFormaPagamentoPreferencial() { return formaPagamentoPreferencial; }

    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setCoberturaPercentual(double coberturaPercentual) { this.coberturaPercentual = coberturaPercentual; }
    public void setPossuiAtendimento24h(boolean possuiAtendimento24h) { this.possuiAtendimento24h = possuiAtendimento24h; }
    public void setFormaPagamentoPreferencial(FormaPagamentoPreferencial formaPagamentoPreferencial) {
        this.formaPagamentoPreferencial = formaPagamentoPreferencial;
    }

    public Seguradora(
            String nome,
            String cidade,
            double coberturaPercentual,
            boolean possuiAtendimento24h,
            FormaPagamentoPreferencial formaPagamentoPreferencial
    ) {
        this.nome = nome;
        this.cidade = cidade;
        this.coberturaPercentual = coberturaPercentual;
        this.possuiAtendimento24h = possuiAtendimento24h;
        this.formaPagamentoPreferencial = formaPagamentoPreferencial;
    }

    public Seguradora(String nome, String cidade, double coberturaPercentual) {
        this(nome, cidade, coberturaPercentual, false, FormaPagamentoPreferencial.BOLETO);
    }

    public Seguradora(String nome, String cidade) {
        this(nome, cidade, 0, false, FormaPagamentoPreferencial.BOLETO);
    }

    public String toString() {
        return nome + " [" + cidade + "]";
    }

    public Seguradora getVisao() {
        return new Seguradora(
                nome,
                cidade,
                coberturaPercentual,
                possuiAtendimento24h,
                formaPagamentoPreferencial
        );
    }

    public static Seguradora[] getVisoes() {
        String sql = "SELECT nome, cidade, cobertura_percentual, possui_atendimento_24h, forma_pagamento_preferencial FROM seguradoras";
        ResultSet listaResultados = null;
        ArrayList<Seguradora> visoes = new ArrayList<>();

        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            listaResultados = comando.executeQuery();

            while (listaResultados.next()) {
                String nome = listaResultados.getString("nome");
                String cidade = listaResultados.getString("cidade");
                double coberturaPercentual = listaResultados.getDouble("cobertura_percentual");
                boolean possuiAtendimento24h = listaResultados.getBoolean("possui_atendimento_24h");
                FormaPagamentoPreferencial formaPagamentoPreferencial =
                        FormaPagamentoPreferencial.fromTexto(
                                listaResultados.getString("forma_pagamento_preferencial")
                        );
                visoes.add(new Seguradora(
                        nome,
                        cidade,
                        coberturaPercentual,
                        possuiAtendimento24h,
                        formaPagamentoPreferencial
                ));
            }

            listaResultados.close();
            comando.close();

        } catch (SQLException excecaoSql) {
            excecaoSql.printStackTrace();
        }

        return visoes.toArray(new Seguradora[visoes.size()]);
    }

    public static Seguradora buscarSeguradora(String nome) {
        String sql = "SELECT * FROM seguradoras WHERE nome = ?";
        ResultSet listaResultados = null;
        Seguradora seguradora = null;

        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setString(1, nome);
            listaResultados = comando.executeQuery();

            while (listaResultados.next()) {
                seguradora = new Seguradora(
                        nome,
                        listaResultados.getString("cidade"),
                        listaResultados.getDouble("cobertura_percentual"),
                        listaResultados.getBoolean("possui_atendimento_24h"),
                        FormaPagamentoPreferencial.fromTexto(
                                listaResultados.getString("forma_pagamento_preferencial")
                        )
                );
            }

            listaResultados.close();
            comando.close();

        } catch (SQLException excecaoSql) {
            excecaoSql.printStackTrace();
            seguradora = null;
        }

        return seguradora;
    }

    public static String inserirSeguradora(Seguradora seguradora) {
        String sql = "INSERT INTO seguradoras (nome, cidade, cobertura_percentual, possui_atendimento_24h, forma_pagamento_preferencial) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);

            comando.setString(1, seguradora.getNome());
            comando.setString(2, seguradora.getCidade());
            comando.setDouble(3, seguradora.getCoberturaPercentual());
            comando.setBoolean(4, seguradora.getPossuiAtendimento24h());
            comando.setString(5, seguradora.getFormaPagamentoPreferencial().toString());

            comando.executeUpdate();
            comando.close();

            return null;

        } catch (SQLException excecaoSql) {
            excecaoSql.printStackTrace();
            return "Erro na Inserção da Seguradora no BD";
        }
    }

    public static String alterarSeguradora(Seguradora seguradora) {
        String sql = "UPDATE seguradoras SET cidade = ?, cobertura_percentual = ?, possui_atendimento_24h = ?, forma_pagamento_preferencial = ?"
                + " WHERE nome = ?";

        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);

            comando.setString(1, seguradora.getCidade());
            comando.setDouble(2, seguradora.getCoberturaPercentual());
            comando.setBoolean(3, seguradora.getPossuiAtendimento24h());
            comando.setString(4, seguradora.getFormaPagamentoPreferencial().toString());
            comando.setString(5, seguradora.getNome());

            comando.executeUpdate();
            comando.close();

            return null;

        } catch (SQLException excecaoSql) {
            excecaoSql.printStackTrace();
            return "Erro na Alteração da Seguradora no BD";
        }
    }

    public static String removerSeguradora(String nome) {
        String sql = "DELETE FROM seguradoras WHERE nome = ?";

        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);

            comando.setString(1, nome);
            comando.executeUpdate();
            comando.close();

            return null;

        } catch (SQLException excecaoSql) {
            excecaoSql.printStackTrace();
            return "Erro na Remoção da Seguradora no BD";
        }
    }
}
