package net.danh.jobs.Events;

import com.google.common.io.FileBackedOutputStream;
import jdk.nashorn.internal.ir.IfNode;
import net.danh.jobs.Files.Files;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class Interact implements Listener {

    @EventHandler
    public void onRev(PlayerInteractAtEntityEvent e) {
        Player bs = e.getPlayer();

        if (Files.getInstance().getdata().getString("players." + bs.getName()).equals("BACSI")) {
            if (e.getRightClicked().getType().equals(EntityType.PLAYER)) {
                String name = e.getRightClicked().getName();
                Player bn = Bukkit.getPlayerExact(name).getPlayer();
                if (bn.getHealth() != bn.getMaxHealth()) {
                    if (bs.getItemInHand().equals(Material.getMaterial(Files.getInstance().getconfig().getString("doctor_material"))) && bs.getItemInHand().hasItemMeta()) {
                        bn.setHealth(bn.getHealth() + (bs.getItemInHand().getAmount() / 2));
                        bs.setItemInHand(null);
                        bs.giveExp(10);
                    }
                }
            }
        }
    }
}
