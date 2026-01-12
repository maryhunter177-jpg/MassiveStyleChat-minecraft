package com.seuprojeto.massivechat.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData {
    // Mapa estático para armazenar o canal de cada jogador pelo UUID
    private static final Map<UUID, String> playerChannels = new HashMap<>();

    public static void setChannel(UUID uuid, String channel) {
        playerChannels.put(uuid, channel.toLowerCase());
    }

    public static String getChannel(UUID uuid) {
        // Se não tiver canal definido, o padrão é 'local'
        return playerChannels.getOrDefault(uuid, "local");
    }
}