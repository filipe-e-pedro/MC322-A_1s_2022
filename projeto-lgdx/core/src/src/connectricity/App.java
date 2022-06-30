package src.connectricity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class App extends ApplicationAdapter {
	int mapLevel = 1;
	String[][] levelInfo;
	FileHandle mapFile;
	BufferedReader mapReader;
	boolean newLevel = true;
	int gameState = 0;
	int maxHeight = 720, maxWidth = 1280, cellsX, cellsY;
	float cellSize;
	String key;
	Map map;
	Controller ctrl;
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
	Texture[] exitSprite;
	Texture[] chargeFrameSprite;
	Texture[] chargeSprite;
	Texture itemFrameSprite;
	BitmapFont font;

	@Override
	public void create () {
		playerSprite = new Texture("sprite_folder/player.png");
		String filePath = playerSprite.toString();
		obstacleSprite = new Texture("sprite_folder/box1.png");
		generatorSprite = new Texture("sprite_folder/generator.png");
		resistorSprite = new Texture("sprite_folder/resistor.png");
		wireSprite = new Texture("sprite_folder/wire.png");
		floor = new Texture("sprite_folder/floor.png");
		batterySprite = new Texture[4];
		for (int i = 1; i < 5; i++)
			batterySprite[i-1] = new Texture("sprite_folder/battery"+ i +".png");
		chargeSprite = new Texture[4];
		for (int i = 1; i < 5; i++)
			chargeSprite[i-1] = new Texture("sprite_folder/charge"+ i +".png");
		exitSprite = new Texture[2];
		exitSprite[0] = new Texture("sprite_folder/door_closed.png");
		exitSprite[1] = new Texture("sprite_folder/door_open.png");
		itemFrameSprite = new Texture("sprite_folder/item_frame.png");
		chargeFrameSprite = new Texture[3];
		for (int i = 1; i < 4; i++){
			chargeFrameSprite[i-1] = new Texture("sprite_folder/charge"+ i +"_frame.png");
		}
		font = new BitmapFont();

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
		if(gameState == 0)
			ruleScreen();
		if(gameState == 1)
			progressGame();
		if(gameState == 2)
			victoryScreen();
		batch.end();
	}

	private void ruleScreen(){
		font.draw(batch, "Regras", 200, 200);
		batch.draw(playerSprite, 400, 400);
		if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))
			gameState++;
	}
	private void victoryScreen(){
		font.draw(batch, "Vitóriaa", 200, 200);
	}
	private void progressGame(){
		if (newLevel) {
			makeMap(mapLevel);
			newLevel = false;
		} else {
			if (!ctrl.getContinuing()) {
				if(mapLevel < 3) {
					newLevel = true;
					mapLevel++;
				}
				else{
					gameState++;
				}
			}
		}
		cellsX = map.getSize()[0];
		cellsY = map.getSize()[1];
		cellSize = ((maxWidth - 300) / cellsX) >= ((maxHeight - 100) / cellsY) ? (maxHeight - 100) / cellsY : (maxWidth - 300) / cellsX;
		renderMap();
		renderInventory();
		renderChargeBars();


		if (key != null && !Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
			ctrl.receiveCommand(key);
			key = null;
		} else {
			if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
				key = "w";
			} else if (Gdx.input.isKeyJustPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				key = "s";
			} else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				key = "d";
			} else if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				key = "a";
			} else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
				key = "q";
			} else if (Gdx.input.isKeyPressed(Input.Keys.F)) {
				key = "f";
			} else if (Gdx.input.isKeyPressed(Input.Keys.R)) {
				key = "r";
			} else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
				key = "q";
			}
		}
	}
	private void renderMap(){
		Texture sprite;
		for(int i = 0; i < cellsX; i++){
			for(int j = 0; j < cellsY; j++){
				batch.draw(floor, cellSize*i, maxHeight-cellSize*(j+1), cellSize, cellSize);
			}
		}
		for(int i = 0; i < cellsX; i++) {
			for (int j = 0; j < cellsY; j++) {
				sprite = getSprite(i, j);
				if (sprite != null) {
					batch.draw(sprite, cellSize * i, maxHeight - cellSize * (j + 1), cellSize, cellSize);
				}
			}
		}
	}

	private void renderChargeBars(){
		for(int i = 0; i < 4 ; i++){
			if(map.getNeededCharge(i)>0){
				batch.draw(batterySprite[i], cellsX*cellSize+50, maxHeight-100-(100*i), 50, 50);
				for(int j = 0; j < map.getBatteryCharge(i); j++){
					batch.draw(chargeSprite[i], cellsX*cellSize+(50*(j+2)),maxHeight-100-(100*i), 50, 50);
				}
				batch.draw(chargeFrameSprite[map.getNeededCharge(i)-1], cellsX*cellSize+100,maxHeight-100-(100*i), 150, 50);
			}
		}
	}
	private void renderInventory(){
		batch.draw(wireSprite, 40, 40, 50, 50);
		batch.draw(itemFrameSprite, 40, 40, 50, 50);
		batch.draw(resistorSprite, 130, 40, 50, 50);
		batch.draw(itemFrameSprite, 130, 40, 50, 50);

		font.draw(batch, "x"+player.getWireNumber(), 90, 40);
		font.draw(batch, "x"+player.getResistorNumber(), 180, 40);
		font.draw(batch, "F", 62, 35);
		font.draw(batch, "R", 152, 35);
	}
	private boolean makeMap(int mapID){
		mapFile = Gdx.files.internal("maps_folder/"+mapID+".csv");
		mapReader = mapFile.reader(8192);

		Vector<String[]> v = new Vector<String[]>();
		try {
			String line = mapReader.readLine();
			while (line != null) {
				String ln[]  = line.split(",");
				v.add(ln);
				line = mapReader.readLine();
			}
			mapReader.close();
		} catch (Exception erro) {
			erro.printStackTrace();
		}
		levelInfo = (String[][])v.toArray(new String[v.size()][]);
		maker = new MapMaker(levelInfo);
		try {
			maker.invalidMap();
		} catch (InvalidMapException exception) {
			System.err.println("Erro: " + exception.getMessage());
			return false;
		}

		maker.createMap();

		map = maker.getMap();
		player = maker.getPlayer();
		ctrl = new Controller(player, map);
		return true;
	}

	private Texture getSprite(int x, int y){
		String name = map.getMatrix()[y][x];
		String component = name.substring(0, 1);
		int state;
		switch (component){
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
				return batterySprite[map.getSquare(x, y).getBattery().getID()];
			case "E":
				if(map.getExitState()){
					state = 1;
				}
				else{
					state = 0;
				}
				return exitSprite[state];
			default:
				return null;
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
