// (C) 2017 uchicom
package com.uchicom.term;

import com.uchicom.term.window.TermFrame;
import javax.swing.SwingUtilities;

/**
 * ターミナル起動クラス.
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class Main {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        () -> {
          TermFrame frame = new TermFrame();
          frame.setVisible(true);
        });
  }
}
