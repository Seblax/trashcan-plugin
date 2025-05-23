package org.seblax.ui;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.seblax.utils.DataFile;
import org.seblax.utils.Parser;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationUI extends DataFile {
    List<SlotUI> inventory = new ArrayList<>();

    /**
     * Loads or creates the trashcan UI configuration file.
     * If the file is empty or missing, it generates default values and displays a console message.
     */
    public ConfigurationUI(Plugin plugin) {
        super("ui-configuration", plugin);

        if (this.getKeyCount() <= 0) {
            generateDefaultMessages();
            this.toConsole("============================================================",
                    "NEW UI-CONFIGURATION.YML FILE CREATED IN " + this.getPath().toUpperCase(),
                    "============================================================");
        }
    }

    /**
     * Sets up default values for the UI configuration file.
     * Includes trashcan name, UI size, and default items for specific slots.
     */
    void generateDefaultMessages() {
        defaultCommentMessage();

        // Trashcan UI title with style and color
        this.set("trashcanName", List.of("DARK_GRAY", "BOLD", "Trashcan"));

        // Number of rows in the trashcan UI (each level = +9 slots)
        this.set("sizeLevel", 4);

        // Slot 0: Trashcan main area – clears items
        this.set("inventory.slot0.position", List.of(0));
        this.set("inventory.slot0.closeTrash", false);
        this.set("inventory.slot0.emptyTrash", true);
        this.set("inventory.slot0.stack.displayName", List.of("DARK_RED", "BOLD", "Trashcan"));
        this.set("inventory.slot0.stack.lore", List.of(
                List.of("ITALIC", "Put your trash here to remove it."),
                List.of("Click on me to clear all your shit!")
        ));
        this.set("inventory.slot0.stack.skull", "be0fd10199e8e4fcdabcae4f85c85918127a7c5553ad235f01c56d18bb9470d3");
        this.set("inventory.slot0.stack.count", 1);

        // Slot 1: Close button
        this.set("inventory.slot1.position", List.of(44));
        this.set("inventory.slot1.closeTrash", true);
        this.set("inventory.slot1.stack.displayName", List.of("DARK_RED", "BOLD", "Close Trashcan"));
        this.set("inventory.slot1.stack.item", "barrier");
        this.set("inventory.slot1.stack.enchanted", true);
        this.set("inventory.slot1.stack.count", 1);

        // Slot 2: Decorative black panes
        this.set("inventory.slot2.position", List.of(1, 3, 5, 7, 9, 17, 27, 35, 37, 39, 41, 43, 45));
        this.set("inventory.slot2.stack.item", "black_stained_glass_pane");

        // Slot 3: Decorative gray panes
        this.set("inventory.slot3.position", List.of(2, 4, 6, 8, 18, 26, 36, 38, 40, 42, 44));
        this.set("inventory.slot3.stack.item", "gray_stained_glass_pane");

        // Save all changes to disk
        this.save();
    }

    /**
     * Writes a detailed explanation at the top of the config file to help users understand the structure and usage.
     */
    public void defaultCommentMessage() {
        YamlConfiguration config = this.getConfigFile();

        config.options().header(String.join(System.lineSeparator(),
                "Hello! If you're reading this, you're likely looking to customize your trashcan UI.",
                "",
                "This configuration file is structured into three main sections:",
                "1. trashcanName   - Defines the name shown on the trashcan UI.",
                "2. sizeLevel      - Controls how many rows (lines) the trashcan inventory has (maximum is 5).",
                "3. inventory      - Defines the behavior and appearance of each slot in the trashcan.",
                "",
                "=== trashcanName ===",
                "To customize the name shown in the UI, provide a list of components using square brackets [].",
                "Each element should be separated by commas.",
                "To apply color and style formatting, use valid ChatColor names from the following page:",
                "https://hub.spigotmc.org/javadocs/spigot/org/bukkit/ChatColor.html",
                "Make sure to use the enum constants from that page (e.g., BOLD, DARK_GRAY).",
                "",
                "=== sizeLevel ===",
                "This integer defines the number of inventory rows.",
                "Each level adds 9 slots. Level 0 = 9 slots, Level 5 = 54 slots (maximum).",
                "",
                "=== inventory ===",
                "This section maps each trashcan slot to a configuration.",
                "Each slot is defined with a key like 'slot0', 'slot1', ..., 'slotX'.",
                "",
                "Important:",
                "- YAML is indentation-sensitive. Make sure your spacing is correct!",
                "- 'position' is a list of integers defining where this item appears (from 0 to 45).",
                "- 'closeTrash' and 'emptyTrash' are booleans. When set to true, they define",
                "   what happens when the item is clicked (either closing or clearing the trashcan).",
                "",
                "=== stack ===",
                "Inside each slot, the 'stack' section defines the item to be shown:",
                "- 'displayName': List of text components with optional formatting (color, style, etc.).",
                "- 'lore': A list of lines, where each line is itself a list of text components.",
                "- 'item': The material name for the item (ignored if 'skull' is defined).",
                "- 'skull': A texture value to render the item as a custom head (overrides 'item').",
                "           You have to paste customhead url value, for example in this url:",
                "           http://textures.minecraft.net/texture/83cbfec34da90d643817bfb11af44bdc786ab3d3db1299e47c4f60c1053b90b2,",
                "           you have to paste only 83cbfec34da90d643817bfb11af44bdc786ab3d3db1299e47c4f60c1053b90b2",
                "- 'enchanted': true/false to determine if the item should appear enchanted.",
                "- 'count': The number of items in the stack (must be >= 1).",
                "",
                "Example lore formatting:",
                "lore:",
                "  - [\"ITALIC\", \"Put your trash here to remove it.\"]",
                "  - [\"Click on me to clear all your items!\"]",
                "",
                "Happy configuring!"
        ));

        config.options().copyHeader(true);
        this.save();
    }


    /**
     * Static builder to create a ConfigurationUI instance.
     */
    public static ConfigurationUI builder(JavaPlugin plugin) {
        return new ConfigurationUI(plugin);
    }

    /**
     * Returns the formatted trashcan UI title as a single string.
     */
    public String getTrashcanName(){
        return Parser.ymlToString(this.getList("trashcanName"));
    }

    /**
     * Returns the number of inventory slots based on the size level (min: 9, max: 54).
     */
    public int getSizeLevel(){
        return  Math.min((Math.max(0, this.getInt("sizeLevel")) + 1 ),6) * 9;
    }

    /**
     * Checks whether a given slot has a UI element defined.
     */
    public boolean isUiSlot(int slot){
        return this.inventory.stream().anyMatch(x -> x.position == slot);
    }

    /**
     * Returns true if the slot can trigger the trashcan to close.
     */
    public boolean canClose(int slot){
        return this.inventory.stream().anyMatch(x -> x.position == slot && x.canCloseTrash());
    }

    /**
     * Returns true if the slot can trigger trashcan clearing (emptying).
     */
    public boolean canErase(int slot){
        return this.inventory.stream().anyMatch(x -> x.position == slot && x.canEmptyTrash());
    }

    /**
     * Loads and returns all slot definitions from the configuration.
     * Converts raw YAML data into usable SlotUI and ItemUI objects.
     */
    public List<SlotUI> getInventory() {
        inventory.clear();
        ConfigurationSection inventorySection = this.getConfigFile().getConfigurationSection("inventory");

        if (inventorySection == null) return inventory;

        for (String slotKey : inventorySection.getKeys(false)) {
            ConfigurationSection slotSection = inventorySection.getConfigurationSection(slotKey);
            if (slotSection == null) continue;

            List<Integer> positions = slotSection.getIntegerList("position");

            for (Integer position : positions){
                // Skip duplicated or already used positions
                if(isUiSlot(position)) continue;

                boolean closeTrash = slotSection.getBoolean("closeTrash", false);
                boolean emptyTrash = slotSection.getBoolean("emptyTrash", false);

                ConfigurationSection stackSection = slotSection.getConfigurationSection("stack");
                if (stackSection == null) continue;

                // Parse the item stack display data
                String displayName = Parser.ymlToString(stackSection.getList("displayName"));
                List<String> lore = new ArrayList<>();
                List<?> loreRaw = stackSection.getList("lore");

                if(loreRaw != null){
                    for (Object obj : loreRaw) {
                        if (obj instanceof List<?> list) {
                            lore.add(Parser.ymlToString(list));
                        }
                    }
                }

                // Extract stack item info
                String item = stackSection.getString("item", null);
                String skull = stackSection.getString("skull", null);
                boolean enchanted = stackSection.getBoolean("enchanted", false);
                int count = stackSection.getInt("count", 1);

                // Build the SlotUI and add to the list
                ItemUI itemUI = new ItemUI(displayName, lore, item, skull, enchanted, count);
                SlotUI slotUI = new SlotUI(position, itemUI, closeTrash, emptyTrash);
                inventory.add(slotUI);
            }
        }

        return inventory;
    }
}
