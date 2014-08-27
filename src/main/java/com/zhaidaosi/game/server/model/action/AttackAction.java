package com.zhaidaosi.game.server.model.action;

import com.zhaidaosi.game.jgframework.handler.BaseHandlerChannel;
import com.zhaidaosi.game.jgframework.model.action.BaseAction;
import com.zhaidaosi.game.server.model.Duel;
import com.zhaidaosi.game.server.model.player.Player;

public class AttackAction extends BaseAction {

    public static int ID = 1;

    private int level = 1;

    private int minDamage;

    private int maxDamage;

    public AttackAction() {
        super(ID, "基本攻击");
    }

    @Override
    public void doAction(Object self, Object target, BaseHandlerChannel ch) {
        Player selfPlayer = (Player) self;
        Player targetPlayer = (Player) target;
        int damage = minDamage + new java.util.Random().nextInt(maxDamage - minDamage);
        targetPlayer.setHp(targetPlayer.getHp() - damage);
        if (targetPlayer.getHp() < 0) {
            targetPlayer.setHp(0);
        }
        String format = "%1$s 使用 %2$s(%3$d级) 攻击 %4$s 造成 %5$d 点伤害";
        String msg = String.format(format, selfPlayer.getName(), this.getName(), this.getLevel(), targetPlayer.getName(), damage);
        ch.writeGroup(Duel.write(msg, Duel.MSG, self, target));
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        this.maxDamage = 30 * level;
        this.minDamage = 15 * level;
    }


}
