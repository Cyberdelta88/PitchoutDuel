package fr.cyberdelta88.IdkWars.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

public class Scoreboard implements ScoreboardManager {

    @EventHandler
    public void onPlayerJoinn(PlayerJoinEvent e) {
        createScoreboard(e.getPlayer());
        updateScoreboardjoin();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        updateScoreboardjoin();
    }

    public void createScoreboard(Player p) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("Stats", "Dummy;");
        obj.setDisplayName(ChatColor.LIGHT_PURPLE + "Pitchout Duel");

        int redscore = 0;
        int bluescore = 0;


        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore("Nombre de joueurs : " + Bukkit.getOnlinePlayers().size());
        Score s2 = obj.getScore("");
        Score s3 = obj.getScore(ChatColor.RED + "RED" + "Red : " + ChatColor.WHITE + redscore + "/8" );
        Score s4 = obj.getScore(ChatColor.BLUE + "BLUE" + "Blue" + ChatColor.WHITE + bluescore + "/8" );

        score.setScore(1);
        s2.setScore(2);
        s3.setScore(3);
        s4.setScore(4);

        p.setScoreboard(board);


    }

    public void updateScoreboardjoin() {
        for (Player online : Bukkit.getOnlinePlayers()) {
            Score score = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore("Nombre de joueurs : " + Bukkit.getOnlinePlayers().size());
            score.setScore(1);
        }
    }

    public void uptateScoreboardgamered() {

    }
    
}
