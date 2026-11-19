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
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class JanelaCadastroOrçamentos extends JFrame {
    private final ControladorCadastroOrçamentos controlador;
    private int id_sinistro_consultado = -1;
    private String seguradora_consultada;

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
        setLocationByPlatform(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints c = new java.awt.GridBagConstraints();
        orçamentos_label = new javax.swing.JLabel();
        sinistros_label = new javax.swing.JLabel();
        seguradoras_label = new javax.swing.JLabel();
        status_label = new javax.swing.JLabel();
        data_label = new javax.swing.JLabel();
        valor_total_label = new javax.swing.JLabel();
        danos_ocultos_label = new javax.swing.JLabel();
        orçamentos = new javax.swing.JComboBox<>();
        sinistros = new javax.swing.JComboBox<>();
        seguradoras = new javax.swing.JComboBox<>();
        status_button_group = new ButtonGroup();
        status_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        status_aprovado_radio_button = new JRadioButton(Orçamento.Status.aprovado.toString());
        status_pendente_radio_button = new JRadioButton(Orçamento.Status.pendente.toString());
        status_recusado_radio_button = new JRadioButton(Orçamento.Status.recusado.toString());
        status_button_group.add(status_aprovado_radio_button);
        status_button_group.add(status_pendente_radio_button);
        status_button_group.add(status_recusado_radio_button);
        status_panel.add(status_aprovado_radio_button);
        status_panel.add(status_pendente_radio_button);
        status_panel.add(status_recusado_radio_button);
        data = new javax.swing.JTextField(19);
        data.setEditable(false);
        valor_total = new javax.swing.JTextField(20);
        java.awt.Dimension tamanho_valor_total = valor_total.getPreferredSize();
        tamanho_valor_total.width = 220;
        valor_total.setPreferredSize(tamanho_valor_total);
        valor_total.setMinimumSize(tamanho_valor_total);
        danos_ocultos = new javax.swing.JCheckBox("Possui danos ocultos");
        comandos_panel = new javax.swing.JPanel();
        inserir_button = new javax.swing.JButton("Inserir");
        consultar_button = new javax.swing.JButton("Consultar");
        alterar_button = new javax.swing.JButton("Alterar");
        remover_button = new javax.swing.JButton("Remover");
        limpar_button = new javax.swing.JButton("Limpar");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Orçamentos");
        getContentPane().setLayout(new java.awt.BorderLayout(8, 8));
        getContentPane().setLayout(new java.awt.GridBagLayout());
        c.insets = new java.awt.Insets(4, 6, 4, 6);
        c.anchor = java.awt.GridBagConstraints.WEST;
        adicionarCampoGerado(getContentPane(), c, 0, orçamentos_label, "Orçamentos cadastrados", orçamentos);
        adicionarCampoGerado(getContentPane(), c, 1, sinistros_label, "Sinistro", sinistros);
        adicionarCampoGerado(getContentPane(), c, 2, seguradoras_label, "Seguradora", seguradoras);
        adicionarCampoGerado(getContentPane(), c, 3, status_label, "Status", status_panel);
        adicionarCampoGerado(getContentPane(), c, 4, data_label, "Data e hora", data);
        adicionarCampoGerado(getContentPane(), c, 5, valor_total_label, "Valor total (R$)", valor_total);
        adicionarCampoGerado(getContentPane(), c, 6, danos_ocultos_label, "Danos ocultos", danos_ocultos);

        comandos_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        comandos_panel.add(inserir_button);
        comandos_panel.add(consultar_button);
        comandos_panel.add(alterar_button);
        comandos_panel.add(remover_button);
        comandos_panel.add(limpar_button);
        c.gridx = 0; c.gridy = 7; c.gridwidth = 2; c.anchor = java.awt.GridBagConstraints.CENTER;
        getContentPane().add(comandos_panel, c);
        inserir_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { inserirOrçamento(evt); }
        });
        consultar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { consultarOrçamento(evt); }
        });
        alterar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { alterarOrçamento(evt); }
        });
        remover_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { removerOrçamento(evt); }
        });
        limpar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { limparCampos(evt); }
        });
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarCampoGerado(java.awt.Container painel, java.awt.GridBagConstraints c,
            int linha, javax.swing.JLabel rotulo, String texto, java.awt.Component campo) {
        adicionarCampoGerado(painel, c, linha, rotulo, texto, campo,
                campo != status_panel && campo != valor_total);
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
        Orçamento.Status status_selecionado = obterStatusSelecionado();
        if (sinistro == null || seguradora == null || status_selecionado == null)
            throw new IllegalArgumentException("Selecione o sinistro, a seguradora e o status.");
        Timestamp data_informada = new Timestamp(System.currentTimeMillis());
        data.setText(data_informada.toString());
        double valor = Double.parseDouble(valor_total.getText().trim().replace(',', '.'));
        if (valor < 0) throw new IllegalArgumentException("O valor total não pode ser negativo.");
        return new Orçamento(status_selecionado, data_informada, sinistro, seguradora, valor, danos_ocultos.isSelected());
    }

    private void inserirOrçamento(java.awt.event.ActionEvent evt) {
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

    private void consultarOrçamento(java.awt.event.ActionEvent evt) {
        Orçamento visão = (Orçamento) orçamentos.getSelectedItem();
        if (visão == null) { erro("Selecione um orçamento."); return; }
        Orçamento o = Orçamento.buscarOrçamento(visão.getSinistro().getId(), visão.getSeguradora().getNome());
        if (o == null) { erro("Orçamento não cadastrado."); return; }
        id_sinistro_consultado = o.getSinistro().getId();
        seguradora_consultada = o.getSeguradora().getNome();
        selecionarSinistro(id_sinistro_consultado);
        selecionarSeguradora(seguradora_consultada);
        definirStatusSelecionado(o.getStatus());
        data.setText(o.getData().toString());
        valor_total.setText(String.valueOf(o.getValorTotal()));
        danos_ocultos.setSelected(o.getPossuiDanosOcultos());
    }

    private void alterarOrçamento(java.awt.event.ActionEvent evt) {
        if (id_sinistro_consultado < 0 || seguradora_consultada == null) {
            erro("Consulte um orçamento antes de alterá-lo."); return;
        }
        try {
            Orçamento o = obterInformado();
            String mensagem = controlador.alterarOrçamento(o, id_sinistro_consultado, seguradora_consultada);
            if (mensagem != null) { erro(mensagem); return; }
            atualizarLista(id_sinistro_consultado, seguradora_consultada);
            limparCampos();
        } catch (IllegalArgumentException e) {
            erro("Informe um valor válido e preencha os campos obrigatórios.");
        }
    }

    private void removerOrçamento(java.awt.event.ActionEvent evt) {
        Orçamento visão = (Orçamento) orçamentos.getSelectedItem();
        if (visão == null) { erro("Selecione um orçamento."); return; }
        String mensagem = controlador.removerOrçamento(visão.getSinistro().getId(), visão.getSeguradora().getNome());
        if (mensagem != null) { erro(mensagem); return; }
        atualizarLista(-1, null);
        limparCampos();
    }

    private void atualizarLista(int id_sinistro_selecionado, String seguradora_selecionada) {
        orçamentos.setModel(new DefaultComboBoxModel<>(Orçamento.getVisoes()));
        if (id_sinistro_selecionado >= 0 && seguradora_selecionada != null) {
            for (int i = 0; i < orçamentos.getItemCount(); i++) {
                Orçamento atual = orçamentos.getItemAt(i);
                if (atual.getSinistro().getId() == id_sinistro_selecionado
                        && atual.getSeguradora().getNome().equals(seguradora_selecionada)) {
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

    private void limparCampos(java.awt.event.ActionEvent evt) {
        limparCampos();
    }

    private void limparCampos() {
        id_sinistro_consultado = -1; seguradora_consultada = null;
        sinistros.setSelectedIndex(-1); seguradoras.setSelectedIndex(-1); status_button_group.clearSelection();
        data.setText(new Timestamp(System.currentTimeMillis()).toString());
        valor_total.setText(""); danos_ocultos.setSelected(false);
    }

    private Orçamento.Status obterStatusSelecionado() {
        if (status_aprovado_radio_button.isSelected()) return Orçamento.Status.aprovado;
        if (status_pendente_radio_button.isSelected()) return Orçamento.Status.pendente;
        if (status_recusado_radio_button.isSelected()) return Orçamento.Status.recusado;
        return null;
    }

    private void definirStatusSelecionado(Orçamento.Status valor) {
        status_button_group.clearSelection();
        if (valor == Orçamento.Status.aprovado) status_aprovado_radio_button.setSelected(true);
        else if (valor == Orçamento.Status.pendente) status_pendente_radio_button.setSelected(true);
        else if (valor == Orçamento.Status.recusado) status_recusado_radio_button.setSelected(true);
    }

    private void erro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel comandos_panel;
    private javax.swing.JLabel danos_ocultos_label;
    private javax.swing.JLabel data_label;
    private javax.swing.JButton inserir_button;
    private javax.swing.JButton consultar_button;
    private javax.swing.JButton alterar_button;
    private javax.swing.JButton remover_button;
    private javax.swing.JButton limpar_button;
    private javax.swing.JLabel orçamentos_label;
    private javax.swing.JLabel seguradoras_label;
    private javax.swing.JLabel sinistros_label;
    private javax.swing.JLabel status_label;
    private ButtonGroup status_button_group;
    private JPanel status_panel;
    private JRadioButton status_aprovado_radio_button;
    private JRadioButton status_pendente_radio_button;
    private JRadioButton status_recusado_radio_button;
    private javax.swing.JLabel valor_total_label;
    private javax.swing.JComboBox<Orçamento> orçamentos;
    private javax.swing.JComboBox<Sinistro> sinistros;
    private javax.swing.JComboBox<Seguradora> seguradoras;
    private javax.swing.JTextField data;
    private javax.swing.JTextField valor_total;
    private javax.swing.JCheckBox danos_ocultos;
    // End of variables declaration//GEN-END:variables
}
