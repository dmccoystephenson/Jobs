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
        if (Files.getInstance().getdata().getString("players." + e.getPlayer().getName()).equals("NGUDAN")) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Nguoi choi la ngu dan");
            }
            int randomNum = Files.getInstance().getconfig().getInt("fishing_values.min") + (int) (Math.random() * Files.getInstance().getconfig().getInt("fishing_values.max"));
            e.getPlayer().giveExp(randomNum);
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
            }

        }
    }
}
