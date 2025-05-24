package org.seblax.utils;

import java.util.List;

/**
 * A generic utility class representing a triple (3-tuple) of values.
 * This is useful for grouping three related objects together without creating a dedicated class.
 *
 * @param <E> Type of the first element.
 * @param <F> Type of the second element.
 * @param <G> Type of the third element.
 */
public class Triple<E, F, G> {
    private final E x;
    private final F y;
    private final G z;

    /**
     * Protected constructor for internal use.
     * Use {@link #of(Object, Object, Object)} to instantiate.
     *
     * @param x First element.
     * @param y Second element.
     * @param z Third element.
     */
    protected Triple(E x, F y, G z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Factory method to create a new Triple instance.
     *
     * @param x   First element.
     * @param y   Second element.
     * @param z   Third element.
     * @param <E> Type of the first element.
     * @param <F> Type of the second element.
     * @param <G> Type of the third element.
     * @return A new Triple containing the given values.
     */
    public static <E, F, G> Triple<E, F, G> of(E x, F y, G z) {
        return new Triple<>(x, y, z);
    }

    /**
     * Returns the first element of the triple.
     *
     * @return The first element.
     */
    public E getFirst() {
        return x;
    }

    /**
     * Returns the second element of the triple.
     *
     * @return The second element.
     */
    public F getSecond() {
        return y;
    }

    /**
     * Returns the third element of the triple.
     *
     * @return The third element.
     */
    public G getThird() {
        return z;
    }

    /**
     * Converts the triple into a List of Objects containing all three elements.
     *
     * @return A List with the three elements in order.
     */
    public List<Object> toList() {
        return List.of(x, y, z);
    }

    /**
     * Returns a string representation of the Triple.
     *
     * @return A string in the format [first, second, third].
     */
    @Override
    public String toString() {
        return "[" +
                getFirst() + ", " +
                getSecond() + ", " +
                getThird() + "]";
    }
}