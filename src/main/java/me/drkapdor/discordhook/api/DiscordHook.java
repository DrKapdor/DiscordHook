package me.drkapdor.discordhook.api;

import lombok.Getter;
import me.drkapdor.discordhook.api.bot.DiscordBot;
import me.drkapdor.discordhook.api.webhook.DiscordWebhook;
import me.drkapdor.discordhook.shared.DiscordHookConfig;

import java.util.Map;

public class DiscordHook {

    @Getter
    private final Map<String, DiscordWebhook> webhookRegistry;
    @Getter
    private final Map<String, DiscordBot> discordBotRegistry;

    public DiscordHook(DiscordHookConfig config) {
        this.webhookRegistry = config.getWebhookRegistry();
        this.discordBotRegistry = config.getDiscordBotRegistry();
    }


}
