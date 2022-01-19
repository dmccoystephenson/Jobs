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
                return "Thợ Mỏ";
            }
            if (Files.getInstance().getdata().getString("players." + p.getName()).equals("THOMOC")) {
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
            if (Files.getInstance().getdata().getString("players." + p.getName()).equals("LAOCONG")) {
                return "Lao Công";
            }
            if (Files.getInstance().getdata().getString("players." + p.getName()).equals("BACSI")) {
                return "Bác Sĩ";
            }
            if (Files.getInstance().getdata().getString("players." + p.getName()).equals("ANTROM")) {
                return "Ăn Trộm";
            }
            if (Files.getInstance().getdata().getString("players." + p.getName()).equals("CANHSAT")) {
                return "Cảnh Sát";
            }
        }

        if (identifier.equalsIgnoreCase("nangluong")) {
            if (Files.getInstance().getPower(p) >= 81) {
                return "&a" + String.valueOf(Files.getInstance().getPower(p)) + "%";
            }
            if (Files.getInstance().getPower(p) <= 80 && Files.getInstance().getPower(p) >= 51) {
                return "&6" + String.valueOf(Files.getInstance().getPower(p)) + "%";
            }

            if (Files.getInstance().getPower(p) <= 50 && Files.getInstance().getPower(p) >= 31) {
                return "&c" + String.valueOf(Files.getInstance().getPower(p)) + "%";
            }

            if (Files.getInstance().getPower(p) <= 30 && Files.getInstance().getPower(p) >= 0) {
                return "&4" + String.valueOf(Files.getInstance().getPower(p)) + "%";
            }
        }

        return null;
    }
}

