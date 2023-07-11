package org.alura;

public enum Time {
    SECONDS("Seconds", 1),
    MINUTES("Minutes", 60),
    HOURS("Hours", 3600),
    DAYS("Days", 86400),
    WEEKS("Weeks", 604800),
    MONTHS("Months", 2629800),
    YEARS("Years", 31557600);

    private final String name;
    private final int value;

    Time(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String[] getNames() {
        String[] names = new String[Time.values().length];
        int i = 0;
        for (Time unit : Time.values()) {
            names[i] = unit.getName();
            i++;
        }
        return names;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    static Time getValueOption(String option) {
        return switch (option) {
            case "Seconds" -> Time.SECONDS;
            case "Minutes" -> Time.MINUTES;
            case "Hours" -> Time.HOURS;
            case "Days" -> Time.DAYS;
            case "Weeks" -> Time.WEEKS;
            case "Months" -> Time.MONTHS;
            case "Years" -> Time.YEARS;
            default -> Time.SECONDS;
        };
    }

    static double convert(Time unit1, Time unit2, double quantity) {
        double valueInSeconds = quantity * unit1.getValue();
        return valueInSeconds / unit2.getValue();
    }
}