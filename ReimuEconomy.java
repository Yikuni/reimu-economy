package com.yikuni.mc.reimueconomy;

import com.yikuni.mc.reimueconomy.core.ReimuEconomyImpl;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class ReimuEconomy extends JavaPlugin {
    public static JavaPlugin INSTANCE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;
        getServer().getServicesManager().register(Economy.class, new ReimuEconomyImpl(), this, ServicePriority.Highest);
        RegisteredServiceProvider<Economy> eco = getServer().getServicesManager().getRegistration(Economy.class);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
