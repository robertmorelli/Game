package Display;

import edu.princeton.cs.algs4.Graph;

public class RandomFinder implements Finder {

	
	Graph gameG;
	GameBoard b;

	public RandomFinder(GameBoard g) {
		gameG = g.gameGR;
		b = g;
	}
	@Override
	public cardinal move(int x1, int y1, int x2, int y2) {
		cardinal[] directions=pipe.getConnections( b.getCharAtDefulat(x1, y1));
		return directions[(int)(Math.random()*directions.length)];
	}

}
