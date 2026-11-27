package interfaces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelFiltrosPeçaAcessório extends JPanel {
    public PainelFiltrosPeçaAcessório() { initComponents(); }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        cor_label = new JLabel();
        cor_acessório = new JTextField();

        cor_label.setText("Cor");
        cor_acessório.setColumns(12);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.WEST;
        add(cor_label, c);
        c.gridx = 1;
        add(cor_acessório, c);
    }// </editor-fold>//GEN-END:initComponents

    public String getCor() {
        String cor = null;
        String valor = cor_acessório.getText().trim();
        if (!valor.isEmpty()) {
            cor = valor;
        }
        return cor;
    }

    public void limparFiltros() { cor_acessório.setText(""); }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel cor_label;
    private JTextField cor_acessório;
    // End of variables declaration//GEN-END:variables
}
