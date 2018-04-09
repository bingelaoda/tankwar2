package com.binge.game;

import com.binge.utils.Configs;

public class MainApp {

	public static void main(String[] args) {
		
		MyTankWar myTankWar = new MyTankWar(Configs.TITLE, Configs.WIDTH, Configs.HEIGHT, Configs.FPS);
		myTankWar.start();
	}
}
