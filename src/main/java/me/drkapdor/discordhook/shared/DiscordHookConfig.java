package me.drkapdor.discordhook.shared;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import lombok.Getter;
import me.drkapdor.discordhook.api.bot.DiscordBot;
import me.drkapdor.discordhook.api.webhook.DiscordWebhook;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class DiscordHookConfig {

    public static CompletableFuture<DiscordHookConfig> fromFile(File file) {
       return CompletableFuture.supplyAsync(() -> new Toml().read(file).to(DiscordHookConfig.class));
    }

    @Getter
    private final Map<String, DiscordWebhook> webhookRegistry;

    @Getter
    private final Map<String, DiscordBot> discordBotRegistry;

    public DiscordHookConfig() {
        this.webhookRegistry = new HashMap<>();
        webhookRegistry.put("webhookId", new DiscordWebhook("webkookId", "<insert_webhook_url_here>"));
        this.discordBotRegistry = new HashMap<>();
        discordBotRegistry.put("discordBotId", new DiscordBot("discordBotId", "<insert_bot_token_here>"));
    }

    public CompletableFuture<Void> save(File file) {
        return CompletableFuture.runAsync(() -> {
            TomlWriter writer = new TomlWriter();
            try {
                writer.write(this, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
