package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOver extends ScreenAdapter {
	DolphinMan dolphinMan;
	Texture gameOver;
	int x,y;
	
	public GameOver (DolphinMan dolphinMan) {
		this.dolphinMan = dolphinMan;
		gameOver = new Texture("GameOver.png");
	}
	
	public void draw(){
		SpriteBatch batch = dolphinMan.batch;
		batch.begin();
		batch.draw(gameOver,0,0);
		batch.end();
	}
	
	public void update (){
		if (Gdx.input.justTouched()) {
			x=Gdx.input.getX();
			y=Gdx.input.getY();
			System.out.println(x + " " + y);
			if (x>=1365 && x<=1571){
				if(y>=549 && y<=680){
					dolphinMan.setScreen(new Loading(dolphinMan));
					return;
				}
			}
			if (x>=1365 && x<=1511){
				if(y>=716 && y<=846){
					Gdx.app.exit();
					return;
				}
			}
		}
	}
	
	public void render(float delta){
		update();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		draw();
	}
}
