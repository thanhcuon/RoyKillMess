package com.github.athanh.roykillmess.utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.StyleBuilderApplicable;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class ColorUtils {
    private static LegacyComponentSerializer LEGACY_COMPONENT_SERIALIZER = null;
    private static LegacyComponentSerializer LEGACY_FORMATTER = null;
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    public ColorUtils() {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        String v = packageName.substring(packageName.lastIndexOf(46) + 1);
        if (!v.contains("1.7") && !v.contains("1_8") && !v.contains("1_9") && !v.contains("1_10") && !v.contains("1_11") && !v.contains("1_12") && !v.contains("1_13") && !v.contains("1_14") && !v.contains("1_15")) {
            LEGACY_COMPONENT_SERIALIZER = LegacyComponentSerializer.builder().character('§').useUnusualXRepeatedCharacterHexFormat().hexColors().build();
            LEGACY_FORMATTER = ((LegacyComponentSerializer.Builder)LegacyComponentSerializer.legacyAmpersand().toBuilder()).useUnusualXRepeatedCharacterHexFormat().hexColors().build();
        } else {
            LEGACY_COMPONENT_SERIALIZER = LegacyComponentSerializer.builder().character('§').useUnusualXRepeatedCharacterHexFormat().build();
            LEGACY_FORMATTER = ((LegacyComponentSerializer.Builder)LegacyComponentSerializer.legacyAmpersand().toBuilder()).useUnusualXRepeatedCharacterHexFormat().build();
        }
    }

    public static @NotNull String format(@NotNull String message) {
        message = message.replace('§', '&');
        message = toLegacy(MINI_MESSAGE.deserialize(message).applyFallbackStyle(new StyleBuilderApplicable[]{TextDecoration.ITALIC.withState(false)}));
        message = LEGACY_FORMATTER.serialize(LEGACY_FORMATTER.deserialize(message));
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }

    public static @NotNull Component formatToComponent(@NotNull String message) {
        return toComponent(format(message));
    }

    public static @NotNull String toLegacy(@NotNull Component component) {
        return LEGACY_COMPONENT_SERIALIZER.serialize(component);
    }

    public static @NotNull Component toComponent(@NotNull String message) {
        return LEGACY_COMPONENT_SERIALIZER.deserialize(message);
    }
}
