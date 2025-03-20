package com.github.athanh.roykillmess.utils;
import com.github.athanh.roykillmess.support.InteractiveChatSupport;
import com.github.athanh.roykillmess.support.PlaceholderAPISupport;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import static com.github.athanh.roykillmess.RoyKillMess.CONFIG;

public class Utils {
    public static String setPlaceholders(@NotNull Player player, @NotNull String msg) {
        return Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null ? PlaceholderAPISupport.setPlaceholders(player, msg) : msg;
    }
    public static String setItem(@NotNull Player player) {
        if (Bukkit.getPluginManager().getPlugin("InteractiveChat") != null) {
            return InteractiveChatSupport.markSender(player);
        } else if (player.getInventory().getItemInHand() == null) {
            return "air";
        } else {
            ItemStack it = player.getInventory().getItemInHand();
            String typeStr = it.getType().toString().replace("_", " ").toLowerCase();
            typeStr = typeStr.replace("air",CONFIG.getString("empty-hand-text"));
            if (it.getItemMeta() == null) {
                return CONFIG.getString("item-format").replace("%item%", typeStr);
            } else {
                ItemMeta meta = it.getItemMeta();
                return CONFIG.getString("item-format").replace("%item%", meta.hasDisplayName() ? meta.getDisplayName() : typeStr);
            }
        }
    }
}
