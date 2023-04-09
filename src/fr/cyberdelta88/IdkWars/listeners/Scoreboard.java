package fr.cyberdelta88.IdkWars.listeners;

import fr.cyberdelta88.IdkWars.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.*;

public class Scoreboard implements Listener {

    Plugin pl = Main.getPlugin(Main.class);

    @EventHandler
    public void onPlayerJoinTeam(PlayerTeleportEvent e) {
        createScoreboard(e.getPlayer());
    }


    @EventHandler
    public void onPlayerDeath(EntityDamageEvent e) {
        if (e.getEntityType() == EntityType.PLAYER) {

            Player p = (Player) e.getEntity();

            if (e.getCause().equals(EntityDamageEvent.DamageCause.LAVA)) {
                org.bukkit.scoreboard.Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
                Team blue = sb.getTeam("blue");
                Team red = sb.getTeam("red");

                if (blue.getPlayers().contains(p)) {
                    uptateScoreboardgamered();
                }

                if (red.getPlayers().contains(p)) {
                    uptateScoreboardgameblue();
                }
            }
        }
    }



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

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore("Nombre de joueurs : " + Bukkit.getOnlinePlayers().size());
        Score s2 = obj.getScore("");
        Score s3 = obj.getScore(ChatColor.RED + "Red : " + ChatColor.WHITE + pl.getConfig().getInt("redscore") + "/8" );
        Score s4 = obj.getScore(ChatColor.BLUE + "Blue : " + ChatColor.WHITE + pl.getConfig().getInt("bluescore") + "/8" );

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
        for (Player online : Bukkit.getOnlinePlayers()) {

            if (pl.getConfig().getInt("redscore") >= 7) {
                pl.getConfig().set("redscore", pl.getConfig().getInt("redscore") + 1);

                org.bukkit.scoreboard.Scoreboard board = online.getScoreboard();

                board.resetScores(online);

                Score s3 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.RED + "Red : " + ChatColor.WHITE + pl.getConfig().getInt("redscore") + "/8" );

                s3.setScore(3);
                online.setScoreboard(board);
            } else {
                pl.getConfig().set("redscore", pl.getConfig().getInt("redscore") + 1);

                org.bukkit.scoreboard.Scoreboard board = online.getScoreboard();

                board.resetScores(online);

                Score s3 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.RED + "Red : " + ChatColor.WHITE + pl.getConfig().getInt("redscore") + "/8" );

                s3.setScore(3);
                online.setScoreboard(board);
            }


        }

    }

    public void uptateScoreboardgameblue() {
        for (Player online : Bukkit.getOnlinePlayers()) {

            if (pl.getConfig().getInt("bluescore") >= 7) {
                pl.getConfig().set("bluescore", pl.getConfig().getInt("bluescore") + 1);

                org.bukkit.scoreboard.Scoreboard board = online.getScoreboard();

                board.resetScores(online);

                Score s4 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.BLUE + "Blue : " + ChatColor.WHITE + pl.getConfig().getInt("bluescore") + "/8" );
                s4.setScore(4);
                online.setScoreboard(board);
            }

            pl.getConfig().set("bluescore", pl.getConfig().getInt("bluescore") + 1);


            org.bukkit.scoreboard.Scoreboard board = online.getScoreboard();

            board.resetScores(online);

            Score s4 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.BLUE + "Blue : " + ChatColor.WHITE + pl.getConfig().getInt("bluescore") + "/8" );

            s4.setScore(4);
            online.setScoreboard(board);
        }

    }

}
