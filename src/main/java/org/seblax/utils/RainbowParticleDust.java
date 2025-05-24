package org.seblax.utils;

import org.bukkit.*;
import org.bukkit.entity.Player;

/**
 * Utility class for displaying rainbow-colored particle effects around players.
 * This class uses Bukkit's Particle.DustOptions to render a series of colored particles
 * that represent a rainbow.
 */
public class RainbowParticleDust {

    /**
     * Array of predefined Bukkit Colors used to simulate a rainbow.
     */
    private static final Color[] rainbowColors = {
            Color.RED,
            Color.BLUE,
            Color.AQUA,
            Color.BLACK,
            Color.FUCHSIA,
            Color.GREEN,
            Color.GRAY,
            Color.TEAL,
            Color.YELLOW,
            Color.WHITE,
            Color.MAROON,
            Color.LIME,
            Color.SILVER,
            Color.PURPLE,
            Color.NAVY,
            Color.OLIVE,
            Color.ORANGE
    };

    /**
     * Plays a rainbow particle effect at the player's location with default size.
     *
     * @param player   The player to show the particles to and get location from.
     * @param particle The type of particle to display (should support DustOptions).
     */
    public static void play(Player player, Particle particle) {
        play(player, player.getLocation(), particle, 1f);
    }

    /**
     * Plays a rainbow particle effect at the player's location with specified size.
     *
     * @param player   The player to show the particles to and get location from.
     * @param particle The type of particle to display.
     * @param size     The size of each particle.
     */
    public static void play(Player player, Particle particle, float size) {
        play(player, player.getLocation(), particle, size);
    }

    /**
     * Plays a rainbow particle effect at the specified location with default size.
     *
     * @param player   The player to show the particles to.
     * @param location The location where the particles will appear.
     * @param particle The type of particle to display.
     */
    public static void play(Player player, Location location, Particle particle) {
        play(player, location, particle, 1f);
    }

    /**
     * Plays a rainbow particle effect at the specified location and size.
     * Each color in the predefined rainbow color array will be used.
     *
     * @param player   The player to show the particles to.
     * @param location The location where the particles will appear.
     * @param particle The type of particle to display.
     * @param size     The size of each particle.
     */
    public static void play(Player player, Location location, Particle particle, float size) {
        for (Color color : rainbowColors) {
            player.spawnParticle(
                    particle,
                    location.getX(),
                    location.getY() + 1f, // Slight vertical offset for visibility
                    location.getZ(),
                    50,                   // Number of particles
                    1.0, 1.0, 1.0,        // Spread on X, Y, Z
                    1.0,                  // Extra data (speed or intensity)
                    new Particle.DustOptions(color, size)
            );
        }
    }
}