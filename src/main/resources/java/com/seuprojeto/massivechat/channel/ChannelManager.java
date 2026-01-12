package com.seuprojeto.massivechat.channel;

import org.bukkit.entity.Player;
import java.util.Set;
import java.util.stream.Collectors;

public class ChannelManager {

    /**
     * Filtra os jogadores que receberão a mensagem com base no range.
     * @param sender O jogador que enviou.
     * @param allPlayers Todos os jogadores online.
     * @param range A distância máxima (-1 para global).
     * @return Conjunto de jogadores que podem ouvir.
     */
    public Set<Player> getRecipients(Player sender, Set<Player> allPlayers, double range) {
        if (range == -1) return allPlayers; // Global

        return allPlayers.stream()
                .filter(p -> p.getWorld().equals(sender.getWorld())) // Mesmo mundo
                .filter(p -> p.getLocation().distance(sender.getLocation()) <= range)
                .collect(Collectors.toSet());
    }
}