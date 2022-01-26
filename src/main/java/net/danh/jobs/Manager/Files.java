package net.danh.jobs.Manager;

import net.danh.jobs.Jobs;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Files {
    private static Files instance;
    private File configFile, languageFile, dataFile;
    private FileConfiguration config, language, data;

    public static Files getInstance() {

        if (instance == null) {
            instance = new Files();
        }
        return instance;
    }

    public void createconfig() {
        configFile = new File(Jobs.getInstance().getDataFolder(), "config.yml");
        languageFile = new File(Jobs.getInstance().getDataFolder(), "language.yml");
        dataFile = new File(Jobs.getInstance().getDataFolder(), "data.yml");

        if (!configFile.exists()) Jobs.getInstance().saveResource("config.yml", false);
        if (!languageFile.exists()) Jobs.getInstance().saveResource("language.yml", false);
        if (!dataFile.exists()) Jobs.getInstance().saveResource("data.yml", false);
        config = new YamlConfiguration();
        language = new YamlConfiguration();
        data = new YamlConfiguration();

        try {
            config.load(configFile);
            language.load(languageFile);
            data.load(dataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public FileConfiguration getconfig() {
        return config;
    }

    public FileConfiguration getlanguage() {
        return language;
    }

    public FileConfiguration getdata() {
        return data;
    }

    public void reloadconfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
        language = YamlConfiguration.loadConfiguration(languageFile);
    }

    public void saveconfig() {
        try {
            config.save(configFile);
        } catch (IOException ignored) {
        }
    }


    public void savelanguage() {
        try {
            language.save(languageFile);
        } catch (IOException ignored) {
        }
    }


    public void savedata() {
        try {
            data.save(dataFile);
        } catch (IOException ignored) {
        }
    }

    public String getJobs(Player p) {
        return getdata().getString("players." + p.getName() + ".Jobs");
    }

    public int getPower(Player p) {
        return getdata().getInt("players." + p.getName() + ".Power");
    }

    public void createJobs(Player p) {
        getdata().set("players." + p.getName() + ".Jobs", "KHONGCONGHE");
        getdata().set("players." + p.getName() + ".Power", 100);
        savedata();
    }

    public void setJobs(Player p, String name) {
        getdata().set("players." + p.getName() + ".Jobs", name);
        if (getdata().getInt("players." + p.getName() + ".Level." + name) >= 1 && getdata().getInt("players." + p.getName() + ".XP." + name) >= 0){
            getdata().set("players." + p.getName() + ".Level." + name, getLevel(p));
            getdata().set("players." + p.getName() + ".XP." + name, + getXP(p));
        } else {
            getdata().set("players." + p.getName() + ".Level." + name, 1);
            getdata().set("players." + p.getName() + ".XP." + name, 0);
        }
        savedata();
    }

    public int getLevel(Player p) {
        return getdata().getInt("players." + p.getName() + ".Level." + getJobs(p));
    }

    public int getXP(Player p) {
        return getdata().getInt("players." + p.getName() + ".XP." + getJobs(p));
    }

    public void setLevel(Player p, int number) {
        getdata().set("players." + p.getName() + ".Level." + getJobs(p), number);
        savedata();
    }

    public void setXP(Player p, int number) {
        getdata().set("players." + p.getName() + ".XP." + getJobs(p), number);
        savedata();
    }

    public void addLevel(Player p, int number) {
        getdata().set("players." + p.getName() + ".Level." + getJobs(p), getLevel(p) + number);
        savedata();
    }

    public void addXP(Player p, int number) {
        getdata().set("players." + p.getName() + ".XP." + getJobs(p), getXP(p) + number);
        checkLevelup(p);
        savedata();
    }

    public void removeLevel(Player p, int number) {
        if (getLevel(p) > number) {
            getdata().set("players." + p.getName() + ".Level." + getJobs(p), getLevel(p) - number);
            savedata();
        } else {
            getdata().set("players." + p.getName() + ".Level", 1);
            savedata();
        }
    }

    public void removeXP(Player p, int number) {
        if (getXP(p) > number) {
            getdata().set("players." + p.getName() + ".XP." + getJobs(p), getXP(p) - number);
            savedata();
        } else {
            getdata().set("players." + p.getName() + ".XP", 0);
            savedata();
        }
    }

    public void checkLevelup(Player p) {
        if (getXP(p) >= (getLevel(p) * 100)) {
            addLevel(p, 1);
            setXP(p, 0);
            p.sendMessage(convert("&aChúc mừng bạn đã lên cấp &6" + getLevel(p) + "&a nghề &c" + getJobs(p)));
        }
        if (getLevel(p) >= 2 && getLevel(p) < 5){
            p.sendMessage(convert("&aCấp độ &6" + getLevel(p) + " &ađã mở khóa:" ));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 200" ));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 2 thức ăn" ));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 5 năng lượng" ));
        } else if (getLevel(p) >= 5 && getLevel(p) < 7){
            p.sendMessage(convert("&aCấp độ &6" + getLevel(p) + " &ađã mở khóa:" ));
            p.sendMessage(convert("&a+ Mở khóa nghề ăn trộm"));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 300"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 3 thức ăn" ));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 10 năng lượng" ));
        } else if (getLevel(p) >= 7 && getLevel(p) < 10) {
            p.sendMessage(convert("&aCấp độ &6" + getLevel(p) + " &ađã mở khóa:" ));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 500"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 3 thức ăn" ));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 20 năng lượng" ));
        } else if (getLevel(p) >= 10 && getLevel(p) < 15) {
            p.sendMessage(convert("&aCấp độ &6" + getLevel(p) + " &ađã mở khóa:" ));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 700"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 5 thức ăn" ));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 30 năng lượng" ));
        } else if (getLevel(p) >= 15 && getLevel(p) < 20) {
            p.sendMessage(convert("&aCấp độ &6" + getLevel(p) + " &ađã mở khóa:" ));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 900"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 5 thức ăn" ));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 40 năng lượng" ));
        } else if (getLevel(p) >= 20 && getLevel(p) < 30) {
            p.sendMessage(convert("&aCấp độ &6" + getLevel(p) + " &ađã mở khóa:" ));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 1200"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 5 thức ăn" ));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 50 năng lượng" ));
        } else if (getLevel(p) >= 30 && getLevel(p) < 50) {
            p.sendMessage(convert("&aCấp độ &6" + getLevel(p) + " &ađã mở khóa:" ));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 1500"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 5 thức ăn" ));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 70 năng lượng" ));
        } else if (getLevel(p) >= 50) {
            p.sendMessage(convert("&aCấp độ &6" + getLevel(p) + " &ađã mở khóa:" ));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 2000"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 7 thức ăn" ));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 100 năng lượng" ));
        }
    }

    public void setPower(Player p, int number) {
        getdata().set("players." + p.getName() + ".Power", number);
        savedata();
    }

    public void addPower(Player p, int number) {
        getdata().set("players." + p.getName() + ".Power", getPower(p) + number);
        savedata();
    }


    public void removePower(Player p, int number) {
        if (getPower(p) > number) {
            getdata().set("players." + p.getName() + ".Power", getPower(p) - number);
            savedata();
        } else {
            getdata().set("players." + p.getName() + ".Power", 0);
            savedata();
        }
    }


    public String convert(String s) {
        return s.replaceAll("&", "§");
    }
}
