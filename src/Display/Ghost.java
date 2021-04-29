package Display;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

public class Ghost implements Displayable {

	Finder findAlg;
	double posX, posY;
	double velX, velY;
	Color ghostColor;
	Player target;
	GameBoard theBoard;

	public Ghost(Finder alg, int[][] box, Player thetarg, GameBoard laBoard, Color c) {
		findAlg = alg;
		posX = (int) (box[0][0] + Math.random() * (box[0][1] - box[0][0]));
		posY = (int) (box[0][0] + Math.random() * (box[0][1] - box[0][0]));
		ghostColor = c;
		target = thetarg;
		theBoard = laBoard;
	}

	@Override
	public void update(GameBoard laBoard) {
		if (posX % 1 < 0.09 && posY % 1 < 0.09) {
			posX=Math.round(posX);
			posY=Math.round(posY);
			cardinal choosen = findAlg.move((int)Math.round(posX), (int)Math.round(posY), (int) target.playerPosX, (int) target.playerPosY);
			if (choosen == cardinal.LEFT) {
				velX=-0.125;
				velY=0;
			}
			if (choosen == cardinal.DOWN) {
				velY=0.125;
				velX=0;
			}
			if (choosen == cardinal.RIGHT) {
				velX=0.125;
				velY=0;
			}
			if (choosen == cardinal.UP) {
				velY=-0.125;
				velX=0;
			}
			
		}
		
		posY+=velY;
		posX+=velX;
		
		
		
		
		if (Math.abs(posX - target.playerPosX)<1 && Math.abs(posY - target.playerPosY)<1) {
			try {
				Game.gameOver();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void raster(Graphics2D g2d) {
		g2d.setColor(ghostColor);
		g2d.fillOval((int)(posX * theBoard.squarewidth), (int)(posY * theBoard.squarewidth), theBoard.squarewidth,
				theBoard.squarewidth);

		g2d.fillRect((int)(posX * theBoard.squarewidth), (int) ((posY + .5) * theBoard.squarewidth), theBoard.squarewidth,
				theBoard.squarewidth / 2);

	}

}
