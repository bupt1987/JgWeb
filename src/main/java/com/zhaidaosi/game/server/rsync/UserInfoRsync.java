package com.zhaidaosi.game.server.rsync;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhaidaosi.game.jgframework.common.sdm.IBaseModel;
import com.zhaidaosi.game.jgframework.common.spring.ServiceManager;
import com.zhaidaosi.game.jgframework.rsync.BaseRsync;
import com.zhaidaosi.game.server.sdm.model.UserInfo;
import com.zhaidaosi.game.server.sdm.service.UserInfoService;

public class UserInfoRsync extends BaseRsync {

	private static final Logger log = LoggerFactory.getLogger(UserInfoRsync.class);

	@Override
	public void runRsync() {
		UserInfoService service = (UserInfoService) ServiceManager.getService(UserInfoService.BEANID);
		for (Map.Entry<Integer, IBaseModel> entry : rsyncMap.entrySet()) {
			UserInfo userInfo = (UserInfo) entry.getValue();
			service.update(userInfo);
			log.info("rsync => " + userInfo);
		}
	}

}
