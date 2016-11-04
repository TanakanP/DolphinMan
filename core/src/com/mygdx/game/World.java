package com.mygdx.game;

public class World{	
	private Dolphin dolphin;
	DolphinMan dolphinMan;
	
	World(DolphinMan dolphinMan){
		this.dolphinMan = dolphinMan;
		
		dolphin = new Dolphin(100,100);
	}
	
	Dolphin getDolphin(){
		return dolphin;
	}
}
