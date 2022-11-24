package ubin.dev.dev.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class configGenerator {

    private static File file;
    private  static FileConfiguration config;

    public static void setup() {
       file = new File(Bukkit.getServer().getPluginManager().getPlugin("Dev").getDataFolder(), "motd.yml");
       if (!file.exists()) {
           try {
               file.createNewFile();
           } catch (IOException e) {
               System.out.println("Configuration Error!");
           }
       }
    }

    public static FileConfiguration get() {
        return config;
    }

    public static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            System.out.println("File saving error!");
        }
    }

    public static void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }

}
