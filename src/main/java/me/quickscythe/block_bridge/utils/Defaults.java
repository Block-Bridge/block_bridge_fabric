package me.quickscythe.block_bridge.utils;

import json2.JSONObject;

public class Defaults {
    public static JSONObject config() {
        JSONObject config = new JSONObject();
        config.put("something", "something");
        return config;
    }
}
