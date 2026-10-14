package interfaces;

import entidades.Orçamento;
import entidades.Sinistro;
import entidades.Peça;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class JanelaPesquisaOrçamentos extends JFrame {
    private final JComboBox<Orçamento.Status> status = new JComboBox<>(Orçamento.Status.values());
    private final JTextField dataMinima = new JTextField(10);
    private final JComboBox<String> danosOcultos = comboBooleano();
    private final JTextField cidadeSeguradora = new JTextField(40);
    private final JComboBox<Sinistro.GrauMonta> grauMonta = new JComboBox<>(Sinistro.GrauMonta.values());
    private final JTextField cidadeSinistro = new JTextField(40);
    private final JComboBox<String> perdaTotal = comboBooleano();
    private final JComboBox<Peça.MarcaPeça> marcaPeça = new JComboBox<>(Peça.MarcaPeça.values());
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

    private static JComboBox<String> comboBooleano() {
        return new JComboBox<>(new String[] {"Todos", "Sim", "Não"});
    }

    private void construirInterface() {
        JPanel filtros = new JPanel(new GridBagLayout());
        filtros.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros de Orçamento, Sinistro, Seguradora e Peças"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.anchor = GridBagConstraints.WEST;
        adicionar(filtros, c, 0, "Status", status);
        adicionar(filtros, c, 1, "Data mínima (AAAA-MM-DD)", dataMinima);
        adicionar(filtros, c, 2, "Possui danos ocultos", danosOcultos);
        adicionar(filtros, c, 3, "Cidade da seguradora", cidadeSeguradora);
        adicionar(filtros, c, 4, "Grau de monta", grauMonta);
        adicionar(filtros, c, 5, "Cidade do sinistro", cidadeSinistro);
        adicionar(filtros, c, 6, "Perda total", perdaTotal);
        adicionar(filtros, c, 7, "Marca de peça associada", marcaPeça);
        resultados.setEditable(false);
        resultados.setLineWrap(true);
        resultados.setWrapStyleWord(true);
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton pesquisar = new JButton("Pesquisar"), limpar = new JButton("Limpar Filtros"), limparPesquisa = new JButton("Limpar Pesquisa");
        pesquisar.addActionListener(e -> pesquisar()); limpar.addActionListener(e -> limparFiltros());
        limparPesquisa.addActionListener(e -> resultados.setText(""));
        botoes.add(pesquisar); botoes.add(limpar); botoes.add(limparPesquisa);
        add(filtros, BorderLayout.NORTH);
        add(new JScrollPane(resultados), BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);
    }

    private void adicionar(JPanel painel, GridBagConstraints c, int linha,
            String rotulo, java.awt.Component componente) {
        c.gridy = linha;
        c.gridx = 0; c.fill = GridBagConstraints.NONE; c.weightx = 0;
        painel.add(new JLabel(rotulo), c);
        c.gridx = 1; painel.add(componente, c);
    }

    private Boolean valorBooleano(JComboBox<String> combo) {
        if (combo.getSelectedIndex() == 1) return Boolean.TRUE;
        if (combo.getSelectedIndex() == 2) return Boolean.FALSE;
        return null;
    }

    private void pesquisar() {
        try {
            Date data = dataMinima.getText().trim().isEmpty() ? null : Date.valueOf(dataMinima.getText().trim());
            Orçamento.Status statusSelecionado = (Orçamento.Status) status.getSelectedItem();
            Sinistro.GrauMonta grau = (Sinistro.GrauMonta) grauMonta.getSelectedItem();
            Peça.MarcaPeça marca = (Peça.MarcaPeça) marcaPeça.getSelectedItem();
            ArrayList<Orçamento> encontrados = Orçamento.pesquisarOrçamentos(statusSelecionado, data,
                    valorBooleano(danosOcultos), cidadeSeguradora.getText(), grau,
                    cidadeSinistro.getText(), valorBooleano(perdaTotal), marca);
            boolean primeiro = true;
            for (Orçamento o : encontrados) {
                if (!primeiro) resultados.append("\n\n");
                resultados.append(o.toStringFull()); primeiro = false;
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Informe a data mínima no formato AAAA-MM-DD.", "Filtro inválido", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparFiltros() {
        status.setSelectedIndex(-1); dataMinima.setText(""); danosOcultos.setSelectedIndex(0);
        cidadeSeguradora.setText(""); grauMonta.setSelectedIndex(-1); cidadeSinistro.setText("");
        perdaTotal.setSelectedIndex(0); marcaPeça.setSelectedIndex(-1);
    }
}
