package net.danh.jobs.Events;

import net.danh.jobs.Files.Files;
import net.danh.jobs.Jobs;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.logging.Level;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        if (Files.getInstance().getdata().getString("players." + p.getName()).equals("THOMO"))
            for (String key : Files.getInstance().getconfig().getConfigurationSection("miner_values").getKeys(false)) {
                int value = Files.getInstance().getconfig().getInt("miner_values." + key);
                if (b.getType().equals(Material.getMaterial(key))) {
                    p.giveExp(value);
                    if (Files.getInstance().getconfig().getBoolean("debug"))
                        Jobs.getInstance().getLogger().log(Level.INFO, String.valueOf(p.getName()) + " (" + p.getUniqueId() + ") broke a block " + b.getType() + " located in " + b.getLocation().toString());
                }
            }
        if (Files.getInstance().getdata().getString("players." + p.getName()).equals("TIEUPHU"))
            for (String key : Files.getInstance().getconfig().getConfigurationSection("treecutter_values").getKeys(false)) {
                int value = Files.getInstance().getconfig().getInt("treecutter_values." + key);
                if (b.getType().equals(Material.getMaterial(key))) {
                    p.giveExp(value);
                    if (Files.getInstance().getconfig().getBoolean("debug"))
                        Jobs.getInstance().getLogger().log(Level.INFO, String.valueOf(p.getName()) + " (" + p.getUniqueId() + ") broke a block " + b.getType() + " located in " + b.getLocation().toString());
                }
            }
    }
}
