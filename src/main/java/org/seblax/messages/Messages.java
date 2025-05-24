package org.seblax.messages;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.seblax.utils.DataFile;

import java.util.List;
import java.util.Random;

public class Messages extends DataFile {
    public static final List<Message> DEFAULT_MESSAGES =
            List.of(
                    // Predefined list of default messages used when no messages.yml exists.
                    Message.of("Poof! Your", "vanished like magic.", ChatColor.GREEN),
                    Message.of("Oops... your", "took a one-way trip to the void.", ChatColor.LIGHT_PURPLE),
                    Message.of("Your", "have been... liberated.", ChatColor.BLUE),
                    Message.of("Those", "? Never heard of them.", ChatColor.WHITE),
                    Message.of("", "gone. Regrets incoming.", ChatColor.RED),
                    Message.of("Your", "have been deleted. Don’t cry. (Okay, maybe a little.)", ChatColor.DARK_PURPLE),
                    Message.of("", "have left the chat.", ChatColor.YELLOW),
                    Message.of("RIP", ": They lived fast, died young.", ChatColor.AQUA),
                    Message.of("Congratulations! You've successfully lost all your", "!", ChatColor.DARK_GREEN),
                    Message.of("Warning:", "may have been sacrificed to the inventory gods.", ChatColor.DARK_RED)
            );

    /**
     * Initializes a new Messages file.
     * If the file is empty, default messages are generated automatically.
     *
     * @param plugin The plugin instance to locate the data folder.
     * @throws IllegalArgumentException if the plugin is null.
     */
    public Messages(JavaPlugin plugin) {
        super("messages", plugin);

        if (this.getKeyCount() <= 0) {
            generateDefaultMessages();
            this.toConsole("============================================================",
                    "NEW MESSAGE.YML FILE CREATED IN " + this.getPath().toUpperCase(),
                    "============================================================");
        }
    }

    /**
     * Builds and returns a new instance of the Messages class.
     *
     * @param plugin The plugin instance to locate the data folder.
     * @return a new Messages object.
     */
    public static Messages builder(JavaPlugin plugin) {
        return new Messages(plugin);
    }

    /**
     * Adds detailed usage comments to the top of the messages.yml file,
     * explaining how to define custom messages and their format.
     */
    public void defaultCommentMessage() {
        YamlConfiguration config = this.getConfigFile();

        config.options().header(String.join(System.lineSeparator(),
                "To add new custom messages, follow the structure below.",
                "Each message is identified by a keyword in the format 'messageX',",
                "where 'X' is a numerical index starting from 1 up to any number (e.g., message1, message2, ..., messageX).",
                "",
                "Each message must consist of exactly **three** elements:",
                "1. prefix – the text that will appear before the item name(s)",
                "2. suffix – the text that will appear after the item name(s)",
                "3. color  – the color in which the whole message (except the item names) will be displayed",
                "",
                "Example:",
                "message1: [\"Hello, I am the prefix\", \"and I am the suffix\", \"GREEN\"]",
                "",
                "Result in chat: \"Hello, I am the prefix Items and I am the suffix\""
        ));

        config.options().copyHeader(true); // Ensure it's written
        this.save();
    }

    /**
     * Retrieves a random message from the messages.yml file.
     * If only one message exists, it is returned directly.
     *
     * @return A formatted message string.
     */
    public String getRandomMessage() {
        int i = this.getKeyCount();

        if (i == 1) {
            return Message.of(this.getList(String.format("message%d", 1))).toString();
        }

        Random r = new Random();
        int index = r.nextInt(i);
        Message message = Message.of(this.getList(String.format("message%d", index)));
        return message.toString();
    }

    // Generates and writes all default messages into the messages.yml file.
    private void generateDefaultMessages() {
        defaultCommentMessage();

        int i = 0;
        for (Message message : DEFAULT_MESSAGES) {
            i++;
            this.set(String.format("message%d", i),
                    message.toList()
            );
        }
    }
}