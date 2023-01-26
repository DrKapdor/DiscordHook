package me.drkapdor.discordhook.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import me.drkapdor.discordhook.IDiscordHookPlugin;
import me.drkapdor.discordhook.api.DiscordHook;
import me.drkapdor.discordhook.shared.DiscordHookConfig;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;

@Plugin(
        id = "discordhook",
        name = "DiscordHook",
        version = "1.1.0-SNAPSHOT",
        url = "https://dev.funbaze.ru",
        description = "Реализация Discord API в формате плагина для сервера Minecraft",
        authors = {
                "DrKapdor"
        }
)
public class VelocityDiscordHookPlugin implements IDiscordHookPlugin {

    private final ProxyServer server;
    private final Logger logger;
    private final Path dataDirectoryPath;
    private DiscordHookConfig configuration;
    private DiscordHook discordHook;

    @Inject
    public VelocityDiscordHookPlugin(ProxyServer server, Logger logger, @DataDirectory Path dataDirectoryPath) {
        this.server = server;
        this.logger = logger;
        this.dataDirectoryPath = dataDirectoryPath;
        logger.info("DiscordHook plugin has been loaded!");
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        init();
        logger.info("DiscordHook plugin has been successfully initialized!");
    }

    @Override
    public void init() {
        File configDirectory = dataDirectoryPath.toFile();
        if (!configDirectory.exists()) configDirectory.mkdir();
        File configFile = new File(dataDirectoryPath + File.separator + "configuration.toml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                configuration = new DiscordHookConfig("");
                configuration.save(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                configuration = DiscordHookConfig.fromFile(configFile).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        this.discordHook = new DiscordHook(configuration);
    }

    @Override
    public DiscordHookConfig getConfiguration() {
        return configuration;
    }

    @Override
    public DiscordHook getDiscordHook() {
        return discordHook;
    }
}
