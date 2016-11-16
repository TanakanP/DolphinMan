package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.DolphinMan;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Dolphin Man";
		config.height = 1080;
		config.width = 1920;
		config.fullscreen = true;
		new LwjglApplication(new DolphinMan(), config);
	}
}
