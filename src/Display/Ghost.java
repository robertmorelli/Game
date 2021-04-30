package Display;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.net.URISyntaxException;

public class Ghost implements Displayable {

	Finder findAlg;
	double posX, posY, velX, velY;
	Color ghostColor;

	public Ghost(Finder alg, Color c) {
		findAlg = alg;
		posX = (int) (Game.ghostSpawnBox[0][0] + Math.random() * (Game.ghostSpawnBox[0][1] - Game.ghostSpawnBox[0][0]));
		posY = (int) (Game.ghostSpawnBox[0][0] + Math.random() * (Game.ghostSpawnBox[0][1] - Game.ghostSpawnBox[0][0]));
		ghostColor = c;

	}

	@Override
	public void update() throws URISyntaxException {
		if (posX % 1 < 0.09 && posY % 1 < 0.09) {
			posX = Math.round(posX);
			posY = Math.round(posY);
			cardinal choosen = findAlg.move((int) Math.round(posX), (int) Math.round(posY), (int) Game.mainCharacter.PosX,
					(int) Game.mainCharacter.PosY);
			if (choosen == cardinal.LEFT) {
				velX = -0.125;
				velY = 0;
			}
			if (choosen == cardinal.DOWN) {
				velY = 0.125;
				velX = 0;
			}
			if (choosen == cardinal.RIGHT) {
				velX = 0.125;
				velY = 0;
			}
			if (choosen == cardinal.UP) {
				velY = -0.125;
				velX = 0;
			}

		}

		posY += velY;
		posX += velX;

		if (Math.abs(posX - Game.mainCharacter.PosX) < 1 && Math.abs(posY - Game.mainCharacter.PosY) < 1) {
			try {
				Game.gameOver();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void raster(Graphics2D g2d) {
		g2d.setColor(ghostColor);
		g2d.fillOval((int) (posX * Game.currentBoard.squarewidth), (int) (posY * Game.currentBoard.squarewidth),
				Game.currentBoard.squarewidth, Game.currentBoard.squarewidth);

		g2d.fillRect((int) (posX * Game.currentBoard.squarewidth), (int) ((posY + .5) * Game.currentBoard.squarewidth),
				Game.currentBoard.squarewidth, Game.currentBoard.squarewidth / 2);

	}

}
