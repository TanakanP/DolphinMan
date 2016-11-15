package com.mygdx.game;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
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
	Texture crossHair;
	Texture bulletDolphin;
	Texture HPborder;
	Texture HPbar;
	Enemy[] enemy;
	Bullet[] bullet;
	int x;
	int spawn_count;
	int spawn_start;
	int multi=2;
	int start;
	int max_count;
	int bullet_mul;
	int score;
	int bullet_cal;
	int monsterMode;
	int level;
	int hp=10;
	PointerInfo a;
	Point b;
	float dum;
	float mouseX;
	float mouseY;
	int[] x_E = new int[100000];
	int[] y_E = new int[100000];
	int[] x_B = new int[100000];
	int[] y_B = new int[100000];
	int[] x_Brem = new int[100000];
	int[] y_Brem = new int[100000];
	int[] bX = new int[100000];
	int[] bY = new int[100000];
	int[] alive = new int[100000];
	int[] bulletR = new int[1000];
	int[] bulletS = new int[1000];
	int[] bullet_calx = new int[100000];
	int[] bullet_caly = new int[100000];
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
		dolphinRight = new Texture("dolphinRight.png");
		dolphinLeft = new Texture("dolphinLeft.png");
		shark = new Texture("Shark.png");
		crossHair = new Texture("crosshair.png");
		bulletDolphin = new Texture("bulletDolphin.png");
		dummyMap = new Texture("MapSea.jpg");
		HPborder = new Texture("HPborder.jpg");
		HPbar = new Texture("HPbar.jpg");
		mainTheme = Gdx.audio.newSound(Gdx.files.internal("MainTheme.mp3"));
		mainTheme.play();

		System.out.println("You enter Game Screen");
		
	
		
		
	}
	
	public void update(float delta){
		setUp();
		dolphinMove();
		monsterAction();
		bulletAction();
		checkStatus();
	}

	public void setUp(){
		a = MouseInfo.getPointerInfo();
		b = a.getLocation();
		dum = (int) b.getX();
		mouseX = dum-470;
		dum = (int) b.getY();
		mouseY = 936-dum;
	}
	
	public void monsterAction(){
		spawnEnemy();
		monsterMove();
	}
	
	public void bulletAction(){
		spawnBullet();
		bulletShoot();
		bulletMove();
		bulletHit();
	}
	
	public void checkStatus(){
		checkDolphinDead();
		checkMonsterDeadAll();
		checkEndgame();
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
			x=1;
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			dolphin.move(Dolphin.DIRECTION_RIGHT);
			x=0;
		}
	}
	
	public void spawnEnemy(){
		if(start==0){
			if(multi<=18){
				multi=multi+2;
			}
			for(int i=0;i<multi;i++){
				x_E[i]=1024;
				y_E[i]= rand.nextInt(768) + 1;
				enemy[i] = new Enemy(x_E[i],y_E[i]);
				alive[i] = 1;
			}
			start=1;
			spawn_count++;
		}
	}
	
	public void monsterMove(){
		for(int i=0;i<multi;i++){
			if(alive[i]==1){
				if(multi<=10){
					level=3;
				}
				else if(multi>10 && multi<=15){
					level=5;
				}
				else{
					level=7;
				}
				x_E[i]=enemy[i].moveX(x_E[i],monsterMode,level);
				if(y_E[i]==0){
					bounce[i]=1;
				}
				if(y_E[i]==768){
					bounce[i]=0;
				}
				y_E[i]=enemy[i].moveY(y_E[i],monsterMode,bounce[i],level);
				if(x_E[i]<=0){
					alive[i]=0;
				}
			}
		}
	}
	
	public void spawnBullet(){
		if(Gdx.input.justTouched()){
			for(int i=0;i<1000;i++){
				if(bulletR[i]==0){
					Vector2 pos = dolphin.getPosition();
					bulletR[i]=1;
					bulletS[i]=1;
					x_B[i]=(int) pos.x;
					y_B[i]=(int) pos.y;
					x_Brem[i]=(int) pos.x;
					y_Brem[i]=(int) pos.y;
					bX[i]=Gdx.input.getX();
					bY[i]=Gdx.input.getY();
					bullet[i] = new Bullet(x_B[i],y_B[i]);
					break;
				}
			}
		}
	}
	
	public void bulletShoot(){
		for(int i=0;i<1000;i++){
			if(bulletS[i]!=0){
				bullet_cal=(bullet[i].move(x_Brem[i],bX[i],y_Brem[i],bY[i])/5);
				bullet_calx[i]=Math.abs(((x_Brem[i]-bX[i])/bullet_cal));
				bullet_caly[i]=Math.abs(((y_Brem[i]-bY[i])/bullet_cal));
				bulletS[i]=0;
				
			}
		}
	}
	
	public void bulletMove(){
		for(int i=0;i<1000;i++){
			if(bulletR[i]!=0){
				if(x_Brem[i]>bX[i]){x_B[i]-=bullet_calx[i];}
				else if(x_Brem[i]<=bX[i]){x_B[i]+=bullet_calx[i];}
				if(y_Brem[i]>bY[i]){y_B[i]+=bullet_caly[i];}
				else if(y_Brem[i]<=bY[i]){y_B[i]-=bullet_caly[i];}
				
				if(x_B[i]<=0 || x_B[i]>=1024){bulletR[i]=0;bulletS[i]=0;}
				if(y_B[i]<=0 || y_B[i]>=768){bulletR[i]=0;bulletS[i]=0;} 
			}
		}
	}
	
	public void bulletHit(){
		for(int i=0;i<1000;i++){
			if(bulletR[i]!=0){
				for(int j=0;j<multi;j++){
					if(alive[j]!=0){
						if(x_B[i]+13>=x_E[j]+20 && x_B[i]+13<=x_E[j]+55){
							if(y_B[i]+13>=y_E[j]+18 && y_B[i]+13<=y_E[j]+52){
								alive[j]=0;
								bulletR[i]=0;
								bulletS[i]=0;
								score++;
								break;
							}
						}
					}
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
					System.out.print("DIE");
				}
			}
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
			dolphinMan.setScreen(new GameOver(dolphinMan));
		}
	}
	
	public void draw(){
		SpriteBatch batch = dolphinMan.batch;
		batch.begin();
		Vector2 pos = dolphin.getPosition();
		batch.draw(dummyMap,0,0);
		batch.draw(crossHair,mouseX,mouseY);
		font.draw(batch, "Shark Eliminated: "+score, 10, 748);
		font.draw(batch, "WASD to move, click to shoot, bullet bug yu na ja ", 700, 20);
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
		if(x==1){batch.draw(dolphinLeft,pos.x,pos.y);}
		else{batch.draw(dolphinRight,pos.x,pos.y);}
		batch.draw(HPborder,0,0);
		for(int i=0;i<hp;i++){
			batch.draw(HPbar,3+(i*30),3);
		}
		font.draw(batch, "HP", 10, 52);
		batch.end();
	}
	
	public void render(float delta){
		update(delta);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		draw();
	}
}
