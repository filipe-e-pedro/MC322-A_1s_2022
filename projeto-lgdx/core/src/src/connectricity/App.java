package src.connectricity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Scanner;

public class App extends ApplicationAdapter {
	int mapLevel = 1, batteryCount = 0;
	boolean newLevel = true;
	int maxHeight = 720, maxWidth = 1280;
	String key;
	Scanner keyboard = new Scanner(System.in);
	Toolkit tk = new Toolkit();
	Map map;
	Player player;
	MapMaker maker;
	Texture floor;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	Texture playerSprite;
	Texture obstacleSprite;
	Texture resistorSprite;
	Texture wireSprite;
	Texture generatorSprite;
	Texture[] batterySprite;


	@Override
	public void create () {
		playerSprite = new Texture("sprite_folder/player.png");
		obstacleSprite = new Texture("sprite_folder/box1.png");
		generatorSprite = new Texture("sprite_folder/generator.png");
		resistorSprite = new Texture("sprite_folder/resistor.png");
		wireSprite = new Texture("sprite_folder/wire.png");
		floor = new Texture("sprite_folder/floor.tga");
		batterySprite = new Texture[4];
		for (int i = 1; i < 5; i++)
			batterySprite[i-1] = new Texture("sprite_folder/battery"+ i +".png");
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		key = null;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if(newLevel){

			makeMap();
			newLevel = false;
		}
		renderMap();
		Controller ctrl = new Controller(player, map, tk);

		if(key != null && !Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
			ctrl.receiveCommand(key);
			key = null;
		}
		else{
			if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
				key = "w";
			}
			else if(Gdx.input.isKeyJustPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				key = "s";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				key = "d";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				key = "a";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.Q)){
				key = "q";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.F)){
				key = "f";
			}

		}


		ctrl.printMap();

		keyboard.close();
		batch.end();
	}

	private Boolean makeMap(){
		maker = new MapMaker(mapLevel, tk);
		if (!maker.createMap()){
			System.out.println("Invalid level file");
			return false;
		}
		else {
			map = maker.getMap();
			player = maker.getPlayer();
			return true;
		}
	}
	private void renderMap(){
		Texture sprite;
		int cellsX = map.getSize()[0], cellsY = map.getSize()[1];
		float cellSize = ((maxWidth-300)/cellsX)>=((maxHeight-100)/cellsY) ? (maxHeight-100)/cellsY : (maxWidth-300)/cellsX;
		for(int i = 0; i < cellsX; i++){
			for(int j = 0; j < cellsY; j++){
				batch.draw(floor, cellSize*i, maxHeight-cellSize*(j+1), cellSize, cellSize);
			}
		}
		for(int i = 0; i < cellsX; i++) {
			for (int j = 0; j < cellsY; j++) {
				sprite = getSprite(map.getMatrix()[j][i]);
				if (sprite != null) {
					batch.draw(sprite, cellSize * i, maxHeight - cellSize * (j + 1), cellSize, cellSize);
				}
			}
		}
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
			case "B":
				Texture sprite = batterySprite[batteryCount];
				batteryCount = (batteryCount<3) ? batteryCount + 1 : 0;
				return sprite;
			default:
				return null;
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
