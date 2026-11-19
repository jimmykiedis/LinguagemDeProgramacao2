package interfaces;

import controles.ControladorCadastroPeçasSinistros;
import controles.ControladorCadastroSinistros;
import entidades.Sinistro;
import java.awt.Frame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class JanelaCadastroSinistros extends javax.swing.JFrame {

    ControladorCadastroSinistros controlador;
    Sinistro[] sinistros_cadastrados;
    private final DefaultListModel pecas_sinistro_model = new DefaultListModel();

    public JanelaCadastroSinistros(ControladorCadastroSinistros controlador) {
        this(controlador, null);
    }

    public JanelaCadastroSinistros(ControladorCadastroSinistros controlador, Frame owner) {
        this.controlador = controlador;
        sinistros_cadastrados = Sinistro.getVisoes();
        initComponents();
        atualizarSinistrosCadastrados();
        setSize(new java.awt.Dimension(820, 620));
        configurarJanelaDependente(owner);
        limparCampos(null);
    }

    private void configurarJanelaDependente(Frame owner) {
        setAutoRequestFocus(true);
        setAlwaysOnTop(true);

        if (owner == null) {
            setLocationByPlatform(true);
            return;
        }

        int x = owner.getX() + (owner.getWidth() - getWidth()) / 2;
        int y = owner.getY() + (owner.getHeight() - getHeight()) / 2;
        setLocation(x, y);
    }

    private Sinistro localizarSinistro(int id) {
        if (sinistros_cadastrados == null) return null;
        for (Sinistro visao : sinistros_cadastrados) {
            if (visao.getId() == id) return visao;
        }
        return null;
    }

    private void atualizarSinistrosCadastrados() {
        atualizarSinistrosCadastrados(0);
    }

    private void atualizarSinistrosCadastrados(int id_selecionado) {
        sinistros_cadastrados = Sinistro.getVisoes();
        sinistros_cadastrados_combo_box.setModel(new DefaultComboBoxModel(sinistros_cadastrados));

        Sinistro visao_selecionada = localizarSinistro(id_selecionado);
        if (visao_selecionada != null) {
            sinistros_cadastrados_combo_box.setSelectedItem(visao_selecionada);
        }
    }

    private void informarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void atualizarListaPeçasSinistro(int sinistro_id) {
        pecas_sinistro_model.clear();
        if (sinistro_id <= 0) {
            pecas_sinistro_list.setModel(pecas_sinistro_model);
            return;
        }

        for (entidades.Peça peca : entidades.Peça.buscarPeçasPorSinistro(sinistro_id)) {
            pecas_sinistro_model.addElement(peca);
        }
        pecas_sinistro_list.setModel(pecas_sinistro_model);
    }

    private Boolean obterPerdaTotalInformada() {
        return perda_total_check_box.isSelected();
    }

    private void definirPerdaTotal(Boolean valor) {
        perda_total_check_box.setSelected(Boolean.TRUE.equals(valor));
    }

    private Sinistro obterSinistroInformado() {
        String segurado = segurado_text_field.getText().trim();
        if (segurado.isEmpty()) return null;

        String telefone = telefone_text_field.getText().trim();
        if (telefone.isEmpty()) return null;

        String cidade = cliente_text_field.getText().trim();
        if (cidade.isEmpty()) cidade = null;

        Sinistro.GrauMonta grau_monta = obterGrauMontaSelecionado();
        if (grau_monta == null) return null;

        Boolean perda_total = obterPerdaTotalInformada();
        if (perda_total == null) return null;

        int id = numero_text_field.getText().trim().isEmpty() ? 0
                : Integer.parseInt(numero_text_field.getText().trim());
        return new Sinistro(id, segurado, telefone, cidade, grau_monta, perda_total);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints grid_bag_constraints;

        comandos_panel = new javax.swing.JPanel();
        inserir_sinistro = new javax.swing.JButton();
        alterar_sinistro = new javax.swing.JButton();
        consultar_sinistro = new javax.swing.JButton();
        remover_sinistro = new javax.swing.JButton();
        pecas_sinistro = new javax.swing.JButton();
        limpar_campos = new javax.swing.JButton();
        sinistros_cadastrados_label = new javax.swing.JLabel();
        sinistros_cadastrados_combo_box = new javax.swing.JComboBox();
        numero_label = new javax.swing.JLabel();
        numero_text_field = new javax.swing.JTextField();
        segurado_label = new javax.swing.JLabel();
        segurado_text_field = new javax.swing.JTextField();
        cliente_label = new javax.swing.JLabel();
        cliente_text_field = new javax.swing.JTextField();
        telefone_label = new javax.swing.JLabel();
        telefone_text_field = new javax.swing.JTextField();
        grau_monta_label = new javax.swing.JLabel();
        grau_monta_button_group = new javax.swing.ButtonGroup();
        grau_monta_panel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        grau_monta_pequena_radio_button = new javax.swing.JRadioButton(Sinistro.GrauMonta.pequena.toString());
        grau_monta_media_radio_button = new javax.swing.JRadioButton(Sinistro.GrauMonta.media.toString());
        grau_monta_grande_radio_button = new javax.swing.JRadioButton(Sinistro.GrauMonta.grande.toString());
        grau_monta_button_group.add(grau_monta_pequena_radio_button);
        grau_monta_button_group.add(grau_monta_media_radio_button);
        grau_monta_button_group.add(grau_monta_grande_radio_button);
        grau_monta_panel.add(grau_monta_pequena_radio_button);
        grau_monta_panel.add(grau_monta_media_radio_button);
        grau_monta_panel.add(grau_monta_grande_radio_button);
        perda_total_label = new javax.swing.JLabel();
        perda_total_panel = new javax.swing.JPanel();
        perda_total_check_box = new javax.swing.JCheckBox();
        pecas_sinistro_label = new javax.swing.JLabel();
        pecas_sinistro_scroll_pane = new javax.swing.JScrollPane();
        pecas_sinistro_list = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Sinistros");
        setMinimumSize(new java.awt.Dimension(680, 420));
        setPreferredSize(new java.awt.Dimension(820, 620));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        inserir_sinistro.setText("Inserir");
        inserir_sinistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirSinistro(evt);
            }
        });
        comandos_panel.add(inserir_sinistro);

        alterar_sinistro.setText("Alterar");
        alterar_sinistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarSinistro(evt);
            }
        });
        comandos_panel.add(alterar_sinistro);

        consultar_sinistro.setText("Consultar");
        consultar_sinistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarSinistro(evt);
            }
        });
        comandos_panel.add(consultar_sinistro);

        remover_sinistro.setText("Remover");
        remover_sinistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerSinistro(evt);
            }
        });
        comandos_panel.add(remover_sinistro);

        pecas_sinistro.setText("Peças");
        pecas_sinistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pecasSinistro(evt);
            }
        });
        comandos_panel.add(pecas_sinistro);

        limpar_campos.setText("Limpar");
        limpar_campos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparCampos(evt);
            }
        });
        comandos_panel.add(limpar_campos);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 8;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        grid_bag_constraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(comandos_panel, grid_bag_constraints);

        sinistros_cadastrados_label.setText("Sinistros Cadastrados");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(sinistros_cadastrados_label, grid_bag_constraints);

        sinistros_cadastrados_combo_box.setModel(new DefaultComboBoxModel(sinistros_cadastrados));
        sinistros_cadastrados_combo_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinistrosCadastradosComboBoxActionPerformed(evt);
            }
        });
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(sinistros_cadastrados_combo_box, grid_bag_constraints);

        numero_label.setText("ID");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 1;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(numero_label, grid_bag_constraints);

        numero_text_field.setEditable(false);
        numero_text_field.setColumns(8);
        numero_text_field.setMinimumSize(new java.awt.Dimension(80, 20));
        numero_text_field.setPreferredSize(new java.awt.Dimension(80, 20));
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 1;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(numero_text_field, grid_bag_constraints);

        segurado_label.setText("Segurado");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 2;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(segurado_label, grid_bag_constraints);

        segurado_text_field.setColumns(24);
        segurado_text_field.setMinimumSize(new java.awt.Dimension(240, 20));
        segurado_text_field.setPreferredSize(new java.awt.Dimension(240, 20));
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 2;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(segurado_text_field, grid_bag_constraints);

        cliente_label.setText("Cidade");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 3;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(cliente_label, grid_bag_constraints);

        cliente_text_field.setColumns(16);
        cliente_text_field.setMinimumSize(new java.awt.Dimension(160, 20));
        cliente_text_field.setPreferredSize(new java.awt.Dimension(160, 20));
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 3;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(cliente_text_field, grid_bag_constraints);

        telefone_label.setText("Telefone");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 4;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(telefone_label, grid_bag_constraints);

        telefone_text_field.setColumns(12);
        telefone_text_field.setMinimumSize(new java.awt.Dimension(120, 20));
        telefone_text_field.setPreferredSize(new java.awt.Dimension(120, 20));
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 4;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(telefone_text_field, grid_bag_constraints);

        grau_monta_label.setText("Grau de Monta");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 5;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(grau_monta_label, grid_bag_constraints);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 5;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(grau_monta_panel, grid_bag_constraints);

        perda_total_label.setText("Perda Total");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 6;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(perda_total_label, grid_bag_constraints);

        perda_total_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        perda_total_check_box.setText("Sim");
        perda_total_panel.add(perda_total_check_box);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 6;
        grid_bag_constraints.gridwidth = 2;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(perda_total_panel, grid_bag_constraints);

        pecas_sinistro_label.setText("Peças do Sinistro");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 7;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(pecas_sinistro_label, grid_bag_constraints);

        pecas_sinistro_scroll_pane.setPreferredSize(new java.awt.Dimension(300, 90));

        pecas_sinistro_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pecas_sinistro_list.setModel(pecas_sinistro_model);
        pecas_sinistro_scroll_pane.setViewportView(pecas_sinistro_list);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 7;
        grid_bag_constraints.fill = java.awt.GridBagConstraints.BOTH;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.weightx = 1.0;
        grid_bag_constraints.weighty = 1.0;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(pecas_sinistro_scroll_pane, grid_bag_constraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private Sinistro.GrauMonta obterGrauMontaSelecionado() {
        if (grau_monta_pequena_radio_button.isSelected()) return Sinistro.GrauMonta.pequena;
        if (grau_monta_media_radio_button.isSelected()) return Sinistro.GrauMonta.media;
        if (grau_monta_grande_radio_button.isSelected()) return Sinistro.GrauMonta.grande;
        return null;
    }

    private void definirGrauMontaSelecionado(Sinistro.GrauMonta valor) {
        grau_monta_button_group.clearSelection();
        if (valor == Sinistro.GrauMonta.pequena) grau_monta_pequena_radio_button.setSelected(true);
        else if (valor == Sinistro.GrauMonta.media) grau_monta_media_radio_button.setSelected(true);
        else if (valor == Sinistro.GrauMonta.grande) grau_monta_grande_radio_button.setSelected(true);
    }

    private void sinistrosCadastradosComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sinistros_cadastradosComboBoxActionPerformed
        Sinistro visao = (Sinistro) sinistros_cadastrados_combo_box.getSelectedItem();
        atualizarListaPeçasSinistro(visao != null ? visao.getId() : 0);
    }//GEN-LAST:event_sinistros_cadastradosComboBoxActionPerformed

    private void limparCampos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparCampos
        numero_text_field.setText("");
        segurado_text_field.setText("");
        cliente_text_field.setText("");
        telefone_text_field.setText("");
        grau_monta_button_group.clearSelection();
        perda_total_check_box.setSelected(false);
        atualizarListaPeçasSinistro(0);
    }//GEN-LAST:event_limparCampos

    private void consultarSinistro(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarSinistro
        Sinistro visao = (Sinistro) sinistros_cadastrados_combo_box.getSelectedItem();
        String mensagem_erro = null;
        if (visao == null) {
            mensagem_erro = "Nenhum Sinistro selecionado";
        } else {
            Sinistro sinistro = Sinistro.buscarSinistro(visao.getId());
            if (sinistro == null) {
                mensagem_erro = "Sinistro não cadastrado";
            } else {
                numero_text_field.setText(String.valueOf(sinistro.getId()));
                segurado_text_field.setText(sinistro.getSegurado());
                cliente_text_field.setText(sinistro.getCidade());
                telefone_text_field.setText(sinistro.getTelefone());
                definirGrauMontaSelecionado(sinistro.getGrauMonta());
                definirPerdaTotal(sinistro.getPerdaTotal());
                atualizarListaPeçasSinistro(sinistro.getId());
            }
        }

        if (mensagem_erro != null) informarErro(mensagem_erro);
    }//GEN-LAST:event_consultarSinistro

    private void inserirSinistro(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirSinistro
        Sinistro sinistro = obterSinistroInformado();
        String mensagem_erro = null;

        if (sinistro != null) {
            mensagem_erro = controlador.inserirSinistro(sinistro);
        } else {
            mensagem_erro = "Algum atributo do Sinistro não foi informado";
        }

        if (mensagem_erro == null) {
            atualizarSinistrosCadastrados(sinistro.getId());
            atualizarListaPeçasSinistro(sinistro.getId());
            numero_text_field.setText(String.valueOf(sinistro.getId()));
        } else {
            informarErro(mensagem_erro);
        }
    }//GEN-LAST:event_inserirSinistro

    private void alterarSinistro(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarSinistro
        Sinistro sinistro = obterSinistroInformado();
        String mensagem_erro = null;

        if (sinistro != null) {
            mensagem_erro = controlador.alterarSinistro(sinistro);
        } else {
            mensagem_erro = "Algum atributo do Sinistro não foi informado";
        }

        if (mensagem_erro == null) {
            Sinistro visao = (Sinistro) sinistros_cadastrados_combo_box.getSelectedItem();

            if (visao != null) {
                visao.setSegurado(sinistro.getSegurado());
                visao.setTelefone(sinistro.getTelefone());
                visao.setCidade(sinistro.getCidade());
                visao.setGrauMonta(sinistro.getGrauMonta());
                visao.setPerdaTotal(sinistro.getPerdaTotal());

                sinistros_cadastrados_combo_box.updateUI();
            }
        } else {
            informarErro(mensagem_erro);
        }
    }//GEN-LAST:event_alterarSinistro

    private void removerSinistro(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerSinistro
        Sinistro visao = (Sinistro) sinistros_cadastrados_combo_box.getSelectedItem();
        String mensagem_erro = null;

        if (visao != null) {
            mensagem_erro = controlador.removerSinistro(visao.getId());
        } else {
            mensagem_erro = "Nenhum Sinistro selecionado";
        }

        if (mensagem_erro == null) {
            atualizarSinistrosCadastrados();
            limparCampos(evt);
        } else {
            informarErro(mensagem_erro);
        }
    }//GEN-LAST:event_removerSinistro

    private void pecasSinistro(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pecasSinistro
        Sinistro visao = (Sinistro) sinistros_cadastrados_combo_box.getSelectedItem();
        String mensagem_erro = null;

        if (visao == null) {
            mensagem_erro = "Nenhum Sinistro selecionado";
        } else {
            Sinistro sinistro = Sinistro.buscarSinistro(visao.getId());
            if (sinistro == null) {
                mensagem_erro = "Sinistro não cadastrado";
            } else {
                new ControladorCadastroPeçasSinistros(this, sinistro);
                atualizarListaPeçasSinistro(sinistro.getId());
            }
        }

        if (mensagem_erro != null) {
            informarErro(mensagem_erro);
        }
    }//GEN-LAST:event_pecasSinistro

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterar_sinistro;
    private javax.swing.JLabel cliente_label;
    private javax.swing.JTextField cliente_text_field;
    private javax.swing.JPanel comandos_panel;
    private javax.swing.JButton consultar_sinistro;
    private javax.swing.ButtonGroup grau_monta_button_group;
    private javax.swing.JPanel grau_monta_panel;
    private javax.swing.JRadioButton grau_monta_pequena_radio_button;
    private javax.swing.JRadioButton grau_monta_media_radio_button;
    private javax.swing.JRadioButton grau_monta_grande_radio_button;
    private javax.swing.JLabel grau_monta_label;
    private javax.swing.JButton inserir_sinistro;
    private javax.swing.JButton limpar_campos;
    private javax.swing.JLabel numero_label;
    private javax.swing.JTextField numero_text_field;
    private javax.swing.JButton pecas_sinistro;
    private javax.swing.JLabel pecas_sinistro_label;
    private javax.swing.JList pecas_sinistro_list;
    private javax.swing.JScrollPane pecas_sinistro_scroll_pane;
    private javax.swing.JCheckBox perda_total_check_box;
    private javax.swing.JLabel perda_total_label;
    private javax.swing.JPanel perda_total_panel;
    private javax.swing.JButton remover_sinistro;
    private javax.swing.JLabel segurado_label;
    private javax.swing.JTextField segurado_text_field;
    private javax.swing.JComboBox sinistros_cadastrados_combo_box;
    private javax.swing.JLabel sinistros_cadastrados_label;
    private javax.swing.JLabel telefone_label;
    private javax.swing.JTextField telefone_text_field;
    // End of variables declaration//GEN-END:variables
}
