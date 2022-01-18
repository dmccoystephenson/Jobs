package net.danh.jobs.Events;

import net.danh.jobs.Files.Files;
import net.danh.jobs.Jobs;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.logging.Level;

public class BlockBreak implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        if (p.getItemInHand().getType() == Material.STONE_PICKAXE) {

            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra cup");
            }

            if (b.getType() == Material.GOLD_ORE
                    || b.getType() == Material.EMERALD_ORE
                    || b.getType() == Material.IRON_ORE
                    || b.getType() == Material.MOSSY_COBBLESTONE) {

                if (Files.getInstance().getdata().getString("players." + p.getName()).equals("THOMO")) {

                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la tho mo");
                    }
                    if (p.getPlayer().getLevel() <= 3) {
                        if (b.getType() == Material.GOLD_ORE
                                || b.getType() == Material.EMERALD_ORE
                                || b.getType() == Material.IRON_ORE) {
                            p.sendMessage(Files.getInstance().convert("&cBạn cần trên cấp độ 3 để có thể đào chúng"));
                            e.setCancelled(true);
                        }
                    } else {
                        e.setCancelled(false);
                    }
                    if (p.getPlayer().getLevel() <= 7) {
                        if (b.getType() == Material.GOLD_ORE
                                || b.getType() == Material.EMERALD_ORE) {
                            p.sendMessage(Files.getInstance().convert("&cBạn cần trên cấp độ 7 để có thể đào chúng"));
                            e.setCancelled(true);
                        }
                    } else {
                        e.setCancelled(false);
                    }

                    if (p.getPlayer().getLevel() <= 12) {
                        if (b.getType() == Material.EMERALD_ORE) {
                            p.sendMessage(Files.getInstance().convert("&cBạn cần trên cấp độ 12 để có thể đào chúng"));
                            e.setCancelled(true);
                        }
                    } else {
                        e.setCancelled(false);
                    }
                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra block");
                    }

                    p.giveExp(Files.getInstance().getconfig().getInt("xp"));
                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                    }
                } else {
                    p.sendMessage(Files.getInstance().convert("&cBạn cần là thợ mỏ để đào"));
                    e.setCancelled(true);
                }
            }
        } else {
            p.sendMessage(Files.getInstance().convert("&cBạn cần sử dụng đúng vật phẩm để đào"));
            e.setCancelled(true);
        }
        if (b.getType() == Material.LOG) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra nguyen lieu");
            }

            if (Files.getInstance().getdata().getString("players." + p.getName()).equals("THOMOC")) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la tho moc");
                }
                int randomNum = Files.getInstance().getconfig().getInt("treecutter_values.min") + (int) (Math.random() * Files.getInstance().getconfig().getInt("treecutter_values.max"));

                p.giveExp(randomNum);
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                }
            } else {
                p.sendMessage(Files.getInstance().convert("&cBạn cần là thợ mộc để đào gỗ"));
                e.setCancelled(true);
            }
        }
        if (b.getType() == Material.GRAVEL
                || b.getType() == Material.SAND
                || b.getType() == Material.CLAY) {

            if (Files.getInstance().getdata().getString("players." + p.getName()).equals("CONGNHAN")) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la cong nhan");
                }
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra nguyen lieu");
                }

                p.giveExp(Files.getInstance().getconfig().getInt("xp"));
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                }
            } else {
                p.sendMessage(Files.getInstance().convert("&CBạn cần là Công Nhân để đào nó!"));
                e.setCancelled(true);
            }
        }

        if (p.getItemInHand().getType() == Material.WOOD_HOE) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra vat pham tren tay");
            }
            if (b.getType() == Material.CROPS
                    || b.getType() == Material.BEETROOT_BLOCK
                    || b.getType() == Material.SUGAR_CANE_BLOCK) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra vat pham thu hoach");
                }

                if (Files.getInstance().getdata().getString("players." + p.getName()).equals("NONGDAN")) {
                    p.giveExp(Files.getInstance().getconfig().getInt("xp"));
                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                    }
                } else {
                    p.sendMessage(Files.getInstance().convert("&cBạn cần là Nông Dân để có thể thu hoạch chúng"));
                    e.setCancelled(true);
                }
            }
        }
    }
}