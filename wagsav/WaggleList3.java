import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
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
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WaggleList3 {

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
	JSplitPane splitPane = new JSplitPane();
	String txt = "";
	String latest_data = "";
	JScrollPane jsp = new JScrollPane();
	GridBagConstraints gbc = new GridBagConstraints();
	String id = "";

	public WaggleList3() {
		//total.setBackground(Color.getColor(""));
		total.setLayout(new GridBagLayout());
		total.setBackground(new Color(217, 222, 235));

		//gbc.fill = GridBagConstraints.HORIZONTAL;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (dim.getWidth() / 1.5);
		int height = (int) (dim.getHeight() / 1.5);

    btn_panel = new JPanel(new GridLayout(4, 1, 0, (int)(height * 0.03)));


		btn_info.setBackground(new Color(198, 211, 234));
		btn_latest.setBackground(new Color(198, 211, 234));
		btn_today.setBackground(new Color(198, 211, 234));
		btn_week.setBackground(new Color(198, 211, 234));

    btn_info.setFont(new Font("Arial", Font.BOLD, (int)(width / 72)));
    btn_latest.setFont(new Font("Arial", Font.BOLD, (int)(width / 72)));
    btn_today.setFont(new Font("Arial", Font.BOLD, (int)(width / 72)));
    btn_week.setFont(new Font("Arial", Font.BOLD, (int)(width / 72)));

		btn_panel.setBackground(new Color(217, 222, 235));
		btn_panel.add(btn_info);
		btn_panel.add(btn_latest);
		btn_panel.add(btn_today);
		btn_panel.add(btn_week);
    btn_panel.setPreferredSize(new Dimension((int)(width * 0.15), (int)(height * 0.5)));

    label2.setFont(new Font("Arial", Font.BOLD, 25));
    list.setModel(model);
    list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    list.setFixedCellWidth((int)(width * 0.18));
    list.setVisibleRowCount(18);
    list.setFont(new Font("Arial", Font.PLAIN, 25));
		//list.setPreferredSize(new Dimension((int) (dim.getWidth() / 20), (int) (dim.getHeight() / 4)));
		gbc.insets = new Insets(1, 10, 1, 1);
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
		label.setFont(new Font("Arial", Font.PLAIN, (int)(width / 48)));
    label.setText("select one of the waggles");
		data_panel.setPreferredSize(new Dimension((int)(width * 0.6), (int)(height * 0.7)));
		data_panel.setBackground(Color.WHITE);
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
		// Arialadd(, gbc); // add home button

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
			label.setFont(new Font("Arial", Font.PLAIN, (int)(width / 48)));
			label.setText(txt);

			id = w.getWaggle_id();
			System.out.println("selected waggle ID : " + id);
			System.out.println("waggle info : " + txt);

			data_panel.add(label);
			gbc.gridx = 1;
			gbc.gridy = 1;
			total.add(data_panel, gbc);

      data_panel.setPreferredSize(new Dimension((int)(width * 0.6), (int)(height * 0.7)));
			data_panel.setBorder(BorderFactory.createLineBorder(Color.black));
			data_panel.add(label);
			gbc.gridx = 1;
			gbc.gridy = 1;
			total.add(data_panel, gbc);
		});

		btn_today.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked today !");
				data_panel.removeAll();

				data_panel.setPreferredSize(new Dimension((int)(width * 0.6), (int)(height * 0.7)));
				data_panel.setBorder(BorderFactory.createLineBorder(Color.black));
				ArrayList<Battery> today_batteries = getTodayBatteries(id);
				data_panel.add(new BatteryGraphPanel(today_batteries), BorderLayout.CENTER);
				data_panel.setBackground(Color.WHITE);
				//frame.getContentPane().setBackground(new Color(217, 222, 235));
				data_panel.repaint();
				frame.setVisible(true);
			}
		});
		btn_week.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked week !");
				data_panel.removeAll();
				data_panel.setPreferredSize(new Dimension((int)(width * 0.6), (int)(height * 0.7)));
				data_panel.setBorder(BorderFactory.createLineBorder(Color.black));
				ArrayList<Battery> week_batteries = getWeekBatteries(id);

				data_panel.add(new BatteryGraphPanel(week_batteries), BorderLayout.CENTER);
				data_panel.setBackground(Color.WHITE);
				//frame.getContentPane().setBackground(new Color(217, 222, 235));
				data_panel.repaint();
				frame.setVisible(true);
			}
		});
		btn_info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("clicked waggle info !");
				label.setFont(new Font("Arial", Font.PLAIN, (int)(width / 48)));
				label.setText(txt);
				data_panel.removeAll();
				//ArialsetVisible(true);
				frame.getContentPane().setBackground(new Color(217, 222, 235));
				data_panel.setBackground(Color.WHITE);
				data_panel.add(label);
				data_panel.repaint();
				frame.setVisible(true);
				//System.out.println("waggle info : " + txt);
			}
		});
		btn_latest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked latest !");
				/*
				btn_latest.setForeground(Color.BLACK);
				btn_info.setForeground(Color.GRAY);
				btn_week.setForeground(Color.GRAY);
				btn_today.setForeground(Color.GRAY); */

				Battery b = getLatestBattery(id);
				if (b.getWaggle_id() == null) {
					latest_data = "<html><br>";
					latest_data += "<b>No data for this waggle</b><br>";
					latest_data += "</html>";
				}
				else {
					latest_data = "<html><br><h2>Latest Data</h2>";
					latest_data += "* Updated Time : " +b.getUpdated_time() + "<br>";
					latest_data += "* Battery Status : " + b.getBattery_status() + "<br>";
					latest_data += "* Voltage : " + b.getVoltage() + "V <br>";
					latest_data += "* Charging : " + b.getCharging() + "<br>";
					latest_data += "* Temparature : " + b.getTemperature() + "°C <br>";
					latest_data += "* Humidity : " + b.getHumidity() + "% <br>";
					latest_data += "* Heater : " + b.getHeater() + "<br>";
					latest_data += "* Fan : " + b.getFan() + "<br>";
					latest_data += "* Note : " + b.getNotification() + "<br>";
					latest_data += "</html>";
				}
				System.out.println("Latest Data : " + latest_data);
				label.setFont(new Font("Arial", Font.PLAIN, (int)(width / 48)));
				label.setText(latest_data);
				data_panel.removeAll();
				data_panel.add(label);
				data_panel.repaint();
				//frame.getContentPane().setBackground(new Color(217, 222, 235));
				frame.setVisible(true);
			}
		});

		frame.add(total);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setLocation((int)(width / 4), (int)(height / 4));
    //frame.getContentPane().setBackground(new Color(217, 222, 235));
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static ArrayList<Battery> getWeekBatteries (String id) {
		ArrayList<Battery> batteries = new ArrayList<>();
		SocketTest st = new SocketTest();
		String result = st.runClient("battery/"+id+"/week");
		System.out.println("week // result is ..." + result);
		StringTokenizer strTok1 = new StringTokenizer(result, "$");
		while (strTok1.hasMoreTokens()) {
			StringTokenizer strTok2  = new StringTokenizer(strTok1.nextToken(), "/");
			int i = 0;
			Battery b = new Battery();
			while (strTok2.hasMoreTokens()) {
				switch (i) {
				case 0: b.setWaggle_id(strTok2.nextToken()); break;
				case 1: b.setBattery_status(Float.parseFloat(strTok2.nextToken())); break;
				case 2: b.setVoltage(Float.parseFloat(strTok2.nextToken()));	break;
				case 3: b.setCharging(strTok2.nextToken().charAt(0)); break;
				case 4: b.setTemperature(Float.parseFloat(strTok2.nextToken())); break;
				case 5: b.setHumidity(Float.parseFloat(strTok2.nextToken())); break;
				case 6: b.setHeater(strTok2.nextToken().charAt(0)); break;
				case 7: b.setFan(strTok2.nextToken().charAt(0)); break;
				case 8: b.setNotification(strTok2.nextToken()); break;
				case 9: b.setNumber(Integer.parseInt(strTok2.nextToken())); break;
				case 10: b.setUpdated_time(strTok2.nextToken()); break;
				default:break;
				}
				i++;
			}
			batteries.add(b);
		}
		return batteries;
	}

	public static ArrayList<Battery> getTodayBatteries (String id) {
		ArrayList<Battery> batteries = new ArrayList<>();
		SocketTest st = new SocketTest();
		String result = st.runClient("battery/"+id+"/today");
		System.out.println("today // result is ..." + result);
		StringTokenizer strTok1 = new StringTokenizer(result, "$");
		while (strTok1.hasMoreTokens()) {
			StringTokenizer strTok2  = new StringTokenizer(strTok1.nextToken(), "/");
			int i = 0;
			Battery b = new Battery();
			while (strTok2.hasMoreTokens()) {
				switch (i) {
				case 0: b.setWaggle_id(strTok2.nextToken()); break;
				case 1: b.setBattery_status(Float.parseFloat(strTok2.nextToken())); break;
				case 2: b.setVoltage(Float.parseFloat(strTok2.nextToken()));	break;
				case 3: b.setCharging(strTok2.nextToken().charAt(0)); break;
				case 4: b.setTemperature(Float.parseFloat(strTok2.nextToken())); break;
				case 5: b.setHumidity(Float.parseFloat(strTok2.nextToken())); break;
				case 6: b.setHeater(strTok2.nextToken().charAt(0)); break;
				case 7: b.setFan(strTok2.nextToken().charAt(0)); break;
				case 8: b.setNotification(strTok2.nextToken()); break;
				case 9: b.setNumber(Integer.parseInt(strTok2.nextToken())); break;
				case 10: b.setUpdated_time(strTok2.nextToken()); break;
				default:break;
				}
				i++;
			}
			batteries.add(b);
		}

		return batteries;
	}

	public static Battery getLatestBattery (String id) {
		Battery b = new Battery();
		SocketTest st = new SocketTest();
		String result = st.runClient("battery/"+id+"/latest");
		System.out.println("latest // result is ..." + result);
		StringTokenizer strTok1 = new StringTokenizer(result, "/");
		int i = 0;

		while (strTok1.hasMoreTokens()) {
			switch (i) {
			case 0: b.setWaggle_id(strTok1.nextToken()); break;
			case 1: b.setBattery_status(Float.parseFloat(strTok1.nextToken())); break;
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
		System.out.println("waggle // result is ..." + result);
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
		SwingUtilities.invokeLater(WaggleList3::new);
	}
}
