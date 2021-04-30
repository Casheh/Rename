package net.casheh.rename.commands;

import net.casheh.rename.Rename;
import net.casheh.rename.util.Util;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand implements CommandExecutor {

    private Rename plugin;

    public RenameCommand(Rename plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender)
            return false;
        Player p = (Player) sender;
        if (!sender.hasPermission("rename.rename")) {
            sender.sendMessage(plugin.getCfg().getNoPermission());
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(plugin.getCfg() + Util.color("&cInvalid usage! Valid subcommands:"));
            sender.sendMessage(plugin.getCfg() + Util.color("&cUsage: /rename <name>"));
            sender.sendMessage(plugin.getCfg() + Util.color("&cUsage: /rename clear"));
            return false;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("rename.reload")) {
                sender.sendMessage(plugin.getCfg().getNoPermission());
                return false;
            }
            plugin.getCfg().assign();
            sender.sendMessage(plugin.getCfg().getPrefix() + Util.color("&aConfiguration files reloaded!"));
        }

        if (args[0].equalsIgnoreCase("clear")) {
            if (p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().hasItemMeta()) {
                ItemStack hand = p.getInventory().getItemInMainHand();
                ItemMeta meta = hand.getItemMeta();
                meta.setDisplayName(null);
                meta.setLore(null);
                hand.setItemMeta(meta);
            } else {
                sender.sendMessage(plugin.getCfg().getInvalidItem());
                return false;
            }
        }

        if (p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR) {
            ItemStack hand = p.getInventory().getItemInMainHand();
            if (plugin.getCfg().isBlacklisted(hand.getType()) && !p.hasPermission("rename.bypass")) {
                p.sendMessage(plugin.getCfg().getInvalidItem());
                return false;
            }
            String text = String.join(" ", args);
            ItemMeta meta = hand.getItemMeta();
            if (p.hasPermission("rename.color")) {
                if (Util.strip(Util.color(text)).length() > plugin.getCfg().getMaxLength() && !p.hasPermission("rename.bypass")) {
                    p.sendMessage(plugin.getCfg().getTooLong());
                    return false;
                }
                meta.setDisplayName(Util.color(text));
            } else {
                if (text.length() > plugin.getCfg().getMaxLength() && !p.hasPermission("rename.bypass")) {
                    p.sendMessage(plugin.getCfg().getTooLong());
                    return false;
                }
                meta.setDisplayName(text);
            }
            hand.setItemMeta(meta);
            p.getInventory().setItemInMainHand(hand);
            p.sendMessage(plugin.getCfg().getSuccessful());
        } else {
            p.sendMessage(plugin.getCfg().getInvalidItem());
            return false;
        }
        return true;
    }
}
