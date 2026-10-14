package interfaces;

import entidades.PeçaAcessórios;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelPeçaAcessórios extends JPanel {
    private final JComboBox<PeçaAcessórios.CategoriaAcessório> categoriaComboBox =
            new JComboBox<>(PeçaAcessórios.CategoriaAcessório.values());
    private final JTextField corTextField = new JTextField(10);
    private final JCheckBox funileiroProprioCheckBox = new JCheckBox("Sim");

    public PainelPeçaAcessórios() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(1, 5, 10, 5);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0; c.gridy = 0; add(new JLabel("Categoria do acessório"), c);
        c.gridx = 1; add(categoriaComboBox, c);
        c.gridx = 0; c.gridy = 1; add(new JLabel("Cor"), c);
        c.gridx = 1; add(corTextField, c);
        c.gridx = 0; c.gridy = 2; add(new JLabel("Funileiro próprio"), c);
        c.gridx = 1; add(funileiroProprioCheckBox, c);
    }

    public PeçaAcessórios.CategoriaAcessório getCategoriaAcessório() {
        return (PeçaAcessórios.CategoriaAcessório) categoriaComboBox.getSelectedItem();
    }
    public String getCor() { return corTextField.getText().trim(); }
    public boolean isFunileiroProprio() { return funileiroProprioCheckBox.isSelected(); }
    public void setCategoriaAcessório(PeçaAcessórios.CategoriaAcessório valor) { categoriaComboBox.setSelectedItem(valor); }
    public void setCor(String valor) { corTextField.setText(valor == null ? "" : valor); }
    public void setFunileiroProprio(boolean valor) { funileiroProprioCheckBox.setSelected(valor); }
    public void limparCampos() { categoriaComboBox.setSelectedIndex(-1); setCor(null); setFunileiroProprio(false); }
}
