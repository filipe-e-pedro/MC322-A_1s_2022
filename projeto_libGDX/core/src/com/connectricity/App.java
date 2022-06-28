package com.connectricity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Scanner;

public class App extends ApplicationAdapter {
	int maxHeight = 720, maxWidth = 1280;
	Scanner keyboard = new Scanner(System.in);
	Toolkit tk = new Toolkit();
	MapMaker maker = new MapMaker(2, tk);
	Texture floor;
	private SpriteBatch batch;
	@Override
	public void create () {
		floor = new Texture("floor.tga");
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		if (!maker.createMap()){
			System.out.println("Invalid level file");
		}
		else{
			Map map = maker.getMap();
			int cellsX = map.getSize()[0], cellsY = map.getSize()[1];
			float cellSize = ((maxWidth-300)/cellsX)>=((maxHeight-100)/cellsY) ? (maxHeight-100)/cellsY : (maxWidth-300)/cellsX;
			for(int i = 0; i < cellsX; i++){
				for(int j = 0; j < cellsY; j++){
					batch.draw(floor, cellSize*i, maxHeight-cellSize*j, cellSize, cellSize);
				}
			}
			Player player = maker.getPlayer();
			Controller ctrl = new Controller(player, map, tk);
			String tecla;

			ctrl.printMap();
			while(ctrl.getContinuing()){
				tecla = keyboard.nextLine();
				ctrl.receiveCommand(tecla);
			}
		}

		keyboard.close();
		batch.end();
	}

	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
