package me.quickscythe.block_bridge.api.listeners;

import me.quickscythe.api.listener.Listener;
import me.quickscythe.block_bridge.utils.BlockBridgeFabricUtils;
import net.minecraft.text.Text;

public class ApiListener implements Listener.ApiChannelListener {

    @Override
    public void onMessage(me.quickscythe.api.event.api.ApiChannelMessageEvent event) {
        if (event.getAction().equalsIgnoreCase("send_message") && event.getTo().equalsIgnoreCase("minecraft"))
            BlockBridgeFabricUtils.getServerInstance().sendMessage(Text.literal(event.getMessage()));
    }
}
