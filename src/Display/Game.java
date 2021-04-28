package Display;

import javax.swing.*;

import java.awt.Color;

import java.util.concurrent.TimeUnit;


public class Game {

	public static final int[] WASD=new int[] { 87, 83, 65, 68 };
	public static final int[] ARROWS=new int[] { 38, 40, 37, 39 };
	static char theChar = 'z';
	static boolean GAME = true;
	// static char[] defualtBoard = new char[boardHeight * (boardWidth + 1)];

	public static void main(String[] args) throws InterruptedException {
		GameBoard theBoard = new GameBoard(24,24);
		theBoard.createBoard();

		Player me = new Player(WASD, theBoard.boardWidth, theBoard.boardHeight, Color.YELLOW,theBoard);

		int[][] spawnBox=new int[][] { { 12, 24 }, { 12, 24 } };
		Ghost BLINKY = new Ghost(new DFSFinder(theBoard),spawnBox , me,theBoard, Color.RED);
		Ghost PINKY = new Ghost(new BFSFinder(theBoard), spawnBox, me,theBoard, Color.PINK);
		Ghost INKY = new Ghost(new BFSFinder(theBoard), spawnBox, me,theBoard, Color.CYAN);
		Ghost CLYDE = new Ghost(new DFSFinder(theBoard), spawnBox, me,theBoard, Color.ORANGE);

		JFrame jf = new JFrame();
		displayPanel jp2 = new displayPanel();
		jp2.Board = theBoard;
		jp2.characters = new Displayable[] { me,BLINKY,PINKY,INKY,CLYDE };
		jf.addKeyListener(me.listening);

		jf.add(jp2);
		jf.setSize(783, 906);
		jf.setVisible(true);

		while (true) {
			TimeUnit.MILLISECONDS.sleep(110);
			jp2.repaint();
		}
	}

	public static void gameOver() {
		GAME = false;
	}

}








class DSTRAFinder implements Finder {

	@Override
	public cardinal move(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return null;
	}

}