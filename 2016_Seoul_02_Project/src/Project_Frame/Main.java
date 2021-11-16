package Project_Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Project_DBInterface.DBInterface;

public class Main extends JFrame implements ActionListener, Runnable {
	JComboBox combo = new JComboBox();
	JLabel tl = new JLabel();
	String[] header = {"열차","출발시간","도착역","도착시간"};
	DefaultTableModel model = new DefaultTableModel(header, 0) {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	JTable table = new JTable(model);
	JScrollPane scroll = new JScrollPane(table);
	JButton btn1 = new JButton("승차권예매");
	JButton btn2 = new JButton("승차권확인");
	JButton btn3 = new JButton("로그아웃");
	SimpleDateFormat f = new SimpleDateFormat("HH:mm");
	Date rd, nd;
	int selected=1;
	int t=0;
	
	public Main() {
		setTitle("메인");
		setSize(500, 450);
		setLocationRelativeTo(null);
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBorder(new EmptyBorder(10, 10, 50, 10));
		
		JPanel p1 = new JPanel(new BorderLayout());
		JPanel pc = new JPanel(new BorderLayout());
		pc.setBorder(new EmptyBorder(0, 0, 0, -19));
		pc.add(combo);
		combo.setPreferredSize(new Dimension(120, 30));
		p1.add(pc, BorderLayout.WEST); p1.add(tl, BorderLayout.EAST);
		
		JPanel p2 = new JPanel(new BorderLayout());
		p2.setBorder(new EmptyBorder(10, 0, 50, 0));
		p2.add(scroll);
		
		JPanel p3 = new JPanel(new GridLayout(1, 3, 10, 10));
		p3.setPreferredSize(new Dimension(480, 50));
		p3.add(btn1); p3.add(btn2); p3.add(btn3);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		
		p.add(p1);
		p.add(p2);
		p.add(p3);
		
		add(p);
		
		Thread th = new Thread(this);
		th.start();
		
		try {
			ResultSet rs = DBInterface.Stmt.executeQuery("select * from station");
			
			while(rs.next()) {
				combo.addItem(rs.getString(2));
			}
			
			Init();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Init();
			}
		});
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				// TODO Auto-generated method stub
				final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				setHorizontalAlignment(this.CENTER);
				if(isSelected) selected=row;
				try {
					rd = f.parse(table.getValueAt(row, 1).toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long dd= rd.getTime() - nd.getTime(); //1분 마다 60000, 1초=1000
				if(t%2==0 && dd <= 1800000) {
					c.setBackground(Color.pink);
				}  else c.setBackground(Color.white);
				
				return c;
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getSource();
		
		if(btn==btn1) {
			new TicketReservation();
		} else if(btn==btn2) {
			new TicketSelect();
		} else {
			this.dispose();
			new Login();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int n=0;
		while(true) {
			Date d = new Date();
			tl.setText("현재시각 : "+f.format(d)+"    ");
			try {
				nd = f.parse(f.format(d));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t++;
			table.repaint();
		}
	}
	
	public void Init() {
		try {
			model.setNumRows(0);
			
			ResultSet sn = DBInterface.Stmt.executeQuery("select * from station where name='"+combo.getSelectedItem().toString()+"'"); sn.next();
			
			ResultSet rs = DBInterface.Stmt.executeQuery("select train.name,date_format(time(Departure_time),'%H:%i'),station.name,date_format(time(addtime(Departure_time,Lead_time)),'%H:%i') from train_service join schedule join station join train on Schedule_num=schedule.id and schedule.Train_num=train.id and Arrival_station=station.id and date(Departure_time)=current_date() and time(Departure_time)>=current_time() and Departure_station='"+sn.getString(1)+"' order by Departure_time;");
			
			String[] newRow = new String[4];
			
			while(rs.next()) {
				newRow[0] = rs.getString(1);
				newRow[1] = rs.getString(2);
				newRow[2] = rs.getString(3);
				newRow[3] = rs.getString(4);
				
				model.addRow(newRow);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
