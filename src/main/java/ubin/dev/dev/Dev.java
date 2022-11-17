package ubin.dev.dev;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import ubin.dev.dev.commands.MainCommand;
import ubin.dev.dev.commands.broadcastCommand;
import ubin.dev.dev.commands.punishCommand;
import ubin.dev.dev.commands.smartRestart;
import ubin.dev.dev.listeners.devGuiHandler;
import ubin.dev.dev.listeners.punishGuiHandler;

public final class Dev extends JavaPlugin {

    // Chat Prefix
    public static final String chatPrefix = ChatColor.GOLD + "[" + ChatColor.BLUE + "Ubinit Dev Tools" + ChatColor.GOLD + "] " + ChatColor.RED ;


    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("\n[Ubiniti Developer Tools] - Loaded Successfully!\n");

        // Commands
        getCommand("development").setExecutor(new MainCommand());
        getCommand("broadcast").setExecutor(new broadcastCommand());
        getCommand("smartrestart").setExecutor(new smartRestart());
        getCommand("punish").setExecutor(new punishCommand());

        // Events
        getServer().getPluginManager().registerEvents(new devGuiHandler(), this);
        getServer().getPluginManager().registerEvents(new punishGuiHandler(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("\n[Ubiniti Developer Tools] - Plugin has been shutdown successfully!\n");

    }
}
