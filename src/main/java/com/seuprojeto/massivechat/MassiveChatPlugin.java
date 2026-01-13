package com.seuprojeto.massivechat;

import org.bukkit.plugin.java.JavaPlugin;
import com.seuprojeto.massivechat.chat.ChatListener;
import com.seuprojeto.massivechat.config.ConfigManager;
import com.seuprojeto.massivechat.command.ChatCommand;
import org.bukkit.Bukkit;

public class MassiveChatPlugin extends JavaPlugin {

    private static MassiveChatPlugin instance;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;
        
        // 1. Inicializar Configurações
        // Removido saveDefaultConfig() para evitar erro de recurso ausente
        this.configManager = new ConfigManager(this);
        
        // 2. Registrar o ChatListener (Onde ocorre a mágica do Markdown/Canais)
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        
        // 3. Registrar o comando principal e seus aliases
        if (getCommand("chat") != null) {
            ChatCommand chatCommand = new ChatCommand();
            getCommand("chat").setExecutor(chatCommand);
            // TabCompleter desativado para garantir compatibilidade imediata
        }
        
        getLogger().info("========================================");
        getLogger().info("DeorumChat ativado com sucesso!");
        getLogger().info("========================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("DeorumChat desativado!");
    }

    /**
     * Método para recarregar o plugin sem precisar reiniciar o servidor.
     */
    public void reloadPlugin() {
        reloadConfig();
        this.configManager = new ConfigManager(this);
        getLogger().info("Configuracoes recarregadas!");
    }

    public static MassiveChatPlugin getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}