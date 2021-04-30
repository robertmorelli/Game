package Display;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.net.URISyntaxException;

import javax.swing.JPanel;


/**
 * handles what to render on the screen
 * 
 * @author Kody Berry
 * @author Robert Morelli
 *
 */
public class displayPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	Displayable[] characters;

	//ScoreBoardDisplay scoreboard;
	
	// public String DisplayString = new String("");

	@Override
	public void paint(Graphics g) {
		if(Game.GAME==gameState.pregame) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			Game.currentBoard.raster(g2d);
			for (Displayable character : characters) {
				//character.update(Board);
				character.raster(g2d);
			}
		}
		
		
		if(Game.GAME==gameState.scoreboard) {
			super.paint(g);
			Game.leaderBoard.drawLeaders((Graphics2D) g);
		}
		
		if(Game.GAME==gameState.settings) {
			super.paint(g);
			Settings.displaySettings((Graphics2D) g);
		}
		
		if (Game.GAME==gameState.game) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			Game.currentBoard.raster(g2d);
			for (Displayable character : characters) {
				try {
					character.update();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
				character.raster(g2d);
			}
		}
		if(Game.GAME==gameState.lost) {
			Game.currentBoard.gameOverScreen((Graphics2D) g);
			
			Game.restButton.raster((Graphics2D) g);
		}
		/*
		 * Kernel kernel = new Kernel(7, 7, new float[] { 0f/1003f ,0f/1003f ,1f/1003f
		 * ,2f/1003f ,1f/1003f ,0f/1003f ,0f/1003f, 0f/1003f ,3f/1003f ,13/1003f
		 * ,22/1003f ,13f/1003f ,3f/1003f ,0f/1003f, 1f/1003f ,13f/1003f ,59f/1003f
		 * ,97f/1003f ,59f/1003f ,13f/1003f ,1f/1003f, 2f/1003f ,22f/1003f ,97f/1003f
		 * ,159f/1003f ,97f/1003f ,22f/1003f ,2f/1003f, 1f/1003f ,13f/1003f ,59f/1003f
		 * ,97f/1003f ,59f/1003f ,13f/1003f ,1f/1003f, 0f/1003f ,3f/1003f ,13/1003f
		 * ,22/1003f ,13f/1003f ,3f/1003f ,0f/1003f, 0f/1003f ,0f/1003f ,1f/1003f
		 * ,2f/1003f ,1f/1003f ,0f/1003f ,0f/1003f }
		 * 
		 * );
		 * 
		 * 
		 * //Graphics2D g2d = image.createGraphics();
		 * 
		 * BufferedImage image = new BufferedImage(800, 800,
		 * BufferedImage.TYPE_INT_ARGB); new float[] {
		 * 
		 * 
		 * 1f / 9f, 1f / 9f, 1f / 9f,
		 * 
		 * 1f / 9f, 1f / 9f, 1f / 9f,
		 * 
		 * 1f / 9f, 1f / 9f, 1f / 9f } BufferedImageOp op = new ConvolveOp(kernel);
		 * 
		 * //image = op.filter(image, null); //image = op.filter(image, null); //image =
		 * op.filter(image, null);
		 * 
		 * g2d.drawImage(image, 10, 10, null); BufferedImage fi=g2d.BufferedImage
		 */

	}

}
