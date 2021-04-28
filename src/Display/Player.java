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
	public int playerPosY = 0;
	public int playerPosX = 0;

	public int boardWidth = 0;
	public int boardHeight = 0;

	public int score=0;
	int up;
	int down;
	int left;
	int right;
	playListener listening;

	Color playerColor;

	public Player(int[] controlls, int boardWidthIn, int boardHeightIn, Color color,GameBoard laBoard) {
		up = controlls[0];
		down = controlls[1];
		left = controlls[2];
		right = controlls[3];
		listening = new playListener();
		boardWidth = boardWidthIn;
		boardHeight = boardHeightIn;
		playerColor = color;
		theBoard=laBoard;
	}

	class playListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == left) {
				playerVelX = -1;
				playerVelY = 0;
			}
			if (e.getKeyCode() == up) {
				playerVelY = -1;
				playerVelX = 0;
			}
			if (e.getKeyCode() == right) {
				playerVelX = 1;
				playerVelY = 0;
			}
			if (e.getKeyCode() == down) {
				playerVelY = 1;
				playerVelX = 0;
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
		cardinal[] directionsToGo = pipe.getBlockages(laBoard.getCharAtDefulat(playerPosX, playerPosY));

		for (cardinal c : directionsToGo) {
			if (c == cardinal.DOWN && velYtocard() == cardinal.DOWN) {
				playerVelY = 0.5;
			}
			if (c == cardinal.UP && velYtocard() == cardinal.UP) {
				playerVelY = 0.5;
			}
			if (c == cardinal.LEFT && velXtocard() == cardinal.LEFT) {
				playerVelX = 0.5;
			}
			if (c == cardinal.RIGHT && velXtocard() == cardinal.RIGHT) {
				playerVelX = 0.5;
			}
		}

		playerPosX += (int) playerVelX;
		playerPosY += (int) playerVelY;
		laBoard.visit(playerPosX, playerPosY, playerColor);
		score+=laBoard.score(playerPosX, playerPosY);

	}

	@Override
	public void raster(Graphics2D g2d) {
		g2d.setColor(playerColor);
		g2d.fillOval(playerPosX * theBoard.squarewidth, playerPosY * theBoard.squarewidth, theBoard.squarewidth, theBoard.squarewidth);
		g2d.setFont(new Font("Consolas", Font.BOLD, 100));
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(10, 976,  20+60*((""+score).length()),100, 10, 10);
		g2d.setColor(Color.BLACK);
		g2d.fillRoundRect(20, 986, 20+60*((""+score).length()) -20,80, 10, 10);
		g2d.setColor(Color.WHITE);
		g2d.drawString(""+score,20 , 1056);
	}
}
