package com.github.hanavan99.controller.ui.server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.hanavan99.controller.core.User;
import com.github.hanavan99.controller.net.PacketServer;

public class ServerMainPanel extends JPanel {

	private static final long serialVersionUID = 112093740127641L;
	private static final Color STOPPED_COLOR = Color.RED;
	private static final Color LOADING_COLOR = Color.YELLOW;
	private static final Color RUNNING_COLOR = Color.GREEN;

	private PacketServer server;

	private JPanel groupServer;
	private JPanel groupSettings;

	private JCheckBox useAddress;
	private JTextField serverAddress;
	private JTextField serverPort;
	private JTextField serverComPort;
	private JButton startServer;
	private JButton stopServer;
	private JLabel serverState;
	private JList<User> users;

	private JCheckBox enableUserPerms;
	private JCheckBox enableSingleUse;

	public ServerMainPanel() {
		setLayout(null);

		groupServer = new JPanel();
		groupServer.setLayout(null);
		groupServer.setBorder(BorderFactory.createTitledBorder("Server"));
		groupServer.setBounds(10, 10, 300, 195);
		add(groupServer);

		useAddress = new JCheckBox("Use address:");
		useAddress.setBounds(10, 20, 100, 20);
		groupServer.add(useAddress);

		serverAddress = new JTextField();
		serverAddress.setBounds(120, 20, 170, 20);
		serverAddress.setEditable(false);
		groupServer.add(serverAddress);

		serverPort = new JTextField();
		serverPort.setBounds(120, 50, 170, 20);
		groupServer.add(serverPort);

		createLabel(groupServer, "Port:", 10, 50, 100, 20);

		serverComPort = new JTextField();
		serverComPort.setBounds(120, 80, 170, 20);
		groupServer.add(serverComPort);

		createLabel(groupServer, "Com port:", 10, 80, 100, 20);

		startServer = new JButton("Start Server");
		startServer.setBounds(10, 110, 135, 40);
		groupServer.add(startServer);
		startServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					serverState.setText("Server starting...");
					serverState.setForeground(LOADING_COLOR);
					server.setAddress(useAddress.isSelected() ? serverAddress.getText() : null);
					server.setPort(Integer.parseInt(serverPort.getText()));
					server.setComPort(serverComPort.getText());
					server.start();
					serverState.setText("Server running");
					serverState.setForeground(RUNNING_COLOR);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(ServerMainPanel.this, "Invalid port for server.");
					serverState.setText("Server stopped");
					serverState.setForeground(STOPPED_COLOR);
				}
			}
		});

		stopServer = new JButton("Stop Server");
		stopServer.setBounds(155, 110, 135, 40);
		groupServer.add(stopServer);
		stopServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				server.stop();
				serverState.setText("Server stopped");
				serverState.setForeground(STOPPED_COLOR);
			}
		});

		serverState = new JLabel("Server stopped");
		serverState.setForeground(STOPPED_COLOR);
		serverState.setBounds(10, 155, 280, 30);
		serverState.setFont(new Font("Arial", Font.PLAIN, 24));
		groupServer.add(serverState);

		useAddress.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				serverAddress.setEditable(useAddress.isSelected());
			}
		});

		groupSettings = new JPanel();
		groupSettings.setLayout(null);
		groupSettings.setBorder(BorderFactory.createTitledBorder("Settings"));
		groupSettings.setBounds(10, 215, 300, 200);
		add(groupSettings);

		enableUserPerms = new JCheckBox("Enable user permisions");
		enableUserPerms.setBounds(10, 20, 280, 20);
		groupSettings.add(enableUserPerms);

		server = new PacketServer();
	}

	private void createLabel(JComponent parent, String text, int x, int y, int width, int height) {
		JLabel label = new JLabel(text);
		label.setBounds(x, y, width, height);
		parent.add(label);
	}

}
