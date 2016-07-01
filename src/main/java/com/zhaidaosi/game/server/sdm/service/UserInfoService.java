package com.zhaidaosi.game.server.sdm.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhaidaosi.game.jgframework.common.BaseJson;
import com.zhaidaosi.game.jgframework.common.sdm.BaseService;
import com.zhaidaosi.game.jgframework.common.sdm.IBaseModel;
import com.zhaidaosi.game.jgframework.rsync.RsyncManager;
import com.zhaidaosi.game.server.model.action.AttackAction;
import com.zhaidaosi.game.server.rsync.UserInfoRsync;
import com.zhaidaosi.game.server.sdm.dao.UserInfoDAO;

public class UserInfoService extends BaseService {

    public final static String BEAN_ID = "userInfoService";

    @Autowired
    protected UserInfoDAO dao;

    @PostConstruct
    protected void setDao() {
        super.setDao(dao);
    }

    public IBaseModel findByUid(int uid) {
        IBaseModel model = RsyncManager.get(uid, UserInfoRsync.class);
        if (model == null) {
            model = super.findOneByProperty(UserInfoDAO.UID, uid);
        }
        return model;
    }

    public static String getDefaultActions() {
        Map<String, Integer> actions = new HashMap<>();
        actions.put(Integer.toString(AttackAction.ID), 1);
        return BaseJson.ObjectToJson(actions);
    }

}
