package com.binge.bean;

import java.io.IOException;

import com.binge.utils.Configs;
import com.binge.utils.DrawUtils;

public class Steel extends Element implements Blockable,Attachable,Brokenable {
	
	private int blood = 200;
	
	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public Steel(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void draw() {
		
		try {
			DrawUtils.draw(Configs.IMG_STEEL, x, y);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@Override
	public Blast attached() {
		
		Blast blast = new Blast(this);
		return blast;
	}

	@Override
	public Blast broke() {
		Blast blast = new Blast(this);
		return blast;	
	}

}
