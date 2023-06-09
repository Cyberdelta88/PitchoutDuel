package fr.cyberdelta88.IdkWars;

import fr.cyberdelta88.IdkWars.commands.*;
import fr.cyberdelta88.IdkWars.listeners.Clickguievent;
import fr.cyberdelta88.IdkWars.listeners.Deathevent;
import fr.cyberdelta88.IdkWars.listeners.GivePerm;
import fr.cyberdelta88.IdkWars.listeners.Scoreboard;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("debug la");
        this.getCommand("team").setExecutor(new Cmdteam());
        this.getCommand("start").setExecutor(new Cmdstart());
        this.getCommand("setredspawn").setExecutor(new Cmdsetredspawn());
        this.getCommand("setbluespawn").setExecutor(new CmdBLueSpawn());
        this.getCommand("setrespawn").setExecutor(new CmdRespawn());

        getServer().getPluginManager().registerEvents(new Clickguievent(), this);
        getServer().getPluginManager().registerEvents(new Deathevent(), this);
        getServer().getPluginManager().registerEvents(new GivePerm(), this);
        getServer().getPluginManager().registerEvents(new Scoreboard(), this);

        getServer().getScoreboardManager().getNewScoreboard();

        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
}

