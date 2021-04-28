package Display;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;

public class BFSFinder implements Finder {

	Graph gameG;
	GameBoard b;

	public BFSFinder(GameBoard g) {
		gameG = g.gameGR;
		b = g;
	}

	@Override
	public cardinal move(int x1, int y1, int x2, int y2) {
		BreadthFirstPaths df = new BreadthFirstPaths(gameG, (y1 * b.boardWidth) + x1);
		for (int i : df.pathTo((y2 * b.boardWidth) + x2)) {
			if (i == ((y1 - 1) * b.boardWidth) + x1) {
				return cardinal.UP;
			}
			if (i == ((y1 + 1) * b.boardWidth) + x1) {
				return cardinal.DOWN;
			}
			if (i == ((y1) * b.boardWidth) + x1 - 1) {
				return cardinal.LEFT;
			}
			if (i == ((y1) * b.boardWidth) + x1 + 1) {
				return cardinal.RIGHT;
			}
		}
		return null;
	}
}
