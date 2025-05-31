package model;

public class Laptop extends Produto {
    private String processador;
    private int memoriaRAM;
    private int armazenamento;
    private String tipoArmazenamento; // SSD ou HD
    private String sistemaOperacional;

    public Laptop(String nome, double preco, String marca, String processador,
                 int memoriaRAM, int armazenamento, String tipoArmazenamento,
                 String sistemaOperacional) {
        super(nome, preco, marca);
        setProcessador(processador);
        setMemoriaRAM(memoriaRAM);
        setArmazenamento(armazenamento);
        setTipoArmazenamento(tipoArmazenamento);
        setSistemaOperacional(sistemaOperacional);
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        if (processador == null || processador.trim().isEmpty()) {
            throw new IllegalArgumentException("Processador não pode ser vazio");
        }
        this.processador = processador.trim();
    }

    public int getMemoriaRAM() {
        return memoriaRAM;
    }

    public void setMemoriaRAM(int memoriaRAM) {
        if (memoriaRAM <= 0) {
            throw new IllegalArgumentException("Memória RAM deve ser maior que zero");
        }
        this.memoriaRAM = memoriaRAM;
    }

    public int getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(int armazenamento) {
        if (armazenamento <= 0) {
            throw new IllegalArgumentException("Armazenamento deve ser maior que zero");
        }
        this.armazenamento = armazenamento;
    }

    public String getTipoArmazenamento() {
        return tipoArmazenamento;
    }

    public void setTipoArmazenamento(String tipoArmazenamento) {
        if (tipoArmazenamento == null || tipoArmazenamento.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de armazenamento não pode ser vazio");
        }
        String tipo = tipoArmazenamento.trim().toUpperCase();
        if (!tipo.equals("SSD") && !tipo.equals("HD")) {
            throw new IllegalArgumentException("Tipo de armazenamento deve ser SSD ou HD");
        }
        this.tipoArmazenamento = tipo;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        if (sistemaOperacional == null || sistemaOperacional.trim().isEmpty()) {
            throw new IllegalArgumentException("Sistema Operacional não pode ser vazio");
        }
        this.sistemaOperacional = sistemaOperacional.trim();
    }

    @Override
    public String getDescricaoDetalhada() {
        return String.format("%s %s - %s - %dGB RAM - %dGB %s - %s - %s",
            getMarca(),
            getNome(),
            processador,
            memoriaRAM,
            armazenamento,
            tipoArmazenamento,
            sistemaOperacional,
            String.format("R$ %.2f", getPreco())
        );
    }

    public String getTipo() {
        return "Laptop";
    }
}
