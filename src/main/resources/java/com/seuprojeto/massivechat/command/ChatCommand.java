package com.seuprojeto.massivechat.command;

import com.seuprojeto.massivechat.player.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cApenas jogadores podem mudar de canal.");
            return true;
        }

        // O label é o nome do comando usado (g, l, rp, etc.)
        String targetChannel = label.equalsIgnoreCase("g") ? "global" : 
                               label.equalsIgnoreCase("l") ? "local" : "rp";

        // Se o jogador digitou apenas o comando (ex: /g), muda o canal focado
        if (args.length == 0) {
            PlayerData.setChannel(player.getUniqueId(), targetChannel);
            player.sendMessage("§aVocê agora está falando no canal: §f" + targetChannel.toUpperCase());
            return true;
        }

        // Se o jogador digitou /g <mensagem>, ele fala direto no canal sem mudar o foco
        String message = String.join(" ", args);
        player.chat("!" + targetChannel + " " + message); // Usamos um prefixo interno para o Listener identificar
        return true;
    }
}