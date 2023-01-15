package me.drkapdor.discordhook.api.webhook;

import com.google.gson.JsonObject;
import lombok.Getter;
import me.drkapdor.discordhook.api.message.IDiscordMessage;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Getter
public class DiscordWebhook {

    private final String id;
    private final String url;

    public DiscordWebhook(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public CompletableFuture<Void> sendMessage(IDiscordMessage message) {
        return CompletableFuture.runAsync(() -> {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(message.toJson().toString(), ContentType.APPLICATION_JSON));
            try {
                client.execute(post);
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
