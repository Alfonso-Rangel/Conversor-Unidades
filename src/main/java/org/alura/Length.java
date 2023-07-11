package org.alura;

public enum Length {
    MILLIMETERS("Millimeters", 0.001),
    CENTIMETERS("Centimeters", 0.01),
    METERS("Meters", 1.0),
    KILOMETERS("Kilometers", 1000.0),
    INCHES("Inches", 0.0254),
    FEET("Feet", 0.3048),
    YARDS("Yards", 0.9144),
    MILES("Miles", 1609.34);

    private final String name;
    private final double value;

    Length(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    static Length getValueOption(String option) {
        return switch (option) {
            case "Millimeters" -> Length.MILLIMETERS;
            case "Centimeters" -> Length.CENTIMETERS;
            case "Meters" -> Length.METERS;
            case "Kilometers" -> Length.KILOMETERS;
            case "Inches" -> Length.INCHES;
            case "Feet" -> Length.FEET;
            case "Yards" -> Length.YARDS;
            case "Miles" -> Length.MILES;
            default -> Length.MILLIMETERS;
        };
    }
}