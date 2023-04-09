package fr.cyberdelta88.IdkWars.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class GivePerm implements Listener {
    @EventHandler
    public void onPlayerjoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
    }
}
