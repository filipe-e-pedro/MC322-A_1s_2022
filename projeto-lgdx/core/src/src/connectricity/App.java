package src.connectricity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.*;
import java.util.Vector;

public class App extends ApplicationAdapter {
/**
 * Classe chamada pelo main (DesktopLauncher.java)
 * que renderiza o jogo e recebe os comandos do usuario
 */
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
	/**
	 * Cria todos os objetos utilizados para a renderizacao
	 */
		playerSprite = new Texture("sprite_folder/player.png");
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
	/**
	 * Funcao que roda em loop renderizando o jogo e recebendo os comandos do usuario
	 */
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

	private void rules(){
	/**
	 * Metodo que renderiza as regras
	 */
		font.getData().setScale(2, 2);
		font.draw(batch, "Regras:", 20, maxHeight-30);
		font.getData().setScale(1, 1);
		font.draw(batch, "-Use WASD ou as setinhas para se mover pelo mapa.", 20, maxHeight-100);
		font.draw(batch, "-Use F para colocar ou remover fios:", 20, maxHeight-140);
		batch.draw(wireSprite, 270, maxHeight-165, 40, 40);
		font.draw(batch, "+Os fios conduzem energia sem mudar o potencial.", 40, maxHeight-170);
		font.draw(batch, "+O número de fios por mapa é limitado.", 40, maxHeight-185);
		font.draw(batch, "-Use R para colocar ou remover resistores:", 20, maxHeight-220);
		batch.draw(resistorSprite, 305, maxHeight-245, 40, 40);
		font.draw(batch, "+Os resistores conduzem energia, mas diminuem o potencial.", 40, maxHeight-250);
		font.draw(batch, "+O número de resistores por mapa é limitado.", 40, maxHeight-265);
		font.draw(batch, "+Dois resistores não se conectam diretamente.", 40, maxHeight-280);
	}

	private void conditions(){
	/**
	 * Metodo que renderiza as condicoes de vitoria
	 */
		font.getData().setScale(2, 2);
		font.draw(batch, "Condições de vitória:", 600, maxHeight-30);
		font.getData().setScale(1, 1);
		font.draw(batch, "-Abra a porta e escape até completar todos os leveis.", 620, maxHeight-100);
		font.draw(batch, "-Para abrir a porta alimente cada bateria com o potencial indicado:", 620, maxHeight-140);
		batch.draw(new Texture("rules_folder/rule_example1.png"), 620, maxHeight-260, 150, 90);
		batch.draw(new Texture("rules_folder/charge_example1.png"), 780, maxHeight-260, 120, 90);
		font.draw(batch, "Nesse caso é necessário carregar \na primeira bateria (azul) com uma carga 3 \ne a segunda (vermelha) com uma carga 1.", 910, maxHeight-190);
		batch.draw(new Texture("rules_folder/rule_example2.png"), 620, maxHeight-380, 150, 90);
		batch.draw(new Texture("rules_folder/charge_example2.png"), 780, maxHeight-380, 120, 90);
		font.draw(batch, "Aqui ambas as baterias foram ligadas \ncom a carga direto do gerador (carga 3), \nportanto a saída não será aberta.", 910, maxHeight-310);
		batch.draw(new Texture("rules_folder/rule_example3.png"), 620, maxHeight-500, 150, 90);
		batch.draw(new Texture("rules_folder/charge_example3.png"), 780, maxHeight-500, 120, 90);
		font.draw(batch, "Aqui a bateria azul foi ligada diretamente \nno gerador (carga 3), enquanto a carga que chega \nna vermelha passou por dois resistores (carga 1). \nAmbas estão com a carga indicada, a saída é aberta.", 910, maxHeight-420);
		batch.draw(new Texture("rules_folder/rule_example4.png"), 620, maxHeight-620, 150, 90);
		batch.draw(new Texture("rules_folder/charge_example2.png"), 780, maxHeight-620, 120, 90);
		font.draw(batch, "Cuidado: \nOs fios conduzem a maior carga \ncom a qual eles têm contato", 910, maxHeight-550);
	}

	private void entitys(){
	/**
	 * Metodo que renderiza todas as entitidades do jogo
	 */
		font.getData().setScale(2, 2);
		font.draw(batch, "Elementos:", 20, 400);
		font.getData().setScale(1, 1);
		font.draw(batch, "Jogador:", 20, 360);
		batch.draw(playerSprite, 20, 285, 60, 60);
		font.draw(batch, "Obstáculo:", 20, 265);
		batch.draw(obstacleSprite, 20, 190, 60, 60);
		font.draw(batch, "Fio:", 20, 175);
		batch.draw(wireSprite, 20, 100, 60, 60);
		font.draw(batch, "Resistor:", 20, 85);
		batch.draw(resistorSprite, 20, 10, 60, 60);
		font.draw(batch, "Baterias:", 140, 360);
		for(int i = 0; i < 4; i++)
			batch.draw(batterySprite[i], 140, 360-75*(i+1), 60, 60);
		font.draw(batch, "Gerador:", 260, 360);
		batch.draw(generatorSprite, 260, 285, 60, 60);
		font.draw(batch, "Saída:", 260, 265);
		batch.draw(exitSprite[0], 260, 190, 60, 60);
		batch.draw(exitSprite[1], 260, 115, 60, 60);

	}

	private void ruleScreen(){
	/**
	 * Metodo que renderiza a tela de regras e espera que alguma tecla seja pressionada para passar para o jogo
	 */
		rules();
		conditions();
		entitys();
		font.getData().setScale(2, 2);
		font.draw(batch, "APERTE  QUALQUER  TECLA  PARA  CONTINUAR > >", 540, 40);
		if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))
			gameState++;
	}

	private void victoryScreen(){
		batch.draw(new Texture("sprite_folder/Vitoria.png"), 0, 0, maxWidth, maxHeight);
	}

	private void progressGame(){
	/**
	 * Metodo que garante a progressao do jogo, le um novo arquivo de mapa caso esteja em uma nova fase,
	 * redimensiona o tamanho de cada celula para que ela seja o maior possivel garantindo que haja espaco para
	 * os outros elementos dessa tela, renderiza o mapa, o inventario do jogador e o estado da carga das baterias,
	 * e recebe os comandos do usuario, repassando para a classe controller
	 */
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
	/**
	 * Renderiza o mapa
	 */
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
	/**
	 * Renderiza a carga atual das baterias
	 */
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
	/**
	 * Renderiza o inventario do jogador
	 */
		batch.draw(wireSprite, 40, 40, 50, 50);
		batch.draw(itemFrameSprite, 40, 40, 50, 50);
		batch.draw(resistorSprite, 130, 40, 50, 50);
		batch.draw(itemFrameSprite, 130, 40, 50, 50);
		font.getData().setScale(1, 1);
		font.draw(batch, "x"+player.getWireNumber(), 90, 40);
		font.draw(batch, "x"+player.getResistorNumber(), 180, 40);
		font.draw(batch, "F", 62, 35);
		font.draw(batch, "R", 152, 35);
	}

	private void makeMap(int mapID){
	/**
	 * Recebe o ID do mapa da fase que esta sendo jogada
	 *
	 * Le o arquivo do mapa relativo ao ID recebido e passa para o MapMaker fazer o mapa.
	 *
	 * Quando ele le o arquivo .csv do mapa ele coloca as informacoes em uma matriz de String
	 */
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
		}

		maker.createMap();

		map = maker.getMap();
		player = maker.getPlayer();
		ctrl = new Controller(player, map);
	}

	private Texture getSprite(int x, int y){
	/**
	 * Recebe dois inteiros que representam uma posicao no mapa
	 * Retorna a sprite da entidade mais importante que esta naquela posicao do mapa
	 */
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
