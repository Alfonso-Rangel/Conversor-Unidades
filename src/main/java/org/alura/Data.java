package org.alura;

public enum Data {
    BIT("Bit", 1),
    BYTE("Byte", 8),
    KILOBYTE("Kilobyte", 8 * 1024),
    MEGABYTE("Megabyte", 8 * 1024 * 1024),
    GIGABYTE("Gigabyte", 8L * 1024 * 1024 * 1024);

    private final String name;
    private final long value;

    Data(String name, long value) {
        this.name = name;
        this.value = value;
    }

    public static String[] getNames() {
        String[] names = new String[Data.values().length];
        int i = 0;
        for (Data unit : Data.values()) {
            names[i] = unit.getName();
            i++;
        }
        return names;
    }

    public String getName() {
        return name;
    }

    public long getValue() {
        return value;
    }

    static Data getValueOption(String option) {
        return switch (option) {
            case "Bit" -> Data.BIT;
            case "Byte" -> Data.BYTE;
            case "Kilobyte" -> Data.KILOBYTE;
            case "Megabyte" -> Data.MEGABYTE;
            case "Gigabyte" -> Data.GIGABYTE;
            default -> Data.BIT;
        };
    }

    public static double convert(Data unit1, Data unit2, double quantity) {
        double valueInBits = quantity * unit1.getValue();
        return valueInBits / unit2.getValue();
    }
}
