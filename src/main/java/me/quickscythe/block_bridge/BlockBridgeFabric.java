package me.quickscythe.block_bridge;

import json2.JSONObject;
import me.quickscythe.block_bridge.server.listeners.ServerListener;
import me.quickscythe.block_bridge.utils.BlockBridgeFabricUtils;
import me.quickscythe.block_bridge.utils.logging.LoggerUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import me.quickscythe.api.config.ConfigClass;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.ActionResult;

import java.io.*;

public class BlockBridgeFabric implements ModInitializer  {
    
    private JSONObject CONFIG;
    public final String MOD_ID = "block_bridge";
    private final String CONFIG_FILE = "config/blockbridge.json";



    @Override
    public void onInitialize() {
        //This will only init on Servers
        BlockBridgeFabricUtils.initializeServer(this);
        BlockBridgeFabricUtils.initializeLogger();
//        CONFIG = loadConfig();
//        configDefaults();



        //Register Events

        ServerListener mainListener = new ServerListener();
        ServerLifecycleEvents.SERVER_STOPPING.register(mainListener);
        ServerLifecycleEvents.SERVER_STARTED.register(mainListener);
        ServerLifecycleEvents.SERVER_STARTING.register(mainListener);
        ServerPlayConnectionEvents.JOIN.register(mainListener);
        ServerPlayConnectionEvents.DISCONNECT.register(mainListener);
        ServerMessageEvents.CHAT_MESSAGE.register(mainListener);

    }


//    private void configDefaults() {
//        setDefault("api_url", "http://localhost:8585");
//        setDefault("api_entry_point", "/api");
//        setDefault("app_entry_point", "/app");
//        setDefault("app_version", "v1");
//
//        saveConfig();
//    }
}
