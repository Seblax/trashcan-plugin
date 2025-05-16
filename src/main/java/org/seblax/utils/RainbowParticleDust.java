package org.seblax.utils;

import org.bukkit.*;
import org.bukkit.entity.Player;

public class RainbowParticleDust {
    final static Color[] rainbowColors = {
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

    public static void play(Player player, Particle particle){
        play(player,player.getLocation(),particle,1);
    }

        public static void play(Player player, Particle particle, float size){
            play(player,player.getLocation(),particle,size);
        }

        public static void play(Player player, Location l, Particle particle){
            play(player,l,particle,1f);
        }

        public static void play(Player player, Location l, Particle particle, float size){
            for(Color c: rainbowColors){
                player.spawnParticle(particle, l.getX(), l.getY() +1f, l.getZ(), 50, 1.0, 1.0, 1.0, 1.0, new Particle.DustOptions(c,size));
            }
        }
}
