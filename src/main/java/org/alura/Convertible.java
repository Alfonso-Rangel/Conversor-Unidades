package org.alura;
public interface Convertible<T> {
    double convert(T targetUnit, double value);

    String getName();
}
