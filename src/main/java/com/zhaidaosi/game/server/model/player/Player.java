package com.zhaidaosi.game.server.model.player;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.zhaidaosi.game.jgframework.common.BaseJson;
import com.zhaidaosi.game.jgframework.model.BasePosition;
import com.zhaidaosi.game.jgframework.model.action.ActionManager;
import com.zhaidaosi.game.jgframework.model.action.IBaseAction;
import com.zhaidaosi.game.jgframework.model.area.AreaManager;
import com.zhaidaosi.game.jgframework.model.area.IBaseArea;
import com.zhaidaosi.game.jgframework.model.entity.BasePlayer;
import com.zhaidaosi.game.jgframework.rsync.RsyncManager;
import com.zhaidaosi.game.server.model.action.AttackAction;
import com.zhaidaosi.game.server.model.area.Area;
import com.zhaidaosi.game.server.rsync.UserInfoRsync;
import com.zhaidaosi.game.server.sdm.model.UserInfo;

public class Player extends BasePlayer {
	
	private static Map<Integer, Integer> levelExperience = new HashMap<Integer, Integer>();

	private UserInfo userInfo;
	private BasePosition oldPosition;
	private Map<String, Integer> actionJson = new HashMap<String, Integer>();
	
	static{
		levelExperience.put(1, 100);
		levelExperience.put(2, 300);
		levelExperience.put(3, 500);
	}
	
	@SuppressWarnings("unchecked")
	public void init(UserInfo userInfo){
		this.userInfo = userInfo;
		this.name = userInfo.getNickname();
		this.level = userInfo.getLevel();
		this.totalHp = this.level * 100;
		this.hp = this.totalHp;
		this.experience = userInfo.getExperience();
		
		actionJson = BaseJson.JsonToObject(userInfo.getActions(), Map.class);
		
		for (Map.Entry<String, Integer> entry : actionJson.entrySet()) {
			AttackAction attackAction = (AttackAction) ActionManager.getAction(Integer.parseInt(entry.getKey()));
			attackAction.setLevel(entry.getValue());
			this.addAction(attackAction);
		}
		
		IBaseArea area = AreaManager.getArea(Area.ID);
		area.addPlayer(this);
	}
	
	public UserInfo gUserInfo(){
		return userInfo;
	}
	
	private void upLevel(int level){
		if(this.level != level){
			this.level = level;
			userInfo.setLevel(level);
			for (Entry<Integer, IBaseAction> entry : actions.entrySet()) {
				IBaseAction action = entry.getValue();
				if(action instanceof AttackAction){
					((AttackAction) action).setLevel(level);
					actionJson.put(action.getId() + "", level);
				}
				userInfo.setActions(BaseJson.ObjectToJson(actionJson));
			}
		}
	}
	
	public void addExperience(int experience){
		this.experience += experience;
		int _level = level;
		Integer _experience = levelExperience.get(_level);
		while(_experience != null && this.experience > _experience){
			_level++;
			_experience = levelExperience.get(_level);
		}
		userInfo.setExperience(this.experience);
		upLevel(_level);
		RsyncManager.add(id, UserInfoRsync.class, userInfo);
	}

	public BasePosition gOldPosition() {
		return oldPosition;
	}

	public void sOldPosition(BasePosition oldPosition) {
		this.oldPosition = oldPosition;
	}
	
}
