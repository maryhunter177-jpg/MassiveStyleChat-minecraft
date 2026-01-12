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
        saveDefaultConfig();
        this.configManager = new ConfigManager(this);
        
        // 2. Registrar o ChatListener
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        
        // 3. Registrar o comando principal
        if (getCommand("chat") != null) {
            ChatCommand chatCommand = new ChatCommand();
            getCommand("chat").setExecutor(chatCommand);
            // Removido o setTabCompleter para evitar o erro de incompatibilidade
        }
        
        getLogger().info("========================================");
        getLogger().info("DeorumChat ativado com sucesso!");
        getLogger().info("========================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("DeorumChat desativado!");
    }

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