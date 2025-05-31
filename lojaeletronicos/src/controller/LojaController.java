package controller;

import model.Cliente;
import model.Produto;
import dao.ClienteDAO;
import dao.ProdutoDAO;
import java.util.List;
import java.util.stream.Collectors;

public class LojaController {
    private final ClienteDAO clienteDAO;
    private final ProdutoDAO produtoDAO;

    public LojaController() {
        this.clienteDAO = new ClienteDAO();
        this.produtoDAO = new ProdutoDAO();
    }

    // Métodos para Cliente
    public void salvarCliente(Cliente cliente) {
        clienteDAO.salvar(cliente);
    }

    public void atualizarCliente(Cliente cliente) {
        clienteDAO.atualizar(cliente);
    }

    public void deletarCliente(Cliente cliente) {
        clienteDAO.deletar(cliente);
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteDAO.buscarPorId(id);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteDAO.listarTodos();
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarTodos();
    }

    public void excluirCliente(Long id) {
        clienteDAO.excluir(id);
    }

    // Métodos para Produto
    public void salvarProduto(Produto produto) {
        produtoDAO.salvar(produto);
    }

    public void atualizarProduto(Produto produto) {
        produtoDAO.atualizar(produto);
    }

    public void deletarProduto(Produto produto) {
        produtoDAO.deletar(produto);
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoDAO.buscarPorId(id);
    }

    public List<Produto> listarTodosProdutos() {
        return produtoDAO.listarTodos();
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listarTodos();
    }

    public void excluirProduto(Long id) {
        produtoDAO.excluir(id);
    }

    // Métodos de gerenciamento de estoque
    public void atualizarEstoque(Long produtoId, int quantidade) {
        Produto produto = produtoDAO.buscarPorId(produtoId);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + quantidade);
        produtoDAO.salvar(produto);
    }

    // Métodos de busca especializada
    public List<Produto> buscarProdutosPorTipo(Class<?> tipo) {
        return produtoDAO.listarTodos().stream()
            .filter(p -> tipo.isInstance(p))
            .collect(Collectors.toList());
    }

    public List<Produto> buscarProdutosPorFaixaDePreco(double minimo, double maximo) {
        return produtoDAO.listarTodos().stream()
            .filter(p -> p.getPreco() >= minimo && p.getPreco() <= maximo)
            .collect(Collectors.toList());
    }
}
