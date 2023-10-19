// (C) 2017 uchicom
package com.uchicom.term;

import java.io.File;

/**
 * 定数クラス.
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class Constants {
  /** 設定ファイル */
  public static final File configFile = new File("conf/term.properties");

  /** ホスト名 */
  public static final String KEY_HOST = "host";

  /** ポート */
  public static final String KEY_PORT = "port";

  /** 公開鍵方式か否か */
  public static final String KEY_PUBLIC = "public";

  /** 公開鍵ファイル */
  public static final String KEY_FILE = "file";

  /** ユーザ */
  public static final String KEY_USER = "user";

  /** パスワードまたは公開鍵のパスフレーズ */
  public static final String KEY_PASSWORD = "password";

  /** ウィンドウキー */
  public static final String KEY_WINDOW = "window";

  /** 文字コードキー */
  public static final String KEY_CHARSET = "charset";
}
