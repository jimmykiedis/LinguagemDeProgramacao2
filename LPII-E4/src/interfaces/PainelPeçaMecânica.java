package interfaces;

import entidades.PeçaMecânica;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelPeçaMecânica extends JPanel {
    public PainelPeçaMecânica() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        categoria_label = new JLabel("Categoria mecânica");
        prazo_garantia_label = new JLabel("Prazo de garantia (dias)");
        mecanico_proprio_label = new JLabel("Mecânico próprio");
        categoria_combo_box = new JComboBox<>(PeçaMecânica.CategoriaMecânica.values());
        prazo_garantia_text_field = new JTextField(10);
        mecanico_proprio_check_box = new JCheckBox("Sim");

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

        GridBagConstraints prazo_garantia_label_constraints = new GridBagConstraints();
        prazo_garantia_label_constraints.gridx = 0;
        prazo_garantia_label_constraints.gridy = 1;
        prazo_garantia_label_constraints.insets = new Insets(1, 5, 10, 5);
        prazo_garantia_label_constraints.anchor = GridBagConstraints.WEST;
        add(prazo_garantia_label, prazo_garantia_label_constraints);

        GridBagConstraints prazo_garantia_text_field_constraints = new GridBagConstraints();
        prazo_garantia_text_field_constraints.gridx = 1;
        prazo_garantia_text_field_constraints.gridy = 1;
        prazo_garantia_text_field_constraints.insets = new Insets(1, 5, 10, 5);
        prazo_garantia_text_field_constraints.anchor = GridBagConstraints.WEST;
        add(prazo_garantia_text_field, prazo_garantia_text_field_constraints);

        GridBagConstraints mecanico_proprio_label_constraints = new GridBagConstraints();
        mecanico_proprio_label_constraints.gridx = 0;
        mecanico_proprio_label_constraints.gridy = 2;
        mecanico_proprio_label_constraints.insets = new Insets(1, 5, 10, 5);
        mecanico_proprio_label_constraints.anchor = GridBagConstraints.WEST;
        add(mecanico_proprio_label, mecanico_proprio_label_constraints);

        GridBagConstraints mecanico_proprio_check_box_constraints = new GridBagConstraints();
        mecanico_proprio_check_box_constraints.gridx = 1;
        mecanico_proprio_check_box_constraints.gridy = 2;
        mecanico_proprio_check_box_constraints.insets = new Insets(1, 5, 10, 5);
        mecanico_proprio_check_box_constraints.anchor = GridBagConstraints.WEST;
        add(mecanico_proprio_check_box, mecanico_proprio_check_box_constraints);
    }// </editor-fold>//GEN-END:initComponents

    public PeçaMecânica.CategoriaMecânica getCategoriaMecânica() {
        return (PeçaMecânica.CategoriaMecânica) categoria_combo_box.getSelectedItem();
    }
    public Integer getPrazoGarantia() {
        String valor = prazo_garantia_text_field.getText().trim();
        return valor.isEmpty() ? null : Integer.valueOf(valor);
    }
    public boolean isMecanicoProprio() { return mecanico_proprio_check_box.isSelected(); }
    public void setCategoriaMecânica(PeçaMecânica.CategoriaMecânica valor) { categoria_combo_box.setSelectedItem(valor); }
    public void setPrazoGarantia(Integer valor) { prazo_garantia_text_field.setText(valor == null ? "" : valor.toString()); }
    public void setMecanicoProprio(boolean valor) { mecanico_proprio_check_box.setSelected(valor); }
    public void limparCampos() { categoria_combo_box.setSelectedIndex(-1); setPrazoGarantia(null); setMecanicoProprio(false); }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox<PeçaMecânica.CategoriaMecânica> categoria_combo_box;
    private JTextField prazo_garantia_text_field;
    private JCheckBox mecanico_proprio_check_box;
    private JLabel categoria_label, prazo_garantia_label, mecanico_proprio_label;
    // End of variables declaration//GEN-END:variables
}
