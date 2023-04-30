package fr.cyberdelta88.IdkWars.commands;

import fr.cyberdelta88.IdkWars.Main;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Cmdstart implements CommandExecutor {

    Plugin pl = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if (sender instanceof Player) {
            Player p = (Player) sender;

            Scoreboard sb = p.getScoreboard();
            Team blue = sb.getTeam("blue");
            Team red = sb.getTeam("red");

            Inventory inv = p.getInventory();
            ItemStack bowuwu = new ItemStack(Material.BOW);
            ItemMeta bowMeta = bowuwu.getItemMeta();
            bowMeta.spigot().setUnbreakable(true);
            bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            bowMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
            bowMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "PUCHY BOW");
            bowMeta.spigot().setUnbreakable(true);
            bowuwu.setItemMeta(bowMeta);

            ItemStack stick = new ItemStack(Material.STICK);
            ItemMeta stickmeta = stick.getItemMeta();
            stickmeta.spigot().setUnbreakable(true);
            stickmeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
            stickmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "KB STICK DE CANCER");
            bowMeta.spigot().setUnbreakable(true);
            stick.setItemMeta(stickmeta);

            ItemStack arrowuwu = new ItemStack(Material.ARROW, 1);



            new BukkitRunnable() {
                int remaining = 3;
                public void run() {
                    if (remaining > 0) {

                        if (blue != null) {
                            for (String entry : blue.getEntries()) {
                                Player o = Bukkit.getPlayer(entry);
                                if (o != null) {
                                    IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + remaining + "\",color:" + ChatColor.BLUE.name().toLowerCase() + "}");

                                    PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
                                    PacketPlayOutTitle length = new PacketPlayOutTitle(5, 50, 5);

                                    ((CraftPlayer) o).getHandle().playerConnection.sendPacket(title);
                                    ((CraftPlayer) o).getHandle().playerConnection.sendPacket(length);
                                    o.playSound(o.getLocation(),Sound.NOTE_PIANO, 3.0F, 0.533F);
                                }
                            }
                        }
                        if (red != null) {
                            for (String entry : red.getEntries()) {
                                Player o = Bukkit.getPlayer(entry);
                                if (o != null) {
                                    IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + remaining + "\",color:" + ChatColor.RED.name().toLowerCase() + "}");

                                    PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
                                    PacketPlayOutTitle length = new PacketPlayOutTitle(5, 50, 5);

                                    ((CraftPlayer) o).getHandle().playerConnection.sendPacket(title);
                                    ((CraftPlayer) o).getHandle().playerConnection.sendPacket(length);

                                    o.playSound(o.getLocation(),Sound.NOTE_PIANO, 3.0F, 0.533F);
                                }
                            }
                        }


                        remaining--;
                    } else {

                        if (blue != null) {
                            for (String entry : blue.getEntries()) {
                                Player o = Bukkit.getPlayer(entry);
                                if (o != null) {
                                    int xcoords = pl.getConfig().getInt("xblue");
                                    int ycoords = pl.getConfig().getInt("yblue");
                                    int zcoords = pl.getConfig().getInt("zblue");

                                    Location loc_blue = new Location(o.getWorld(), xcoords, ycoords, zcoords);
                                    o.teleport(loc_blue);
                                    o.setGameMode(GameMode.ADVENTURE);
                                    o.setScoreboard(sb);
                                }
                                Inventory invv = o.getInventory();
                                invv.clear();
                                invv.setItem(0, bowuwu);
                                invv.setItem(1, stick);
                                invv.setItem(2, arrowuwu);

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
                                    o.setGameMode(GameMode.ADVENTURE);
                                    o.setScoreboard(sb);
                                }
                                Inventory invv = o.getInventory();
                                invv.clear();
                                invv.setItem(0, bowuwu);
                                invv.setItem(1, stick);
                                invv.setItem(2, arrowuwu);
                            }
                        }
                        this.cancel();
                    }
                }
            }.runTaskTimer(Main.getPlugin(Main.class), 0, 100l);
        }
        return false;
    }
}


