package org.seblax.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.seblax.ui.SlotUI;

import java.util.List;

public class Parser {
    public static <E> String ymlToString(List<E> list){
        if(list == null) return " ";
        StringBuilder res = new StringBuilder();

        for(Object o: list){
            res.append(getColor(o.toString()));
        }

        return res.toString();
    }

    public static String getColor(String s){
        try{
            return ChatColor.valueOf(s).toString();
        }catch (Exception e){
            return s;
        }
    }

}
