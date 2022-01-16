package net.danh.jobs.Events;

import net.danh.jobs.Files.Files;
import net.danh.jobs.Jobs;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EntityEquipment;

import java.util.logging.Level;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        if (Files.getInstance().getdata().getString("players." + p.getName()).equals("THOMO")) {
            for (String key : Files.getInstance().getconfig().getConfigurationSection("miner_values").getKeys(false)) {
                int value = Files.getInstance().getconfig().getInt("miner_values." + key);
                if (b.getType().equals(Material.getMaterial(key))) {
                    p.giveExp(value);
                    if (Files.getInstance().getconfig().getBoolean("debug"))
                        Jobs.getInstance().getLogger().log(Level.INFO, String.valueOf(p.getName()) + " (" + p.getUniqueId() + ") broke a block " + b.getType() + " located in " + b.getLocation().toString());
                }
            }
        }
        if (Files.getInstance().getdata().getString("players." + p.getName()).equals("THOMOC")) {
            for (String key : Files.getInstance().getconfig().getConfigurationSection("treecutter_values").getKeys(false)) {
                int value = Files.getInstance().getconfig().getInt("treecutter_values." + key);
                if (b.getType().equals(Material.getMaterial(key))) {
                    p.giveExp(value);
                    if (Files.getInstance().getconfig().getBoolean("debug"))
                        Jobs.getInstance().getLogger().log(Level.INFO, String.valueOf(p.getName()) + " (" + p.getUniqueId() + ") broke a block " + b.getType() + " located in " + b.getLocation().toString());
                }
            }
        }
        if (Files.getInstance().getdata().getString("players." + p.getName()).equals("CONGNHAN")) {
            for (String key : Files.getInstance().getconfig().getConfigurationSection("build_values").getKeys(false)) {
                int value = Files.getInstance().getconfig().getInt("build_values." + key);
                if (b.getType().equals(Material.getMaterial(key))) {
                    p.giveExp(value);
                    if (Files.getInstance().getconfig().getBoolean("debug"))
                        Jobs.getInstance().getLogger().log(Level.INFO, String.valueOf(p.getName()) + " (" + p.getUniqueId() + ") broke a block " + b.getType() + " located in " + b.getLocation().toString());
                }
            }
        }

        if (Files.getInstance().getdata().getString("players." + p.getName()).equals("NONGDAN")) {
            if (!(b instanceof Ageable))
                return;
            Ageable ageable = (Ageable) b;
            if (ageable.getAge() < ageable.getMaximumAge())
                return;
            EntityEquipment equipment = p.getEquipment();
            if (equipment.getItemInHand().equals(Material.WOOD_HOE) && equipment.getItemInHand().hasItemMeta()) {
                for (String key : Files.getInstance().getconfig().getConfigurationSection("farm_values").getKeys(false)) {
                    int value = Files.getInstance().getconfig().getInt("farm_values." + key);
                    if (b.getType().equals(Material.getMaterial(key))) {
                        p.giveExp(value);
                        if (Files.getInstance().getconfig().getBoolean("debug"))
                            Jobs.getInstance().getLogger().log(Level.INFO, String.valueOf(p.getName()) + " (" + p.getUniqueId() + ") broke a block " + b.getType() + " located in " + b.getLocation().toString());
                    }
                }
            }
        }
    }
}