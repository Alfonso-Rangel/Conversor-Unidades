package org.alura;

public class Conversor {
    public static double convert(Currency unit1, Currency unit2, double quantity) {
        return 3.1416;
    }

    public static double convert(Length unit1, Length unit2, double quantity) {
        double valueInMeters = quantity * unit1.getValue();
        return valueInMeters / unit2.getValue();
    }

    public static double convert(Temperature unit1, Temperature unit2, double quantity) {
        if (unit1 == Temperature.CELSIUS && unit2 == Temperature.FAHRENHEIT) {
            return (quantity * 9/5) + 32;
        }
        else if (unit1 == Temperature.CELSIUS && unit2 == Temperature.KELVIN) {
            return quantity + 273.15;
        }
        else if (unit1 == Temperature.FAHRENHEIT && unit2 == Temperature.CELSIUS) {
            return (quantity - 32) * 5/9;
        }
        else if (unit1 == Temperature.FAHRENHEIT && unit2 == Temperature.KELVIN) {
            return (quantity + 459.67) * 5/9;
        }
        else if (unit1 == Temperature.KELVIN && unit2 == Temperature.CELSIUS) {
            return quantity - 273.15;
        }
        else if (unit1 == Temperature.KELVIN && unit2 == Temperature.FAHRENHEIT) {
            return (quantity * 9/5) - 459.67;
        }
        else {
            return quantity;
        }
    }

    public static double convert(Time unit1, Time unit2, double quantity) {
        double valueInSeconds = quantity * unit1.getValue();
        return valueInSeconds / unit2.getValue();
    }

    public static double convert(Data unit1, Data unit2, double quantity) {
        double valueInBits = quantity * unit1.getValue();
        return valueInBits / unit2.getValue();
    }
}
