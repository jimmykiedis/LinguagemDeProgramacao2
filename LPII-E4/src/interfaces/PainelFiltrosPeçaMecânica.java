package interfaces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelFiltrosPeçaMecânica extends JPanel {
    public PainelFiltrosPeçaMecânica() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        prazoLabel = new JLabel();
        prazoMínimoGarantia = new JTextField();

        prazoLabel.setText("Prazo mínimo de garantia");
        prazoMínimoGarantia.setColumns(12);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.WEST;
        add(prazoLabel, c);
        c.gridx = 1;
        add(prazoMínimoGarantia, c);
    }// </editor-fold>//GEN-END:initComponents

    public Integer getPrazoMínimoGarantia() {
        String valor = prazoMínimoGarantia.getText().trim();
        if (valor.isEmpty()) return null;
        try {
            int prazo = Integer.parseInt(valor);
            if (prazo < 0) throw new NumberFormatException();
            return prazo;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Informe um número inteiro não negativo para prazo mínimo de garantia.");
        }
    }

    public void limparFiltros() { prazoMínimoGarantia.setText(""); }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel prazoLabel;
    private JTextField prazoMínimoGarantia;
    // End of variables declaration//GEN-END:variables
}
