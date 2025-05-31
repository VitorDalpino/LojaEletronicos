package main;

import controller.LojaController;
import view.MenuPrincipalFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Define o look and feel do sistema
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                LojaController controller = new LojaController();
                MenuPrincipalFrame mainFrame = new MenuPrincipalFrame(controller);
                mainFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                    "Erro ao iniciar o sistema: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
