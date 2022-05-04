package com.tolentsgames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.tolentsgames.main.Game;
import com.tolentsgames.world.Camera;
import com.tolentsgames.world.World;

public class Player extends Entity {

	public boolean left, right;
	
	public static double life = 100;
	
	public static double currentCoins = 0;
	public static double maxCoins = 0;
	
	public static double currentEnemies = 0;
	public static double maxEnemies = 0;
	
	public int dir = 1;
	private double gravity = 0.3;
	private double vspd = 0;
	
	public boolean jump1 = false;
	public boolean jump2 = false;
	
	public boolean isjump1ing = false;
	public int jump1Height = 38;
	public int jump1Frames = 0;
	
	private int framesAnimation = 0;
	private int maxFrames = 15;
	private boolean moved = false;
	
	private int maxSprite = 2;
	private int curSprite = 0;
	
	public Player(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
		
	public void tick() {
		moved = false;
		depth = 2;
		vspd += gravity;
//		System.out.println("jump1: " + jump1);
		if(!World.isFree((int)x, (int)(y + 1)) && jump1) {
			vspd = -5;
			jump1 = false;
		}
		
		if(jump2) {
			vspd = -2;
			jump2 = false;
		}
		
		if(!World.isFree((int)x, (int)(y + vspd))) {
			
			int signVsp = 0;
			if(vspd >= 0)
				signVsp = 1;
			else
				signVsp = -1;
			while(World.isFree((int)x, (int)(y + signVsp))) {
				y += signVsp;
			}
			vspd = 0;
		}

		y += vspd;
		
		
		if(vspd > 0) {
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Enemy) {
					if(Entity.isColidding(this, e)) {
						//Aplicar pulo:
						jump2 = true;
						// Remover vida do inimigo 
						((Enemy) e).vida--;
						if(((Enemy) e).vida == 0) {
							// Destruir inimigo
							Game.entities.remove(i);
							currentEnemies++;
							break;
						}						
					}
				}				
			}
		}

		if(right && World.isFree((int)(x + speed), (int)y)) {
			moved = true;
			x += speed;
			dir = 1;
		}
			
		else if(left && World.isFree((int)(x - speed), (int)y)) {
			moved = true;
			x -= speed;
			dir = -1;
		}
		
		//Detectar dano...
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy) {
				if(Entity.isColidding(this, e)) {
					if(life > 0)
						if(Entity.rand.nextInt(100) < 10)
							life--;
				}
			}				
		}
		
		//Colisão com moeda
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Moeda) {
				if(Entity.isColidding(this, e)) {
					Game.entities.remove(i);
					currentCoins++;
					break;
				}
			}				
		}
		
		Camera.x = Camera.clamp((int)x - Game.WIDTH/2, 0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp((int)y - Game.HEIGHT/2, 0, World.HEIGHT * 16 - Game.HEIGHT);
		
		if(moved) {
			framesAnimation++;
			if(framesAnimation == maxFrames ) {
				curSprite++;
				framesAnimation = 0;
				if(curSprite == maxSprite) {
					curSprite = 0;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		
		if(dir == 1) {
			sprite = Entity.PLAYER_SPRITE_RIGHT[curSprite];
		} else if(dir == -1) {
			sprite = Entity.PLAYER_SPRITE_LEFT[curSprite];
		}
		super.render(g);
	}
}
	
