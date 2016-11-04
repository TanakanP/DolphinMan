package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bullet {
	private Vector2 position;

	DolphinMan dolphinMan;
	int calculate;

	public Bullet(int x, int y){
		position = new Vector2(x,y);

		
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public int move(int x_B, int bX, int y_B, int bY){
		calculate=(int) Math.sqrt(((x_B-bX)*(x_B-bX))+((y_B-bY)*(y_B-bY)));
		return calculate;
	}
	
}
