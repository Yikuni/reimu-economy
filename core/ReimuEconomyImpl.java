package com.yikuni.mc.reimueconomy.core;

import com.yikuni.mc.reimueconomy.ReimuEconomyPlugin;
import com.yikuni.mc.reimueconomy.dao.ReimuEconomyDao;
import com.yikuni.mc.reimueconomy.domain.ReimuEconomy;
import com.yikuni.mc.reimueconomy.util.MybatisUtil;
import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.logging.Logger;

public class ReimuEconomyImpl extends AbstractEconomy {
    private final Logger log = ReimuEconomyPlugin.getInstance().getLogger();
    private final String symbol;
    private final String currencyNamePlural;
    private final String currencyNameSingular;
    private final Double initialMoney;
    public ReimuEconomyImpl(){
        String simbol1 = ReimuEconomyPlugin.getInstance().getConfig().getString("currency.symbol");
        symbol = simbol1 == null? "coin": simbol1;
        String currencyNamePlural1 = ReimuEconomyPlugin.getInstance().getConfig().getString("currency.name_plural");
        currencyNamePlural = currencyNamePlural1 == null? "CNY": currencyNamePlural1;
        String currencyNameSingular1 = ReimuEconomyPlugin.getInstance().getConfig().getString("currency.name_singular");
        currencyNameSingular = currencyNameSingular1 == null? "CNY": currencyNameSingular1;
        initialMoney = ReimuEconomyPlugin.getInstance().getConfig().getDouble("currency.initial_money");
    }


    @Override
    public boolean isEnabled() {
        return ReimuEconomyPlugin.getInstance().isEnabled();
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
        return String.format("%f %s", amount, symbol);
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
        SqlSession session = MybatisUtil.getSqlSession();
        ReimuEconomyDao mapper = session.getMapper(ReimuEconomyDao.class);
        int i = mapper.hasAccount(playerName);
        session.close();
        return i > 0;
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(playerName);
    }

    @Override
    public double getBalance(String playerName) {
        SqlSession session = MybatisUtil.getSqlSession();
        ReimuEconomyDao mapper = session.getMapper(ReimuEconomyDao.class);
        ReimuEconomy reimuEconomy = mapper.selectByPrimaryKey(playerName);
        Double money = reimuEconomy.getMoney();
        session.close();
        return money;
    }

    @Override
    public double getBalance(String playerName, String world) {
        return getBalance(playerName);
    }

    @Override
    public boolean has(String playerName, double amount) {
        return getBalance(playerName) >= amount;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return has(playerName, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        if (amount < 0){
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "withdraw amount must > 0");
        }
        SqlSession session = MybatisUtil.getSqlSession();
        ReimuEconomyDao mapper = session.getMapper(ReimuEconomyDao.class);
        try{
            ReimuEconomy reimuEconomy = mapper.selectByPrimaryKey(playerName);
            if (reimuEconomy == null){
                return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Failed to find player account");
            }
            if (reimuEconomy.getMoney() >= amount){
                reimuEconomy.setMoney(reimuEconomy.getMoney() - amount);
                mapper.updateByPrimaryKey(reimuEconomy);
                return new EconomyResponse(amount, reimuEconomy.getMoney(), EconomyResponse.ResponseType.SUCCESS, null);
            }else {
                return new EconomyResponse(0, reimuEconomy.getMoney(), EconomyResponse.ResponseType.FAILURE, "Not enough money");
            }
        }finally {
            session.close();
        }
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return withdrawPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        if (amount < 0){
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Deposit amount must > 0");
        }
        SqlSession session = MybatisUtil.getSqlSession();
        ReimuEconomyDao mapper = session.getMapper(ReimuEconomyDao.class);
        try{
            ReimuEconomy reimuEconomy = mapper.selectByPrimaryKey(playerName);
            if (reimuEconomy == null){
                return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Failed to find player account");
            }
            reimuEconomy.setMoney(reimuEconomy.getMoney() + amount);
            mapper.updateByPrimaryKey(reimuEconomy);
        }finally {
            session.close();
        }
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Unknown Error");
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return depositPlayer(playerName, amount);
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
        SqlSession session = MybatisUtil.getSqlSession();
        ReimuEconomyDao mapper = session.getMapper(ReimuEconomyDao.class);
        try {
            mapper.insert(new ReimuEconomy(playerName, initialMoney));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(playerName);
    }



}
