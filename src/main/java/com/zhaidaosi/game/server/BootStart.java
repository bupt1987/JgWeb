package com.zhaidaosi.game.server;

import javax.servlet.http.HttpServlet;

import com.zhaidaosi.game.jgframework.Boot;
import com.zhaidaosi.game.server.model.player.PlayFactory;

@SuppressWarnings("serial")
public class BootStart extends HttpServlet  {

	private static void start(){
		Boot.setActionPackage("com.zhaidaosi.game.server.model.action");
		Boot.setAreaPackage("com.zhaidaosi.game.server.model.area");
		Boot.setPlayerFactory(new PlayFactory());
		Boot.start();
	}
	
	@Override
	public void init() {
		start();
	}
	
	public static void main(String[] args) {
		start();
	}
	
}
