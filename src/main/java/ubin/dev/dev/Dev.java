package ubin.dev.dev;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import ubin.dev.dev.commands.*;
import ubin.dev.dev.files.configGenerator;
import ubin.dev.dev.listeners.*;

public final class Dev extends JavaPlugin {

    // Chat Prefix
    public static final String chatPrefix = ChatColor.GOLD + "[" + ChatColor.BLUE + "Ubiniti Dev Tools" + ChatColor.GOLD + "] " + ChatColor.RED ;
    public static final String pluginVersion = "0.32A";


    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("\n[Ubiniti Developer Tools] - Loaded Successfully!\n");

        loadConfig();

//        configGenerator.setup();
//        configGenerator.get().addDefault("motdTitle", "Your server name!");
//        configGenerator.get().addDefault("motdFooter", "Additional info here!");
//        configGenerator.get().options().copyDefaults(true);
//        configGenerator.save();

        // Commands
        getCommand("development").setExecutor(new MainCommand());
        getCommand("broadcast").setExecutor(new broadcastCommand());
        getCommand("smartrestart").setExecutor(new smartRestart());
        getCommand("punish").setExecutor(new punishCommand());
        getCommand("chat").setExecutor(new chatCommand());
        getCommand("testing").setExecutor(new testingClass());
        getCommand("dev").setExecutor(new devCommand());

        // Events
        getServer().getPluginManager().registerEvents(new devGuiHandler(), this);
        getServer().getPluginManager().registerEvents(new punishGuiHandler(), this);
        getServer().getPluginManager().registerEvents(new playerChatted(), this);
        getServer().getPluginManager().registerEvents(new motdHandler(), this);
        getServer().getPluginManager().registerEvents(new chatCensor(), this);

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
