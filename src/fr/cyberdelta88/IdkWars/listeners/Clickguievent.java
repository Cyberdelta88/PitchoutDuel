package fr.cyberdelta88.IdkWars.listeners;

import fr.cyberdelta88.IdkWars.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Clickguievent implements Listener {

Plugin pl = Main.getPlugin(Main.class);

    @EventHandler
    public void guiclickevent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard sb = manager.getMainScoreboard();


        if (e.getClickedInventory().getName().equalsIgnoreCase(ChatColor.GOLD + "TeamSelector")) {
            Team blue = sb.getTeam("blue");
            Team red = sb.getTeam("red");
            switch (e.getCurrentItem().getItemMeta().getDisplayName()) {
                case "Join blue team" :
                    p.closeInventory();
                    p.sendMessage("debug blue");


                    if (blue.getEntries().contains(p)) {
                            blue.removePlayer(p);
                            p.setGameMode(GameMode.SURVIVAL);
                        } else {
                            blue.addPlayer(p);
                            red.removePlayer(p);
                        }

                    break;

                case "Join red team" :
                    p.closeInventory();
                    p.sendMessage("debug red");

                    if (red.getEntries().contains(p)) {
                        red.removePlayer(p);
                        p.setGameMode(GameMode.SURVIVAL);
                    } else {
                        red.addPlayer(p);
                        blue.removePlayer(p);
                    }
                    break;

                case "Empty the teams" :
                    p.closeInventory();
                    p.sendMessage("Maybe it will work one day");
            }

            e.setCancelled(true);
            p.playSound(p.getLocation(), Sound.NOTE_STICKS, 3.0F, 0.533F);

        }
        p.setScoreboard(sb);
    }
}
