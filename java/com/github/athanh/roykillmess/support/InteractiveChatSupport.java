package com.github.athanh.roykillmess.support;
import com.loohp.interactivechat.api.InteractiveChatAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
public class InteractiveChatSupport {

    public static String markSender(@NotNull Player killer) {
        return InteractiveChatAPI.markSender("[item]", killer.getUniqueId());
    }
}
