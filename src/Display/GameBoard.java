package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class GameBoard implements Displayable {
	public int squarewidth = 40;
	public int boardWidth;
	public int boardHeight;
	public int density = 500;

	public char[] BoardString;
	public Color[] visited;
	public boolean[] bubbles;

	Graph gameGR;
	WeightedQuickUnionUF gameQU;

	public GameBoard(int boardWidthIn, int boardHeightIn) {
		boardWidth = boardWidthIn;
		boardHeight = boardHeightIn;
		BoardString = new char[boardHeight * (boardWidth)];
		visited = new Color[boardHeight * (boardWidth)];

		bubbles = new boolean[boardHeight * (boardWidth)];
		for (int t = 0; t < (boardHeight * (boardWidth)); t++) {
			bubbles[t] = false;
		}

	}

	public void visit(int x, int y, Color c) {
		visited[y * (boardWidth) + x] = c;

	}

	public int score(int x, int y) {
		if (bubbles[y * (boardWidth) + x])
			return 0;
		bubbles[y * (boardWidth) + x] = true;
		return 1;
	}

	public boolean GetScoreAt(int x, int y) {
		return bubbles[y * (boardWidth) + x];
	}

	public Color GetVisitColor(int x, int y) {
		return visited[y * (boardWidth) + x];
	}

	@Override
	public void update(GameBoard laBoard) {
	}

	@Override
	public void raster(Graphics2D g2d) {

		g2d.scale(.8, .8);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, boardWidth * squarewidth + 200, boardHeight * squarewidth + 200);

		
		int lineN = 0;
		int ovalSize = 10;
		String rastersting = new String(BoardString);
		for (int i = 0; i < boardHeight; i++) {
			// String line = rastersting.substring(i * boardWidth, (i + 1) * boardWidth);
			// g2d.drawString(line, 0, 70 + (60 * (lineN++)));
			String c = rastersting.substring(i * boardWidth, (i + 1) * boardWidth);
			for (int u = 0; u < boardWidth; u++) {
				// g2d.setColor(GetVisitColor(u, i));
				g2d.setColor(Color.BLUE);
				// g2d.drawString("" + c.charAt(u), (int) (-2 + u * 33), 70 + (60 * (lineN)));
				pipe.drawSquares(c.charAt(u), u * squarewidth, (squarewidth * (lineN)), squarewidth, squarewidth, g2d);
				if (!GetScoreAt(u, i)) {
					g2d.setColor(Color.WHITE);
					g2d.fillOval((int) ((u + .5) * squarewidth) - ovalSize / 2,
							(int) (squarewidth * (lineN + .5)) - ovalSize / 2, ovalSize, ovalSize);
				}
			}
			lineN++;
		}

	}
	
	
	public void gameOverScreen(Graphics2D g2d) {
		int alpha = 20; // 50% transparent
		Color myColor = new Color(0, 0, 0, alpha);
		g2d.setColor(myColor);
		g2d.fillRect(0,0,783, 770);
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("Consolas", Font.BOLD, 40));
		g2d.drawString("GAME OVER!", 275, 350);
		
		
	}

	public void putCharAtDefulat(char val, int x, int y) {
		BoardString[y * (boardWidth) + x] = val;
	}

	public char getCharAtDefulat(int x, int y) {
		return BoardString[y * (boardWidth) + x];
	}

	public void createBoard() {
		for (int y = 0; y < boardHeight; y++) {
			inner: for (int x = 0; x < boardWidth; x++) {
				if (x == 0 && y == 0) {
					putCharAtDefulat('╔', x, y);
					continue inner;
				}

				if (x == boardWidth - 1 && y == 0) {
					putCharAtDefulat('╗', x, y);
					continue inner;
				}

				if (x == boardWidth - 1 && y == boardHeight - 1) {
					putCharAtDefulat('╝', x, y);
					continue inner;
				}

				if (x == 0 && y == boardHeight - 1) {
					putCharAtDefulat('╚', x, y);
					continue inner;
				}

				if (x == 0) {
					putCharAtDefulat('╠', x, y);
					continue inner;
				}

				if (x == boardWidth - 1) {
					putCharAtDefulat('╣', x, y);
					continue inner;
				}

				if (y == 0) {
					putCharAtDefulat('╦', x, y);
					continue inner;
				}

				if (y == boardHeight - 1) {
					putCharAtDefulat('╩', x, y);
					continue inner;
				}

				putCharAtDefulat('╬', x, y);

			}

		}

		for (int i = 0; i < density; i++) {
			int randoY = (int) (Math.random() * (boardHeight - 1));
			int randoX = (int) (Math.random() * (boardWidth));

			if (pipe.getDegree(getCharAtDefulat(randoX, randoY)) > 2
					&& pipe.getDegree(getCharAtDefulat(randoX, randoY + 1)) > 2) {

				putCharAtDefulat(pipe.removeConnection(getCharAtDefulat(randoX, randoY), cardinal.DOWN), randoX,
						randoY);
				putCharAtDefulat(pipe.removeConnection(getCharAtDefulat(randoX, randoY + 1), cardinal.UP), randoX,
						randoY + 1);
			}

			randoY = (int) (Math.random() * (boardHeight));
			randoX = (int) (Math.random() * (boardWidth - 1));

			if (pipe.getDegree(getCharAtDefulat(randoX, randoY)) > 2
					&& pipe.getDegree(getCharAtDefulat(randoX + 1, randoY)) > 2) {

				putCharAtDefulat(pipe.removeConnection(getCharAtDefulat(randoX, randoY), cardinal.RIGHT), randoX,
						randoY);
				putCharAtDefulat(pipe.removeConnection(getCharAtDefulat(randoX + 1, randoY), cardinal.LEFT), randoX + 1,
						randoY);
			}
		}

		gameGR = new Graph(boardHeight * boardWidth);
		gameQU = new WeightedQuickUnionUF(boardHeight * boardWidth);
		for (int y = 0; y < boardHeight; y++) {
			for (int x = 0; x < boardWidth; x++) {
				for (cardinal c : pipe.getConnections(getCharAtDefulat(x, y))) {
					if (c == cardinal.LEFT) {
						gameGR.addEdge((boardWidth * y) + x, (boardWidth * y) + x - 1);
						gameQU.union((boardWidth * y) + x, (boardWidth * y) + x - 1);
					}
					if (c == cardinal.DOWN) {
						gameGR.addEdge((boardWidth * y) + x, (boardWidth * (y + 1)) + x);
						gameQU.union((boardWidth * y) + x, (boardWidth * (y + 1)) + x);
					}
					if (c == cardinal.RIGHT) {
						gameGR.addEdge((boardWidth * y) + x, (boardWidth * y) + x + 1);
						gameQU.union((boardWidth * y) + x, (boardWidth * y) + x + 1);
					}
					if (c == cardinal.UP) {
						gameGR.addEdge((boardWidth * y) + x, (boardWidth * (y - 1)) + x);
						gameQU.union((boardWidth * y) + x, (boardWidth * (y - 1)) + x);
					}
				}
				visit(x, y, Color.BLUE);
			}
		}
		System.out.println(gameQU.count());
		if (gameQU.count() > 1) {
			createBoard();
		}
	}

}