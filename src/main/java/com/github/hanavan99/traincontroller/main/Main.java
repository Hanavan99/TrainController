package com.github.hanavan99.traincontroller.main;

import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

import com.github.hanavan99.traincontroller.ui.client.ClientMainPanel;

public class Main {

	public static void main(String[] args) throws Exception {

		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// runDemo(args[0]);
		runUI();
	}

	public static void runUI() throws IOException {
		JFrame frame = new JFrame("TrainController");
		frame.add(new ClientMainPanel(new Socket("192.168.1.247", 12345)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50, 50, 500, 500);
		frame.setVisible(true);
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {

		}
	}

}
