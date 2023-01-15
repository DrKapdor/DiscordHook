package me.drkapdor.discordhook.api.message;

import com.google.gson.JsonObject;
import lombok.Getter;
import me.drkapdor.discordhook.api.message.IDiscordMessage;

public class SimpleMessage implements IDiscordMessage {

    @Getter
    private final String text;

    public SimpleMessage(String text) {
        this.text = text;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", text);
        return jsonObject;
    }
}
