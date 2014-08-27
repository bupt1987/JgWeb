package com.zhaidaosi.game.server.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.zhaidaosi.game.jgframework.handler.BaseHandlerChannel;
import com.zhaidaosi.game.jgframework.message.OutMessage;
import com.zhaidaosi.game.jgframework.model.action.IBaseAction;
import com.zhaidaosi.game.jgframework.model.entity.BasePlayer;
import com.zhaidaosi.game.server.model.player.Player;
import com.zhaidaosi.game.server.model.zone.DuelZone;

public class Duel {

    public static final String END = "end";
    public static final String INIT = "init";
    public static final String MSG = "msg";

    public static Player doDuel(Player me, Player target, DuelZone zone, String handlerName) {
        Player hiter;
        Player beHiter;
        Player winer;

        zone.addPlayer(me);
        zone.addPlayer(target);
        BaseHandlerChannel ch = new BaseHandlerChannel(handlerName, zone.getChannelGroup());

        ch.writeGroup(Duel.write("", Duel.INIT, me, target));

        do {
            int select = getHiter();
            switch (select) {
                case 0:
                    hiter = me;
                    beHiter = target;
                    break;
                case 1:
                    hiter = target;
                    beHiter = me;
                    break;
                default:
                    hiter = me;
                    beHiter = target;
                    break;
            }
            IBaseAction action = getAction(hiter);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
            action.doAction(hiter, beHiter, ch);
        } while (me.getHp() > 0 && target.getHp() > 0);

        if (me.getHp() > 0) {
            winer = me;
        } else {
            winer = target;
        }
        ch.writeGroup(write(winer.getName() + " 获胜", END, me, target));
        zone.close();
        me.setHp(me.getTotalHp());
        target.setHp(target.getTotalHp());

        me.gOldPosition().getArea().addPlayer(me);

        target.gOldPosition().getArea().addPlayer(target);

        return winer;
    }

    public static OutMessage write(String msg, String action, Object me, Object target) {
        Map<String, Object> out = new HashMap<String, Object>();
        out.put("msg", msg);
        out.put("action", action);
        out.put("player_me", me);
        out.put("player_target", target);
        return OutMessage.showSucc(out);
    }

    private static int getHiter() {
        return new Random().nextInt(2);
    }

    private static IBaseAction getAction(BasePlayer hiter) {
        IBaseAction[] actions = new IBaseAction[hiter.getActions().size()];
        actions = hiter.getActions().values().toArray(actions);
        int select = 0;
        if (actions.length > 1) {
            int random = actions.length;
            select = new Random().nextInt(random);
        }
        return actions[select];
    }

}
