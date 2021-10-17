package Display;

//import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JTextField;

//import com.sun.tools.javac.util.List;

public class ScoreBoardDisplay implements Displayable,listeningtostuff {
	public static String myName = "Anon";
	JFrame nameboax;
	JTextField name;
	public static String[] leaderNames = { "jeff", "jeff", "jeff", "still jeff","randy" };
	public static int[] leaderScores = {  25, 20, 15, 10, 5 };

	//public boardListener listening;

	public ScoreBoardDisplay() {
		//listening = new boardListener();
		nameboax = new JFrame();
		name = new JTextField("Anon");
		nameboax.add(name);
		nameboax.setSize(300, 80);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	public static void oraganisescores() {
		String[] leaderComb=new String[leaderNames.length];
		for(int p=0;p<leaderNames.length;p++) {
			leaderComb[p]=String.format("%09d",leaderScores[p])+","+leaderNames[p];
			
		}
		Arrays.sort(leaderComb);
		for(int p=0;p<leaderNames.length;p++) {
			leaderScores[leaderNames.length-p-1]=Integer.parseInt(leaderComb[p].split(",")[0]);
			leaderNames[leaderNames.length-p-1]=leaderComb[p].split(",")[1];
		}
		
	}
	

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Consolas", Font.BOLD, 40));
		g2d.drawString("Leaderboard", 600, 1000);
	}

	public void drawLeaders(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 1000, 1000);
		g2d.setFont(new Font("Consolas", Font.BOLD, 40));
		g2d.setColor(Color.WHITE);
		g2d.drawString("Scores", 20, 40);
		g2d.setFont(new Font("Consolas", Font.BOLD, 20));
		for (int i = 0; i < 10; i++) {
			g2d.drawString((i + 1) + "", 20, 40 * (i + 2));
		}
		
		for (int i = 0; i < 10; i++) {
			g2d.drawString(leaderNames[i], 60, 40 * (i + 2));
		}
		for (int i = 0; i < 10; i++) {
			g2d.drawString(leaderScores[i] + "", 520, 40 * (i + 2));
		}

		g2d.drawString("set name ["+myName+"]", 500, 770);
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("Consolas", Font.BOLD, 20));
		g2d.drawString("leave Leaderboard", 500, 800);

	}

	public void setName() {

		nameboax.setVisible(true);
		name.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				myName = name.getText();
				nameboax.setVisible(false);
			}

		});
	}


	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		int x = p.x;
		int y = p.y;
		if (x > 500 && x < 660 && y > 800 && y < 840) {

			if (Game.GAME == gameState.pregame) {
				Game.GAME = gameState.scoreboard;
			} else if (Game.GAME == gameState.scoreboard) {
				Game.GAME = gameState.pregame;
			}

		}
		if (x > 500 && x < 660 && y > 750 && y < 800) {
			if (Game.GAME == gameState.scoreboard) {
				setName();
			}
		}

	}

}