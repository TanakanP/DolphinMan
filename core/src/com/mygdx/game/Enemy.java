package com.mygdx.game;



import com.badlogic.gdx.math.Vector2;

public class Enemy {
	private Vector2 position;

	DolphinMan dolphinMan;

	public Enemy(int x, int y){
		position = new Vector2(x,y);

		
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public int moveX(int x, float x_d,int mode){
		if(mode==1){
			if(x>=x_d){
				return x-3;
			}
			else{
				return x+9;
			}
		}
		else if(mode==2){
			if(x>=x_d){
				return x-6;
			}
			else{
				return x+3;
			}
		}
		else if(mode==3){
			if(x>=x_d){
				return x-9;
			}
			else{
				return x+3;
			}
		}
		else{
			if(x>=x_d){
				return x-6;
			}
			else{
				return x+3;
			}
		}
	}
	
	public int moveY(int y, float y_d,int mode){
		if(mode==1){
			if(y>=y_d){
				return y-2;
			}
			else{
				return y+4;
			}
		}
		else if(mode==2){
			if(y>=y_d){
				return y-4;
			}
			else{
				return y+2;
			}
		}
		else if(mode==3){
			if(y>=y_d){
				return y-6;
			}
			else{
				return y+2;
			}
		}
		else{
			if(y>=y_d){
				return y-2;
			}
			else{
				return y+6;
			}
		}
	}
	
}
