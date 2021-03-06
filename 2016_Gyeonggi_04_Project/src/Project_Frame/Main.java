package Project_Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class Main extends JFrame implements ActionListener {
	JButton[] btn = new JButton[6];
	String[] bn = {"달력","입력","나역","차트","회원정보","종료"};
	JLabel[] l = new JLabel[6];
	String[] ln = {"가계북 달력보기","가계부 입력","수입&지출 조회","수입&지출 차트","회원정보관리","종료하기"};
	
	public Main() {
		setTitle("메인");
		setSize(600, 400);
		setLocationRelativeTo(null);
		
		JPanel p = new JPanel(new GridLayout(2, 3));
		for(int i=0; i<6; i++) {
			JPanel pp = new JPanel(new BorderLayout());
			
			String path = System.getProperty("user.dir")+"\\res\\main\\"+bn[i]+".png";
			path = path.replace('\\', '/');
			btn[i] = new JButton(new ImageIcon(path));
			btn[i].setBackground(Color.white);
			btn[i].setBorderPainted(false);
			btn[i].setOpaque(false);
			
			l[i] = new JLabel(ln[i], JLabel.CENTER);
			
			pp.add(btn[i], BorderLayout.CENTER);
			pp.add(l[i], BorderLayout.SOUTH);
		}
		
		add(p);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton bt = (JButton) e.getSource();
		if(bt==btn[0]) {
			
		} else if(bt==btn[1]) {
			
		} else if(bt==btn[2]) {
			
		} else if(bt==btn[3]) {
			
		} else if(bt==btn[4]) {
			
		} else if(bt==btn[5]) {
			System.exit(0);
		}
	}
}
