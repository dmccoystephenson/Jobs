package net.danh.jobs.Events;

import net.danh.gang.Manager.Gangs;
import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Files;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.logging.Level;

public class Fishing implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onFishing(PlayerFishEvent e) {
        if (Files.getInstance().getJobs(e.getPlayer()).equals("NGUDAN")) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la ngu dan");
            }
            if (Files.getInstance().getPower(e.getPlayer()) <= 0) {
                e.getPlayer().sendMessage(Files.getInstance().convert("&cBạn cần phải trên 0 năng lượng để làm việc"));
                e.setCancelled(true);
                return;
            } else {
                if (Files.getInstance().getPower(e.getPlayer()) > 50) {
                    if (e.getCaught() instanceof Item) {
                        if (((Item) e.getCaught()).getItemStack().getType() == Material.RAW_FISH
                                || (((Item) e.getCaught()).getItemStack().getType()) == Material.LEATHER) {
                            double systemchance = Math.random() * 100.0D;
                            double playerchance = (double) (Files.getInstance().getLevel(e.getPlayer()) / 2);
                            if (playerchance >= systemchance) {
                                Files.getInstance().addXP(e.getPlayer(), 2);
                                Files.getInstance().addPower(e.getPlayer(), 5);
                                e.getPlayer().sendMessage(Files.getInstance().convert("&aChúc mừng bạn câu được vật phẩm may mắn! Bạn nhận được 5 năng lượng, 2 kinh nghiệm!"));
                                return;
                            }
                            Files.getInstance().removePower(e.getPlayer(), 1);
                            if (Jobs.getInstance().getServer().getPluginManager().isPluginEnabled("Gang")) {
                                if (Gangs.inGang(e.getPlayer())) {
                                    net.danh.gang.Files.Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp"));
                                }
                            }

                            Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp"));
                            e.setCancelled(false);
                        } else {
                            e.getPlayer().sendMessage(Files.getInstance().convert("&cVật phẩm bạn câu không phải là cá"));
                            e.setCancelled(true);
                            return;
                        }
                        if (((Item) e.getCaught()).getItemStack().getType() == Material.FISHING_ROD) {
                            e.getPlayer().sendMessage(Files.getInstance().convert("&cVật phẩm bạn câu không phải là cá"));

                            e.setCancelled(true);
                            return;
                        }

                        if (((Item) e.getCaught()).getItemStack().getItemMeta().hasEnchants()) {
                            e.getPlayer().sendMessage(Files.getInstance().convert("&cVật phẩm bạn câu không phải là cá"));

                            e.setCancelled(true);
                            return;
                        }
                    }
                } else {
                    e.getPlayer().sendMessage(Files.getInstance().convert("&cBạn cần ít nhất trên 50 năng lượng để câu cá"));
                    e.setCancelled(true);
                    return;
                }
            }
        } else {
            e.getPlayer().sendMessage(Files.getInstance().convert("&cBạn cần là Ngư Dân để câu cá"));
            e.setCancelled(true);
            return;
        }
    }
}