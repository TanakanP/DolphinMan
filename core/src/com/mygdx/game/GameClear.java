package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameClear extends ScreenAdapter {
	DolphinMan dolphinMan;
	Texture gameClear;
	int x,y;
	
	public GameClear (DolphinMan dolphinMan) {
		this.dolphinMan = dolphinMan;
		gameClear = new Texture("GameClear.jpg");
	}
	
	public void draw(){
		SpriteBatch batch = dolphinMan.batch;
		batch.begin();
		batch.draw(gameClear,0,0);
		batch.end();
	}
	
	public void update (){
		if (Gdx.input.justTouched()) {
			x=Gdx.input.getX();
			y=Gdx.input.getY();
			System.out.println(x + " " + y);
			if (x>=972 && x<=1086){
				if(y>=394 && y<=484){
					dolphinMan.setScreen(new MainMenu(dolphinMan));
					return;
				}
			}
			if (x>=973 && x<=1071){
				if(y>=511 && y<=596){
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