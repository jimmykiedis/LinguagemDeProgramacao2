package interfaces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelFiltrosPeçaMecânica extends JPanel {
    private final JLabel prazoLabel = new JLabel("Prazo mínimo de garantia");
    private final JTextField prazoMínimoGarantia = new JTextField(12);

    public PainelFiltrosPeçaMecânica() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.WEST;
        add(prazoLabel, c);
        c.gridx = 1;
        add(prazoMínimoGarantia, c);
    }

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
}
