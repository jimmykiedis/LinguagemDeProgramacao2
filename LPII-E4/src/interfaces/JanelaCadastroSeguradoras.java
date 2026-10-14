package interfaces;

import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import controles.ControladorCadastroSeguradoras;
import entidades.Seguradora;
import javax.swing.DefaultComboBoxModel;
import java.awt.Frame;

public class JanelaCadastroSeguradoras extends javax.swing.JFrame {

    ControladorCadastroSeguradoras controlador;
    Seguradora[] seguradorasCadastradas;

    public JanelaCadastroSeguradoras(ControladorCadastroSeguradoras controlador) {
        this(controlador, null);
    }

    public JanelaCadastroSeguradoras(
            ControladorCadastroSeguradoras controlador,
            Frame owner
    ) {
        this.controlador = controlador;
        seguradorasCadastradas = Seguradora.getVisoes();
        initComponents();
        atualizarSeguradorasCadastradas();
        setSize(new java.awt.Dimension(760, 540));
        configurarJanelaDependente(owner);
        limparCampos(null);
    }

    private void configurarJanelaDependente(Frame owner) {
        setAutoRequestFocus(true);
        JanelaUtil.posicionarNoCanto(this);
    }

    private Seguradora localizarSeguradora(String nome) {
        if (nome == null) return null;
        for (Seguradora visao : seguradorasCadastradas) {
            if (nome.equals(visao.getNome())) return visao;
        }
        return null;
    }

    private void atualizarSeguradorasCadastradas() {
        atualizarSeguradorasCadastradas(null);
    }

    private void atualizarSeguradorasCadastradas(String nomeSelecionado) {
        seguradorasCadastradas = Seguradora.getVisoes();
        seguradorasCadastradasComboBox.setModel(
                new DefaultComboBoxModel(seguradorasCadastradas)
        );

        Seguradora visaoSelecionada = localizarSeguradora(nomeSelecionado);
        if (visaoSelecionada != null) {
            seguradorasCadastradasComboBox.setSelectedItem(visaoSelecionada);
        }
    }

    private void informarErro(String mensagem) {
        JOptionPane.showMessageDialog(
            this,
            mensagem,
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );
    }

    private Boolean obterPossuiAtendimento24hInformado() {
        if (possuiAtendimento24hSimRadioButton.isSelected()) return true;
        if (possuiAtendimento24hNaoRadioButton.isSelected()) return false;
        return null;
    }

    private void definirPossuiAtendimento24h(Boolean valor) {
        possuiAtendimento24hButtonGroup.clearSelection();
        if (valor == null) return;

        if (valor) {
            possuiAtendimento24hSimRadioButton.setSelected(true);
        } else {
            possuiAtendimento24hNaoRadioButton.setSelected(true);
        }
    }

    private Seguradora.FormaPagamentoPreferencial obterFormaPagamentoPreferencialSelecionada() {
        Object selecionado = formaPagamentoPreferencialComboBox.getSelectedItem();
        if (selecionado == null) {
            return null;
        }

        return Seguradora.FormaPagamentoPreferencial.fromTexto(selecionado.toString());
    }

    private Seguradora obterSeguradoraInformada() {

        String nome = nomeTextField.getText();
        if (nome.isEmpty()) return null;

        String cidade = cidadeTextField.getText();
        if (cidade.isEmpty()) cidade = null;

        String coberturaStr = coberturaPercentualTextField.getText();
        if (coberturaStr.isEmpty()) return null;

        double coberturaPercentual;

        try {
            coberturaPercentual =
                    Double.parseDouble(coberturaStr.replace(",", "."));
        } catch (NumberFormatException e) {
            return null;
        }

        Boolean possuiAtendimento24h =
                obterPossuiAtendimento24hInformado();
        if (possuiAtendimento24h == null) return null;

        Seguradora.FormaPagamentoPreferencial formaPagamentoPreferencial =
                obterFormaPagamentoPreferencialSelecionada();
        if (formaPagamentoPreferencial == null) return null;

        return new Seguradora(
            nome,
            cidade,
            coberturaPercentual,
            possuiAtendimento24h,
            formaPagamentoPreferencial
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        comandosPanel = new javax.swing.JPanel();
        inserirSeguradora = new javax.swing.JButton();
        alterarSeguradora = new javax.swing.JButton();
        consultarSeguradora = new javax.swing.JButton();
        removerSeguradora = new javax.swing.JButton();
        limparCampos = new javax.swing.JButton();
        nomeLabel = new javax.swing.JLabel();
        nomeTextField = new javax.swing.JTextField();
        cidadeLabel = new javax.swing.JLabel();
        cidadeTextField = new javax.swing.JTextField();
        coberturaPercentualLabel = new javax.swing.JLabel();
        coberturaPercentualTextField = new javax.swing.JTextField();
        possuiAtendimento24hLabel = new javax.swing.JLabel();
        possuiAtendimento24hPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 0));
        possuiAtendimento24hButtonGroup = new ButtonGroup();
        possuiAtendimento24hSimRadioButton = new javax.swing.JRadioButton();
        possuiAtendimento24hNaoRadioButton = new javax.swing.JRadioButton();
        formaPagamentoPreferencialLabel = new javax.swing.JLabel();
        formaPagamentoPreferencialComboBox = new javax.swing.JComboBox();
        seguradorasCadastradasLabel = new javax.swing.JLabel();
        seguradorasCadastradasComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Seguradoras");
        setMinimumSize(new java.awt.Dimension(680, 440));
        setPreferredSize(new java.awt.Dimension(760, 540));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        inserirSeguradora.setText("Inserir");
        inserirSeguradora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirSeguradora(evt);
            }
        });
        comandosPanel.add(inserirSeguradora);

        alterarSeguradora.setText("Alterar");
        alterarSeguradora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarSeguradora(evt);
            }
        });
        comandosPanel.add(alterarSeguradora);

        consultarSeguradora.setText("Consultar");
        consultarSeguradora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarSeguradora(evt);
            }
        });
        comandosPanel.add(consultarSeguradora);

        removerSeguradora.setText("Remover");
        removerSeguradora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerSeguradora(evt);
            }
        });
        comandosPanel.add(removerSeguradora);

        limparCampos.setText("Limpar");
        limparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparCampos(evt);
            }
        });
        comandosPanel.add(limparCampos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(comandosPanel, gridBagConstraints);

        nomeLabel.setText("Nome");
        nomeLabel.setMaximumSize(null);
        nomeLabel.setMinimumSize(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 16, 10, 10);
        getContentPane().add(nomeLabel, gridBagConstraints);

        nomeTextField.setColumns(24);
        nomeTextField.setPreferredSize(new java.awt.Dimension(240, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(nomeTextField, gridBagConstraints);

        cidadeLabel.setText("Cidade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 16, 10, 10);
        getContentPane().add(cidadeLabel, gridBagConstraints);

        cidadeTextField.setColumns(18);
        cidadeTextField.setPreferredSize(new java.awt.Dimension(180, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(cidadeTextField, gridBagConstraints);

        coberturaPercentualLabel.setText("Cobertura Percentual");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 16, 10, 10);
        getContentPane().add(coberturaPercentualLabel, gridBagConstraints);

        coberturaPercentualTextField.setColumns(10);
        coberturaPercentualTextField.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(coberturaPercentualTextField, gridBagConstraints);

        possuiAtendimento24hLabel.setText("Possui Atendimento 24h");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 16, 10, 10);
        getContentPane().add(possuiAtendimento24hLabel, gridBagConstraints);

        possuiAtendimento24hButtonGroup.add(possuiAtendimento24hSimRadioButton);
        possuiAtendimento24hSimRadioButton.setText("Sim");
        possuiAtendimento24hButtonGroup.add(possuiAtendimento24hNaoRadioButton);
        possuiAtendimento24hNaoRadioButton.setText("Não");
        possuiAtendimento24hPanel.add(possuiAtendimento24hSimRadioButton);
        possuiAtendimento24hPanel.add(possuiAtendimento24hNaoRadioButton);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(possuiAtendimento24hPanel, gridBagConstraints);

        formaPagamentoPreferencialLabel.setText("Forma Pagamento Preferencial");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 16, 10, 10);
        getContentPane().add(formaPagamentoPreferencialLabel, gridBagConstraints);

        formaPagamentoPreferencialComboBox.setModel(
                new DefaultComboBoxModel(
                        new String[] {"boleto", "cartao", "pix", "debito_automatico"}
                )
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(formaPagamentoPreferencialComboBox, gridBagConstraints);

        seguradorasCadastradasLabel.setText("Seguradoras Cadastradas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 5);
        getContentPane().add(seguradorasCadastradasLabel, gridBagConstraints);

        seguradorasCadastradasComboBox.setModel(new DefaultComboBoxModel(seguradorasCadastradas));
        seguradorasCadastradasComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seguradorasCadastradasComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(seguradorasCadastradasComboBox, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void alterarSeguradora(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarSeguradora
        Seguradora seguradora =
                obterSeguradoraInformada();

        String mensagemErro = null;

        if (seguradora != null)
            mensagemErro =
                    controlador.alterarSeguradora(seguradora);
        else
            mensagemErro =
                    "Algum atributo da Seguradora não foi informado.";

        if (mensagemErro == null) {

            Seguradora visao =
                    (Seguradora) seguradorasCadastradasComboBox.getSelectedItem();

            if (visao != null) {

                visao.setCidade(
                        seguradora.getCidade()
                );

                seguradorasCadastradasComboBox.updateUI();
            }

        } else {
            informarErro(mensagemErro);
        }
    }//GEN-LAST:event_alterarSeguradora

    private void inserirSeguradora(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirSeguradora
        Seguradora seguradora =
                obterSeguradoraInformada();

        String mensagemErro = null;

        if (seguradora != null)
            mensagemErro =
                    controlador.inserirSeguradora(seguradora);
        else
            mensagemErro =
                    "Algum atributo da Seguradora não foi informado";

        if (mensagemErro == null) {

            Seguradora visao = seguradora.getVisao();
            atualizarSeguradorasCadastradas(visao.getNome());

        } else {
            informarErro(mensagemErro);
        }
    }//GEN-LAST:event_inserirSeguradora

    private void consultarSeguradora(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarSeguradora
        Seguradora visao =
                (Seguradora)
                seguradorasCadastradasComboBox
                        .getSelectedItem();

        Seguradora seguradora = null;

        String mensagemErro = null;

        if (visao != null) {

            seguradora =
                    Seguradora.buscarSeguradora(
                            visao.getNome()
                    );

            if (seguradora == null)
                mensagemErro =
                        "Seguradora não cadastrada";

        } else {
            mensagemErro =
                    "Nenhuma Seguradora selecionada";
        }

        if (mensagemErro == null) {

            nomeTextField.setText(
                    seguradora.getNome()
            );

            String cidade =
                    seguradora.getCidade();

            if (cidade == null)
                cidade = "";

            cidadeTextField.setText(cidade);

            coberturaPercentualTextField.setText(
                    String.valueOf(
                            seguradora
                                    .getCoberturaPercentual()
                    )
            );

            definirPossuiAtendimento24h(seguradora.getPossuiAtendimento24h());

            formaPagamentoPreferencialComboBox.setSelectedItem(
                    seguradora.getFormaPagamentoPreferencial().toString()
            );

        } else {
            informarErro(mensagemErro);
        }
    }//GEN-LAST:event_consultarSeguradora

    private void removerSeguradora(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerSeguradora
        Seguradora visao =
                (Seguradora)
                seguradorasCadastradasComboBox
                        .getSelectedItem();

        String mensagemErro = null;

        if (visao != null)
            mensagemErro =
                    controlador.removerSeguradora(
                            visao.getNome()
                    );
        else
            mensagemErro =
                    "Nenhuma Seguradora selecionada";

        if (mensagemErro == null) {

            atualizarSeguradorasCadastradas();

            limparCampos(evt);

        } else {
            informarErro(mensagemErro);
        }
    }//GEN-LAST:event_removerSeguradora

    private void limparCampos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparCampos
        nomeTextField.setText("");
        cidadeTextField.setText("");
        coberturaPercentualTextField.setText("");
        possuiAtendimento24hButtonGroup.clearSelection();
        formaPagamentoPreferencialComboBox.setSelectedIndex(-1);
    }//GEN-LAST:event_limparCampos

    private void seguradorasCadastradasComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seguradoras_cadastradasComboBoxActionPerformed
       
    }//GEN-LAST:event_seguradoras_cadastradasComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterarSeguradora;
    private javax.swing.JLabel cidadeLabel;
    private javax.swing.JTextField cidadeTextField;
    private javax.swing.JLabel coberturaPercentualLabel;
    private javax.swing.JTextField coberturaPercentualTextField;
    private javax.swing.JPanel comandosPanel;
    private javax.swing.JButton consultarSeguradora;
    private javax.swing.JLabel formaPagamentoPreferencialLabel;
    private javax.swing.JComboBox formaPagamentoPreferencialComboBox;
    private javax.swing.JButton inserirSeguradora;
    private javax.swing.JButton limparCampos;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JPanel possuiAtendimento24hPanel;
    private javax.swing.ButtonGroup possuiAtendimento24hButtonGroup;
    private javax.swing.JRadioButton possuiAtendimento24hNaoRadioButton;
    private javax.swing.JRadioButton possuiAtendimento24hSimRadioButton;
    private javax.swing.JLabel possuiAtendimento24hLabel;
    private javax.swing.JButton removerSeguradora;
    private javax.swing.JLabel seguradorasCadastradasLabel;
    private javax.swing.JComboBox seguradorasCadastradasComboBox;
    // End of variables declaration//GEN-END:variables
}
