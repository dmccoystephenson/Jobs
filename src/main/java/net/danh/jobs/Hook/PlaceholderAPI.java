package net.danh.jobs.Hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.jobs.Files.Files;
import net.danh.jobs.Jobs;
import org.bukkit.entity.Player;

public class PlaceholderAPI extends PlaceholderExpansion {


    @Override
    public String getAuthor() {
        return Jobs.getInstance().getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier() {
        return "nghe";
    }

    @Override
    public String getVersion() {
        return Jobs.getInstance().getDescription().getVersion();
    }


    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        if (p == null) {
            return "Player not online";
        }

        if (identifier.equalsIgnoreCase("ten")) {
            if (Files.getInstance().getdata().getString("players." + p.getName()).equals("THOMO")) {
                return "Đào Mỏ";
            }
            if (Files.getInstance().getdata().getString("players." + p.getName()).equals("TIEUPHU")) {
                return "Thợ Mộc";
            }
            if (Files.getInstance().getdata().getString("players." + p.getName()).equals("KHONGCONGHE")) {
                return "";
            }
            if (Files.getInstance().getdata().getString("players." + p.getName()).equals("CONGNHAN")) {
                return "Công Nhân";
            }
            if (Files.getInstance().getdata().getString("players." + p.getName()).equals("NGUDAN")) {
                return "Ngư Dân";
            }
            if (Files.getInstance().getdata().getString("players." + p.getName()).equals("NONGDAN")) {
                return "Nông Dân";
            }
            if (Files.getInstance().getdata().getString("players." + p.getName()).equals("DONRAC")) {
                return "Dọn Rác";
            }
        }

        return null;
    }
}

