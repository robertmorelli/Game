package Display;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ghost implements Displayable {

	Finder findAlg;
	int posX, posY;
	Color ghostColor;
	Player target;
	GameBoard theBoard;
	public Ghost(Finder alg, int[][] box, Player thetarg,GameBoard laBoard, Color c) {
		findAlg = alg;
		posX = (int) (box[0][0] + Math.random() * (box[0][1] - box[0][0]));
		posY = (int) (box[0][0] + Math.random() * (box[0][1] - box[0][0]));
		ghostColor = c;
		target = thetarg;
		theBoard=laBoard;
	}

	@Override
	public void update(GameBoard laBoard) {
		cardinal choosen = findAlg.move(posX, posY, target.playerPosX, target.playerPosY);
		if (choosen == cardinal.LEFT) {
			posX--;
		}
		if (choosen == cardinal.DOWN) {
			posY++;
		}
		if (choosen == cardinal.RIGHT) {
			posX++;
		}
		if (choosen == cardinal.UP) {
			posY--;
		}
		if (posX == target.playerPosX && posY == target.playerPosY) {
			Game.gameOver();
		}

	}

	@Override
	public void raster(Graphics2D g2d) {
		g2d.setColor(ghostColor);
		g2d.fillOval(posX * theBoard.squarewidth, posY * theBoard.squarewidth, theBoard.squarewidth, theBoard.squarewidth);
		
		g2d.fillRect(posX * theBoard.squarewidth, (int)((posY+.5) *theBoard.squarewidth), theBoard.squarewidth, theBoard.squarewidth/2);

	}

}
