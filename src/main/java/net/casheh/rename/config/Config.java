package net.casheh.rename.config;

import net.casheh.rename.Rename;
import net.casheh.rename.util.Util;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private final Rename plugin;

    private String prefix;

    private String noPermission;

    private String invalidItem;

    private String tooLong;

    private String successful;

    private String cleared;

    private int maxLength;

    private List<String> blacklist;

    public Config(Rename plugin) {
        this.plugin = plugin;
    }

    public void assign() {
        this.plugin.reloadConfig();
        this.prefix = this.plugin.getConfig().getString("messages.prefix");
        this.noPermission = this.plugin.getConfig().getString("messages.no-permission");
        this.invalidItem = this.plugin.getConfig().getString("messages.invalid-item");
        this.tooLong = this.plugin.getConfig().getString("messages.too-long");
        this.successful = this.plugin.getConfig().getString("messages.successful");
        this.cleared = this.plugin.getConfig().getString("messages.cleared");
        this.maxLength = this.plugin.getConfig().getInt("items.max-length");
        this.blacklist = this.plugin.getConfig().getStringList("items.blacklist");
    }

    public String getPrefix() {
        return Util.color(this.prefix);
    }

    public String getNoPermission() {
        return getPrefix() + Util.color(this.noPermission);
    }

    public String getInvalidItem() {
        return getPrefix() + Util.color(this.invalidItem);
    }

    public String getTooLong() {
        return getPrefix() + Util.color(this.tooLong);
    }

    public String getSuccessful() {
        return getPrefix() + Util.color(this.successful);
    }

    public String getCleared() {
        return getPrefix() + Util.color(this.cleared);
    }

    public int getMaxLength() {
        return this.maxLength;
    }

    public boolean isBlacklisted(Material type) {
        List<Material> blacklist = new ArrayList<>();
        this.blacklist.forEach(str -> blacklist.add(Material.valueOf(str)));
        return blacklist.contains(type);
    }




}
