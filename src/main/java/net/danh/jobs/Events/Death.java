package net.danh.jobs.Events;

import net.danh.jobs.Files.Files;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onDeath(PlayerDeathEvent e){
        Files.getInstance().removeXP(e.getEntity().getPlayer(), Files.getInstance().getXP(e.getEntity().getPlayer())/2);
    }
}
