package ubin.dev.dev.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import ubin.dev.dev.Dev;

public class punishGuiHandler implements Listener {

    @EventHandler
    public void onPunishGuiClick(InventoryClickEvent e) {

       Player p = null;
       if (e.getWhoClicked() instanceof Player) {
           p = (Player) e.getWhoClicked();
       }
       if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {

           if (e.getView().getTitle().equals(ChatColor.DARK_AQUA + "Punish Player")) {
               if (e.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GOLD + ChatColor.BOLD + "Kick Player")) {
                   e.setCancelled(true);
                   p.sendMessage("You clicked the redstone!");
               }
               if (e.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.RED + ChatColor.BOLD + "Ban Player")) {
                   e.setCancelled(true);
                   p.sendMessage("You clicked the bedrock!");
               }
           }

       }

    }

}
