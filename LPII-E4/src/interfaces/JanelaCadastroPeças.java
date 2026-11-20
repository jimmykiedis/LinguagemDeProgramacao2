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
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class JanelaCadastroPeças extends javax.swing.JFrame {
    private final ControladorCadastroPeças controlador;
    private Peça[] peças_cadastradas;

    public JanelaCadastroPeças(ControladorCadastroPeças controlador) { this(controlador,null); }
    public JanelaCadastroPeças(ControladorCadastroPeças controlador, Frame owner) {
        this.controlador=controlador;
        initComponents();
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
        marca_button_group = new ButtonGroup();
        marca_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        marca_oem_radio_button = new JRadioButton(Peça.MarcaPeça.oem.toString());
        marca_original_radio_button = new JRadioButton(Peça.MarcaPeça.original.toString());
        marca_genuina_radio_button = new JRadioButton(Peça.MarcaPeça.genuína.toString());
        marca_button_group.add(marca_oem_radio_button);
        marca_button_group.add(marca_original_radio_button);
        marca_button_group.add(marca_genuina_radio_button);
        marca_panel.add(marca_oem_radio_button);
        marca_panel.add(marca_original_radio_button);
        marca_panel.add(marca_genuina_radio_button);
        preco_field = new JTextField(10);
        sob_encomenda_check_box = new JCheckBox("Sob encomenda");
        painel_mecânica = new JPanel(new GridBagLayout());
        painel_acessórios = new JPanel(new GridBagLayout());
        categoria_mecânica_combo_box = new JComboBox<>(PeçaMecânica.CategoriaMecânica.values());
        prazo_garantia_text_field = new JTextField(10);
        mecânico_próprio_check_box = new JCheckBox("Sim");
        categoria_acessório_combo_box = new JComboBox<>(PeçaAcessório.CategoriaAcessório.values());
        cor_text_field = new JTextField(10);
        funileiro_próprio_check_box = new JCheckBox("Sim");
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
        categoria_mecânica_label = new JLabel(); prazo_garantia_label = new JLabel(); mecânico_próprio_label = new JLabel();
        categoria_acessório_label = new JLabel(); cor_label = new JLabel(); funileiro_próprio_label = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Peças");
        getContentPane().setLayout(new GridBagLayout());
        c.insets = new Insets(1, 5, 10, 5); c.anchor = GridBagConstraints.WEST;
        adicionarCampoGerado(campos_comuns_panel, c, 0, peças_label, "Peças cadastradas", peças_combo_box, false);
        adicionarCampoGerado(campos_comuns_panel, c, 1, codigo_label, "Código", codigo_field, false);
        adicionarCampoGerado(campos_comuns_panel, c, 2, nome_label, "Nome", nome_field, false);
        adicionarCampoGerado(campos_comuns_panel, c, 3, marca_label, "Marca", marca_panel, false);
        adicionarCampoGerado(campos_comuns_panel, c, 4, preco_label, "Preço", preco_field, false);
        c.gridx = 1; c.gridy = 5; campos_comuns_panel.add(sob_encomenda_check_box, c);
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(campos_comuns_panel, c);
        painel_mecânica.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        painel_acessórios.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        adicionarCampoGerado(painel_mecânica, c, 0, categoria_mecânica_label, "Categoria mecânica", categoria_mecânica_combo_box, true);
        adicionarCampoGerado(painel_mecânica, c, 1, prazo_garantia_label, "Prazo de garantia (dias)", prazo_garantia_text_field, true);
        adicionarCampoGerado(painel_mecânica, c, 2, mecânico_próprio_label, "Mecânico próprio", mecânico_próprio_check_box, true);
        adicionarCampoGerado(painel_acessórios, c, 0, categoria_acessório_label, "Categoria do acessório", categoria_acessório_combo_box, true);
        adicionarCampoGerado(painel_acessórios, c, 1, cor_label, "Cor", cor_text_field, true);
        adicionarCampoGerado(painel_acessórios, c, 2, funileiro_próprio_label, "Funileiro próprio", funileiro_próprio_check_box, true);
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
        Peça.MarcaPeça marca=obterMarcaSelecionada(); if(marca==null) throw new IllegalArgumentException("Selecione a marca.");
        try { preco=Double.parseDouble(preco_field.getText().trim().replace(',','.')); } catch(NumberFormatException e) { throw new IllegalArgumentException("Informe um preço válido."); }
        if(preco<0) throw new IllegalArgumentException("O preço não pode ser negativo.");
        try {
            if(especialização_tabbed_pane.getSelectedIndex()==0) {
                PeçaMecânica.CategoriaMecânica categoria=(PeçaMecânica.CategoriaMecânica) categoria_mecânica_combo_box.getSelectedItem();
                if(categoria==null) throw new IllegalArgumentException("Selecione a categoria mecânica.");
                String prazo_texto=prazo_garantia_text_field.getText().trim();
                Integer prazo=prazo_texto.isEmpty()?null:Integer.valueOf(prazo_texto);
                if(prazo!=null && prazo<0) throw new IllegalArgumentException("O prazo de garantia não pode ser negativo.");
                return new PeçaMecânica(codigo,nome,marca,preco,categoria,prazo,mecânico_próprio_check_box.isSelected(),sob_encomenda_check_box.isSelected());
            }
            PeçaAcessório.CategoriaAcessório categoria=(PeçaAcessório.CategoriaAcessório) categoria_acessório_combo_box.getSelectedItem();
            if(categoria==null) throw new IllegalArgumentException("Selecione a categoria do acessório.");
            String cor=cor_text_field.getText().trim();
            return new PeçaAcessório(codigo,nome,marca,preco,categoria,cor.isEmpty()?null:cor,funileiro_próprio_check_box.isSelected(),sob_encomenda_check_box.isSelected());
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
        codigo_field.setText(Integer.toString(p.getCodigo())); nome_field.setText(p.getNome()); definirMarcaSelecionada(p.getMarca()); preco_field.setText(Double.toString(p.getPreco()));
        sob_encomenda_check_box.setSelected(p.getSobEncomenda());
        if(p instanceof PeçaMecânica) {
            PeçaMecânica m=(PeçaMecânica)p; especialização_tabbed_pane.setSelectedComponent(painel_mecânica);
            categoria_mecânica_combo_box.setSelectedItem(m.getCategoriaMecânica()); prazo_garantia_text_field.setText(m.getPrazoGarantia()==null?"":m.getPrazoGarantia().toString()); mecânico_próprio_check_box.setSelected(m.getMecânicoPróprio());
        } else if(p instanceof PeçaAcessório) {
            PeçaAcessório a=(PeçaAcessório)p; especialização_tabbed_pane.setSelectedComponent(painel_acessórios);
            categoria_acessório_combo_box.setSelectedItem(a.getCategoriaAcessório()); cor_text_field.setText(a.getCor()==null?"":a.getCor()); funileiro_próprio_check_box.setSelected(a.getFunileiroPróprio());
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
        codigo_field.setText("");nome_field.setText("");marca_button_group.clearSelection();preco_field.setText("");
        sob_encomenda_check_box.setSelected(false);
        categoria_mecânica_combo_box.setSelectedIndex(-1); prazo_garantia_text_field.setText(""); mecânico_próprio_check_box.setSelected(false);
        categoria_acessório_combo_box.setSelectedIndex(-1); cor_text_field.setText(""); funileiro_próprio_check_box.setSelected(false); especialização_tabbed_pane.setSelectedIndex(0);
    }
    private Peça.MarcaPeça obterMarcaSelecionada() {
        if (marca_oem_radio_button.isSelected()) return Peça.MarcaPeça.oem;
        if (marca_original_radio_button.isSelected()) return Peça.MarcaPeça.original;
        if (marca_genuina_radio_button.isSelected()) return Peça.MarcaPeça.genuína;
        return null;
    }

    private void definirMarcaSelecionada(Peça.MarcaPeça valor) {
        marca_button_group.clearSelection();
        if (valor == Peça.MarcaPeça.oem) marca_oem_radio_button.setSelected(true);
        else if (valor == Peça.MarcaPeça.original) marca_original_radio_button.setSelected(true);
        else if (valor == Peça.MarcaPeça.genuína) marca_genuina_radio_button.setSelected(true);
    }

    private void erro(String msg) { JOptionPane.showMessageDialog(this,msg,"Erro",JOptionPane.ERROR_MESSAGE); }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox<Peça> peças_combo_box;
    private JTextField codigo_field, nome_field, preco_field;
    private ButtonGroup marca_button_group;
    private JPanel marca_panel;
    private JRadioButton marca_oem_radio_button, marca_original_radio_button, marca_genuina_radio_button;
    private JCheckBox sob_encomenda_check_box;
    private JPanel painel_mecânica, painel_acessórios;
    private JComboBox<PeçaMecânica.CategoriaMecânica> categoria_mecânica_combo_box;
    private JTextField prazo_garantia_text_field, cor_text_field;
    private JCheckBox mecânico_próprio_check_box, funileiro_próprio_check_box;
    private JComboBox<PeçaAcessório.CategoriaAcessório> categoria_acessório_combo_box;
    private JLabel categoria_mecânica_label, prazo_garantia_label, mecânico_próprio_label;
    private JLabel categoria_acessório_label, cor_label, funileiro_próprio_label;
    private JTabbedPane especialização_tabbed_pane;
    private JPanel comandos_panel;
    private JButton inserir_button, consultar_button, alterar_button, remover_button, limpar_button;
    private JLabel peças_label, codigo_label, nome_label, marca_label, preco_label;
    private JPanel campos_comuns_panel, abas_alinhadas_panel;
    // End of variables declaration//GEN-END:variables
}
