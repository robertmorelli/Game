package Display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;


public class Settings implements Displayable, listeningtostuff {
	public static difficulties difficulty = difficulties.impossible;

	@Override
	public void update(GameBoard laBoard) {

	}

	@Override
	public void raster(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.fillOval(860, 980, 80, 80);

		g2d.setStroke(new BasicStroke(20, BasicStroke.CAP_ROUND, 0));

		g2d.drawLine(945, 1020, 855, 1020);

		g2d.drawLine(900, 975, 900, 1065);

		g2d.drawLine(870, 990, 930, 1050);

		g2d.drawLine(870, 1050, 930, 990);

		g2d.setColor(Color.BLACK);
		g2d.fillOval(870, 990, 60, 60);

	}

	public static void displaySettings(Graphics2D g2d) {

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 1000, 1000);

		g2d.setColor(Color.WHITE);
		// g2d.fillOval(650, 750, 80, 80);
		g2d.fillOval(660, 780, 80, 80);

		g2d.setStroke(new BasicStroke(20, BasicStroke.CAP_ROUND, 0));

		g2d.drawLine(745, 820, 655, 820);

		g2d.drawLine(700, 775, 700, 865);

		g2d.drawLine(670, 790, 730, 850);

		g2d.drawLine(670, 850, 730, 790);

		g2d.setColor(Color.BLACK);
		g2d.fillOval(670, 790, 60, 60);

		g2d.setFont(new Font("Consolas", Font.BOLD, 50));
		if(Game.keyoption==0) {
			g2d.setColor(Color.RED);
			g2d.drawString("WASD", 30, 70);
			g2d.setColor(Color.WHITE);
			g2d.drawString("▲◄▼►", 200, 70);
		}
		if(Game.keyoption==1) {
			g2d.setColor(Color.WHITE);
			g2d.drawString("WASD", 30, 70);
			g2d.setColor(Color.RED);
			g2d.drawString("▲◄▼►", 200, 70);
		}
		
		
		
		if(Game.difficulty==difficulties.peaceful ) {
			g2d.setColor(Color.RED);
			g2d.drawString("peaceful", 30, 130);
			g2d.setColor(Color.WHITE);
			g2d.drawString("easy", 270, 130);
			g2d.setColor(Color.WHITE);
			g2d.drawString("impossible", 410, 130);
		}
		if(Game.difficulty==difficulties.easy ) {
			g2d.setColor(Color.WHITE);
			g2d.drawString("peaceful", 30, 130);
			g2d.setColor(Color.RED);
			g2d.drawString("easy", 270, 130);
			g2d.setColor(Color.WHITE);
			g2d.drawString("impossible", 410, 130);
		}
		if(Game.difficulty==difficulties.impossible ) {
			g2d.setColor(Color.WHITE);
			g2d.drawString("peaceful", 30, 130);
			g2d.setColor(Color.WHITE);
			g2d.drawString("easy", 270, 130);
			g2d.setColor(Color.RED);
			g2d.drawString("impossible", 410, 130);
		}
		

	}

	public void mouseClicked(MouseEvent e) {

		Point p = e.getPoint();
		int x = p.x;
		int y = p.y;
		//System.out.println(x + " : " + y);
		if (x > 700 && x < 770 && y > 800 && y < 900) {
			if (Game.GAME == gameState.pregame) {
				Game.GAME = gameState.settings;
			} else if (Game.GAME == gameState.settings) {
				Game.GAME = gameState.pregame;
				Game.mainCharacter.setKeys(Game.keyoptions[Game.keyoption]);
				if(Game.difficulty==difficulties.peaceful) {
					for(Ghost g:Game.Ghosts) {
						g.findAlg=new RandomFinder();
					}
				}
				if(Game.difficulty==difficulties.easy) {
					for(Ghost g:Game.Ghosts) {
						g.findAlg=new DFSFinder();
					}
				}
				if(Game.difficulty==difficulties.impossible) {
					for(Ghost g:Game.Ghosts) {
						g.findAlg=new BFSFinder();
					}
				}
			}
		}

		if (Game.GAME == gameState.settings) {
			if (x > 40 && x < 145 && y > 70 && y < 100) {
				Game.keyoption=0;
			}
			if (x > 210 && x < 312 && y > 70 && y < 100) {
				Game.keyoption=1;
			}
			
			
			
			if (x > 40 && x < 250 && y > 130 && y < 160) {
				Game.difficulty=difficulties.peaceful;
			}
			if (x > 280 && x < 380 && y > 130 && y < 160) {
				Game.difficulty=difficulties.easy ;
			}
			if (x > 420 && x < 700 && y > 130 && y < 160) {
				Game.difficulty=difficulties.impossible ;
			}
			
		}


	}

}


