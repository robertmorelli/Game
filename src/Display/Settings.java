package Display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Settings implements Displayable {

	@Override
	public void update(GameBoard laBoard) {
		
	}

	@Override
	public void raster(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.fillOval(860,980, 80, 80);
		
		g2d.setStroke(new BasicStroke(20,BasicStroke.CAP_ROUND,0 ));
		
		g2d.drawLine(945, 1020, 855, 1020);
		
		g2d.drawLine(900, 975, 900, 1065);
		
		g2d.drawLine(870, 990, 930, 1050);
		
		g2d.drawLine(870, 1050, 930, 990);
		
		
		g2d.setColor(Color.BLACK);
		g2d.fillOval(870,990, 60, 60);
		
	}
	
	
	//class SettingsListener implements 
	
	
	

}
