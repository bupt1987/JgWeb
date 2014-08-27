package com.zhaidaosi.game.server.sdm.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zhaidaosi.game.jgframework.common.sdm.BaseModel;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "user", catalog = "jgframework")
public class User extends BaseModel {

    // Fields

    private Integer id;
    private String username;
    private String password;
    private Long lastLoginTime;
    private Long creatTime;

    // Constructors

    /** default constructor */
    public User() {
    }

    /** full constructor */
    public User(String username, String password, Long lastLoginTime, Long creatTime) {
        this.username = username;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
        this.creatTime = creatTime;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "username", nullable = false, length = 30)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "last_login_time", nullable = false)
    public Long getLastLoginTime() {
        return this.lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Column(name = "creat_time", nullable = false)
    public Long getCreatTime() {
        return this.creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }

}