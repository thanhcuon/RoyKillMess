package com.github.athanh.roykillmess.support;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
public class PlaceholderAPISupport {

    public static @NotNull String setPlaceholders(@NotNull Player player, @NotNull String msg) {
        return PlaceholderAPI.setPlaceholders(player, msg);
    }
}
