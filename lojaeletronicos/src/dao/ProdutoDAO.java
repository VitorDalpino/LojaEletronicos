package dao;

import model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private static List<Produto> produtos = new ArrayList<>();
    private static Long nextId = 1L;

    public void salvar(Produto produto) {
        if (produto.getId() == null) {
            produto.setId(nextId++);
        }
        produtos.add(produto);
    }

    public void atualizar(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId().equals(produto.getId())) {
                produtos.set(i, produto);
                break;
            }
        }
    }

    public void deletar(Produto produto) {
        produtos.removeIf(p -> p.getId().equals(produto.getId()));
    }

    public Produto buscarPorId(Long id) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    public void excluir(Long id) {
        Produto produto = buscarPorId(id);
        if (produto != null) {
            deletar(produto);
        } else {
            throw new IllegalArgumentException("Produto não encontrado para exclusão");
        }
    }
}
