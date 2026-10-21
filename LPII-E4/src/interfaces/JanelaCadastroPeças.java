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
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class JanelaCadastroPeças extends JFrame {
    private final ControladorCadastroPeças controlador;
    private Peça[] peçasCadastradas;

    public JanelaCadastroPeças(ControladorCadastroPeças controlador) { this(controlador,null); }
    public JanelaCadastroPeças(ControladorCadastroPeças controlador, Frame owner) {
        this.controlador=controlador;
        initComponents();
        especializaçãoTabbedPane.addTab("Peça mecânica", painelMecânica);
        especializaçãoTabbedPane.addTab("Peça de acessórios", painelAcessórios);
        setTitle("Cadastrar Peças");
        setResizable(false);
        atualizarPeçasCadastradas(-1);
        limparCampos();
        pack();
        setSize(590, 460);
        JanelaUtil.posicionarNoCanto(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        GridBagConstraints c = new GridBagConstraints();
        peçasComboBox = new JComboBox<>();
        codigoField = new JTextField(8);
        nomeField = new JTextField(24);
        marcaComboBox = new JComboBox<>(Peça.MarcaPeça.values());
        precoField = new JTextField(10);
        sobEncomendaCheckBox = new JCheckBox("Sob encomenda");
        painelMecânica = new JPanel(new GridBagLayout());
        painelAcessórios = new JPanel(new GridBagLayout());
        categoriaMecânicaComboBox = new JComboBox<>(PeçaMecânica.CategoriaMecânica.values());
        prazoGarantiaTextField = new JTextField(10);
        mecanicoProprioCheckBox = new JCheckBox("Sim");
        categoriaAcessórioComboBox = new JComboBox<>(PeçaAcessório.CategoriaAcessório.values());
        corTextField = new JTextField(10);
        funileiroProprioCheckBox = new JCheckBox("Sim");
        especializaçãoTabbedPane = new JTabbedPane();
        camposComunsPanel = new JPanel(new GridBagLayout());
        abasAlinhadasPanel = new JPanel(new GridBagLayout());
        comandosPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inserirButton = new JButton("Inserir");
        consultarButton = new JButton("Consultar");
        alterarButton = new JButton("Alterar");
        removerButton = new JButton("Remover");
        limparButton = new JButton("Limpar");
        peçasLabel = new JLabel(); codigoLabel = new JLabel(); nomeLabel = new JLabel();
        marcaLabel = new JLabel(); precoLabel = new JLabel();
        categoriaMecânicaLabel = new JLabel(); prazoGarantiaLabel = new JLabel(); mecanicoProprioLabel = new JLabel();
        categoriaAcessórioLabel = new JLabel(); corLabel = new JLabel(); funileiroProprioLabel = new JLabel();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Peças");
        getContentPane().setLayout(new GridBagLayout());
        c.insets = new Insets(1, 5, 10, 5); c.anchor = GridBagConstraints.WEST;
        adicionarCampoGerado(camposComunsPanel, c, 0, peçasLabel, "Peças cadastradas", peçasComboBox, false);
        adicionarCampoGerado(camposComunsPanel, c, 1, codigoLabel, "Código", codigoField, false);
        adicionarCampoGerado(camposComunsPanel, c, 2, nomeLabel, "Nome", nomeField, false);
        adicionarCampoGerado(camposComunsPanel, c, 3, marcaLabel, "Marca", marcaComboBox, false);
        adicionarCampoGerado(camposComunsPanel, c, 4, precoLabel, "Preço", precoField, false);
        c.gridx = 1; c.gridy = 5; camposComunsPanel.add(sobEncomendaCheckBox, c);
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(camposComunsPanel, c);
        painelMecânica.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        painelAcessórios.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        adicionarCampoGerado(painelMecânica, c, 0, categoriaMecânicaLabel, "Categoria mecânica", categoriaMecânicaComboBox, true);
        adicionarCampoGerado(painelMecânica, c, 1, prazoGarantiaLabel, "Prazo de garantia (dias)", prazoGarantiaTextField, true);
        adicionarCampoGerado(painelMecânica, c, 2, mecanicoProprioLabel, "Mecânico próprio", mecanicoProprioCheckBox, true);
        adicionarCampoGerado(painelAcessórios, c, 0, categoriaAcessórioLabel, "Categoria do acessório", categoriaAcessórioComboBox, true);
        adicionarCampoGerado(painelAcessórios, c, 1, corLabel, "Cor", corTextField, true);
        adicionarCampoGerado(painelAcessórios, c, 2, funileiroProprioLabel, "Funileiro próprio", funileiroProprioCheckBox, true);
        abasAlinhadasPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        abasAlinhadasPanel.add(especializaçãoTabbedPane);
        c.gridx = 0; c.gridy = 1; c.gridwidth = 2; c.fill = GridBagConstraints.BOTH; c.weightx = 1.0; c.weighty = 1.0;
        getContentPane().add(abasAlinhadasPanel, c);
        comandosPanel.add(inserirButton); comandosPanel.add(consultarButton);
        comandosPanel.add(alterarButton); comandosPanel.add(removerButton); comandosPanel.add(limparButton);
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2; c.fill = GridBagConstraints.NONE; c.weightx = 0; c.weighty = 0;
        getContentPane().add(comandosPanel, c);
        inserirButton.addActionListener(e -> inserir()); consultarButton.addActionListener(e -> consultar());
        alterarButton.addActionListener(e -> alterar()); removerButton.addActionListener(e -> remover());
        limparButton.addActionListener(e -> limparCampos());
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
    private void atualizarPeçasCadastradas(int codigoSelecionado) {
        peçasCadastradas=Peça.getVisoes(); peçasComboBox.setModel(new DefaultComboBoxModel<>(peçasCadastradas));
        for(Peça p:peçasCadastradas) if(p.getCodigo()==codigoSelecionado) { peçasComboBox.setSelectedItem(p);break; }
    }
    private Peça obterPeçaInformada() {
        int codigo; double preco;
        try { codigo=Integer.parseInt(codigoField.getText().trim()); } catch(NumberFormatException e) { throw new IllegalArgumentException("Informe um código inteiro válido."); }
        String nome=nomeField.getText().trim(); if(nome.isEmpty()) throw new IllegalArgumentException("Informe o nome da peça.");
        Peça.MarcaPeça marca=(Peça.MarcaPeça)marcaComboBox.getSelectedItem(); if(marca==null) throw new IllegalArgumentException("Selecione a marca.");
        try { preco=Double.parseDouble(precoField.getText().trim().replace(',','.')); } catch(NumberFormatException e) { throw new IllegalArgumentException("Informe um preço válido."); }
        if(preco<0) throw new IllegalArgumentException("O preço não pode ser negativo.");
        try {
            if(especializaçãoTabbedPane.getSelectedIndex()==0) {
                PeçaMecânica.CategoriaMecânica categoria=(PeçaMecânica.CategoriaMecânica) categoriaMecânicaComboBox.getSelectedItem();
                if(categoria==null) throw new IllegalArgumentException("Selecione a categoria mecânica.");
                String prazoTexto=prazoGarantiaTextField.getText().trim();
                Integer prazo=prazoTexto.isEmpty()?null:Integer.valueOf(prazoTexto);
                if(prazo!=null && prazo<0) throw new IllegalArgumentException("O prazo de garantia não pode ser negativo.");
                return new PeçaMecânica(codigo,nome,marca,preco,categoria,prazo,mecanicoProprioCheckBox.isSelected(),sobEncomendaCheckBox.isSelected());
            }
            PeçaAcessório.CategoriaAcessório categoria=(PeçaAcessório.CategoriaAcessório) categoriaAcessórioComboBox.getSelectedItem();
            if(categoria==null) throw new IllegalArgumentException("Selecione a categoria do acessório.");
            String cor=corTextField.getText().trim();
            return new PeçaAcessório(codigo,nome,marca,preco,categoria,cor.isEmpty()?null:cor,funileiroProprioCheckBox.isSelected(),sobEncomendaCheckBox.isSelected());
        } catch(NumberFormatException e) { throw new IllegalArgumentException("Informe um prazo de garantia inteiro válido."); }
    }
    private void inserir() {
        try { Peça p=obterPeçaInformada(); String erro=controlador.inserirPeças(p); if(erro!=null) erro(erro); else atualizarPeçasCadastradas(p.getCodigo()); }
        catch(IllegalArgumentException e) { erro(e.getMessage()); }
    }
    private void alterar() {
        try { Peça p=obterPeçaInformada(); String erro=controlador.alterarPeças(p); if(erro!=null) erro(erro); else atualizarPeçasCadastradas(p.getCodigo()); }
        catch(IllegalArgumentException e) { erro(e.getMessage()); }
    }
    private void consultar() {
        Peça selecionada=(Peça)peçasComboBox.getSelectedItem(); if(selecionada==null) { erro("Selecione uma peça.");return; }
        Peça p=Peça.buscarPeças(selecionada.getCodigo()); if(p==null) { erro("Peça não encontrada.");return; }
        codigoField.setText(Integer.toString(p.getCodigo())); nomeField.setText(p.getNome()); marcaComboBox.setSelectedItem(p.getMarca()); precoField.setText(Double.toString(p.getPreco()));
        sobEncomendaCheckBox.setSelected(p.isSobEncomenda());
        if(p instanceof PeçaMecânica) {
            PeçaMecânica m=(PeçaMecânica)p; especializaçãoTabbedPane.setSelectedComponent(painelMecânica);
            categoriaMecânicaComboBox.setSelectedItem(m.getCategoriaMecânica()); prazoGarantiaTextField.setText(m.getPrazoGarantia()==null?"":m.getPrazoGarantia().toString()); mecanicoProprioCheckBox.setSelected(m.isMecanicoProprio());
        } else if(p instanceof PeçaAcessório) {
            PeçaAcessório a=(PeçaAcessório)p; especializaçãoTabbedPane.setSelectedComponent(painelAcessórios);
            categoriaAcessórioComboBox.setSelectedItem(a.getCategoriaAcessório()); corTextField.setText(a.getCor()==null?"":a.getCor()); funileiroProprioCheckBox.setSelected(a.isFunileiroProprio());
        }
    }
    private void remover() {
        Peça p=(Peça)peçasComboBox.getSelectedItem(); if(p==null) { erro("Selecione uma peça.");return; }
        String msg=controlador.removerPeças(p.getCodigo()); if(msg!=null) erro(msg); else { atualizarPeçasCadastradas(-1);limparCampos(); }
    }
    private void limparCampos() {
        codigoField.setText("");nomeField.setText("");marcaComboBox.setSelectedIndex(-1);precoField.setText("");
        sobEncomendaCheckBox.setSelected(false);
        categoriaMecânicaComboBox.setSelectedIndex(-1); prazoGarantiaTextField.setText(""); mecanicoProprioCheckBox.setSelected(false);
        categoriaAcessórioComboBox.setSelectedIndex(-1); corTextField.setText(""); funileiroProprioCheckBox.setSelected(false); especializaçãoTabbedPane.setSelectedIndex(0);
    }
    private void erro(String msg) { JOptionPane.showMessageDialog(this,msg,"Erro",JOptionPane.ERROR_MESSAGE); }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox<Peça> peçasComboBox;
    private JTextField codigoField, nomeField, precoField;
    private JComboBox<Peça.MarcaPeça> marcaComboBox;
    private JCheckBox sobEncomendaCheckBox;
    private JPanel painelMecânica, painelAcessórios;
    private JComboBox<PeçaMecânica.CategoriaMecânica> categoriaMecânicaComboBox;
    private JTextField prazoGarantiaTextField, corTextField;
    private JCheckBox mecanicoProprioCheckBox, funileiroProprioCheckBox;
    private JComboBox<PeçaAcessório.CategoriaAcessório> categoriaAcessórioComboBox;
    private JLabel categoriaMecânicaLabel, prazoGarantiaLabel, mecanicoProprioLabel;
    private JLabel categoriaAcessórioLabel, corLabel, funileiroProprioLabel;
    private JTabbedPane especializaçãoTabbedPane;
    private JPanel comandosPanel;
    private JButton inserirButton, consultarButton, alterarButton, removerButton, limparButton;
    private JLabel peçasLabel, codigoLabel, nomeLabel, marcaLabel, precoLabel;
    private JPanel camposComunsPanel, abasAlinhadasPanel;
    // End of variables declaration//GEN-END:variables
}
