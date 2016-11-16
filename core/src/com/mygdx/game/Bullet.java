package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bullet {
	private Vector2 position;

	DolphinMan dolphinMan;
	float calculate;

	public Bullet(float x, float y){
		position = new Vector2(x,y);

		
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public float move(float x_B, float bX, float y_B, float bY){
		calculate=(float) Math.sqrt(((x_B-bX)*(x_B-bX))+((y_B-bY)*(y_B-bY)));
		return calculate;
	}
	
}
