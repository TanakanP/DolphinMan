package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bullet {
	private Vector2 position;

	DolphinMan dolphinMan;

	public Bullet(float x, float y){
		position = new Vector2(x,y);

		
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public float moveX(float x, int mode){
		if(mode==1){
			return x+10;
		}
		else{
			return x-20;
		}
	}
	
	public float moveY(float y, int mode){
	    if(mode==3){
			return y-5;
		}
		else{
			return y+5;
		}
	}
}
