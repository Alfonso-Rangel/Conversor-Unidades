package org.alura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JButton selectedButton;
    private final Color resaltado = Color.decode("#FF666F");
    private final Color resaltadoAcento = Color.decode("#333333");
    private final Color defecto = Color.decode("#293840");
    private final Color defectoAcento = Color.decode("#A4AABB");

    public MainFrame() {
        setTitle("Conversor de Unidades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        createMenuPanel();
        createMainPanel();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        selectDefaultButton(); // Llama al método para seleccionar el primer botón por defecto
    }

    private void createMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1, 0, 0));
        //menuPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        JLabel logoLabel = new JLabel();
        logoLabel.setPreferredSize(new Dimension(180, 100));
        menuPanel.add(logoLabel);

        JButton button1 = createMenuButton("Moneda", "multimedia/monedas.png");
        menuPanel.add(button1);

        JButton button2 = createMenuButton("Tiempo", "ruta/imagen2.png");
        menuPanel.add(button2);

        JButton button3 = createMenuButton("Longitud", "ruta/imagen3.png");
        menuPanel.add(button3);

        JButton button4 = createMenuButton("Temperatura", "ruta/imagen4.png");
        menuPanel.add(button4);

        JButton button5 = createMenuButton("Datos", "ruta/imagen5.png");
        menuPanel.add(button5);

        add(menuPanel, BorderLayout.WEST);
    }

    private JButton createMenuButton(String text, String imagePath) {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(imagePath));

        button.setVerticalTextPosition(SwingConstants.BOTTOM); // Alinea el texto debajo de la imagen
        button.setHorizontalTextPosition(SwingConstants.CENTER); // Alinea el texto en el centro horizontal
        button.setText(text);

        button.setPreferredSize(new Dimension(180, 100));

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedButton != null) {
                    selectedButton.setBackground(defecto);
                    selectedButton.setForeground(defectoAcento);
                }

                button.setBackground(resaltado);
                button.setForeground(resaltadoAcento);
                selectedButton = button;
            }
        });

        return button;
    }

    private void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        add(mainPanel, BorderLayout.CENTER);
    }

    private void selectDefaultButton() {
        Component[] components = menuPanel.getComponents();

        if (components.length > 1 && components[1] instanceof JButton) {
            JButton firstButton = (JButton) components[1];
            firstButton.setBackground(resaltado);
            firstButton.setForeground(resaltadoAcento);
            selectedButton = firstButton;
        }
    }
}
