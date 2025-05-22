package org.seblax.utils;

import org.bukkit.ChatColor;

import java.util.List;

public class Message extends Triple<String,String,ChatColor>{
    public static final String ITEMS = String.format("%s%s Items %s",ChatColor.GOLD,ChatColor.BOLD, ChatColor.RESET);

    Message(String prefix, String suffix, ChatColor color) {
        super(prefix, suffix, color);
    }

    public static Message of(String prefix, String suffix, ChatColor color){
        return  new Message(prefix,suffix,color);
    }

    public static Message of(List<Object> list){
        String prefix = list.get(0).toString();
        String suffix = list.get(1).toString();
        ChatColor color = ChatColor.valueOf(list.get(2).toString());

        return of(prefix, suffix, color);
    }

    @Override
    public List<Object> toList() {
        return List.of(getPrefix(), getSufix(), getColor().name());
    }

    public String getPrefix(){
        return getFirst();
    }

    public String getSufix(){
        return getSecond();
    }

    public ChatColor getColor(){
        return getThird();
    }

    @Override
    public String toString(){
        return getColor() + getPrefix() +
                ITEMS +
                getColor() + getSufix();
    }
}
