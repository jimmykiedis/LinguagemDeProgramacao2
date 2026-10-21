package interfaces;

import entidades.Orçamento;
import entidades.Peça;
import entidades.Sinistro;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JanelaPesquisaOrçamentos extends JFrame {
    public JanelaPesquisaOrçamentos() {
        initComponents();
        limparFiltros();
        JanelaUtil.posicionarNoCanto(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar Orçamentos");
        setLayout(new BorderLayout(8, 8));
        construirInterface();
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void construirInterface() {
        filtros = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;

        dataLabel = new JLabel("Data mínima (AAAA-MM-DD HH:MM:SS)");
        marcaLabel = new JLabel("Marca");
        atendimentoLabel = new JLabel("Seguradora possui atendimento 24h");
        grauLabel = new JLabel("Grau da monta");
        prazoLabel = new JLabel("Prazo mínimo de garantia (Peça Mecânica)");
        corLabel = new JLabel("Cor (Peça Acessórios)");
        dataMinimaOrçamento = new JTextField(12);
        marcaPeça = new JComboBox<>(Peça.MarcaPeça.values());
        atendimento24hSim = new JRadioButton("Sim"); atendimento24hNao = new JRadioButton("Não");
        atendimento24hGrupo = new ButtonGroup();
        grauMonta = new JComboBox<>(Sinistro.GrauMonta.values());
        prazoMínimoGarantia = new JTextField(12); corAcessório = new JTextField(12);
        resultados = new JTextArea(18, 70);

        painelOrçamento = criarPainelFiltro("Filtro de Orçamento");
        GridBagConstraints orçamentoC = criarConstraintsInternos();
        adicionar(painelOrçamento, orçamentoC, 0, dataLabel.getText(), dataMinimaOrçamento);

        painelSeguradora = criarPainelFiltro("Filtro de Seguradora");
        atendimento24h = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        atendimento24hGrupo.add(atendimento24hSim);
        atendimento24hGrupo.add(atendimento24hNao);
        atendimento24h.add(atendimento24hSim);
        atendimento24h.add(atendimento24hNao);
        GridBagConstraints seguradoraC = criarConstraintsInternos();
        adicionar(painelSeguradora, seguradoraC, 0, atendimentoLabel.getText(), atendimento24h);

        painelSinistro = criarPainelFiltro("Filtro de Sinistro");
        GridBagConstraints sinistroC = criarConstraintsInternos();
        adicionar(painelSinistro, sinistroC, 0, grauLabel.getText(), grauMonta);

        painelPeças = criarPainelFiltro("Filtros de Peças");
        GridBagConstraints peçasC = criarConstraintsInternos();
        adicionar(painelPeças, peçasC, 0, marcaLabel.getText(), marcaPeça);

        painelMecânica = new JPanel(new GridBagLayout());
        painelAcessórios = new JPanel(new GridBagLayout());
        subclassesTabbedPane = new JTabbedPane();
        GridBagConstraints subclasseC = criarConstraintsInternos();
        adicionar(painelMecânica, subclasseC, 0, prazoLabel.getText(), prazoMínimoGarantia);
        subclasseC = criarConstraintsInternos();
        adicionar(painelAcessórios, subclasseC, 0, corLabel.getText(), corAcessório);
        subclassesTabbedPane.addTab("Peça mecânica", painelMecânica);
        subclassesTabbedPane.addTab("Peça de acessórios", painelAcessórios);
        abasAlinhadasPanel = new JPanel(new BorderLayout());
        abasAlinhadasPanel.add(subclassesTabbedPane, BorderLayout.CENTER);
        peçasC = criarConstraintsInternos();
        peçasC.gridy = 1;
        peçasC.gridwidth = 2;
        peçasC.fill = GridBagConstraints.BOTH;
        peçasC.weightx = 1.0;
        peçasC.weighty = 1.0;
        painelPeças.add(abasAlinhadasPanel, peçasC);

        adicionarPainel(filtros, c, 0, painelOrçamento);
        adicionarPainel(filtros, c, 1, painelSeguradora);
        adicionarPainel(filtros, c, 2, painelSinistro);
        adicionarPainel(filtros, c, 3, painelPeças);

        resultados.setEditable(false);
        resultados.setLineWrap(true);
        resultados.setWrapStyleWord(true);
        botoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pesquisarButton = new JButton("Pesquisar");
        limparButton = new JButton("Limpar Filtros");
        limparPesquisaButton = new JButton("Limpar Pesquisa");
        pesquisarButton.addActionListener(e -> pesquisar());
        limparButton.addActionListener(e -> limparFiltros());
        limparPesquisaButton.addActionListener(e -> resultados.setText(""));
        botoes.add(pesquisarButton); botoes.add(limparButton); botoes.add(limparPesquisaButton);
        resultadosScrollPane = new JScrollPane(resultados);
        add(filtros, BorderLayout.NORTH);
        add(resultadosScrollPane, BorderLayout.CENTER);
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

    private JPanel criarPainelFiltro(String título) {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(javax.swing.BorderFactory.createTitledBorder(título));
        return painel;
    }

    private GridBagConstraints criarConstraintsInternos() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.anchor = GridBagConstraints.WEST;
        return c;
    }

    private void adicionarPainel(JPanel painel, GridBagConstraints c, int linha,
            JPanel painelFiltro) {
        c.gridx = 0;
        c.gridy = linha;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.weighty = 0;
        painel.add(painelFiltro, c);
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
            Timestamp data = textoData.isEmpty() ? null : obterTimestamp(textoData);
            Boolean atendimentoSelecionado = atendimento24hSim.isSelected() ? Boolean.TRUE
                    : atendimento24hNao.isSelected() ? Boolean.FALSE : null;
            Sinistro.GrauMonta grau = (Sinistro.GrauMonta) grauMonta.getSelectedItem();
            Integer prazo = inteiroOpcional(prazoMínimoGarantia, "prazo mínimo de garantia");
            String cor = corAcessório.getText().trim();
            if (cor.isEmpty()) cor = null;

            Peça.MarcaPeça marca = (Peça.MarcaPeça) marcaPeça.getSelectedItem();
            ArrayList<Orçamento> encontrados = Orçamento.pesquisarOrçamentos(
                    data, marca,
                    atendimentoSelecionado, grau, prazo, cor);
            resultados.setText("");
            for (Orçamento orçamento : encontrados) {
                if (resultados.getDocument().getLength() > 0) resultados.append("\n\n");
                resultados.append(orçamento.toStringFull());
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage() == null ? "Informe a data no formato AAAA-MM-DD HH:MM:SS." : e.getMessage(),
                    "Filtro inválido", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Timestamp obterTimestamp(String texto) {
        try {
            return Timestamp.valueOf(texto.length() == 10 ? texto + " 00:00:00" : texto);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Informe a data no formato AAAA-MM-DD HH:MM:SS.");
        }
    }

    private void limparFiltros() {
        dataMinimaOrçamento.setText("");
        marcaPeça.setSelectedIndex(-1);
        atendimento24hGrupo.clearSelection();
        grauMonta.setSelectedIndex(-1);
        prazoMínimoGarantia.setText("");
        corAcessório.setText("");
        subclassesTabbedPane.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextField dataMinimaOrçamento;
    private JComboBox<Peça.MarcaPeça> marcaPeça;
    private JRadioButton atendimento24hSim, atendimento24hNao;
    private ButtonGroup atendimento24hGrupo;
    private JComboBox<Sinistro.GrauMonta> grauMonta;
    private JTextField prazoMínimoGarantia, corAcessório;
    private JTextArea resultados;
    private JPanel filtros, atendimento24h, botoes, painelOrçamento, painelSeguradora,
            painelSinistro, painelPeças, painelMecânica, painelAcessórios, abasAlinhadasPanel;
    private JTabbedPane subclassesTabbedPane;
    private JLabel dataLabel, marcaLabel, atendimentoLabel, grauLabel, prazoLabel, corLabel;
    private JButton pesquisarButton, limparButton, limparPesquisaButton;
    private JScrollPane resultadosScrollPane;
    // End of variables declaration//GEN-END:variables

}
