package org.seblax.utils;

import org.bukkit.ChatColor;
import java.util.List;

/**
 * Utility class for parsing and formatting strings, especially for color formatting
 * and YAML configuration list conversions.
 */
public class Parser {

    /**
     * Converts a list of elements to a single string, attempting to interpret each element
     * as a ChatColor if possible.
     *
     * @param list The list of elements to convert.
     * @param <E>  The type of elements in the list.
     * @return A string representing the colored elements concatenated.
     */
    public static <E> String ymlToString(List<E> list) {
        if (list == null) return " ";
        StringBuilder res = new StringBuilder();

        for (Object o : list) {
            res.append(getColor(o.toString()));
        }

        return res.toString();
    }

    /**
     * Converts a string to its corresponding ChatColor if it's a valid color name,
     * otherwise returns the original string.
     *
     * @param s The string to convert.
     * @return The colored string if valid, otherwise the original string.
     */
    public static String getColor(String s) {
        try {
            return ChatColor.valueOf(s).toString();
        } catch (Exception e) {
            return s;
        }
    }

}