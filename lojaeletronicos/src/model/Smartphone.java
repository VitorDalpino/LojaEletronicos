package model;

public class Smartphone extends Produto {
    private String processador;
    private int memoriaRAM;
    private int armazenamento;
    private String sistemaOperacional;

    public Smartphone(String nome, double preco, String marca, String processador,
                     int memoriaRAM, int armazenamento, String sistemaOperacional) {
        super(nome, preco, marca);
        setProcessador(processador);
        setMemoriaRAM(memoriaRAM);
        setArmazenamento(armazenamento);
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
    public String getTipo() {
        return "Smartphone";
    }

    @Override
    public String toString() {
        return String.format("%s - %s %s (%dGB RAM, %dGB) - %s",
            getTipo(), getMarca(), getNome(),
            memoriaRAM, armazenamento, sistemaOperacional);
    }

    @Override
    public String getDescricaoDetalhada() {
        return String.format("Smartphone %s %s - %dGB RAM, %dGB, %s, %s - R$ %.2f",
            getMarca(), getNome(), memoriaRAM, armazenamento, processador, sistemaOperacional, getPreco());
    }
}
