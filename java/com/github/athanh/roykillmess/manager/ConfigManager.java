package com.github.athanh.roykillmess.manager;

import dev.dejvokep.boostedyaml.YamlDocument;

public interface ConfigManager {
    void setup();

    YamlDocument getConfig();

    void reloadConfig();

    void saveConfig();
}
