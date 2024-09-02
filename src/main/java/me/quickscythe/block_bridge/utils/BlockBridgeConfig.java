package me.quickscythe.block_bridge.utils;

import me.quickscythe.api.BotPlugin;
import me.quickscythe.api.config.ConfigClass;
import me.quickscythe.block_bridge.BlockBridgeFabricPlugin;

public class BlockBridgeConfig extends ConfigClass {
    public BlockBridgeConfig() {
        super(new BlockBridgeFabricPlugin(), "blockbridge", Defaults.config());
    }
}
