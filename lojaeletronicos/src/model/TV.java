package model;

public class TV extends Produto {
    private int polegadas;
    private String resolucao;
    private boolean smart;

    public TV(String nome, double preco, String marca, int polegadas, String resolucao, boolean smart) {
        super(nome, preco, marca);
        setPolegadas(polegadas);
        setResolucao(resolucao);
        this.smart = smart;
    }

    public int getPolegadas() {
        return polegadas;
    }

    public void setPolegadas(int polegadas) {
        if (polegadas <= 0) {
            throw new IllegalArgumentException("Polegadas deve ser maior que zero");
        }
        this.polegadas = polegadas;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        if (resolucao == null || resolucao.trim().isEmpty()) {
            throw new IllegalArgumentException("Resolução não pode ser vazia");
        }
        this.resolucao = resolucao.trim();
    }

    public boolean isSmart() {
        return smart;
    }

    public void setSmart(boolean smart) {
        this.smart = smart;
    }

    @Override
    public String getDescricaoDetalhada() {
        return String.format("TV %s %d\" - %s - %s - %s",
            getMarca(),
            polegadas,
            resolucao,
            smart ? "Smart TV" : "TV Convencional",
            String.format("R$ %.2f", getPreco())
        );
    }

    public String getTipo() {
        return "TV";
    }
}
