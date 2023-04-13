package fr.cyberdelta88.IdkWars.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Clickguievent implements Listener {



    @EventHandler
    public void guiclickevent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();


        Scoreboard sb = p.getScoreboard();


        if (e.getClickedInventory().getName().equalsIgnoreCase(ChatColor.GOLD + "TeamSelector")) {
            Team blue = sb.getTeam("blue");
            Team red = sb.getTeam("red");
            switch (e.getCurrentItem().getType()) {
                case WOOL :
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

                case STAINED_GLASS_PANE :
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
            }
            e.setCancelled(true);
        }
        p.setScoreboard(sb);
    }
}
