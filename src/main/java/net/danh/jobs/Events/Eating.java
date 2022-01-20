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
            e.getPlayer().sendMessage(Files.getInstance().convert("&cCủ dền không phải để ăn!"));
            e.setCancelled(true);
        } else {
            if (Files.getInstance().getPower(e.getPlayer()) < 100) {
                Files.getInstance().addPower(e.getPlayer(), 2);
                e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel() + 2);
                e.getPlayer().sendMessage(Files.getInstance().convert("&a+2 Năng Lượng"));
            } else {
                e.setCancelled(true);
            }
        }
    }
}
