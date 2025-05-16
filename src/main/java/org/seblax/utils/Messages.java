package org.seblax.utils;

import org.bukkit.ChatColor;

import java.util.Random;

public class Messages {
    public static final String ITEMS = String.format("%s%sItems",ChatColor.GOLD,ChatColor.BOLD);

    public static final String[] MESSAGES = {
            parser(ChatColor.GREEN,"Poof! Your", "vanished like magic."),
            parser(ChatColor.LIGHT_PURPLE,"Oops... your", "took a one-way trip to the void."),
            parser(ChatColor.BLUE,"Your", "have been... liberated."),
            parser(ChatColor.WHITE,"Those", "? Never heard of them."),
            parser(ChatColor.RED,"", "gone. Regrets incoming."),
            parser(ChatColor.DARK_PURPLE,"Your", "have been deleted. Don’t cry. (Okay, maybe a little.)"),
            parser(ChatColor.YELLOW,"","have left the chat."),
            parser(ChatColor.AQUA,"RIP", ": They lived fast, died young."),
            parser(ChatColor.DARK_GREEN,"Congratulations! You've successfully lost all your","!"),
            parser(ChatColor.DARK_RED,"Warning:", "may have been sacrificed to the inventory gods.")
    };

    public static String getRandomMessage(){
        String[] m = MESSAGES;
        int i = m.length;
        Random r = new Random();
        return m[r.nextInt(i)];
    }

    public static String parser(ChatColor color,String... message){
        return String.format("%s%s %s %s%s",color,message[0],ITEMS,color,message[1]);
    }
}
