package com.onyxi7.reciperemover.event;

import com.onyxi7.reciperemover.RecipeRemover;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.recipebook.GuiButtonRecipeBook;
import net.minecraft.client.gui.recipebook.RecipeBook;
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
        
        if (gui instanceof GuiContainer) {
            event.getButtonList().removeIf(button -> button instanceof GuiButtonRecipeBook);

            try {
                Field recipeBookField = GuiContainer.class.getDeclaredField("recipeBook");
                recipeBookField.setAccessible(true);
                RecipeBook book = (RecipeBook) recipeBookField.get(gui);
                
                if (book != null) {
                    book.visible = false;
                    
                    Field recipesField = RecipeBook.class.getDeclaredField("recipes");
                    recipesField.setAccessible(true);
                    List<?> recipesList = (List<?>) recipesField.get(book);
                    if (recipesList != null) {
                        recipesList.clear();
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                RecipeRemover.logger.error("Error attempting to clear the RecipeBook via Reflection", e);
            }
        }
    }
}