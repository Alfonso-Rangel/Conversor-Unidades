package org.alura;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JButton selectedButton;
    private String mode = "Currency";
    private String unitSource = "";
    private String unitDestiny = "";
    private final Color highlightedColor = Color.decode("#FF666F");
    private final Color highlightedAccentColor = Color.decode("#333333");
    private final Color defaultColor = Color.decode("#293840");
    private final Color defaultAccentColor = Color.decode("#A4AABB");
    private final String[] currency = Currency.getNames();
    private final String[] lengthUnit = Length.getNames();
    private final String[] time = Time.getNames();
    private final String[] temperature = Temperature.getNames();
    private final String[] data = Data.getNames();

    public MainFrame() {
        setTitle("Unit Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        createSelectorMenu();
        selectDefaultButton();
        createConverterPanel(currency);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void createSelectorMenu() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1, 0, 0));

        JLabel logoLabel = new JLabel();
        logoLabel.setPreferredSize(new Dimension(180, 100));
        logoLabel.setIcon(new ImageIcon("multimedia/aluraoracle.png"));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 75, 0, 0));
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
                selectedButton.setBackground(defaultColor);
                selectedButton.setForeground(defaultAccentColor);
            }
            button.setBackground(highlightedColor);
            button.setForeground(highlightedAccentColor);
            selectedButton = button;

            mainPanel.removeAll();
            createConverterPanel(unit);
            mainPanel.revalidate();
            mainPanel.repaint();
            mode = button.getText();
        });

        return button;
    }

    private void selectDefaultButton() {
        Component[] components = menuPanel.getComponents();

        if (components.length > 1 && components[1] instanceof JButton firstButton) {
            firstButton.setBackground(highlightedColor);
            firstButton.setForeground(highlightedAccentColor);
            selectedButton = firstButton;
        }
    }

    private void createConverterPanel(String[] options) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 40));

        JTextField sourceTextField = new JTextField();
        JComboBox<String> sourceComboBox = new JComboBox<>(options);
        JPanel sourcePanel = designConverterPanel("Source Value: ", sourceTextField, sourceComboBox);

        JTextField destinationTextField = new JTextField();
        destinationTextField.setEditable(false);
        JComboBox<String> destinationComboBox = new JComboBox<>(options);
        JPanel destinationPanel = designConverterPanel("Destination Value: ", destinationTextField, destinationComboBox);

        unitSource = (String) sourceComboBox.getSelectedItem();
        unitDestiny = (String) destinationComboBox.getSelectedItem();

        sourceComboBox.addActionListener(e -> {
            unitSource = (String) sourceComboBox.getSelectedItem();
            updateDestinationTextField(sourceTextField, destinationTextField);
        });

        destinationComboBox.addActionListener(e -> {
            unitDestiny = (String) destinationComboBox.getSelectedItem();
            updateDestinationTextField(sourceTextField, destinationTextField);
        });

        sourceTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateDestinationTextField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateDestinationTextField();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateDestinationTextField();
            }

            private void updateDestinationTextField() {
                SwingUtilities.invokeLater(() -> {
                    try {
                        destinationTextField.setText(convertUnits(sourceTextField.getText()));
                    } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException ignored) {
                    }
                });
            }
        });

        mainPanel.add(sourcePanel);
        mainPanel.add(destinationPanel);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 70, 100));

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel designConverterPanel(String label, JTextField textField, JComboBox<String> comboBox) {
        JPanel panel = new JPanel(new BorderLayout());
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        comboBox.setPreferredSize(new Dimension(100, 35));
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(new JLabel(label), BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        panel.add(comboBox, BorderLayout.SOUTH);
        return panel;
    }

    private void updateDestinationTextField(JTextField sourceTextField, JTextField destinationTextField) {
        SwingUtilities.invokeLater(() -> {
            try {
                destinationTextField.setText(convertUnits(sourceTextField.getText()));
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException ignored) {
            }
        });
    }

    private String convertUnits(String input) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        switch (mode) {
            case "Currency" -> {
                return convertUnits(input, Currency.class);
            }
            case "Length" -> {
                return convertUnits(input, Length.class);
            }
            case "Temperature" -> {
                return convertUnits(input, Temperature.class);
            }
            case "Time" -> {
                return convertUnits(input, Time.class);
            }
            case "Data" -> {
                return convertUnits(input, Data.class);
            }
            default -> {
                return input;
            }
        }
    }

    private <T extends Enum<T> & Convertible<T>> String convertUnits(String input, Class<T> enumClass) {
        double value;
        try {
            value = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return input;
        }

        T unit1 = Enum.valueOf(enumClass, unitSource.toUpperCase());
        T unit2 = Enum.valueOf(enumClass, unitDestiny.toUpperCase());

        value = unit1.convert(unit2, value);

        return String.valueOf(value);
    }
}
