package ubin.dev.dev.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ubin.dev.dev.Dev;

import java.io.IOException;
import java.util.StringJoiner;

public class smartRestart implements CommandExecutor {

    private String restartReason = "";
    private int restartCounter = 0;
    private boolean initRestart = false;
    private boolean cancelRestart = false;
    Plugin plugin = Dev.getPlugin(Dev.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // If command is issued by a player in game
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.isOp()) {

                if (initRestart && strings.length == 1 && strings[0].equalsIgnoreCase("confirm")){
                    cancelRestart = false;
                    if (plugin.getConfig().getBoolean("announceRestarts")) {
                        Bukkit.broadcastMessage(Dev.chatPrefix + ChatColor.DARK_RED + "Notice! " + ChatColor.RED + "A server restart has been initiated! Server will restart in " + ChatColor.GOLD + restartCounter + ChatColor.RED + " seconds!");
                        if (restartCounter > 5) {
                            Bukkit.getScheduler().runTaskLater(plugin, () -> {if (!cancelRestart) {Bukkit.broadcastMessage(ChatColor.RED + "Restarting in 5 seconds!");}}, (restartCounter - 5) * 20L);
                            Bukkit.getScheduler().runTaskLater(plugin, () -> {if (!cancelRestart) {Bukkit.broadcastMessage(ChatColor.RED + "Restarting in 4 seconds!");}}, (restartCounter - 4) * 20L);
                            Bukkit.getScheduler().runTaskLater(plugin, () -> {if (!cancelRestart) {Bukkit.broadcastMessage(ChatColor.RED + "Restarting in 3 seconds!");}}, (restartCounter - 3) * 20L);
                            Bukkit.getScheduler().runTaskLater(plugin, () -> {if (!cancelRestart) {Bukkit.broadcastMessage(ChatColor.RED + "Restarting in 2 seconds!");}}, (restartCounter - 2) * 20L);
                            Bukkit.getScheduler().runTaskLater(plugin, () -> {if (!cancelRestart) {Bukkit.broadcastMessage(ChatColor.RED + "Restarting in 1 seconds!");}}, (restartCounter - 1) * 20L);
                            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                if (!cancelRestart) {
                                    for (Player plr : Bukkit.getOnlinePlayers()) {
                                        plr.kickPlayer(ChatColor.GOLD + "Server is restarting!\n" + ChatColor.RED + "Reason: " + ChatColor.WHITE + restartReason + ChatColor.AQUA + "\n\nYou should be able to rejoin in a few minutes!");
                                    }
                                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "restart");
                                }
                            }, (restartCounter + 1) * 20L);
                        } else if(restartCounter > 0) {
                            Bukkit.broadcastMessage(ChatColor.RED + "Restarting in " + restartCounter + " seconds!");
                            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                if (!cancelRestart) {
                                    for (Player plr : Bukkit.getOnlinePlayers()) {
                                        plr.kickPlayer(ChatColor.GOLD + "Server is restarting!\n" + ChatColor.RED + "Reason: " + ChatColor.WHITE + restartReason + ChatColor.AQUA + "\n\nYou should be able to rejoin in a few minutes!");
                                    }
                                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "restart");
                                }
                            }, restartCounter * 20L);
                        } else {
                            for (Player plr : Bukkit.getOnlinePlayers()) {
                                plr.kickPlayer(ChatColor.GOLD + "Server is restarting!\n" + ChatColor.RED + "Reason: " + ChatColor.WHITE + restartReason + ChatColor.AQUA + "\n\nYou should be able to rejoin in a few minutes!");
                            }
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "restart");
                        }
                    } else {
                        player.sendMessage(Dev.chatPrefix + ChatColor.LIGHT_PURPLE + "You've initiated a restart. Only you will see this message!");
                        player.sendMessage("Restarting server in " +ChatColor.GOLD+ restartCounter +ChatColor.RESET+ " seconds!");
                    }
                } else if (strings.length == 1 && strings[0].equalsIgnoreCase("cancel")) {
                    cancelRestart = true;
                    player.sendMessage(Dev.chatPrefix + ChatColor.GREEN + "Restart has been canceled successfully!");
                } else if (strings.length < 2) {
                    player.sendMessage(ChatColor.GOLD + "How to use SmartRestart:\n" +
                            ChatColor.WHITE + "/smartrestart {time in seconds - 0 for instant} {reason}");
                    initRestart = false;
                    cancelRestart = false;
                } else {
                    if (isInteger(strings[0])) {
                        restartCounter = Integer.parseInt(strings[0]);
                    } else {
                        player.sendMessage(ChatColor.RED + "SmartRestart Error: " + ChatColor.WHITE + "Invalid time integer! Please try again!");
                        initRestart = false;
                        cancelRestart = false;
                        return true;
                    }
                    StringJoiner reason = new StringJoiner(" ");
                    for (int i = 1; i<strings.length; i++) {
                        reason.add(strings[i]);
                    }
                    restartReason = reason.toString();
                    player.sendMessage(ChatColor.RED + "Notice! " + ChatColor.WHITE + "Please type " + ChatColor.GREEN + "/smartrestart confirm " + ChatColor.WHITE + "to initiate the restart!");
                    initRestart = true;
                    cancelRestart = false;
//                    if (plugin.getConfig().getBoolean("announceRestarts")) {
//                        Bukkit.broadcastMessage(ChatColor.RED + "Attention! " + ChatColor.WHITE + "A restart has been initiated! Server will restart in " +
//                                ChatColor.GOLD + restartCounter + ChatColor.WHITE + " seconds!");
//                        player.sendMessage(Dev.chatPrefix + ChatColor.WHITE + "Restart has been initiated!");
//                    }
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

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

}
