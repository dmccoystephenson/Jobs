package net.danh.jobs.Events;

import net.danh.jobs.Files.Files;
import net.danh.jobs.Jobs;
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

            if (Files.getInstance().getPower(e.getPlayer()) >= 50) {
                if (e.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
                    Files.getInstance().removePower(e.getPlayer(), 10);
                    e.getPlayer().giveExp(1);
                    e.setCancelled(false);
                } else {
                    e.getPlayer().sendMessage(Files.getInstance().convert("&cVật phẩm bạn câu không phải là cá"));
                    e.setCancelled(true);
                }
            } else {
                e.getPlayer().sendMessage(Files.getInstance().convert("&cBạn cần ít nhất trên 50 năng lượng để câu cá"));
                e.setCancelled(true);
            }
        } else {
            e.getPlayer().sendMessage(Files.getInstance().convert("&cBạn cần là Ngư Dân để câu cá"));
            e.setCancelled(true);
        }
    }
}