package interfaces;

import controles.ControladorCadastroOrçamentos;
import entidades.Orçamento;
import entidades.Seguradora;
import entidades.Sinistro;
import java.awt.FlowLayout;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JanelaCadastroOrçamentos extends javax.swing.JFrame {
    ControladorCadastroOrçamentos controlador;
    DefaultListModel modelo_lista_orçamentos;
    Sinistro[] sinistros_cadastrados;
    Seguradora[] seguradoras_cadastradas;

    public JanelaCadastroOrçamentos(ControladorCadastroOrçamentos controlador) {
        this.controlador = controlador;
        sinistros_cadastrados = Sinistro.getVisoes();
        seguradoras_cadastradas = Seguradora.getVisoes();
        initComponents();
        modelo_lista_orçamentos = (DefaultListModel) orçamentos_cadastradosList.getModel();
        inicializarListaOrçamentos();
        limparCampos(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints c = new java.awt.GridBagConstraints();
        orçamentos_label = new javax.swing.JLabel();
        sinistros_label = new javax.swing.JLabel();
        seguradoras_label = new javax.swing.JLabel();
        status_label = new javax.swing.JLabel();
        sequencial_label = new javax.swing.JLabel();
        data_label = new javax.swing.JLabel();
        valor_total_label = new javax.swing.JLabel();
        danos_ocultos_label = new javax.swing.JLabel();
        orçamentos_cadastradosList = new JList<>(new DefaultListModel());
        orçamentos_cadastradosList.setVisibleRowCount(4);
        orçamentos_scroll_pane = new JScrollPane(orçamentos_cadastradosList);
        orçamentos_scroll_pane.setPreferredSize(new java.awt.Dimension(330, 80));
        sinistros = new javax.swing.JComboBox<>();
        seguradoras = new javax.swing.JComboBox<>();
        status_combo_box = new JComboBox<>(Orçamento.Status.values());
        data = new javax.swing.JTextField(19);
        data.setEditable(false);
        sequencial = new javax.swing.JTextField(6);
        sequencial.setEditable(false);
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
        getContentPane().setLayout(new java.awt.GridBagLayout());
        c.insets = new java.awt.Insets(4, 6, 4, 6);
        c.anchor = java.awt.GridBagConstraints.WEST;
        adicionarCampoGerado(getContentPane(), c, 0, orçamentos_label, "Orçamentos cadastrados", orçamentos_scroll_pane);
        adicionarCampoGerado(getContentPane(), c, 1, sequencial_label, "Identificador sequencial", sequencial, false);
        adicionarCampoGerado(getContentPane(), c, 2, sinistros_label, "Sinistro", sinistros);
        adicionarCampoGerado(getContentPane(), c, 3, seguradoras_label, "Seguradora", seguradoras);
        adicionarCampoGerado(getContentPane(), c, 4, status_label, "Status", status_combo_box);
        adicionarCampoGerado(getContentPane(), c, 5, valor_total_label, "Valor total (R$)", valor_total);
        adicionarCampoGerado(getContentPane(), c, 6, danos_ocultos_label, "Danos ocultos", danos_ocultos);
        adicionarCampoGerado(getContentPane(), c, 7, data_label, "Data e hora", data);

        comandos_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        comandos_panel.add(inserir_button);
        comandos_panel.add(consultar_button);
        comandos_panel.add(alterar_button);
        comandos_panel.add(remover_button);
        comandos_panel.add(limpar_button);
        c.gridx = 0; c.gridy = 8; c.gridwidth = 2; c.anchor = java.awt.GridBagConstraints.CENTER;
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
        setResizable(false);
        pack();
        setSize(590, 500);
        setLocationByPlatform(true);
    }// </editor-fold>//GEN-END:initComponents

    private void inicializarListaOrçamentos() {
        for (Sinistro sinistro : sinistros_cadastrados) sinistros.addItem(sinistro);
        for (Seguradora seguradora : seguradoras_cadastradas) seguradoras.addItem(seguradora);
        Orçamento[] visões = Orçamento.getVisoes();
        for (Orçamento visão : visões) modelo_lista_orçamentos.addElement(visão);
    }

    private void adicionarCampoGerado(java.awt.Container painel, java.awt.GridBagConstraints c,
            int linha, javax.swing.JLabel rotulo, String texto, java.awt.Component campo) {
        adicionarCampoGerado(painel, c, linha, rotulo, texto, campo,
                campo != status_combo_box && campo != valor_total);
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
        String sequencial_str = sequencial.getText();
        int sequencial_informado = 0;
        try {
            if (!sequencial_str.isEmpty()) sequencial_informado = Integer.parseInt(sequencial_str);
        } catch (NumberFormatException excecao) {
            return null;
        }
        Sinistro sinistro = (Sinistro) sinistros.getSelectedItem();
        if (sinistro == null) return null;
        Seguradora seguradora = (Seguradora) this.seguradoras.getSelectedItem();
        if (seguradora == null) return null;
        Orçamento.Status status_selecionado = obterStatusSelecionado();
        if (status_selecionado == null) return null;
        double valor;
        try {
            valor = Double.parseDouble(valor_total.getText().trim().replace(',', '.'));
        } catch (NumberFormatException excecao) {
            return null;
        }
        if (valor < 0) return null;
        Timestamp data_informada = new Timestamp(Calendar.getInstance().getTimeInMillis());
        return new Orçamento(sequencial_informado, status_selecionado, data_informada,
                sinistro, seguradora, valor, danos_ocultos.isSelected());
    }

    private void inserirOrçamento(java.awt.event.ActionEvent evt) {
        Orçamento orçamento = obterInformado();
        String mensagem_erro = null;
        if (orçamento != null) mensagem_erro = controlador.inserirOrçamento(orçamento);
        else mensagem_erro = "Algum atributo do orçamento não foi informado corretamente";
        if (mensagem_erro == null) {
            int ultimo_sequencial = Orçamento.últimoSequencial();
            orçamento.setSequencial(ultimo_sequencial);
            modelo_lista_orçamentos.addElement(orçamento.getVisao());
            orçamentos_cadastradosList.setSelectedIndex(modelo_lista_orçamentos.size() - 1);
            sequencial.setText(String.valueOf(ultimo_sequencial));
            data.setText(orçamento.getData().toString());
        } else erro(mensagem_erro);
    }

    private void consultarOrçamento(java.awt.event.ActionEvent evt) {
        Orçamento visão_orçamento = (Orçamento) orçamentos_cadastradosList.getSelectedValue();
        Orçamento orçamento = null;
        String mensagem_erro = null;
        if (visão_orçamento != null) {
            orçamento = Orçamento.buscarOrçamento(visão_orçamento.getSequencial());
            if (orçamento == null) mensagem_erro = "Orçamento não cadastrado";
        } else mensagem_erro = "Nenhum orçamento selecionado";
        if (mensagem_erro == null) {
            selecionarSinistro(orçamento.getSinistro().getId());
            selecionarSeguradora(orçamento.getSeguradora().getNome());
            definirStatusSelecionado(orçamento.getStatus());
            sequencial.setText(String.valueOf(orçamento.getSequencial()));
            data.setText(orçamento.getData().toString());
            valor_total.setText(String.valueOf(orçamento.getValorTotal()));
            danos_ocultos.setSelected(orçamento.getPossuiDanosOcultos());
        } else erro(mensagem_erro);
    }

    private void alterarOrçamento(java.awt.event.ActionEvent evt) {
        Orçamento orçamento = obterInformado();
        String mensagem_erro = null;
        if (orçamento != null) mensagem_erro = alterarOrçamentoEAtualizarLista(orçamento);
        else mensagem_erro = "Algum atributo do orçamento não foi informado corretamente";
        if (mensagem_erro != null) erro(mensagem_erro);
    }

    private String alterarOrçamentoEAtualizarLista(Orçamento orçamento) {
        String mensagem_erro = controlador.alterarOrçamento(orçamento);
        if (mensagem_erro == null) {
            atualizarLista(orçamento.getSinistro().getId(), orçamento.getSeguradora().getNome());
        }
        return mensagem_erro;
    }

    private void removerOrçamento(java.awt.event.ActionEvent evt) {
        Orçamento visão = (Orçamento) orçamentos_cadastradosList.getSelectedValue();
        if (visão == null) { erro("Selecione um orçamento."); return; }
        String mensagem = controlador.removerOrçamento(visão.getSequencial());
        if (mensagem != null) { erro(mensagem); return; }
        atualizarLista(-1, null);
        limparCampos(null);
    }

    private void atualizarLista(int id_sinistro_selecionado, String seguradora_selecionada) {
        modelo_lista_orçamentos.clear();
        Orçamento[] visões = Orçamento.getVisoes();
        for (Orçamento visão : visões) modelo_lista_orçamentos.addElement(visão);
        if (id_sinistro_selecionado >= 0 && seguradora_selecionada != null) {
            for (int i = 0; i < modelo_lista_orçamentos.size(); i++) {
                Orçamento atual = (Orçamento) modelo_lista_orçamentos.getElementAt(i);
                if (atual.getSinistro().getId() == id_sinistro_selecionado
                        && atual.getSeguradora().getNome().equals(seguradora_selecionada)) {
                    orçamentos_cadastradosList.setSelectedIndex(i); return;
                }
            }
        }
        orçamentos_cadastradosList.setSelectedIndex(-1);
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
        sequencial.setText("");
        sinistros.setSelectedIndex(-1);
        seguradoras.setSelectedIndex(-1);
        status_combo_box.setSelectedIndex(-1);
        data.setText("");
        valor_total.setText("");
        danos_ocultos.setSelected(false);
    }

    private Orçamento.Status obterStatusSelecionado() {
        return (Orçamento.Status) status_combo_box.getSelectedItem();
    }

    private void definirStatusSelecionado(Orçamento.Status valor) {
        status_combo_box.setSelectedItem(valor);
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
    private JScrollPane orçamentos_scroll_pane;
    private javax.swing.JLabel seguradoras_label;
    private javax.swing.JLabel sinistros_label;
    private javax.swing.JLabel status_label;
    private javax.swing.JLabel sequencial_label;
    private JComboBox<Orçamento.Status> status_combo_box;
    private javax.swing.JLabel valor_total_label;
    private JList<Orçamento> orçamentos_cadastradosList;
    private javax.swing.JComboBox<Sinistro> sinistros;
    private javax.swing.JComboBox<Seguradora> seguradoras;
    private javax.swing.JTextField data;
    private javax.swing.JTextField sequencial;
    private javax.swing.JTextField valor_total;
    private javax.swing.JCheckBox danos_ocultos;
    // End of variables declaration//GEN-END:variables
}
