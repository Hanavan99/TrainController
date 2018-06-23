package controller.ui.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.core.Engine;
import controller.core.NetCommandBase;
import controller.core.Switch;
import controller.core.enums.CommandType;
import controller.core.enums.RequestType;
import controller.net.packets.RequestPacket;
import controller.net.packets.ResponsePacket;

public class ClientMainPanel extends JPanel {

	private static final long serialVersionUID = -5535166103376983096L;

	private JTabbedPane pane = new JTabbedPane();
	private JPanel enginePanel = new JPanel();
	private JComboBox<Engine> engines = new JComboBox<Engine>();
	private JButton horn = new JButton("Horn");
	private JButton bell = new JButton("Bell");
	private JButton fwd = new JButton("Forward");
	private JButton rev = new JButton("Reverse");
	private JButton speedup = new JButton("Speed +");
	private JButton speeddown = new JButton("Speed -");
	private JSlider speed = new JSlider();
	private JPanel switchPanel = new JPanel();
	private JComboBox<Switch> switches = new JComboBox<Switch>();
	private JButton switchthrough = new JButton("Through");
	private JButton switchout = new JButton("Out");

	public ClientMainPanel(Socket socket) throws IOException {

		final NetCommandBase base = new NetCommandBase("192.168.1.247", 12345, null);
		base.start();
		final ObjectInputStream in = base.createObjectInputStream();

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Object o = in.readObject();
						if (o instanceof ResponsePacket) {
							ResponsePacket response = (ResponsePacket) o;
							switch (response.getRequestType()) {
							case GET_ACCESSORIES:
								break;
							case GET_ENGINES:
								engines.removeAllItems();
								for (Engine eng : (Engine[]) response.getArgs()) {
									engines.addItem(eng);
								}
								break;
							case GET_ROUTES:
								break;
							case GET_SWITCHES:
								switches.removeAllItems();
								for (Switch sw : (Switch[]) response.getArgs()) {
									switches.addItem(sw);
								}
								break;
							case GET_USER_PERMISSIONS:
								break;
							case SET_ACCESSORY:
								break;
							case SET_ENGINE:
								break;
							case SET_ROUTE:
								break;
							case SET_SWITCH:
								break;
							default:
								break;

							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();

		setLayout(null);

		pane.setBounds(10, 10, 225, 500);
		add(pane);
		pane.add("Engine", enginePanel);
		pane.add("Switch", switchPanel);

		enginePanel.setLayout(null);
		switchPanel.setLayout(null);

		engines.setBounds(10, 10, 200, 20);
		enginePanel.add(engines);

		horn.setBounds(10, 40, 95, 20);
		enginePanel.add(horn);
		// horn.getModel().addChangeListener(new ChangeListener() {
		// @Override
		// public void stateChanged(ChangeEvent e) {
		//
		// }
		// });
		horn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				base.setCommandType(CommandType.REPEAT_START);
				base.engineBlowHorn1((Engine) engines.getSelectedItem());
				base.setCommandType(CommandType.NORMAL);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				base.setCommandType(CommandType.REPEAT_STOP);
				base.engineBlowHorn1((Engine) engines.getSelectedItem());
				base.setCommandType(CommandType.NORMAL);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		bell.setBounds(115, 40, 95, 20);
		enginePanel.add(bell);
		bell.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				base.engineRingBell((Engine) engines.getSelectedItem());
			}
		});

		rev.setBounds(10, 70, 95, 20);
		enginePanel.add(rev);
		rev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				base.engineDirectionReverse((Engine) engines.getSelectedItem());
			}
		});

		fwd.setBounds(115, 70, 95, 20);
		enginePanel.add(fwd);
		fwd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				base.engineDirectionForward((Engine) engines.getSelectedItem());
			}
		});

		speeddown.setBounds(10, 100, 95, 20);
		// enginePanel.add(speeddown);
		speeddown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				base.engineSpeedRelative((Engine) engines.getSelectedItem(), 4);
			}
		});

		speedup.setBounds(115, 100, 95, 20);
		// enginePanel.add(speedup);
		speedup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				base.engineSpeedRelative((Engine) engines.getSelectedItem(), 6);
			}
		});

		speed.setBounds(10, 100, 200, 20);
		speed.setMinimum(0);
		speed.setMaximum(31);
		speed.setMajorTickSpacing(1);
		speed.setSnapToTicks(true);
		enginePanel.add(speed);
		speed.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				base.engineSpeedAbsolute((Engine) engines.getSelectedItem(), speed.getValue());
			}
		});

		switches.setBounds(10, 10, 200, 20);
		switchPanel.add(switches);

		switchthrough.setBounds(10, 40, 95, 20);
		switchPanel.add(switchthrough);
		switchthrough.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				base.switchThrowThrough((Switch) switches.getSelectedItem());
			}
		});

		switchout.setBounds(115, 40, 95, 20);
		switchPanel.add(switchout);
		switchout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				base.switchThrowOut((Switch) switches.getSelectedItem());
			}
		});

		base.sendCommand(new RequestPacket(RequestType.GET_ENGINES, null, null));
		base.sendCommand(new RequestPacket(RequestType.GET_SWITCHES, null, null));

	}

	public int getEngineID() {
		return ((Engine) engines.getSelectedItem()).getID();
	}

	public int getSwitchID() {
		return ((Switch) switches.getSelectedItem()).getID();
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ex) {

		}
	}

}
