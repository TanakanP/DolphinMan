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
	Rectangle playButton;
	Rectangle helpButton;
	Rectangle exitButton;
	int x,y;
	
	public MainMenu (DolphinMan dolphinMan) {
		this.dolphinMan = dolphinMan;
		img = new Texture("DolphinManMenu.png");
		mainSong = Gdx.audio.newSound(Gdx.files.internal("MainMenu.mp3"));
		mainSong.play();
		playButton = new Rectangle(796,409,61,47);
		helpButton = new Rectangle(796,480,66,48);
		exitButton = new Rectangle(796,556,58,47);
	}
	
	public void update (){
		if (Gdx.input.justTouched()) {
			x=Gdx.input.getX();
			y=Gdx.input.getY();
			System.out.println(x + " " + y);
			if (x>=796 && x<=857){
				if(y>=409 && y<=456){
				mainSong.dispose();
				dolphinMan.setScreen(new GameScreen(dolphinMan));
				return;
				}
			}
			if (x>=796 && x<=862){
				if(y>=480 && y<=528){
				dolphinMan.setScreen(new HelpScreen(dolphinMan));
				return;
				}
			}
			if (x>=796 && x<=853){
				if(y>=556 && y<=603){
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