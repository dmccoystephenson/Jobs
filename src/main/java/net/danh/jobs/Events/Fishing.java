package net.danh.jobs.Events;

import net.danh.jobs.Files.Files;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class Fishing implements Listener {

    public void onFishing(PlayerFishEvent e) {
        int randomNum = Files.getInstance().getconfig().getInt("fishing_values.min") + (int) (Math.random() * Files.getInstance().getconfig().getInt("fishing_values.max"));
        e.getPlayer().giveExp(randomNum);

    }
}
