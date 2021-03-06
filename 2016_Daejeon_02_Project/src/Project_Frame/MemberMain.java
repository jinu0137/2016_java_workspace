package Project_Frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MemberMain extends JFrame implements ActionListener {
	JButton[] btn = new JButton[4];
	String[] bn = {"상품목록","개인정보 수정","거래내역","로그아웃"};
	
	public MemberMain() {
		setSize(250, 300);
		setLocationRelativeTo(null);
		
		JPanel p = new JPanel(new GridLayout(4, 1, 10, 10));
		p.setBorder(new EmptyBorder(10, 10, 10, 10));
		for(int i=0; i<4; i++) {
			btn[i] = new JButton(bn[i]);
			btn[i].addActionListener(this);
			p.add(btn[i]);
		}
		
		add(p);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton bt = (JButton) e.getSource();
		
		if(bt==btn[0]) {
			new Products();
		} else if(bt==btn[1]) {
			new MemberUpdate();
		} else if(bt==btn[2]) {
			new Purchases();
		} else if(bt==btn[3]) {
			this.dispose();
			new Login();
		}
	}
}
