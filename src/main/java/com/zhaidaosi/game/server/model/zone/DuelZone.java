package com.zhaidaosi.game.server.model.zone;

import com.zhaidaosi.game.jgframework.model.area.BaseZone;
import com.zhaidaosi.game.server.model.player.Player;

public class DuelZone extends BaseZone {

    public final static String ZONE_NAME = "DuelZone";

    public DuelZone() {
        super(ZONE_NAME);
    }

    @Override
    public void init() {

    }

    public void addPlayer(Player player) {
        player.sOldPosition(player.gPosition());
        super.addPlayer(player);
    }

}
