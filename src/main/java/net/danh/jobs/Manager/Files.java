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
        savedata();
    }

    public int getAge(Player p) {
        return getdata().getInt("players." + p.getName() + ".Age");
    }

    public int getXP(Player p) {
        return getdata().getInt("players." + p.getName() + ".XP." + getJobs(p));
    }

    public void setAge(Player p, int number) {
        getdata().set("players." + p.getName() + ".Age", number);
        savedata();
    }

    public void setXP(Player p, int number) {
        getdata().set("players." + p.getName() + ".XP." + getJobs(p), number);
        savedata();
    }

    public void addAge(Player p, int number) {
        getdata().set("players." + p.getName() + ".Age", getAge(p) + number);
        savedata();
        if (getAge(p) >= 2 && getAge(p) < 5) {
            p.sendMessage(convert("&aChúc mừng bạn đã lên &6" + getAge(p) + " &atuổi và bạn đã có thể"));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 200"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 2 thức ăn"));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 5 năng lượng"));
        } else if (getAge(p) >= 5 && getAge(p) < 7) {
            p.sendMessage(convert("&aChúc mừng bạn đã lên &6" + getAge(p) + " &atuổi và bạn đã có thể"));
            p.sendMessage(convert("&a+ Mở khóa nghề ăn trộm"));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 300"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 3 thức ăn"));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 10 năng lượng"));
        } else if (getAge(p) >= 7 && getAge(p) < 10) {
            p.sendMessage(convert("&aChúc mừng bạn đã lên &6" + getAge(p) + " &atuổi và bạn đã có thể"));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 500"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 3 thức ăn"));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 20 năng lượng"));
        } else if (getAge(p) >= 10 && getAge(p) < 15) {
            p.sendMessage(convert("&aChúc mừng bạn đã lên &6" + getAge(p) + " &atuổi và bạn đã có thể"));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 700"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 5 thức ăn"));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 30 năng lượng"));
        } else if (getAge(p) >= 15 && getAge(p) < 20) {
            p.sendMessage(convert("&aChúc mừng bạn đã lên &6" + getAge(p) + " &atuổi và bạn đã có thể"));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 900"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 5 thức ăn"));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 40 năng lượng"));
        } else if (getAge(p) >= 20 && getAge(p) < 30) {
            p.sendMessage(convert("&aChúc mừng bạn đã lên &6" + getAge(p) + " &atuổi và bạn đã có thể"));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 1200"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 5 thức ăn"));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 50 năng lượng"));
        } else if (getAge(p) >= 30 && getAge(p) < 50) {
            p.sendMessage(convert("&aChúc mừng bạn đã lên &6" + getAge(p) + " &atuổi và bạn đã có thể"));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 1500"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 5 thức ăn"));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 70 năng lượng"));
        } else if (getAge(p) >= 50) {
            p.sendMessage(convert("&aChúc mừng bạn đã lên &6" + getAge(p) + " &atuổi và bạn đã có thể"));
            p.sendMessage(convert("&a+ Năng lượng tối đa lên 2000"));
            p.sendMessage(convert("&a+ Khi ăn sẽ hồi phục 7 thức ăn"));
            p.sendMessage(convert("&a+ Khi uống nước sẽ hồi phục 100 năng lượng"));
        }
    }

    public void addXP(Player p, int number) {
        getdata().set("players." + p.getName() + ".XP." + getJobs(p), getXP(p) + number);
        checkLevelUp(p);
        savedata();
    }

    public void removeAge(Player p, int number) {
        if (getAge(p) > number) {
            getdata().set("players." + p.getName() + ".Age", getAge(p) - number);
            savedata();
        } else {
            getdata().set("players." + p.getName() + ".Age", 1);
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

    public String getGang(Player p) {
        return getdata().getString("players." + p.getName() + ".Gang");
    }

    public void selectGang(Player p, String name) {
        if (getGang(p) != null) {
            removeMemberGang(p);
        }
        getdata().set("players." + p.getName() + ".Gang", name.toUpperCase());
        if (getLevelGang(p) == 0){
            setLevelGang(p);
        }
        addMemberGang(p);
        setLeveluptimes(p);
        savedata();
    }

    public int getMemberGang(Player p) {
        return getdata().getInt("gangs." + getGang(p) + ".Member");
    }

    public void removeMemberGang(Player p) {
        getdata().set("gangs." + getGang(p) + ".Member", getMemberGang(p) - 1);
        savedata();
    }

    public void addMemberGang(Player p) {
        getdata().set("gangs." + getGang(p) + ".Member", getMemberGang(p) + 1);
        savedata();
    }

    public int getLevelGang(Player p) {
        return getdata().getInt("gangs." + getGang(p) + ".Level");
    }

    public void addLevelGang(Player p, Integer number) {
        getdata().set("gangs." + getGang(p) + ".Level", getLevelGang(p) + number);
        savedata();
    }
    public void setLevelGang(Player p) {
        getdata().set("gangs." + getGang(p) + ".Level", 1);
        savedata();
    }


    public void setLevelGang(Player p, Integer number) {
        getdata().set("gangs." + getGang(p) + ".Level", number);
        savedata();
    }

    public void removeLevelGang(Player p, Integer number) {
        if (getLevelGang(p) > 1) {
            getdata().set("gangs." + getGang(p) + ".Level", getLevelGang(p) - number);
        } else {
            getdata().set("gangs." + getGang(p) + ".Level", 1);
        }
        savedata();
    }

    public void checkLevelUp(Player p) {
        if (getXP(p) >= (((getAge(p) + getLevelGang(p)) / 2)) * 1000) {
            addLevelGang(p, 1);
            setXP(p, 0);
            addLeveluptimes(p, 1);
            p.sendMessage(Files.getInstance().convert("&aBạn đã giúp Gang lên cấp độ &6" + getLevelGang(p) + "&a vì thế bạn sẽ được &6+1 &ađiểm Gang &b(Điểm của bạn là &d" + getleveluptimes(p) + "&b)"));
        }
    }

    public int getleveluptimes(Player p) {
        return getdata().getInt("gangs." + getGang(p) + ".LevelUpTimes." + p.getName());
    }

    public void addLeveluptimes(Player p, Integer number) {
        getdata().set("gangs." + getGang(p) + ".LevelUpTimes." + p.getName(), getLevelGang(p) + number);
        savedata();
    }

    public void setLeveluptimes(Player p) {
        getdata().set("gangs." + getGang(p) + ".LevelUpTimes." + p.getName(), 0);
        savedata();
    }

    public String convert(String s) {
        return s.replaceAll("&", "§");
    }
}
