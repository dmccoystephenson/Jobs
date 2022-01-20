package net.danh.jobs.Events;

import net.danh.jobs.Files.Files;
import net.danh.jobs.Jobs;
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
            } else {

                if (Files.getInstance().getPower(e.getPlayer()) >= 50) {
                    if (e.getCaught() instanceof Item) {
                        if (((Item) e.getCaught()).getItemStack().getType() == Material.RAW_FISH
                                || (((Item) e.getCaught()).getItemStack().getType()) == Material.LEATHER) {
                            Files.getInstance().removePower(e.getPlayer(), 2);
                            e.getPlayer().giveExp(1);
                            e.setCancelled(false);
                        } else {
                            e.getPlayer().sendMessage(Files.getInstance().convert("&cVật phẩm bạn câu không phải là cá"));
                            e.setCancelled(true);
                        }
                        if (((Item) e.getCaught()).getItemStack().getType() == Material.FISHING_ROD) {
                            e.getPlayer().sendMessage(Files.getInstance().convert("&cVật phẩm bạn câu không phải là cá"));

                            e.setCancelled(true);
                        }

                        if (((Item) e.getCaught()).getItemStack().getItemMeta().hasEnchants()) {
                            e.getPlayer().sendMessage(Files.getInstance().convert("&cVật phẩm bạn câu không phải là cá"));

                            e.setCancelled(true);
                        }
                    }
                } else {
                    e.getPlayer().sendMessage(Files.getInstance().convert("&cBạn cần ít nhất trên 50 năng lượng để câu cá"));
                    e.setCancelled(true);
                }
            }
        } else {
            e.getPlayer().sendMessage(Files.getInstance().convert("&cBạn cần là Ngư Dân để câu cá"));
            e.setCancelled(true);
        }
    }
}