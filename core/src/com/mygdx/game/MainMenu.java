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
			if (x>=1582 && x<=1704){
				if(y>=435 && y<=549){
					mainSong.dispose();
					dolphinMan.setScreen(new GameScreen(dolphinMan));
					return;
				}
			}
			if (x>=1582 && x<=1714){
				if(y>=594 && y<=690){
					dolphinMan.setScreen(new HelpScreen(dolphinMan));
					return;
				}
			}
			if (x>=1582 && x<=1696){
				if(y>=750 && y<=844){
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