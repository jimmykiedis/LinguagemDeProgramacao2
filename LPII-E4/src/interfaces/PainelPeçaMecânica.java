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
        categoriaLabel = new JLabel("Categoria mecânica");
        prazoGarantiaLabel = new JLabel("Prazo de garantia (dias)");
        mecanicoProprioLabel = new JLabel("Mecânico próprio");
        categoriaComboBox = new JComboBox<>(PeçaMecânica.CategoriaMecânica.values());
        prazoGarantiaTextField = new JTextField(10);
        mecanicoProprioCheckBox = new JCheckBox("Sim");

        setLayout(new GridBagLayout());

        GridBagConstraints categoriaLabelConstraints = new GridBagConstraints();
        categoriaLabelConstraints.gridx = 0;
        categoriaLabelConstraints.gridy = 0;
        categoriaLabelConstraints.insets = new Insets(1, 5, 10, 5);
        categoriaLabelConstraints.anchor = GridBagConstraints.WEST;
        add(categoriaLabel, categoriaLabelConstraints);

        GridBagConstraints categoriaComboBoxConstraints = new GridBagConstraints();
        categoriaComboBoxConstraints.gridx = 1;
        categoriaComboBoxConstraints.gridy = 0;
        categoriaComboBoxConstraints.insets = new Insets(1, 5, 10, 5);
        categoriaComboBoxConstraints.anchor = GridBagConstraints.WEST;
        add(categoriaComboBox, categoriaComboBoxConstraints);

        GridBagConstraints prazoGarantiaLabelConstraints = new GridBagConstraints();
        prazoGarantiaLabelConstraints.gridx = 0;
        prazoGarantiaLabelConstraints.gridy = 1;
        prazoGarantiaLabelConstraints.insets = new Insets(1, 5, 10, 5);
        prazoGarantiaLabelConstraints.anchor = GridBagConstraints.WEST;
        add(prazoGarantiaLabel, prazoGarantiaLabelConstraints);

        GridBagConstraints prazoGarantiaTextFieldConstraints = new GridBagConstraints();
        prazoGarantiaTextFieldConstraints.gridx = 1;
        prazoGarantiaTextFieldConstraints.gridy = 1;
        prazoGarantiaTextFieldConstraints.insets = new Insets(1, 5, 10, 5);
        prazoGarantiaTextFieldConstraints.anchor = GridBagConstraints.WEST;
        add(prazoGarantiaTextField, prazoGarantiaTextFieldConstraints);

        GridBagConstraints mecanicoProprioLabelConstraints = new GridBagConstraints();
        mecanicoProprioLabelConstraints.gridx = 0;
        mecanicoProprioLabelConstraints.gridy = 2;
        mecanicoProprioLabelConstraints.insets = new Insets(1, 5, 10, 5);
        mecanicoProprioLabelConstraints.anchor = GridBagConstraints.WEST;
        add(mecanicoProprioLabel, mecanicoProprioLabelConstraints);

        GridBagConstraints mecanicoProprioCheckBoxConstraints = new GridBagConstraints();
        mecanicoProprioCheckBoxConstraints.gridx = 1;
        mecanicoProprioCheckBoxConstraints.gridy = 2;
        mecanicoProprioCheckBoxConstraints.insets = new Insets(1, 5, 10, 5);
        mecanicoProprioCheckBoxConstraints.anchor = GridBagConstraints.WEST;
        add(mecanicoProprioCheckBox, mecanicoProprioCheckBoxConstraints);
    }// </editor-fold>//GEN-END:initComponents

    public PeçaMecânica.CategoriaMecânica getCategoriaMecânica() {
        return (PeçaMecânica.CategoriaMecânica) categoriaComboBox.getSelectedItem();
    }
    public Integer getPrazoGarantia() {
        String valor = prazoGarantiaTextField.getText().trim();
        return valor.isEmpty() ? null : Integer.valueOf(valor);
    }
    public boolean isMecanicoProprio() { return mecanicoProprioCheckBox.isSelected(); }
    public void setCategoriaMecânica(PeçaMecânica.CategoriaMecânica valor) { categoriaComboBox.setSelectedItem(valor); }
    public void setPrazoGarantia(Integer valor) { prazoGarantiaTextField.setText(valor == null ? "" : valor.toString()); }
    public void setMecanicoProprio(boolean valor) { mecanicoProprioCheckBox.setSelected(valor); }
    public void limparCampos() { categoriaComboBox.setSelectedIndex(-1); setPrazoGarantia(null); setMecanicoProprio(false); }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox<PeçaMecânica.CategoriaMecânica> categoriaComboBox;
    private JTextField prazoGarantiaTextField;
    private JCheckBox mecanicoProprioCheckBox;
    private JLabel categoriaLabel, prazoGarantiaLabel, mecanicoProprioLabel;
    // End of variables declaration//GEN-END:variables
}
