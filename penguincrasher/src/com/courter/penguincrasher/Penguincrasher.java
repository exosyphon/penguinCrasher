
package com.courter.penguincrasher;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.courter.penguincrasher.Art;
import com.courter.penguincrasher.Input;
import com.courter.penguincrasher.Sound;
import com.courter.penguincrasher.screen.Screen;
import com.courter.penguincrasher.screen.TitleScreen;

public class Penguincrasher implements ApplicationListener {
	public static final int GAME_WIDTH = 320;
	public static final int GAME_HEIGHT = 240;
	public static final int SCREEN_SCALE = 2;

	private static final long serialVersionUID = 1L;

	private boolean running = false;
	private Screen screen;
	private final Input input = new Input();
	private final boolean started = false;
	private float accum = 0;
	OrthographicCamera guiCam;

	@Override
	public void create () {
		Art.load();
		Sound.load();
		Gdx.input.setInputProcessor(new GestureDetector(input));
		running = true;
		setScreen(new TitleScreen());
	}

	@Override
	public void pause () {
		running = false;
	}

	@Override
	public void resume () {
		running = true;
	}

	public void setScreen (Screen newScreen) {
		if (screen != null) screen.removed();
		screen = newScreen;
		if (screen != null) screen.init(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		accum += Gdx.graphics.getDeltaTime();
		while (accum > 1.0f / 60.0f) {
			screen.tick(input);
//			input.tick();
			accum -= 1.0f / 60.0f;
		}
		screen.render();
// batch.begin();
// font.draw(batch, "fps: " + Gdx.graphics.getFramesPerSecond(), 10, 30);
// batch.end();
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void dispose () {
	}
}