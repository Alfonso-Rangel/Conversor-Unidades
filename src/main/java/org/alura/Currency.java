package org.alura;

public enum Currency {
    MXN("Mexican Peso", "$"),
    USD("United States Dollar", "$"),
    EUR("Euro", "€"),
    GBP("British Pound Sterling", "£"),
    JPY("Japanese Yen", "¥"),
    AUD("Australian Dollar", "$"),
    CAD("Canadian Dollar", "$"),
    CHF("Swiss Franc", "CHF"),
    CNY("Chinese Yuan", "¥"),
    SEK("Swedish Krona", "kr"),
    NZD("New Zealand Dollar", "$");

    private final String name;
    private final String symbol;

    Currency(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public static String[] getNames() {
        String[] names = new String[Currency.values().length];
        int i = 0;
        for (Currency unit : Currency.values()) {
            names[i] = unit.getName();
            i++;
        }
        return names;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    static Currency getValueOption(String option) {
        return switch (option) {
            case "Mexican Peso" -> Currency.MXN;
            case "United States Dollar" -> Currency.USD;
            case "Euro" -> Currency.EUR;
            case "British Pound Sterling" -> Currency.GBP;
            case "Japanese Yen" -> Currency.JPY;
            case "Australian Dollar" -> Currency.AUD;
            case "Canadian Dollar" -> Currency.CAD;
            case "Swiss Franc" -> Currency.CHF;
            case "Chinese Yuan" -> Currency.CNY;
            case "Swedish Krona" -> Currency.SEK;
            case "New Zealand Dollar" -> Currency.NZD;
            default -> Currency.MXN;
        };
    }

    public static double convert(Currency unit1, Currency unit2, double quantity) {
        return 3.1416;
    }
}
