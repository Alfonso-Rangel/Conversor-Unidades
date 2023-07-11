package org.alura;

public enum Currency  implements Convertible<Currency> {
    MXN("MXN","Mexican Peso", "$", 17),
    USD("USD", "United States Dollar", "$", 1),
    EUR("EUR", "Euro", "€", 0.9),
    GBP("GBP", "British Pound Sterling", "£", 0.78),
    JPY("JPY", "Japanese Yen", "¥", 140.75),
    CAD("CAD", "Canadian Dollar", "$", 1.33),
    CNY("CNY", "Chinese Yuan", "¥", 7.21);

    private final String name;
    private final String fullname;
    private final String symbol;
    private final double value;

    Currency(String name, String fullname, String symbol, double value) {
        this.name = name;
        this.fullname = fullname;
        this.symbol = symbol;
        this.value = value;
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

    public String getFullname() {
        return fullname;
    }

    public String getSymbol() {
        return symbol;
    }

    public double convert(Currency unit2, double quantity) {
        double valueInDollars = quantity / this.value;
        return valueInDollars * unit2.value;
    }

}