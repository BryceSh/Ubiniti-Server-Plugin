package ubin.dev.dev.utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class devGui {

    public static void openDevGui(Player p) {

        Inventory menu = Bukkit.createInventory(p, 27, ChatColor.DARK_RED + "Development Option");

        ItemStack restart = new ItemStack(Material.BARRIER, 1);
        ItemMeta restartM = restart.getItemMeta();
        assert restartM != null;
        restartM.setDisplayName(ChatColor.GOLD + "Restart Options");
        restart.setItemMeta(restartM);

        ItemStack shutdown = new ItemStack(Material.BEDROCK, 1);
        ItemMeta shutdownM = shutdown.getItemMeta();
        assert shutdownM != null;
        shutdownM.setDisplayName(ChatColor.DARK_RED + "Shutdown Options");
        shutdown.setItemMeta(shutdownM);


        menu.setItem(17, shutdown);
        menu.setItem(10, restart);

        p.openInventory(menu);

    }

    public static void openRestartOptions(Player p) {

        Inventory menu = Bukkit.createInventory(p, 27, ChatColor.DARK_RED + "Restart Reasons");

        ItemStack update = new ItemStack(Material.RED_WOOL, 1);
        ItemMeta updateM = update.getItemMeta();
        assert updateM != null;
        updateM.setDisplayName(ChatColor.GOLD + "Game Update");
        update.setItemMeta(updateM);

        ItemStack lag = new ItemStack(Material.ORANGE_WOOL, 1);
        ItemMeta lagM = update.getItemMeta();
        assert lagM != null;
        lagM.setDisplayName(ChatColor.GOLD + "Game Lag");
        lag.setItemMeta(lagM);

        ItemStack other = new ItemStack(Material.YELLOW_WOOL, 1);
        ItemMeta otherM = update.getItemMeta();
        assert otherM != null;
        otherM.setDisplayName(ChatColor.GOLD + "Other");
        other.setItemMeta(otherM);

        menu.setItem(10, update);
        menu.setItem(11, lag);
        menu.setItem(12, other);

        p.openInventory(menu);

    }

    public static void confirmMenu(Player p, String confirmText, String cancelText) {

        Inventory menu = Bukkit.createInventory(p, 9, ChatColor.GREEN + "Confirm");

        ItemStack confirm = new ItemStack(Material.GREEN_WOOL, 1);
        ItemMeta confirmM = confirm.getItemMeta();
        assert confirmM != null;
        confirmM.setDisplayName(ChatColor.GREEN +  confirmText);
        confirm.setItemMeta(confirmM);

        ItemStack cancel = new ItemStack(Material.RED_WOOL, 1);
        ItemMeta cancelM = cancel.getItemMeta();
        assert cancelM != null;
        cancelM.setDisplayName(ChatColor.RED +  cancelText);
        cancel.setItemMeta(cancelM);

        menu.setItem(2, confirm);
        menu.setItem(6, cancel);

        p.openInventory(menu);


    }

}
