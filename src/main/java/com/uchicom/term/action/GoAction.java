// (c) 2017 uchicom
package com.uchicom.term.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.uchicom.term.window.TermFrame;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class GoAction extends AbstractAction {

	private TermFrame termFrame;
	public GoAction(TermFrame termFrame) {
		this.termFrame = termFrame;
		putValue(NAME, "Go");
	}
	/* (非 Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		termFrame.go();
	}

}
