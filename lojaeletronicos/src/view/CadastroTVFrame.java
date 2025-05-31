package view;

import controller.LojaController;
import model.TV;

import javax.swing.*;
import java.awt.*;

public class CadastroTVFrame extends JFrame {
    private LojaController controller;
    private JTextField txtNome, txtPreco, txtMarca, txtPolegadas, txtResolucao;
    private JCheckBox cbSmart;

    public CadastroTVFrame(LojaController controller) {
        this.controller = controller;
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Cadastro de TV");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
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

        panel.add(new JLabel("Polegadas:"));
        txtPolegadas = new JTextField();
        panel.add(txtPolegadas);

        panel.add(new JLabel("Resolução:"));
        txtResolucao = new JTextField();
        panel.add(txtResolucao);

        panel.add(new JLabel("Smart TV:"));
        cbSmart = new JCheckBox("Sim");
        panel.add(cbSmart);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> salvarTV());
        btnCancelar.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void salvarTV() {
        try {
            String nome = txtNome.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            String marca = txtMarca.getText();
            int polegadas = Integer.parseInt(txtPolegadas.getText());
            String resolucao = txtResolucao.getText();
            boolean smart = cbSmart.isSelected();

            TV tv = new TV(nome, preco, marca, polegadas, resolucao, smart);
            controller.salvarProduto(tv);

            JOptionPane.showMessageDialog(this, "TV cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar TV: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
