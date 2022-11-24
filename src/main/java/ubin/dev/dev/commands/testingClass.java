package ubin.dev.dev.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ubin.dev.dev.Dev;

public class testingClass implements CommandExecutor {

    Plugin plugin = Dev.getPlugin(Dev.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                player.sendMessage("Broadcast will send in 10 seconds!");
                Bukkit.getScheduler().runTaskLater(plugin, () -> Bukkit.broadcastMessage("10 seconds later"), 20 * 10);
            }
        }
        return true;
    }

}
