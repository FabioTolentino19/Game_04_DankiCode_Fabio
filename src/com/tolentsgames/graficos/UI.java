package com.tolentsgames.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.tolentsgames.entities.Player;
import com.tolentsgames.main.Game;

public class UI {
	
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(48, 8, 160, 30);
		g.fillRect(256, 8, 160, 30);
		g.fillRect(464, 8, 160, 30);
		g.setColor(Color.green);
		g.fillRect(48, 8, (int)((Player.life/100)*160), 30);
		g.setColor(Color.yellow);
		g.fillRect(256, 8, (int)((Player.currentCoins/Player.maxCoins)*160), 30);
		g.setColor(Color.gray);
		g.fillRect(464, 8, (int)((Player.currentEnemies/Player.maxEnemies)*160), 30);
		g.setColor(Color.white);
		g.drawRect(48, 8, 160, 30);
		g.drawRect(256, 8, 160, 30);
		g.drawRect(464, 8, 160, 30);
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 14));
		g.drawString("Vida: " + (int)Player.life + "/" + "100", 80, 30);
		g.drawString("Moedas: " + (int)Player.currentCoins + "/" + (int)Player.maxCoins, 288, 30);
		g.drawString("Inimigos: " + (int)Player.currentEnemies + "/" + (int)Player.maxEnemies, 496, 30);		
	}

}
