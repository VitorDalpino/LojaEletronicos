package model;

import java.util.HashMap;
import java.util.Map;

public class CarrinhoDeCompras {
    private final Map<model.Produto, Integer> itens = new HashMap<>();

    public void adicionarProduto(model.Produto produto, int quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade inválida");
        itens.merge(produto, quantidade, Integer::sum);
    }

    public void removerProduto(model.Produto produto) {
        itens.remove(produto);
    }

    public Map<model.Produto, Integer> getItens() {
        return new HashMap<>(itens);
    }

    public double calcularTotal() {
        return itens.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPreco() * entry.getValue())
                .sum();
    }

    public void limparCarrinho() {
        itens.clear();
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }
}

