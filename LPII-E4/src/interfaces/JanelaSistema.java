package interfaces;

import controles.ControladorCadastroSeguradoras;
import controles.ControladorCadastroPeças;
import controles.ControladorCadastroSinistros;
import controles.ControladorCadastroOrçamentos;
import persistência.BD;

public class JanelaSistema extends javax.swing.JFrame {

    public JanelaSistema() {
        BD.criaConexao();
        initComponents();
        setLocationByPlatform(true);
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        seguradoras_orçamento_menu_bar = new javax.swing.JMenuBar();
        peça_menu = new javax.swing.JMenu();
        cadastrar_peça_menu_item = new javax.swing.JMenuItem();
        seguradora_menu = new javax.swing.JMenu();
        cadastrar_seguradora_item_menu = new javax.swing.JMenuItem();
        sinistro_menu = new javax.swing.JMenu();
        cadastrar_sinistro_menu_item = new javax.swing.JMenuItem();
        orçamento_menu = new javax.swing.JMenu();
        cadastrar_orçamento_menu_item = new javax.swing.JMenuItem();
        pesquisar_orçamento_menu_item = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Orçamentos da Seguradora");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                terminarSistema(evt);
            }
        });

        peça_menu.setText("Peça");

        cadastrar_peça_menu_item.setText("Cadastrar");
        cadastrar_peça_menu_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarPeça(evt);
            }
        });
        peça_menu.add(cadastrar_peça_menu_item);

        seguradoras_orçamento_menu_bar.add(peça_menu);

        seguradora_menu.setText("Seguradora");

        cadastrar_seguradora_item_menu.setText("Cadastrar");
        cadastrar_seguradora_item_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarSeguradora(evt);
            }
        });
        seguradora_menu.add(cadastrar_seguradora_item_menu);

        seguradoras_orçamento_menu_bar.add(seguradora_menu);

        sinistro_menu.setText("Sinistro");

        cadastrar_sinistro_menu_item.setText("Cadastrar");
        cadastrar_sinistro_menu_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarSinistro(evt);
            }
        });
        sinistro_menu.add(cadastrar_sinistro_menu_item);

        seguradoras_orçamento_menu_bar.add(sinistro_menu);

        orçamento_menu.setText("Orçamento");

        cadastrar_orçamento_menu_item.setText("Cadastrar");
        cadastrar_orçamento_menu_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarOrçamentos(evt);
            }
        });
        orçamento_menu.add(cadastrar_orçamento_menu_item);

        pesquisar_orçamento_menu_item.setText("Pesquisar");
        pesquisar_orçamento_menu_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarOrçamentos(evt);
            }
        });
        orçamento_menu.add(pesquisar_orçamento_menu_item);

        seguradoras_orçamento_menu_bar.add(orçamento_menu);

        setJMenuBar(seguradoras_orçamento_menu_bar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cadastrarSeguradora(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarSeguradora
        new ControladorCadastroSeguradoras(this);
    }//GEN-LAST:event_cadastrarSeguradora

    private void terminarSistema(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_terminarSistema
        BD.fechaConexao();
        System.exit(0);
    }//GEN-LAST:event_terminarSistema
  
    private void pesquisarOrçamentos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarOrçamentos
        JanelaPesquisaOrçamentos janela = new JanelaPesquisaOrçamentos();
        janela.setVisible(true);
        janela.toFront();
        janela.requestFocus();
    }//GEN-LAST:event_pesquisarOrçamentos

    private void cadastrarPeça(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarPeça
        new ControladorCadastroPeças(this);
    }//GEN-LAST:event_cadastrarPeça

    private void cadastrarOrçamentos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarOrçamentos
        new ControladorCadastroOrçamentos();
    }//GEN-LAST:event_cadastrarOrçamentos

    private void cadastrarSinistro(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarSinistro
        new ControladorCadastroSinistros(this);
    }//GEN-LAST:event_cadastrarSinistro

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaSistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cadastrar_orçamento_menu_item;
    private javax.swing.JMenuItem cadastrar_peça_menu_item;
    private javax.swing.JMenuItem cadastrar_seguradora_item_menu;
    private javax.swing.JMenuItem cadastrar_sinistro_menu_item;
    private javax.swing.JMenu orçamento_menu;
    private javax.swing.JMenuItem pesquisar_orçamento_menu_item;
    private javax.swing.JMenu peça_menu;
    private javax.swing.JMenu seguradora_menu;
    private javax.swing.JMenuBar seguradoras_orçamento_menu_bar;
    private javax.swing.JMenu sinistro_menu;
    // End of variables declaration//GEN-END:variables
 
}
