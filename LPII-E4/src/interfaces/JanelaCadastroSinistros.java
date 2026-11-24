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
        if (perda_total_sim_radio_button.isSelected()) return true;
        if (perda_total_nao_radio_button.isSelected()) return false;
        return null;
    }

    private void definirPerdaTotal(Boolean valor) {
        if (Boolean.TRUE.equals(valor)) perda_total_sim_radio_button.setSelected(true);
        else perda_total_nao_radio_button.setSelected(true);
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
        java.awt.GridBagConstraints gridBagConstraints;

        perda_total_button_group = new javax.swing.ButtonGroup();
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
        grau_monta_combo_box = new javax.swing.JComboBox();
        perda_total_label = new javax.swing.JLabel();
        perda_total_panel = new javax.swing.JPanel();
        perda_total_sim_radio_button = new javax.swing.JRadioButton();
        perda_total_nao_radio_button = new javax.swing.JRadioButton();
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(comandos_panel, gridBagConstraints);

        sinistros_cadastrados_label.setText("Sinistros Cadastrados");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(sinistros_cadastrados_label, gridBagConstraints);

        sinistros_cadastrados_combo_box.setModel(new DefaultComboBoxModel(sinistros_cadastrados));
        sinistros_cadastrados_combo_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinistrosCadastradosComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(sinistros_cadastrados_combo_box, gridBagConstraints);

        numero_label.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(numero_label, gridBagConstraints);

        numero_text_field.setEditable(false);
        numero_text_field.setColumns(8);
        numero_text_field.setMinimumSize(new java.awt.Dimension(80, 20));
        numero_text_field.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(numero_text_field, gridBagConstraints);

        segurado_label.setText("Segurado");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(segurado_label, gridBagConstraints);

        segurado_text_field.setColumns(24);
        segurado_text_field.setMinimumSize(new java.awt.Dimension(240, 20));
        segurado_text_field.setPreferredSize(new java.awt.Dimension(240, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(segurado_text_field, gridBagConstraints);

        cliente_label.setText("Cidade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(cliente_label, gridBagConstraints);

        cliente_text_field.setColumns(16);
        cliente_text_field.setMinimumSize(new java.awt.Dimension(160, 20));
        cliente_text_field.setPreferredSize(new java.awt.Dimension(160, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(cliente_text_field, gridBagConstraints);

        telefone_label.setText("Telefone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(telefone_label, gridBagConstraints);

        telefone_text_field.setColumns(12);
        telefone_text_field.setMinimumSize(new java.awt.Dimension(120, 20));
        telefone_text_field.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(telefone_text_field, gridBagConstraints);

        grau_monta_label.setText("Grau de Monta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(grau_monta_label, gridBagConstraints);
        grau_monta_combo_box.setModel(new DefaultComboBoxModel(Sinistro.GrauMonta.values()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(grau_monta_combo_box, gridBagConstraints);

        perda_total_label.setText("Perda Total");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(perda_total_label, gridBagConstraints);

        perda_total_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        perda_total_sim_radio_button.setText("Sim");
        perda_total_panel.add(perda_total_sim_radio_button);

        perda_total_nao_radio_button.setText("Não");
        perda_total_panel.add(perda_total_nao_radio_button);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(perda_total_panel, gridBagConstraints);

        pecas_sinistro_label.setText("Peças do Sinistro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(pecas_sinistro_label, gridBagConstraints);

        pecas_sinistro_scroll_pane.setPreferredSize(new java.awt.Dimension(300, 90));

        pecas_sinistro_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pecas_sinistro_list.setModel(pecas_sinistro_model);
        pecas_sinistro_scroll_pane.setViewportView(pecas_sinistro_list);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(pecas_sinistro_scroll_pane, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private Sinistro.GrauMonta obterGrauMontaSelecionado() {
        return (Sinistro.GrauMonta) grau_monta_combo_box.getSelectedItem();
    }

    private void definirGrauMontaSelecionado(Sinistro.GrauMonta valor) {
        grau_monta_combo_box.setSelectedItem(valor);
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
        grau_monta_combo_box.setSelectedIndex(-1);
        definirPerdaTotal(false);
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
    private javax.swing.JComboBox grau_monta_combo_box;
    private javax.swing.JLabel grau_monta_label;
    private javax.swing.JButton inserir_sinistro;
    private javax.swing.JButton limpar_campos;
    private javax.swing.JLabel numero_label;
    private javax.swing.JTextField numero_text_field;
    private javax.swing.JButton pecas_sinistro;
    private javax.swing.JLabel pecas_sinistro_label;
    private javax.swing.JList pecas_sinistro_list;
    private javax.swing.JScrollPane pecas_sinistro_scroll_pane;
    private javax.swing.ButtonGroup perda_total_button_group;
    private javax.swing.JLabel perda_total_label;
    private javax.swing.JRadioButton perda_total_nao_radio_button;
    private javax.swing.JPanel perda_total_panel;
    private javax.swing.JRadioButton perda_total_sim_radio_button;
    private javax.swing.JButton remover_sinistro;
    private javax.swing.JLabel segurado_label;
    private javax.swing.JTextField segurado_text_field;
    private javax.swing.JComboBox sinistros_cadastrados_combo_box;
    private javax.swing.JLabel sinistros_cadastrados_label;
    private javax.swing.JLabel telefone_label;
    private javax.swing.JTextField telefone_text_field;
    // End of variables declaration//GEN-END:variables
}
