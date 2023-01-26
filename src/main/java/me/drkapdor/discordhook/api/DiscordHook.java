package me.drkapdor.discordhook.api;

import lombok.Getter;
import me.drkapdor.discordhook.api.webhook.DiscordWebhook;
import me.drkapdor.discordhook.shared.DiscordHookConfig;

import java.util.Map;

public class DiscordHook {

    @Getter
    private final Map<String, DiscordWebhook> webhookRegistry;

    public DiscordHook(DiscordHookConfig config) {
        this.webhookRegistry = config.getWebhookRegistry();
    }


}
