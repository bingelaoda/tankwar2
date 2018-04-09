package com.binge.bean;

import java.io.IOException;

import com.binge.utils.Configs;
import com.binge.utils.DrawUtils;

public class Grass extends Element {
	public Grass(int x,int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public void draw() {

		try {
			DrawUtils.draw(Configs.IMG_GRASS, x, y);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
	
	@Override
	public int getLever() {
		// TODO 自动生成的方法存根
		return 1;
	}

}
