package org.seblax;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.seblax.ui.ConfigurationUI;
import org.seblax.utils.DataFile;
import org.seblax.messages.Messages;

import java.util.Objects;

/**
 * Main plugin class for the Trashcan plugin.
 * Manages plugin lifecycle, configuration, and command registration.
 */
public final class Trashcan extends JavaPlugin {

    /**
     * Configuration data file handler, loaded on plugin start.
     */
    final DataFile CONFIGURATION = DataFile.builder("configuration", this);

    /**
     * Static reference to the messages manager for localization or messages.
     */
    public static Messages MESSAGES;

    /**
     * Static reference to the UI configuration manager for the trashcan interface.
     */
    public static ConfigurationUI UI_CONFIGURATION;

    /**
     * Static array of strings shown in the console on plugin activation.
     */
    static final String[] ACTIVATION_MESSAGE = {
            "=================================================",
            "\tTrash Plugin Activated Properly",
            "================================================="
    };

    /**
     * Called when the plugin is enabled by the server.
     * Initializes messages, UI configuration, registers listeners, and commands.
     */
    @Override
    public void onEnable() {
        // Initialize messages manager with this plugin instance
        MESSAGES = Messages.builder(this);

        // Initialize UI configuration manager with this plugin instance
        UI_CONFIGURATION = ConfigurationUI.builder(this);

        // Log activation messages to the console
        for (String m : ACTIVATION_MESSAGE) {
            this.getLogger().info(m);
        }

        // Register event listener to handle trashcan related events
        Bukkit.getPluginManager().registerEvents(new TrashListener(), this);

        // Register multiple command aliases all handled by the Trash command executor
        Objects.requireNonNull(getCommand("trash")).setExecutor(new Trash());
        Objects.requireNonNull(getCommand("trashcan")).setExecutor(new Trash());
        Objects.requireNonNull(getCommand("basura")).setExecutor(new Trash());
        Objects.requireNonNull(getCommand("mierda")).setExecutor(new Trash());
        Objects.requireNonNull(getCommand("shit")).setExecutor(new Trash());
    }
}