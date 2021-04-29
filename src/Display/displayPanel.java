package Display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class displayPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	Displayable[] characters;
	GameBoard Board;
	ScoreBoard scoreboard;
	
	// public String DisplayString = new String("");

	@Override
	public void paint(Graphics g) {
		if(Game.GAME==gameState.pregame) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			Board.raster(g2d);
			for (Displayable character : characters) {
				//character.update(Board);
				character.raster(g2d);
			}
		}
		
		
		if(Game.GAME==gameState.scoreboard) {
			super.paint(g);
			scoreboard.drawLeaders((Graphics2D) g);
		}
		
		if(Game.GAME==gameState.settings) {
			super.paint(g);
			Settings.displaySettings((Graphics2D) g);
		}
		
		if (Game.GAME==gameState.game) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			Board.raster(g2d);
			for (Displayable character : characters) {
				character.update(Board);
				character.raster(g2d);
			}
		}
		if(Game.GAME==gameState.lost) {
			Board.gameOverScreen((Graphics2D) g);
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
