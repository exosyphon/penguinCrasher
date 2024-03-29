package com.courter.penguincrasher.entity;

import com.courter.penguincrasher.Art;
import com.courter.penguincrasher.level.Camera;
import com.courter.penguincrasher.level.Level;
import com.courter.penguincrasher.screen.Screen;


public class Gore extends Entity {
//	private int life;

	public Gore (double x, double y, double xa, double ya) {
		this.x = x;
		this.y = y;
		this.w = 1;
		this.h = 1;
		bounce = 0.2;
		this.xa = (xa + (random.nextDouble() - random.nextDouble()) * 1) * 0.2;
		this.ya = (ya + (random.nextDouble() - random.nextDouble()) * 1) * 0.2;

//		life = random.nextInt(20) + 10;
	}

	@Override
	public void tick () {
//		if (life-- <= 0) remove();
		onGround = false;
		tryMove(xa, ya);

		xa *= 0.999;
		ya *= 0.999;
		ya += Level.GRAVITY * 0.15;
	}

	@Override
	protected void hitWall (double xa, double ya) {
		this.xa *= 0.4;
		this.ya *= 0.4;
	}

	@Override
	public void render (Screen g, Camera camera) {
		int xp = (int)x;
		int yp = (int)y;
		g.draw(Art.guys[7][1], xp, yp);
	}
}