/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.tival.check.view;

import javax.swing.JComponent;

import org.springframework.richclient.application.support.AbstractView;

/**
 *
 * @author jguerrero
 */
public class MainView extends AbstractView {
	
	private MainPanel mainPanel;

	@Override
    protected JComponent createControl() {
        return mainPanel;
    }

	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

}
