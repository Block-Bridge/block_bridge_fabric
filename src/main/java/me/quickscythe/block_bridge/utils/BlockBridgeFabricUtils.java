package me.quickscythe.block_bridge.utils;

import me.quickscythe.Api;
import me.quickscythe.BlockBridgeApi;
import me.quickscythe.api.BotPlugin;
import me.quickscythe.block_bridge.BlockBridgeFabric;
import me.quickscythe.block_bridge.api.listeners.ApiListener;
import me.quickscythe.block_bridge.utils.logging.LoggerUtils;
import net.minecraft.server.MinecraftServer;

public class BlockBridgeFabricUtils {
    private static BlockBridgeApi api;

    static LoggerUtils loggerUtils;
    static BlockBridgeFabric blockBridge;
    static BlockBridgeConfig config;
    static MinecraftServer serverInstance;

    public static void initializeLogger() {
        loggerUtils = new LoggerUtils();
        loggerUtils.log("Starting BlockBridge");
    }

    public static void setServerInstance(MinecraftServer server) {
        serverInstance = server;
    }

    public static MinecraftServer getServerInstance() {
        return serverInstance;
    }

    public static void initializeServer(BlockBridgeFabric blockBridge) {
        BlockBridgeFabricUtils.blockBridge = blockBridge;
        api = new BlockBridgeApi();
        api.init(true);
        config = new BlockBridgeConfig();
        BotPlugin plugin = config.getConfig().getPlugin();
        api.getWebApp().addListener(plugin, new ApiListener());
//        BotPlugin plugin = config.getConfig().getPlugin();
//        BlockBridgeDiscordUtils.getPluginLoader().registerPlugin(plugin);
//        BlockBridgeDiscordUtils.getPluginLoader().enablePlugin(plugin);
    }

    public static BlockBridgeConfig getConfigManager() {
        return config;
    }

    public static Api getApi() {
        return api;
    }

    public static LoggerUtils getLogger(){
        return loggerUtils;
    }

    public static BlockBridgeFabric getMod() {
        return blockBridge;
    }

    public static void end() {
        getConfigManager().finish();
        BlockBridgeFabricUtils.getApi().appData("status?a=offline");
        getLogger().log("Sent status ping to API. Status: Offline");
    }
}
