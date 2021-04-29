package Display;

import java.awt.Graphics2D;
import java.io.IOException;

public interface Displayable {
	void update(GameBoard laBoard);

	void raster(Graphics2D g2d);
}
