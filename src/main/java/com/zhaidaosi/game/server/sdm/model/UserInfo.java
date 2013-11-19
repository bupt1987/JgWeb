package com.zhaidaosi.game.server.sdm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.zhaidaosi.game.jgframework.common.sdm.BaseModel;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "user_info", catalog = "jgframework", uniqueConstraints = @UniqueConstraint(columnNames = "uid"))
public class UserInfo extends BaseModel {

	// Fields

	private Integer uid;
	private String nickname;
	private Integer level;
	private Integer experience;
	private String actions;

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** full constructor */
	public UserInfo(Integer uid, String nickname, Integer level, Integer experience, String actions) {
		this.uid = uid;
		this.nickname = nickname;
		this.level = level;
		this.experience = experience;
		this.actions = actions;
	}

	@Id
	@Column(name = "uid", unique = true, nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "nickname", nullable = false, length = 30)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "level", nullable = false)
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "experience", nullable = false)
	public Integer getExperience() {
		return this.experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	@Column(name = "actions", nullable = false, length = 65535)
	public String getActions() {
		return this.actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

}