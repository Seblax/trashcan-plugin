package org.seblax;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Trashcan extends JavaPlugin {
    static final String[] ACTIVATION_MESSAGE = {
            "###############################################%s",
            "\tTrash Plugin Activated Properly%s",
            "###############################################%s"
    };

    @Override
    public void onEnable() {
        for (String m: ACTIVATION_MESSAGE){
            this.getLogger().info(m);
        }

        Bukkit.getPluginManager().registerEvents(new TrashListener(), this);

        Objects.requireNonNull(getCommand("trash")).setExecutor(new Trash());
        Objects.requireNonNull(getCommand("trashcan")).setExecutor(new Trash());
        Objects.requireNonNull(getCommand("basura")).setExecutor(new Trash());
        Objects.requireNonNull(getCommand("mierda")).setExecutor(new Trash());
        Objects.requireNonNull(getCommand("shit")).setExecutor(new Trash());
    }
}
