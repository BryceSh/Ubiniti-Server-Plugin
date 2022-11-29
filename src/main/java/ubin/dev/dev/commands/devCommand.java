package ubin.dev.dev.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ubin.dev.dev.Dev;

public class devCommand implements CommandExecutor {
    Plugin plugin = Dev.getPlugin(Dev.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Player command handler
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.GREEN + "Dev plugin is developed by " + ChatColor.GOLD + "Ubiniti\n" + ChatColor.LIGHT_PURPLE + "Access to this plugin is private and" +
                        " developed for this server only!\n" + ChatColor.BLUE + "Version: " + ChatColor.AQUA + Dev.pluginVersion);
            } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (player.isOp()) {
                    plugin.reloadConfig();
                    player.sendMessage(Dev.chatPrefix + "Config Reloaded!");
                } else {
                    player.sendMessage(Dev.chatPrefix + "Invalid Permissions");
                }
            }
        } else {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                System.out.println(ChatColor.GREEN + "Config has been reloaded!");
            }
        }
        return true;
    }
}
