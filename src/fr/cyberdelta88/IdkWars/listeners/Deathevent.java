package fr.cyberdelta88.IdkWars.listeners;

import fr.cyberdelta88.IdkWars.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Deathevent implements Listener {

    @EventHandler
    public void deathevent(EntityDamageEvent e) {

        Plugin pl = Main.getPlugin(Main.class);

        if (e.getEntityType() == EntityType.PLAYER) {

            Player p = (Player) e.getEntity();
            p.setHealth(20);
            p.setFoodLevel(20);
            p.setSaturation(20);


            Scoreboard sb = p.getScoreboard();
            Team blue = sb.getTeam("blue");
            Team red = sb.getTeam("red");

            if (e.getCause().equals(EntityDamageEvent.DamageCause.LAVA)){
                if (blue != null) {
                    for (String entry : blue.getEntries()) { //non-player entities can be on teams
                        Player o = Bukkit.getPlayer(entry);
                        if (o != null) {

                            int xcoords = pl.getConfig().getInt("xblue");
                            int ycoords = pl.getConfig().getInt("yblue");
                            int zcoords = pl.getConfig().getInt("zblue");

                            Location loc_blue = new Location(o.getWorld(), xcoords, ycoords, zcoords);
                            o.teleport(loc_blue);
                            o.setHealth(20);
                            o.setScoreboard(sb);
                            blue.addPlayer(o);
                            o.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 500, 1));
                        }

                    }
                }
                if (red != null) {
                    for (String entry : red.getEntries()) {
                        Player o = Bukkit.getPlayer(entry);
                        if (o != null) {

                            int xcoords = pl.getConfig().getInt("xred");
                            int ycoords = pl.getConfig().getInt("yred");
                            int zcoords = pl.getConfig().getInt("zred");

                            Location loc_red = new Location(o.getWorld(), xcoords, ycoords, zcoords);
                            o.teleport(loc_red);
                            o.setHealth(20);
                            o.setScoreboard(sb);
                            red.addPlayer(o);
                            o.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 500, 1));
                        }
                    }
                }
            }


        }


    }
}
