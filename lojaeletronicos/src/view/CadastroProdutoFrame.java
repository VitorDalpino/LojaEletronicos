package view;

import controller.LojaController;

import javax.swing.*;
import java.awt.*;

public class CadastroProdutoFrame extends JFrame {
    private LojaController controller;
    private JComboBox<String> cbTipo;

    public CadastroProdutoFrame(LojaController controller) {
        this.controller = controller;
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Cadastro de Produto");
        setSize(350, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Tipo de Produto:"));
        cbTipo = new JComboBox<>(new String[]{"Laptop", "Smartphone", "TV"});
        panel.add(cbTipo);

        JButton btnAvancar = new JButton("Avançar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAvancar.addActionListener(e -> abrirTelaEspecifica());
        btnCancelar.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnAvancar);
        buttonPanel.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void abrirTelaEspecifica() {
        String tipoSelecionado = (String) cbTipo.getSelectedItem();
        switch (tipoSelecionado) {
            case "Laptop":
                new CadastroLaptopFrame(controller).setVisible(true);
                break;
            case "Smartphone":
                new CadastroSmartphoneFrame(controller).setVisible(true);
                break;
            case "TV":
                new CadastroTVFrame(controller).setVisible(true);
                break;
        }
        dispose();
    }
}