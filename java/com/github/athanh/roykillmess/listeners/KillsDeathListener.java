package com.github.athanh.roykillmess.listeners;

import com.github.athanh.roykillmess.utils.ColorUtils;
import com.github.athanh.roykillmess.utils.Utils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;

import static com.github.athanh.roykillmess.RoyKillMess.CONFIG;

public class KillsDeathListener implements Listener {

    @EventHandler
    public void onDeath(@NotNull PlayerDeathEvent event) {
        final Player player = event.getEntity();
        Entity killer = player.getKiller(); // Chỉ lấy người chơi giết
        String msg;
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            msg = CONFIG.getString("death-messages.KILLED", "&c%victim% đã bị %attacker% giết!");
            msg = msg.replace("%attacker%", killerPlayer.getName())
                    .replace("%victim%", player.getName())
                    .replace("%item%", Utils.setItem(killerPlayer));

        } else {

            Entity damageSource = null;
            if (player.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
                damageSource = ((EntityDamageByEntityEvent) player.getLastDamageCause()).getDamager();
            }

            if (damageSource instanceof LivingEntity) {
                String killerName = getEntityName((LivingEntity) damageSource);
                msg = CONFIG.getString("death-messages.MOB_KILLED", "&c%victim% đã bị %attacker% giết bởi quái vật!");
                msg = msg.replace("%attacker%", killerName)
                        .replace("%victim%", player.getName());

            } else {
                // Nếu chết do nguyên nhân khác (ngã, lửa, TNT, ...)
                String cause = player.getLastDamageCause() != null ? player.getLastDamageCause().getCause().toString() : "UNKNOWN";
                if (CONFIG.isString("death-messages." + cause)) {
                    msg = CONFIG.getString("death-messages." + cause);
                } else {
                    msg = "&c%victim% đã chết!";
                }
                msg = msg.replace("%victim%", player.getName());
            }
        }

        msg = Utils.setPlaceholders(player, msg);

        event.setDeathMessage(ColorUtils.format(Utils.setPlaceholders(player, msg)));
    }

    private String getEntityName(LivingEntity entity) {
        if (entity == null) return "quái vật";

        ActiveMob mythicMob = MythicBukkit.inst().getMobManager().getMythicMobInstance(entity);
        if (mythicMob != null && mythicMob.getDisplayName() != null) {
            return mythicMob.getDisplayName();
        }

        if (entity.getCustomName() != null) {
            return entity.getCustomName();
        }

        return entity.getName();
    }

//    private void debugLog(String playerName, String killerName, String msg) {
//        System.out.println("[DEBUG] Người chơi chết: " + playerName);
//        System.out.println("[DEBUG] Kẻ giết: " + killerName);
//        System.out.println("[DEBUG] Chuỗi tin nhắn trước khi format: " + msg);
//    }
}
