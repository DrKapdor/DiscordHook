package me.drkapdor.discordhook.api.message.embed.objects;

import com.google.gson.JsonObject;
import lombok.Builder;

@Builder
public class EmbedAuthor implements IEmbedObject{

    private String name;
    private String url;
    private String iconUrl;
    private String proxyIconUrl;

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        if (url != null)
            jsonObject.addProperty("url", url);
        if (iconUrl != null)
            jsonObject.addProperty("icon_url", iconUrl);
        if (proxyIconUrl != null)
            jsonObject.addProperty("proxy_icon_url", proxyIconUrl);
        return jsonObject;
    }
}
