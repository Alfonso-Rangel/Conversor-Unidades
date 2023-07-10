package org.alura;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JButton selectedButton;
    private final Color resaltado = Color.decode("#FF666F");
    private final Color resaltadoAcento = Color.decode("#333333");
    private final Color defecto = Color.decode("#293840");
    private final Color defectoAcento = Color.decode("#A4AABB");

    private final String[] devices = new String[]{"Dollar", "Euro", "Peso"};
    private final String[] lengthUnit; {
        lengthUnit = new String[Length.values().length];
        int i = 0;
        for (Length unit : Length.values()) {
            lengthUnit[i] = unit.getName();
            i++;
        }
    }

    private final String[] time = new String[]{"Seconds", "Minutes", "Days"};
    private final String[] tempeture = new String[]{"Centigrade", "Kelvin", "Fahrenheit"};
    private final String[] data = new String[]{"Bit", "Megabit", "Gigabit"};

    public MainFrame() {
        setTitle("Conversor de Unidades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        createSelectorMenu();
        selectDefaultButton(); // Llama al método para seleccionar el primer botón por defecto
        createConversorPanel(devices);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createSelectorMenu() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1, 0, 0));

        JLabel logoLabel = new JLabel();
        logoLabel.setPreferredSize(new Dimension(180, 100));
        menuPanel.add(logoLabel);

        JButton button1 = createButtonMenu("Moneda", "multimedia/monedas.png", devices);
        menuPanel.add(button1);

        JButton button2 = createButtonMenu("Tiempo", "multimedia/tiempo.png", time);
        menuPanel.add(button2);

        JButton button3 = createButtonMenu("Longitud", "multimedia/longitud.png", lengthUnit);
        menuPanel.add(button3);

        JButton button4 = createButtonMenu("Temperatura", "multimedia/temperatura.png", tempeture);
        menuPanel.add(button4);

        JButton button5 = createButtonMenu("Datos", "multimedia/datos.png", data);
        menuPanel.add(button5);

        add(menuPanel, BorderLayout.WEST);
    }

    private JButton createButtonMenu(String text, String imagePath, String[] unit) {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(imagePath));

        button.setVerticalTextPosition(SwingConstants.BOTTOM); // Alinea el texto debajo de la imagen
        button.setHorizontalTextPosition(SwingConstants.CENTER); // Alinea el texto en el centro horizontal
        button.setText(text);

        button.setPreferredSize(new Dimension(180, 100));

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.addActionListener(e -> {
            if (selectedButton != null) {
                selectedButton.setBackground(defecto);
                selectedButton.setForeground(defectoAcento);
            }
            button.setBackground(resaltado);
            button.setForeground(resaltadoAcento);
            selectedButton = button;

            // Actualizar la interfaz
            mainPanel.removeAll();
            createConversorPanel(unit);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        return button;
    }

    private void selectDefaultButton() {
        Component[] components = menuPanel.getComponents();

        if (components.length > 1 && components[1] instanceof JButton firstButton) {
            firstButton.setBackground(resaltado);
            firstButton.setForeground(resaltadoAcento);
            selectedButton = firstButton;
        }
    }

    private void createConversorPanel(String[] options) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 40));

        JPanel origenPanel = designConversorPanel("Valor de origen: ", new JTextField(), new JComboBox<>(options));
        JPanel destinoPanel = designConversorPanel("Valor destino: ", new JTextField(), new JComboBox<>(options));

        mainPanel.add(origenPanel);
        mainPanel.add(destinoPanel);

        JLabel infoLabel = new JLabel("PLACEHOLDER");
        mainPanel.add(infoLabel);

        // Agregar espacio vertical entre los paneles
        mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel designConversorPanel(String label, JTextField textField, JComboBox<String> comboBox) {
        JPanel panel = new JPanel(new BorderLayout());
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        comboBox.setPreferredSize(new Dimension(100, 35));
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(new JLabel(label), BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        panel.add(comboBox, BorderLayout.SOUTH);
        return panel;
    }
}