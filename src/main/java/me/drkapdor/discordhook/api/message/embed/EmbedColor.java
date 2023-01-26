package me.drkapdor.discordhook.api.message.embed;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class EmbedColor {

    private static final Pattern HEX_PATTERN = Pattern.compile("#.{6}");

    public static final EmbedColor WHITE = new EmbedColor(255, 255, 255);
    public static final EmbedColor LIGHT_GRAY = new EmbedColor(170, 170, 170);
    public static final EmbedColor DARK_GRAY = new EmbedColor(85, 85, 85);
    public static final EmbedColor BLACK = new EmbedColor(0, 0, 0);
    public static final EmbedColor BLUE = new EmbedColor(85, 85, 255);
    public static final EmbedColor DARK_BLUE = new EmbedColor(0, 0, 170);
    public static final EmbedColor GREEN = new EmbedColor(85, 255, 85);
    public static final EmbedColor DARK_GREEN = new EmbedColor(0, 170, 0);
    public static final EmbedColor RED = new EmbedColor(255, 85, 85);
    public static final EmbedColor DARK_RED = new EmbedColor(170, 0, 0);

    public static EmbedColor of(int red, int green, int blue) {
        return new EmbedColor(red, green, blue);
    }

    public static EmbedColor of(String hex) {
        if (HEX_PATTERN.matcher(hex).matches()) {
            hex = hex.substring(1);
            return of(Integer.valueOf(hex.substring(0, 2), 16),
                    Integer.valueOf(hex.substring(2, 4), 16),
                    Integer.valueOf(hex.substring(4, 6), 16));
        } else return BLACK;
    }

    private final int red;
    private final int green;
    private final int blue;

    protected EmbedColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getCode() {
        return ((red << 16) | (green << 8) | blue) & 0xFFFFFF;
    }
}
