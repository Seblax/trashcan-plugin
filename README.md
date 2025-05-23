# 🗑️ Trashcan Plugin

A lightweight Minecraft plugin that introduces a simple, user-friendly **trash can system** for item disposal. Designed to improve inventory management, especially in survival or creative gameplay, this plugin helps players remove unwanted items safely and quickly without using lava or dropping them.

## 🔧 Features

- ✅ Add a trash can GUI with a command or item
- 🧹 Instantly delete items by placing them inside
- 🌍 Supports multiple Minecraft versions (1.xx+)
- 🎨 Fully customizable messages
- 🎨 Fully customizable interface

## 📦 Installation

1. Download the latest `.jar` from the [Releases](https://github.com/Seblax/trashcan-plugin/releases) page.
2. Drop it into the `plugins/` folder of your Minecraft server.
3. Restart or reload the server.

## 🚀 Usage

- Use `/trash` to open the trash can GUI.
- Drag and drop items you want to delete.
- Close the GUI to confirm deletion.

## 🌟 Future Features

- 🔒 Optional permissions for who can use the trash can    

# 🆕 Update: Customizable Trashcan UI via ui-configuration.yml
Hey everyone!
I'm excited to announce a new feature in the latest update of the plugin: you can now fully customize your trashcan UI using a simple YAML file! 🎉

## 📁 New File Created:
**/plugins/trashcan/ui-configuration.yml**

When the plugin starts for the first time (or if the file doesn't exist), it will generate a new configuration file with detailed instructions included at the top. This file lets you personalize the layout, style, and behavior of each slot in the UI.

## 🧩 What You Can Customize

- **UI Title:** Set a custom name for your trashcan with color and formatting (e.g., ["DARK_GRAY", "BOLD", "Trashcan"]).
- **Size Level**: Define how many rows the inventory should have (0 to 5, where each level adds 9 slots).
- **Inventory Slots**: Create and configure slots by position. Each slot supports:
- **position**: One or more inventory indices (0–53)
- **closeTrash**: Whether clicking closes the UI
- **emptyTrash**: Whether clicking clears the inventory

# stack: 
The item shown in the slot, including:
- **displayName and lore** (with formatting!)
- **item** (Material name) or skull (custom texture)
- **enchanted**: true/false
- **count**: quantity

## 🧠 Example Configuration

```
trashcanName: ["DARK_GRAY", "BOLD", "Trashcan"]
sizeLevel: 4

inventory:
  slot0:
    position: [0]
    closeTrash: false
    emptyTrash: true
    stack:
      displayName: ["DARK_RED", "BOLD", "Trashcan"]
      lore:
        - ["ITALIC", "Put your trash here to remove it."]
        - ["Click on me to clear all your stuff!"]
      skull: "your-skull-texture-hash"
      count: 1

  slot1:
    position: [44]
    closeTrash: true
    stack:
      displayName: ["DARK_RED", "BOLD", "Close Trashcan"]
      item: "barrier"
      enchanted: true
      count: 1
```

## 📌 Notes
Every change to ui-configuration.yml is hot-reloadable by restarting the server or reloading the plugin.

The file includes helpful comments at the top to guide you.

Use this to create your own custom styles, layouts, and even icons for decorative or functional purposes.

If you build something cool with this new feature, feel free to share screenshots!
As always, feedback and suggestions are welcome.

Happy trashing! 🗑️✨

## 📜 License

This plugin is open source and available under the [MIT License](LICENSE).

---

Contributions and suggestions are welcome! Submit a pull request or open an issue.
