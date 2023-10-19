// (C) 2017 uchicom
package com.uchicom.term.action;

import com.uchicom.term.window.TermFrame;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * コマンド実行アクション.
 *
 * @author uchicom: Shigeki Uchiyama
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
