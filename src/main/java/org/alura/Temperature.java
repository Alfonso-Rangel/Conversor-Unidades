package org.alura;

public enum Temperature {
    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit"),
    KELVIN("Kelvin");

    private final String name;

    Temperature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static Temperature getValueOption(String option) {
        return switch (option) {
            case "Celsius" -> Temperature.CELSIUS;
            case "Fahrenheit" -> Temperature.FAHRENHEIT;
            case "Kelvin" -> Temperature.KELVIN;
            default -> Temperature.CELSIUS;
        };
    }
}