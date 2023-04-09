package fr.cyberdelta88.IdkWars;

import fr.cyberdelta88.IdkWars.commands.Cmdstart;
import fr.cyberdelta88.IdkWars.commands.Cmdteam;
import fr.cyberdelta88.IdkWars.listeners.Clickguievent;
import fr.cyberdelta88.IdkWars.listeners.Deathevent;
import fr.cyberdelta88.IdkWars.listeners.GivePerm;
import fr.cyberdelta88.IdkWars.listeners.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("debug la");
        this.getCommand("team").setExecutor(new Cmdteam());
        this.getCommand("start").setExecutor(new Cmdstart());

        getServer().getPluginManager().registerEvents(new Clickguievent(), this);
        getServer().getPluginManager().registerEvents(new Deathevent(), this);
        getServer().getPluginManager().registerEvents(new GivePerm(), this);
        getServer().getPluginManager().registerEvents(new Scoreboard(), this);

        getServer().getScoreboardManager().getNewScoreboard();

        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
}

