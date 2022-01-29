package net.danh.jobs.Events;

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
                            double playerchance = (double) (Files.getInstance().getAge(e.getPlayer()) / 10);
                            if (playerchance >= systemchance) {
                                Files.getInstance().addXP(e.getPlayer(), 2 + Files.getInstance().getleveluptimes(e.getPlayer()));
                                Files.getInstance().addPower(e.getPlayer(), 5 + Files.getInstance().getleveluptimes(e.getPlayer()));
                                e.getPlayer().sendMessage(Files.getInstance().convert("&aChúc mừng bạn đào được khối may mắn! Bạn nhận được &65 &b+(" + Files.getInstance().getleveluptimes(e.getPlayer()) + "&b) năng lượng, &62 &b+(" + Files.getInstance().getleveluptimes(e.getPlayer()) + "&b) kinh nghiệm!"));
                                return;
                            }
                            Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp") + Files.getInstance().getleveluptimes(e.getPlayer()));
                            Files.getInstance().removePower(e.getPlayer(), 1);
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