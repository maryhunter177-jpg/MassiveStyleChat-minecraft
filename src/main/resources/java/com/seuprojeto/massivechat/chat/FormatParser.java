package com.seuprojeto.massivechat.chat;

import java.util.regex.Pattern;

public class FormatParser {

    public String parseAll(String text) {
        text = parseMarkdown(text);
        text = parseColors(text);
        return text;
    }

    private String parseMarkdown(String text) {
        // Bold: *texto* -> §ltexto§r
        text = text.replaceAll("\\*(.*?)\\*", "§l$1§r");
        // Italic: _texto_ -> §otexto§r
        text = text.replaceAll("_(.*?)_", "§o$1§r");
        // Strikethrough: ~texto~ -> §mtexto§r
        text = text.replaceAll("~(.*?)~", "§m$1§r");
        return text;
    }

    private String parseColors(String text) {
        // Transforma & em § para cores clássicas
        return text.replace("&", "§");
    }
}