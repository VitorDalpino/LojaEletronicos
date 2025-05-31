package view;

import controller.LojaController;
import model.Cliente;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListagemClientesFrame extends JFrame {
    private LojaController controller;
    private JTable tableClientes;
    private DefaultTableModel tableModel;

    public ListagemClientesFrame(LojaController controller) {
        this.controller = controller;
        configurarJanela();
        inicializarComponentes();
        carregarClientes();
    }

    private void configurarJanela() {
        setTitle("Listagem de Clientes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializarComponentes() {
        // Configurar o modelo da tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Email");
        tableModel.addColumn("Telefone");

        // Configurar a tabela
        tableClientes = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableClientes);

        // Botões de ação
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnFechar = new JButton("Fechar");

        btnAtualizar.addActionListener(e -> carregarClientes());
        btnExcluir.addActionListener(e -> excluirClienteSelecionado());
        btnFechar.addActionListener(e -> dispose());

        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnExcluir);
        buttonPanel.add(btnFechar);

        // Adicionar componentes ao frame
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void carregarClientes() {
        // Limpar a tabela
        tableModel.setRowCount(0);

        // Carregar clientes do controller
        List<Cliente> clientes = controller.listarTodosClientes();
        for (Cliente cliente : clientes) {
            tableModel.addRow(new Object[]{
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone()
            });
        }
    }

    private void excluirClienteSelecionado() {
        int selectedRow = tableClientes.getSelectedRow();
        if (selectedRow >= 0) {
            Long id = (Long) tableModel.getValueAt(selectedRow, 0);
            Cliente cliente = controller.buscarClientePorId(id);

            if (cliente != null) {
                int confirmacao = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja excluir este cliente?",
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION);

                if (confirmacao == JOptionPane.YES_OPTION) {
                    controller.deletarCliente(cliente);
                    carregarClientes();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Selecione um cliente para excluir!",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        }
    }
}
