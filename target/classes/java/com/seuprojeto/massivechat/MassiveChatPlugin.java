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
        
        // 1. Inicializar Configurações e carregar canais.yml
        saveDefaultConfig();
        this.configManager = new ConfigManager(this);
        
        // 2. Registrar o ChatListener (Onde ocorre a mágica do Markdown/Canais)
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        
        // 3. Registrar o comando principal e seus aliases
        // Certifique-se de que no plugin.yml 'chat' tenha aliases: [g, l, rp]
        if (getCommand("chat") != null) {
            ChatCommand chatCommand = new ChatCommand();
            getCommand("chat").setExecutor(chatCommand);
            getCommand("chat").setTabCompleter(chatCommand);
        }
        
        getLogger().info("========================================");
        getLogger().info("MassiveStyleChat ativado com sucesso!");
        getLogger().info("Canais carregados e prontos para uso.");
        getLogger().info("========================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("MassiveStyleChat desativado! Salvando dados...");
    }

    /**
     * Método para recarregar o plugin sem precisar reiniciar o servidor.
     */
    public void reloadPlugin() {
        reloadConfig();
        this.configManager = new ConfigManager(this);
        getLogger().info("Configuracoes recarregadas com sucesso!");
    }

    public static MassiveChatPlugin getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}