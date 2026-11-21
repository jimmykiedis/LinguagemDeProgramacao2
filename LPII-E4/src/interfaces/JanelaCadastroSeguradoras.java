package interfaces;

import javax.swing.JOptionPane;
import controles.ControladorCadastroSeguradoras;
import entidades.Seguradora;
import javax.swing.DefaultComboBoxModel;
import java.awt.Frame;

public class JanelaCadastroSeguradoras extends javax.swing.JFrame {

    ControladorCadastroSeguradoras controlador;
    Seguradora[] seguradoras_cadastradas;

    public JanelaCadastroSeguradoras(ControladorCadastroSeguradoras controlador) {
        this(controlador, null);
    }

    public JanelaCadastroSeguradoras(
            ControladorCadastroSeguradoras controlador,
            Frame owner
    ) {
        this.controlador = controlador;
        seguradoras_cadastradas = Seguradora.getVisoes();
        initComponents();
        atualizarSeguradorasCadastradas();
        setSize(new java.awt.Dimension(760, 540));
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

    private Seguradora localizarSeguradora(String nome) {
        if (nome == null) return null;
        for (Seguradora visao : seguradoras_cadastradas) {
            if (nome.equals(visao.getNome())) return visao;
        }
        return null;
    }

    private void atualizarSeguradorasCadastradas() {
        atualizarSeguradorasCadastradas(null);
    }

    private void atualizarSeguradorasCadastradas(String nome_selecionado) {
        seguradoras_cadastradas = Seguradora.getVisoes();
        seguradoras_cadastradas_combo_box.setModel(
                new DefaultComboBoxModel(seguradoras_cadastradas)
        );

        Seguradora visao_selecionada = localizarSeguradora(nome_selecionado);
        if (visao_selecionada != null) {
            seguradoras_cadastradas_combo_box.setSelectedItem(visao_selecionada);
        }
    }

    private void definirFormaPagamentoPreferencial(Seguradora.FormaPagamentoPreferencial valor) {
        forma_pagamento_preferencial_combo_box.setSelectedItem(valor);
    }

    private void informarErro(String mensagem) {
        JOptionPane.showMessageDialog(
            this,
            mensagem,
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );
    }

    private boolean obterPossuiAtendimento24hInformado() {
        return possui_atendimento_24h_check_box.isSelected();
    }

    private void definirPossuiAtendimento24h(boolean valor) {
        possui_atendimento_24h_check_box.setSelected(valor);
    }

    private Seguradora.FormaPagamentoPreferencial obterFormaPagamentoPreferencialSelecionada() {
        return (Seguradora.FormaPagamentoPreferencial)
                forma_pagamento_preferencial_combo_box.getSelectedItem();
    }

    private Seguradora obterSeguradoraInformada() {

        String nome = nome_text_field.getText();
        if (nome.isEmpty()) return null;

        String cidade = cidade_text_field.getText();
        if (cidade.isEmpty()) cidade = null;

        String cobertura_str = cobertura_percentual_text_field.getText();
        if (cobertura_str.isEmpty()) return null;

        double cobertura_percentual;

        try {
            cobertura_percentual =
                    Double.parseDouble(cobertura_str.replace(",", "."));
        } catch (NumberFormatException e) {
            return null;
        }

        boolean possui_atendimento_24h = obterPossuiAtendimento24hInformado();

        Seguradora.FormaPagamentoPreferencial forma_pagamento_preferencial =
                obterFormaPagamentoPreferencialSelecionada();
        if (forma_pagamento_preferencial == null) return null;

        return new Seguradora(
            nome,
            cidade,
            cobertura_percentual,
            possui_atendimento_24h,
            forma_pagamento_preferencial
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints grid_bag_constraints;

        comandos_panel = new javax.swing.JPanel();
        inserir_seguradora = new javax.swing.JButton();
        alterar_seguradora = new javax.swing.JButton();
        consultar_seguradora = new javax.swing.JButton();
        remover_seguradora = new javax.swing.JButton();
        limpar_campos = new javax.swing.JButton();
        nome_label = new javax.swing.JLabel();
        nome_text_field = new javax.swing.JTextField();
        cidade_label = new javax.swing.JLabel();
        cidade_text_field = new javax.swing.JTextField();
        cobertura_percentual_label = new javax.swing.JLabel();
        cobertura_percentual_text_field = new javax.swing.JTextField();
        possui_atendimento_24h_label = new javax.swing.JLabel();
        possui_atendimento_24h_panel = new javax.swing.JPanel();
        possui_atendimento_24h_check_box = new javax.swing.JCheckBox();
        forma_pagamento_preferencial_combo_box = new javax.swing.JComboBox<>(
                Seguradora.FormaPagamentoPreferencial.values()
        );
        forma_pagamento_preferencial_label = new javax.swing.JLabel();
        seguradoras_cadastradas_label = new javax.swing.JLabel();
        seguradoras_cadastradas_combo_box = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Seguradoras");
        setMinimumSize(new java.awt.Dimension(620, 360));
        setPreferredSize(new java.awt.Dimension(700, 460));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        inserir_seguradora.setText("Inserir");
        inserir_seguradora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirSeguradora(evt);
            }
        });
        comandos_panel.add(inserir_seguradora);

        alterar_seguradora.setText("Alterar");
        alterar_seguradora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarSeguradora(evt);
            }
        });
        comandos_panel.add(alterar_seguradora);

        consultar_seguradora.setText("Consultar");
        consultar_seguradora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarSeguradora(evt);
            }
        });
        comandos_panel.add(consultar_seguradora);

        remover_seguradora.setText("Remover");
        remover_seguradora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerSeguradora(evt);
            }
        });
        comandos_panel.add(remover_seguradora);

        limpar_campos.setText("Limpar");
        limpar_campos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparCampos(evt);
            }
        });
        comandos_panel.add(limpar_campos);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 6;
        grid_bag_constraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(comandos_panel, grid_bag_constraints);

        nome_label.setText("Nome");
        nome_label.setMaximumSize(null);
        nome_label.setMinimumSize(null);
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 1;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 16, 10, 10);
        getContentPane().add(nome_label, grid_bag_constraints);

        nome_text_field.setColumns(24);
        nome_text_field.setPreferredSize(new java.awt.Dimension(240, 20));
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 1;
        grid_bag_constraints.ipadx = 80;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(nome_text_field, grid_bag_constraints);

        cidade_label.setText("Cidade");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 2;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 16, 10, 10);
        getContentPane().add(cidade_label, grid_bag_constraints);

        cidade_text_field.setColumns(18);
        cidade_text_field.setPreferredSize(new java.awt.Dimension(180, 20));
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 2;
        grid_bag_constraints.ipadx = 20;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(cidade_text_field, grid_bag_constraints);

        cobertura_percentual_label.setText("Cobertura Percentual");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 3;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 16, 10, 10);
        getContentPane().add(cobertura_percentual_label, grid_bag_constraints);

        cobertura_percentual_text_field.setColumns(10);
        cobertura_percentual_text_field.setPreferredSize(new java.awt.Dimension(100, 20));
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 3;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(cobertura_percentual_text_field, grid_bag_constraints);

        possui_atendimento_24h_label.setText("Possui Atendimento 24h");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 4;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 16, 10, 10);
        getContentPane().add(possui_atendimento_24h_label, grid_bag_constraints);

        possui_atendimento_24h_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        possui_atendimento_24h_check_box.setText("Sim");
        possui_atendimento_24h_panel.add(possui_atendimento_24h_check_box);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 4;
        grid_bag_constraints.gridwidth = 2;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(possui_atendimento_24h_panel, grid_bag_constraints);

        forma_pagamento_preferencial_label.setText("Forma de Pagamento Preferencial");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 5;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 16, 10, 10);
        getContentPane().add(forma_pagamento_preferencial_label, grid_bag_constraints);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 5;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(forma_pagamento_preferencial_combo_box, grid_bag_constraints);

        seguradoras_cadastradas_label.setText("Seguradoras Cadastradas");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(0, 12, 0, 5);
        getContentPane().add(seguradoras_cadastradas_label, grid_bag_constraints);

        seguradoras_cadastradas_combo_box.setModel(new DefaultComboBoxModel(seguradoras_cadastradas));
        seguradoras_cadastradas_combo_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seguradorasCadastradasComboBoxActionPerformed(evt);
            }
        });
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(seguradoras_cadastradas_combo_box, grid_bag_constraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void alterarSeguradora(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarSeguradora
        Seguradora seguradora =
                obterSeguradoraInformada();

        String mensagem_erro = null;

        if (seguradora != null)
            mensagem_erro =
                    controlador.alterarSeguradora(seguradora);
        else
            mensagem_erro =
                    "Algum atributo da Seguradora não foi informado.";

        if (mensagem_erro == null) {

            Seguradora visao =
                    (Seguradora) seguradoras_cadastradas_combo_box.getSelectedItem();

            if (visao != null) {

                visao.setCidade(
                        seguradora.getCidade()
                );

                seguradoras_cadastradas_combo_box.updateUI();
            }

        } else {
            informarErro(mensagem_erro);
        }
    }//GEN-LAST:event_alterarSeguradora

    private void inserirSeguradora(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirSeguradora
        Seguradora seguradora =
                obterSeguradoraInformada();

        String mensagem_erro = null;

        if (seguradora != null)
            mensagem_erro =
                    controlador.inserirSeguradora(seguradora);
        else
            mensagem_erro =
                    "Algum atributo da Seguradora não foi informado";

        if (mensagem_erro == null) {

            Seguradora visao = seguradora.getVisao();
            atualizarSeguradorasCadastradas(visao.getNome());

        } else {
            informarErro(mensagem_erro);
        }
    }//GEN-LAST:event_inserirSeguradora

    private void consultarSeguradora(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarSeguradora
        Seguradora visao =
                (Seguradora)
                seguradoras_cadastradas_combo_box
                        .getSelectedItem();

        Seguradora seguradora = null;

        String mensagem_erro = null;

        if (visao != null) {

            seguradora =
                    Seguradora.buscarSeguradora(
                            visao.getNome()
                    );

            if (seguradora == null)
                mensagem_erro =
                        "Seguradora não cadastrada";

        } else {
            mensagem_erro =
                    "Nenhuma Seguradora selecionada";
        }

        if (mensagem_erro == null) {

            nome_text_field.setText(
                    seguradora.getNome()
            );

            String cidade =
                    seguradora.getCidade();

            if (cidade == null)
                cidade = "";

            cidade_text_field.setText(cidade);

            cobertura_percentual_text_field.setText(
                    String.valueOf(
                            seguradora
                                    .getCoberturaPercentual()
                    )
            );

            definirPossuiAtendimento24h(seguradora.getPossuiAtendimento24h());

            definirFormaPagamentoPreferencial(seguradora.getFormaPagamentoPreferencial());

        } else {
            informarErro(mensagem_erro);
        }
    }//GEN-LAST:event_consultarSeguradora

    private void removerSeguradora(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerSeguradora
        Seguradora visao =
                (Seguradora)
                seguradoras_cadastradas_combo_box
                        .getSelectedItem();

        String mensagem_erro = null;

        if (visao != null)
            mensagem_erro =
                    controlador.removerSeguradora(
                            visao.getNome()
                    );
        else
            mensagem_erro =
                    "Nenhuma Seguradora selecionada";

        if (mensagem_erro == null) {

            atualizarSeguradorasCadastradas();

            limparCampos(evt);

        } else {
            informarErro(mensagem_erro);
        }
    }//GEN-LAST:event_removerSeguradora

    private void limparCampos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparCampos
        nome_text_field.setText("");
        cidade_text_field.setText("");
        cobertura_percentual_text_field.setText("");
        possui_atendimento_24h_check_box.setSelected(false);
        definirFormaPagamentoPreferencial(Seguradora.FormaPagamentoPreferencial.boleto);
    }//GEN-LAST:event_limparCampos

    private void seguradorasCadastradasComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seguradoras_cadastradasComboBoxActionPerformed
       
    }//GEN-LAST:event_seguradoras_cadastradasComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterar_seguradora;
    private javax.swing.JLabel cidade_label;
    private javax.swing.JTextField cidade_text_field;
    private javax.swing.JLabel cobertura_percentual_label;
    private javax.swing.JTextField cobertura_percentual_text_field;
    private javax.swing.JPanel comandos_panel;
    private javax.swing.JButton consultar_seguradora;
    private javax.swing.JButton inserir_seguradora;
    private javax.swing.JButton limpar_campos;
    private javax.swing.JLabel nome_label;
    private javax.swing.JTextField nome_text_field;
    private javax.swing.JLabel possui_atendimento_24h_label;
    private javax.swing.JCheckBox possui_atendimento_24h_check_box;
    private javax.swing.JPanel possui_atendimento_24h_panel;
    private javax.swing.JComboBox<Seguradora.FormaPagamentoPreferencial> forma_pagamento_preferencial_combo_box;
    private javax.swing.JLabel forma_pagamento_preferencial_label;
    private javax.swing.JButton remover_seguradora;
    private javax.swing.JComboBox seguradoras_cadastradas_combo_box;
    private javax.swing.JLabel seguradoras_cadastradas_label;
    // End of variables declaration//GEN-END:variables
}
