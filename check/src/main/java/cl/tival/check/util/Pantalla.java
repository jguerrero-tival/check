package cl.tival.check.util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class Pantalla {

	public static Point centrar(Dimension oldPosition) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point newPosition = new Point();
		newPosition.setLocation((int) (screenSize.width - oldPosition
				.getWidth()) / 2, (int) (screenSize.height - oldPosition
				.getHeight()) / 2);
		return newPosition;
	}

}