package ubin.dev.dev.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ubin.dev.dev.Dev;

public class devCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.GREEN + "Dev plugin is developed by " + ChatColor.GOLD + "Ubiniti\n" + ChatColor.LIGHT_PURPLE + "Access to this plugin is private and" +
                    " developed for this server only!\n" + ChatColor.BLUE + "Version: " + ChatColor.AQUA + Dev.pluginVersion);
        }
        return true;
    }
}
