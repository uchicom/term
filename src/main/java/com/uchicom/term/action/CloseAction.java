// (C) 2017 uchicom
package com.uchicom.term.action;

import com.uchicom.term.window.TermFrame;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * SSH接続の切断アクション.
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class CloseAction extends AbstractAction {

  private TermFrame termFrame;

  public CloseAction(TermFrame termFrame) {
    this.termFrame = termFrame;
    putValue(NAME, "切断");
  }

  /* (非 Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    termFrame.close();
  }
}
