package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOver extends ScreenAdapter {
	DolphinMan dolphinMan;
	BitmapFont font = new BitmapFont();
	Texture black;
	
	public GameOver (DolphinMan dolphinMan) {
		this.dolphinMan = dolphinMan;
		black = new Texture("black.jpg");
	}
	
	public void draw(){
		SpriteBatch batch = dolphinMan.batch;
		batch.begin();
		batch.draw(black,0,0);
		font.draw(batch,"GAME OVER", 512, 324);
		batch.end();
	}
	
	public void render(float delta){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		draw();
	}
}
