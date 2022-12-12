package com.yikuni.mc.reimueconomy.core;

import com.yikuni.mc.reimueconomy.ReimuEconomy;
import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;

import java.util.List;

public class ReimuEconomyImpl extends AbstractEconomy {
    private final String simbol;
    private final String currencyNamePlural;
    private final String currencyNameSingular;
    public ReimuEconomyImpl(){
        String simbol1 = ReimuEconomy.INSTANCE.getConfig().getString("currency.symbol");
        simbol = simbol1 == null? "coin": simbol1;
        String currencyNamePlural1 = ReimuEconomy.INSTANCE.getConfig().getString("currency.name_plural");
        currencyNamePlural = currencyNamePlural1 == null? "CNY": currencyNamePlural1;
        String currencyNameSingular1 = ReimuEconomy.INSTANCE.getConfig().getString("currency.name_singular");
        currencyNameSingular = currencyNameSingular1 == null? "CNY": currencyNameSingular1;

    }


    @Override
    public boolean isEnabled() {
        return ReimuEconomy.INSTANCE.isEnabled();
    }

    @Override
    public String getName() {
        return "Reimu Economy";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return -1;
    }

    @Override
    public String format(double amount) {
        return String.format("%f %s", amount, simbol);
    }

    @Override
    public String currencyNamePlural() {
        return currencyNamePlural;
    }

    @Override
    public String currencyNameSingular() {
        return currencyNameSingular;
    }

    @Override
    public boolean hasAccount(String playerName) {

        return false;
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(playerName);
    }

    @Override
    public double getBalance(String playerName) {
        return 0;
    }

    @Override
    public double getBalance(String playerName, String world) {
        return getBalance(playerName);
    }

    @Override
    public boolean has(String playerName, double amount) {
        return false;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return false;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return false;
    }

    public String getPlayerUUID(String playerName){
        return Bukkit.getOfflinePlayer(playerName).getUniqueId().toString();
    }


}
