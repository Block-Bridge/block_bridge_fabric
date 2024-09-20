package me.quickscythe.block_bridge.server.listeners;

import json2.JSONObject;
import me.quickscythe.block_bridge.BlockBridgeFabric;
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

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class ServerListener implements ServerLifecycleEvents.ServerStopping, ServerPlayConnectionEvents.Join, ServerPlayConnectionEvents.Disconnect, ServerMessageEvents.ChatMessage, ServerLifecycleEvents.ServerStarting, ServerLifecycleEvents.ServerStarted {
    @Override
    public void onServerStopping(MinecraftServer server) {
        BlockBridgeFabricUtils.end();
    }

    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        BlockBridgeFabricUtils.getApi().appData("save_player?a=" + handler.getPlayer().getName().getString() + "&b=" + handler.getPlayer().getUuid() + "&c=" + handler.getConnectionAddress().toString());
        BlockBridgeFabricUtils.getLogger().log(BlockBridgeFabricUtils.getApi().appData("join?a=" + handler.getPlayer().getUuid()).toString());
    }

    @Override
    public void onPlayDisconnect(ServerPlayNetworkHandler handler, MinecraftServer server) {
        BlockBridgeFabricUtils.getApi().appData("save_player?a=" + handler.getPlayer().getUuid() + "&b=" + handler.getPlayer().getUuid() + "&c=" + handler.getConnectionAddress().toString());
        BlockBridgeFabricUtils.getLogger().log(BlockBridgeFabricUtils.getApi().appData("leave?a=" + handler.getPlayer().getUuid()).toString(2));

    }

    @Override
    public void onChatMessage(SignedMessage message, ServerPlayerEntity sender, MessageType.Parameters params) {
        BlockBridgeFabricUtils.getLogger().log(BlockBridgeFabricUtils.getApi().appData("chat?a=" + sender.getUuid() + "&b=" + message.getSignedContent() + "&c=" + new Date().getTime()).toString(2));
    }

    @Override
    public void onServerStarting(MinecraftServer server) {

    }

    @Override
    public void onServerStarted(MinecraftServer server) {
        BlockBridgeFabricUtils.setServerInstance(server);

        BlockBridgeFabricUtils.getApi().appData("save_server?a=" + format(server.getName()) + "&b=" + server.getServerPort() + "&c=" + format(server.getServerMotd()) + "&d=" + server.getMaxPlayerCount() + "&e=" + server.getPlayerManager().getPlayerNames().length);

        JSONObject data = BlockBridgeFabricUtils.getApi().apiData("server_data?a=this");
        BlockBridgeFabricUtils.getLogger().log("Got server data from API. Data: " + data.toString());
        BlockBridgeFabricUtils.getApi().appData("status?a=online&b=" + data.getString("ip"));
        BlockBridgeFabricUtils.getLogger().log("Sent status ping to APP. Status: Online");
    }

    private String format(String name) {
        return URLEncoder.encode(name, StandardCharsets.UTF_8);
    }
}
