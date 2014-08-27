package com.zhaidaosi.game.server.handler;

import org.jboss.netty.channel.Channel;

import com.zhaidaosi.game.jgframework.handler.BaseHandler;
import com.zhaidaosi.game.jgframework.message.IBaseMessage;
import com.zhaidaosi.game.jgframework.message.InMessage;
import com.zhaidaosi.game.jgframework.message.OutMessage;
import com.zhaidaosi.game.jgframework.model.area.AreaManager;
import com.zhaidaosi.game.server.model.area.Area;

public class OnlineUserHandler extends BaseHandler {

    @Override
    public IBaseMessage run(InMessage im, Channel ch) {
        Area area = (Area) AreaManager.getArea(Area.ID);
        return OutMessage.showSucc(area.getPlayers());
    }

}
