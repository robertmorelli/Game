package Display;

import edu.princeton.cs.algs4.BreadthFirstPaths;

public class BFSFinder implements Finder {

	

	@Override
	public cardinal move(int x1, int y1, int x2, int y2) {
		BreadthFirstPaths df = new BreadthFirstPaths(Game.currentBoard.gameGR, (y1 * Game.currentBoard.boardWidth) + x1);
		for (int i : df.pathTo((y2 * Game.currentBoard.boardWidth) + x2)) {
			if (i == ((y1 - 1) * Game.currentBoard.boardWidth) + x1) {
				return cardinal.UP;
			}
			if (i == ((y1 + 1) * Game.currentBoard.boardWidth) + x1) {
				return cardinal.DOWN;
			}
			if (i == ((y1) * Game.currentBoard.boardWidth) + x1 - 1) {
				return cardinal.LEFT;
			}
			if (i == ((y1) * Game.currentBoard.boardWidth) + x1 + 1) {
				return cardinal.RIGHT;
			}
		}
		return null;
	}
}
