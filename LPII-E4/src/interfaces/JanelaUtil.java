package interfaces;

import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;

/** Utilitários de posicionamento para manter as janelas na área visível do monitor. */
final class JanelaUtil {
    private JanelaUtil() { }

    static void posicionarNoCanto(JFrame janela) {
        janela.setAlwaysOnTop(false);

        GraphicsConfiguration tela = janela.getGraphicsConfiguration();
        Rectangle area = new Rectangle(tela.getBounds());
        Insets margens = Toolkit.getDefaultToolkit().getScreenInsets(tela);
        area.x += margens.left;
        area.y += margens.top;
        area.width -= margens.left + margens.right;
        area.height -= margens.top + margens.bottom;

        janela.setLocation(area.x, area.y);
    }
}
