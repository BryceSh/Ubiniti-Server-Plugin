package ubin.dev.dev.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ubin.dev.dev.listeners.playerChatted;

public class chatCommand implements CommandExecutor {

    private boolean confirmClear = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {

                if (args.length < 1) {
                    // If they just say /chat then this will run
                    player.sendMessage("" + ChatColor.GOLD + "Chat manager usage:\n" +
                            ChatColor.WHITE + "/chat clear - " + ChatColor.GREEN + "Clears chat\n" +
                            ChatColor.WHITE + "/chat lock - " + ChatColor.GREEN + "Locks server chat\n" +
                            ChatColor.WHITE + "/chat unlock - " + ChatColor.GREEN + "Unlocks server chat");
                } else {
                    // Anything thing else and this will run!
                    if (args[0].equalsIgnoreCase("clear")) {
                        if (confirmClear && args.length == 2 && args[1].equalsIgnoreCase("confirm")) {
                            confirmClear = false;
                            for (int i = 0; i<200; i++) {
                                Bukkit.broadcastMessage("\n");
                            }
                            Bukkit.broadcastMessage(ChatColor.DARK_RED + "=============================\n" +
                                    ChatColor.GOLD + "Chat has been cleared by:\n" + ChatColor.GREEN + player.getDisplayName() + "\n" +
                                    ChatColor.DARK_RED + "=============================");
                        } else {
                            player.sendMessage(ChatColor.DARK_RED + "Notice! " + ChatColor.RESET + "Please type " + ChatColor.GREEN + "/chat clear confirm " + ChatColor.RESET);
                            confirmClear = true;
                        }

                    } else if (args[0].equalsIgnoreCase("lock")) {
                        playerChatted.setChatEnabled(false);
                        Bukkit.broadcastMessage(ChatColor.DARK_RED + "Notice! " + ChatColor.WHITE + "Chat has been locked by " + ChatColor.GOLD + player.getDisplayName());
                    } else if (args[0].equalsIgnoreCase("unlock")) {
                        playerChatted.setChatEnabled(true);
                        Bukkit.broadcastMessage(ChatColor.GREEN + "Chat has been unlocked!");
                    }
                }


//                for (int i = 0; i<200; i++) {
//                    Bukkit.broadcastMessage("\n");
//                }
//                Bukkit.broadcastMessage(ChatColor.DARK_RED + "=============================\n" +
//                        ChatColor.GOLD + "Chat has been cleared by:\n" + ChatColor.GREEN + player.getDisplayName() + "\n" +
//                        ChatColor.DARK_RED + "=============================");

            }
        }
        return true;
    }
}
