package controller.main;

import javax.swing.JFrame;

import controller.ui.server.ServerMainPanel;

public class Server {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Server");
		frame.add(new ServerMainPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50, 50, 800, 600);
		frame.setVisible(true);

	}

}
