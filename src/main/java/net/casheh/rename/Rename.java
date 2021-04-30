package net.casheh.rename;

import net.casheh.rename.commands.RenameCommand;
import net.casheh.rename.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class Rename extends JavaPlugin {

    private Config config;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        this.getConfig().options().copyDefaults(true);

        config = new Config(this);

        config.assign();

        getCommand("rename").setExecutor(new RenameCommand(this));

    }

    public Config getCfg() {
        return this.config;
    }
}
