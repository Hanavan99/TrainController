package com.github.hanavan99.traincontroller.client;

import javax.swing.JFrame;

import com.github.hanavan99.traincontroller.ui.client.ClientMainPanel;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("TrainController");
		frame.add(new ClientMainPanel("192.168.1.247"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 800, 480);
		// frame.setUndecorated(true);
		frame.setVisible(true);
	}

}
