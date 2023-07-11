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
}