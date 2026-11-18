package interfaces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelFiltrosPeçaAcessórios extends JPanel {
    public PainelFiltrosPeçaAcessórios() { initComponents(); }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        corLabel = new JLabel();
        corAcessório = new JTextField();

        corLabel.setText("Cor");
        corAcessório.setColumns(12);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.WEST;
        add(corLabel, c);
        c.gridx = 1;
        add(corAcessório, c);
    }// </editor-fold>//GEN-END:initComponents

    public String getCor() {
        String cor = corAcessório.getText().trim();
        return cor.isEmpty() ? null : cor;
    }

    public void limparFiltros() { corAcessório.setText(""); }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel corLabel;
    private JTextField corAcessório;
    // End of variables declaration//GEN-END:variables
}
