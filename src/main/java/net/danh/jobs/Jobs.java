package net.danh.jobs;

import net.danh.jobs.Events.BlockBreak;
import net.danh.jobs.Events.Join;
import net.danh.jobs.Hook.PlaceholderAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Jobs extends JavaPlugin implements Listener {


    private static Jobs instance;

    public static Jobs getInstance() {
        return instance;
    }


    public static Economy economy;

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


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null)
            economy = economyProvider.getProvider();
        return (economy != null);
    }
}
