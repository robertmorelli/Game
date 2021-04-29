package Display;

import java.util.Iterator;

import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DFSFinder implements Finder {
	Graph gameG;
	GameBoard b;

	int oldx1 = -100;
	int oldy1 = -100;
	int oldx2 = -100;
	int oldy2 = -100;
	Iterator<Integer> currentPath;

	public DFSFinder(GameBoard g) {
		gameG = g.gameGR;
		b = g;
	}

	@Override
	public cardinal move(int x1, int y1, int x2, int y2) {
		//(oldx1 - x1) + (oldy1 - y1) +
		if (Math.abs(( (oldx2 - x2) + (oldy2 - y2))) > 8) {
			DepthFirstPaths df = new DepthFirstPaths(gameG, (y1 * b.boardWidth) + x1);
			currentPath = df.pathTo((y2 * b.boardWidth) + x2).iterator();
		}
		oldx1 = x1;
		oldy1 = y1;
		oldx2 = x2;
		oldy2 = y2;
		Queue<Integer> boyo=new Queue<Integer>();
		if (!currentPath.hasNext()) {
			DepthFirstPaths df = new DepthFirstPaths(gameG, (y1 * b.boardWidth) + x1);
			for(int t:df.pathTo((y2 * b.boardWidth) + x2)) {
				boyo.enqueue(t);
			}
			currentPath = boyo.iterator();
			return pipe.getConnections(b.getCharAtDefulat(x1, y1))[0];
		}
		int i = currentPath.next();
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

		return null;
	}
}

