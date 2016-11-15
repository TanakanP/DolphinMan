package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOver extends ScreenAdapter {
	DolphinMan dolphinMan;
	BitmapFont font = new BitmapFont();
	Texture black;
	
	public GameOver (DolphinMan DolphinMan) {
		this.dolphinMan = dolphinMan;
		black = new Texture("black.jpg");
	}
	
	public void render(float delta){
		SpriteBatch batch = dolphinMan.batch;
		batch.begin();
		batch.draw(black,0,0);
		font.draw(batch,"GAME OVER", 512, 324);
		batch.end();
	}
}
