package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class WorldRenderer {

	World world;
	DolphinMan dolphinMan;
	Dolphin dolphin;
	BitmapFont font = new BitmapFont();

	public WorldRenderer(DolphinMan dolphinMan, World world, Dolphin dolphin) {
		this.dolphinMan = dolphinMan;
		
		this.world = world;
		
		this.dolphin = dolphin;
		
	}
	
	public void draw(){
		SpriteBatch batch = dolphinMan.batch;
		batch.begin();
		Vector2 pos = dolphin.getPosition();
		batch.draw(world.dummyMap,0,0);		
		batch.draw(world.dolphinRight,pos.x,pos.y);
		font.draw(batch, "Mission: Eliminate 500 shark and Kill King Shark", 10, 763);
		font.draw(batch, "Shark Eliminated: "+world.score, 10, 748);
		for(int i=0;i<world.multi;i++){
			if(world.alive[i]!=0){
				batch.draw(world.shark,world.x_E[i],world.y_E[i]);
			}
		}
		for(int i=0;i<1000;i++){
			if(world.bulletR[i]!=0){
				batch.draw(world.bulletDolphin,world.x_B[i],world.y_B[i]);
			}
		}
		if(world.bossSpawn==1){
			for(int i=0;i<10000;i++){
				if(world.bulletBossS[i]!=0){
					batch.draw(world.bossBulletImg,world.x_B_boss[i],world.y_B_boss[i]);
				}
			}
		}
		for(int i=0;i<10000;i++){
			if(world.bulletSpecialS1[i]!=0){
				batch.draw(world.bulletDolphin,world.x_Bsp1[i],world.y_Bsp1[i]);
			}
			if(world.bulletSpecialS2[i]!=0){
				batch.draw(world.bulletDolphin,world.x_Bsp2[i],world.y_Bsp2[i]);
			}
			if(world.bulletSpecialS3[i]!=0){
				batch.draw(world.bulletDolphin,world.x_Bsp3[i],world.y_Bsp3[i]);
			}
			if(world.bulletSpecialS4[i]!=0){
				batch.draw(world.bulletDolphin,world.x_Bsp4[i],world.y_Bsp4[i]);
			}
		}
		batch.draw(world.HPborder,0,0);
		for(int i=0;i<world.hp;i++){
			batch.draw(world.HPbar,3+(i*30),3);
		}
		font.draw(batch, "HP: "+world.hp+"/10", 10, 52);
		batch.draw(world.EXPborder,326,0);
		if(world.explvl==1){
			for(int i=0;i<world.exp;i++){
				batch.draw(world.EXPbar1,329+(i*10),3);
			}
		}
		else if(world.explvl==2){
			for(int i=0;i<world.exp;i++){
				batch.draw(world.EXPbar2,329+(i*6),3);
			}
		}
		else if(world.explvl>=3){
			for(int i=0;i<world.exp;i++){
				batch.draw(world.EXPbar3,329+(i*3),3);
			}
		}
		if(world.explvl<4){
			font.draw(batch, "EXP-LEVEL: "+world.explvl, 336, 52);
		}
		else{
			font.draw(batch, "EXP-LEVEL: MAX", 336, 52);
		}
		if(world.bossSpawn==1){
			if(world.BossX>=711){
				world.BossX-=15;
			}
			batch.draw(world.bossImg,world.BossX,0);
			batch.draw(world.HPBossborder,1328,0);
			for(int i=0;i<world.BossHP;i++){
				batch.draw(world.HPBossbar,1332,4+(i));
			}
		}
		batch.end();
	}
}
