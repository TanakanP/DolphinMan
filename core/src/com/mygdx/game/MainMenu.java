package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MainMenu extends ScreenAdapter {
	private DolphinMan dolphinMan;
	Texture img;
	public Sound mainSong;
	int x,y;
	
	public MainMenu (DolphinMan dolphinMan) {
		this.dolphinMan = dolphinMan;
		img = new Texture("DolphinManMenu_2.jpg");
		mainSong = Gdx.audio.newSound(Gdx.files.internal("MainMenu.mp3"));
		mainSong.play();
	}
	
	public void update (){
		if (Gdx.input.justTouched()) {
			x=Gdx.input.getX();
			y=Gdx.input.getY();
			System.out.println(x + " " + y);
			if (x>=1125 && x<=1210){
				if(y>=325 && y<=390){
					mainSong.dispose();
					dolphinMan.setScreen(new Loading(dolphinMan));
					return;
				}
			}
			if (x>=1125 && x<=1212){
				if(y>=425 && y<=490){
					mainSong.dispose();
					dolphinMan.setScreen(new HelpScreen(dolphinMan));
					return;
				}
			}
			if (x>=1127 && x<=1201){
				if(y>=532 && y<=599){
					Gdx.app.exit();
					return;
				}
			}
		}
	}
	
	public void render(float delta){
		SpriteBatch batch = dolphinMan.batch;
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		update();
	}
}