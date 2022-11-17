package ubin.dev.dev.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ubin.dev.dev.Dev;
import ubin.dev.dev.utilities.devGui;

import java.sql.SQLOutput;

public class MainCommand implements CommandExecutor{


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;
            if (player.isOp()) {

                devGui.openDevGui(player);

            } else {
                player.sendMessage(Dev.chatPrefix + "Invalid Permissions!");
            }

        }

        return true;
    }
}
