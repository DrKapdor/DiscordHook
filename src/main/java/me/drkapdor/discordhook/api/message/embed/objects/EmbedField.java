package me.drkapdor.discordhook.api.message.embed.objects;

import com.google.gson.JsonObject;
import lombok.Builder;

@Builder
public class EmbedField implements IEmbedObject {

    private String name;
    private String value;
    private boolean inline;

    public EmbedField(String name, String value, boolean inline) {
        this.name = name;
        this.value = value;
        this.inline = inline;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("value", value);
        jsonObject.addProperty("inline", inline);
        return jsonObject;
    }
}
