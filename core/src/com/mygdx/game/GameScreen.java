package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.World;
import com.mygdx.game.WorldRenderer;


public class GameScreen extends ScreenAdapter{
	private Dolphin dolphin;
	DolphinMan dolphinMan;
	World world;
	WorldRenderer worldRenderer;
	Texture dummyMap;
	Texture dolphinRight;
	Texture dolphinLeft;
	Texture shark;
	Texture bulletDolphin;
	Texture HPborder;
	Texture HPbar;
	Texture HPBossborder;
	Texture HPBossbar;
	Texture EXPborder;
	Texture EXPbar1;
	Texture EXPbar2;
	Texture EXPbar3;
	Texture bossImg;
	Texture bossBulletImg;
	Enemy[] enemy;
	Bullet[] bullet;
	Bullet[] bulletSpecial1;
	Bullet[] bulletSpecial2;
	Bullet[] bulletSpecial3;
	Bullet[] bulletSpecial4;
	Bullet[] bossBullet;
 	Boss boss;
	int multi=2;
	int start;
	int score;
	int monsterMode;
	int level;
	int hp=10;
	int bossSpawn=0;
	int BossX=1920;
	int BossHP=1076;
	int exp=0;
	int explvl=1;
	int bosslvl=50;
	int onlyOneBoss=0;
	int[] x_E = new int[100000];
	int[] y_E = new int[100000];
	long timer_start;
	float[] x_B = new float[100000];
	float[] y_B = new float[100000];
	float[] x_Bsp1 = new float[10000];
	float[] y_Bsp1 = new float[10000];
	float[] x_Bsp2 = new float[10000];
	float[] y_Bsp2 = new float[10000];
	float[] x_Bsp3 = new float[10000];
	float[] y_Bsp3 = new float[10000];
	float[] x_Bsp4 = new float[10000];
	float[] y_Bsp4 = new float[10000];
	float[] x_B_boss = new float[10000];
	float[] y_B_boss = new float[10000];
	int[] alive = new int[100000];
	int[] bulletR = new int[10000];
	int[] bulletSpecialS1 = new int[10000];
	int[] bulletSpecialS2 = new int[10000];
	int[] bulletSpecialS3 = new int[10000];
	int[] bulletSpecialS4 = new int[10000];
	int[] bulletBossS = new int[10000];
	int[] bounce = new int[100000];
	BitmapFont font = new BitmapFont();
	Random rand = new Random();
	public Sound mainTheme;
	
	
	public GameScreen (DolphinMan dolphinMan) {
		this.dolphinMan = dolphinMan;
		
		world = new World(dolphinMan);
		worldRenderer = new WorldRenderer(dolphinMan, world);
			
		dolphin = new Dolphin(100,100);
		enemy = new Enemy[100000];
		bullet = new Bullet[1000];
		bossBullet = new Bullet[10000];
		bulletSpecial1 = new Bullet[10000];
		bulletSpecial2 = new Bullet[10000];
		bulletSpecial3 = new Bullet[10000];
		bulletSpecial4 = new Bullet[10000];
		dolphinRight = new Texture("dolphinRight.png");
		dolphinLeft = new Texture("dolphinLeft.png");
		shark = new Texture("Shark.png");
		bulletDolphin = new Texture("bulletDolphin.png");
		dummyMap = new Texture("MapSea.jpg");
		HPborder = new Texture("HPborder.jpg");
		HPbar = new Texture("HPbar.jpg");
		bossImg = new Texture("SharkBoss.png");
		HPBossborder = new Texture("HPBossborder.jpg");
		HPBossbar = new Texture("HPBossbar.jpg");
		EXPborder = new Texture("EXPborder.jpg");
		EXPbar1 = new Texture("EXPbar1.jpg");
		EXPbar2 = new Texture("EXPbar2.jpg");
		EXPbar3 = new Texture("EXPbar3.jpg");
		bossBulletImg = new Texture("bossBulletImg.png");
		timer_start = System.currentTimeMillis();
		mainTheme = Gdx.audio.newSound(Gdx.files.internal("MainTheme.mp3"));
		mainTheme.play();

		System.out.println("You enter Game Screen");
		
	
		
		
	}
	
	public void update(float delta){
		dolphinMove();
		monsterAction();
		bulletAction();
		bossAction();
		checkStatus();
	}
	
	public void monsterAction(){
		spawnEnemy();
		monsterMove();
	}
	
	public void bulletAction(){
		spawnBullet(explvl);
		bulletMove();
		bulletHitBoss();
		bulletHit();
	}
	
	public void checkStatus(){
		checkDolphinDead();
		checkMonsterDeadAll();
		checkEndgame();
		checkBoss();
		checkEXP();
		checkDolphinBoss();
	}
	
	public void bossAction(){
		spawnBoss();
		if(bossSpawn==1){
			bossAttackSpawn();
			bossAttackMove();
		}
	}
	
	public void dolphinMove(){
		if(Gdx.input.isKeyPressed(Keys.W)){
			dolphin.move(Dolphin.DIRECTION_UP);
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			dolphin.move(Dolphin.DIRECTION_DOWN);
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
			dolphin.move(Dolphin.DIRECTION_LEFT);
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			dolphin.move(Dolphin.DIRECTION_RIGHT);
		}
	}
	
	public void spawnEnemy(){
		if(start==0){
			if(multi<=38){
				multi=multi+2;
			}
			for(int i=0;i<multi;i++){
				x_E[i]=1920;
				y_E[i]= rand.nextInt(1080) + 1;
				enemy[i] = new Enemy(x_E[i],y_E[i]);
				alive[i] = 1;
			}
			start=1;
		}
	}
	
	public void monsterMove(){
		for(int i=0;i<multi;i++){
			if(alive[i]==1){
				if(multi<=10){
					level=5;
				}
				else if(multi>10 && multi<=20){
					level=9;
				}
				else{
					level=13;
				}
				x_E[i]=enemy[i].moveX(x_E[i],monsterMode,level);
				if(y_E[i]==0){
					bounce[i]=1;
				}
				if(y_E[i]==1080){
					bounce[i]=0;
				}
				y_E[i]=enemy[i].moveY(y_E[i],monsterMode,bounce[i],level);
				if(x_E[i]<=0){
					alive[i]=0;
				}
			}
		}
	}
	
	public void spawnBullet(int lvl){
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			Vector2 pos = dolphin.getPosition();
			for(int i=0;i<10000;i++){
				if(bulletR[i]==0){
					bulletR[i]=1;
					x_B[i]=(int) pos.x+15;
					y_B[i]=(int) pos.y+15;
					bullet[i] = new Bullet(x_B[i],y_B[i]);
					break;
				}
			}
			if(lvl>=2){
				for(int i=0;i<10000;i++){
					if(bulletSpecialS1[i]==0){
						bulletSpecialS1[i]=1;
						bulletSpecial1[i] = new Bullet(0,0);
						x_Bsp1[i]=pos.x+15;
						y_Bsp1[i]=pos.y+60;
						break;
					}
				}
			}
			if(lvl>=3){
				for(int i=0;i<10000;i++){
					if(bulletSpecialS2[i]==0){
						bulletSpecialS2[i]=1;
						bulletSpecial2[i] = new Bullet(0,0);
						x_Bsp2[i]=pos.x+15;
						y_Bsp2[i]=pos.y-30;
						break;
					}
				}
			}
			if(lvl>=4){
				for(int i=0;i<10000;i++){
					if(bulletSpecialS3[i]==0){
						bulletSpecialS3[i]=1;
						bulletSpecial3[i] = new Bullet(0,0);
						x_Bsp3[i]=pos.x+15;
						y_Bsp3[i]=pos.y+15;
						break;
					}
				}
				for(int i=0;i<10000;i++){
					if(bulletSpecialS4[i]==0){
						bulletSpecialS4[i]=1;
						bulletSpecial4[i] = new Bullet(0,0);
						x_Bsp4[i]=pos.x+15;
						y_Bsp4[i]=pos.y+15;
						break;
					}
				}
			}
		}
		
	}
	
	public void bulletMove(){
		for(int i=0;i<10000;i++){
			if(bulletR[i]!=0){
				x_B[i]=bullet[i].moveX(x_B[i],1);
			}
			if(bulletSpecialS1[i]!=0){
				x_Bsp1[i]=bulletSpecial1[i].moveX(x_Bsp1[i],1);
			}
			if(bulletSpecialS2[i]!=0){
				x_Bsp2[i]=bulletSpecial2[i].moveX(x_Bsp2[i],1);
			}
			if(bulletSpecialS3[i]!=0){
				x_Bsp3[i]=bulletSpecial3[i].moveX(x_Bsp3[i],1);
				y_Bsp3[i]=bulletSpecial3[i].moveY(y_Bsp3[i],3);
			}
			if(bulletSpecialS4[i]!=0){
				x_Bsp4[i]=bulletSpecial4[i].moveX(x_Bsp4[i],1);
				y_Bsp4[i]=bulletSpecial4[i].moveY(y_Bsp4[i],4);
			}
			if(x_B[i]>=1920){
				bulletR[i]=0;
			}
			if(x_Bsp1[i]>=1920){
				bulletSpecialS1[i]=0;
			}
			if(x_Bsp2[i]>=1920){
				bulletSpecialS2[i]=0;
			}
			if(x_Bsp3[i]>=1920){
				bulletSpecialS3[i]=0;
			}
			if(x_Bsp4[i]>=1920){
				bulletSpecialS4[i]=0;
			}
		}
	}
	
	public void bulletHit(){
		for(int i=0;i<10000;i++){
			if(bulletR[i]!=0){
				for(int j=0;j<multi;j++){
					if(alive[j]!=0){
						if(x_B[i]+13>=x_E[j]+20 && x_B[i]+13<=x_E[j]+65){
							if(y_B[i]+13>=y_E[j]+3 && y_B[i]+13<=y_E[j]+56){
								alive[j]=0;
								bulletR[i]=0;
								score++;
								exp++;
								break;
							}
						}
					}
				}
			}
			if(bulletSpecialS1[i]!=0){
				for(int j=0;j<multi;j++){
					if(alive[j]!=0){
						if(x_Bsp1[i]+13>=x_E[j]+20 && x_Bsp1[i]+13<=x_E[j]+65){
							if(y_Bsp1[i]+13>=y_E[j]+3 && y_Bsp1[i]+13<=y_E[j]+56){
								alive[j]=0;
								bulletSpecialS1[i]=0;
								score++;
								exp++;
								break;
							}
						}
					}
				}
			}
			if(bulletSpecialS2[i]!=0){
				for(int j=0;j<multi;j++){
					if(alive[j]!=0){
						if(x_Bsp2[i]+13>=x_E[j]+20 && x_Bsp2[i]+13<=x_E[j]+65){
							if(y_Bsp2[i]+13>=y_E[j]+3 && y_Bsp2[i]+13<=y_E[j]+56){
								alive[j]=0;
								bulletSpecialS2[i]=0;
								score++;
								exp++;
								break;
							}
						}
					}
				}
			}
			if(bulletSpecialS3[i]!=0){
				for(int j=0;j<multi;j++){
					if(alive[j]!=0){
						if(x_Bsp3[i]+13>=x_E[j]+20 && x_Bsp3[i]+13<=x_E[j]+65){
							if(y_Bsp3[i]+13>=y_E[j]+3 && y_Bsp3[i]+13<=y_E[j]+56){
								alive[j]=0;
								bulletSpecialS3[i]=0;
								score++;
								exp++;
								break;
							}
						}
					}
				}
			}
			if(bulletSpecialS4[i]!=0){
				for(int j=0;j<multi;j++){
					if(alive[j]!=0){
						if(x_Bsp4[i]+13>=x_E[j]+20 && x_Bsp4[i]+13<=x_E[j]+65){
							if(y_Bsp4[i]+13>=y_E[j]+3 && y_Bsp4[i]+13<=y_E[j]+56){
								alive[j]=0;
								bulletSpecialS4[i]=0;
								score++;
								exp++;
								break;
							}
						}
					}
				}
			}
		}
	}
	
	public void bulletHitBoss(){
		for(int i=0;i<1000;i++){
			if(bulletR[i]!=0){
				if(y_B[i]+13>=862 && x_B[i]+13>=BossX+176){
					bulletR[i]=0;
					BossHP--;
				}
				if(y_B[i]+13>=546 && y_B[i]+13<862){
					if(x_B[i]+13>=BossX){
						bulletR[i]=0;
						BossHP--;
					}
				}
				if(y_B[i]+13>=344 && y_B[i]+13<546){
					if(x_B[i]+13>=BossX+255){
						bulletR[i]=0;
						BossHP--;
					}
				}
				if(y_B[i]+13<344 && x_B[i]+13>=BossX+600){
					bulletR[i]=0;
					BossHP--;
				}
			}	
			if(bulletSpecialS1[i]!=0){
				if(y_Bsp1[i]+13>=862 && x_Bsp1[i]+13>=BossX+176){
					bulletSpecialS1[i]=0;
					BossHP--;
				}
				if(y_Bsp1[i]+13>=546 && y_Bsp1[i]+13<862){
					if(x_Bsp1[i]+13>=BossX){
						bulletSpecialS1[i]=0;
						BossHP--;
					}
				}
				if(y_Bsp1[i]+13>=344 && y_Bsp1[i]+13<546){
					if(x_Bsp1[i]+13>=BossX+255){
						bulletSpecialS1[i]=0;
						BossHP--;
					}
				}
				if(y_Bsp1[i]+13<344 && x_Bsp1[i]+13>=BossX+600){
					bulletSpecialS1[i]=0;
					BossHP--;
				}
			}
			if(bulletSpecialS2[i]!=0){
				if(y_Bsp2[i]+13>=862 && x_Bsp2[i]+13>=BossX+176){
					bulletSpecialS2[i]=0;
					BossHP--;
				}
				if(y_Bsp2[i]+13>=546 && y_Bsp2[i]+13<862){
					if(x_Bsp2[i]+13>=BossX){
						bulletSpecialS2[i]=0;
						BossHP--;
					}
				}
				if(y_Bsp2[i]+13>=344 && y_Bsp2[i]+13<546){
					if(x_Bsp2[i]+13>=BossX+255){
						bulletSpecialS2[i]=0;
						BossHP--;
					}
				}
				if(y_Bsp2[i]+13<344 && x_Bsp2[i]+13>=BossX+600){
					bulletSpecialS2[i]=0;
					BossHP--;
				}
			}
			if(bulletSpecialS3[i]!=0){
				if(y_Bsp3[i]+13>=862 && x_Bsp3[i]+13>=BossX+176){
					bulletSpecialS3[i]=0;
					BossHP--;
				}
				if(y_Bsp3[i]+13>=546 && y_Bsp3[i]+13<862){
					if(x_Bsp3[i]+13>=BossX){
						bulletSpecialS3[i]=0;
						BossHP--;
					}
				}
				if(y_Bsp3[i]+13>=344 && y_Bsp3[i]+13<546){
					if(x_Bsp3[i]+13>=BossX+255){
						bulletSpecialS3[i]=0;
						BossHP--;
					}
				}
				if(y_Bsp3[i]+13<344 && x_Bsp3[i]+13>=BossX+600){
					bulletSpecialS3[i]=0;
					BossHP--;
				}
			}
			if(bulletSpecialS4[i]!=0){
				if(y_Bsp4[i]+13>=862 && x_Bsp4[i]+13>=BossX+176){
					bulletSpecialS4[i]=0;
					BossHP--;
				}
				if(y_Bsp4[i]+13>=546 && y_Bsp4[i]+13<862){
					if(x_Bsp4[i]+13>=BossX){
						bulletSpecialS4[i]=0;
						BossHP--;
					}
				}
				if(y_Bsp4[i]+13>=344 && y_Bsp4[i]+13<546){
					if(x_Bsp4[i]+13>=BossX+255){
						bulletSpecialS4[i]=0;
						BossHP--;
					}
				}
				if(y_Bsp4[i]+13<344 && x_Bsp4[i]+13>=BossX+600){
					bulletSpecialS4[i]=0;
					BossHP--;
				}
			}
		}
	}
	
	public void checkDolphinDead(){
		for(int i=0;i<multi;i++){
			Vector2 pos = dolphin.getPosition();
			if(pos.x+25>=x_E[i]+20 && pos.x+25<=x_E[i]+55){
				if(pos.y+25>=y_E[i]+18 && pos.y+25<=y_E[i]+52){
					alive[i]=0;
					y_E[i]=0;
					x_E[i]=0;
					hp--;
				}
			}
		}
		for(int i=0;i<10000;i++){
			Vector2 pos = dolphin.getPosition();
			if(bulletBossS[i]!=0){
				if(x_B_boss[i]+8<=pos.x+50 && x_B_boss[i]+8>=pos.x+5){
					if(y_B_boss[i]+10>=pos.y+16 && y_B_boss[i]+10<=pos.y+57){
						bulletBossS[i]=0;
						hp--;
						break;
					}
				}					
			}
		}
	}
	
	public void checkDolphinBoss(){
		Vector2 pos = dolphin.getPosition();
		if(pos.y+47>=862 && pos.x+50>=BossX+176){
			hp=0;
		}
		if(pos.y+47>=546 && pos.y+47<862){
			if(pos.x+50>=BossX){
				hp=0;
			}
		}
		if(pos.y+47>=344 && pos.y+47<546){
			if(pos.x+50>=BossX+255){
				hp=0;
			}
		}
		if(pos.y+47<344 && pos.x+50>=BossX+600){
			hp=0;
		}
	}
	
	public void checkMonsterDeadAll(){
		for(int i=0;i<multi;i++){
			if(alive[i]==1){break;}
			if(i==multi-1){
				start=0;
				monsterMode++;
				if(monsterMode==3){
					monsterMode=0;
				}
				System.out.println("Spawn");
			}
		}
	}
	
	public void checkEndgame(){
		if(hp==0){
			mainTheme.dispose();
			dolphinMan.setScreen(new GameOver(dolphinMan));
		}
		if(BossHP<=0){
			mainTheme.dispose();
			dolphinMan.setScreen(new GameClear(dolphinMan));
		}
	}
	
	public void checkBoss(){
		if(score>=500){
			bossSpawn=1;
		}
	}
	
	public void spawnBoss(){
		if(bossSpawn!=0 && onlyOneBoss!=1){
			boss = new Boss(BossX,0);
			for(int i=0;i<10000;i++){
				bulletR[i]=0;
				bulletSpecialS1[i]=0;
				bulletSpecialS2[i]=0;
				bulletSpecialS3[i]=0;
				bulletSpecialS4[i]=0;
			}
			onlyOneBoss=1;
		}
	}
	
	public void bossAttackSpawn(){
		if(BossHP<500 && BossHP>=150){
			bosslvl=100;
		}
		if(BossHP<150){
			bosslvl=150;
		}
		if((System.currentTimeMillis()-timer_start)%500>=0 && (System.currentTimeMillis()-timer_start)%500<=bosslvl){
			for(int i=0;i<10000;i++){
				if(bulletBossS[i]==0){
					bulletBossS[i]=1;
					x_B_boss[i]=1920;
					y_B_boss[i]=rand.nextInt(1080);
					bossBullet[i] = new Bullet(x_B_boss[i],y_B_boss[i]);
					break;
				}
			}
		}
	}
	
	public void bossAttackMove(){
		for(int i=0;i<10000;i++){
			if(bulletBossS[i]!=0){
				x_B_boss[i]=bossBullet[i].moveX(x_B_boss[i],0);
				if(x_B_boss[i]<=0){
					bulletBossS[i]=0;
				}
			}
		}
	}
	
	public void checkEXP(){
		if(explvl==1){
			if(exp>=30){
				exp=0;
				explvl=2;
			}
		}
		else if(explvl==2){
			if(exp>=50){
				exp=0;
				explvl=3;
			}
		}
		else if(explvl>=3){
			if(exp>=100){
				exp=100;
				explvl=4;
			}
		}
		else{
		}
	}
	
	public void draw(){
		SpriteBatch batch = dolphinMan.batch;
		batch.begin();
		Vector2 pos = dolphin.getPosition();
		batch.draw(dummyMap,0,0);
		font.draw(batch, "Shark Eliminated: "+score, 10, 1060);
		for(int i=0;i<multi;i++){
			if(alive[i]!=0){
				batch.draw(shark,x_E[i],y_E[i]);
			}
		}
		for(int i=0;i<1000;i++){
			if(bulletR[i]!=0){
				batch.draw(bulletDolphin,x_B[i],y_B[i]);
			}
		}
		if(bossSpawn==1){
			for(int i=0;i<10000;i++){
				if(bulletBossS[i]!=0){
					batch.draw(bossBulletImg,x_B_boss[i],y_B_boss[i]);
				}
			}
		}
		for(int i=0;i<10000;i++){
			if(bulletSpecialS1[i]!=0){
				batch.draw(bulletDolphin,x_Bsp1[i],y_Bsp1[i]);
			}
			if(bulletSpecialS2[i]!=0){
				batch.draw(bulletDolphin,x_Bsp2[i],y_Bsp2[i]);
			}
			if(bulletSpecialS3[i]!=0){
				batch.draw(bulletDolphin,x_Bsp3[i],y_Bsp3[i]);
			}
			if(bulletSpecialS4[i]!=0){
				batch.draw(bulletDolphin,x_Bsp4[i],y_Bsp4[i]);
			}
		}
		batch.draw(dolphinRight,pos.x,pos.y);
		batch.draw(HPborder,0,0);
		for(int i=0;i<hp;i++){
			batch.draw(HPbar,3+(i*30),3);
		}
		font.draw(batch, "HP: "+hp+"/10", 10, 52);
		batch.draw(EXPborder,326,0);
		if(explvl==1){
			for(int i=0;i<exp;i++){
				batch.draw(EXPbar1,329+(i*10),3);
			}
		}
		else if(explvl==2){
			for(int i=0;i<exp;i++){
				batch.draw(EXPbar2,329+(i*6),3);
			}
		}
		else if(explvl>=3){
			for(int i=0;i<exp;i++){
				batch.draw(EXPbar3,329+(i*3),3);
			}
		}
		if(explvl<4){
			font.draw(batch, "EXP-LEVEL: "+explvl, 336, 52);
		}
		else{
			font.draw(batch, "EXP-LEVEL: MAX", 336, 52);
		}
		if(bossSpawn==1){
			if(BossX>=1000){
				BossX-=15;
			}
			batch.draw(bossImg,BossX,0);
			batch.draw(HPBossborder,1882,0);
			for(int i=0;i<BossHP;i++){
				batch.draw(HPBossbar,1886,4+(i));
			}
		}
		batch.end();
	}
	
	public void render(float delta){
		update(delta);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		draw();
	}
}
