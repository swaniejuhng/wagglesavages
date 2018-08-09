import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WaggleList2 {
	JFrame frame = new JFrame("Waggle Monitor");
	JList<Waggle> list = new JList<>();
	DefaultListModel<Waggle> model = new DefaultListModel<>();
	JButton btn_today = new JButton("Today");
	JButton btn_latest = new JButton("Latest");
	JButton btn_week = new JButton("Week");
	JButton btn_info = new JButton("Waggle Info");
	JLabel label = new JLabel();
	JLabel label2 = new JLabel("Waggle ID");
	JPanel total = new JPanel();
	JPanel data_panel = new JPanel();
	JPanel btn_panel;
	String txt = "";
	String latest_data = "";
	JScrollPane jsp = new JScrollPane();
	GridBagConstraints gbc = new GridBagConstraints();

	public WaggleList2() {

		//btn_today.setPreferredSize(new Dimension(20, 10));
		//frame.setLayout(new GridBagLayout());
		total.setLayout(new GridBagLayout());

		//gbc.fill = GridBagConstraints.HORIZONTAL;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)(dim.getWidth() / 2);
		int height = (int)(dim.getHeight() / 2);

		btn_panel = new JPanel(new GridLayout(4, 1, 0, (int)(height * 0.03)));

		btn_info.setFont(new Font("Arial", Font.BOLD, 15));
		btn_latest.setFont(new Font("Arial", Font.BOLD, 15));
		btn_today.setFont(new Font("Arial", Font.BOLD, 15));
		btn_week.setFont(new Font("Arial", Font.BOLD, 15));

		btn_panel.add(btn_info);
		btn_panel.add(btn_latest);
		btn_panel.add(btn_today);
		btn_panel.add(btn_week);
		btn_panel.setPreferredSize(new Dimension((int)(width * 0.15), (int)(height * 0.5)));

		label2.setFont(new Font("Arial", Font.BOLD, 25));
		list.setModel(model);
		list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		list.setFixedCellWidth((int)(width * 0.15));
		list.setVisibleRowCount(15);
		list.setFont(new Font("Arial", Font.PLAIN, 25));
		//list.setFixedCellHeight((int)(height * 0.7));
		//list.setPreferredSize(new Dimension((int) (dim.getWidth() / 20), (int) (dim.getHeight() / 4)));
		gbc.insets = new Insets(1, 10, 1, 1);		// top, left, bottom, right
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		total.add(new JScrollPane(list), gbc); // add real waggle list

		//jsp.setPreferredSize(new Dimension((int) (dim.getWidth() / 4), (int) (dim.getHeight() / 6)));;
		//jsp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 6;
		gbc.gridx = 1;
		gbc.gridy = 1;

		//gbc.gridwidth = 6;
		//gbc.gridheight = 4;
		label.setText("select one of the waggles");
		data_panel.setPreferredSize(new Dimension((int)(width * 0.6), (int)(height * 0.7)));
		data_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		data_panel.add(label);
		JScrollPane jsp = new JScrollPane(data_panel);
		total.add(jsp, gbc);

		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		total.add(label2, gbc); // label2 : Waggle List

		gbc.gridx = 1;
		gbc.gridy = 0;
		// frame.add(, gbc); // add home button

		/* not yetttt
		gbc.gridx = 2;
		gbc.gridy = 0; // logo or small info about developers (us)
		JLabel label3 = new JLabel("<html><p style='font-size:80%;text-align:right;'>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "developed by hyemin kim & swanie juhng</p></html>");
		total.add(label3, gbc);
		*
		*/
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.weightx = 1;
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(5, 5, 5, 5);		// top, left, bottom, right
		total.add(btn_panel, gbc);

		ArrayList<Waggle> wagList = getWaggleData();

		for (Waggle w : wagList) {
			model.addElement(w);
		}

		list.getSelectionModel().addListSelectionListener(e -> {

			Waggle w = list.getSelectedValue();
			txt = "<html><h2>Waggle Information</h2>";
			txt += "ID : " + w.getWaggle_id() + "<br>";
			txt += "Longitude : " + w.getLongitude() + "<br>";
			txt += "Latitude : " + w.getLatitude() + "<br>";
			txt += "Created Date : " + w.getDate_created() + "<br>";
			txt += "Location : " + w.getLocation() + "<br> <html>";
			label.setText(txt);

			String id = w.getWaggle_id();
			System.out.println("selected waggle ID : " + id);
			System.out.println("waggle info : " + txt);

			data_panel.setPreferredSize(new Dimension((int)(width * 0.6), (int)(height * 0.7)));
			data_panel.setBorder(BorderFactory.createLineBorder(Color.black));
			data_panel.add(label);
			gbc.gridx = 1;
			gbc.gridy = 1;
			total.add(data_panel, gbc);
			btn_today.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("clicked today !");
					data_panel.removeAll();
					data_panel.setPreferredSize(new Dimension((int)(width * 0.6), (int)(height * 0.7)));
					data_panel.setBorder(BorderFactory.createLineBorder(Color.black));
					data_panel.add(new BatteryGraphPanel());
					frame.setVisible(true);
				}
			});
			btn_week.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("clicked week !");
				}
			});
			btn_info.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("clicked waggle info !");
					label.setText(txt);
					data_panel.removeAll();
					data_panel.setPreferredSize(new Dimension((int)(width * 0.6), (int)(height * 0.7)));
					data_panel.setBorder(BorderFactory.createLineBorder(Color.black));
					data_panel.add(label);

					System.out.println("waggle info : " + txt);
				}
			});
			btn_latest.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("clicked latest !");
					Battery b = getLatestBattery(id);
					if (b.getWaggle_id() == null) {
						latest_data = "<html><br>";
						latest_data += "<b>No data for this waggle</b><br>";
						latest_data += "</html>";
					}
					else {
						latest_data = "<html><br><h2>Latest Data</h2>";
						latest_data += "• Updated Time : " +b.getUpdated_time() + "<br>";
						latest_data += "• Battery Status : " + b.getBattery_status() + "<br>";
						latest_data += "• Voltage : " + b.getVoltage() + "V <br>";
						latest_data += "• Charging : " + b.getCharging() + "<br>";
						latest_data += "• Temparature : " + b.getTemperature() + "°C <br>";
						latest_data += "• Humidity : " + b.getHumidity() + "% <br>";
						latest_data += "• Heater : " + b.getHeater() + "<br>";
						latest_data += "• Fan : " + b.getFan() + "<br>";
						latest_data += "• Note : " + b.getNotification() + "<br>";
						latest_data += "</html>";
					}
				System.out.println("Latest Data : " + latest_data);
				label.setText(latest_data);
				//data_panel.removeAll();

				data_panel.removeAll();
				data_panel.setPreferredSize(new Dimension((int)(width * 0.6), (int)(height * 0.7)));
				data_panel.setBorder(BorderFactory.createLineBorder(Color.black));
				data_panel.add(label);

				}
			});
		});


		frame.add(total);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setLocation((int)(width / 2), (int)(height / 2));

		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static Battery getLatestBattery (String id) {
		Battery b = new Battery();
		SocketTest st = new SocketTest();
		String result = st.runClient("battery/"+id+"/latest");
		System.out.println("result is …" + result);
		StringTokenizer strTok1 = new StringTokenizer(result, "/");
		int i = 0;

		while (strTok1.hasMoreTokens()) {
			switch (i) {
			case 0: b.setWaggle_id(strTok1.nextToken()); break;
			case 1: b.setBattery_status(Integer.parseInt(strTok1.nextToken())); break;
			case 2: b.setVoltage(Float.parseFloat(strTok1.nextToken()));	break;
			case 3: b.setCharging(strTok1.nextToken().charAt(0)); break;
			case 4: b.setTemperature(Float.parseFloat(strTok1.nextToken())); break;
			case 5: b.setHumidity(Float.parseFloat(strTok1.nextToken())); break;
			case 6: b.setHeater(strTok1.nextToken().charAt(0)); break;
			case 7: b.setFan(strTok1.nextToken().charAt(0)); break;
			case 8: b.setNotification(strTok1.nextToken()); break;
			case 9: b.setNumber(Integer.parseInt(strTok1.nextToken())); break;
			case 10: b.setUpdated_time(strTok1.nextToken()); break;
			default:break;
			}
			i++;
		}
		return b;
	}

	public static ArrayList<Waggle> getWaggleData () {
		ArrayList<Waggle> wagList = new ArrayList<>();
		SocketTest st = new SocketTest();
		String result = st.runClient("waggle");
		System.out.println("result is …" + result);
		StringTokenizer strTok1 = new StringTokenizer(result, "$");
		while (strTok1.hasMoreTokens()) {
			StringTokenizer strTok2  = new StringTokenizer(strTok1.nextToken(), "/");
			int i = 0;
			Waggle w = new Waggle();
			while (strTok2.hasMoreTokens()) {
				switch (i) {
				case 0: w.setWaggle_id(strTok2.nextToken()); break;
				case 1: w.setLongitude(strTok2.nextToken()); break;
				case 2: w.setLatitude(strTok2.nextToken());	break;
				case 3: w.setDate_created(strTok2.nextToken()); break;
				case 4: w.setLocation(strTok2.nextToken()); break;
				default:break;
				}
				i++;
			}
			wagList.add(w);
		}
		return wagList;
	}

	public static void main (String[] args) {
		SwingUtilities.invokeLater(WaggleList2::new);
	}
}
