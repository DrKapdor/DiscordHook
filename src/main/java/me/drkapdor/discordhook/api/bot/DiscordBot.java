package me.drkapdor.discordhook.api.bot;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Getter
public class DiscordBot {

    private final String id;
    private final String token;
    private transient JDA jda;

    public DiscordBot(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public CompletableFuture<Void> setActivity(Activity activity) {
        return CompletableFuture.runAsync(() -> getJda().getPresence().setActivity(activity));
    }

    public CompletableFuture<Void> addListener(ListenerAdapter adapter) {
        return CompletableFuture.runAsync(() -> getJda().addEventListener(adapter));
    }

    public CompletableFuture<Void> addCommand(CommandData data, Consumer<SlashCommandInteractionEvent> commandActions) {
        return CompletableFuture.runAsync(() -> {
            getJda().updateCommands().addCommands(data).queue();
            getJda().addEventListener(new ListenerAdapter() {
                @Override
                public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
                    if (event.getName().equals(data.getName()))
                        commandActions.accept(event);
                }
            });
        });
    }

    public CompletableFuture<Void> sendPrivateMessage(long userId, MessageEmbed message, ItemComponent... components) {
        return CompletableFuture.runAsync(() -> {
            PrivateChannel privateChannel = getJda().getPrivateChannelCache().getElementById(userId);
            if (privateChannel == null)
                privateChannel = getJda().getPrivateChannelById(userId);
            if (privateChannel == null)
                privateChannel = getJda().openPrivateChannelById(userId).complete();
            privateChannel.sendMessageEmbeds(message).addActionRow(components).queue();
        });
    }

    public JDA getJda() {
        if (jda != null)
            return jda;
       else jda = JDABuilder.createDefault(token).build();
       return jda;
    }
}
