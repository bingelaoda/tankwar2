package com.binge.bean;

import java.io.IOException;

import com.binge.game.MyTankWar;
import com.binge.utils.CollsionUtils;
import com.binge.utils.Configs;
import com.binge.utils.DrawUtils;
import com.binge.utils.SoundUtils;

public class Bullet extends Element {
	
	private Direction direction = Direction.UP;
	private int speed=6;
	//攻击力
	private int power = 50;
	public Bullet(Tank tank) {
		try {
			SoundUtils.play(Configs.MUSIC_FIRE);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		//子弹的方向和坦克的方向一致
		this.direction = tank.getDirection();
		
		//tank的坐标
		int tankX = tank.getX();
		int tankY = tank.getY();
		
		//获取tank的宽度和高度
		int tankWidth = Configs.ELEMENT_WIDTH;
		int tankHeight = Configs.ELEMENT_HEIGHT;
		
		//获取子弹的宽度和高度
		int[] cor= {};
		try {
			cor = DrawUtils.getSize("res/img/bullet_u.gif");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		int bulletWidth = cor[0];
		int bulletHeight = cor[1];
		
		//子弹的坐标
		switch (direction) {
		case UP:
			this.x = (int)(tankX + (tankWidth-bulletWidth)/2.0 + 0.5);
			this.y = (int)(tankY-bulletHeight/2.0 + 0.5);
			break;
		case DOWN:
			this.x = (int)(tankX + (tankWidth-bulletWidth)/2.0 + 0.5);
			this.y = (int)(tankY + tankHeight-bulletHeight/2.0 + 0.5);
			break;
		case LEFT:
			this.x = (int)(tankX -bulletWidth/2.0 + 0.5);
			this.y = (int)(tankY + (tankHeight-bulletHeight)/2.0 + 0.5);
			break;
		case RIGHT:
			this.x = (int)(tankX + tankWidth-bulletHeight/2 + 0.5);
			this.y = (int)(tankY + (tankHeight-bulletHeight)/2.0 + 0.5);
		}
	}

	/**
	 * 子弹的坐标与tank的坐标相关
	 * 子弹的方向与tank的方向相关
	 * 所以在子弹的构造函数中需要传入tank的对象
	 */
	
	@Override
	public void draw() {

		for(Element e: MyTankWar.elementList) {
			if (checkAttached(e)) {
				MyTankWar.elementList.remove(this);
			}
		}
		switch (direction) {
		case UP:
			y = y-speed;
			try {
				DrawUtils.draw(Configs.IMG_BULLET_UP, x, y);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			break;
		case DOWN:
			y = y+speed;
			try {
				DrawUtils.draw(Configs.IMG_BULLET_DOWN, x, y);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			break;
		case LEFT:
			x = x-speed;
			try {
				DrawUtils.draw(Configs.IMG_BULLET_LEFT, x, y);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			break;
		case RIGHT:
			x = x + speed;
			try {
				DrawUtils.draw(Configs.IMG_BULLET_RIGHT, x, y);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			break;

		}
	}
	public boolean isDestroyed() {
		if (x<0||y<0||x>Configs.WIDTH||y>Configs.HEIGHT) {
			return true;
		}
		return false;
	}
	
	public boolean checkAttached(Element e) {

		if (!(e instanceof Attachable)) {
			return false;
		}
		
		//被碰撞的元素
		int x1 = e.getX();
		int y1 = e.getY();
		
		int w1 = Configs.ELEMENT_WIDTH;
		int h1 = Configs.ELEMENT_HEIGHT;
		
		//子弹
		int x2 = x;
		int y2 = y;
		
		int w2 = 14;
		int h2 = 14;
		boolean isAttached = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
		if (isAttached) {
			try {
				SoundUtils.play(Configs.MUSIC_HIT);
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			System.out.println("打中了");
			if (e instanceof Wall) {
				Blast blast = ((Wall) e).attached();
				int tempBlood = ((Wall) e).getBlood()-power;
				((Wall) e).setBlood(tempBlood);
				try {
					SoundUtils.play(Configs.MUSIC_BLAST);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				if (((Wall) e).getBlood()<=0) {
					blast = ((Wall) e).broke();
					MyTankWar.elementList.remove(e);
				}
				MyTankWar.elementList.add(blast);
			}
			if (e instanceof Steel) {
				Blast blast = ((Steel) e).attached();
				int tempBlood = ((Steel) e).getBlood()-power;
				((Steel) e).setBlood(tempBlood);
				try {
					SoundUtils.play(Configs.MUSIC_BLAST);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				if (((Steel) e).getBlood()<=0) {
					blast = ((Steel) e).broke();
					MyTankWar.elementList.remove(e);
				}
				MyTankWar.elementList.add(blast);
			}
			
		}
		return isAttached;
	}

}
