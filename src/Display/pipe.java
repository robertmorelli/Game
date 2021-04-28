package Display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class pipe {
	public static char[] pipes = new char[] { '┼', '╡', '╥', '╗', '╞', '═', '╔', '╦', '╨', '╝', '║', '╣', '╚', '╩', '╠',
			'╬' };

	public static char getChar(boolean up, boolean down, boolean left, boolean right) {
		return pipes[(up ? 8 : 0) | (right ? 4 : 0) | (down ? 2 : 0) | (left ? 1 : 0)];
	}

	public static int getDegree(char piper) {
		return Integer.bitCount((indexOf(piper)));
	}

	public static int indexOf(char piper) {
		int index = 0;
		for (char chr : pipes) {
			if (piper == chr)
				return index;
			else
				index++;
		}
		return -1;
	}

	public static boolean hasConnection(char piper, cardinal c) {
		int code = 0;
		if (c == cardinal.LEFT) {
			code = 1;
		}
		if (c == cardinal.DOWN) {
			code = 2;
		}
		if (c == cardinal.RIGHT) {
			code = 4;
		}
		if (c == cardinal.UP) {
			code = 8;
		}

		return (code & piper) > 0;
	}

	public static cardinal[] getConnections(char piper) {
		cardinal[] out = new cardinal[getDegree(piper)];
		int index = 0;
		int code = indexOf(piper);
		if ((code & 1) == 1) {
			out[index++] = cardinal.LEFT;
		}
		if ((code & 2) == 2) {
			out[index++] = cardinal.DOWN;
		}
		if ((code & 4) == 4) {
			out[index++] = cardinal.RIGHT;
		}
		if ((code & 8) == 8) {
			out[index++] = cardinal.UP;
		}
		return out;
	}

	public static cardinal[] getBlockages(char piper) {
		cardinal[] out = new cardinal[getDegree(piper)];
		int index = 0;
		int code = indexOf(piper);
		if ((code & 1) != 1) {
			out[index++] = cardinal.LEFT;
		}
		if ((code & 2) != 2) {
			out[index++] = cardinal.DOWN;
		}
		if ((code & 4) != 4) {
			out[index++] = cardinal.RIGHT;
		}
		if ((code & 8) != 8) {
			out[index++] = cardinal.UP;
		}
		return out;
	}

	public static char removeConnection(char piper, cardinal c) {
		int charCode = indexOf(piper);
		int code = 0;
		if (c == cardinal.LEFT) {
			code = 1;
		}
		if (c == cardinal.DOWN) {
			code = 2;
		}
		if (c == cardinal.RIGHT) {
			code = 4;
		}
		if (c == cardinal.UP) {
			code = 8;
		}
		return pipes[charCode & (~code)];
	}

	public static cardinal opposite(cardinal c) {
		if (c == cardinal.LEFT) {
			return cardinal.RIGHT;
		}
		if (c == cardinal.DOWN) {
			return cardinal.UP;
		}
		if (c == cardinal.RIGHT) {
			return cardinal.LEFT;
		}
		if (c == cardinal.UP) {
			return cardinal.DOWN;
		}
		return c;
	}

	public static void drawLines(char pip, int x, int y, int sizex, int sizey, Graphics2D g2d) {

		for (cardinal c : getConnections(pip)) {
			if (c == cardinal.LEFT) {
				g2d.drawLine(x + (sizex / 2), y + (sizey / 2), x, y + (sizey / 2));
			}
			if (c == cardinal.DOWN) {
				g2d.drawLine(x + (sizex / 2), y + (sizey / 2), x + (sizex / 2), y + sizey);
			}
			if (c == cardinal.RIGHT) {
				g2d.drawLine(x + (sizex / 2), y + (sizey / 2), x + sizex, y + (sizey / 2));
			}
			if (c == cardinal.UP) {
				g2d.drawLine(x + (sizex / 2), y + (sizey / 2), x + (sizex / 2), y);
			}
		}
	}

	public static void drawSquares(char pip, int x, int y, int sizex, int sizey, Graphics2D g2d) {
		g2d.fillRect(x, y, sizex, sizey);
		g2d.setColor(Color.BLACK);
		g2d.fillRoundRect(x + 5, y + 5, sizex - 10, sizey - 10, 10, 10);
		//g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(20 ));
		
		int begin = 15;
		int end = 15;
		for (cardinal c : getConnections(pip)) {
			if (c == cardinal.LEFT) {
				g2d.drawLine(x, y + begin, x, y + sizey - end);
			}
			if (c == cardinal.DOWN) {
				g2d.drawLine(x + begin, y + sizey, x + sizex - end, y + sizey);
			}
			if (c == cardinal.RIGHT) {
				g2d.drawLine(x + sizex, y + begin, x + sizex, y + sizey - end);
			}
			if (c == cardinal.UP) {
				g2d.drawLine(x + begin, y, x + sizex - end, y);
			}
		}
	}

}
