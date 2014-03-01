package com.courter.penguincrasher.entity;

import com.courter.penguincrasher.Art;
import com.courter.penguincrasher.level.Camera;
import com.courter.penguincrasher.screen.GameScreen;
import com.courter.penguincrasher.screen.Screen;

public class Door extends Entity{
	public int id;
	public boolean startNewLevel = false;

	public Door (int x, int y, int id) {
		this.x = x;
		this.y = y;
		this.w = 6;
		this.h = 6;
		xa = ya = 0;
		this.id = id;
	}
	
	@Override
	public void render (Screen g, Camera camera) {
			g.draw(Art.walls[4][0], (int)x, (int)y);
	}
	
	@Override
	public void tick () {
		java.util.List<Entity> entities = level.getEntities((int)x, (int)y, 6, 6);
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e instanceof Player) {
				Player player = (Player)e;
				level.transition(0, 0);
			}
		}
	}
}
