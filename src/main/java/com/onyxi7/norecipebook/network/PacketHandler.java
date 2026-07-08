package com.onyxi7.norecipebook.network;

import com.onyxi7.norecipebook.NoRecipeBook;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketRecipeBook;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PacketHandler {
    
    // Initialize packet interception
    public static void init() {
        // This will be called when the client connects to a server
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onClientConnected(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        // Add our packet handler to the channel pipeline
        if (!event.isLocal()) {
            event.getManager().channel().pipeline().addBefore("packet_handler", 
                "norecipebook_packet_handler", new RecipeBookPacketInterceptor());
            NoRecipeBook.logger.info("[NoRecipeBook] Packet interceptor registered");
        }
    }
    
    // Intercepts and blocks SPacketRecipeBook from the server
    @ChannelHandler.Sharable
    private static class RecipeBookPacketInterceptor extends SimpleChannelInboundHandler<Packet<?>> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Packet<?> msg) throws Exception {
            // Block recipe book packets from the server
            if (msg instanceof SPacketRecipeBook) {
                NoRecipeBook.logger.debug("[NoRecipeBook] Blocked SPacketRecipeBook from server");
                return; // Don't pass the packet to the next handler
            }
            
            // Pass all other packets through
            ctx.fireChannelRead(msg);
        }
    }
}
