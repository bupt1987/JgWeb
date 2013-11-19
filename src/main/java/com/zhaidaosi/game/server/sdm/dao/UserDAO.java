package com.zhaidaosi.game.server.sdm.dao;

import org.springframework.stereotype.Repository;

import com.zhaidaosi.game.jgframework.common.sdm.BaseDao;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.dao.User
 * @author MyEclipse Persistence Tools
 */
@Repository
public class UserDAO extends BaseDao {
	// property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String NICKNAME = "nickname";
	public static final String LAST_LOGIN_TIME = "lastLoginTime";
	public static final String CREAT_TIME = "creatTime";

	public UserDAO(){
		super.setTableName("user");
		super.setModelName("User");
	}
	
}