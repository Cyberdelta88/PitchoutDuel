package fr.cyberdelta88.IdkWars.commands;

import fr.cyberdelta88.IdkWars.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdBLueSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Plugin pl = Main.getPlugin(Main.class);

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length > 2) {

                pl.getConfig().set("xblue", args[0]);
                pl.getConfig().set("yblue", args[1]);
                pl.getConfig().set("zblue", args[2]);


                String xcoords = pl.getConfig().getString("xblue");
                String ycoords = pl.getConfig().getString("yblue");
                String zcoords = pl.getConfig().getString("zblue");

                p.sendMessage(ChatColor.DARK_AQUA + "You set the blue team spawn coordinates to x : " + xcoords +", y : " + ycoords + ", z : " + zcoords);


            } else {
                p.sendMessage(ChatColor.RED + "/" + label + " {x coordinates} {y coordinates} {z coordinates}");
            }
        }
        return false;
    }
}
