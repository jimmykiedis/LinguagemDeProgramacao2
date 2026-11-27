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
        mecânico_próprio_label = new JLabel("Mecânico próprio");
        categoria_combo_box = new JComboBox<>(PeçaMecânica.CategoriaMecânica.values());
        prazo_garantia_text_field = new JTextField(10);
        mecânico_próprio_check_box = new JCheckBox("Sim");

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
        prazo_garantia_text_field_constraints.fill = GridBagConstraints.HORIZONTAL;
        prazo_garantia_text_field_constraints.weightx = 1.0;
        add(prazo_garantia_text_field, prazo_garantia_text_field_constraints);

        GridBagConstraints mecânico_próprio_label_constraints = new GridBagConstraints();
        mecânico_próprio_label_constraints.gridx = 0;
        mecânico_próprio_label_constraints.gridy = 2;
        mecânico_próprio_label_constraints.insets = new Insets(1, 5, 10, 5);
        mecânico_próprio_label_constraints.anchor = GridBagConstraints.WEST;
        add(mecânico_próprio_label, mecânico_próprio_label_constraints);

        GridBagConstraints mecânico_próprio_check_box_constraints = new GridBagConstraints();
        mecânico_próprio_check_box_constraints.gridx = 1;
        mecânico_próprio_check_box_constraints.gridy = 2;
        mecânico_próprio_check_box_constraints.insets = new Insets(1, 5, 10, 5);
        mecânico_próprio_check_box_constraints.anchor = GridBagConstraints.WEST;
        add(mecânico_próprio_check_box, mecânico_próprio_check_box_constraints);
    }// </editor-fold>//GEN-END:initComponents

    public PeçaMecânica.CategoriaMecânica getCategoriaMecânica() {
        return (PeçaMecânica.CategoriaMecânica) categoria_combo_box.getSelectedItem();
    }
    public int getPrazoGarantia() {
        String valor = prazo_garantia_text_field.getText().trim();
        if (valor.isEmpty()) return -1;
        int prazo = Integer.parseInt(valor);
        if (prazo < 0) throw new IllegalArgumentException("O prazo de garantia não pode ser negativo.");
        return prazo;
    }
    public boolean isMecânicoPróprio() { return mecânico_próprio_check_box.isSelected(); }
    public void setCategoriaMecânica(PeçaMecânica.CategoriaMecânica valor) { categoria_combo_box.setSelectedItem(valor); }
    public void setPrazoGarantia(int valor) { prazo_garantia_text_field.setText(valor + ""); }
    public void setPrazoGarantia(Integer valor) {
        if (valor == null) prazo_garantia_text_field.setText("");
        else setPrazoGarantia(valor.intValue());
    }
    public void setMecânicoPróprio(boolean valor) { mecânico_próprio_check_box.setSelected(valor); }
    public void limparCampos() { categoria_combo_box.setSelectedIndex(-1); setPrazoGarantia(null); setMecânicoPróprio(false); }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox<PeçaMecânica.CategoriaMecânica> categoria_combo_box;
    private JTextField prazo_garantia_text_field;
    private JCheckBox mecânico_próprio_check_box;
    private JLabel categoria_label, prazo_garantia_label, mecânico_próprio_label;
    // End of variables declaration//GEN-END:variables
}
