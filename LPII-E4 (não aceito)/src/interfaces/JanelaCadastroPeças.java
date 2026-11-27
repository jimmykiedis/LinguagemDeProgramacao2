package interfaces;

import controles.ControladorCadastroPeças;
import entidades.Peça;
import entidades.PeçaAcessório;
import entidades.PeçaMecânica;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class JanelaCadastroPeças extends javax.swing.JFrame {
    private final ControladorCadastroPeças controlador;
    private Peça[] peças_cadastradas;

    public JanelaCadastroPeças(ControladorCadastroPeças controlador) { this(controlador,null); }
    public JanelaCadastroPeças(ControladorCadastroPeças controlador, Frame owner) {
        this.controlador=controlador;
        initComponents();
        painel_mecânica = new PainelPeçaMecânica();
        painel_acessórios = new PainelPeçaAcessório();
        painel_mecânica.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        painel_acessórios.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        especialização_tabbed_pane.addTab("Peça mecânica", painel_mecânica);
        especialização_tabbed_pane.addTab("Peça de acessórios", painel_acessórios);
        setTitle("Cadastrar Peças");
        setResizable(false);
        atualizarPeçasCadastradas(-1);
        limparCampos();
        pack();
        setSize(590, 460);
        if (owner == null) {
            setLocationByPlatform(true);
        } else {
            setLocation(
                    owner.getX() + (owner.getWidth() - getWidth()) / 2,
                    owner.getY() + (owner.getHeight() - getHeight()) / 2
            );
        }
        setAlwaysOnTop(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        GridBagConstraints c = new GridBagConstraints();
        peças_combo_box = new JComboBox<>();
        codigo_field = new JTextField(8);
        nome_field = new JTextField(24);
        marca_combo_box = new JComboBox<>(Peça.MarcaPeça.values());
        preco_field = new JTextField(10);
        sob_encomenda_check_box = new JCheckBox("Sob encomenda");
        especialização_tabbed_pane = new JTabbedPane();
        campos_comuns_panel = new JPanel(new GridBagLayout());
        abas_alinhadas_panel = new JPanel(new GridBagLayout());
        comandos_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inserir_button = new JButton("Inserir");
        consultar_button = new JButton("Consultar");
        alterar_button = new JButton("Alterar");
        remover_button = new JButton("Remover");
        limpar_button = new JButton("Limpar");
        peças_label = new JLabel(); codigo_label = new JLabel(); nome_label = new JLabel();
        marca_label = new JLabel(); preco_label = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Peças");
        getContentPane().setLayout(new GridBagLayout());
        c.insets = new Insets(1, 5, 10, 5); c.anchor = GridBagConstraints.WEST;
        adicionarCampoGerado(campos_comuns_panel, c, 0, peças_label, "Peças cadastradas", peças_combo_box, false);
        adicionarCampoGerado(campos_comuns_panel, c, 1, codigo_label, "Código", codigo_field, false);
        adicionarCampoGerado(campos_comuns_panel, c, 2, nome_label, "Nome", nome_field, false);
        adicionarCampoGerado(campos_comuns_panel, c, 3, marca_label, "Marca", marca_combo_box, false);
        adicionarCampoGerado(campos_comuns_panel, c, 4, preco_label, "Preço", preco_field, false);
        c.gridx = 1; c.gridy = 5; campos_comuns_panel.add(sob_encomenda_check_box, c);
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(campos_comuns_panel, c);
        abas_alinhadas_panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        abas_alinhadas_panel.add(especialização_tabbed_pane);
        c.gridx = 0; c.gridy = 1; c.gridwidth = 2; c.fill = GridBagConstraints.BOTH; c.weightx = 1.0; c.weighty = 1.0;
        getContentPane().add(abas_alinhadas_panel, c);
        comandos_panel.add(inserir_button); comandos_panel.add(consultar_button);
        comandos_panel.add(alterar_button); comandos_panel.add(remover_button); comandos_panel.add(limpar_button);
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2; c.fill = GridBagConstraints.NONE; c.weightx = 0; c.weighty = 0;
        getContentPane().add(comandos_panel, c);
        inserir_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { inserirPeça(evt); }
        });
        consultar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { consultarPeça(evt); }
        });
        alterar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { alterarPeça(evt); }
        });
        remover_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { removerPeça(evt); }
        });
        limpar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { limparCampos(evt); }
        });
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarCampoGerado(java.awt.Container painel, GridBagConstraints c, int linha,
            JLabel rotulo, String texto, java.awt.Component campo, boolean expandir) {
        rotulo.setText(texto);
        c.gridwidth = 1;
        c.gridx = 0; c.gridy = linha; c.fill = GridBagConstraints.NONE; c.weightx = 0;
        painel.add(rotulo, c);
        c.gridx = 1; c.fill = expandir ? GridBagConstraints.HORIZONTAL : GridBagConstraints.NONE;
        c.weightx = expandir ? 1.0 : 0;
        painel.add(campo, c);
    }
    private void atualizarPeçasCadastradas(int codigo_selecionado) {
        peças_cadastradas=Peça.getVisoes(); peças_combo_box.setModel(new DefaultComboBoxModel<>(peças_cadastradas));
        for(Peça p:peças_cadastradas) if(p.getCodigo()==codigo_selecionado) { peças_combo_box.setSelectedItem(p);break; }
    }
    private Peça obterPeçaInformada() {
        int codigo; double preco;
        try { codigo=Integer.parseInt(codigo_field.getText().trim()); } catch(NumberFormatException e) { throw new IllegalArgumentException("Informe um código inteiro válido."); }
        String nome=nome_field.getText().trim(); if(nome.isEmpty()) throw new IllegalArgumentException("Informe o nome da peça.");
        Peça.MarcaPeça marca=(Peça.MarcaPeça) marca_combo_box.getSelectedItem(); if(marca==null) throw new IllegalArgumentException("Selecione a marca.");
        try { preco=Double.parseDouble(preco_field.getText().trim().replace(',','.')); } catch(NumberFormatException e) { throw new IllegalArgumentException("Informe um preço válido."); }
        if(preco<0) throw new IllegalArgumentException("O preço não pode ser negativo.");
        try {
            switch (especialização_tabbed_pane.getSelectedIndex()) {
                case 0:
                    PeçaMecânica.CategoriaMecânica categoria_mecânica = painel_mecânica.getCategoriaMecânica();
                    if (categoria_mecânica == null) throw new IllegalArgumentException("Selecione a categoria mecânica.");
                    int prazo_informado = painel_mecânica.getPrazoGarantia();
                    Integer prazo = prazo_informado == -1 ? null : prazo_informado;
                    return new PeçaMecânica(codigo, nome, marca, preco, categoria_mecânica, prazo,
                            painel_mecânica.isMecânicoPróprio(), sob_encomenda_check_box.isSelected());
                case 1:
                    PeçaAcessório.CategoriaAcessório categoria_acessório = painel_acessórios.getCategoriaAcessório();
                    if (categoria_acessório == null) throw new IllegalArgumentException("Selecione a categoria do acessório.");
                    return new PeçaAcessório(codigo, nome, marca, preco, categoria_acessório,
                            painel_acessórios.getCor(), painel_acessórios.isFunileiroPróprio(),
                            sob_encomenda_check_box.isSelected());
                default:
                    throw new IllegalArgumentException("Selecione o tipo de peça.");
            }
        } catch(NumberFormatException e) { throw new IllegalArgumentException("Informe um prazo de garantia inteiro válido."); }
    }
    private void inserirPeça(java.awt.event.ActionEvent evt) {
        try { Peça p=obterPeçaInformada(); String erro=controlador.inserirPeças(p); if(erro!=null) erro(erro); else atualizarPeçasCadastradas(p.getCodigo()); }
        catch(IllegalArgumentException e) { erro(e.getMessage()); }
    }
    private void alterarPeça(java.awt.event.ActionEvent evt) {
        try { Peça p=obterPeçaInformada(); String erro=controlador.alterarPeças(p); if(erro!=null) erro(erro); else atualizarPeçasCadastradas(p.getCodigo()); }
        catch(IllegalArgumentException e) { erro(e.getMessage()); }
    }
    private void consultarPeça(java.awt.event.ActionEvent evt) {
        Peça selecionada=(Peça)peças_combo_box.getSelectedItem(); if(selecionada==null) { erro("Selecione uma peça.");return; }
        Peça p=Peça.buscarPeças(selecionada.getCodigo()); if(p==null) { erro("Peça não encontrada.");return; }
        codigo_field.setText(Integer.toString(p.getCodigo())); nome_field.setText(p.getNome()); marca_combo_box.setSelectedItem(p.getMarca()); preco_field.setText(Double.toString(p.getPreco()));
        sob_encomenda_check_box.setSelected(p.getSobEncomenda());
        if(p instanceof PeçaMecânica) {
            PeçaMecânica m=(PeçaMecânica)p; especialização_tabbed_pane.setSelectedComponent(painel_mecânica);
            painel_mecânica.setCategoriaMecânica(m.getCategoriaMecânica());
            painel_mecânica.setPrazoGarantia(m.getPrazoGarantia());
            painel_mecânica.setMecânicoPróprio(m.getMecânicoPróprio());
        } else if(p instanceof PeçaAcessório) {
            PeçaAcessório a=(PeçaAcessório)p; especialização_tabbed_pane.setSelectedComponent(painel_acessórios);
            painel_acessórios.setCategoriaAcessório(a.getCategoriaAcessório());
            painel_acessórios.setCor(a.getCor());
            painel_acessórios.setFunileiroPróprio(a.getFunileiroPróprio());
        }
    }
    private void removerPeça(java.awt.event.ActionEvent evt) {
        Peça p=(Peça)peças_combo_box.getSelectedItem(); if(p==null) { erro("Selecione uma peça.");return; }
        String msg=controlador.removerPeças(p.getCodigo()); if(msg!=null) erro(msg); else { atualizarPeçasCadastradas(-1);limparCampos(); }
    }
    private void limparCampos(java.awt.event.ActionEvent evt) {
        limparCampos();
    }

    private void limparCampos() {
        codigo_field.setText("");nome_field.setText("");marca_combo_box.setSelectedIndex(-1);preco_field.setText("");
        sob_encomenda_check_box.setSelected(false);
        painel_mecânica.limparCampos();
        painel_acessórios.limparCampos();
        especialização_tabbed_pane.setSelectedIndex(0);
    }
    private void erro(String msg) { JOptionPane.showMessageDialog(this,msg,"Erro",JOptionPane.ERROR_MESSAGE); }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox<Peça> peças_combo_box;
    private JTextField codigo_field, nome_field, preco_field;
    private JComboBox<Peça.MarcaPeça> marca_combo_box;
    private JCheckBox sob_encomenda_check_box;
    private PainelPeçaMecânica painel_mecânica;
    private PainelPeçaAcessório painel_acessórios;
    private JTabbedPane especialização_tabbed_pane;
    private JPanel comandos_panel;
    private JButton inserir_button, consultar_button, alterar_button, remover_button, limpar_button;
    private JLabel peças_label, codigo_label, nome_label, marca_label, preco_label;
    private JPanel campos_comuns_panel, abas_alinhadas_panel;
    // End of variables declaration//GEN-END:variables
}
