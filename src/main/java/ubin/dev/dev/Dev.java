package ubin.dev.dev;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import ubin.dev.dev.commands.*;
import ubin.dev.dev.listeners.devGuiHandler;
import ubin.dev.dev.listeners.playerChatted;
import ubin.dev.dev.listeners.punishGuiHandler;

public final class Dev extends JavaPlugin {

    // Chat Prefix
    public static final String chatPrefix = ChatColor.GOLD + "[" + ChatColor.BLUE + "Ubinit Dev Tools" + ChatColor.GOLD + "] " + ChatColor.RED ;


    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("\n[Ubiniti Developer Tools] - Loaded Successfully!\n");

        loadConfig();

        // Commands
        getCommand("development").setExecutor(new MainCommand());
        getCommand("broadcast").setExecutor(new broadcastCommand());
        getCommand("smartrestart").setExecutor(new smartRestart());
        getCommand("punish").setExecutor(new punishCommand());
        getCommand("chat").setExecutor(new chatCommand());

        // Events
        getServer().getPluginManager().registerEvents(new devGuiHandler(), this);
        getServer().getPluginManager().registerEvents(new punishGuiHandler(), this);
        getServer().getPluginManager().registerEvents(new playerChatted(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("\n[Ubiniti Developer Tools] - Plugin has been shutdown successfully!\n");

    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
