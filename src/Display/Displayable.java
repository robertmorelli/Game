package Display;

import java.awt.Graphics2D;
import java.io.IOException;
import java.net.URISyntaxException;

@SuppressWarnings("unused")
public interface Displayable {
	void update(GameBoard laBoard) throws URISyntaxException;

	void raster(Graphics2D g2d);
}
