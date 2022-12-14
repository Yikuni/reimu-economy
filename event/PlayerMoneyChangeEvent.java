package com.yikuni.mc.reimueconomy.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerMoneyChangeEvent extends PlayerMoneyEvent{
    private static final HandlerList handlers = new HandlerList();
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
    private final Double amount;
    public PlayerMoneyChangeEvent(Player who, Double amount) {
        super(who);
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }
}
