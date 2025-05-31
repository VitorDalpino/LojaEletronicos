package view;

import controller.LojaController;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalFrame extends JFrame {
    private LojaController controller;

    public MenuPrincipalFrame(LojaController controller) {
        this.controller = controller;
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Loja de Eletrônicos - Menu Principal");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
        JButton btnListarClientes = new JButton("Listar Clientes");
        JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
        JButton btnListarProdutos = new JButton("Listar Produtos");
        JButton btnCarrinho = new JButton("Carrinho de Compras");
        JButton btnSair = new JButton("Sair");

        btnCadastrarCliente.addActionListener(e -> new CadastroClienteFrame(controller).setVisible(true));
        btnListarClientes.addActionListener(e -> new ListagemClientesFrame(controller).setVisible(true));
        btnCadastrarProduto.addActionListener(e -> new CadastroProdutoFrame(controller).setVisible(true));
        btnListarProdutos.addActionListener(e -> new ListagemProdutosFrame(controller).setVisible(true));
        btnCarrinho.addActionListener(e -> JOptionPane.showMessageDialog(this, "Funcionalidade do carrinho será implementada."));
        btnSair.addActionListener(e -> System.exit(0));

        panel.add(btnCadastrarCliente);
        panel.add(btnListarClientes);
        panel.add(btnCadastrarProduto);
        panel.add(btnListarProdutos);
        panel.add(btnCarrinho);
        panel.add(btnSair);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LojaController controller = new LojaController();
            new MenuPrincipalFrame(controller).setVisible(true);
        });
    }
}
