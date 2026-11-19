package interfaces;

import entidades.Orçamento;
import entidades.Peça;
import entidades.Sinistro;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class JanelaPesquisaOrçamentos extends JFrame {
    public JanelaPesquisaOrçamentos() {
        initComponents();
        limparFiltros();
        setLocationByPlatform(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints grid_bag_constraints;

        atendimento_24h_grupo = new javax.swing.ButtonGroup();
        filtros = new javax.swing.JPanel();
        painel_orçamento = new javax.swing.JPanel();
        data_label = new javax.swing.JLabel();
        data_minima_orçamento = new javax.swing.JTextField();
        painel_seguradora = new javax.swing.JPanel();
        atendimento_label = new javax.swing.JLabel();
        atendimento_24h = new javax.swing.JPanel();
        atendimento_24h_sim = new javax.swing.JRadioButton();
        atendimento_24h_nao = new javax.swing.JRadioButton();
        painel_sinistro = new javax.swing.JPanel();
        grau_label = new javax.swing.JLabel();
        grau_monta_grupo = new javax.swing.ButtonGroup();
        grau_monta_panel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        grau_monta_pequena = new javax.swing.JRadioButton(Sinistro.GrauMonta.PEQUENA.toString());
        grau_monta_media = new javax.swing.JRadioButton(Sinistro.GrauMonta.MEDIA.toString());
        grau_monta_grande = new javax.swing.JRadioButton(Sinistro.GrauMonta.GRANDE.toString());
        grau_monta_grupo.add(grau_monta_pequena);
        grau_monta_grupo.add(grau_monta_media);
        grau_monta_grupo.add(grau_monta_grande);
        grau_monta_panel.add(grau_monta_pequena);
        grau_monta_panel.add(grau_monta_media);
        grau_monta_panel.add(grau_monta_grande);
        painel_peças = new javax.swing.JPanel();
        marca_label = new javax.swing.JLabel();
        marca_peça_grupo = new javax.swing.ButtonGroup();
        marca_peça_panel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        marca_oem_radio_button = new javax.swing.JRadioButton(Peça.MarcaPeça.OEM.toString());
        marca_original_radio_button = new javax.swing.JRadioButton(Peça.MarcaPeça.ORIGINAL.toString());
        marca_genuina_radio_button = new javax.swing.JRadioButton(Peça.MarcaPeça.GENUINA.toString());
        marca_peça_grupo.add(marca_oem_radio_button);
        marca_peça_grupo.add(marca_original_radio_button);
        marca_peça_grupo.add(marca_genuina_radio_button);
        marca_peça_panel.add(marca_oem_radio_button);
        marca_peça_panel.add(marca_original_radio_button);
        marca_peça_panel.add(marca_genuina_radio_button);
        todos_objetos_check_box = new javax.swing.JCheckBox();
        subclasses_tabbed_pane = new javax.swing.JTabbedPane();
        painel_mecânica = new PainelFiltrosPeçaMecânica();
        painel_acessórios = new PainelFiltrosPeçaAcessório();
        resultados_scroll_pane = new javax.swing.JScrollPane();
        resultados = new javax.swing.JTextArea();
        botoes = new javax.swing.JPanel();
        pesquisar_button = new javax.swing.JButton();
        limpar_button = new javax.swing.JButton();
        limpar_pesquisa_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar Orçamentos");
        setMinimumSize(new java.awt.Dimension(600, 800));
        setPreferredSize(new java.awt.Dimension(600, 810));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        filtros.setLayout(new java.awt.GridBagLayout());

        painel_orçamento.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro de Orçamento"));
        painel_orçamento.setLayout(new java.awt.GridBagLayout());

        data_label.setText("Data mínima (AAAA-MM-DD HH:MM:SS)");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        painel_orçamento.add(data_label, grid_bag_constraints);

        data_minima_orçamento.setColumns(16);
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        painel_orçamento.add(data_minima_orçamento, grid_bag_constraints);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        grid_bag_constraints.weightx = 1.0;
        filtros.add(painel_orçamento, grid_bag_constraints);

        painel_seguradora.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro de Seguradora"));
        painel_seguradora.setLayout(new java.awt.GridBagLayout());

        atendimento_label.setText("Seguradora possui atendimento 24h");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        painel_seguradora.add(atendimento_label, grid_bag_constraints);

        atendimento_24h.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        atendimento_24h_sim.setText("Sim");
        atendimento_24h.add(atendimento_24h_sim);

        atendimento_24h_nao.setText("Não");
        atendimento_24h.add(atendimento_24h_nao);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        painel_seguradora.add(atendimento_24h, grid_bag_constraints);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 1;
        grid_bag_constraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        grid_bag_constraints.weightx = 1.0;
        filtros.add(painel_seguradora, grid_bag_constraints);

        painel_sinistro.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro de Sinistro"));
        painel_sinistro.setLayout(new java.awt.GridBagLayout());

        grau_label.setText("Grau da monta");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        painel_sinistro.add(grau_label, grid_bag_constraints);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        painel_sinistro.add(grau_monta_panel, grid_bag_constraints);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 2;
        grid_bag_constraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        grid_bag_constraints.weightx = 1.0;
        filtros.add(painel_sinistro, grid_bag_constraints);

        painel_peças.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros de Peças"));
        painel_peças.setLayout(new java.awt.GridBagLayout());

        marca_label.setText("Marca da peça");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        painel_peças.add(marca_label, grid_bag_constraints);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        painel_peças.add(marca_peça_panel, grid_bag_constraints);

        todos_objetos_check_box.setText("Todos os objetos do sinistro");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 1;
        grid_bag_constraints.gridwidth = 2;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        painel_peças.add(todos_objetos_check_box, grid_bag_constraints);

        subclasses_tabbed_pane.addTab("Peça mecânica", painel_mecânica);
        subclasses_tabbed_pane.addTab("Peça de acessórios", painel_acessórios);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 2;
        grid_bag_constraints.gridwidth = 2;
        grid_bag_constraints.fill = java.awt.GridBagConstraints.BOTH;
        grid_bag_constraints.weightx = 1.0;
        grid_bag_constraints.weighty = 1.0;
        painel_peças.add(subclasses_tabbed_pane, grid_bag_constraints);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 3;
        grid_bag_constraints.fill = java.awt.GridBagConstraints.BOTH;
        grid_bag_constraints.weightx = 1.0;
        grid_bag_constraints.weighty = 1.0;
        filtros.add(painel_peças, grid_bag_constraints);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        grid_bag_constraints.weightx = 1.0;
        grid_bag_constraints.insets = new java.awt.Insets(4, 6, 4, 6);
        getContentPane().add(filtros, grid_bag_constraints);

        resultados.setEditable(false);
        resultados.setLineWrap(true);
        resultados.setWrapStyleWord(true);
        resultados_scroll_pane.setViewportView(resultados);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 1;
        grid_bag_constraints.fill = java.awt.GridBagConstraints.BOTH;
        grid_bag_constraints.weightx = 1.0;
        grid_bag_constraints.weighty = 1.0;
        grid_bag_constraints.insets = new java.awt.Insets(4, 6, 4, 6);
        getContentPane().add(resultados_scroll_pane, grid_bag_constraints);

        pesquisar_button.setText("Pesquisar");
        botoes.add(pesquisar_button);

        limpar_button.setText("Limpar Filtros");
        botoes.add(limpar_button);

        limpar_pesquisa_button.setText("Limpar Pesquisa");
        botoes.add(limpar_pesquisa_button);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 2;
        grid_bag_constraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        grid_bag_constraints.weightx = 1.0;
        grid_bag_constraints.insets = new java.awt.Insets(4, 6, 4, 6);
        getContentPane().add(botoes, grid_bag_constraints);

        atendimento_24h_grupo.add(atendimento_24h_sim);
        atendimento_24h_grupo.add(atendimento_24h_nao);
        pesquisar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { pesquisar(evt); }
        });
        limpar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { limparFiltros(evt); }
        });
        limpar_pesquisa_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { limparPesquisa(evt); }
        });
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisar(java.awt.event.ActionEvent evt) {
        try {
            String texto_data = data_minima_orçamento.getText().trim();
            Timestamp data = texto_data.isEmpty() ? null : obterTimestamp(texto_data);
            Boolean atendimento_selecionado = atendimento_24h_sim.isSelected() ? Boolean.TRUE
                    : atendimento_24h_nao.isSelected() ? Boolean.FALSE : null;
            Sinistro.GrauMonta grau = obterGrauMontaSelecionado();
            // Somente o filtro da aba selecionada participa da pesquisa. Isso
            // evita que um valor antigo da outra subclasse seja aplicado sem
            // que o usuário perceba.
            Integer prazo = subclasses_tabbed_pane.getSelectedIndex() == 0
                    ? painel_mecânica.getPrazoMínimoGarantia() : null;
            String cor = subclasses_tabbed_pane.getSelectedIndex() == 1
                    ? painel_acessórios.getCor() : null;

            Peça.MarcaPeça marca = obterMarcaSelecionada();
            ArrayList<Orçamento> encontrados = Orçamento.pesquisarOrçamentos(
                    data, marca,
                    atendimento_selecionado, grau, prazo, cor,
                    todos_objetos_check_box.isSelected());
            resultados.setText("");
            if (encontrados.isEmpty()) {
                resultados.setText("Nenhum orçamento encontrado para os filtros informados.");
                return;
            }
            mostrarOrçamentosSelecionados(encontrados);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage() == null ? "Informe a data no formato AAAA-MM-DD HH:MM:SS." : e.getMessage(),
                    "Filtro inválido", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarOrçamentosSelecionados(ArrayList<Orçamento> orçamentos_selecionados) {
        boolean primeiro_orçamento = true;
        for (Orçamento orçamento : orçamentos_selecionados) {
            if (primeiro_orçamento) {
                resultados.append(orçamento.toStringFull());
                primeiro_orçamento = false;
            } else {
                resultados.append("\n\n");
                resultados.append(orçamento.toStringFull());
            }
        }
    }

    private Peça.MarcaPeça obterMarcaSelecionada() {
        if (marca_oem_radio_button.isSelected()) return Peça.MarcaPeça.OEM;
        if (marca_original_radio_button.isSelected()) return Peça.MarcaPeça.ORIGINAL;
        if (marca_genuina_radio_button.isSelected()) return Peça.MarcaPeça.GENUINA;
        return null;
    }

    private Sinistro.GrauMonta obterGrauMontaSelecionado() {
        if (grau_monta_pequena.isSelected()) return Sinistro.GrauMonta.PEQUENA;
        if (grau_monta_media.isSelected()) return Sinistro.GrauMonta.MEDIA;
        if (grau_monta_grande.isSelected()) return Sinistro.GrauMonta.GRANDE;
        return null;
    }

    private void limparPesquisa(java.awt.event.ActionEvent evt) {
        resultados.setText("");
    }

    private Timestamp obterTimestamp(String texto) {
        try {
            return Timestamp.valueOf(texto.length() == 10 ? texto + " 00:00:00" : texto);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Informe a data no formato AAAA-MM-DD HH:MM:SS.");
        }
    }

    private void limparFiltros(java.awt.event.ActionEvent evt) {
        limparFiltros();
    }

    private void limparFiltros() {
        data_minima_orçamento.setText("");
        marca_peça_grupo.clearSelection();
        atendimento_24h_grupo.clearSelection();
        grau_monta_grupo.clearSelection();
        painel_mecânica.limparFiltros();
        painel_acessórios.limparFiltros();
        todos_objetos_check_box.setSelected(false);
        subclasses_tabbed_pane.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel atendimento_24h;
    private javax.swing.ButtonGroup atendimento_24h_grupo;
    private javax.swing.JRadioButton atendimento_24h_nao;
    private javax.swing.JRadioButton atendimento_24h_sim;
    private javax.swing.JLabel atendimento_label;
    private javax.swing.JPanel botoes;
    private javax.swing.JLabel data_label;
    private javax.swing.JTextField data_minima_orçamento;
    private javax.swing.JPanel filtros;
    private javax.swing.JLabel grau_label;
    private javax.swing.ButtonGroup grau_monta_grupo;
    private javax.swing.JPanel grau_monta_panel;
    private javax.swing.JRadioButton grau_monta_pequena;
    private javax.swing.JRadioButton grau_monta_media;
    private javax.swing.JRadioButton grau_monta_grande;
    private javax.swing.JButton limpar_button;
    private javax.swing.JButton limpar_pesquisa_button;
    private javax.swing.JLabel marca_label;
    private javax.swing.ButtonGroup marca_peça_grupo;
    private javax.swing.JPanel marca_peça_panel;
    private javax.swing.JRadioButton marca_oem_radio_button;
    private javax.swing.JRadioButton marca_original_radio_button;
    private javax.swing.JRadioButton marca_genuina_radio_button;
    private PainelFiltrosPeçaAcessório painel_acessórios;
    private PainelFiltrosPeçaMecânica painel_mecânica;
    private javax.swing.JPanel painel_orçamento;
    private javax.swing.JPanel painel_peças;
    private javax.swing.JPanel painel_seguradora;
    private javax.swing.JPanel painel_sinistro;
    private javax.swing.JButton pesquisar_button;
    private javax.swing.JTextArea resultados;
    private javax.swing.JScrollPane resultados_scroll_pane;
    private javax.swing.JTabbedPane subclasses_tabbed_pane;
    private javax.swing.JCheckBox todos_objetos_check_box;
    // End of variables declaration//GEN-END:variables

}
