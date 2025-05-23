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

public class Head {
    private static PlayerProfile getPlayerURL(String message){
        PlayerProfile playerProfile = Bukkit.createPlayerProfile(new UUID(0, 0));
        URL skin = null;

        try {
            skin = new URL("https://textures.minecraft.net/texture/" + message);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al formar la URL de la skin:" + e);
        }

        playerProfile.getTextures().setSkin(skin);

        return playerProfile;
    }

    static public ItemStack getPlayerHeadByURL(String url){
        return getPlayerHeadByURL(url,"");
    }

    static public ItemStack getPlayerHeadByURL(String url,String name){
        return getPlayerHeadByURL(url,name, List.of());
    }
    static public ItemStack getPlayerHeadByURL(String url,String name, List<String> lore){
        String[] arrayLore = new String[lore.size()];

        lore.forEach( x -> arrayLore[lore.indexOf(x)] = x);
        return getPlayerHeadByURL(url,name, arrayLore);
    }

    static public ItemStack getPlayerHeadByURL(String url, String name, String[] lore){
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) playerHead.getItemMeta();

        meta.setOwnerProfile(getPlayerURL(url));
        meta.setDisplayName(name);
        meta.setLore(Arrays.stream(lore).toList());

        playerHead.setItemMeta(meta);

        return playerHead;
    }
}
