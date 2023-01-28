package me.drkapdor.discordhook.paper;

import me.drkapdor.discordhook.IDiscordHookPlugin;
import me.drkapdor.discordhook.api.DiscordHook;
import me.drkapdor.discordhook.shared.DiscordHookConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class PaperDiscordHookPlugin extends JavaPlugin implements IDiscordHookPlugin {

    private DiscordHookConfig configuration;
    private DiscordHook discordHook;
    @Override
    public void onEnable() {
        init();
    }

    @Override
    public void init() {
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        File configFile = new File(getDataFolder() + File.separator + "configuration.toml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                configuration = new DiscordHookConfig();
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
        Bukkit.getServicesManager().register(DiscordHook.class, discordHook, this, ServicePriority.Normal);
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
