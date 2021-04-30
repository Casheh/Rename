package net.casheh.rename.util;

import org.bukkit.ChatColor;

public class Util {

    public static String color(String raw) {
        return ChatColor.translateAlternateColorCodes('&', raw);
    }

    public static String strip(String raw) {
        return ChatColor.stripColor(raw);
    }

}
