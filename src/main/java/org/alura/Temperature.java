package org.alura;

public enum Temperature implements Convertible<Temperature> {
    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit"),
    KELVIN("Kelvin");

    private final String name;

    Temperature(String name) {
        this.name = name;
    }

    public static String[] getNames() {
        String[] names = new String[Temperature.values().length];
        int i = 0;
        for (Temperature unit : Temperature.values()) {
            names[i] = unit.getName();
            i++;
        }
        return names;
    }

    public String getName() {
        return name;
    }

    public double convert(Temperature unit2, double quantity) {
        if (this == Temperature.CELSIUS && unit2 == Temperature.FAHRENHEIT) {
            return (quantity * 9/5) + 32;
        }
        else if (this == Temperature.CELSIUS && unit2 == Temperature.KELVIN) {
            return quantity + 273.15;
        }
        else if (this == Temperature.FAHRENHEIT && unit2 == Temperature.CELSIUS) {
            return (quantity - 32) * 5/9;
        }
        else if (this == Temperature.FAHRENHEIT && unit2 == Temperature.KELVIN) {
            return (quantity + 459.67) * 5/9;
        }
        else if (this == Temperature.KELVIN && unit2 == Temperature.CELSIUS) {
            return quantity - 273.15;
        }
        else if (this == Temperature.KELVIN && unit2 == Temperature.FAHRENHEIT) {
            return (quantity * 9/5) - 459.67;
        }
        else {
            return quantity;
        }
    }
}