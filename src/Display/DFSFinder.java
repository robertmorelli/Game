package Display;

import java.util.Iterator;

import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.Queue;


public class DFSFinder implements Finder {


	int oldx1 = -100;
	int oldy1 = -100;
	int oldx2 = -100;
	int oldy2 = -100;
	
	Iterator<Integer> currentPath;

	

	@Override
	public cardinal move(int x1, int y1, int x2, int y2) {
		if (Math.abs(( (oldx2 - x2) + (oldy2 - y2))) > 8) {
			DepthFirstPaths df = new DepthFirstPaths(Game.currentBoard.gameGR, (y1 * Game.currentBoard.boardWidth) + x1);
			currentPath = df.pathTo((y2 * Game.currentBoard.boardWidth) + x2).iterator();
		}
		oldx1 = x1;
		oldy1 = y1;
		oldx2 = x2;
		oldy2 = y2;
		Queue<Integer> boyo=new Queue<Integer>();
		if (!currentPath.hasNext()) {
			DepthFirstPaths df = new DepthFirstPaths(Game.currentBoard.gameGR, (y1 * Game.currentBoard.boardWidth) + x1);
			for(int t:df.pathTo((y2 * Game.currentBoard.boardWidth) + x2)) {
				boyo.enqueue(t);
			}
			currentPath = boyo.iterator();
			return pipe.getConnections(Game.currentBoard.getCharAtDefulat(x1, y1))[0];
		}
		int i = currentPath.next();
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

		return null;
	}
}

