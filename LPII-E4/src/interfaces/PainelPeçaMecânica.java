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
        GridBagConstraints c = new GridBagConstraints();
        categoriaLabel = new JLabel("Categoria mecânica");
        prazoGarantiaLabel = new JLabel("Prazo de garantia (dias)");
        mecanicoProprioLabel = new JLabel("Mecânico próprio");
        categoriaComboBox = new JComboBox<>(PeçaMecânica.CategoriaMecânica.values());
        prazoGarantiaTextField = new JTextField(10);
        mecanicoProprioCheckBox = new JCheckBox("Sim");
        setLayout(new GridBagLayout());
        c.insets = new Insets(1, 5, 10, 5); c.anchor = GridBagConstraints.WEST;
        adicionar(c, 0, categoriaLabel, categoriaComboBox);
        adicionar(c, 1, prazoGarantiaLabel, prazoGarantiaTextField);
        adicionar(c, 2, mecanicoProprioLabel, mecanicoProprioCheckBox);
    }// </editor-fold>//GEN-END:initComponents

    private void adicionar(GridBagConstraints c, int linha, JLabel rotulo, java.awt.Component campo) {
        c.gridx = 0; c.gridy = linha; add(rotulo, c); c.gridx = 1; add(campo, c);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox<PeçaMecânica.CategoriaMecânica> categoriaComboBox;
    private JTextField prazoGarantiaTextField;
    private JCheckBox mecanicoProprioCheckBox;
    private JLabel categoriaLabel, prazoGarantiaLabel, mecanicoProprioLabel;
    // End of variables declaration//GEN-END:variables
}
