package ubin.dev.dev.utilities;

import jdk.jfr.internal.PlatformRecorder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class punishGui {

    public static void openPunishmentGui(Player p, Player target) {

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

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta playerHeadM = (SkullMeta)  playerHead.getItemMeta();
        assert playerHeadM != null;
        playerHeadM.setDisplayName(target.getDisplayName());
        playerHeadM.setOwnerProfile(target.getPlayerProfile());
        playerHead.setItemMeta(playerHeadM);

        menu.setItem(11, kick);
        menu.setItem(15, ban);
        menu.setItem(4, playerHead);

        p.openInventory(menu);


    }

    public static void openKickOptions(Player p, Player target) {

        Inventory menu = Bukkit.createInventory(p, 27, ChatColor.GOLD + "Kick Options");

        // Back button
        ItemStack back = new ItemStack(Material.BARRIER, 1);
        ItemMeta backM = back.getItemMeta();
        assert backM != null;
        backM.setDisplayName(ChatColor.RED + "Go Back");
        back.setItemMeta(backM);

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta playerHeadM = (SkullMeta)  playerHead.getItemMeta();
        assert playerHeadM != null;
        playerHeadM.setDisplayName(target.getDisplayName());
        playerHeadM.setOwnerProfile(target.getPlayerProfile());
        playerHead.setItemMeta(playerHeadM);

        menu.setItem(26, back);
        menu.setItem(4, playerHead);

        p.openInventory(menu);

    }

}
