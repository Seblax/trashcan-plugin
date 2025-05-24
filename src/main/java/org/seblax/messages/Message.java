package org.seblax.messages;

import org.bukkit.ChatColor;
import org.seblax.utils.Triple;

import java.util.List;

/**
 * Represents a formatted chat message consisting of a prefix, suffix, and color.
 * Inherits from Triple to store three values.
 */
public class Message extends Triple<String, String, ChatColor> {

    // Constant placeholder for item names used in messages.
    public static final String ITEMS = String.format("%s%s Items %s", ChatColor.GOLD, ChatColor.BOLD, ChatColor.RESET);

    // Private constructor; use static factory methods instead.
    Message(String prefix, String suffix, ChatColor color) {
        super(prefix, suffix, color);
    }

    /**
     * Creates a new Message instance from raw values.
     *
     * @param prefix Text shown before the item name.
     * @param suffix Text shown after the item name.
     * @param color  Color applied to prefix and suffix.
     * @return A new Message object.
     */
    public static Message of(String prefix, String suffix, ChatColor color) {
        return new Message(prefix, suffix, color);
    }

    /**
     * Creates a new Message from a list of three elements: prefix, suffix, and color name.
     *
     * @param list A list containing [prefix, suffix, colorName].
     * @return A new Message object.
     * @throws IndexOutOfBoundsException or IllegalArgumentException if the list is invalid.
     */
    public static Message of(List<Object> list) {
        String prefix = list.get(0).toString();
        String suffix = list.get(1).toString();
        ChatColor color = ChatColor.valueOf(list.get(2).toString());

        return of(prefix, suffix, color);
    }

    /**
     * Converts this message to a list representation.
     *
     * @return A list of [prefix, suffix, colorName].
     */
    @Override
    public List<Object> toList() {
        return List.of(getPrefix(), getSufix(), getColor().name());
    }

    public String getPrefix() {
        return getFirst();
    }

    public String getSufix() {
        return getSecond();
    }

    public ChatColor getColor() {
        return getThird();
    }

    /**
     * Formats the message as a colored string to be displayed in chat.
     *
     * @return A formatted message string.
     */
    @Override
    public String toString() {
        return getColor() + getPrefix() +
                ITEMS +
                getColor() + getSufix();
    }
}