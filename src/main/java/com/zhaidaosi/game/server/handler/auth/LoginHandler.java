package com.zhaidaosi.game.server.handler.auth;

import java.util.HashMap;


import com.zhaidaosi.game.jgframework.Boot;
import com.zhaidaosi.game.jgframework.common.BaseString;
import com.zhaidaosi.game.jgframework.common.encrpt.BaseMd5;
import com.zhaidaosi.game.jgframework.common.spring.ServiceManager;
import com.zhaidaosi.game.jgframework.connector.AuthConnector;
import com.zhaidaosi.game.jgframework.handler.BaseHandler;
import com.zhaidaosi.game.jgframework.message.IBaseMessage;
import com.zhaidaosi.game.jgframework.message.InMessage;
import com.zhaidaosi.game.jgframework.message.OutMessage;
import com.zhaidaosi.game.jgframework.rsync.RsyncManager;
import com.zhaidaosi.game.jgframework.session.SessionManager;
import com.zhaidaosi.game.server.rsync.UserRsync;
import com.zhaidaosi.game.server.sdm.model.User;
import com.zhaidaosi.game.server.sdm.service.UserService;
import io.netty.channel.Channel;

public class LoginHandler extends BaseHandler {

    UserService userService = (UserService) ServiceManager.getService(UserService.BEAN_ID);

    @Override
    public IBaseMessage run(InMessage im, Channel ch) throws Exception {
        HashMap<String, Object> args = im.getP();
        String username = (String) args.get("username");
        String password = (String) args.get("password");
        if (!BaseString.isEmpty(username)
                && !BaseString.isEmpty(password)) {
            User user = userService.findByUserName(username);
            if (user == null) {
                return OutMessage.showError("该用户不存在", 10);
            }
            String checkPassword = BaseMd5.encrypt(username + password);
            if (checkPassword.equals(user.getPassword())) {
                int userId = user.getId();
                String secret = SessionManager.createSecret(userId);
                HashMap<String, Object> result = new HashMap<String, Object>();
                result.put("address", Boot.getServiceAddress(SessionManager.getServerIp(userId)));
                result.put("secret", secret);
                user.setLastLoginTime(System.currentTimeMillis());
                RsyncManager.add(userId, UserRsync.class, user);
                return OutMessage.showSucc(result);
            }
        }
        return OutMessage.showError("账号密码错误", 11);
    }


}
