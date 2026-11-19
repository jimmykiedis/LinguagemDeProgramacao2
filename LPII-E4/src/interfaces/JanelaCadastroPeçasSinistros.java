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
    private Peça[] pecas_cadastradas;
    private final DefaultListModel pecas_sinistro_model = new DefaultListModel();

    public JanelaCadastroPeçasSinistros(
            ControladorCadastroPeçasSinistros controlador,
            Sinistro sinistro,
            Frame owner
    ) {
        this.controlador = controlador;
        this.sinistro = sinistro;
        pecas_cadastradas = Peça.getVisoes();
        initComponents();
        if (owner instanceof JanelaCadastroSinistros) {
            final JanelaCadastroSinistros janela_mae = (JanelaCadastroSinistros) owner;
            addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent evt) {
                    if (sinistro != null) {
                        janela_mae.atualizarListaPeçasSinistro(sinistro.getId());
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
        setAlwaysOnTop(true);

        if (owner == null) {
            setLocationByPlatform(true);
            return;
        }

        int x = owner.getX() + (owner.getWidth() - getWidth()) / 2;
        int y = owner.getY() + (owner.getHeight() - getHeight()) / 2;
        setLocation(x, y);
    }

    private void carregarSinistro() {
        if (sinistro == null) {
            sinistro_text_field.setText("Sinistro nao informado");
            return;
        }

        sinistro_text_field.setText(sinistro.toString());
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
        pecas_cadastradas = Peça.getVisoes();
        pecas_cadastradas_combo_box.setModel(
                new DefaultComboBoxModel(pecas_cadastradas)
        );
    }

    private void atualizarPeçasAssociadas() {
        pecas_sinistro_model.clear();

        if (sinistro == null) {
            pecas_sinistro_list.setModel(pecas_sinistro_model);
            return;
        }

        Peça[] pecas = Peça.buscarPeçasPorSinistro(sinistro.getId());
        sinistro.setPeças(pecas);

        for (Peça peca : pecas) {
            pecas_sinistro_model.addElement(peca);
        }

        pecas_sinistro_list.setModel(pecas_sinistro_model);
    }

    private Peça obterPeçaSelecionada() {
        return (Peça) pecas_cadastradas_combo_box.getSelectedItem();
    }

    private Peça obterPeçaAssociadaSelecionada() {
        return (Peça) pecas_sinistro_list.getSelectedValue();
    }

    private void selecionarPeçaNaLista(Peça peca) {
        if (peca == null) return;
        pecas_sinistro_list.setSelectedValue(peca, true);
    }

    private Peça localizarPeça(int codigo) {
        if (pecas_cadastradas == null) return null;
        for (Peça visao : pecas_cadastradas) {
            if (visao.getCodigo() == codigo) return visao;
        }
        return null;
    }

    private void limparCampos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparCampos
        if (pecas_cadastradas_combo_box.getItemCount() > 0) {
            pecas_cadastradas_combo_box.setSelectedIndex(0);
        } else {
            pecas_cadastradas_combo_box.setSelectedItem(null);
        }
        pecas_sinistro_list.clearSelection();
    }//GEN-LAST:event_limparCampos

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints grid_bag_constraints;

        comandos_panel = new javax.swing.JPanel();
        inserir_peça = new javax.swing.JButton();
        remover_peça = new javax.swing.JButton();
        limpar_campos = new javax.swing.JButton();
        sinistro_label = new javax.swing.JLabel();
        sinistro_text_field = new javax.swing.JTextField();
        pecas_cadastradas_label = new javax.swing.JLabel();
        pecas_cadastradas_combo_box = new javax.swing.JComboBox();
        pecas_sinistro_label = new javax.swing.JLabel();
        pecas_sinistro_scroll_pane = new javax.swing.JScrollPane();
        pecas_sinistro_list = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Peças por Sinistro");
        setMinimumSize(new java.awt.Dimension(680, 430));
        setPreferredSize(new java.awt.Dimension(760, 520));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        inserir_peça.setText("Inserir");
        inserir_peça.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirPeça(evt);
            }
        });
        comandos_panel.add(inserir_peça);

        remover_peça.setText("Remover");
        remover_peça.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerPeça(evt);
            }
        });
        comandos_panel.add(remover_peça);

        limpar_campos.setText("Limpar");
        limpar_campos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparCampos(evt);
            }
        });
        comandos_panel.add(limpar_campos);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 4;
        grid_bag_constraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(comandos_panel, grid_bag_constraints);

        sinistro_label.setText("Sinistro");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(sinistro_label, grid_bag_constraints);

        sinistro_text_field.setEditable(false);
        sinistro_text_field.setColumns(28);
        sinistro_text_field.setPreferredSize(new java.awt.Dimension(280, 20));
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 0;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(sinistro_text_field, grid_bag_constraints);

        pecas_cadastradas_label.setText("Peças Cadastradas");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 1;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.EAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(pecas_cadastradas_label, grid_bag_constraints);

        pecas_cadastradas_combo_box.setModel(new DefaultComboBoxModel(pecas_cadastradas));
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 1;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(pecas_cadastradas_combo_box, grid_bag_constraints);

        pecas_sinistro_label.setText("Peças do Sinistro");
        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 0;
        grid_bag_constraints.gridy = 2;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(pecas_sinistro_label, grid_bag_constraints);

        pecas_sinistro_list.setModel(pecas_sinistro_model);
        pecas_sinistro_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pecas_sinistro_scroll_pane.setViewportView(pecas_sinistro_list);

        grid_bag_constraints = new java.awt.GridBagConstraints();
        grid_bag_constraints.gridx = 1;
        grid_bag_constraints.gridy = 2;
        grid_bag_constraints.fill = java.awt.GridBagConstraints.BOTH;
        grid_bag_constraints.anchor = java.awt.GridBagConstraints.WEST;
        grid_bag_constraints.weightx = 1.0;
        grid_bag_constraints.weighty = 1.0;
        grid_bag_constraints.insets = new java.awt.Insets(1, 5, 10, 5);
        getContentPane().add(pecas_sinistro_scroll_pane, grid_bag_constraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inserirPeça(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirPeça
        Peça peca = obterPeçaSelecionada();
        String mensagem_erro = null;

        if (sinistro == null) {
            mensagem_erro = "Sinistro nao informado";
        } else if (peca == null) {
            mensagem_erro = "Nenhuma Peça selecionada";
        } else {
            mensagem_erro = controlador.inserirPeçasSinistros(peca, sinistro);
        }

        if (mensagem_erro == null) {
            atualizarPeçasAssociadas();
            selecionarPeçaNaLista(localizarPeça(peca.getCodigo()));
        } else {
            informarErro(mensagem_erro);
        }
    }//GEN-LAST:event_inserirPeça

    private void removerPeça(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerPeça
        Peça peca = obterPeçaAssociadaSelecionada();
        String mensagem_erro = null;

        if (sinistro == null) {
            mensagem_erro = "Sinistro nao informado";
        } else if (peca == null) {
            mensagem_erro = "Nenhuma Peça selecionada";
        } else {
            mensagem_erro = controlador.removerPeçasSinistros(peca, sinistro);
        }

        if (mensagem_erro == null) {
            atualizarPeçasAssociadas();
            pecas_sinistro_list.clearSelection();
        } else {
            informarErro(mensagem_erro);
        }
    }//GEN-LAST:event_removerPeça

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel comandos_panel;
    private javax.swing.JButton inserir_peça;
    private javax.swing.JButton limpar_campos;
    private javax.swing.JComboBox pecas_cadastradas_combo_box;
    private javax.swing.JLabel pecas_cadastradas_label;
    private javax.swing.JLabel pecas_sinistro_label;
    private javax.swing.JList pecas_sinistro_list;
    private javax.swing.JScrollPane pecas_sinistro_scroll_pane;
    private javax.swing.JButton remover_peça;
    private javax.swing.JLabel sinistro_label;
    private javax.swing.JTextField sinistro_text_field;
    // End of variables declaration//GEN-END:variables
}
