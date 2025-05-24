package org.seblax.utils;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for handling YAML-based configuration files.
 */
public class DataFile {
    private static final Logger LOGGER = Logger.getLogger(DataFile.class.getName());

    private final YamlConfiguration configFile;
    private final File file;
    private final boolean empty;

    /**
     * Loads or creates a YAML configuration file within the plugin's data folder.
     *
     * @param fileName The base name of the file (without .yml extension).
     * @param plugin   The plugin instance used to locate the data folder.
     * @throws IllegalArgumentException if the plugin directory is null.
     */
    public DataFile(String fileName, Plugin plugin) {
        fileName += ".yml";
        File pluginDirectory = plugin.getDataFolder();

        if (!pluginDirectory.exists() && !pluginDirectory.mkdirs()) {
            LOGGER.warning("Failed to create plugin directory: " + pluginDirectory.getAbsolutePath());
        }

        this.file = new File(pluginDirectory, fileName);
        empty = !this.file.exists();

        // Create the file if it doesn't exist
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    LOGGER.info("Created new file: " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Cannot create file: " + file.getAbsolutePath(), e);
            }
        }

        this.configFile = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Static builder to create a DataFile instance.
     */
    public static DataFile builder(String path, JavaPlugin plugin){
        return new DataFile(path, plugin);
    }

    /**
     * Returns the loaded configuration file.
     */
    public YamlConfiguration getConfigFile() {
        return configFile;
    }

    /**
     * Checks whether the file exists on disk.
     */
    public boolean exists() {
        return file.exists();
    }

    /**
     * Returns the full path of the configuration file.
     */
    public String getPath() {
        return this.file.getPath();
    }

    /**
     * Returns true if the file did not exist at load time.
     */
    public boolean isEmpty() {
        return this.empty;
    }

    /**
     * Sets a key-value pair and saves the configuration.
     */
    public synchronized void set(String key, Object value) {
        configFile.set(key, value);
        save();
    }

    /**
     * Retrieves a value from the file by key.
     */
    public synchronized Object get(String key) {
        return configFile.get(key);
    }

    /**
     * Returns a boolean from the file.
     */
    public synchronized boolean getBoolean(String key) {
        return configFile.getBoolean(key);
    }

    /**
     * Returns a string from the file.
     */
    public synchronized String getString(String key) {
        return configFile.getString(key);
    }

    /**
     * Safely retrieves a list from the file. Returns empty list if the cast fails.
     */
    public synchronized <E> List<E> getList(String key) {
        List<?> rawList = configFile.getList(key);
        if (rawList == null) return Collections.emptyList();

        try {
            return (List<E>) rawList;
        } catch (ClassCastException e) {
            LOGGER.warning("Failed to cast list from YAML for key: " + key);
            return Collections.emptyList();
        }
    }

    /**
     * Stores an integer value.
     */
    public synchronized void setInt(String key, Integer value) {
        configFile.set(key, value);
        save();
    }

    /**
     * Returns an integer value.
     */
    public synchronized Integer getInt(String key) {
        return configFile.getInt(key);
    }

    /**
     * Stores a double value.
     */
    public synchronized void setDouble(String key, Double value) {
        configFile.set(key, value);
        save();
    }

    /**
     * Returns a double value.
     */
    public synchronized Double getDouble(String key) {
        return configFile.getDouble(key);
    }

    /**
     * Checks if a path exists in the configuration.
     */
    public boolean contains(String path) {
        return configFile.contains(path);
    }

    /**
     * Retrieves a value or returns the default if the key does not exist.
     */
    public synchronized <T> T getOrDefault(String key, T defaultValue) {
        Object value = configFile.get(key);
        return (value != null) ? (T) value : defaultValue;
    }

    /**
     * Returns the total number of keys in the configuration.
     */
    public int getKeyCount() {
        return configFile.getKeys(true).size();
    }

    /**
     * Returns a subsection of the configuration.
     */
    public ConfigurationSection getConfigurationSection(String s) {
        return this.getConfigFile().getConfigurationSection(s);
    }

    /**
     * Saves the configuration to disk.
     */
    public synchronized void save() {
        try {
            configFile.save(file);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error saving file: " + file.getAbsolutePath(), e);
        }
    }

    /**
     * Logs messages to the console.
     */
    public void toConsole(String... messages) {
        for (String m : messages) {
            LOGGER.info(m);
        }
    }
}