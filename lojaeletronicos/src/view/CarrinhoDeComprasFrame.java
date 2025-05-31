package view;

import controller.LojaController;
import model.CarrinhoDeCompras;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CarrinhoDeComprasFrame extends JFrame {
    private final LojaController controller;
    private final CarrinhoDeCompras carrinho;
    private JTable tabelaItens;
    private DefaultTableModel tableModel;

    public CarrinhoDeComprasFrame(LojaController controller, CarrinhoDeCompras carrinho) {
        this.controller = controller;
        this.carrinho = carrinho;
        configurarJanela();
        inicializarComponentes();
        carregarTabela();
    }

    private void configurarJanela() {
        setTitle("Carrinho de Compras");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializarComponentes() {
        tableModel = new DefaultTableModel(new Object[]{"Produto", "Preço", "Quantidade", "Subtotal"}, 0);
        tabelaItens = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaItens);

        JButton btnAdicionar = new JButton("Adicionar Produto");
        JButton btnRemover = new JButton("Remover Produto");
        JButton btnFinalizar = new JButton("Finalizar Compra");

        btnAdicionar.addActionListener(e -> abrirDialogoAdicionarProduto());
        btnRemover.addActionListener(e -> removerProdutoSelecionado());
        btnFinalizar.addActionListener(e -> finalizarCompra());

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoes.add(btnAdicionar);
        botoes.add(btnRemover);
        botoes.add(btnFinalizar);

        add(scrollPane, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);
    }

    private void carregarTabela() {
        tableModel.setRowCount(0);
        for (var entry : carrinho.getItens().entrySet()) {
            Produto p = entry.getKey();
            int qtd = entry.getValue();
            double subtotal = p.getPreco() * qtd;
            tableModel.addRow(new Object[]{p.getNome(), p.getPreco(), qtd, subtotal});
        }
    }

    private void abrirDialogoAdicionarProduto() {
        List<Produto> produtos = controller.listarTodosProdutos();
        Produto selecionado = (Produto) JOptionPane.showInputDialog(this,
                "Selecione um produto:",
                "Adicionar ao Carrinho",
                JOptionPane.PLAIN_MESSAGE,
                null,
                produtos.toArray(),
                null);

        if (selecionado != null) {
            String input = JOptionPane.showInputDialog(this, "Quantidade:", "1");
            try {
                int quantidade = Integer.parseInt(input);
                carrinho.adicionarProduto(selecionado, quantidade);
                carregarTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removerProdutoSelecionado() {
        int row = tabelaItens.getSelectedRow();
        if (row >= 0) {
            String nomeProduto = (String) tabelaItens.getValueAt(row, 0);
            Produto paraRemover = controller.listarTodosProdutos().stream()
                    .filter(p -> p.getNome().equals(nomeProduto))
                    .findFirst().orElse(null);
            if (paraRemover != null) {
                carrinho.removerProduto(paraRemover);
                carregarTabela();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void finalizarCompra() {
        if (carrinho.estaVazio()) {
            JOptionPane.showMessageDialog(this, "Carrinho está vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            double total = carrinho.calcularTotal();
            JOptionPane.showMessageDialog(this,
                    String.format("Compra finalizada! Total: R$ %.2f", total),
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            carrinho.limparCarrinho();
            carregarTabela();
        }
    }
}
