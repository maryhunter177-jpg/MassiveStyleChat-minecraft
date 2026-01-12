package com.seuprojeto.massivechat.config;

import com.seuprojeto.massivechat.MassiveChatPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private final MassiveChatPlugin plugin;
    private FileConfiguration channelsConfig;
    private File channelsFile;

    public ConfigManager(MassiveChatPlugin plugin) {
        this.plugin = plugin;
        setupChannels();
    }

    public void setupChannels() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        channelsFile = new File(plugin.getDataFolder(), "channels.yml");

        if (!channelsFile.exists()) {
            plugin.saveResource("channels.yml", false);
        }

        channelsConfig = YamlConfiguration.loadConfiguration(channelsFile);
    }

    public FileConfiguration getChannels() {
        return channelsConfig;
    }
}