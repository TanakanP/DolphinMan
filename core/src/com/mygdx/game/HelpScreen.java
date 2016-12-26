package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelpScreen extends ScreenAdapter {
	DolphinMan dolphinMan;
	Texture HelpScreen;
	int x,y;
	
	public HelpScreen (DolphinMan dolphinMan) {
		this.dolphinMan = dolphinMan;
		HelpScreen = new Texture("HelpScreen.jpg");
	}
	
	public void draw(){
		SpriteBatch batch = dolphinMan.batch;
		batch.begin();
		batch.draw(HelpScreen,0,0);
		batch.end();
	}
	
	public void update (){
		if (Gdx.input.justTouched()) {
			x=Gdx.input.getX();
			y=Gdx.input.getY();
			System.out.println(x + " " + y);
			if (x>=1090 && x<=1200){
				if(y>=572 && y<=661){
					dolphinMan.setScreen(new MainMenu(dolphinMan));
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