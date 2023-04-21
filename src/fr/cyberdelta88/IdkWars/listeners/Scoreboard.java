package fr.cyberdelta88.IdkWars.listeners;

import fr.cyberdelta88.IdkWars.Main;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class Scoreboard implements Listener {

    Plugin pl = Main.getPlugin(Main.class);


    @EventHandler
    public void onPlayerDeath(EntityDamageEvent e) {
        if (e.getEntityType() == EntityType.PLAYER) {

            Player p = (Player) e.getEntity();

            if (e.getCause().equals(EntityDamageEvent.DamageCause.LAVA)) {
                org.bukkit.scoreboard.Scoreboard sb = p.getScoreboard();
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
        updateScoreboardleave();
    }

    public void createScoreboard(Player p) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard board = manager.getMainScoreboard();

        Team blue = board.registerNewTeam("blue");
        blue.setPrefix(ChatColor.BLUE + "xssx");

        Team red = board.registerNewTeam("red");
        red.setPrefix(ChatColor.RED + "sxxs");


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
            org.bukkit.scoreboard.Scoreboard board = online.getScoreboard();
            int i = Bukkit.getOnlinePlayers().size() - 1;
            Score prescore = board.getObjective(DisplaySlot.SIDEBAR).getScore("Nombre de joueurs : " + i);
            board.resetScores(prescore.getEntry());

            Score score = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore("Nombre de joueurs : " + Bukkit.getOnlinePlayers().size());
            score.setScore(1);
        }
    }

    public void updateScoreboardleave() {
        for (Player online : Bukkit.getOnlinePlayers()) {
            org.bukkit.scoreboard.Scoreboard board = online.getScoreboard();
            int i = Bukkit.getOnlinePlayers().size() + 1;
            Score prescore = board.getObjective(DisplaySlot.SIDEBAR).getScore("Nombre de joueurs : " + i);
            board.resetScores(prescore.getEntry());

            Score score = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore("Nombre de joueurs : " + Bukkit.getOnlinePlayers().size());
            score.setScore(1);
        }
    }


    public void uptateScoreboardgamered() {
        for (Player online : Bukkit.getOnlinePlayers()) {

            if (pl.getConfig().getInt("redscore") >= 7) {

                org.bukkit.scoreboard.Scoreboard board = online.getScoreboard();
                Score pres3 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.RED + "Red : " + ChatColor.WHITE + pl.getConfig().getInt("redscore") + "/8" );
                board.resetScores(pres3.getEntry());

                pl.getConfig().set("redscore", pl.getConfig().getInt("redscore") + 1);
                int i = pl.getConfig().getInt("redscore") / 2;

                Score s3 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.RED + "Red : " + ChatColor.WHITE + i + "/8" );

                s3.setScore(3);

                Team blue = board.getTeam("blue");
                Team red = board.getTeam("red");

                if (blue != null) {
                    for (String entry : blue.getEntries()) {
                        Player o = Bukkit.getPlayer(entry);
                        if (o != null) {
                            IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + "Défaite" + "\",color:" + ChatColor.RED.name().toLowerCase() + "}");

                            PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
                            PacketPlayOutTitle length = new PacketPlayOutTitle(5, 100, 5);

                            ((CraftPlayer) o).getHandle().playerConnection.sendPacket(title);
                            ((CraftPlayer) o).getHandle().playerConnection.sendPacket(length);

                            Location respawnloc = new Location(o.getWorld(), 0, 0, 0);
                            o.teleport(respawnloc);
                        }
                    }
                }
                if (red != null) {
                    for (String entry : red.getEntries()) {
                        Player o = Bukkit.getPlayer(entry);
                        if (o != null) {
                            IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + "Victoire" + "\",color:" + ChatColor.GREEN.name().toLowerCase() + "}");

                            PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
                            PacketPlayOutTitle length = new PacketPlayOutTitle(5, 100, 5);

                            ((CraftPlayer) o).getHandle().playerConnection.sendPacket(title);
                            ((CraftPlayer) o).getHandle().playerConnection.sendPacket(length);

                            Location respawnloc = new Location(o.getWorld(), 0, 0, 0);
                            o.teleport(respawnloc);
                        }
                    }
                }

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        org.bukkit.scoreboard.Scoreboard board = online.getScoreboard();
                        Score pres3 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.RED + "Red : " + ChatColor.WHITE + pl.getConfig().getInt("redscore") + "/8" );
                        board.resetScores(pres3.getEntry());

                        Score pres4 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.BLUE + "Blue : " + ChatColor.WHITE + pl.getConfig().getInt("bluescore") + "/8" );
                        board.resetScores(pres4.getEntry());

                        pl.getConfig().set("redscore", 0);
                        pl.getConfig().set("bluescore", 0);


                        Score s3 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.RED + "Red : " + ChatColor.WHITE + pl.getConfig().getInt("redscore") + "/8" );
                        s3.setScore(3);

                        Score s4 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.BLUE + "Blue : " + ChatColor.WHITE + pl.getConfig().getInt("bluescore") + "/8" );
                        s4.setScore(4);

                        Location respawn = new Location(online.getWorld(), -1119, 68, -272);


                        online.teleport(respawn);

                    }
                }.runTaskLater(pl, 200);


            } else {

                org.bukkit.scoreboard.Scoreboard board = online.getScoreboard();
                Score pres3 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.RED + "Red : " + ChatColor.WHITE + pl.getConfig().getInt("redscore") + "/8" );
                board.resetScores(pres3.getEntry());

                pl.getConfig().set("redscore", pl.getConfig().getInt("redscore") + 1);
                int i = pl.getConfig().getInt("redscore") / 2;

                Score s3 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.RED + "Red : " + ChatColor.WHITE + i + "/8" );
                s3.setScore(3);
            }


        }

    }

    public void uptateScoreboardgameblue() {
        for (Player online : Bukkit.getOnlinePlayers()) {

            if (pl.getConfig().getInt("bluescore") >= 7) {
                org.bukkit.scoreboard.Scoreboard board = online.getScoreboard();
                Score pres4 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.BLUE + "Blue : " + ChatColor.WHITE + pl.getConfig().getInt("bluescore") + "/8" );
                board.resetScores(pres4.getEntry());

                pl.getConfig().set("bluescore", pl.getConfig().getInt("bluescore") + 1);
                int i = pl.getConfig().getInt("bluescore") / 2;

                Score s4 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.BLUE + "Blue : " + ChatColor.WHITE + i + "/8" );

                s4.setScore(4);

                Team blue = board.getTeam("blue");
                Team red = board.getTeam("red");

                if (blue != null) {
                    for (String entry : blue.getEntries()) {
                        Player o = Bukkit.getPlayer(entry);
                        if (o != null) {
                            IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + "Victoire" + "\",color:" + ChatColor.GREEN.name().toLowerCase() + "}");

                            PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
                            PacketPlayOutTitle length = new PacketPlayOutTitle(5, 100, 5);

                            ((CraftPlayer) o).getHandle().playerConnection.sendPacket(title);
                            ((CraftPlayer) o).getHandle().playerConnection.sendPacket(length);

                            Location respawnloc = new Location(o.getWorld(), 0, 0, 0);
                            o.teleport(respawnloc);
                        }
                    }
                }
                if (red != null) {
                    for (String entry : red.getEntries()) {
                        Player o = Bukkit.getPlayer(entry);
                        if (o != null) {
                            IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + "Défaite" + "\",color:" + ChatColor.RED.name().toLowerCase() + "}");

                            PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
                            PacketPlayOutTitle length = new PacketPlayOutTitle(5, 100, 5);

                            ((CraftPlayer) o).getHandle().playerConnection.sendPacket(title);
                            ((CraftPlayer) o).getHandle().playerConnection.sendPacket(length);
                            Location respawnloc = new Location(o.getWorld(), 0, 0, 0);
                            o.teleport(respawnloc);
                        }
                    }
                }

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        org.bukkit.scoreboard.Scoreboard board = online.getScoreboard();
                        Score pres3 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.RED + "Red : " + ChatColor.WHITE + pl.getConfig().getInt("redscore") + "/8" );
                        board.resetScores(pres3.getEntry());

                        Score pres4 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.BLUE + "Blue : " + ChatColor.WHITE + pl.getConfig().getInt("bluescore") + "/8" );
                        board.resetScores(pres4.getEntry());

                        pl.getConfig().set("redscore", 0);
                        pl.getConfig().set("bluescore", 0);


                        Score s3 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.RED + "Red : " + ChatColor.WHITE + pl.getConfig().getInt("redscore") + "/8" );
                        s3.setScore(3);

                        Score s4 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.BLUE + "Blue : " + ChatColor.WHITE + pl.getConfig().getInt("bluescore") + "/8" );
                        s4.setScore(4);

                        Location respawn = new Location(online.getWorld(), -1119, 68, -272);

                            online.teleport(respawn);

                    }
                }.runTaskLater(pl, 200);

            } else {
                org.bukkit.scoreboard.Scoreboard board = online.getScoreboard();
                Score pres4 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.BLUE + "Blue : " + ChatColor.WHITE + pl.getConfig().getInt("bluescore") + "/8" );

                board.resetScores(pres4.getEntry());



                pl.getConfig().set("bluescore", pl.getConfig().getInt("bluescore") + 1);
                int i = pl.getConfig().getInt("bluescore") / 2;

                Score s4 = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.BLUE + "Blue : " + ChatColor.WHITE + i + "/8" );


                s4.setScore(4);
            }



        }

    }

}
