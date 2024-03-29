package com.courter.penguincrasher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Art {
	public static TextureRegion[][] guys;
	public static TextureRegion[][] player1;
	public static TextureRegion[][] player2;
	public static TextureRegion[][] playerslide;
	public static TextureRegion[][] playerslide2;
	public static TextureRegion[][] walls;
	public static TextureRegion[][] gremlins;
	public static TextureRegion bg;
	public static Pixmap levels;
	public static TextureRegion titleScreen0;
	public static TextureRegion titleScreen1;
	public static TextureRegion shot;
	public static TextureRegion button;

	public static TextureRegion winScreen1;
	public static TextureRegion winScreen2;

	public static void load () {
		bg = load("res/background.png", 320, 240);
		levels = new Pixmap(Gdx.files.internal("res/penguinlevels.png"));
		titleScreen0 = load("res/titlescreen0.png", 480, 320);
		titleScreen1 = load("res/titlescreen1.png", 480, 320);
		guys = split("res/guys.png", 6, 6);
		playerslide = split("res/player.png", 32, 16);
		playerslide2 = split("res/player.png", 32, 32);
		player1 = split("res/player.png", 16, 32);
		player2 = split("res/player.png", 16, 32, true);
		walls = split("res/walls.png", 10, 10);
		button = new TextureRegion(guys[0][0].getTexture(), 0, 66, 30, 30);
		shot = new TextureRegion(guys[0][0].getTexture(), 3, 27, 2, 2);
		winScreen1 = load("res/winscreen1.png", 320, 240);
		winScreen2 = load("res/winscreen2.png", 320, 240);
	}

	private static TextureRegion[][] split (String name, int width, int height) {
		return split(name, width, height, false);
	}

	private static TextureRegion[][] split (String name, int width, int height, boolean flipX) {
		Texture texture = new Texture(Gdx.files.internal(name));
		int xSlices = texture.getWidth() / width;
		int ySlices = texture.getHeight() / height;
		TextureRegion[][] res = new TextureRegion[xSlices][ySlices];
		for (int x = 0; x < xSlices; x++) {
			for (int y = 0; y < ySlices; y++) {
				res[x][y] = new TextureRegion(texture, x * width, y * height, width, height);
				res[x][y].flip(flipX, true);
			}
		}
		return res;
	}

	public static TextureRegion load (String name, int width, int height) {
		Texture texture = new Texture(Gdx.files.internal(name));
		TextureRegion region = new TextureRegion(texture, 0, 0, width, height);
		region.flip(false, true);
		return region;
	}
}