package com.github.athanh.roykillmess.manager.config;

import com.github.athanh.roykillmess.RoyKillMess;
import com.github.athanh.roykillmess.manager.ConfigManager;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import java.io.File;
import java.io.IOException;
public class Config implements ConfigManager {
    private YamlDocument file = null;

    @Override
    public void setup() {
        try {
            file = YamlDocument.create(new File(RoyKillMess.getInstance().getDataFolder(), "config.yml"), RoyKillMess.getInstance().getResource("config.yml"), GeneralSettings.builder().setUseDefaults(false).build(), LoaderSettings.DEFAULT, DumperSettings.DEFAULT, UpdaterSettings.builder().setKeepAll(true).setVersioning(new BasicVersioning("version")).build());
            file.update();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (DamageCause cause : DamageCause.values()) {
            if (!file.isString("death-messages." + cause.toString()))
                file.set("death-messages." + cause, "&#33ff33%victim% &fdied!");
        }
        saveConfig();
    }

    public YamlDocument getConfig() {
        return file;
    }

    public void reloadConfig() {
        try {
            file.reload();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void saveConfig() {
        try {
            file.save();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

    
