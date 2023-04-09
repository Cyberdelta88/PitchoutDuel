package fr.cyberdelta88.IdkWars.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Cmdteam implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            Inventory gui = Bukkit.createInventory(p, 9, ChatColor.GOLD + "TeamSelector");

            ItemStack blue = new ItemStack(Material.WOOL, 1, (byte)11);
            ItemMeta bluemeta = blue.getItemMeta();
            bluemeta.setDisplayName("Join blue team");
            blue.setItemMeta(bluemeta);

            ItemStack red = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)14);
            ItemMeta redmeta = red.getItemMeta();
            redmeta.setDisplayName("Join red team");
            red.setItemMeta(redmeta);

            ItemStack[] guicontent = {blue,red};
            gui.setContents(guicontent);

            p.openInventory(gui);
        }

        return false;
    }
}
