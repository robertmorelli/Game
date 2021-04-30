package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class restartButton implements Displayable, listeningtostuff {

	@Override
	public void update() {
	}

	public int frame = 0;

	@Override
	public void raster(Graphics2D g2d) {
		if (Game.GAME == gameState.lost) {
			if (frame++ > 100) {
				g2d.setColor(Color.WHITE);
				g2d.setFont(new Font("Consolas", Font.BOLD, 40));
				g2d.drawString("restart", 300, 400);
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		int x = p.x;
		int y = p.y;
		if (x > 250 && x < 430 && y > 400 && y < 800) {
			if (Game.GAME == gameState.lost) {
				Game.GAME = gameState.restarting;
				frame = 0;
			}
		}

	}

}
