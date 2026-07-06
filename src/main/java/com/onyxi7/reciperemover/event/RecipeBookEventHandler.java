package com.onyxi7.reciperemover.event;

import com.onyxi7.reciperemover.RecipeRemover;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.util.List;

@Mod.EventBusSubscriber(modid = RecipeRemover.MODID, value = Side.CLIENT)
public class RecipeBookEventHandler {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
        GuiScreen gui = event.getGui();
        
        // This only applies to inventory containers, crafting, etc.
        if (gui instanceof GuiContainer) {
            try {
                // The recipe book is handled internally in GuiContainer
                // Searches for the ‘recipeBook’ field, which is an instance of GuiRecipeBook (an internal or private class)
                Field recipeBookField = GuiContainer.class.getDeclaredField("recipeBook");
                recipeBookField.setAccessible(true);
                Object book = recipeBookField.get(gui);
                
                if (book != null) {
                    // 1. Make the book invisible so it doesn't get in the way visually
                    Field visibleField = book.getClass().getDeclaredField("visible");
                    visibleField.setAccessible(true);
                    visibleField.setBoolean(book, false);

                    // 2. ANTI-LAG: We clear the internal recipe list
                    // Lag in modpacks occurs because the book tries to filter through thousands of mod recipes
                    // By clearing this list, rendering and filtering become instantaneous
                    Field recipesField = book.getClass().getDeclaredField("recipes");
                    recipesField.setAccessible(true);
                    List<?> recipesList = (List<?>) recipesField.get(book);
                    if (recipesList != null) {
                        recipesList.clear();
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                RecipeRemover.logger.error("The internal RecipeBook could not be accessed", e);
            }
        }
    }
}
