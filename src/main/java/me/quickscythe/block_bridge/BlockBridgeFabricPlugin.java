package me.quickscythe.block_bridge;

import me.quickscythe.api.BotPlugin;

public class BlockBridgeFabricPlugin extends BotPlugin {
    public BlockBridgeFabricPlugin() {
        setName("BlockBridgeFabric");
    }

    public void enable() {
        getLogger().info("{} enabled", getName());
    }
}
