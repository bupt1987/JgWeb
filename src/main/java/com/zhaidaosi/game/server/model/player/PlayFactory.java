package com.zhaidaosi.game.server.model.player;

import com.zhaidaosi.game.jgframework.model.entity.IBaseCharacter;
import com.zhaidaosi.game.jgframework.model.entity.IBasePlayerFactory;

public class PlayFactory implements IBasePlayerFactory {

	public IBaseCharacter getPlayer() {
		return new Player();
	}

}
