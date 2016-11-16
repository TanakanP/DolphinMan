package com.mygdx.game;



import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class Enemy {
	private Vector2 position;

	Random rand = new Random();

	DolphinMan dolphinMan;

	public Enemy(int x, int y){
		position = new Vector2(x,y);

		
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public int moveX(int x, int mode, int level){
		if(mode==0){
			return x-rand.nextInt(level+4)-1;
		}
		else if(mode==1){
			return x-rand.nextInt(level+2)-1;
		}
		else{
			return x-rand.nextInt(level+7)-1;
		}
	}
	
	public int moveY(int y, int mode, int bounce, int level){
		if(bounce==0){
			if(mode==0){
				return y;
			}
			else if(mode==1){
				if(y-5<0){
					return 0;
				}
				else{
					return y-rand.nextInt(level+7)-1;
				}
			}	
			else{
				if(y-4<0){
					return 0;
				}
				else{
					return y-rand.nextInt(level+2)-1;
				}
			}
		}
		else{
			if(mode==0){
				return y;
			}
			else if(mode==1){
				if(y+5>1080){
					return 1080;
				}
				else{
					return y+rand.nextInt(level+7)+1;
				}
			}	
			else{
				if(y+4>1080){
					return 1080;
				}
				else{
					return y+rand.nextInt(level+2)+1;
				}
			}
		}
	}
	
}
