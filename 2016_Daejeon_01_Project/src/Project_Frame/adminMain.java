package Project_Frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class adminMain extends JFrame implements ActionListener {
	JButton btn1 = new JButton("상품추가");
	JButton btn2 = new JButton("회원조회");
	JButton btn3 = new JButton("로그인");
	
	public adminMain() {
		setSize(220, 200);
		setLocationRelativeTo(null);
		
		JPanel p = new JPanel(new GridLayout(3, 1, 10, 10));
		p.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		p.add(btn1);
		p.add(btn2);
		p.add(btn3);
		
		add(p);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton bt = (JButton) e.getSource();
		
		if(bt==btn1) {
			new productInsert();
		} else if(bt==btn2) {
			new MemberSelect();
		} else if(bt==btn3) {
			this.dispose();
			new Login();
		}
	}
}
