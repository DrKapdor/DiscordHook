package me.drkapdor.discordhook.api.message.embed;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Getter;
import me.drkapdor.discordhook.api.message.IDiscordMessage;
import me.drkapdor.discordhook.api.message.embed.objects.EmbedAuthor;
import me.drkapdor.discordhook.api.message.embed.objects.EmbedField;
import me.drkapdor.discordhook.api.message.embed.objects.EmbedFooter;
import me.drkapdor.discordhook.api.message.embed.objects.EmbedThumbnail;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Builder
public class EmbedMessage implements IDiscordMessage {

    private String title;
    private EmbedType type;
    private String description;
    private long timestamp;
    private String url;
    private int color;
    private EmbedFooter footer;
    private EmbedThumbnail thumbnail;
    private EmbedAuthor author;
    private EmbedField[] fields;

    @Override
    public JsonObject toJson() {
        JsonObject jsonEmbedObject = new JsonObject();
        if (title != null)
            jsonEmbedObject.addProperty("title", title);
        if (type != null)
            jsonEmbedObject.addProperty("type", type.toString().toLowerCase());
        if (description != null)
            jsonEmbedObject.addProperty("description", description);
        if (timestamp != 0)
            jsonEmbedObject.addProperty("timestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ROOT).format(timestamp));
        if (url != null)
            jsonEmbedObject.addProperty("url", url);
        if (color != 0)
            jsonEmbedObject.addProperty("color", color);
        if (footer != null)
            jsonEmbedObject.add("footer", footer.toJson());
        if (thumbnail != null)
            jsonEmbedObject.add("thumbnail", thumbnail.toJson());
        if (author != null)
            jsonEmbedObject.add("author", author.toJson());
        if (fields != null) {
            JsonArray jsonArray = new JsonArray();
            for (EmbedField field : fields)
                jsonArray.add(field.toJson());
            jsonEmbedObject.add("fields", jsonArray);
        }
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonEmbedObject);
        jsonObject.add("embeds", jsonArray);
        return jsonObject;
    }
}
