package com.courter.penguincrasher.screen;

import java.util.Vector;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Gdx;
import com.courter.penguincrasher.Art;
import com.courter.penguincrasher.Input;
import com.courter.penguincrasher.Penguincrasher;
import com.courter.penguincrasher.Stats;
import com.courter.penguincrasher.entity.Player;
import com.courter.penguincrasher.level.Camera;
import com.courter.penguincrasher.level.Level;
import com.sun.java.browser.net.ProxyServiceProvider;

public class GameScreen extends Screen {
	OrthographicCamera guiCam;
	private int worldNumber;
	private int levelNumber;

	private static final boolean DEBUG_MODE = false;
	private int xLevel = DEBUG_MODE ? 8 : 0;
	private int yLevel = DEBUG_MODE ? 4 : 0;

	private final Camera camera = new Camera(Penguincrasher.GAME_WIDTH, Penguincrasher.GAME_HEIGHT);

	public boolean mayRespawn = false;
	public Level level;
	public GameScreen (int worldNum, int levelNum) {
		Stats.reset();

		this.guiCam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.guiCam.setToOrtho(true, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		guiCam.position.y = FRUSTUM_HEIGHT/2;
		guiCam.position.x = FRUSTUM_WIDTH/2;
		this.worldNumber = worldNum;
		this.levelNumber = levelNum;
		level = new Level(this, 320, 24, 0, 2*(levelNumber-1), 0, 0, worldNumber);
	}

	@Override
	public void tick (Input input) {
		Stats.instance.time++;
		if (!input.oldButtons[Input.ESCAPE] && input.buttons[Input.ESCAPE]) {
			setScreen(new PauseScreen(this));
			return;
		}
		if(input.flinging){
			level.player.setSliding(true);
			input.flinging = false;
		}
		if (!level.player.removed)
			level.player.tick(input);
		else if (mayRespawn) {
				respawnRoom();
				mayRespawn = false;
		}
		level.tick();
	}

	public void transition (int xa, int ya) {
		if (yLevel >= 1) {
			setScreen(new WinScreen(worldNumber, levelNumber));
			return;
		}
		
		yLevel +=1;

		level.player.x = level.xSpawn;
		level.player.y = level.ySpawn *2 + 35;
		guiCam.position.x = FRUSTUM_WIDTH/2;
		
		guiCam.update();
	}

	@Override
	public void render () {

		if ((level.player.x > guiCam.position.x) && (level.player.x > Penguincrasher.GAME_WIDTH/2))
		{
			guiCam.position.x = (int)level.player.x;
		}
		guiCam.update();
		spriteBatch.setProjectionMatrix(guiCam.combined);

		spriteBatch.begin();
		draw(Art.bg, 0, 0);

		spriteBatch.end();
		level.render(this, camera);

//		spriteBatch.begin();
//		if (mayRespawn) {
//			String msg = "stuff " + yLevel;//"PRESS X TO TRY AGAIN";
//			drawString(msg, 160 - msg.length() * 3, 120 - 3);
//		}
//		spriteBatch.end();
	}

	public void respawnRoom () {
		Level newLevel = new Level(this, 320, 24, 0, 2*(levelNumber-1), 0, 0, worldNumber);
		this.level = newLevel;
		if(yLevel > 0) {
			level.player.y = level.ySpawn *2 + 35;
		}
		else
		{
			level.player.y = level.ySpawn;
		}
		level.player.x = level.xSpawn;
		guiCam.position.x = FRUSTUM_WIDTH/2;
		guiCam.update();
	}
}