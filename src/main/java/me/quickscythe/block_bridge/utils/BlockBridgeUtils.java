package me.quickscythe.block_bridge.utils;

import me.quickscythe.block_bridge.BlockBridge;
import me.quickscythe.block_bridge.api.Api;
import me.quickscythe.block_bridge.api.BlockBridgeApi;
import me.quickscythe.block_bridge.utils.logging.LoggerUtils;
import net.fabricmc.api.ModInitializer;

public class BlockBridgeUtils {
    private static Api api;

    static LoggerUtils loggerUtils;
    static BlockBridge blockBridge;

    public static void initializeServer(BlockBridge blockBridge) {
        BlockBridgeUtils.blockBridge = blockBridge;
        api = new BlockBridgeApi();
        loggerUtils = new LoggerUtils();
        loggerUtils.log("Starting BlockBridge");

    }

    public static Api getApi() {
        return api;
    }

    public static LoggerUtils getLogger(){
        return loggerUtils;
    }

    public static BlockBridge getMod() {
        return blockBridge;
    }
}
