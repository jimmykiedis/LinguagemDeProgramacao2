package interfaces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelFiltrosPeçaAcessórios extends JPanel {
    private final JLabel corLabel = new JLabel("Cor");
    private final JTextField corAcessório = new JTextField(12);

    public PainelFiltrosPeçaAcessórios() { initComponents(); }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.WEST;
        add(corLabel, c);
        c.gridx = 1;
        add(corAcessório, c);
    }

    public String getCor() {
        String cor = corAcessório.getText().trim();
        return cor.isEmpty() ? null : cor;
    }

    public void limparFiltros() { corAcessório.setText(""); }
}
