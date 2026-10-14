package interfaces;

import entidades.Orçamento;
import entidades.Peça;
import entidades.Sinistro;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JanelaPesquisaOrçamentos extends JFrame {
    private final JTextField dataMinimaOrçamento = new JTextField(12);
    private final JTextField valorMáximoPeça = new JTextField(12);
    private final JTextField coberturaMínima = new JTextField(12);
    private final JComboBox<Sinistro.GrauMonta> grauMonta = new JComboBox<>(Sinistro.GrauMonta.values());
    private final JTextField prazoMínimoGarantia = new JTextField(12);
    private final JTextField corAcessório = new JTextField(12);
    private final JTextArea resultados = new JTextArea(18, 70);

    public JanelaPesquisaOrçamentos() {
        setTitle("Pesquisar Orçamentos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
        construirInterface();
        limparFiltros();
        pack();
        JanelaUtil.posicionarNoCanto(this);
    }

    private void construirInterface() {
        JPanel filtros = new JPanel(new GridBagLayout());
        filtros.setBorder(javax.swing.BorderFactory.createTitledBorder(
                "Um filtro por entidade: Orçamento, Peça, Seguradora, Sinistro e subclasses"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.anchor = GridBagConstraints.WEST;
        adicionar(filtros, c, 0, "Data mínima do orçamento (AAAA-MM-DD)", dataMinimaOrçamento);
        adicionar(filtros, c, 1, "Valor máximo da peça", valorMáximoPeça);
        adicionar(filtros, c, 2, "Cobertura percentual mínima", coberturaMínima);
        adicionar(filtros, c, 3, "Grau da monta", grauMonta);
        adicionar(filtros, c, 4, "Prazo mínimo de garantia (Peça Mecânica)", prazoMínimoGarantia);
        adicionar(filtros, c, 5, "Cor (Peça Acessórios)", corAcessório);

        resultados.setEditable(false);
        resultados.setLineWrap(true);
        resultados.setWrapStyleWord(true);
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton pesquisar = new JButton("Pesquisar");
        JButton limpar = new JButton("Limpar Filtros");
        JButton limparPesquisa = new JButton("Limpar Pesquisa");
        pesquisar.addActionListener(e -> pesquisar());
        limpar.addActionListener(e -> limparFiltros());
        limparPesquisa.addActionListener(e -> resultados.setText(""));
        botoes.add(pesquisar);
        botoes.add(limpar);
        botoes.add(limparPesquisa);
        add(filtros, BorderLayout.NORTH);
        add(new JScrollPane(resultados), BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);
    }

    private void adicionar(JPanel painel, GridBagConstraints c, int linha,
            String rotulo, java.awt.Component componente) {
        c.gridy = linha;
        c.gridx = 0;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        painel.add(new JLabel(rotulo), c);
        c.gridx = 1;
        painel.add(componente, c);
    }

    private Double númeroOpcional(JTextField campo, String descrição) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) return null;
        try {
            double valor = Double.parseDouble(texto.replace(',', '.'));
            if (valor < 0) throw new NumberFormatException();
            return valor;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Informe um valor válido e não negativo para " + descrição + ".");
        }
    }

    private Integer inteiroOpcional(JTextField campo, String descrição) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) return null;
        try {
            int valor = Integer.parseInt(texto);
            if (valor < 0) throw new NumberFormatException();
            return valor;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Informe um número inteiro não negativo para " + descrição + ".");
        }
    }

    private void pesquisar() {
        try {
            String textoData = dataMinimaOrçamento.getText().trim();
            Date data = textoData.isEmpty() ? null : Date.valueOf(textoData);
            Double valorMáximo = númeroOpcional(valorMáximoPeça, "valor máximo da peça");
            Double cobertura = númeroOpcional(coberturaMínima, "cobertura mínima");
            if (cobertura != null && cobertura > 100) {
                throw new IllegalArgumentException("A cobertura percentual deve estar entre 0 e 100.");
            }
            Sinistro.GrauMonta grau = (Sinistro.GrauMonta) grauMonta.getSelectedItem();
            Integer prazo = inteiroOpcional(prazoMínimoGarantia, "prazo mínimo de garantia");
            String cor = corAcessório.getText().trim();
            if (cor.isEmpty()) cor = null;

            ArrayList<Orçamento> encontrados = Orçamento.pesquisarOrçamentos(
                    data, valorMáximo, cobertura, grau, prazo, cor);
            resultados.setText("");
            for (Orçamento orçamento : encontrados) {
                if (resultados.getDocument().getLength() > 0) resultados.append("\n\n");
                resultados.append(orçamento.toStringFull());
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage() == null ? "Informe a data no formato AAAA-MM-DD." : e.getMessage(),
                    "Filtro inválido", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparFiltros() {
        dataMinimaOrçamento.setText("");
        valorMáximoPeça.setText("");
        coberturaMínima.setText("");
        grauMonta.setSelectedIndex(-1);
        prazoMínimoGarantia.setText("");
        corAcessório.setText("");
    }
}
