
package com.courter.penguincrasher.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.courter.penguincrasher.Art;
import com.courter.penguincrasher.Input;
import com.courter.penguincrasher.OverlapTester;

public class LevelSelectorScreen extends Screen {
	private int worldNum;
	private int lvlNum;
	private int time = 0;
	private Rectangle levelOneBounds = new Rectangle(50, 70, 30, 30);
	private Rectangle levelTwoBounds = new Rectangle(150, 70, 30, 30);
	private Rectangle levelThreeBounds = new Rectangle(250, 70, 30, 30);
	private Rectangle levelFourBounds = new Rectangle(100, 130, 30, 30);
	private Rectangle levelFiveBounds = new Rectangle(200, 130, 30, 30);
	private Rectangle backBounds = new Rectangle(290, 210, 30, 30);

	Vector3 touchPoint;
	private OrthographicCamera guiCam;

	public LevelSelectorScreen (int worldNumber) {
		this.worldNum = worldNumber;
		touchPoint = new Vector3();
		guiCam = new OrthographicCamera(320, 240);
		guiCam.position.set(320 / 2, 240 / 2, 0);
		guiCam.setToOrtho(true, 320, 240);
	}

	@Override
	public void render () {
		spriteBatch.begin();
		draw(Art.bg, 0, 0);
		draw(Art.button, 50, 70);
		draw(Art.button, 150, 70);
		draw(Art.button, 250, 70);
		draw(Art.button, 100, 130);
		draw(Art.button, 200, 130);
		draw(Art.button, 290, 210);
		drawString("Levels", 140, 30);
		drawString("1", 60, 80);
		drawString("2", 160, 80);
		drawString("3", 260, 80);
		drawString("4", 110, 140);
		drawString("5", 210, 140);
		drawString("<-", 300, 220);
		spriteBatch.end();
	}

	@Override
	public void tick (Input input) {
		time++;
		if (time > 10) {
			if (Gdx.input.isTouched()) {
				guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

				if (OverlapTester.pointInRectangle(levelOneBounds, touchPoint.x, touchPoint.y)) {
					setScreen(new GameScreen(worldNum, 1));
				} else if (OverlapTester.pointInRectangle(levelTwoBounds, touchPoint.x, touchPoint.y)) {
					setScreen(new GameScreen(worldNum, 2));
				} else if (OverlapTester.pointInRectangle(levelThreeBounds, touchPoint.x, touchPoint.y)) {
					setScreen(new GameScreen(worldNum, 3));
				} else if (OverlapTester.pointInRectangle(levelFourBounds, touchPoint.x, touchPoint.y)) {
					setScreen(new GameScreen(worldNum, 4));
				} else if (OverlapTester.pointInRectangle(levelFiveBounds, touchPoint.x, touchPoint.y)) {
					setScreen(new GameScreen(worldNum, 5));
				} else if (OverlapTester.pointInRectangle(backBounds, touchPoint.x, touchPoint.y)) {
					setScreen(new WorldSelectorScreen());
				}
			}
		}
	}

}
