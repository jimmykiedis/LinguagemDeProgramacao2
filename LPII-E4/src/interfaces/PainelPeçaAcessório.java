package interfaces;

import entidades.PeçaAcessório;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelPeçaAcessório extends JPanel {
    public PainelPeçaAcessório() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        categoria_label = new JLabel("Categoria do acessório");
        cor_label = new JLabel("Cor");
        funileiro_proprio_label = new JLabel("Funileiro próprio");
        categoria_combo_box = new JComboBox<>(PeçaAcessório.CategoriaAcessório.values());
        cor_text_field = new JTextField(10);
        funileiro_proprio_check_box = new JCheckBox("Sim");

        setLayout(new GridBagLayout());

        GridBagConstraints categoria_label_constraints = new GridBagConstraints();
        categoria_label_constraints.gridx = 0;
        categoria_label_constraints.gridy = 0;
        categoria_label_constraints.insets = new Insets(1, 5, 10, 5);
        categoria_label_constraints.anchor = GridBagConstraints.WEST;
        add(categoria_label, categoria_label_constraints);

        GridBagConstraints categoria_combo_box_constraints = new GridBagConstraints();
        categoria_combo_box_constraints.gridx = 1;
        categoria_combo_box_constraints.gridy = 0;
        categoria_combo_box_constraints.insets = new Insets(1, 5, 10, 5);
        categoria_combo_box_constraints.anchor = GridBagConstraints.WEST;
        add(categoria_combo_box, categoria_combo_box_constraints);

        GridBagConstraints cor_label_constraints = new GridBagConstraints();
        cor_label_constraints.gridx = 0;
        cor_label_constraints.gridy = 1;
        cor_label_constraints.insets = new Insets(1, 5, 10, 5);
        cor_label_constraints.anchor = GridBagConstraints.WEST;
        add(cor_label, cor_label_constraints);

        GridBagConstraints cor_text_field_constraints = new GridBagConstraints();
        cor_text_field_constraints.gridx = 1;
        cor_text_field_constraints.gridy = 1;
        cor_text_field_constraints.insets = new Insets(1, 5, 10, 5);
        cor_text_field_constraints.anchor = GridBagConstraints.WEST;
        add(cor_text_field, cor_text_field_constraints);

        GridBagConstraints funileiro_proprio_label_constraints = new GridBagConstraints();
        funileiro_proprio_label_constraints.gridx = 0;
        funileiro_proprio_label_constraints.gridy = 2;
        funileiro_proprio_label_constraints.insets = new Insets(1, 5, 10, 5);
        funileiro_proprio_label_constraints.anchor = GridBagConstraints.WEST;
        add(funileiro_proprio_label, funileiro_proprio_label_constraints);

        GridBagConstraints funileiro_proprio_check_box_constraints = new GridBagConstraints();
        funileiro_proprio_check_box_constraints.gridx = 1;
        funileiro_proprio_check_box_constraints.gridy = 2;
        funileiro_proprio_check_box_constraints.insets = new Insets(1, 5, 10, 5);
        funileiro_proprio_check_box_constraints.anchor = GridBagConstraints.WEST;
        add(funileiro_proprio_check_box, funileiro_proprio_check_box_constraints);
    }// </editor-fold>//GEN-END:initComponents

    public PeçaAcessório.CategoriaAcessório getCategoriaAcessório() {
        return (PeçaAcessório.CategoriaAcessório) categoria_combo_box.getSelectedItem();
    }
    public String getCor() { return cor_text_field.getText().trim(); }
    public boolean isFunileiroProprio() { return funileiro_proprio_check_box.isSelected(); }
    public void setCategoriaAcessório(PeçaAcessório.CategoriaAcessório valor) { categoria_combo_box.setSelectedItem(valor); }
    public void setCor(String valor) { cor_text_field.setText(valor == null ? "" : valor); }
    public void setFunileiroProprio(boolean valor) { funileiro_proprio_check_box.setSelected(valor); }
    public void limparCampos() { categoria_combo_box.setSelectedIndex(-1); setCor(null); setFunileiroProprio(false); }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox<PeçaAcessório.CategoriaAcessório> categoria_combo_box;
    private JTextField cor_text_field;
    private JCheckBox funileiro_proprio_check_box;
    private JLabel categoria_label, cor_label, funileiro_proprio_label;
    // End of variables declaration//GEN-END:variables
}
