package me.drkapdor.discordhook;

import me.drkapdor.discordhook.api.webhook.DiscordHook;
import me.drkapdor.discordhook.shared.DiscordHookConfig;

public interface IDiscordHookPlugin {

    void init();

    DiscordHookConfig getConfiguration();

    DiscordHook getDiscordHook();

}
