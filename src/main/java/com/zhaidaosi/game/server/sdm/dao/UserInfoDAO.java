package com.zhaidaosi.game.server.sdm.dao;

import org.springframework.stereotype.Repository;

import com.zhaidaosi.game.jgframework.common.sdm.BaseDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.zhaidaosi.game.server.sdm.dao.UserInfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class UserInfoDAO extends BaseDao {
	// property constants
	public static final String UID = "uid";
	public static final String NICKNAME = "nickname";
	public static final String LEVEL = "level";
	public static final String EXPERIENCE = "experience";
	public static final String ACTIONS = "actions";

	public UserInfoDAO(){
		super.setTableName("user_info");
		super.setModelName("UserInfo");
	}
	
}