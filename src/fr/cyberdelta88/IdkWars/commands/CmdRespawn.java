package fr.cyberdelta88.IdkWars.commands;

import fr.cyberdelta88.IdkWars.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdRespawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Plugin pl = Main.getPlugin(Main.class);

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length > 2) {

                try{
                    int num = Integer.parseInt(args[0]);
                    pl.getConfig().set("xrespawn", num);
                } catch (NumberFormatException e) {

                }

                try{
                    int num = Integer.parseInt(args[1]);
                    pl.getConfig().set("yrespawn", num);;
                } catch (NumberFormatException e) {

                }

                try{
                    int num = Integer.parseInt(args[2]);
                    pl.getConfig().set("zrespawn", num);
                } catch (NumberFormatException e) {

                }


                int xcoords = pl.getConfig().getInt("xrespawn");
                int ycoords = pl.getConfig().getInt("yrespawn");
                int zcoords = pl.getConfig().getInt("zrespawn");

                p.sendMessage(ChatColor.DARK_AQUA + "You set the respawn coordinates to x : " + xcoords +", y : " + ycoords + ", z : " + zcoords);


            } else {
                p.sendMessage(ChatColor.RED + "/" + label + " {x coordinates} {y coordinates} {z coordinates}");
            }
        }
        return false;
    }
}
