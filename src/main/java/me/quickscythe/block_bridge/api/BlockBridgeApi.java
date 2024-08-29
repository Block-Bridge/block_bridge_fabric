package me.quickscythe.block_bridge.api;

import json2.JSONObject;
import me.quickscythe.block_bridge.utils.BlockBridgeUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class BlockBridgeApi implements Api {

    String token = null;


    @Override
    public void validateToken() {
        BlockBridgeUtils.getLogger().log("Validating Token");
        if (getData("check_token", false).has("error") || token == null) {
            BlockBridgeUtils.getLogger().log("Token Invalid");
            token = generateNewToken();
        }
    }

    private String generateNewToken() {
        try {
            BlockBridgeUtils.getLogger().log("Generating new token");
            URL url = URI.create(URL() + APP_ENTRY() + "/token").toURL();
            String result = new Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next();
            BlockBridgeUtils.getLogger().log(result);
            token = new JSONObject(result).getString("success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public JSONObject getData(String path) {
        return getData(path, true);
    }

    @Override
    public JSONObject getData(String path, boolean validate) {
        if (validate) validateToken();
        try {
            URL url = URI.create(URL() + APP_ENTRY() + "/" + APP_VERSION() + "/" + token + "/" + path).toURL();
            return new JSONObject(new Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String URL() {
        return BlockBridgeUtils.getMod().getConfig().getString("api_url");
    }

    @Override
    public String APP_ENTRY() {
        return BlockBridgeUtils.getMod().getConfig().getString("app_entry_point");
    }

    @Override
    public String API_ENTRY() {
        return BlockBridgeUtils.getMod().getConfig().getString("api_entry_point");
    }

    @Override
    public String APP_VERSION() {
        return BlockBridgeUtils.getMod().getConfig().getString("app_version");
    }
}
