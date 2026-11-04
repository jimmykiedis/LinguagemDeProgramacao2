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

public class PainelPeçaAcessórios extends JPanel {
    public PainelPeçaAcessórios() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        categoriaLabel = new JLabel("Categoria do acessório");
        corLabel = new JLabel("Cor");
        funileiroProprioLabel = new JLabel("Funileiro próprio");
        categoriaComboBox = new JComboBox<>(PeçaAcessório.CategoriaAcessório.values());
        corTextField = new JTextField(10);
        funileiroProprioCheckBox = new JCheckBox("Sim");

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

        GridBagConstraints corLabelConstraints = new GridBagConstraints();
        corLabelConstraints.gridx = 0;
        corLabelConstraints.gridy = 1;
        corLabelConstraints.insets = new Insets(1, 5, 10, 5);
        corLabelConstraints.anchor = GridBagConstraints.WEST;
        add(corLabel, corLabelConstraints);

        GridBagConstraints corTextFieldConstraints = new GridBagConstraints();
        corTextFieldConstraints.gridx = 1;
        corTextFieldConstraints.gridy = 1;
        corTextFieldConstraints.insets = new Insets(1, 5, 10, 5);
        corTextFieldConstraints.anchor = GridBagConstraints.WEST;
        add(corTextField, corTextFieldConstraints);

        GridBagConstraints funileiroProprioLabelConstraints = new GridBagConstraints();
        funileiroProprioLabelConstraints.gridx = 0;
        funileiroProprioLabelConstraints.gridy = 2;
        funileiroProprioLabelConstraints.insets = new Insets(1, 5, 10, 5);
        funileiroProprioLabelConstraints.anchor = GridBagConstraints.WEST;
        add(funileiroProprioLabel, funileiroProprioLabelConstraints);

        GridBagConstraints funileiroProprioCheckBoxConstraints = new GridBagConstraints();
        funileiroProprioCheckBoxConstraints.gridx = 1;
        funileiroProprioCheckBoxConstraints.gridy = 2;
        funileiroProprioCheckBoxConstraints.insets = new Insets(1, 5, 10, 5);
        funileiroProprioCheckBoxConstraints.anchor = GridBagConstraints.WEST;
        add(funileiroProprioCheckBox, funileiroProprioCheckBoxConstraints);
    }// </editor-fold>//GEN-END:initComponents

    public PeçaAcessório.CategoriaAcessório getCategoriaAcessório() {
        return (PeçaAcessório.CategoriaAcessório) categoriaComboBox.getSelectedItem();
    }
    public String getCor() { return corTextField.getText().trim(); }
    public boolean isFunileiroProprio() { return funileiroProprioCheckBox.isSelected(); }
    public void setCategoriaAcessório(PeçaAcessório.CategoriaAcessório valor) { categoriaComboBox.setSelectedItem(valor); }
    public void setCor(String valor) { corTextField.setText(valor == null ? "" : valor); }
    public void setFunileiroProprio(boolean valor) { funileiroProprioCheckBox.setSelected(valor); }
    public void limparCampos() { categoriaComboBox.setSelectedIndex(-1); setCor(null); setFunileiroProprio(false); }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox<PeçaAcessório.CategoriaAcessório> categoriaComboBox;
    private JTextField corTextField;
    private JCheckBox funileiroProprioCheckBox;
    private JLabel categoriaLabel, corLabel, funileiroProprioLabel;
    // End of variables declaration//GEN-END:variables
}
