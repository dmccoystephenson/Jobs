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
        if (Files.getInstance().getJobs(p).equalsIgnoreCase("KHONGCONGHE")) {
            Files.getInstance().createJobs(p);
            Files.getInstance().createPower(p, 100);
            Files.getInstance().savedata();
            p.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("select-usage")));
        } else {
            p.sendMessage(Files.getInstance().convert("&aBạn đã có nghề nghiệp ! Hãy làm việc chăm chỉ nhé!!"));
        }
    }
}
