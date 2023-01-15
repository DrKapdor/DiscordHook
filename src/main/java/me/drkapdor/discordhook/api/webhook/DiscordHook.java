package me.drkapdor.discordhook.api.webhook;

import lombok.Getter;
import me.drkapdor.discordhook.shared.DiscordHookConfig;

import java.util.Map;

public class DiscordHook {

    @Getter
    private final Map<String, DiscordWebhook> webhookRegistry;

    public DiscordHook(DiscordHookConfig config) {
        this.webhookRegistry = config.getWebhookRegistry();
    }

}
