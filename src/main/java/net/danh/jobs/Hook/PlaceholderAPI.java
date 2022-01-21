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
            if (Files.getInstance().getJobs(p).equals("THOMO")) {
                return "Thợ Mỏ";
            }
            if (Files.getInstance().getJobs(p).equals("THOMOC")) {
                return "Thợ Mộc";
            }
            if (Files.getInstance().getJobs(p).equals("KHONGCONGHE")) {
                return "";
            }
            if (Files.getInstance().getJobs(p).equals("CONGNHAN")) {
                return "Công Nhân";
            }
            if (Files.getInstance().getJobs(p).equals("NGUDAN")) {
                return "Ngư Dân";
            }
            if (Files.getInstance().getJobs(p).equals("NONGDAN")) {
                return "Nông Dân";
            }
            if (Files.getInstance().getJobs(p).equals("LAOCONG")) {
                return "Lao Công";
            }
            if (Files.getInstance().getJobs(p).equals("BACSI")) {
                return "Bác Sĩ";
            }
            if (Files.getInstance().getJobs(p).equals("ANTROM")) {
                return "Ăn Trộm";
            }
            if (Files.getInstance().getJobs(p).equals("CANHSAT")) {
                return "Cảnh Sát";
            }
        }

        if (identifier.equalsIgnoreCase("nangluong")) {
            if (Files.getInstance().getPower(p) >= 81) {
                return "&a" + Files.getInstance().getPower(p) + "%";
            }
            if (Files.getInstance().getPower(p) <= 80 && Files.getInstance().getPower(p) >= 51) {
                return "&6" + Files.getInstance().getPower(p) + "%";
            }

            if (Files.getInstance().getPower(p) <= 50 && Files.getInstance().getPower(p) >= 31) {
                return "&c" + Files.getInstance().getPower(p) + "%";
            }

            if (Files.getInstance().getPower(p) <= 30 && Files.getInstance().getPower(p) >= 0) {
                return "&4" + Files.getInstance().getPower(p) + "%";
            }
        }

        return null;
    }
}

