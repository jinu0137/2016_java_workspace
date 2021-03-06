package Project_Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Project_DBInterface.DBInterface;

public class CalendarView extends JFrame implements ActionListener {
	JButton btn1 = new JButton("◀");
	JButton btn2 = new JButton("▶");
	JLabel yLabel = new JLabel(new SimpleDateFormat("yyyy").format(new Date()));
	JComboBox combo = new JComboBox();
	JLabel[] wl = new JLabel[7];
	String[] wn = {"일","월","화","수","목","금","토"};
	JPanel[] pp = new JPanel[42];
	JLabel[] day = new JLabel[42];
	JLabel[] in = new JLabel[42];
	JLabel[] out = new JLabel[42];
	
	public CalendarView() {
		setTitle("달력");
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		new ToolBar(this);
		
		JPanel p1 = new JPanel();
		p1.add(btn1); p1.add(yLabel); p1.add(btn2); p1.add(new JLabel("년     ")); p1.add(combo); p1.add(new JLabel("월"));
		
		JPanel p2 = new JPanel(new GridLayout(1, 7));
		for(int i=0; i<7; i++) {
			wl[i] = new JLabel(wn[i], JLabel.CENTER);
			p2.add(wl[i]);
		}
		wl[0].setForeground(Color.red);
		wl[1].setForeground(Color.blue);
		
		JPanel p3 = new JPanel(new GridLayout(6, 7));
		p3.setBorder(new EmptyBorder(0, 0, 10, 0));
		for(int i=0; i<42; i++) {
			pp[i] = new JPanel();
			pp[i].setBorder(new LineBorder(Color.gray));
			pp[i].setLayout(new BoxLayout(pp[i], BoxLayout.Y_AXIS));
			
			day[i] = new JLabel();
			day[i].setBorder(new EmptyBorder(5, 0, 5, 0));
			in[i] = new JLabel();
			out[i] = new JLabel();
			out[i].setBorder(new EmptyBorder(5, 0, 5, 0));
			
			pp[i].add(day[i]);
			pp[i].add(in[i]);
			pp[i].add(out[i]);
			p3.add(pp[i]);
		}
		
		JPanel s1 = new JPanel(new BorderLayout());
		s1.add(p2, BorderLayout.NORTH);
		s1.add(p3, BorderLayout.CENTER);
		
		JPanel s2 = new JPanel(new BorderLayout());
		s2.add(p1, BorderLayout.NORTH);
		s2.add(s1, BorderLayout.CENTER);
		
		add(s2);
		
		Init();
		Setting();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				new Main();
			}
		});
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		combo.addActionListener(this);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btn1)) {
			yLabel.setText(Integer.toString(Integer.parseInt(yLabel.getText())-1));
		} else if(e.getSource().equals(btn2)) {
			yLabel.setText(Integer.toString(Integer.parseInt(yLabel.getText())+1));
		}
		
		Setting();
	}
	
	public void Init() {
		for(int i=1; i<13; i++)  {
			combo.addItem(i);
		}
		
		yLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		combo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		for(int i=0; i<42; i++)  {
			in[i].setForeground(Color.blue);
			out[i].setForeground(Color.red);
		}
		
		combo.setSelectedIndex(new Date().getMonth());
	}
	
	public void Setting() {
		for(int i=0; i<42; i++) {
			day[i].setText("");
			in[i].setText("");
			out[i].setText("");
			in[i].setToolTipText(null);
			out[i].setToolTipText(null);
		}
		
		GregorianCalendar cal = new GregorianCalendar(Integer.parseInt(yLabel.getText()), combo.getSelectedIndex(), 1);
		
		int sd = cal.get(Calendar.DAY_OF_WEEK);
		int ed = cal.getActualMaximum(Calendar.DATE);
		
		int cnt=1;
		
		for(int i=sd-1; i<sd+ed-1; i++) {
			day[i].setText(String.format("%02d", cnt));
			String d = yLabel.getText()+"-"+String.format("%02d", Integer.parseInt(combo.getSelectedItem().toString()))+"-"+day[i].getText();
			
			try {
				ResultSet rs = DBInterface.Stmt.executeQuery("select * from ledger where date='"+d+"' and division='수입'");
				if(rs.next()) {
					in[i].setText("수입: "+rs.getString(6)+"원");
					if(!rs.getString(7).equals("")) {
						in[i].setToolTipText(rs.getString(7));
					}
				}
				
				rs = DBInterface.Stmt.executeQuery("select * from ledger where date='"+d+"' and division='지출'");
				if(rs.next()) {
					out[i].setText("지출: "+rs.getString(6)+"원");
					if(!rs.getString(7).equals("")) {
						out[i].setToolTipText(rs.getString(7));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cnt++;
		}
	}
}
