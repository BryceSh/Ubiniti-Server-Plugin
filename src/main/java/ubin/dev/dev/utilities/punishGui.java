package ubin.dev.dev.utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class punishGui {

    public static void openPunishmentGui(Player p) {

        Inventory menu = Bukkit.createInventory(p, 27, ChatColor.DARK_AQUA + "Punish Player");

        ItemStack kick = new ItemStack(Material.REDSTONE);
        ItemMeta kickM = kick.getItemMeta();
        assert kickM != null;
        kickM.setDisplayName("" + ChatColor.GOLD + ChatColor.BOLD + "Kick Player");
        kick.setItemMeta(kickM);

        ItemStack ban = new ItemStack(Material.BEDROCK);
        ItemMeta banM = ban.getItemMeta();
        assert banM != null;
        banM.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "Ban Player");
        ban.setItemMeta(banM);


        menu.setItem(11, kick);
        menu.setItem(15, ban);

        p.openInventory(menu);


    }

}
