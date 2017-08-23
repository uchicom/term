// (c) 2017 uchicom
package com.uchicom.term;

import javax.swing.SwingUtilities;

import com.uchicom.term.window.TermFrame;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			TermFrame frame = new TermFrame();
			frame.setVisible(true);
		});
	}

}
