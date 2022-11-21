package ubin.dev.dev.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import ubin.dev.dev.Dev;

public class playerChatted implements Listener {

    private static boolean chatEnabled = true;
    Plugin plugin = Dev.getPlugin(Dev.class);

    @EventHandler
    public void chatted(AsyncPlayerChatEvent e) {
        if (!chatEnabled) {
            if (plugin.getConfig().getBoolean("opsChatOnLock")) {
                if (!e.getPlayer().isOp()) {
                    e.getPlayer().sendMessage(ChatColor.RED + "Error: " + ChatColor.WHITE + "Chat is locked!");
                    e.setCancelled(true);
                }
            } else {
                e.getPlayer().sendMessage(ChatColor.RED + "Error: " + ChatColor.WHITE + "Chat is locked!");
                e.setCancelled(true);
            }
        }
    }

    public static void setChatEnabled(boolean v) {
        chatEnabled = v;
    }



}
