package me.quickscythe.block_bridge;

import json2.JSONObject;
import me.quickscythe.block_bridge.utils.BlockBridgeUtils;
import me.quickscythe.block_bridge.utils.logging.LoggerUtils;
import net.fabricmc.api.ModInitializer;

import java.io.*;

public class BlockBridge implements ModInitializer {
    
    private JSONObject CONFIG;
    public final String MOD_ID = "block_bridge";

    @Override
    public void onInitialize() {
        //This will only init on Servers
        BlockBridgeUtils.initializeServer(this);
        CONFIG = loadConfig();
        configDefaults();

        BlockBridgeUtils.getApi().getData("join?b=QuickScythe");


    }

    private void configDefaults() {
        if(!CONFIG.has("api_url"))
            CONFIG.put("api_url", "http://localhost:8585");
        if(!CONFIG.has("api_entry_point"))
            CONFIG.put("api_entry_point", "/api");
        if(!CONFIG.has("app_version"))
            CONFIG.put("app_version", "v1");
        if(!CONFIG.has("app_entry_point"))
            CONFIG.put("app_entry_point", "/app");

        saveConfig();
    }

    private static JSONObject loadConfig() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            File config = new File("config.json");
            if (!config.exists()) if (config.createNewFile()) {
                BlockBridgeUtils.getLogger().log("Config file generated.");
            }
            BufferedReader reader = new BufferedReader(new FileReader("config.json"));

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();


        } catch (IOException ex) {
            BlockBridgeUtils.getLogger().log("Config File couldn't be generated or accessed. Please check console for more details.");
//            BlockBridgeUtils.getLogger().log(LoggerUtils.LogLevel.ERROR,ex);
        }
        String config = stringBuilder.toString();
        return config.isEmpty() ? new JSONObject() : new JSONObject(config);
    }

    public JSONObject getConfig() {
        return CONFIG;
    }

    public void saveConfig() {
        try {
            FileWriter f2 = new FileWriter("config.json", false);
            f2.write(CONFIG.toString(2));
            f2.close();
        } catch (IOException e) {
            BlockBridgeUtils.getLogger().log(LoggerUtils.LogLevel.ERROR, "There was an error saving the config file.");
            //Utils.getLogger().error("Error", e);
        }
    }
}
