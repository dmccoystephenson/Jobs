package net.danh.jobs.Events;

import net.danh.jobs.Files.Files;
import net.danh.jobs.Jobs;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Eating implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEating(PlayerItemConsumeEvent e) {
        if (e.getItem().getType() == Material.BEETROOT) {
            e.getPlayer().sendMessage(Files.getInstance().convert("&cCủ dền không phải để ăn!"));
            e.setCancelled(true);
        } else {
            if (Files.getInstance().getPower(e.getPlayer()) < 100) {
                if (e.getItem().getType() == Material.POTION) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
                        }
                    }.runTaskLaterAsynchronously(Jobs.getInstance(), 1L);
                    Files.getInstance().addPower(e.getPlayer(), 5);
                } else {
                    e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel() + 2);
                }
            }
        }
    }
}
