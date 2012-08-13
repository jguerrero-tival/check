package cl.tival.check.util;

import java.awt.Color;

import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Message {

	final static Logger LOG = LoggerFactory.getLogger(Message.class);
	private static long sleepTime = 4000;

	public static void timerLabel(final JLabel jLabelAnswer, final Color color, final String answer) {
		LOG.trace("Starting \"timerLabel\" method.");

		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					jLabelAnswer.setText(answer);
					jLabelAnswer.setBackground(color);
					Thread.sleep(sleepTime);
					jLabelAnswer.setText("...");
					jLabelAnswer.setBackground(Color.GRAY);
					LOG.debug("{}", answer);
				} catch (InterruptedException e) {
					LOG.error("Error al definir mensaje", e);
				}
			}
		});
		t.start();

		LOG.trace("Finishing \"timerLabel\" method.");
	}

}