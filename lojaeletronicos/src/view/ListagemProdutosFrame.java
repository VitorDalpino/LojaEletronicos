package view;

import controller.LojaController;
import model.Produto;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListagemProdutosFrame extends JFrame {
    private final LojaController controller;
    private JTable tableProdutos;
    private DefaultTableModel tableModel;

    public ListagemProdutosFrame(LojaController controller) {
        this.controller = controller;
        configurarJanela();
        inicializarComponentes();
        carregarProdutos();
    }

    private void configurarJanela() {
        setTitle("Listagem de Produtos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializarComponentes() {
        // Configurar o modelo da tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Preço");
        tableModel.addColumn("Marca");
        tableModel.addColumn("Tipo");

        // Configurar a tabela
        tableProdutos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableProdutos);

        // Botões de ação
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnFechar = new JButton("Fechar");

        btnAtualizar.addActionListener(e -> carregarProdutos());
        btnExcluir.addActionListener(e -> excluirProdutoSelecionado());
        btnFechar.addActionListener(e -> dispose());

        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnExcluir);
        buttonPanel.add(btnFechar);

        // Adicionar componentes ao frame
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void carregarProdutos() {
        // Limpar a tabela
        tableModel.setRowCount(0);

        // Carregar produtos do controller
        List<Produto> produtos = controller.listarTodosProdutos();
        for (Produto produto : produtos) {
            tableModel.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getMarca(),
                produto.getClass().getSimpleName()
            });
        }
    }

    private void excluirProdutoSelecionado() {
        int selectedRow = tableProdutos.getSelectedRow();
        if (selectedRow >= 0) {
            Long id = (Long) tableModel.getValueAt(selectedRow, 0);
            Produto produto = controller.buscarProdutoPorId(id);

            if (produto != null) {
                int confirmacao = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja excluir este produto?",
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION);

                if (confirmacao == JOptionPane.YES_OPTION) {
                    controller.deletarProduto(produto);
                    carregarProdutos();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Selecione um produto para excluir!",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        }
    }
}
