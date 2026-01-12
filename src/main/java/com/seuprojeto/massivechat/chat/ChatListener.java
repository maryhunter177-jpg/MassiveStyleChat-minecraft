package com.seuprojeto.massivechat.chat;

import com.seuprojeto.massivechat.MassiveChatPlugin;
import com.seuprojeto.massivechat.channel.ChannelManager;
import com.seuprojeto.massivechat.player.PlayerData; // Importe corrigido aqui
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Set;

public class ChatListener implements Listener {

    private final MassiveChatPlugin plugin;
    private final FormatParser formatParser = new FormatParser();
    private final EmoteParser emoteParser = new EmoteParser();
    private final ChannelManager channelManager = new ChannelManager();

    public ChatListener(MassiveChatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        String rawMessage = LegacyComponentSerializer.legacyAmpersand().serialize(event.message());

        // 1. Determina o canal
        String channelName = PlayerData.getChannel(player.getUniqueId());
        
        // Suporte para atalho rápido
        if (rawMessage.startsWith("!")) {
            String[] split = rawMessage.split(" ", 2);
            channelName = split[0].substring(1);
            rawMessage = split.length > 1 ? split[1] : "";
        }

        // 2. Pega as configs do canal
        var section = plugin.getConfigManager().getChannels().getConfigurationSection("channels." + channelName);
        if (section == null) section = plugin.getConfigManager().getChannels().getConfigurationSection("channels.local");

        String prefix = section.getString("prefix", "&7[L]");
        String format = section.getString("format", "%prefix% %player%: %message%");
        double range = section.getDouble("range", 100.0);

        // 3. Processa a mensagem
        String processedMsg = emoteParser.parse(rawMessage);
        processedMsg = formatParser.parseAll(processedMsg);

        // 4. Monta o formato final
        String finalEntry = format.replace("%prefix%", prefix)
                                  .replace("%player%", player.getName())
                                  .replace("%message%", processedMsg);
        
        Component component = LegacyComponentSerializer.legacySection().deserialize(finalEntry.replace("&", "§"));

        // 5. Envia para os destinatários corretos
        Set<Player> recipients = channelManager.getRecipients(player, (Set<Player>) plugin.getServer().getOnlinePlayers(), range);
        for (Player recipient : recipients) {
            recipient.sendMessage(component);
        }
    }
}