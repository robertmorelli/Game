package Display;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;


public class Game {

	public static final int[] WASD=new int[] { 87, 83, 65, 68 };
	public static final int[] ARROWS=new int[] { 38, 40, 37, 39 };
	public static int keyoption=0;
	public static int[][] keyoptions=new int[][]{WASD,ARROWS};
	public static Player mainChar;
	public static Ghost[] Ghosts;
	public static difficulties dif;
	public static GameBoard gameB;
	static char theChar = 'z';
	public static gameState GAME = gameState.pregame;
	// static char[] defualtBoard = new char[boardHeight * (boardWidth + 1)];

	public static void main(String[] args) throws InterruptedException {
		dif=difficulties.easy;
		
		JFrame jf = new JFrame();
		displayPanel jp2 = new displayPanel();
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		startButton start= new startButton();
		//jp2.addMouseListener(start.listening);
		
		ScoreBoard theScore= new ScoreBoard();
		jp2.scoreboard=theScore;
		
		GameBoard theBoard = new GameBoard(24,24);
		theBoard.createBoard();
		gameB=theBoard;
		Player me = new Player(WASD, theBoard.boardWidth, theBoard.boardHeight, Color.YELLOW,theBoard);
		mainChar=me;
		int[][] spawnBox=new int[][] { { 12, 24 }, { 12, 24 } };
		Ghost BLINKY = new Ghost(new DFSFinder(theBoard),spawnBox , me,theBoard, Color.RED);
		Ghost PINKY = new Ghost(new DFSFinder(theBoard), spawnBox, me,theBoard, Color.PINK);
		Ghost INKY = new Ghost(new DFSFinder(theBoard), spawnBox, me,theBoard, Color.CYAN);
		Ghost CLYDE = new Ghost(new DFSFinder(theBoard), spawnBox, me,theBoard, Color.ORANGE);
		Ghosts=new Ghost[] {BLINKY,PINKY,INKY,CLYDE};
		
		jp2.Board = theBoard;
		Settings settings=new Settings();
		jp2.characters = new Displayable[] { me,settings,theScore,BLINKY,PINKY,INKY,CLYDE,start };
		jf.addKeyListener(me.listening);
		
		
		jf.addMouseListener(new mouseListener(new listeningtostuff[] {start,theScore,settings}));
		jf.add(jp2);
		jf.setSize(783, 906);
		jf.setVisible(true);

		while (true) {
			jp2.repaint();
		}
	}

	public static void gameOver() {
		GAME = gameState.lost;
	}

}








class DSTRAFinder implements Finder {

	@Override
	public cardinal move(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return null;
	}

}




class mouseListener implements MouseListener{
	public listeningtostuff[] tocall;
	public mouseListener(listeningtostuff[] tocallin) {
		tocall=tocallin;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("hi");
		for(listeningtostuff phone:tocall) {
			phone.mouseClicked(e);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}




class startButton implements Displayable,listeningtostuff{

	
	//public startListener listening;
	//public startButton() {
	//	listening=new startListener();
	//}
	
	@Override
	public void update(GameBoard laBoard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void raster(Graphics2D g2d) {
		if(Game.GAME==gameState.pregame) {
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Consolas", Font.BOLD, 40));
			g2d.drawString("start", 300, 1000);
		}
		if(Game.GAME==gameState.game) {
			
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		//System.out.println("hi2");
		
			Point p = e.getPoint();
			int x = p.x;
			int y = p.y;
			
			if (x > 250 && x < 330 && y > 800 && y < 830) {
				if(Game.GAME==gameState.pregame) {
					Game.GAME=gameState.game;
				}

			}

		}

		
	
}










