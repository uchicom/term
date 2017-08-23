// (c) 2017 uchicom
package com.uchicom.term.window;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import com.uchicom.term.Constants;
import com.uchicom.term.action.CloseAction;
import com.uchicom.term.action.ConnectAction;
import com.uchicom.term.action.GoAction;
import com.uchicom.ui.ResumeFrame;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class TermFrame extends ResumeFrame {

	private JSch jsch = new JSch();
	private Session session;
	private JTextArea outputArea = new JTextArea();
	private JTextArea inputArea = new JTextArea();
	public TermFrame() {
		super(Constants.configFile, Constants.KEY_WINDOW);

		initComponents();
	}

	/**
	 *	テキストエリア2つ、出力結果と入力欄とGOボタン
	 */
	private void initComponents() {
		outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
		setJMenuBar(createJMenuBar());
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(new JScrollPane(outputArea), BorderLayout.CENTER);
		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);
		southPanel.add(new JButton(new GoAction(this)), BorderLayout.EAST);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		pack();
	}

	/**
	 * @return
	 */
	private JMenuBar createJMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("セッション");
		JMenuItem menuItem = new JMenuItem(new ConnectAction(this));
		menu.add(menuItem);
		menuItem = new JMenuItem(new CloseAction(this));
		menu.add(menuItem);
		menuBar.add(menu);
		return menuBar;
	}

	/**
	 * @param jsch
	 * @throws JSchException
	 * @throws NumberFormatException
	 */
	public Session createSession(JSch jsch) throws NumberFormatException, JSchException {
		Session session = null;
		if (Boolean.valueOf(config.getProperty(Constants.KEY_PUBLIC))) {
			jsch.addIdentity(config.getProperty(Constants.KEY_FILE));
			jsch.setKnownHosts("~/.ssh/known_hosts");
			UserInfo userInfo = new UserInfo() {

				@Override
				public String getPassphrase() {
					// TODO 自動生成されたメソッド・スタブ
					return config.getProperty(Constants.KEY_PASSWORD);
				}

				@Override
				public String getPassword() {
					// TODO 自動生成されたメソッド・スタブ
					return config.getProperty(Constants.KEY_PASSWORD);
				}

				@Override
				public boolean promptPassphrase(String arg0) {
					// TODO 自動生成されたメソッド・スタブ
					return true;
				}

				@Override
				public boolean promptPassword(String arg0) {
					// TODO 自動生成されたメソッド・スタブ
					return true;
				}

				@Override
				public boolean promptYesNo(String arg0) {
					// TODO 自動生成されたメソッド・スタブ
					return true;
				}

				@Override
				public void showMessage(String arg0) {
					// TODO 自動生成されたメソッド・スタブ

				}

			};
			session = jsch.getSession(config.getProperty(Constants.KEY_USER), config.getProperty(Constants.KEY_HOST), Integer.parseInt(config.getProperty(Constants.KEY_PORT)));
//			session.setConfig("StrictHostKeyChecking", "no");
			session.setUserInfo(userInfo);
//            session.setPassword(config.getProperty(Constants.KEY_PASSWORD));

		} else {
			session = jsch.getSession(config.getProperty(Constants.KEY_USER), config.getProperty(Constants.KEY_HOST), Integer.parseInt(config.getProperty(Constants.KEY_PORT)));
			session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(config.getProperty(Constants.KEY_PASSWORD));
		}
		session.connect();
		session.setServerAliveInterval(120);//キープアライブを送らなくても大丈夫な時間、これ以降はキープアライブが必要


		return session;
	}

	/**
	 * 入力コマンド実行
	 */
	public void go() {

		ChannelExec channel = null;
		try {
			String command = inputArea.getText();
			outputArea.append(">");
			outputArea.append(command);
			outputArea.append("\n");
			outputArea.append("-----------------------------------------------------");
			outputArea.append("\n");
			channel = (ChannelExec)session.openChannel("exec");
			channel.setCommand(command);
			channel.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(channel.getInputStream(), config.getProperty(Constants.KEY_CHARSET)));
			String line = null;
			while ((line = br.readLine()) != null) {
				outputArea.append(line);
				outputArea.append("\n");
			}
			inputArea.setText("");
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (channel != null) {
				channel.disconnect();
			}
		}
	}

	/**
	 * 接続
	 */
	public void connect() {
		try {
			// 接続処理
			session = createSession(jsch);
			outputArea.append(session.getServerVersion());
			outputArea.append("\n");
		} catch (JSchException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 切断
	 */
	public void close() {
		if (session != null) {
			session.disconnect();
		}
	}
}
