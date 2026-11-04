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
        java.awt.GridBagConstraints gridBagConstraints;

        atendimento24hGrupo = new javax.swing.ButtonGroup();
        filtros = new javax.swing.JPanel();
        painelOrçamento = new javax.swing.JPanel();
        dataLabel = new javax.swing.JLabel();
        dataMinimaOrçamento = new javax.swing.JTextField();
        painelSeguradora = new javax.swing.JPanel();
        atendimentoLabel = new javax.swing.JLabel();
        atendimento24h = new javax.swing.JPanel();
        atendimento24hSim = new javax.swing.JRadioButton();
        atendimento24hNao = new javax.swing.JRadioButton();
        painelSinistro = new javax.swing.JPanel();
        grauLabel = new javax.swing.JLabel();
        grauMonta = new javax.swing.JComboBox();
        grauMonta.setModel(new javax.swing.DefaultComboBoxModel<>(Sinistro.GrauMonta.values()));
        painelPeças = new javax.swing.JPanel();
        marcaLabel = new javax.swing.JLabel();
        marcaPeça = new javax.swing.JComboBox();
        marcaPeça.setModel(new javax.swing.DefaultComboBoxModel<>(Peça.MarcaPeça.values()));
        todosObjetosCheckBox = new javax.swing.JCheckBox();
        subclassesTabbedPane = new javax.swing.JTabbedPane();
        painelMecânica = new javax.swing.JPanel();
        prazoLabel = new javax.swing.JLabel();
        prazoMínimoGarantia = new javax.swing.JTextField();
        painelAcessórios = new javax.swing.JPanel();
        corLabel = new javax.swing.JLabel();
        corAcessório = new javax.swing.JTextField();
        resultadosScrollPane = new javax.swing.JScrollPane();
        resultados = new javax.swing.JTextArea();
        botoes = new javax.swing.JPanel();
        pesquisarButton = new javax.swing.JButton();
        limparButton = new javax.swing.JButton();
        limparPesquisaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar Orçamentos");
        setMinimumSize(new java.awt.Dimension(600, 800));
        setPreferredSize(new java.awt.Dimension(600, 810));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        filtros.setLayout(new java.awt.GridBagLayout());

        painelOrçamento.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro de Orçamento"));
        painelOrçamento.setLayout(new java.awt.GridBagLayout());

        dataLabel.setText("Data mínima (AAAA-MM-DD HH:MM:SS)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        painelOrçamento.add(dataLabel, gridBagConstraints);

        dataMinimaOrçamento.setColumns(16);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        painelOrçamento.add(dataMinimaOrçamento, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        filtros.add(painelOrçamento, gridBagConstraints);

        painelSeguradora.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro de Seguradora"));
        painelSeguradora.setLayout(new java.awt.GridBagLayout());

        atendimentoLabel.setText("Seguradora possui atendimento 24h");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        painelSeguradora.add(atendimentoLabel, gridBagConstraints);

        atendimento24h.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        atendimento24hSim.setText("Sim");
        atendimento24h.add(atendimento24hSim);

        atendimento24hNao.setText("Não");
        atendimento24h.add(atendimento24hNao);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        painelSeguradora.add(atendimento24h, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        filtros.add(painelSeguradora, gridBagConstraints);

        painelSinistro.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro de Sinistro"));
        painelSinistro.setLayout(new java.awt.GridBagLayout());

        grauLabel.setText("Grau da monta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        painelSinistro.add(grauLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        painelSinistro.add(grauMonta, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        filtros.add(painelSinistro, gridBagConstraints);

        painelPeças.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros de Peças"));
        painelPeças.setLayout(new java.awt.GridBagLayout());

        marcaLabel.setText("Marca da peça");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        painelPeças.add(marcaLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        painelPeças.add(marcaPeça, gridBagConstraints);

        todosObjetosCheckBox.setText("Todos os objetos do sinistro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        painelPeças.add(todosObjetosCheckBox, gridBagConstraints);

        painelMecânica.setLayout(new java.awt.GridBagLayout());

        prazoLabel.setText("Prazo mínimo de garantia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        painelMecânica.add(prazoLabel, gridBagConstraints);

        prazoMínimoGarantia.setColumns(12);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        painelMecânica.add(prazoMínimoGarantia, gridBagConstraints);

        subclassesTabbedPane.addTab("Peça mecânica", painelMecânica);

        painelAcessórios.setLayout(new java.awt.GridBagLayout());

        corLabel.setText("Cor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        painelAcessórios.add(corLabel, gridBagConstraints);

        corAcessório.setColumns(12);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        painelAcessórios.add(corAcessório, gridBagConstraints);

        subclassesTabbedPane.addTab("Peça de acessórios", painelAcessórios);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        painelPeças.add(subclassesTabbedPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        filtros.add(painelPeças, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        getContentPane().add(filtros, gridBagConstraints);

        resultados.setEditable(false);
        resultados.setLineWrap(true);
        resultados.setWrapStyleWord(true);
        resultadosScrollPane.setViewportView(resultados);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        getContentPane().add(resultadosScrollPane, gridBagConstraints);

        pesquisarButton.setText("Pesquisar");
        botoes.add(pesquisarButton);

        limparButton.setText("Limpar Filtros");
        botoes.add(limparButton);

        limparPesquisaButton.setText("Limpar Pesquisa");
        botoes.add(limparPesquisaButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        getContentPane().add(botoes, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                    atendimentoSelecionado, grau, prazo, cor,
                    todosObjetosCheckBox.isSelected());
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
        todosObjetosCheckBox.setSelected(false);
        subclassesTabbedPane.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel atendimento24h;
    private javax.swing.ButtonGroup atendimento24hGrupo;
    private javax.swing.JRadioButton atendimento24hNao;
    private javax.swing.JRadioButton atendimento24hSim;
    private javax.swing.JLabel atendimentoLabel;
    private javax.swing.JPanel botoes;
    private javax.swing.JTextField corAcessório;
    private javax.swing.JLabel corLabel;
    private javax.swing.JLabel dataLabel;
    private javax.swing.JTextField dataMinimaOrçamento;
    private javax.swing.JPanel filtros;
    private javax.swing.JLabel grauLabel;
    private javax.swing.JComboBox grauMonta;
    private javax.swing.JButton limparButton;
    private javax.swing.JButton limparPesquisaButton;
    private javax.swing.JLabel marcaLabel;
    private javax.swing.JComboBox marcaPeça;
    private javax.swing.JPanel painelAcessórios;
    private javax.swing.JPanel painelMecânica;
    private javax.swing.JPanel painelOrçamento;
    private javax.swing.JPanel painelPeças;
    private javax.swing.JPanel painelSeguradora;
    private javax.swing.JPanel painelSinistro;
    private javax.swing.JButton pesquisarButton;
    private javax.swing.JLabel prazoLabel;
    private javax.swing.JTextField prazoMínimoGarantia;
    private javax.swing.JTextArea resultados;
    private javax.swing.JScrollPane resultadosScrollPane;
    private javax.swing.JTabbedPane subclassesTabbedPane;
    private javax.swing.JCheckBox todosObjetosCheckBox;
    // End of variables declaration//GEN-END:variables

}
