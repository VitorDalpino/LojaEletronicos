package view;

import controller.LojaController;
import model.Smartphone;

import javax.swing.*;
import java.awt.*;

public class CadastroSmartphoneFrame extends JFrame {
    private LojaController controller;
    private JTextField txtNome, txtPreco, txtMarca, txtProcessador, txtRAM, txtArmazenamento, txtSistema;

    public CadastroSmartphoneFrame(LojaController controller) {
        this.controller = controller;
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Cadastro de Smartphone");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
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

        panel.add(new JLabel("Sistema Operacional:"));
        txtSistema = new JTextField();
        panel.add(txtSistema);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> salvarSmartphone());
        btnCancelar.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void salvarSmartphone() {
        try {
            String nome = txtNome.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            String marca = txtMarca.getText();
            String processador = txtProcessador.getText();
            int ram = Integer.parseInt(txtRAM.getText());
            int armazenamento = Integer.parseInt(txtArmazenamento.getText());
            String sistema = txtSistema.getText();

            Smartphone smartphone = new Smartphone(nome, preco, marca, processador, ram, armazenamento, sistema);
            controller.salvarProduto(smartphone);

            JOptionPane.showMessageDialog(this, "Smartphone cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar smartphone: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

