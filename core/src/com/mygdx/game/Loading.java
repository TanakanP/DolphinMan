package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Loading extends ScreenAdapter {
	DolphinMan dolphinMan;
	Texture Loading;
	Texture LoadingText;
	Texture LoadingShark;
	long time_start=System.currentTimeMillis();
	int x_text=142;
	int x_shark=-150;
	
	public Loading (DolphinMan dolphinMan) {
		this.dolphinMan = dolphinMan;
		Loading = new Texture("Loading.jpg");
		LoadingText = new Texture("LoadingText.png");
		LoadingShark = new Texture("LoadingShark.png");
	}
	
	public void draw(){
		SpriteBatch batch = dolphinMan.batch;
		batch.begin();
		batch.draw(Loading,0,0);
		batch.draw(LoadingText,x_text,165);
		batch.draw(LoadingShark,x_shark,0);
		batch.end();
	}
	
	public void update (){
		if(System.currentTimeMillis()-time_start>=1500){
			dolphinMan.setScreen(new GameScreen(dolphinMan));
		}
		x_text=x_text-1;
		x_shark=x_shark+1;
	}
	
	public void render(float delta){
		update();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		draw();
	}
}