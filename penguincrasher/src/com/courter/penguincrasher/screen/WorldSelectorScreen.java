
package com.courter.penguincrasher.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.courter.penguincrasher.Input;
import com.courter.penguincrasher.Art;
import com.courter.penguincrasher.OverlapTester;

public class WorldSelectorScreen extends Screen {
	private int time = 0;
	private Rectangle worldOneBounds = new Rectangle(50, 70, 30, 30);
	private Rectangle worldTwoBounds = new Rectangle(150, 70, 30, 30);
	private Rectangle worldThreeBounds = new Rectangle(250, 70, 30, 30);
	private Rectangle worldFourBounds = new Rectangle(100, 130, 30, 30);
	private Rectangle worldFiveBounds = new Rectangle(200, 130, 30, 30);
	Vector3 touchPoint;
	private OrthographicCamera guiCam;
	
	public WorldSelectorScreen() {

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
		drawString("Worlds", 140, 30);
		drawString("1", 60, 80);
		drawString("2", 160, 80);
		drawString("3", 260, 80);
		drawString("4", 110, 140);
		drawString("5", 210, 140);
		spriteBatch.end();
	}

	@Override
	public void tick (Input input) {
		time++;
		if (time > 10) {
			if (Gdx.input.isTouched()) {
				guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
				
				if (OverlapTester.pointInRectangle(worldOneBounds, touchPoint.x, touchPoint.y)) {
					setScreen(new LevelSelectorScreen(1));
				} else if (OverlapTester.pointInRectangle(worldTwoBounds, touchPoint.x, touchPoint.y)) {
					setScreen(new LevelSelectorScreen(2));
				} else if (OverlapTester.pointInRectangle(worldThreeBounds, touchPoint.x, touchPoint.y)) {
					setScreen(new LevelSelectorScreen(3));
				} else if (OverlapTester.pointInRectangle(worldFourBounds, touchPoint.x, touchPoint.y)) {
					setScreen(new LevelSelectorScreen(4));
				} else if (OverlapTester.pointInRectangle(worldFiveBounds, touchPoint.x, touchPoint.y)) {
					setScreen(new LevelSelectorScreen(5));
				}
			}
		}
	}

}
