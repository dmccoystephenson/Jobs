package net.danh.jobs.Events;

import net.danh.jobs.Files.Files;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (Files.getInstance().getdata().getString("players." + p.getName()) == null) {
            Files.getInstance().getdata().set("players." + p.getName(), "KHONGCONGHE");
            Files.getInstance().savedata();
            p.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("select-usage")));
        }
    }
}
