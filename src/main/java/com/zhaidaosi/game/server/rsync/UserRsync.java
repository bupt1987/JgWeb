package com.zhaidaosi.game.server.rsync;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhaidaosi.game.jgframework.common.sdm.IBaseModel;
import com.zhaidaosi.game.jgframework.common.spring.ServiceManager;
import com.zhaidaosi.game.jgframework.rsync.BaseRsync;
import com.zhaidaosi.game.server.sdm.model.User;
import com.zhaidaosi.game.server.sdm.service.UserService;

public class UserRsync extends BaseRsync {

	private static final Logger log = LoggerFactory.getLogger(UserRsync.class);

	@Override
	public void runRsync() {
		UserService service = (UserService) ServiceManager.getService(UserService.BEANID);
		for (Map.Entry<Integer, IBaseModel> entry : rsyncMap.entrySet()) {
			User user = (User) entry.getValue();
			service.update(user);
			log.info("rsync => " + user);
		}
	}

}
