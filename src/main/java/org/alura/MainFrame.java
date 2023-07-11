package org.alura;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JButton selectedButton;
    private String mode = "Currency";
    private String unitSource = "";
    private String unitDestiny = "";
    private final Color resaltado = Color.decode("#FF666F");
    private final Color resaltadoAcento = Color.decode("#333333");
    private final Color defecto = Color.decode("#293840");
    private final Color defectoAcento = Color.decode("#A4AABB");
    private final String[] currency = Currency.getNames();
    private final String[] lengthUnit = Length.getNames();
    private final String[] time = Time.getNames();
    private final String[] temperature = Temperature.getNames();
    private final String[] data = Data.getNames();

    public MainFrame() {
        setTitle("Conversor de Unidades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        createSelectorMenu();
        selectDefaultButton();
        createConversorPanel(currency);

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

        JButton button1 = createButtonMenu("Currency", "multimedia/monedas.png", currency);
        menuPanel.add(button1);

        JButton button2 = createButtonMenu("Time", "multimedia/tiempo.png", time);
        menuPanel.add(button2);

        JButton button3 = createButtonMenu("Length", "multimedia/longitud.png", lengthUnit);
        menuPanel.add(button3);

        JButton button4 = createButtonMenu("Temperature", "multimedia/temperatura.png", temperature);
        menuPanel.add(button4);

        JButton button5 = createButtonMenu("Data", "multimedia/datos.png", data);
        menuPanel.add(button5);

        add(menuPanel, BorderLayout.WEST);
    }

    private JButton createButtonMenu(String text, String imagePath, String[] unit) {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(imagePath));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
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

            mainPanel.removeAll();
            createConversorPanel(unit);
            mainPanel.revalidate();
            mainPanel.repaint();
            mode = button.getText();
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

        JTextField origenTextField = new JTextField();
        JComboBox<String> origenComboBox = new JComboBox<>(options);
        JPanel origenPanel = designConversorPanel("Valor de origen: ", origenTextField, origenComboBox);

        JTextField destinoTextField = new JTextField();
        destinoTextField.setEditable(false);
        JComboBox<String> destinoComboBox = new JComboBox<>(options);
        JPanel destinoPanel = designConversorPanel("Valor destino: ", destinoTextField, destinoComboBox);

        origenComboBox.addActionListener(e -> {
            unitSource = (String) origenComboBox.getSelectedItem();
            updateDestinoTextField(origenTextField, destinoTextField);
        });

        destinoComboBox.addActionListener(e -> {
            unitDestiny = (String) destinoComboBox.getSelectedItem();
            updateDestinoTextField(origenTextField, destinoTextField);
        });

        origenTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateDestinoTextField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateDestinoTextField();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateDestinoTextField();
            }

            private void updateDestinoTextField() {
                SwingUtilities.invokeLater(() -> {
                    destinoTextField.setText(convertUnits(origenTextField.getText()));
                });
            }
        });

        mainPanel.add(origenPanel);
        mainPanel.add(destinoPanel);

        JLabel infoLabel = new JLabel("PLACEHOLDER");
        mainPanel.add(infoLabel);

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

    private void updateDestinoTextField(JTextField origenTextField, JTextField destinoTextField) {
        SwingUtilities.invokeLater(() -> {
            destinoTextField.setText(convertUnits(origenTextField.getText()));
        });
    }

    private String convertUnits(String input) {
        double value;
        try {
            value = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return input;
        }
        switch (mode) {
            case "Currency" -> {
                Currency unit1 = Currency.getValueOption(unitSource);
                Currency unit2 = Currency.getValueOption(unitDestiny);
                value = (Currency.convert(unit1, unit2, value));
            }
            case "Length" -> {
                Length unit1 = Length.getValueOption(unitSource);
                Length unit2 = Length.getValueOption(unitDestiny);
                value = (Length.convert(unit1, unit2, value));
            }
            case "Temperature" -> {
                Temperature unit1 = Temperature.getValueOption(unitSource);
                Temperature unit2 = Temperature.getValueOption(unitDestiny);
                value = (Temperature.convert(unit1, unit2, value));
            }
            case "Time" -> {
                Time unit1 = Time.getValueOption(unitSource);
                Time unit2 = Time.getValueOption(unitDestiny);
                value = (Time.convert(unit1, unit2, value));
            }
            case "Data" -> {
                Data unit1 = Data.getValueOption(unitSource);
                Data unit2 = Data.getValueOption(unitDestiny);
                value = (Data.convert(unit1, unit2, value));
            }
            default -> { return input; }
        }
        return String.valueOf(value);
    }
}
