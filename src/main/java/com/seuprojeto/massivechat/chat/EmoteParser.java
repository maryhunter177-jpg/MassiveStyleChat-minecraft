package com.seuprojeto.massivechat.chat;

import java.util.HashMap;
import java.util.Map;

public class EmoteParser {

    private final Map<String, String> emotes = new HashMap<>();

    public EmoteParser() {
        // No futuro, isso pode ser carregado do emotes.yml
        emotes.put(":smile:", "😄");
        emotes.put(":heart:", "❤");
        emotes.put(":skull:", "☠");
        emotes.put(":check:", "✔");
    }

    public String parse(String text) {
        for (Map.Entry<String, String> entry : emotes.entrySet()) {
            text = text.replace(entry.getKey(), entry.getValue());
        }
        return text;
    }
}