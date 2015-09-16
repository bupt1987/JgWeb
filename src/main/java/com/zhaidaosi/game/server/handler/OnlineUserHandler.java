package com.zhaidaosi.game.server.handler;


import com.zhaidaosi.game.jgframework.handler.BaseHandler;
import com.zhaidaosi.game.jgframework.message.IBaseMessage;
import com.zhaidaosi.game.jgframework.message.InMessage;
import com.zhaidaosi.game.jgframework.message.OutMessage;
import com.zhaidaosi.game.jgframework.model.area.AreaManager;
import com.zhaidaosi.game.server.model.area.Area;
import io.netty.channel.Channel;

public class OnlineUserHandler extends BaseHandler {

    @Override
    public IBaseMessage run(InMessage im, Channel ch) {
        Area area = (Area) AreaManager.getArea(Area.ID);
        return OutMessage.showSucc(area.getPlayers());
    }

}
