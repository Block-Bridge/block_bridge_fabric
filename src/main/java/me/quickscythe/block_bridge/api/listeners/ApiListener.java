package me.quickscythe.block_bridge.api.listeners;

import me.quickscythe.api.listener.Listener;

public class ApiListener implements Listener.ApiChannelListener {

    @Override
    public void onMessage(me.quickscythe.api.event.api.ApiChannelMessageEvent event) {
        if (event.getAction().equalsIgnoreCase("send_message") && event.getTo().equalsIgnoreCase("minecraft"))
            System.out.println(event.getMessage());
    }
}
