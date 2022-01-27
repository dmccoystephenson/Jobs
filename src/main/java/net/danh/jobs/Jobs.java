package net.danh.jobs;

import net.danh.jobs.Commands.Commands;
import net.danh.jobs.Events.*;
import net.danh.jobs.Hook.PlaceholderAPI;
import net.danh.jobs.Manager.Files;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

public final class Jobs extends JavaPlugin implements Listener {


    public static Economy economy;
    private static Jobs instance;

    public static Jobs getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getLogger().log(Level.INFO, "Hooked onto PlaceholderAPI");
            new PlaceholderAPI().register();
        } else {
            getServer().getPluginManager().disablePlugin(this);
        }
        if (getServer().getPluginManager().isPluginEnabled("Vault")) {
            getLogger().log(Level.INFO, "Hooked onto Vault");
        } else {
            getServer().getPluginManager().disablePlugin(this);
        }
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Fishing(), this);
        getServer().getPluginManager().registerEvents(new Interact(), this);
        getServer().getPluginManager().registerEvents(new Regen(), this);
        getServer().getPluginManager().registerEvents(new Eating(), this);
        getServer().getPluginManager().registerEvents(new Death(), this);
        getCommand("nghe").setExecutor(new Commands());
        getCommand("chonnghe").setExecutor(new Commands());
        getCommand("doinghe").setExecutor(new Commands());
        getCommand("danhsachnghe").setExecutor(new Commands());
        getCommand("caydao").setExecutor(new Commands());
        getCommand("thuocbacsi").setExecutor(new Commands());
        getCommand("115").setExecutor(new Commands());
        getCommand("hangcam").setExecutor(new Commands());
        getCommand("jobs").setExecutor(new Commands());
        getCommand("power").setExecutor(new Commands());
        getCommand("thongtin").setExecutor(new Commands());
        Files.getInstance().createconfig();
        (new BukkitRunnable() {
            public void run() {
                Iterator var2 = Bukkit.getOnlinePlayers().iterator();
                while (var2.hasNext()) {
                    Player p = (Player) var2.next();
                    List<String> w = getConfig().getStringList("available-worlds");
                    if (w.contains(p.getWorld().getName())) {
                        if (Files.getInstance().getAge(p) == 1) {
                            if (Files.getInstance().getPower(p) > 100) {
                                Files.getInstance().setPower(p, 100);
                            }
                        } else if (Files.getInstance().getAge(p) >= 2 && Files.getInstance().getAge(p) < 5) {
                            if (Files.getInstance().getPower(p) > 200) {
                                Files.getInstance().setPower(p, 200);
                            }
                        } else if (Files.getInstance().getAge(p) >= 5 && Files.getInstance().getAge(p) < 7) {
                            if (Files.getInstance().getPower(p) > 300) {
                                Files.getInstance().setPower(p, 300);
                            }
                        } else if (Files.getInstance().getAge(p) >= 7 && Files.getInstance().getAge(p) < 10) {
                            if (Files.getInstance().getPower(p) > 500) {
                                Files.getInstance().setPower(p, 500);
                            }
                        } else if (Files.getInstance().getAge(p) >= 10 && Files.getInstance().getAge(p) < 15) {
                            if (Files.getInstance().getPower(p) > 700) {
                                Files.getInstance().setPower(p, 700);
                            }
                        } else if (Files.getInstance().getAge(p) >= 15 && Files.getInstance().getAge(p) < 20) {
                            if (Files.getInstance().getPower(p) > 900) {
                                Files.getInstance().setPower(p, 900);
                            }
                        } else if (Files.getInstance().getAge(p) >= 20 && Files.getInstance().getAge(p) < 30) {
                            if (Files.getInstance().getPower(p) > 1200) {
                                Files.getInstance().setPower(p, 1200);
                            }
                        } else if (Files.getInstance().getAge(p) >= 30 && Files.getInstance().getAge(p) < 50) {
                            if (Files.getInstance().getPower(p) > 1500) {
                                Files.getInstance().setPower(p, 1500);
                            }
                        } else if (Files.getInstance().getAge(p) >= 50) {
                            if (Files.getInstance().getPower(p) > 2000) {
                                Files.getInstance().setPower(p, 2000);
                            }
                        }
                        if (Files.getInstance().getPower(p) <= 30) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, Integer.MIN_VALUE));
                        } else {
                            p.removePotionEffect(PotionEffectType.CONFUSION);
                        }
                    }
                }
            }
        }).runTaskTimer(this, 20L, 20L);
        (new BukkitRunnable() {
            public void run() {
                Iterator var2 = Bukkit.getOnlinePlayers().iterator();
                while (var2.hasNext()) {
                    Player p = (Player) var2.next();
                    List<String> w = getConfig().getStringList("available-worlds");
                    if (w.contains(p.getWorld().getName())) {
                        if (Files.getInstance().getconfig().getBoolean("EnableAge")) {
                            Files.getInstance().addAge(p, 1);
                        } else {
                            continue;
                        }
                    }
                }
            }
        }).runTaskTimer(this, 7200 * 20L, 7200 * 20L);
    }

    @Override
    public void onDisable() {
        Files.getInstance().savedata();
        Files.getInstance().saveconfig();
        Files.getInstance().savelanguage();
    }


    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null)
            economy = economyProvider.getProvider();
        return (economy != null);
    }
}
