package src.connectricity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import jdk.internal.misc.FileSystemOption;

import java.util.Scanner;

public class App extends ApplicationAdapter {
	int maxHeight = 720, maxWidth = 1280;
	Scanner keyboard = new Scanner(System.in);
	Toolkit tk = new Toolkit();
	MapMaker maker = new MapMaker(1, tk);
	Texture floor;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	Texture playerSprite;
	Texture obstacleSprite;
	Texture resistorSprite;
	Texture wireSprite;
	Texture generatorSprite;


	@Override
	public void create () {
		playerSprite = new Texture("player.png");
		obstacleSprite = new Texture("box1.png");
		generatorSprite = new Texture("generator.png");
		resistorSprite = new Texture("resistor.png");
		wireSprite = new Texture("wire.png");
		floor = new Texture("floor.tga");
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		Texture sprite;
		if (!maker.createMap()){
			System.out.println("Invalid level file");
		}
		else{
			Map map = maker.getMap();
			int cellsX = map.getSize()[0], cellsY = map.getSize()[1];
			float cellSize = ((maxWidth-300)/cellsX)>=((maxHeight-100)/cellsY) ? (maxHeight-100)/cellsY : (maxWidth-300)/cellsX;
			for(int i = 0; i < cellsX; i++){
				for(int j = 0; j < cellsY; j++){
					batch.draw(floor, cellSize*i, maxHeight-cellSize*(j+1), cellSize, cellSize);
				}
			}
			for(int i = 0; i < cellsX; i++){
				for(int j = 0; j < cellsY; j++){
					sprite = getSprite(map.getMatrix()[j][i]);
					if(sprite != null) {
						batch.draw(sprite, cellSize * i, maxHeight - cellSize * (j+1), cellSize, cellSize);
					}
				}
			}
			Gdx.input.isKeyPressed(23);

			Player player = maker.getPlayer();
			Controller ctrl = new Controller(player, map, tk);
			String tecla;

			ctrl.printMap();
			//while(ctrl.getContinuing()){
			//	tecla = keyboard.nextLine();
			//	ctrl.receiveCommand(tecla);
			//}
		}

		keyboard.close();
		batch.end();
	}


	private Texture getSprite(String name){
		switch (name){
			case "R" :
				return resistorSprite;
			case "W":
				return  wireSprite;
			case "P":
				return playerSprite;
			case "O":
				return obstacleSprite;
			case "G":
				return generatorSprite;
			default:
				return null;
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
