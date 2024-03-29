package com.courter.penguincrasher.screen;

import com.courter.penguincrasher.Art;
import com.courter.penguincrasher.Input;

public class PauseScreen extends Screen {
	private final GameScreen parent;
	private int selected = 0;

	private final String[] options = {"BACK TO GAME", "AUTOMORTIS", "QUIT TO TITLE"};

	public PauseScreen (GameScreen parent) {
		this.parent = parent;
	}

	@Override
	public void render () {
		parent.render();

		spriteBatch.begin();
		int xs = 0;
		int ys = options.length;
		for (int y = 0; y < options.length; y++) {
			int s = options[y].length();
			if (s > xs) xs = s;
		}
		xs += 1;
		int xp = 40;
		int yp = 40;
		for (int x = 0 - 1; x < xs + 1; x++) {
			for (int y = 0 - 1; y < ys + 1; y++) {
				int xf = 1;
				int yf = 12;
				if (x < 0) xf--;
				if (y < 0) yf--;
				if (x >= xs) xf++;
				if (y >= ys) yf++;
				draw(Art.guys[xf][yf], xp + x * 6, yp + y * 6);
			}
		}
		for (int y = 0; y < options.length; y++) {
			if (y == selected) {
				drawString("+", xp, yp + y * 6);
			}
			drawString(options[y], xp + 6, yp + y * 6);
		}
		spriteBatch.end();
	}

	@Override
	public void tick (Input input) {
		if (!input.oldButtons[Input.ESCAPE] && input.buttons[Input.ESCAPE]) {
			setScreen(parent);
			return;
		}
		if (input.buttons[Input.UP] && !input.oldButtons[Input.UP]) {
			selected--;
			if (selected < 0) selected += options.length;
		}
		if (input.buttons[Input.DOWN] && !input.oldButtons[Input.DOWN]) {
			selected++;
			if (selected >= options.length) selected -= options.length;
		}
		if (input.buttons[Input.SHOOT] && !input.oldButtons[Input.SHOOT]) {
			if (selected == 0) {
				setScreen(parent);
			} else if (selected == 1) {
				parent.level.player.die();
				setScreen(parent);
			} else if (selected == 2) {
				setScreen(new TitleScreen());
//			} else if (selected == 3) {
//				setScreen(new WinScreen());
			}
		}
		// if (delay>0) delay--;
		// if (delay==0 && input.buttons[Input.SHOOT] && !input.oldButtons[Input.SHOOT]) {
		// setScreen(parent);
		// }
	}
}