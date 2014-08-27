package com.zhaidaosi.game.server;

import javax.servlet.http.HttpServlet;

import com.zhaidaosi.game.jgframework.Boot;
import com.zhaidaosi.game.server.model.player.PlayerFactory;

@SuppressWarnings("serial")
public class BootStart extends HttpServlet {

    private static void start() {
        //设置action所在包路径，不设置不扫描
        Boot.setActionPackage("com.zhaidaosi.game.server.model.action");
        //设置area所在包路径，不设置不扫描
        Boot.setAreaPackage("com.zhaidaosi.game.server.model.area");
        //设置player工厂，默认为BasePlayerFactory
        Boot.setPlayerFactory(new PlayerFactory());
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
