package com.binge.bean;

import java.io.IOException;

import com.binge.utils.Configs;
import com.binge.utils.DrawUtils;

public class Water extends Element implements Blockable{
	public Water(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void draw() {
		try {
			DrawUtils.draw(Configs.IMG_WATER, x, y);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
}
