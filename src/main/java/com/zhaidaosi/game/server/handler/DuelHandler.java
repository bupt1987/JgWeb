package com.zhaidaosi.game.server.handler;

import org.jboss.netty.channel.Channel;

import com.zhaidaosi.game.jgframework.handler.BaseHandler;
import com.zhaidaosi.game.jgframework.message.IBaseMessage;
import com.zhaidaosi.game.jgframework.message.InMessage;
import com.zhaidaosi.game.jgframework.message.OutMessage;
import com.zhaidaosi.game.jgframework.session.SessionManager;
import com.zhaidaosi.game.server.model.Duel;
import com.zhaidaosi.game.server.model.player.Player;
import com.zhaidaosi.game.server.model.zone.DuelZone;

public class DuelHandler extends BaseHandler {

	@Override
	public IBaseMessage run(InMessage im, Channel ch) {
		Player me = (Player) ch.getAttachment();
		Integer targetId = 0;
		if(im.getMember("uid") != null){
			targetId = Integer.valueOf((String)im.getMember("uid"));
		}
		if(targetId < 1){
			return OutMessage.showError("目标用户id为空");
		}
		Player target = (Player) SessionManager.getPlayerByUserId(targetId);
		if(target == null){
			return OutMessage.showError("目标用户已下线");
		}
		//new 一个打架场地
		DuelZone zone = new DuelZone();
		Player winer = Duel.doDuel(me, target, zone, this.getHandlerName());
		winer.addExperience(5);
		return OutMessage.showSucc(me);
	}

}
