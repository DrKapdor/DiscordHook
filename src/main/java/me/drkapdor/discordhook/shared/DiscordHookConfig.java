package me.drkapdor.discordhook.shared;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import lombok.Getter;
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
    private final String apiToken;

    @Getter
    private final Map<String, DiscordWebhook> webhookRegistry;

    public DiscordHookConfig(String apiToken) {
        this.apiToken = apiToken;
        this.webhookRegistry = new HashMap<>();
        webhookRegistry.put("testHook", new DiscordWebhook("test", "https://discord.com/api/webhooks/{webhook.id}/{webhook.token}"));
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
