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
    private final JComboBox<PeçaMecânica.CategoriaMecânica> categoriaComboBox =
            new JComboBox<>(PeçaMecânica.CategoriaMecânica.values());
    private final JTextField prazoGarantiaTextField = new JTextField(10);
    private final JCheckBox mecanicoProprioCheckBox = new JCheckBox("Sim");

    public PainelPeçaMecânica() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(1, 5, 10, 5);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0; c.gridy = 0; add(new JLabel("Categoria mecânica"), c);
        c.gridx = 1; add(categoriaComboBox, c);
        c.gridx = 0; c.gridy = 1; add(new JLabel("Prazo de garantia (dias)"), c);
        c.gridx = 1; add(prazoGarantiaTextField, c);
        c.gridx = 0; c.gridy = 2; add(new JLabel("Mecânico próprio"), c);
        c.gridx = 1; add(mecanicoProprioCheckBox, c);
    }

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
}
