package net.danh.jobs.Events;

import net.danh.gang.Gang;
import net.danh.gang.Manager.Gangs;
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

        if (b.getType() == Material.GOLD_ORE
                || b.getType() == Material.EMERALD_ORE
                || b.getType() == Material.IRON_ORE
                || b.getType() == Material.MOSSY_COBBLESTONE) {

            if (p.getItemInHand().getType() == Material.STONE_PICKAXE) {

                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra cup");
                }

                if (Files.getInstance().getJobs(p).equals("THOMO")) {

                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la tho mo");
                    }

                    if (Files.getInstance().getPower(e.getPlayer()) <= 0) {
                        e.getPlayer().sendMessage(Files.getInstance().convert("&cBạn cần phải trên 0 năng lượng để làm việc"));
                        e.setCancelled(true);
                    } else {
                        if (Files.getInstance().getPower(p) <= 30) {
                            if (b.getType() == Material.GOLD_ORE
                                    || b.getType() == Material.EMERALD_ORE
                                    || b.getType() == Material.IRON_ORE) {
                                p.sendMessage(Files.getInstance().convert("&cBạn cần trên 30 năng lượng để có thể đào chúng!"));
                                e.setCancelled(true);
                            }
                        } else {
                            e.setCancelled(false);
                        }
                        if (Files.getInstance().getPower(p) <= 50) {
                            if (b.getType() == Material.GOLD_ORE
                                    || b.getType() == Material.EMERALD_ORE) {
                                p.sendMessage(Files.getInstance().convert("&cBạn cần trên 50 năng lượng để có thể đào chúng"));
                                e.setCancelled(true);
                            }
                        } else {
                            e.setCancelled(false);
                        }

                        if (Files.getInstance().getPower(p) <= 80) {
                            if (b.getType() == Material.EMERALD_ORE) {
                                p.sendMessage(Files.getInstance().convert("&cBạn cần trên 80 năng lượng để có thể đào chúng"));
                                e.setCancelled(true);
                            }
                        } else {
                            e.setCancelled(false);
                        }
                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra block");
                        }
                        Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp"));
                        Files.getInstance().removePower(p, 1);
                        if (Jobs.getInstance().getServer().getPluginManager().isPluginEnabled("Gang")) {
                            if (Gangs.inGang(p)) {
                                net.danh.gang.Files.Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp"));
                            }
                        }
                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                        }
                    }
                } else {
                    p.sendMessage(Files.getInstance().convert("&cBạn cần là thợ mỏ để đào"));
                    e.setCancelled(true);
                }
            } else {
                p.sendMessage(Files.getInstance().convert("&cBạn cần sử dụng đúng vật phẩm để đào"));
                e.setCancelled(true);
            }
        }
        if (b.getType() == Material.LOG) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra nguyen lieu");
            }

            if (Files.getInstance().getJobs(p).equals("THOMOC")) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la tho moc");
                }

                if (Files.getInstance().getPower(e.getPlayer()) <= 0) {
                    e.getPlayer().sendMessage(Files.getInstance().convert("&cBạn cần phải trên 0 năng lượng để làm việc"));
                    e.setCancelled(true);
                } else {
                    if (Files.getInstance().getPower(p) <= 50) {
                        p.sendMessage(Files.getInstance().convert("&cBạn cần trên 50 năng lượng để có thể đào chúng"));
                        e.setCancelled(true);
                    }


                    Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp"));
                    Files.getInstance().removePower(p, 1);
                        if (Jobs.getInstance().getServer().getPluginManager().isPluginEnabled("Gang")) {
                            if (Gangs.inGang(p)) {
                                net.danh.gang.Files.Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp"));
                            }
                        }
                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                    }
                }
            } else {
                p.sendMessage(Files.getInstance().convert("&cBạn cần là thợ mộc để đào gỗ"));
                e.setCancelled(true);
            }
        }
        if (b.getType() == Material.GRAVEL
                || b.getType() == Material.SAND
                || b.getType() == Material.CLAY) {

            if (Files.getInstance().getJobs(p).equals("CONGNHAN")) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la cong nhan");
                }

                if (Files.getInstance().getPower(e.getPlayer()) <= 0) {
                    e.getPlayer().sendMessage(Files.getInstance().convert("&cBạn cần phải trên 0 năng lượng để làm việc"));
                    e.setCancelled(true);
                } else {

                    if (Files.getInstance().getPower(p) <= 30) {
                        if (b.getType() == Material.SAND) {
                            p.sendMessage(Files.getInstance().convert("&cBạn cần trên 30 năng lượng để có thể đào chúng!"));
                            e.setCancelled(true);
                        }
                    } else {
                        e.setCancelled(false);
                    }
                    if (Files.getInstance().getPower(p) <= 50) {
                        if (b.getType() == Material.CLAY) {
                            p.sendMessage(Files.getInstance().convert("&cBạn cần trên 50 năng lượng để có thể đào chúng"));
                            e.setCancelled(true);
                        }
                    } else {
                        e.setCancelled(false);
                    }

                    if (Files.getInstance().getPower(p) <= 80) {
                        if (b.getType() == Material.GRAVEL) {
                            p.sendMessage(Files.getInstance().convert("&cBạn cần trên 80 năng lượng để có thể đào chúng"));
                            e.setCancelled(true);
                        }
                    } else {
                        e.setCancelled(false);
                    }


                    Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp"));
                    Files.getInstance().removePower(p, 1);
                        if (Jobs.getInstance().getServer().getPluginManager().isPluginEnabled("Gang")) {
                            if (Gangs.inGang(p)) {
                                net.danh.gang.Files.Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp"));
                            }
                        }
                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                    }
                }
            } else {
                p.sendMessage(Files.getInstance().convert("&CBạn cần là Công Nhân để đào nó!"));
                e.setCancelled(true);
            }
        }

        if (b.getType() == Material.CROPS
                || b.getType() == Material.BEETROOT_BLOCK
                || b.getType() == Material.SUGAR_CANE_BLOCK) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra vat pham thu hoach");
            }

            if (p.getItemInHand().getType() == Material.WOOD_HOE) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Kiem tra vat pham tren tay");
                }
                if (Files.getInstance().getJobs(p).equals("NONGDAN")) {

                    if (Files.getInstance().getPower(e.getPlayer()) <= 0) {
                        e.getPlayer().sendMessage(Files.getInstance().convert("&cBạn cần phải trên 0 năng lượng để làm việc"));
                        e.setCancelled(true);
                    } else {

                        if (Files.getInstance().getPower(p) <= 5) {
                            if (b.getType() == Material.CROPS) {
                                p.sendMessage(Files.getInstance().convert("&cBạn cần trên 5 năng lượng để có thể đào chúng!"));
                                e.setCancelled(true);
                            }
                        } else {
                            e.setCancelled(false);
                        }
                        if (Files.getInstance().getPower(p) <= 10) {
                            if (b.getType() == Material.BEETROOT_BLOCK) {
                                p.sendMessage(Files.getInstance().convert("&cBạn cần trên 10 năng lượng để có thể đào chúng"));
                                e.setCancelled(true);
                            }
                        } else {
                            e.setCancelled(false);
                        }

                        if (Files.getInstance().getPower(p) <= 20) {
                            if (b.getType() == Material.SUGAR_CANE_BLOCK) {
                                p.sendMessage(Files.getInstance().convert("&cBạn cần trên 20 năng lượng để có thể đào chúng"));
                                e.setCancelled(true);
                            }
                        } else {
                            e.setCancelled(false);
                        }
                        Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp"));
                        Files.getInstance().removePower(p, 1);
                        if (Jobs.getInstance().getServer().getPluginManager().isPluginEnabled("Gang")) {
                            if (Gangs.inGang(p)) {
                                net.danh.gang.Files.Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp"));
                            }
                        }
                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                        }
                    }
                } else {
                    p.sendMessage(Files.getInstance().convert("&cBạn cần là Nông Dân để có thể thu hoạch chúng"));
                    e.setCancelled(true);
                }
            }
        }
    }
}