package Display;

import java.security.InvalidAlgorithmParameterException;
import java.util.Iterator;

import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.Queue;

public class RandomFinder implements Finder {

	int oldx1,oldy1,oldx2,oldy2;
	Iterator<Integer> currentPath;

	

	@Override
	public cardinal move(int x1, int y1, int x2, int y2) {
		
		x2+=(int)(Math.random()*24)-12;
		y2+=(int)(Math.random()*24)-12;
		x2=x2>23?23:x2;
		y2=y1>23?23:y2;
		x2=x2<0?23:x2;
		y2=y2<0?23:y2;
		//System.out.println(x2+" : "+y2);
		if (Math.abs(( (oldx1 - x1) + (oldy1 - y1))) > 8) {
			DepthFirstPaths df = new DepthFirstPaths(Game.currentBoard.gameGR, (y1 * Game.currentBoard.boardWidth) + x1);
			
			currentPath = df.pathTo((y2 * Game.currentBoard.boardWidth) + x2).iterator();
		}
		oldx1 = x1;
		oldy1 = y1;
		
		Queue<Integer> boyo=new Queue<Integer>();
		if (!currentPath.hasNext()) {
			DepthFirstPaths df = new DepthFirstPaths(Game.currentBoard.gameGR, (y1 * Game.currentBoard.boardWidth) + x1);
			for(int t:df.pathTo((y2 * Game.currentBoard.boardWidth) + x2)) {
				boyo.enqueue(t);
			}
			currentPath = boyo.iterator();
			return pipe.getConnections(Game.currentBoard.getCharAtDefulat(x1, y1))[0];
		}
		oldx1=x1;
		oldy1=y1;
		int i = currentPath.next();
		//System.out.println((y1 - 1) * b.boardWidth + x1);
		//System.out.println(i);
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
		try {
		throw new InvalidAlgorithmParameterException("");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
}