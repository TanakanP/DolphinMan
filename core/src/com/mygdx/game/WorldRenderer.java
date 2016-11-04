package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	public Texture dummy;
	public Texture dummyMap;
	private Dolphin dolphin;
	World world;
	DolphinMan dolphinMan;

	public WorldRenderer(DolphinMan dolphinMan, World world) {
		this.dolphinMan = dolphinMan;
		
		this.world = world;
		dolphin = new Dolphin(100,100);
		dummy = new Texture("dummy.png");
		dummyMap = new Texture("dummyMap.jpg");
		
	}

	public void render(float delta){
		SpriteBatch batch = dolphinMan.batch;
		batch.begin();
		Vector2 pos = dolphin.getPosition();
		batch.draw(dummyMap,0,0);
		batch.draw(dummy,pos.x,pos.y);
		batch.end();
	}
}
