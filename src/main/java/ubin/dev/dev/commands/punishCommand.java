package ubin.dev.dev.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ubin.dev.dev.Dev;
import ubin.dev.dev.utilities.punishGui;

public class punishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;
            if (player.isOp()) {

                if (strings.length == 1) {
                    Player target = Bukkit.getPlayer(strings[0]);
                    if (target != null && target.isOnline()) {
                        punishGui.openPunishmentGui(player, target);
                    } else {
                        player.sendMessage("Player is offline!");
                    }
                } else {
                    player.sendMessage(Dev.chatPrefix + "Invalid Usage! " + ChatColor.GOLD + "/punish {username}");
                }

            } else {
                player.sendMessage(Dev.chatPrefix + "Invalid Permissions!");
            }

        }

        return true;
    }
}
