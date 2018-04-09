package com.binge.bean;

public abstract class Element {
	//坐标
	protected int x;
	protected int y;
	
	public int getX() {
		return x;
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

	public abstract void draw() ;
	
	public int getLever() {
		return 0;
	}
}
