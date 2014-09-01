package com.zhaidaosi.game.server.sdm.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhaidaosi.game.jgframework.common.cache.BaseLocalCached;
import com.zhaidaosi.game.jgframework.common.encrpt.BaseMd5;
import com.zhaidaosi.game.jgframework.common.sdm.BaseService;
import com.zhaidaosi.game.server.sdm.dao.UserDAO;
import com.zhaidaosi.game.server.sdm.dao.UserInfoDAO;
import com.zhaidaosi.game.server.sdm.model.User;
import com.zhaidaosi.game.server.sdm.model.UserInfo;

public class UserService extends BaseService {

    public final static String BEAN_ID = "userService";

    private static BaseLocalCached cached = new BaseLocalCached();

    @Autowired
    protected UserDAO dao;

    @Autowired
    protected UserInfoDAO userInfoDao;

    @PostConstruct
    protected void setDao() {
        super.setDao(dao);
    }

    public User findByUserName(String username) {
        String cacheKey = "findByUserName_" + username;
        Object user = cached.get(cacheKey);
        if (cached.checkInvalid(user)) {
            List<?> list = super.findByProperty(UserDAO.USERNAME, username);
            if (list.size() > 0) {
                user = list.get(0);
            } else {
                user = null;
            }
            cached.set("UserService_findByUserName_" + username, list, 300);
        }
        return user == null ? null : (User) user;
    }

    public void addUser(String username, String password, String nickName) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(BaseMd5.encrypt(username + password));
        user.setCreatTime(System.currentTimeMillis());
        user.setLastLoginTime(0L);
        dao.save(user);

        UserInfo userinfo = new UserInfo();
        userinfo.setActions(UserInfoService.getDefaultActions());
        userinfo.setExperience(0);
        userinfo.setLevel(1);
        userinfo.setNickname(nickName);
        userinfo.setUid(user.getId());
        userInfoDao.save(userinfo);
    }


}
