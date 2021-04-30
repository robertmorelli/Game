package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Player implements Displayable {

	public double VelY ,VelX ,PosY ,PosX;
	int up,down,left,right;
	public int score = 0;
	
	playListener listening;
	Color playerColor;

	public Player(int[] controlls,  Color color) {

		up = controlls[0];
		down = controlls[1];
		left = controlls[2];
		right = controlls[3];
		listening = new playListener();

		playerColor = color;

	}
	
	public void setKeys(int[] controlls) {
		up = controlls[0];
		down = controlls[1];
		left = controlls[2];
		right = controlls[3];
	}

	class playListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			//while (!(0.0 + ((int) playerPosX) == playerPosX && 0.0 + ((int) playerPosY) == playerPosY)) {}
			//if (0.0 + ((int) playerPosX) == playerPosX && 0.0 + ((int) playerPosY) == playerPosY) {
				//playerPosXLast=(int)playerPosX;
				//playerPosYLast=(int)playerPosY;
			//if(playerPosX%1<0.5&&playerPosY%1<0.5) {
			if(true) {
				PosX=Math.round(PosX);
				PosY=Math.round(PosY);
				if (e.getKeyCode() == left) {
					VelX = -0.125;
					VelY = 0;
				}
				if (e.getKeyCode() == up) {
					VelY = -0.125;
					VelX = 0;
				}
				if (e.getKeyCode() == right) {
					VelX = 0.125;
					VelY = 0;
				}
				if (e.getKeyCode() == down) {
					VelY = 0.125;
					VelX = 0;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

	public cardinal velYtocard() {
		return (VelY > 0) ? cardinal.DOWN : cardinal.UP;
	}

	public cardinal velXtocard() {
		return (VelX > 0) ? cardinal.RIGHT : cardinal.LEFT;
	}

	@Override
	public void update() {
		if(PosX%1<0.09&&PosY%1<0.09) {
			cardinal[] directionsToGo = pipe
					.getBlockages(Game.currentBoard.getCharAtDefulat((int)Math.round(PosX),(int)Math.round(PosY)));

			for (cardinal c : directionsToGo) {
				if (c == cardinal.DOWN && velYtocard() == cardinal.DOWN) {
					VelY = 0;
				}
				if (c == cardinal.UP && velYtocard() == cardinal.UP) {
					VelY = 0;
				}
			
				
				if (c == cardinal.LEFT && velXtocard() == cardinal.LEFT) {
					VelX = 0;
				}
				if (c == cardinal.RIGHT && velXtocard() == cardinal.RIGHT) {
					VelX = 0;
				}
			}
		}
		

		PosX += VelX;
		PosY += VelY;
		Game.currentBoard.visit((int) PosX, (int) PosY, playerColor);
		score += Game.currentBoard.score((int) PosX, (int) PosY);

	}

	@Override
	public void raster(Graphics2D g2d) {
		g2d.setColor(playerColor);
		g2d.fillOval((int) (PosX * Game.currentBoard.squarewidth), (int) (PosY * Game.currentBoard.squarewidth),
				Game.currentBoard.squarewidth, Game.currentBoard.squarewidth);
		g2d.setFont(new Font("Consolas", Font.BOLD, 100));
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(10, 976, 20 + 60 * (("" + score).length()), 100, 10, 10);
		g2d.setColor(Color.BLACK);
		g2d.fillRoundRect(20, 986, 20 + 60 * (("" + score).length()) - 20, 80, 10, 10);
		g2d.setColor(Color.WHITE);
		g2d.drawString("" +score, 20, 1056);
	}
}
