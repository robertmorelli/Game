package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFrame;



/**
 * Game class holds static game information and hosts the main()
 * 
 * @author Kody Berry
 * @author Robert Morelli
 *
 */
public class Game {

	public static final int[] WASD = new int[] { 87, 83, 65, 68 };
	public static final int[] ARROWS = new int[] { 38, 40, 37, 39 };
	public static int keyoption = 0;
	public static int[][] keyoptions = new int[][] { WASD, ARROWS };
	public static Player mainCharacter;
	public static Ghost[] Ghosts;
	public static difficulties difficulty = difficulties.easy;
	public static GameBoard currentBoard;
	public static gameState GAME = gameState.pregame;
	public static scoreBoard leaderBoardIO;
	public static restartButton restButton;
	public static int[][] ghostSpawnBox = new int[][] { { 12, 24 }, { 12, 24 } };
	public static ScoreBoardDisplay leaderBoard;
	public static startButton start;
	public static JFrame MainPanel;
	public static displayPanel gameDisplay;
	public static Settings settings;

	public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
		while (true) {
			MainPanel = new JFrame();
			gameDisplay = new displayPanel();
			
			leaderBoardIO = new scoreBoard();
			leaderBoardIO.ReadNamesAndScoreToFile();
			ScoreBoardDisplay.oraganisescores();
			
			MainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			start = new startButton();
			leaderBoard = new ScoreBoardDisplay();
			restButton = new restartButton();
			currentBoard = new GameBoard(24, 24);
			currentBoard.createBoard();
			mainCharacter = new Player(WASD, Color.YELLOW);
			
			settings = new Settings();
			MainPanel.addKeyListener(mainCharacter.listening);
			MainPanel.add(gameDisplay);
			MainPanel.setSize(783, 906);
			MainPanel.setVisible(true);
			currentBoard.createBoard();
			Ghost BLINKY = new Ghost(new DFSFinder(), Color.RED);
			Ghost PINKY = new Ghost(new DFSFinder(), Color.PINK);
			Ghost INKY = new Ghost(new DFSFinder(), Color.CYAN);
			Ghost CLYDE = new Ghost(new DFSFinder(), Color.ORANGE);
			Ghosts = new Ghost[] { BLINKY, PINKY, INKY, CLYDE };

			gameDisplay.characters = new Displayable[] { mainCharacter, settings, leaderBoard, BLINKY, PINKY, INKY,
					CLYDE, start };

			MainPanel.addMouseListener(
					new mouseListener(new listeningtostuff[] { start, leaderBoard, settings, restButton }));
			Game.mainCharacter.setKeys(Game.keyoptions[Game.keyoption]);
			if (Game.difficulty == difficulties.peaceful) {
				for (Ghost g : Game.Ghosts) {
					g.findAlg = new RandomFinder();
				}
			}
			if (Game.difficulty == difficulties.easy) {
				for (Ghost g : Game.Ghosts) {
					g.findAlg = new DFSFinder();
				}
			}
			if (Game.difficulty == difficulties.impossible) {
				for (Ghost g : Game.Ghosts) {
					g.findAlg = new BFSFinder();
				}
			}
			while (Game.GAME != gameState.restarting) {
				gameDisplay.repaint();
			}
			Game.GAME = gameState.pregame;

			MainPanel.dispose();
		}
	}

	public static void gameOver() throws IOException, URISyntaxException {
		leaderBoardIO.WriteNamesAndScoreToFile();
		GAME = gameState.lost;
	}

}


class mouseListener implements MouseListener {
	public listeningtostuff[] tocall;

	public mouseListener(listeningtostuff[] tocallin) {
		tocall = tocallin;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (listeningtostuff phone : tocall) {
			phone.mouseClicked(e);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}

class startButton implements Displayable, listeningtostuff {

	@Override
	public void update() {
	}

	@Override
	public void raster(Graphics2D g2d) {
		if (Game.GAME == gameState.pregame) {
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Consolas", Font.BOLD, 40));
			g2d.drawString("start", 300, 1000);
		}
		if (Game.GAME == gameState.game) {

		}

	}

	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		int x = p.x;
		int y = p.y;

		if (x > 250 && x < 330 && y > 800 && y < 830) {
			if (Game.GAME == gameState.pregame) {
				Game.GAME = gameState.game;
			}

		}
	}
}























