package org.seblax.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Utility class to create custom player heads using texture URLs.
 */
public class Head {

    /**
     * Creates a dummy PlayerProfile with a custom skin URL.
     *
     * @param textureId The texture ID part of the URL (from Minecraft texture servers).
     * @return A PlayerProfile with the skin applied.
     */
    private static PlayerProfile getPlayerURL(String textureId) {
        PlayerProfile playerProfile = Bukkit.createPlayerProfile(new UUID(0, 0));
        URL skin;

        try {
            skin = new URL("https://textures.minecraft.net/texture/" + textureId);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to create skin URL: " + e);
        }

        playerProfile.getTextures().setSkin(skin);
        return playerProfile;
    }

    /**
     * Returns a custom player head with a texture and no name or lore.
     */
    public static ItemStack getPlayerHeadByURL(String url) {
        return getPlayerHeadByURL(url, "");
    }

    /**
     * Returns a custom player head with a texture and display name.
     */
    public static ItemStack getPlayerHeadByURL(String url, String name) {
        return getPlayerHeadByURL(url, name, List.of());
    }

    /**
     * Returns a custom player head with a texture, display name, and lore list.
     */
    public static ItemStack getPlayerHeadByURL(String url, String name, List<String> lore) {
//        String[] arrayLore = new String[lore.size()];
//        lore.forEach(x -> arrayLore[lore.indexOf(x)] = x);
        String[] arrayLore = new String[lore.size()];
        return getPlayerHeadByURL(url, name, arrayLore);
    }

    /**
     * Returns a custom player head with a texture, display name, and lore array.
     */
    public static ItemStack getPlayerHeadByURL(String url, String name, String[] lore) {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) playerHead.getItemMeta();

        meta.setOwnerProfile(getPlayerURL(url));
        meta.setDisplayName(name);
        meta.setLore(Arrays.stream(lore).toList());

        playerHead.setItemMeta(meta);
        return playerHead;
    }
}