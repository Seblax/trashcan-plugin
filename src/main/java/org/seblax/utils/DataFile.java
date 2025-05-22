package org.seblax.utils;

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
     * Initializes a new FileData instance.
     *
     * @param fileName        The name of the YAML file.
     * @param plugin The directory where the file is stored.
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

        // Create file if it does not exist
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

    public static DataFile builder(String fileName, JavaPlugin plugin) {;
        return new DataFile(fileName,plugin);
    }

    public YamlConfiguration getConfigFile() {
        return configFile;
    }

    /**
     * Checks if the file exists.
     *
     * @return true if the file exists, false otherwise.
     */
    public boolean exists() {
        return file.exists();
    }

    public String getPath(){
        return this.file.getPath();
    }

    public boolean isEmpty(){
        return this.empty;
    }

    /**
     * Saves a key-value pair to the YAML file.
     *
     * @param key   The configuration key.
     * @param value The value to be stored.
     */
    public synchronized void set(String key, Object value) {
        configFile.set(key, value);
        save();
    }

    /**
     * Retrieves a value from the YAML file.
     *
     * @param key The configuration key.
     * @return The stored value, or null if the key is not found.
     */
    public synchronized Object get(String key) {
        return configFile.get(key);
    }

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
     * Saves a key-int pair to the YAML file.
     *
     * @param key   The configuration key.
     * @param value The value to be stored.
     */
    public synchronized void setInt(String key, Integer value) {
        configFile.set(key, value);
        save();
    }

    /**
     * Retrieves a int value from the YAML file.
     *
     * @param key The configuration key.
     * @return The stored value, or null if the key is not found.
     */
    public synchronized Integer getInt(String key) {
        return configFile.getInt(key);
    }

    /**
     * Saves a key-double pair to the YAML file.
     *
     * @param key   The configuration key.
     * @param value The value to be stored.
     */
    public synchronized void setDouble(String key, Double value) {
        configFile.set(key, value);
        save();
    }

    /**
     * Retrieves a double value from the YAML file.
     *
     * @param key The configuration key.
     * @return The stored value, or null if the key is not found.
     */
    public synchronized Double getDouble(String key) {
        return configFile.getDouble(key);
    }

    /**
     * Retrieves a boolean value from the YAML file.
     *
     * @param path The value's path.
     * @return True or false if the path is not found.
     */
    public boolean contains(String path){
        return configFile.contains(path);
    }

    /**
     * Retrieves a value from the YAML file with a default fallback.
     *
     * @param key          The configuration key.
     * @param defaultValue The default value to return if the key is not found.
     * @param <T>          The expected return type.
     * @return The stored value or the default if the key does not exist.
     */
    public synchronized <T> T getOrDefault(String key, T defaultValue) {
        Object value = configFile.get(key);
        return (value != null) ? (T) value : defaultValue;
    }

    public int getKeyCount() {
        return configFile.getKeys(true).size();
    }

    /**
     * Saves the current state of the YAML file.
     */
    public synchronized void save() {
        try {
            configFile.save(file);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error saving file: " + file.getAbsolutePath(), e);
        }
    }

    public void toConsole(String... messages){
        for (String m: messages){
            LOGGER.info(m);
        }
    }

}