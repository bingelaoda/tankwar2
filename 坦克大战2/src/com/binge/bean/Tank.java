package com.binge.bean;

import java.io.IOException;

import com.binge.game.MyTankWar;
import com.binge.utils.CollsionUtils;
import com.binge.utils.Configs;
import com.binge.utils.DrawUtils;

public class Tank extends Element{

	// 属性
	// tank的坐标
	private int speed=Configs.TANK_SPEED; 
	private Direction direction = Direction.UP;
	/**
	 * 撞上的方向
	 */
	Direction baDirection;
	/**
	 * 
	 * 创建时间戳，表示上一次发射子弹的时间
	 */
	long lastShotTime = 0;
	


	public Direction getDirection() {
		return direction;
	}

	public int getX() {
		return x;
	}

	public Tank(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	// 行为
	/**
	 * 此方法用于画出tank本身
	 */
	public void draw() {
		try {
			switch (direction) {
			case UP:
				DrawUtils.draw(Configs.IMG_TANK_UP, x, y);
				break;
			case DOWN:
				DrawUtils.draw(Configs.IMG_TANK_DOWN, x, y);
				break;
			case LEFT:
				DrawUtils.draw(Configs.IMG_TANK_LEFT, x, y);
				break;
			case RIGHT:
				DrawUtils.draw(Configs.IMG_TANK_RIGHT, x, y);
				break;
			}
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * 此方法用于tank的移动
	 */
	public void move(Direction direction) {
		
		for (Element elem : MyTankWar.elementList) {
			boolean boo = isHit(elem);
			if (boo && direction==baDirection) {
				
				return;
			}
			
		}
		
		if (this.direction != direction) {
			this.direction = direction;
			return ;
		}
		
		switch (direction) {
		case UP:
			y = y - speed;
			break;
		case DOWN:
			y = y + speed;
			break;
		case LEFT:
			x = x - speed	;
			break;
		case RIGHT:
			x =	x + speed;	
			break;

		}
		
		//对坐标是否越界进行判断并处理
		checkCoordinate();
	}
	/**
	 * 判断坐标是否越界并且进行处理
	 */
	private void checkCoordinate() {
		if (x < 0) {
			x = 0;
		}
		if(x > Configs.WIDTH-Configs.ELEMENT_WIDTH) {
			x = Configs.WIDTH-Configs.ELEMENT_WIDTH;
		}
		if (y < 0) {
			y = 0;
		}	
		if (y > Configs.HEIGHT - Configs.ELEMENT_HEIGHT) {
			y = Configs.HEIGHT - Configs.ELEMENT_HEIGHT;
		}
	}
	
	/**
	 * tank发射子弹的方法
	 */
	public Bullet shot() {
		long currentTimeMillis = System.currentTimeMillis();
		if (currentTimeMillis-lastShotTime>500) {
			lastShotTime = currentTimeMillis;
			Bullet bullet = new Bullet(this);
			return bullet;
		}
		return null;
	}
	
	public boolean isHit(Element elem) {
		
		if (!(elem instanceof Blockable)) {
			return false;
		}
		//被检测碰撞的坐标宽高
		int x1 = elem.getX();
		int y1 = elem.getY();
		int w1 = Configs.ELEMENT_WIDTH;
		int h1 = Configs.ELEMENT_HEIGHT;
		
		//坦克的坐标与宽高
		int x2 = x;
		int y2 = y;
		int w2 = Configs.ELEMENT_WIDTH;
		int h2 = Configs.ELEMENT_HEIGHT;
		
		//预判是否发生碰撞
		switch (direction) {	
		case UP:
			y2 = y2 - speed;
			break;
		case DOWN:
			y2 = y2 + speed;
			break;
		case LEFT:
			x2 = x2 - speed;
			break;
		case RIGHT:
			x2 = x2 + speed;
			break;
		}
		
		boolean boo = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
		if (boo) {
			baDirection = direction;
			switch (direction) {
			
			case UP:
				y = y1+Configs.ELEMENT_HEIGHT;
				break;
			case DOWN:
				y = y1-Configs.ELEMENT_HEIGHT;
				break;
			case LEFT:
				x = x1+Configs.ELEMENT_WIDTH;
				break;
			case RIGHT:
				x = x1-Configs.ELEMENT_WIDTH;
				break;
			}
		}
		
		return boo;
	}

}
