package ubin.dev.dev.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ubin.dev.dev.Dev;
import ubin.dev.dev.utilities.devGui;

import java.util.StringJoiner;

public class broadcastCommand implements CommandExecutor{


    private static final String boardcastChatPrefix = "" + ChatColor.GOLD + ChatColor.BOLD + "[" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Broadcast" + ChatColor.GOLD + ChatColor.BOLD + "] " + ChatColor.AQUA;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;
            if (player.isOp()) {

                if (strings.length >= 2 ) {
                    StringJoiner message = new StringJoiner(" ");
                    for (int i = 1; i < strings.length; i++ ) {
                        message.add(strings[i]);
                    }
                    if (strings[0].equalsIgnoreCase("chat")) {
                        // Code for the chat message
                        Bukkit.broadcastMessage(boardcastChatPrefix + message);
                    } else if (strings[0].equalsIgnoreCase("title")) {
                        // Code for sending a title to all players
                        for (Player plr : Bukkit.getOnlinePlayers()) {
                            plr.sendTitle(ChatColor.RED + "Broadcast", "" + message, 1, 10 * 20, 1);
                        }


                    } else {
                        player.sendMessage(Dev.chatPrefix + "Invalid Arguments! /broadcast help");
                    }

                } else if (strings.length == 1) {
                    if (strings[0].equalsIgnoreCase("help")) {
                        player.sendMessage(Dev.chatPrefix + ChatColor.GREEN +"Help Menu:");
                        player.sendMessage(ChatColor.GOLD + "Usage: " + ChatColor.WHITE +"/broadcast " + ChatColor.LIGHT_PURPLE + "<chat | title> " + ChatColor.BLUE + "{message}");
                    } else {
                        player.sendMessage(Dev.chatPrefix + "Invalid Arguments! /broadcast help");
                    }
                } else {
                    player.sendMessage(Dev.chatPrefix + "Invalid Arguments! /broadcast help");
                }

//                player.sendMessage(Dev.chatPrefix + "Invalid Arguments");


            } else {
                player.sendMessage(Dev.chatPrefix + "Invalid Permissions!");
            }

        }
        return true;
    }
}
