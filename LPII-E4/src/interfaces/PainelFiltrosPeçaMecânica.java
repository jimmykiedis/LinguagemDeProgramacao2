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
        prazo_label = new JLabel();
        prazo_mínimo_garantia = new JTextField();

        prazo_label.setText("Prazo mínimo de garantia");
        prazo_mínimo_garantia.setColumns(12);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.WEST;
        add(prazo_label, c);
        c.gridx = 1;
        add(prazo_mínimo_garantia, c);
    }// </editor-fold>//GEN-END:initComponents

    public Integer getPrazoMínimoGarantia() {
        String valor = prazo_mínimo_garantia.getText().trim();
        Integer prazo = null;
        if (!valor.isEmpty()) {
            try {
                prazo = Integer.parseInt(valor);
                if (prazo < 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Informe um número inteiro não negativo para prazo mínimo de garantia.");
            }
        }
        return prazo;
    }

    public void limparFiltros() { prazo_mínimo_garantia.setText(""); }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel prazo_label;
    private JTextField prazo_mínimo_garantia;
    // End of variables declaration//GEN-END:variables
}
