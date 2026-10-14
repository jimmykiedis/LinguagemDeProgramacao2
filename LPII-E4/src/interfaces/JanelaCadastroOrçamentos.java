package interfaces;

import controles.ControladorCadastroOrçamentos;
import entidades.Orçamento;
import entidades.Seguradora;
import entidades.Sinistro;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JanelaCadastroOrçamentos extends JFrame {
    private final ControladorCadastroOrçamentos controlador;
    private final JComboBox<Orçamento> orçamentos = new JComboBox<>();
    private final JComboBox<Sinistro> sinistros = new JComboBox<>();
    private final JComboBox<Seguradora> seguradoras = new JComboBox<>();
    private final JComboBox<Orçamento.Status> status = new JComboBox<>(Orçamento.Status.values());
    private final JTextField data = new JTextField(10);
    private final JTextField valorTotal = new JTextField(12);
    private final javax.swing.JCheckBox danosOcultos = new javax.swing.JCheckBox("Possui danos ocultos");
    private int idSinistroConsultado = -1;
    private String seguradoraConsultada;

    public JanelaCadastroOrçamentos(ControladorCadastroOrçamentos controlador) {
        this.controlador = controlador;
        setTitle("Cadastrar Orçamentos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(8, 8));

        sinistros.setModel(new DefaultComboBoxModel<>(Sinistro.getVisoes()));
        seguradoras.setModel(new DefaultComboBoxModel<>(Seguradora.getVisoes()));
        orçamentos.setModel(new DefaultComboBoxModel<>(Orçamento.getVisoes()));
        add(criarCampos(), BorderLayout.CENTER);
        add(criarComandos(), BorderLayout.SOUTH);
        atualizarLista(-1, null);
        limparCampos();
        pack();
        setSize(590, 460);
        JanelaUtil.posicionarNoCanto(this);
    }

    private JPanel criarCampos() {
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.anchor = GridBagConstraints.WEST;
        adicionarCampo(painel, c, 0, "Orçamentos cadastrados", orçamentos);
        adicionarCampo(painel, c, 1, "Sinistro", sinistros);
        adicionarCampo(painel, c, 2, "Seguradora", seguradoras);
        adicionarCampo(painel, c, 3, "Status", status);
        adicionarCampo(painel, c, 4, "Data (AAAA-MM-DD)", data);
        adicionarCampo(painel, c, 5, "Valor total (R$)", valorTotal);
        adicionarCampo(painel, c, 6, "Danos ocultos", danosOcultos);
        return painel;
    }

    private void adicionarCampo(JPanel painel, GridBagConstraints c, int linha,
            String rotulo, java.awt.Component campo) {
        c.gridx = 0; c.gridy = linha; c.fill = GridBagConstraints.NONE; c.weightx = 0;
        painel.add(new JLabel(rotulo), c);
        c.gridx = 1; painel.add(campo, c);
    }

    private JPanel criarComandos() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton inserir = new JButton("Inserir"), consultar = new JButton("Consultar");
        JButton alterar = new JButton("Alterar"), remover = new JButton("Remover"), limpar = new JButton("Limpar");
        inserir.addActionListener(e -> inserir());
        consultar.addActionListener(e -> consultar());
        alterar.addActionListener(e -> alterar());
        remover.addActionListener(e -> remover());
        limpar.addActionListener(e -> limparCampos());
        painel.add(inserir); painel.add(consultar); painel.add(alterar); painel.add(remover); painel.add(limpar);
        return painel;
    }

    private Orçamento obterInformado() {
        Sinistro sinistro = (Sinistro) sinistros.getSelectedItem();
        Seguradora seguradora = (Seguradora) this.seguradoras.getSelectedItem();
        Orçamento.Status statusSelecionado = (Orçamento.Status) status.getSelectedItem();
        if (sinistro == null || seguradora == null || statusSelecionado == null)
            throw new IllegalArgumentException("Selecione o sinistro, a seguradora e o status.");
        Date dataInformada = Date.valueOf(data.getText().trim());
        double valor = Double.parseDouble(valorTotal.getText().trim().replace(',', '.'));
        if (valor < 0) throw new IllegalArgumentException("O valor total não pode ser negativo.");
        return new Orçamento(statusSelecionado, dataInformada, sinistro, seguradora, valor, danosOcultos.isSelected());
    }

    private void inserir() {
        try {
            Orçamento o = obterInformado();
            String mensagem = controlador.inserirOrçamento(o);
            if (mensagem != null) { erro(mensagem); return; }
            atualizarLista(o.getSinistro().getId(), o.getSeguradora().getNome());
            limparCampos();
        } catch (IllegalArgumentException e) {
            erro("Informe uma data válida (AAAA-MM-DD), um valor válido e os campos obrigatórios.");
        }
    }

    private void consultar() {
        Orçamento visão = (Orçamento) orçamentos.getSelectedItem();
        if (visão == null) { erro("Selecione um orçamento."); return; }
        Orçamento o = Orçamento.buscarOrçamento(visão.getSinistro().getId(), visão.getSeguradora().getNome());
        if (o == null) { erro("Orçamento não cadastrado."); return; }
        idSinistroConsultado = o.getSinistro().getId();
        seguradoraConsultada = o.getSeguradora().getNome();
        selecionarSinistro(idSinistroConsultado);
        selecionarSeguradora(seguradoraConsultada);
        status.setSelectedItem(o.getStatus());
        data.setText(o.getData().toString());
        valorTotal.setText(String.valueOf(o.getValorTotal()));
        danosOcultos.setSelected(o.getPossuiDanosOcultos());
    }

    private void alterar() {
        if (idSinistroConsultado < 0 || seguradoraConsultada == null) {
            erro("Consulte um orçamento antes de alterá-lo."); return;
        }
        try {
            Orçamento o = obterInformado();
            String mensagem = controlador.alterarOrçamento(o, idSinistroConsultado, seguradoraConsultada);
            if (mensagem != null) { erro(mensagem); return; }
            atualizarLista(idSinistroConsultado, seguradoraConsultada);
            limparCampos();
        } catch (IllegalArgumentException e) {
            erro("Informe uma data válida (AAAA-MM-DD), um valor válido e os campos obrigatórios.");
        }
    }

    private void remover() {
        Orçamento visão = (Orçamento) orçamentos.getSelectedItem();
        if (visão == null) { erro("Selecione um orçamento."); return; }
        String mensagem = controlador.removerOrçamento(visão.getSinistro().getId(), visão.getSeguradora().getNome());
        if (mensagem != null) { erro(mensagem); return; }
        atualizarLista(-1, null);
        limparCampos();
    }

    private void atualizarLista(int idSinistroSelecionado, String seguradoraSelecionada) {
        orçamentos.setModel(new DefaultComboBoxModel<>(Orçamento.getVisoes()));
        if (idSinistroSelecionado >= 0 && seguradoraSelecionada != null) {
            for (int i = 0; i < orçamentos.getItemCount(); i++) {
                Orçamento atual = orçamentos.getItemAt(i);
                if (atual.getSinistro().getId() == idSinistroSelecionado
                        && atual.getSeguradora().getNome().equals(seguradoraSelecionada)) {
                    orçamentos.setSelectedIndex(i); return;
                }
            }
        }
        orçamentos.setSelectedIndex(-1);
    }

    private void selecionarSinistro(int id) {
        for (int i = 0; i < sinistros.getItemCount(); i++)
            if (sinistros.getItemAt(i).getId() == id) { sinistros.setSelectedIndex(i); return; }
    }

    private void selecionarSeguradora(String nome) {
        for (int i = 0; i < seguradoras.getItemCount(); i++)
            if (seguradoras.getItemAt(i).getNome().equals(nome)) { seguradoras.setSelectedIndex(i); return; }
    }

    private void limparCampos() {
        idSinistroConsultado = -1; seguradoraConsultada = null;
        sinistros.setSelectedIndex(-1); seguradoras.setSelectedIndex(-1); status.setSelectedIndex(-1);
        data.setText(""); valorTotal.setText(""); danosOcultos.setSelected(false);
    }

    private void erro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
