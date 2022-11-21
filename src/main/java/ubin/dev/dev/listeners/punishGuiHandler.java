package ubin.dev.dev.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import ubin.dev.dev.Dev;
import ubin.dev.dev.utilities.punishGui;

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
                   ItemStack targetI = e.getView().getItem(4);
                   assert targetI != null;
                   Player target = Bukkit.getPlayer("" + ChatColor.RESET + targetI.getItemMeta().getDisplayName());
                   punishGui.openKickOptions(p, target);
               }
               if (e.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.RED + ChatColor.BOLD + "Ban Player")) {

               }
               e.setCancelled(true);
           }
           if (e.getView().getTitle().equals(ChatColor.GOLD + "Kick Options")) {

               if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Go Back")) {
                   ItemStack targetI = e.getView().getItem(4);
                   assert targetI != null;
                   Player target = Bukkit.getPlayer("" + ChatColor.RESET + targetI.getItemMeta().getDisplayName());
                   assert target != null;
                   punishGui.openPunishmentGui(p, target);
               }
               e.setCancelled(true);

           }

       }

    }

}
