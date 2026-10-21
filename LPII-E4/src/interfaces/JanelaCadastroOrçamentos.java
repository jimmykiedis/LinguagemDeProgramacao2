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
import java.sql.Timestamp;
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
    private int idSinistroConsultado = -1;
    private String seguradoraConsultada;

    public JanelaCadastroOrçamentos(ControladorCadastroOrçamentos controlador) {
        this.controlador = controlador;
        initComponents();
        setTitle("Cadastrar Orçamentos");
        setResizable(false);

        sinistros.setModel(new DefaultComboBoxModel<>(Sinistro.getVisoes()));
        seguradoras.setModel(new DefaultComboBoxModel<>(Seguradora.getVisoes()));
        orçamentos.setModel(new DefaultComboBoxModel<>(Orçamento.getVisoes()));
        atualizarLista(-1, null);
        limparCampos();
        pack();
        setSize(590, 460);
        JanelaUtil.posicionarNoCanto(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints c = new java.awt.GridBagConstraints();
        orçamentosLabel = new javax.swing.JLabel();
        sinistrosLabel = new javax.swing.JLabel();
        seguradorasLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        dataLabel = new javax.swing.JLabel();
        valorTotalLabel = new javax.swing.JLabel();
        danosOcultosLabel = new javax.swing.JLabel();
        orçamentos = new javax.swing.JComboBox<>();
        sinistros = new javax.swing.JComboBox<>();
        seguradoras = new javax.swing.JComboBox<>();
        status = new javax.swing.JComboBox<>(Orçamento.Status.values());
        data = new javax.swing.JTextField(19);
        data.setEditable(false);
        valorTotal = new javax.swing.JTextField(20);
        java.awt.Dimension tamanhoValorTotal = valorTotal.getPreferredSize();
        tamanhoValorTotal.width = 220;
        valorTotal.setPreferredSize(tamanhoValorTotal);
        valorTotal.setMinimumSize(tamanhoValorTotal);
        danosOcultos = new javax.swing.JCheckBox("Possui danos ocultos");
        comandosPanel = new javax.swing.JPanel();
        inserirButton = new javax.swing.JButton("Inserir");
        consultarButton = new javax.swing.JButton("Consultar");
        alterarButton = new javax.swing.JButton("Alterar");
        removerButton = new javax.swing.JButton("Remover");
        limparButton = new javax.swing.JButton("Limpar");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Orçamentos");
        getContentPane().setLayout(new java.awt.BorderLayout(8, 8));
        getContentPane().setLayout(new java.awt.GridBagLayout());
        c.insets = new java.awt.Insets(4, 6, 4, 6);
        c.anchor = java.awt.GridBagConstraints.WEST;
        adicionarCampoGerado(getContentPane(), c, 0, orçamentosLabel, "Orçamentos cadastrados", orçamentos);
        adicionarCampoGerado(getContentPane(), c, 1, sinistrosLabel, "Sinistro", sinistros);
        adicionarCampoGerado(getContentPane(), c, 2, seguradorasLabel, "Seguradora", seguradoras);
        adicionarCampoGerado(getContentPane(), c, 3, statusLabel, "Status", status);
        adicionarCampoGerado(getContentPane(), c, 4, dataLabel, "Data e hora", data);
        adicionarCampoGerado(getContentPane(), c, 5, valorTotalLabel, "Valor total (R$)", valorTotal);
        adicionarCampoGerado(getContentPane(), c, 6, danosOcultosLabel, "Danos ocultos", danosOcultos);

        comandosPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        comandosPanel.add(inserirButton);
        comandosPanel.add(consultarButton);
        comandosPanel.add(alterarButton);
        comandosPanel.add(removerButton);
        comandosPanel.add(limparButton);
        c.gridx = 0; c.gridy = 7; c.gridwidth = 2; c.anchor = java.awt.GridBagConstraints.CENTER;
        getContentPane().add(comandosPanel, c);
        inserirButton.addActionListener(e -> inserir());
        consultarButton.addActionListener(e -> consultar());
        alterarButton.addActionListener(e -> alterar());
        removerButton.addActionListener(e -> remover());
        limparButton.addActionListener(e -> limparCampos());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarCampoGerado(java.awt.Container painel, java.awt.GridBagConstraints c,
            int linha, javax.swing.JLabel rotulo, String texto, java.awt.Component campo) {
        adicionarCampoGerado(painel, c, linha, rotulo, texto, campo,
                campo != status && campo != valorTotal);
    }

    private void adicionarCampoGerado(java.awt.Container painel, java.awt.GridBagConstraints c,
            int linha, javax.swing.JLabel rotulo, String texto, java.awt.Component campo,
            boolean expandir) {
        rotulo.setText(texto);
        c.gridx = 0; c.gridy = linha; c.fill = java.awt.GridBagConstraints.NONE; c.weightx = 0;
        painel.add(rotulo, c);
        // Mantém os campos estáveis quando um novo modelo é carregado ou limpo.
        c.gridx = 1;
        c.fill = expandir ? java.awt.GridBagConstraints.HORIZONTAL : java.awt.GridBagConstraints.NONE;
        c.weightx = expandir ? 1.0 : 0;
        painel.add(campo, c);
    }

    private Orçamento obterInformado() {
        Sinistro sinistro = (Sinistro) sinistros.getSelectedItem();
        Seguradora seguradora = (Seguradora) this.seguradoras.getSelectedItem();
        Orçamento.Status statusSelecionado = (Orçamento.Status) status.getSelectedItem();
        if (sinistro == null || seguradora == null || statusSelecionado == null)
            throw new IllegalArgumentException("Selecione o sinistro, a seguradora e o status.");
        Timestamp dataInformada = new Timestamp(System.currentTimeMillis());
        data.setText(dataInformada.toString());
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
            erro("Informe um valor válido e preencha os campos obrigatórios.");
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
            erro("Informe um valor válido e preencha os campos obrigatórios.");
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
        data.setText(new Timestamp(System.currentTimeMillis()).toString());
        valorTotal.setText(""); danosOcultos.setSelected(false);
    }

    private void erro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel comandosPanel;
    private javax.swing.JLabel danosOcultosLabel;
    private javax.swing.JLabel dataLabel;
    private javax.swing.JButton inserirButton;
    private javax.swing.JButton consultarButton;
    private javax.swing.JButton alterarButton;
    private javax.swing.JButton removerButton;
    private javax.swing.JButton limparButton;
    private javax.swing.JLabel orçamentosLabel;
    private javax.swing.JLabel seguradorasLabel;
    private javax.swing.JLabel sinistrosLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel valorTotalLabel;
    private javax.swing.JComboBox<Orçamento> orçamentos;
    private javax.swing.JComboBox<Sinistro> sinistros;
    private javax.swing.JComboBox<Seguradora> seguradoras;
    private javax.swing.JComboBox<Orçamento.Status> status;
    private javax.swing.JTextField data;
    private javax.swing.JTextField valorTotal;
    private javax.swing.JCheckBox danosOcultos;
    // End of variables declaration//GEN-END:variables
}
