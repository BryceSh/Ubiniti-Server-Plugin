package ubin.dev.dev.listeners;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;
import ubin.dev.dev.Dev;

public class motdHandler implements Listener {

    Plugin plugin = Dev.getPlugin(Dev.class);

    @EventHandler
    public void onServerPing(ServerListPingEvent e) {

        e.setMaxPlayers(plugin.getConfig().getInt("customPlayerCounter"));

        String topMOTD = plugin.getConfig().getString("motdTitle");
        String bottomMOTD = plugin.getConfig().getString("motdFooter");

        if (plugin.getConfig().getBoolean("motdTitleCenter")) {
            if (topMOTD.length() > 0) {
                int x = 27 - (topMOTD.length() / 2);
                for (int i = 0; i<x; i++) {
                    topMOTD = " " + topMOTD;
                }
            }
        }

        if (plugin.getConfig().getBoolean("motdFooterCenter")) {
            if (bottomMOTD.length() > 0) {
                int x = 26 - (bottomMOTD.length() / 2);
                for (int i = 0; i<x; i++) {
                    bottomMOTD = " " + bottomMOTD;
                }
            }

        }

        String generatedMOTD = topMOTD + "\n" + bottomMOTD;

        if (Bukkit.getBanList(BanList.Type.IP).isBanned(String.valueOf(e.getAddress()))) {
            generatedMOTD = ChatColor.DARK_RED + "Can't connect to server";
        }

        e.setMotd(generatedMOTD);

    }

}
