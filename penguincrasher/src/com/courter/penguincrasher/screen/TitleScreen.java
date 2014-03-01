package com.courter.penguincrasher.screen;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.courter.penguincrasher.Art;
import com.courter.penguincrasher.Input;
import com.courter.penguincrasher.Sound;

public class TitleScreen extends Screen {
	private int time = 0;

	@Override
	public void render () {
		int xOffs = 480 - time * 2;
		if (xOffs < 0) xOffs = 0;
		spriteBatch.begin();
		draw(Art.titleScreen0, 0, 0);
		draw(Art.titleScreen1, -xOffs, 0);
		if (time > 240) {
			String msg = null;
			if (Gdx.app.getType() == ApplicationType.Android)
				msg = "TOUCH TO START";
			else
				msg = "PRESS X TO START";
			drawString(msg, 160 - msg.length() * 3, 180 - 3 - (int)Math.abs(Math.sin(time * 0.1) * 10));

		}
		if (time >= 0) {
			String msg = "COPYRIGHT COURTER TECHNOLOGIES 2013";
			drawString(msg, 2, 240 - 6 - 2);
		}
		spriteBatch.end();
	}

	@Override
	public void tick (Input input) {
		time++;
//		if (time > 240) {
			if (Gdx.input.isTouched()) {
				Sound.startgame.play();
				setScreen(new WorldSelectorScreen());
//				input.releaseAllKeys();
			}
//		}
	}
}