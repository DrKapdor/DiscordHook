package me.drkapdor.discordhook.api.message.embed.objects;

import com.google.gson.JsonObject;
import lombok.Builder;

@Builder
public class EmbedThumbnail implements IEmbedObject{

    private String url;
    private String proxyUrl;
    private int height;
    private int width;

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("url", url);
        if (proxyUrl != null)
            jsonObject.addProperty("proxy_url", proxyUrl);
        if (height != 0)
            jsonObject.addProperty("height", height);
        if (width != 0)
            jsonObject.addProperty("width", width);
        return jsonObject;
    }

}
