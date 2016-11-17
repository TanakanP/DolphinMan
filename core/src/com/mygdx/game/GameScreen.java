package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.World;
import com.mygdx.game.WorldRenderer;


public class GameScreen extends ScreenAdapter{
	
	DolphinMan dolphinMan;
	World world;
	WorldRenderer worldRenderer;
	
	
	public GameScreen (DolphinMan dolphinMan) {
		this.dolphinMan = dolphinMan;
		
		world = new World(dolphinMan);
		worldRenderer = new WorldRenderer(dolphinMan, world, world.dolphin);
	
		
		
	}
	
	
	
	
	
	public void render(float delta){
		world.update(delta);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		worldRenderer.draw();
	}
}
