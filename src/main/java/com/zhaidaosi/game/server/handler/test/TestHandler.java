package com.zhaidaosi.game.server.handler.test;


import com.zhaidaosi.game.jgframework.handler.BaseHandler;
import com.zhaidaosi.game.jgframework.message.IBaseMessage;
import com.zhaidaosi.game.jgframework.message.InMessage;
import com.zhaidaosi.game.jgframework.message.OutMessage;
import io.netty.channel.Channel;

public class TestHandler extends BaseHandler {

    @Override
    public IBaseMessage run(InMessage im, Channel ch) {
        Object msg = im.getMember("msg");
        if (msg != null) {
            return OutMessage.showSucc("system : " + msg);
        } else {
            return OutMessage.showSucc(im.toString());
        }
    }

}
