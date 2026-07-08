package com.onyxi7.norecipebook;

import com.onyxi7.norecipebook.event.RecipeBookEventHandler;
import com.onyxi7.norecipebook.network.PacketHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
    @Override
    public void init() {
        // Manually register the event handler to ensure it's loaded
        MinecraftForge.EVENT_BUS.register(RecipeBookEventHandler.class);
        
        // Initialize packet handler to intercept recipe book packets
        PacketHandler.init();
        
        NoRecipeBook.logger.info("[NoRecipeBook] Client-side initialization complete");
    }
}
