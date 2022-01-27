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
                    if (Files.getInstance().getAge(e.getPlayer()) == 1) {
                        Files.getInstance().addPower(e.getPlayer(), 2);
                    } else if (Files.getInstance().getAge(e.getPlayer()) >= 2 && Files.getInstance().getAge(e.getPlayer()) < 5) {
                        Files.getInstance().addPower(e.getPlayer(), 5);
                    } else if (Files.getInstance().getAge(e.getPlayer()) >= 5 && Files.getInstance().getAge(e.getPlayer()) < 7) {
                        Files.getInstance().addPower(e.getPlayer(), 10);
                    } else if (Files.getInstance().getAge(e.getPlayer()) >= 7 && Files.getInstance().getAge(e.getPlayer()) < 10) {
                        Files.getInstance().addPower(e.getPlayer(), 20);
                    } else if (Files.getInstance().getAge(e.getPlayer()) >= 10 && Files.getInstance().getAge(e.getPlayer()) < 15) {
                        Files.getInstance().addPower(e.getPlayer(), 30);
                    } else if (Files.getInstance().getAge(e.getPlayer()) >= 15 && Files.getInstance().getAge(e.getPlayer()) < 20) {
                        Files.getInstance().addPower(e.getPlayer(), 40);
                    } else if (Files.getInstance().getAge(e.getPlayer()) >= 20 && Files.getInstance().getAge(e.getPlayer()) < 30) {
                        Files.getInstance().addPower(e.getPlayer(), 50);
                    } else if (Files.getInstance().getAge(e.getPlayer()) >= 30 && Files.getInstance().getAge(e.getPlayer()) < 50) {
                        Files.getInstance().addPower(e.getPlayer(), 70);
                    } else if (Files.getInstance().getAge(e.getPlayer()) >= 50) {
                        Files.getInstance().addPower(e.getPlayer(), 100);
                    }
                } else {
                    e.setCancelled(true);
                }
            } else {
                if (Files.getInstance().getAge(e.getPlayer()) >= 1 && Files.getInstance().getAge(e.getPlayer()) < 10) {
                    e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel() + 3);
                } else if (Files.getInstance().getAge(e.getPlayer()) >= 10 && Files.getInstance().getAge(e.getPlayer()) < 50) {
                    e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel() + 5);
                } else if (Files.getInstance().getAge(e.getPlayer()) >= 50) {
                    e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel() + 7);
                }
            }
        }
    }
}
