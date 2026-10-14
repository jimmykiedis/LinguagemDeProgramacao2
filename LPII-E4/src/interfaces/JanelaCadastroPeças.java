package interfaces;

import controles.ControladorCadastroPeças;
import entidades.Peça;
import entidades.PeçaAcessórios;
import entidades.PeçaMecânica;
import java.awt.BorderLayout;
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
    private final JComboBox<Peça> peçasComboBox = new JComboBox<>();
    // Mantém a escala compacta do formulário antigo, sem expandir os campos pela janela.
    private final JTextField codigoField = new JTextField(8);
    private final JTextField nomeField = new JTextField(24);
    private final JComboBox<Peça.MarcaPeça> marcaComboBox = new JComboBox<>(Peça.MarcaPeça.values());
    private final JTextField precoField = new JTextField(10);
    private final JCheckBox sobEncomendaCheckBox = new JCheckBox("Sob encomenda");
    private final PainelPeçaMecânica painelMecânica = new PainelPeçaMecânica();
    private final PainelPeçaAcessórios painelAcessórios = new PainelPeçaAcessórios();
    private final JTabbedPane especializaçãoTabbedPane = new JTabbedPane();

    public JanelaCadastroPeças(ControladorCadastroPeças controlador) { this(controlador,null); }
    public JanelaCadastroPeças(ControladorCadastroPeças controlador, Frame owner) {
        this.controlador=controlador;
        setTitle("Cadastrar Peças");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(8,8));
        especializaçãoTabbedPane.addTab("Peça mecânica",painelMecânica);
        especializaçãoTabbedPane.addTab("Peça de acessórios",painelAcessórios);
        add(criarCamposComuns(),BorderLayout.NORTH);
        JPanel abasAlinhadas = new JPanel(new GridBagLayout());
        abasAlinhadas.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        abasAlinhadas.add(especializaçãoTabbedPane);
        add(abasAlinhadas,BorderLayout.CENTER);
        add(criarComandos(),BorderLayout.SOUTH);
        atualizarPeçasCadastradas(-1);
        limparCampos();
        pack();
        setSize(590, 460);
        JanelaUtil.posicionarNoCanto(this);
    }

    private JPanel criarCamposComuns() {
        JPanel painel=new JPanel(new GridBagLayout()); GridBagConstraints c=new GridBagConstraints();
        c.insets=new Insets(1,5,10,5); c.anchor=GridBagConstraints.WEST;
        adicionarCampo(painel,c,0,"Peças cadastradas",peçasComboBox);
        adicionarCampo(painel,c,1,"Código",codigoField);
        adicionarCampo(painel,c,2,"Nome",nomeField);
        adicionarCampo(painel,c,3,"Marca",marcaComboBox);
        adicionarCampo(painel,c,4,"Preço",precoField);
        c.gridx=1; c.gridy=5; painel.add(sobEncomendaCheckBox,c);
        return painel;
    }
    private void adicionarCampo(JPanel painel,GridBagConstraints c,int linha,String rotulo,java.awt.Component campo) {
        c.gridx=0;c.gridy=linha; painel.add(new JLabel(rotulo),c);
        c.gridx=1;c.fill=GridBagConstraints.NONE;c.weightx=0; painel.add(campo,c);
    }
    private JPanel criarComandos() {
        JPanel p=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton inserir=new JButton("Inserir"), consultar=new JButton("Consultar"), alterar=new JButton("Alterar"), remover=new JButton("Remover"), limpar=new JButton("Limpar");
        inserir.addActionListener(e->inserir()); consultar.addActionListener(e->consultar()); alterar.addActionListener(e->alterar()); remover.addActionListener(e->remover()); limpar.addActionListener(e->limparCampos());
        p.add(inserir);p.add(consultar);p.add(alterar);p.add(remover);p.add(limpar);return p;
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
                PeçaMecânica.CategoriaMecânica categoria=painelMecânica.getCategoriaMecânica();
                if(categoria==null) throw new IllegalArgumentException("Selecione a categoria mecânica.");
                Integer prazo=painelMecânica.getPrazoGarantia();
                if(prazo!=null && prazo<0) throw new IllegalArgumentException("O prazo de garantia não pode ser negativo.");
                return new PeçaMecânica(codigo,nome,marca,preco,categoria,prazo,painelMecânica.isMecanicoProprio(),sobEncomendaCheckBox.isSelected());
            }
            PeçaAcessórios.CategoriaAcessório categoria=painelAcessórios.getCategoriaAcessório();
            if(categoria==null) throw new IllegalArgumentException("Selecione a categoria do acessório.");
            String cor=painelAcessórios.getCor();
            return new PeçaAcessórios(codigo,nome,marca,preco,categoria,cor.isEmpty()?null:cor,painelAcessórios.isFunileiroProprio(),sobEncomendaCheckBox.isSelected());
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
            painelMecânica.setCategoriaMecânica(m.getCategoriaMecânica()); painelMecânica.setPrazoGarantia(m.getPrazoGarantia()); painelMecânica.setMecanicoProprio(m.isMecanicoProprio());
        } else if(p instanceof PeçaAcessórios) {
            PeçaAcessórios a=(PeçaAcessórios)p; especializaçãoTabbedPane.setSelectedComponent(painelAcessórios);
            painelAcessórios.setCategoriaAcessório(a.getCategoriaAcessório()); painelAcessórios.setCor(a.getCor()); painelAcessórios.setFunileiroProprio(a.isFunileiroProprio());
        }
    }
    private void remover() {
        Peça p=(Peça)peçasComboBox.getSelectedItem(); if(p==null) { erro("Selecione uma peça.");return; }
        String msg=controlador.removerPeças(p.getCodigo()); if(msg!=null) erro(msg); else { atualizarPeçasCadastradas(-1);limparCampos(); }
    }
    private void limparCampos() {
        codigoField.setText("");nomeField.setText("");marcaComboBox.setSelectedIndex(-1);precoField.setText("");
        sobEncomendaCheckBox.setSelected(false);
        painelMecânica.limparCampos();painelAcessórios.limparCampos();especializaçãoTabbedPane.setSelectedIndex(0);
    }
    private void erro(String msg) { JOptionPane.showMessageDialog(this,msg,"Erro",JOptionPane.ERROR_MESSAGE); }
}
