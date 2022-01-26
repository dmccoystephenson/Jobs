package net.danh.jobs.Events;

import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Files;
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

            if (e.getItem().getType() == Material.POTION) {
                if (Files.getInstance().getPower(e.getPlayer()) < 100) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
                        }
                    }.runTaskLaterAsynchronously(Jobs.getInstance(), 1L);
                    Files.getInstance().addPower(e.getPlayer(), 5);
                } else {
                    e.setCancelled(true);
                }
            } else {
                e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel() + 3);
            }
        }
    }
}
