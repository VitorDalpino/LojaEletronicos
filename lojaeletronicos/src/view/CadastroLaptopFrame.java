package view;

import controller.LojaController;
import model.Laptop;

import javax.swing.*;
import java.awt.*;

public class CadastroLaptopFrame extends JFrame {
    private LojaController controller;
    private JTextField txtNome, txtPreco, txtMarca, txtProcessador, txtRAM, txtArmazenamento, txtTipoArmazenamento, txtSistema;

    public CadastroLaptopFrame(LojaController controller) {
        this.controller = controller;
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Cadastro de Laptop");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panel.add(txtNome);

        panel.add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        panel.add(txtPreco);

        panel.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        panel.add(txtMarca);

        panel.add(new JLabel("Processador:"));
        txtProcessador = new JTextField();
        panel.add(txtProcessador);

        panel.add(new JLabel("Memória RAM (GB):"));
        txtRAM = new JTextField();
        panel.add(txtRAM);

        panel.add(new JLabel("Armazenamento (GB):"));
        txtArmazenamento = new JTextField();
        panel.add(txtArmazenamento);

        panel.add(new JLabel("Tipo de Armazenamento (SSD/HD):"));
        txtTipoArmazenamento = new JTextField();
        panel.add(txtTipoArmazenamento);

        panel.add(new JLabel("Sistema Operacional:"));
        txtSistema = new JTextField();
        panel.add(txtSistema);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> salvarLaptop());
        btnCancelar.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void salvarLaptop() {
        try {
            String nome = txtNome.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            String marca = txtMarca.getText();
            String processador = txtProcessador.getText();
            int ram = Integer.parseInt(txtRAM.getText());
            int armazenamento = Integer.parseInt(txtArmazenamento.getText());
            String tipoArmazenamento = txtTipoArmazenamento.getText();
            String sistema = txtSistema.getText();

            Laptop laptop = new Laptop(nome, preco, marca, processador, ram, armazenamento, tipoArmazenamento, sistema);
            controller.salvarProduto(laptop);

            JOptionPane.showMessageDialog(this, "Laptop cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar laptop: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

