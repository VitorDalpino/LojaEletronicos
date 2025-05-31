package model;

public abstract class Produto {
    private Long id;
    private String nome;
    private double preco;
    private String marca;
    private int quantidadeEmEstoque;
    private String descricao;

    public Produto(String nome, double preco, String marca) {
        setNome(nome);
        setPreco(preco);
        setMarca(marca);
        this.quantidadeEmEstoque = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.nome = nome.trim();
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("Marca não pode ser vazia");
        }
        this.marca = marca.trim();
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa");
        }
        this.quantidadeEmEstoque = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void adicionarAoEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade a adicionar deve ser maior que zero");
        }
        this.quantidadeEmEstoque += quantidade;
    }

    public void removerDoEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade a remover deve ser maior que zero");
        }
        if (quantidade > this.quantidadeEmEstoque) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }
        this.quantidadeEmEstoque -= quantidade;
    }

    public boolean temEstoque() {
        return this.quantidadeEmEstoque > 0;
    }

    public abstract String getTipo();

    public abstract String getDescricaoDetalhada();

    @Override
    public String toString() {
        return String.format("%s - %s (%s) - R$ %.2f - Estoque: %d",
            getTipo(), nome, marca, preco, quantidadeEmEstoque);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id != null && id.equals(produto.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
