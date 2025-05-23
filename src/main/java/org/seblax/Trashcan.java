package org.seblax;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.seblax.ui.ConfigurationUI;
import org.seblax.utils.DataFile;
import org.seblax.messages.Messages;

import java.util.Objects;

public final class Trashcan extends JavaPlugin {
    final DataFile CONFIGURATION = DataFile.builder("configuration", this);
    public static Messages MESSAGES;
    public static ConfigurationUI UI_CONFIGURATION;


    static final String[] ACTIVATION_MESSAGE = {
            "=================================================",
            "\tTrash Plugin Activated Properly",
            "================================================="
    };


    @Override
    public void onEnable() {
        MESSAGES = Messages.builder(this);
        UI_CONFIGURATION = ConfigurationUI.builder(this);

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
