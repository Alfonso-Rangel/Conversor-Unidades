package org.alura;

public enum Length {
    MILLIMETERS("millimeters", 0.001),
    CENTIMETERS("centimeters", 0.01),
    METERS("meters", 1.0),
    KILOMETERS("kilometers", 1000.0),
    INCHES("inches", 0.0254),
    FEET("feet", 0.3048),
    YARDS("yards", 0.9144),
    MILES("miles", 1609.34);

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
}