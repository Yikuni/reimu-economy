package com.yikuni.mc.reimueconomy.event;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

public abstract class PlayerMoneyEvent extends PlayerEvent {
    public PlayerMoneyEvent(Player who) {
        super(who);
    }
}
