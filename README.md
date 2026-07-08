# NoRecipeBook

A lightweight Minecraft mod that completely removes the vanilla recipe book interface and optimizes performance by preventing the game from processing thousands of modded recipes when opening inventory screens

---

## Description

**NoRecipeBook** is a simple yet effective performance optimization mod for Minecraft 1.12.2. It targets the vanilla recipe book system, which can cause significant lag in modpacks with hundreds or thousands of recipes from various mods.

### What it does:
- **Removes the recipe book button** from the inventory and crafting table interfaces
- **Clears the internal recipe list** to prevent the game from processing and filtering thousands of modded recipes every time you open your inventory

### Why you should use it:
If you play modpacks with many mods, you've probably experienced that annoying 1-2 second freeze when opening your inventory or crafting table. This happens because the vanilla recipe book tries to load, filter, and prepare to render thousands of recipes from all your installed mods. NoRecipeBook eliminates this lag source completely while keeping HEI fully functional for viewing recipes.

---

## Features

- **Performance Boost**: Eliminates inventory opening lag in heavy modpacks
- **Clean Interface**: Removes the recipe book button that most players don't use
- **Crash-Proof**: Uses safe error handling to prevent game crashes
- **Lightweight**: Minimal resource usage
- **Client-Side Only**: Can be installed on client without requiring server installation

---

## Requirements

### Dependencies:
- **Minecraft**: 1.12.2
- **Minecraft Forge**: 14.23.5.2847 or compatible versions
- **HEI (Optional but Recommended)**: Had Enough Items for viewing recipes after removing the vanilla book

### Installation:
1. Download the latest version of NoRecipeBook
2. Place the `.jar` file in your Minecraft `mods` folder
3. Launch the game

---

## Technical Details

The mod works by:
1. Intercepting the GUI initialization event for inventory and crafting screens
2. Hiding the recipe book button (ID 10 in vanilla Minecraft 1.12.2)
3. Using reflection to access and clear the internal recipe list, preventing the game from processing thousands of recipes
4. Maintaining all other game functionality intact

---

## Important Notes

- This mod is **client-side only** and does not need to be installed on servers
- The vanilla recipe book is completely removed - use HEI or other mods to view recipes
- Compatible with most mods that add recipes
- Does not affect crafting mechanics or recipe functionality in any way

---

## License

This mod is licensed under the MIT License. You are free to use, modify, and distribute this mod as long as proper credit is given.

---

## Bug Reports & Support

If you encounter any issues or have suggestions, please report them on the GitHub Issue Tracker.

