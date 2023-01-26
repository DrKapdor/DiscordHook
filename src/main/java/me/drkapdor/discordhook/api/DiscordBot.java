package me.drkapdor.discordhook.api;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Collection;

public class DiscordBot {

    public static class DiscordBotBuilder {
        private String token;
        private Activity activity;
        private final Collection<ListenerAdapter> listeners;

        public DiscordBotBuilder() {
            listeners = new ArrayList<>();
        }

        public DiscordBotBuilder token(String token) {
            this.token = token;
            return this;
        }

        public DiscordBotBuilder activity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public DiscordBotBuilder listener(ListenerAdapter adapter) {
            listeners.add(adapter);
            return this;
        }

        public DiscordBot build() {
            return new DiscordBot(token, activity, listeners);
        }
    }

    public static DiscordBotBuilder builder() {
        return new DiscordBotBuilder();
    }

    @Getter
    private final JDA jda;

    private DiscordBot(String token, Activity activity, Collection<ListenerAdapter> listeners) {
        this.jda = JDABuilder.createDefault(token)
                .setActivity(activity)
                .addEventListeners(listeners.toArray())
                .build();

    }
}
