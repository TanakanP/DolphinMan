package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class Boss {
	private Vector2 position;

	Random rand = new Random();

	DolphinMan dolphinMan;

	public Boss(int x, int y){
		position = new Vector2(x,y);

		
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	
}
