package ubin.dev.dev.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ubin.dev.dev.Dev;

import java.util.StringJoiner;

public class smartRestart implements CommandExecutor {

    private String restartReason = "";
    private boolean initRestart = false;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // If command is issued by a player in game
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.isOp()) {
                if (strings.length >= 1 && !(strings[0].equalsIgnoreCase("confirm"))) {
                    initRestart = true;
                    StringJoiner builder = new StringJoiner(" ");
                    for (int i = 0; i<strings.length; i++) {
                        builder.add(strings[i]);
                    }
                    restartReason = builder.toString();
                    player.sendMessage("" + ChatColor.DARK_RED + ChatColor.BOLD + "Notice! " + ChatColor.WHITE + "Please type "+ChatColor.GOLD+"/smartrestart" + ChatColor.GREEN + " confirm " + ChatColor.WHITE + "to confirm a restart!");
                } else if (strings.length == 1 && strings[0].equalsIgnoreCase("confirm")) {
                    System.out.println("Restart Confirmed! Restarted for the reason: " + restartReason);
                    for (Player plr : Bukkit.getOnlinePlayers()) {
                        plr.kickPlayer(ChatColor.GOLD + "Server is restarting!\n" + ChatColor.RED + "Reason: " + ChatColor.WHITE + restartReason + ChatColor.AQUA + "\n\nYou should be able to rejoin in a few minutes!");
                    }
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "restart");
                } else {
                    initRestart = false;
                    restartReason = "";
                    player.sendMessage(Dev.chatPrefix + "Invalid command usage! /smartrestart {reason}");
                }

            } else {
                player.sendMessage(Dev.chatPrefix + "Invalid Permissions!");
            }
        } else {
            // If the command is issued by the console
            if (strings.length >= 1 && !(strings[0].equalsIgnoreCase("confirm"))) {
                initRestart = true;
                StringJoiner builder = new StringJoiner(" ");
                for (int i = 0; i<strings.length; i++) {
                    builder.add(strings[i]);
                }
                restartReason = builder.toString();
                System.out.println("Please type \"/smartrestart confirm\" to confirm a restart!");
            } else if (strings.length == 1 && strings[0].equalsIgnoreCase("confirm") && initRestart) {
                System.out.println("Restart Confirmed! Restarted for the reason: " + restartReason);
                for (Player plr : Bukkit.getOnlinePlayers()) {
                    plr.kickPlayer(ChatColor.GOLD + "Server is restarting!\n" + ChatColor.RED + "Reason: " + ChatColor.WHITE + restartReason + ChatColor.AQUA + "\n\nYou should be able to rejoin in a few minutes!");
                }
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "restart");
            } else {
                initRestart = false;
                restartReason = "";
                System.out.println("Invalid command usage! /smartrestart {reason}");
            }
        }
        return true;
    }
}
