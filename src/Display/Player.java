package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements Displayable {

	GameBoard theBoard;
	public double playerVelX = 0;
	public double playerVelY = 0;
	public double playerPosY = 0;
	public double playerPosX = 0;

	public int playerPosYLast = 0;
	public int playerPosXLast = 0;
	
	public int boardWidth = 0;
	public int boardHeight = 0;

	public int score = 0;
	int up;
	int down;
	int left;
	int right;
	playListener listening;

	Color playerColor;

	public Player(int[] controlls, int boardWidthIn, int boardHeightIn, Color color, GameBoard laBoard) {

		int whichFrame = 0;
		up = controlls[0];
		down = controlls[1];
		left = controlls[2];
		right = controlls[3];
		listening = new playListener();
		boardWidth = boardWidthIn;
		boardHeight = boardHeightIn;
		playerColor = color;
		theBoard = laBoard;
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
				playerPosX=Math.round(playerPosX);
				playerPosY=Math.round(playerPosY);
				if (e.getKeyCode() == left) {
					playerVelX = -0.125;
					playerVelY = 0;
				}
				if (e.getKeyCode() == up) {
					playerVelY = -0.125;
					playerVelX = 0;
				}
				if (e.getKeyCode() == right) {
					playerVelX = 0.125;
					playerVelY = 0;
				}
				if (e.getKeyCode() == down) {
					playerVelY = 0.125;
					playerVelX = 0;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

	public cardinal velYtocard() {
		return (playerVelY > 0) ? cardinal.DOWN : cardinal.UP;
	}

	public cardinal velXtocard() {
		return (playerVelX > 0) ? cardinal.RIGHT : cardinal.LEFT;
	}

	@Override
	public void update(GameBoard laBoard) {
		if(playerPosX%1<0.09&&playerPosY%1<0.09) {
			cardinal[] directionsToGo = pipe
					.getBlockages(laBoard.getCharAtDefulat((int)Math.round(playerPosX),(int)Math.round(playerPosY)));

			for (cardinal c : directionsToGo) {
				if (c == cardinal.DOWN && velYtocard() == cardinal.DOWN) {
					playerVelY = 0;
				}
				if (c == cardinal.UP && velYtocard() == cardinal.UP) {
					playerVelY = 0;
				}
			
				
				if (c == cardinal.LEFT && velXtocard() == cardinal.LEFT) {
					playerVelX = 0;
				}
				if (c == cardinal.RIGHT && velXtocard() == cardinal.RIGHT) {
					playerVelX = 0;
				}
			}
		}
		

		playerPosX += playerVelX;
		playerPosY += playerVelY;
		laBoard.visit((int) playerPosX, (int) playerPosY, playerColor);
		score += laBoard.score((int) playerPosX, (int) playerPosY);

	}

	@Override
	public void raster(Graphics2D g2d) {
		g2d.setColor(playerColor);
		g2d.fillOval((int) (playerPosX * theBoard.squarewidth), (int) (playerPosY * theBoard.squarewidth),
				theBoard.squarewidth, theBoard.squarewidth);
		g2d.setFont(new Font("Consolas", Font.BOLD, 100));
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(10, 976, 20 + 60 * (("" + score).length()), 100, 10, 10);
		g2d.setColor(Color.BLACK);
		g2d.fillRoundRect(20, 986, 20 + 60 * (("" + score).length()) - 20, 80, 10, 10);
		g2d.setColor(Color.WHITE);
		g2d.drawString("" + score, 20, 1056);
	}
}
