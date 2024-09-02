package me.quickscythe.block_bridge.utils;

import me.quickscythe.Api;
import me.quickscythe.BlockBridgeApi;
import me.quickscythe.block_bridge.BlockBridgeFabric;
import me.quickscythe.block_bridge.utils.logging.LoggerUtils;

public class BlockBridgeFabricUtils {
    private static BlockBridgeApi api;

    static LoggerUtils loggerUtils;
    static BlockBridgeFabric blockBridge;
    static BlockBridgeConfig config;

    public static void initializeLogger() {
        loggerUtils = new LoggerUtils();
        loggerUtils.log("Starting BlockBridge");
    }

    public static void initializeServer(BlockBridgeFabric blockBridge) {
        BlockBridgeFabricUtils.blockBridge = blockBridge;
        api = new BlockBridgeApi();
        api.init(false);
        config = new BlockBridgeConfig();
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
