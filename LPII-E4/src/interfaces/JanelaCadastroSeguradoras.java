/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import interfaces.JanelaCadastroSeguradoras;
import controles.ControladorCadastroSeguradoras;
import entidades.Seguradora;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
/**
 *
 * @author jimmy
 */
public class JanelaCadastroSeguradoras extends javax.swing.JFrame {

    /**
     * Creates new form JanelaCadastrarSeguradora
     */

    public static Seguradora.FormaPagamentoPreferencial
                converteStringParaFormaPagamentoPreferencial(String forma_pagamento_str) {
        if (forma_pagamento_str == null) return null;

        switch (forma_pagamento_str.trim()) {
            case "boleto":
                return Seguradora.FormaPagamentoPreferencial.boleto;
            case "debito_automatico":
                return Seguradora.FormaPagamentoPreferencial.debito_automatico;
            case "pix":
                return Seguradora.FormaPagamentoPreferencial.pix;
            case "cartao":
                return Seguradora.FormaPagamentoPreferencial.cartao;
            default:
                return null;
        }
    }

    ControladorCadastroSeguradoras controlador;
    Seguradora[] seguradoras_cadastradas;

    public JanelaCadastroSeguradoras(ControladorCadastroSeguradoras controlador) {
        this.controlador = controlador;
        seguradoras_cadastradas = Seguradora.getVisoes();
        initComponents();
        limparCampos(null);
    }

    
    private void inserirSeguradora(java.awt.event.ActionEvent evt) {
        Seguradora seguradora = obterSeguradoraInformada();
        String mensagem_erro = null;

        if (seguradora != null)
            mensagem_erro = controlador.inserirSeguradora(seguradora);
        else
            mensagem_erro = "Algum atributo da Seguradora não foi informado";

        if (mensagem_erro == null) {
            Seguradora visao = seguradora.getVisao();
            seguradoras_cadastradasComboBox.addItem(visao);
            seguradoras_cadastradasComboBox.setSelectedItem(visao);
        } else {
            informarErro(mensagem_erro);
        }
    }

    private Seguradora obterSeguradoraInformada() {
        String nome = nomeTextField.getText();
        if (nome.isEmpty()) return null;

        String cidade = cidadeTextField.getText();
        if (cidade.isEmpty()) cidade = null;

        String cobertura_str = coberturaPercentualTextField.getText();
        if (cobertura_str.isEmpty()) return null;

        double cobertura_percentual;
        try {
            cobertura_percentual = Double.parseDouble(cobertura_str);
        } catch (NumberFormatException e) {
            return null;
        }

        Seguradora.FormaPagamentoPreferencial forma_pagamento_preferencial =
                (Seguradora.FormaPagamentoPreferencial)
                        pagamentoComboBox.getSelectedItem();
        if (forma_pagamento_preferencial == null) return null;

        if (atendimentoButtonGroup.getSelection() == null) return null;
        boolean possui_atendimento_24h = simRadioButton.isSelected();

        return new Seguradora(
            nome,
            cidade,
            cobertura_percentual,
            possui_atendimento_24h,
            forma_pagamento_preferencial
        );
    }

    private void informarErro (String mensagem) {
        JOptionPane.showMessageDialog (this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void consultarSeguradora(java.awt.event.ActionEvent evt) {
        Seguradora visao =
                (Seguradora) seguradoras_cadastradasComboBox.getSelectedItem();
        Seguradora seguradora = null;
        String mensagem_erro = null;

        if (visao != null) {
            seguradora = Seguradora.buscarSeguradora(visao.getNome());
            if (seguradora == null)
                mensagem_erro = "Seguradora não cadastrada";
        } else {
            mensagem_erro = "Nenhuma seguradora selecionada";
        }

        if (mensagem_erro == null) {
            nomeTextField.setText(seguradora.getNome());

            String cidade = seguradora.getCidade();
            if (cidade == null) cidade = "";
            cidadeTextField.setText(cidade);

            coberturaPercentualTextField.setText(
                    Double.toString(seguradora.getCoberturaPercentual()));

            pagamentoComboBox.setSelectedItem(
                    seguradora.getFormaPagamentoPreferencial());

            if (seguradora.getPossuiAtendimento24h())
                simRadioButton.setSelected(true);
            else
                nãoRadioButton.setSelected(true);
        } else {
            informarErro(mensagem_erro);
        }
    
    }

    private void alterarSeguradora(java.awt.event.ActionEvent evt) {
        Seguradora seguradora = obterSeguradoraInformada();
        String mensagem_erro = null;
        if (seguradora != null) mensagem_erro = controlador.alterarSeguradora(seguradora);
        else mensagem_erro = "Algum atributo da Seguradora não foi informado";
        if (mensagem_erro == null) {
            Seguradora visao = getVisaoAlterada(seguradora.getNome());
            if (visao != null) {
                visao.setCidade(seguradora.getCidade());
                seguradoras_cadastradasComboBox.updateUI();
                seguradoras_cadastradasComboBox.setSelectedItem(visao);
            }
        } else informarErro(mensagem_erro);
    }
    
    private Seguradora getVisaoAlterada(String nome) {
        for (Seguradora visao : seguradoras_cadastradas) {
            if (visao.getNome().equals(nome)) return visao;
        }
        return null;
    }
    
    private void limparCampos(java.awt.event.ActionEvent evt) {
        nomeTextField.setText("");
        cidadeTextField.setText("");
        coberturaPercentualTextField.setText("");
        pagamentoComboBox.setSelectedIndex(-1);
        atendimentoButtonGroup.clearSelection();

        for (java.awt.Component componente : atendimentoPanel.getComponents()) {
            if (componente instanceof javax.swing.AbstractButton) {
                ((javax.swing.AbstractButton) componente).setSelected(false);
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        atendimentoButtonGroup = new javax.swing.ButtonGroup();
        comandosPanel = new javax.swing.JPanel();
        inserirButton = new javax.swing.JButton();
        consultarButton = new javax.swing.JButton();
        alterarButton = new javax.swing.JButton();
        removerButton = new javax.swing.JButton();
        limparButton = new javax.swing.JButton();
        seguradorasLabel = new javax.swing.JLabel();
        seguradoras_cadastradasComboBox = new javax.swing.JComboBox();
        nomeLabel = new javax.swing.JLabel();
        cidadeLabel = new javax.swing.JLabel();
        coberturaLabel = new javax.swing.JLabel();
        atendimentoLabel = new javax.swing.JLabel();
        pagamentoLabel = new javax.swing.JLabel();
        nomeTextField = new javax.swing.JTextField();
        cidadeTextField = new javax.swing.JTextField();
        coberturaPercentualTextField = new javax.swing.JTextField();
        pagamentoComboBox = new javax.swing.JComboBox();
        atendimentoPanel = new javax.swing.JPanel();
        simRadioButton = new javax.swing.JRadioButton();
        nãoRadioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Seguradoras");
        setPreferredSize(new java.awt.Dimension(645, 440));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        inserirButton.setText("Inserir");
        comandosPanel.add(inserirButton);

        consultarButton.setText("Consultar");
        comandosPanel.add(consultarButton);

        alterarButton.setText("Alterar");
        comandosPanel.add(alterarButton);

        removerButton.setText("Remover");
        comandosPanel.add(removerButton);

        limparButton.setText("Limpar");
        comandosPanel.add(limparButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(67, 173, 29, 166);
        getContentPane().add(comandosPanel, gridBagConstraints);

        seguradorasLabel.setText("Seguradoras Cadastradas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(56, 93, 0, 0);
        getContentPane().add(seguradorasLabel, gridBagConstraints);

        seguradoras_cadastradasComboBox.setModel(new DefaultComboBoxModel (seguradoras_cadastradas));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(53, 19, 0, 1);
        getContentPane().add(seguradoras_cadastradasComboBox, gridBagConstraints);

        nomeLabel.setText("Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(14, 192, 0, 0);
        getContentPane().add(nomeLabel, gridBagConstraints);

        cidadeLabel.setText("Cidade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(8, 188, 0, 0);
        getContentPane().add(cidadeLabel, gridBagConstraints);

        coberturaLabel.setText("Cobertura Percentual");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(8, 113, 0, 0);
        getContentPane().add(coberturaLabel, gridBagConstraints);

        atendimentoLabel.setText("Atendimento 24h");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(12, 133, 0, 0);
        getContentPane().add(atendimentoLabel, gridBagConstraints);

        pagamentoLabel.setText("Pagamento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(15, 164, 0, 0);
        getContentPane().add(pagamentoLabel, gridBagConstraints);

        nomeTextField.setColumns(50);
        nomeTextField.setPreferredSize(new java.awt.Dimension(456, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 225;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 18, 0, 0);
        getContentPane().add(nomeTextField, gridBagConstraints);

        cidadeTextField.setColumns(50);
        cidadeTextField.setPreferredSize(new java.awt.Dimension(456, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 225;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 0, 0);
        getContentPane().add(cidadeTextField, gridBagConstraints);

        coberturaPercentualTextField.setColumns(50);
        coberturaPercentualTextField.setPreferredSize(new java.awt.Dimension(456, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 225;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 0, 0);
        getContentPane().add(coberturaPercentualTextField, gridBagConstraints);

        pagamentoComboBox.setModel(new DefaultComboBoxModel(Seguradora.FormaPagamentoPreferencial.values()));
        pagamentoComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagamentoComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 18, 0, 0);
        getContentPane().add(pagamentoComboBox, gridBagConstraints);

        atendimentoButtonGroup.add(simRadioButton);
        simRadioButton.setText("Sim");
        atendimentoPanel.add(simRadioButton);

        atendimentoButtonGroup.add(nãoRadioButton);
        nãoRadioButton.setMnemonic('\u0001');
        nãoRadioButton.setText("Não");
        atendimentoPanel.add(nãoRadioButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 18, 0, 0);
        getContentPane().add(atendimentoPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pagamentoComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagamentoComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pagamentoComboBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public void run() {
        new JanelaSistema().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterarButton;
    private javax.swing.ButtonGroup atendimentoButtonGroup;
    private javax.swing.JLabel atendimentoLabel;
    private javax.swing.JPanel atendimentoPanel;
    private javax.swing.JLabel cidadeLabel;
    private javax.swing.JTextField cidadeTextField;
    private javax.swing.JLabel coberturaLabel;
    private javax.swing.JTextField coberturaPercentualTextField;
    private javax.swing.JPanel comandosPanel;
    private javax.swing.JButton consultarButton;
    private javax.swing.JButton inserirButton;
    private javax.swing.JButton limparButton;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JRadioButton nãoRadioButton;
    private javax.swing.JComboBox pagamentoComboBox;
    private javax.swing.JLabel pagamentoLabel;
    private javax.swing.JButton removerButton;
    private javax.swing.JLabel seguradorasLabel;
    private javax.swing.JComboBox seguradoras_cadastradasComboBox;
    private javax.swing.JRadioButton simRadioButton;
    // End of variables declaration//GEN-END:variables
}
