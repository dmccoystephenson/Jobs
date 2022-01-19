package net.danh.jobs.Events;

import net.danh.jobs.Files.Files;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class Eating implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEating(PlayerItemConsumeEvent e) {
        if (e.getItem().getType() == Material.BEETROOT) {
            e.setCancelled(true);
        } else {
            /*   if (e.getItem().getType() == Material.POTION){*/
            Files.getInstance().addPower(e.getPlayer(), 2);
        }
    }
}
