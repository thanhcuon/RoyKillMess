package com.github.athanh.roykillmess;

import com.github.athanh.roykillmess.commands.RoyKillMessCommand;
import com.github.athanh.roykillmess.listeners.KillsDeathListener;
import com.github.athanh.roykillmess.manager.ConfigManager;
import com.github.athanh.roykillmess.manager.config.Config;
import com.github.athanh.roykillmess.utils.ColorUtils;
import dev.dejvokep.boostedyaml.YamlDocument;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
public final class RoyKillMess extends JavaPlugin {
    public static YamlDocument CONFIG;
    private static RoyKillMess instance;
    public static ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;

        int pluginId = 25182;
        new Metrics(this, pluginId);

        configManager = new Config();
        configManager.setup();
        CONFIG = configManager.getConfig();

        new ColorUtils();

        this.getCommand("roykillmess").setExecutor(new RoyKillMessCommand());

        getServer().getPluginManager().registerEvents(new KillsDeathListener(), this);
    }

    @Override
    public void onDisable() {

    }


    public static RoyKillMess getInstance() {
        return instance;
    }
    public static ConfigManager getConfigManager(){
        return configManager;
    }
}
