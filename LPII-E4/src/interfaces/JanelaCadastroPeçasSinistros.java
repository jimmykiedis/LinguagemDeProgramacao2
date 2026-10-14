package interfaces;

import controles.ControladorCadastroPeçasSinistros;
import entidades.Peça;
import entidades.Sinistro;
import java.awt.Frame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class JanelaCadastroPeçasSinistros extends javax.swing.JFrame {

    private final ControladorCadastroPeçasSinistros controlador;
    private Sinistro sinistro;
    private Peça[] pecasCadastradas;
    private final DefaultListModel pecasSinistroModel = new DefaultListModel();

    public JanelaCadastroPeçasSinistros(
            ControladorCadastroPeçasSinistros controlador,
            Sinistro sinistro,
            Frame owner
    ) {
        this.controlador = controlador;
        this.sinistro = sinistro;
        pecasCadastradas = Peça.getVisoes();
        initComponents();
        if (owner instanceof JanelaCadastroSinistros) {
            final JanelaCadastroSinistros janelaMae = (JanelaCadastroSinistros) owner;
            addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent evt) {
                    if (sinistro != null) {
                        janelaMae.atualizarListaPeçasSinistro(sinistro.getId());
                    }
                }
            });
        }
        carregarSinistro();
        atualizarPeçasCadastradas();
        atualizarPeçasAssociadas();
        setSize(new java.awt.Dimension(760, 520));
        configurarJanelaDependente(owner);
        limparCampos(null);
    }

    private void configurarJanelaDependente(Frame owner) {
        setAutoRequestFocus(true);
        JanelaUtil.posicionarNoCanto(this);
    }

    private void carregarSinistro() {
        if (sinistro == null) {
            sinistroTextField.setText("Sinistro nao informado");
            return;
        }

        sinistroTextField.setText(sinistro.toString());
    }

    private void informarErro(String mensagem) {
        JOptionPane.showMessageDialog(
                this,
                mensagem,
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void atualizarPeçasCadastradas() {
        pecasCadastradas = Peça.getVisoes();
        pecasCadastradasComboBox.setModel(
                new DefaultComboBoxModel(pecasCadastradas)
        );
    }

    private void atualizarPeçasAssociadas() {
        pecasSinistroModel.clear();

        if (sinistro == null) {
            pecasSinistroList.setModel(pecasSinistroModel);
            return;
        }

        Peça[] pecas = Peça.buscarPeçasPorSinistro(sinistro.getId());
        sinistro.setPeças(pecas);

        for (Peça peca : pecas) {
            pecasSinistroModel.addElement(peca);
        }

        pecasSinistroList.setModel(pecasSinistroModel);
    }

    private Peça obterPeçaSelecionada() {
        return (Peça) pecasCadastradasComboBox.getSelectedItem();
    }

    private Peça obterPeçaAssociadaSelecionada() {
        return (Peça) pecasSinistroList.getSelectedValue();
    }

    private void selecionarPeçaNaLista(Peça peca) {
        if (peca == null) return;
        pecasSinistroList.setSelectedValue(peca, true);
    }

    private Peça localizarPeça(int codigo) {
        if (pecasCadastradas == null) return null;
        for (Peça visao : pecasCadastradas) {
            if (visao.getCodigo() == codigo) return visao;
        }
        return null;
    }

    private void limparCampos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparCampos
        if (pecasCadastradasComboBox.getItemCount() > 0) {
            pecasCadastradasComboBox.setSelectedIndex(0);
        } else {
            pecasCadastradasComboBox.setSelectedItem(null);
        }
        pecasSinistroList.clearSelection();
    }//GEN-LAST:event_limparCampos

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        comandosPanel = new javax.swing.JPanel();
        inserirPeça = new javax.swing.JButton();
        removerPeça = new javax.swing.JButton();
        limparCampos = new javax.swing.JButton();
        sinistroLabel = new javax.swing.JLabel();
        sinistroTextField = new javax.swing.JTextField();
        pecasCadastradasLabel = new javax.swing.JLabel();
        pecasCadastradasComboBox = new javax.swing.JComboBox();
        pecasSinistroLabel = new javax.swing.JLabel();
        pecasSinistroScrollPane = new javax.swing.JScrollPane();
        pecasSinistroList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Peça por Sinistro");
        setMinimumSize(new java.awt.Dimension(680, 430));
        setPreferredSize(new java.awt.Dimension(760, 520));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        inserirPeça.setText("Inserir");
        inserirPeça.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirPeça(evt);
            }
        });
        comandosPanel.add(inserirPeça);

        removerPeça.setText("Remover");
        removerPeça.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerPeça(evt);
            }
        });
        comandosPanel.add(removerPeça);

        limparCampos.setText("Limpar");
        limparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparCampos(evt);
            }
        });
        comandosPanel.add(limparCampos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(comandosPanel, gridBagConstraints);

        sinistroLabel.setText("Sinistro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(sinistroLabel, gridBagConstraints);

        sinistroTextField.setEditable(false);
        sinistroTextField.setColumns(28);
        sinistroTextField.setPreferredSize(new java.awt.Dimension(280, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(sinistroTextField, gridBagConstraints);

        pecasCadastradasLabel.setText("Peças Cadastradas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(pecasCadastradasLabel, gridBagConstraints);

        pecasCadastradasComboBox.setModel(new DefaultComboBoxModel(pecasCadastradas));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(pecasCadastradasComboBox, gridBagConstraints);

        pecasSinistroLabel.setText("Peças do Sinistro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(pecasSinistroLabel, gridBagConstraints);

        pecasSinistroList.setModel(pecasSinistroModel);
        pecasSinistroList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pecasSinistroScrollPane.setViewportView(pecasSinistroList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(pecasSinistroScrollPane, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inserirPeça(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirPeça
        Peça peca = obterPeçaSelecionada();
        String mensagemErro = null;

        if (sinistro == null) {
            mensagemErro = "Sinistro nao informado";
        } else if (peca == null) {
            mensagemErro = "Nenhuma Peça selecionada";
        } else {
            mensagemErro = controlador.inserirPeçasSinistros(peca, sinistro);
        }

        if (mensagemErro == null) {
            atualizarPeçasAssociadas();
            selecionarPeçaNaLista(localizarPeça(peca.getCodigo()));
        } else {
            informarErro(mensagemErro);
        }
    }//GEN-LAST:event_inserirPeça

    private void removerPeça(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerPeça
        Peça peca = obterPeçaAssociadaSelecionada();
        String mensagemErro = null;

        if (sinistro == null) {
            mensagemErro = "Sinistro nao informado";
        } else if (peca == null) {
            mensagemErro = "Nenhuma Peça selecionada";
        } else {
            mensagemErro = controlador.removerPeçasSinistros(peca, sinistro);
        }

        if (mensagemErro == null) {
            atualizarPeçasAssociadas();
            pecasSinistroList.clearSelection();
        } else {
            informarErro(mensagemErro);
        }
    }//GEN-LAST:event_removerPeça

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel comandosPanel;
    private javax.swing.JButton inserirPeça;
    private javax.swing.JButton limparCampos;
    private javax.swing.JLabel pecasCadastradasLabel;
    private javax.swing.JLabel pecasSinistroLabel;
    private javax.swing.JList pecasSinistroList;
    private javax.swing.JScrollPane pecasSinistroScrollPane;
    private javax.swing.JComboBox pecasCadastradasComboBox;
    private javax.swing.JButton removerPeça;
    private javax.swing.JLabel sinistroLabel;
    private javax.swing.JTextField sinistroTextField;
    // End of variables declaration//GEN-END:variables
}
