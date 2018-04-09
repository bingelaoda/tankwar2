package com.binge.game;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.input.Keyboard;

import com.binge.bean.Bullet;
import com.binge.bean.Direction;
import com.binge.bean.Element;
import com.binge.bean.Grass;
import com.binge.bean.Steel;
import com.binge.bean.Tank;
import com.binge.bean.Wall;
import com.binge.bean.Water;
import com.binge.utils.Configs;
import com.binge.utils.SoundUtils;
import com.binge.utils.Window;

public class MyTankWar extends Window {
	private Tank tank ;
	public static  CopyOnWriteArrayList<Element> elementList = new CopyOnWriteArrayList<>();
	public MyTankWar(String title, int width, int height, int fps) {
		super(title, width, height, fps);
		// TODO 自动生成的构造函数存根
	}
	/**
	 * 游戏窗口创建的时候调用
	 */
	@Override
	protected void onCreate() {
		try {
			SoundUtils.play(Configs.MUSIC_START);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		//创建一个tank对象
		int x = Configs.WIDTH/Configs.ELEMENT_WIDTH/2*Configs.ELEMENT_WIDTH;
		int y = Configs.HEIGHT-Configs.ELEMENT_HEIGHT;
		tank = new Tank(x,y);
		
		//在指定位置加一排墙
		Wall wall;
		for(int i=0;i<Configs.WIDTH/Configs.ELEMENT_WIDTH;i=i+2) {
			wall = new Wall(i*Configs.ELEMENT_WIDTH, Configs.ELEMENT_HEIGHT);
			System.out.println(i);
			addElement(wall);
		}	
		//在指定位置添加一排草
		Grass grass;
		for(int i=0;i<Configs.WIDTH/Configs.ELEMENT_WIDTH;i++) {
			grass = new Grass(i*Configs.ELEMENT_WIDTH, Configs.ELEMENT_HEIGHT*10);
			addElement(grass);
		}
		
		//在指定位置添加一排水
		Water water;
		for(int i=1;i<Configs.WIDTH/Configs.ELEMENT_WIDTH-1;i++) {
			water = new Water(i*Configs.ELEMENT_WIDTH, Configs.ELEMENT_HEIGHT*4);
			addElement(water);
		}
		
		//在指定位置添加一排铁
		Steel steel;
		for(int i=4;i<Configs.WIDTH/Configs.ELEMENT_WIDTH-2;i=i+2) {
			steel = new Steel(i*Configs.ELEMENT_WIDTH, Configs.ELEMENT_HEIGHT*7);
			addElement(steel);
		}
		
		addElement(tank);	

	}
	//添加元素并且对元素进行排序
	private void addElement(Element e) {
		elementList.add(e);
		
		Collections.sort(elementList,new Comparator<Element>() {

			@Override
			public int compare(Element arg0, Element arg1) {
				// TODO 自动生成的方法存根
				return arg0.getLever()-arg1.getLever();
			}
		});
		
	}
	/**
	 * 有鼠标按键行为的时候调用
	 */
	@Override
	protected void onMouseEvent(int key, int x, int y) {
		// TODO 自动生成的方法存根
		
	}
	/**
	 * 有按键盘行为的时候调用
	 */
	@Override
	protected void onKeyEvent(int key) {
		if (key == Keyboard.KEY_W) {
			tank.move(Direction.UP);
		}else if (key == Keyboard.KEY_S) {
			tank.move(Direction.DOWN);
		}else if (key == Keyboard.KEY_A) {
			tank.move(Direction.LEFT);
		}else if (key == Keyboard.KEY_D) {
			tank.move(Direction.RIGHT);
		}else if (key == Keyboard.KEY_SPACE) {
			Bullet bullet = tank.shot();
			if (bullet != null ) {
				addElement(bullet);				
			}
		} 
		
	}
	/**
	 * 屏幕刷新的时候调用
	 */
	@Override
	protected void onDisplayUpdate() {
		for (Element elem : elementList) {
			
			//对元素是否是子弹类型以及是否应该被销毁进行判断
			if (elem instanceof Bullet && ((Bullet) elem).isDestroyed() ) {
				elementList.remove(elem);
			}
				//如果是的话，就销毁	
			elem.draw();
		}
	}

}
