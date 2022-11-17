package ubin.dev.dev.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import ubin.dev.dev.Dev;
import ubin.dev.dev.utilities.devGui;

import java.util.Objects;


public class devGuiHandler implements Listener {

    @EventHandler
    public void onDevGuiClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "Development Option")) {

            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
                    devGui.openRestartOptions(p);
                }
                if (e.getCurrentItem().getType().equals(Material.BEDROCK)) {
                    System.out.println("Bedrock was clicked!");
                }
            }

            e.setCancelled(true);

        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "Restart Reasons")) {

            if (e.getCurrentItem() != null) {

                if (e.getCurrentItem() != null) {

                    if (e.getCurrentItem().getType().equals(Material.RED_WOOL)) {
                        devGui.confirmMenu(p, "Confirm Restart", "Cancel");
                    }
                    if (e.getCurrentItem().getType().equals(Material.ORANGE_WOOL)) {
                        devGui.confirmMenu(p, "Confirm Restart", "Cancel");
                    }
                    if (e.getCurrentItem().getType().equals(Material.YELLOW_WOOL)) {
                        devGui.confirmMenu(p, "Confirm Restart", "Cancel");
                    }

                }

            }

        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Confirm")) {

            if (e.getCurrentItem() != null) {

                if (e.getCurrentItem() != null) {

                    if (e.getCurrentItem().getType().equals(Material.GREEN_WOOL)) {
                        // Process Action
                        p.closeInventory();
                        boolean init = initiateRestart(p, "testing");
                        if (init) {
                            p.sendMessage(Dev.chatPrefix + ChatColor.DARK_RED + "Restart has been initiated. Restarting in 30 seconds!");
                        }
                        System.out.println("  _   _       _   _            _ \n" +
                                " | \\ | |     | | (_)          | |\n" +
                                " |  \\| | ___ | |_ _  ___ ___  | |\n" +
                                " | . ` |/ _ \\| __| |/ __/ _ \\ | |\n" +
                                " | |\\  | (_) | |_| | (_|  __/ |_|\n" +
                                " |_| \\_|\\___/ \\__|_|\\___\\___| (_)\n" +
                                "                                 \n" +
                                " Restart Initiated! Server will restart in 30 seconds!");
                    }
                    if (e.getCurrentItem().getType().equals(Material.RED_WOOL)) {
                        devGui.openRestartOptions(p);
                    }

                }

            }

        }
//ChatColor.GREEN + "Confirm"
    }

    public boolean initiateRestart(Player p, String reason) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(ChatColor.RED + "Notice!", "Server will be restarting in 30 seconds!", 1, 6 * 20, 1);
        }

//        Bukkit.getScheduler().runTaskLater(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin()), () -> {
//            for (Player player : Bukkit.getOnlinePlayers()) {
//                player.sendTitle(ChatColor.RED + "Notice!", "Server will be restarting in 30 seconds!", 1, 6 * 20, 1);
//            }
//        }, 80L);
        return true;
    }

}
