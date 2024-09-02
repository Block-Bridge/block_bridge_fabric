package me.quickscythe.block_bridge.server.listeners;

import me.quickscythe.block_bridge.BlockBridgeFabricPlugin;
import me.quickscythe.block_bridge.utils.BlockBridgeFabricUtils;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.block.Block;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class ServerListener implements ServerLifecycleEvents.ServerStopping, ServerPlayConnectionEvents.Join, ServerPlayConnectionEvents.Disconnect, ServerMessageEvents.ChatMessage, ServerLifecycleEvents.ServerStarting {
    @Override
    public void onServerStopping(MinecraftServer server) {
        BlockBridgeFabricUtils.end();
    }

    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        BlockBridgeFabricUtils.getApi().appData("save_player?a=" + handler.getPlayer().getName().getString() + "&b=" + handler.getPlayer().getUuid() + "&c=" + handler.getConnectionAddress().toString());
        BlockBridgeFabricUtils.getLogger().log(BlockBridgeFabricUtils.getApi().appData("join?a=" + handler.getPlayer().getName().getString()).toString() + "&b=" + handler.getPlayer().getUuid());
    }

    @Override
    public void onPlayDisconnect(ServerPlayNetworkHandler handler, MinecraftServer server) {
        BlockBridgeFabricUtils.getApi().appData("save_player?a=" + handler.getPlayer().getName().getString() + "&b=" + handler.getPlayer().getUuid());
        BlockBridgeFabricUtils.getLogger().log(BlockBridgeFabricUtils.getApi().appData("leave?a=" + handler.getPlayer().getName().getString()).toString() + "&b=" + handler.getPlayer().getUuid());

    }

    @Override
    public void onChatMessage(SignedMessage message, ServerPlayerEntity sender, MessageType.Parameters params) {
        BlockBridgeFabricUtils.getLogger().log(BlockBridgeFabricUtils.getApi().appData("chat?a=" + sender.getName().getString()).toString() + "&b=" + sender.getUuid() + "&c=" + message.getSignedContent());
    }

    @Override
    public void onServerStarting(MinecraftServer server) {
        BlockBridgeFabricUtils.getApi().appData("save_server?a=" + server.getName() + "&b=" + server.getServerIp() + "&c=" + server.getServerPort() + "&d=" + server.getServerMotd() + "&e=0" + "&f=0");
        BlockBridgeFabricUtils.getApi().appData("status?a=online");
        BlockBridgeFabricUtils.getLogger().log("Sent status ping to API. Status: Online");
    }
}
