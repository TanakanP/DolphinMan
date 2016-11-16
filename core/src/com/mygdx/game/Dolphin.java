package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Dolphin {
	private Vector2 position;
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_DOWN = 3;
	public static final int DIRECTION_LEFT = 4;
	public static final int DIRECTION_STILL = 0;
	private static final int [][]DIR_OFFSETS = new int[][]{
		{0,0},
		{0,1},
		{1,0},
		{0,-1},
		{-1,0}
	};
	public static final int SPEED = 5;
	
	public Dolphin(int x,int y){
		position = new Vector2(x,y);
		
	}

	public Vector2 getPosition(){
		return position;
	}
	
	public void move(int dir){
		position.x+=SPEED*DIR_OFFSETS[dir][0];
		position.y+=SPEED*DIR_OFFSETS[dir][1];
		if(position.x>1850){position.x=1850;}
		if(position.x<0){position.x=0;}
		if(position.y>1010){position.y=1010;}
		if(position.y<0){position.y=0;}
	}
}
