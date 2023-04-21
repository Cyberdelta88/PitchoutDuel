package fr.cyberdelta88.IdkWars.commands;

import fr.cyberdelta88.IdkWars.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Cmdsetredspawn implements CommandExecutor {

    Plugin pl = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length >= 3) {
                pl.getConfig().set("xred", args[0]);
                pl.getConfig().set("yred", args[1]);
                pl.getConfig().set("zred", args[2]);

                int xcoords = pl.getConfig().getInt("xred");
                int ycoords = pl.getConfig().getInt("yred");
                int zcoords = pl.getConfig().getInt("zred");

                p.sendMessage(ChatColor.DARK_AQUA + "You set the red team spawn coordinates to x : " + xcoords +", y : " + ycoords + ", z : " + zcoords);

            } else {
                p.sendMessage(ChatColor.RED + "/setredspawn {x coordinates} {y coordinates} {z coordinates}");
            }
        }


        return false;
    }
}