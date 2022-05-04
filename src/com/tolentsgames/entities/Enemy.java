package com.tolentsgames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.tolentsgames.world.World;

public class Enemy extends Entity {
	
	public boolean right = true, left = false;
	public int vida = 3;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		if(World.isFree((int)x, (int)(y+1))) {
			y += 1;
		}
		
		if(right && World.isFree((int)(x + speed), (int)y)) {
			x += speed;
			if(World.isFree((int)(x + 16), (int)(y + 1)) || !World.isFree((int)(x + 16), (int)y)) {
				right = false;
				left = true;
			}
		} else if(left && World.isFree((int)(x - speed), (int)y)) {
			x -= speed;
			if(World.isFree((int)(x - 16), (int)(y + 1)) || !World.isFree((int)(x - 16), (int)y)) {
				right = true;
				left = false;
			}
		}
	}
	
	public void render(Graphics g) {
		if(right) 
			sprite = Entity.ENEMY1_SPRITE_RIGHT[vida-1];
		else if(left)
			sprite = Entity.ENEMY1_SPRITE_LEFT[vida-1];
		
		super.render(g);
	}

}
