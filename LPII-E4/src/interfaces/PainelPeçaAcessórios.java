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
        GridBagConstraints c = new GridBagConstraints();
        categoriaLabel = new JLabel("Categoria do acessório");
        corLabel = new JLabel("Cor");
        funileiroProprioLabel = new JLabel("Funileiro próprio");
        categoriaComboBox = new JComboBox<>(PeçaAcessório.CategoriaAcessório.values());
        corTextField = new JTextField(10);
        funileiroProprioCheckBox = new JCheckBox("Sim");
        setLayout(new GridBagLayout());
        c.insets = new Insets(1, 5, 10, 5); c.anchor = GridBagConstraints.WEST;
        adicionar(c, 0, categoriaLabel, categoriaComboBox);
        adicionar(c, 1, corLabel, corTextField);
        adicionar(c, 2, funileiroProprioLabel, funileiroProprioCheckBox);
    }// </editor-fold>//GEN-END:initComponents

    private void adicionar(GridBagConstraints c, int linha, JLabel rotulo, java.awt.Component campo) {
        c.gridx = 0; c.gridy = linha; add(rotulo, c); c.gridx = 1; add(campo, c);
    }

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
