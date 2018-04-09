package com.binge.bean;

import java.io.IOException;
import com.binge.game.MyTankWar;
import com.binge.utils.Configs;
import com.binge.utils.DrawUtils;

public class Blast extends Element {

	boolean isBigBoom = false;
	
	int limit = 3;
	
	public Blast(Element e) {
		this(e, false);		
	}
	public Blast(Element e,boolean isBigBoom) {
		//元素e的坐标
		int eX = e.getX();
		int eY = e.getY();
		//元素e的宽度和高度
		int eWidth = Configs.ELEMENT_WIDTH;
		int eHeight = Configs.ELEMENT_HEIGHT;
		
		int blastWidth = 0;
		int blastHeight = 0;
		try {
			int[] cor = DrawUtils.getSize("res/img/blast_8.gif");
			blastWidth = cor[0];
			blastHeight = cor[1];
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		x = (int) (eX - (blastWidth-eWidth)/2.0 + 0.5);
		y = (int) (eY -(blastHeight-eHeight)/2.0 + 0.5);
	}
	
	
	@Override
	public void draw() {
		try {
			if (isBigBoom) {
				limit = 0;
			}else {
				limit = 3;
			}
			
			for(int i=0;i<Configs.IMG_BLASTS.length-limit;i++) {
				DrawUtils.draw(Configs.IMG_BLASTS[i], x, y);
				System.out.println(i);
			}
			
			MyTankWar.elementList.remove(this);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
