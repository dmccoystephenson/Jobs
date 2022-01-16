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

            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la tho mo");
            }
            if (b.getType() == Material.GOLD_ORE
                    || b.getType() == Material.EMERALD_ORE
                    || b.getType() == Material.IRON_ORE
                    || b.getType() == Material.MOSSY_COBBLESTONE) {

                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra block");
                }

                int randomNum = Files.getInstance().getconfig().getInt("miner_values.min") + (int) (Math.random() * Files.getInstance().getconfig().getInt("miner_values.max"));

                p.giveExp(randomNum);
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                }
            }
        }
        if (Files.getInstance().getdata().getString("players." + p.getName()).equals("THOMOC")) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la tho moc");
            }
            if (b.getType() == Material.LOG) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra nguyen lieu");
                }
                int randomNum = Files.getInstance().getconfig().getInt("treecutter_values.min") + (int) (Math.random() * Files.getInstance().getconfig().getInt("treecutter_values.max"));

                p.giveExp(randomNum);
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                }
            }
        }
        if (Files.getInstance().getdata().getString("players." + p.getName()).equals("CONGNHAN")) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la cong nhan");
            }
            if (b.getType() == Material.GRAVEL
                    || b.getType() == Material.SAND
                    || b.getType() == Material.CLAY) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra nguyen lieu");
                }
                int randomNum = Files.getInstance().getconfig().getInt("build_values.min") + (int) (Math.random() * Files.getInstance().getconfig().getInt("build_values.max"));

                p.giveExp(randomNum);
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                }
            }
        }

        if (Files.getInstance().getdata().getString("players." + p.getName()).equals("NONGDAN")) {
            if (!(b instanceof Ageable)) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Cay khong co tuoii");
                }
                return;
            }
            Ageable ageable = (Ageable) b;
            if (ageable.getAge() < ageable.getMaximumAge()) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Cay chua lon");
                }
                return;
            }
            EntityEquipment equipment = p.getEquipment();
            if (equipment.getItemInHand().getType() == Material.WOOD_HOE) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra vat pham tren tay");
                }
                if (b.getType() == Material.WHEAT
                        || b.getType() == Material.BEETROOT
                        || b.getType() == Material.SUGAR) {
                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra vat pham thu hoach");
                    }

                    int randomNum = Files.getInstance().getconfig().getInt("farm_values.min") + (int) (Math.random() * Files.getInstance().getconfig().getInt("farm_values.max"));

                    p.giveExp(randomNum);
                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                    }
                }
            }
        }
    }
}