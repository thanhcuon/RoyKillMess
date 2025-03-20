package com.github.athanh.roykillmess.commands;
import com.github.athanh.roykillmess.RoyKillMess;
import com.github.athanh.roykillmess.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class RoyKillMessCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission("roykillmess.reload") && !sender.hasPermission("roykillmess.admin") && !sender.hasPermission("roykillmess.*")) {
            MessageUtils.sendMsgP(sender, "errors.no-permission");
            return true;
        }

        RoyKillMess.getConfigManager().reloadConfig();
        MessageUtils.sendMsgP(sender, "reload");
        return true;
    }
}
