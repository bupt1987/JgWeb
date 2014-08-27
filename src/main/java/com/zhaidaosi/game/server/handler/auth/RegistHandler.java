package com.zhaidaosi.game.server.handler.auth;

import org.jboss.netty.channel.Channel;

import com.zhaidaosi.game.jgframework.common.BaseString;
import com.zhaidaosi.game.jgframework.common.spring.ServiceManager;
import com.zhaidaosi.game.jgframework.handler.BaseHandler;
import com.zhaidaosi.game.jgframework.message.IBaseMessage;
import com.zhaidaosi.game.jgframework.message.InMessage;
import com.zhaidaosi.game.jgframework.message.OutMessage;
import com.zhaidaosi.game.server.sdm.service.UserService;

public class RegistHandler extends BaseHandler {

    UserService userService = (UserService) ServiceManager.getService(UserService.BEANID);

    @Override
    public IBaseMessage run(InMessage im, Channel ch) {

        String username = (String) im.getMember("username");
        String password = (String) im.getMember("password");
        String nickname = (String) im.getMember("nickname");

        if (BaseString.isEmpty(username) || BaseString.isEmpty(password) || BaseString.isEmpty(nickname)) {
            return OutMessage.showError("输入内容不能为空", 10);
        }

        if (userService.findByUserName(username) != null) {
            return OutMessage.showError("该用户已被注册", 11);
        }

        userService.addUser(username, password, nickname);

        return OutMessage.showSucc("succ");
    }

}
