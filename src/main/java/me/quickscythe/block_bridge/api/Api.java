package me.quickscythe.block_bridge.api;

import json2.JSONObject;

public interface Api {

    void validateToken();

    JSONObject getData(String path);
    JSONObject getData(String path, boolean validate);

    String URL();
    String APP_ENTRY();
    String API_ENTRY();
    String APP_VERSION();

}
