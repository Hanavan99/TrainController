package controller.ui;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class ColumnPanel extends JPanel {

	class Group {
		private String name;
		private int row;
		private JPanel panel;

		public Group(String name, int row, JPanel panel) {
			super();
			this.name = name;
			this.row = row;
			this.panel = panel;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public JPanel getPanel() {
			return panel;
		}

		public void setPanel(JPanel panel) {
			this.panel = panel;
		}

	}

	private static final long serialVersionUID = -1029763401287643071L;

	private int[] rowWidths;
	private ArrayList<Group> groups = new ArrayList<Group>();
	private int componentHeight = 20;

	public ColumnPanel(int[] rowWidths) {
		this.rowWidths = rowWidths;
		setLayout(null);
	}

	public void addGroup(String name, int row, double labelWeight, double compWeight, String[] labels, JComponent[] components) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(name));

		int curY = 10;
		for (int i = 0; i < labels.length; i++) {
			JLabel label = new JLabel(labels[i]);
			label.setBounds(10, curY, (int) (labelWeight * rowWidths[row]), componentHeight);
			panel.add(label);

			components[i].setBounds((int) (labelWeight * (rowWidths[row] - 20)), curY, (int) (compWeight * (rowWidths[row] - 20)), componentHeight);
			panel.add(components[i]);

			curY += componentHeight + 10;
		}

		Group group = new Group(name, row, panel);
		groups.add(group);
		add(panel);
	}

}
