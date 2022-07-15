package src.connectricity;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import src.connectricity.App ;

/**
 * main class
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.useVsync(true);
		config.setWindowedMode(1280, 720);
		config.setTitle("Connectricity");
		new Lwjgl3Application(new src.connectricity.App(), config);
	}
}
