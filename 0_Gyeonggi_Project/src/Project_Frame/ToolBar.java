package Project_Frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import Project_DBInterface.DBInterface;

public class ToolBar extends JFrame implements ActionListener {
	JToolBar tb = new JToolBar();
	JButton[] btn = new JButton[4];
	String[] bn = {"����","����","����","����"};
	JFrame frame;
	
	public ToolBar(JFrame frame) {
		this.frame = frame;
		setLayout(new BorderLayout());
		
		for(int i=0; i<4; i++) {
			String path = System.getProperty("user.dir")+"\\�����ڷ�\\menu\\"+bn[i]+".png";
			path = path.replace('\\', '/');
			btn[i] = new JButton(new ImageIcon(path));
			btn[i].addActionListener(this);
			tb.add(btn[i]);
		}
		
		btn[0].setToolTipText("���⸦ �����ŵ�ϴ�.");
		btn[1].setToolTipText("���� ���¸� �����ݴϴ�.");
		btn[2].setToolTipText("���θ޴��� �̵��մϴ�.");
		btn[3].setToolTipText("�ý����� �����մϴ�.");
		
		frame.add(tb, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton bt = (JButton) e.getSource();
		
		if(bt==btn[0]) {
			try {
				Process calc = Runtime.getRuntime().exec("calc");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(bt==btn[1]) {
			try {
				int a=0, b=0;
				ResultSet rs = DBInterface.Stmt.executeQuery("select sum(amount) from ledger where division='����' and memberid='"+Login.id.getText()+"'");
				if(rs.next()) {
					a = rs.getInt(1);
				}
				
				rs = DBInterface.Stmt.executeQuery("select sum(amount) from ledger where division='����' and memberid='"+Login.id.getText()+"'");
				if(rs.next()) {
					b = rs.getInt(1);
				}
				
				String st;
				
				if(a-b>=0) {
					st = "<html><font color=#000FFF>���� :"+(a-b)+"</font>";
				} else {
					st = "<html><font color=#F00000>���� :"+(a-b)+"</font>";
				}
				
				JOptionPane.showMessageDialog(null, "�Ѽ��Ծ� : "+a+"��\n�� ����� : "+b+"��\n"+st);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(bt==btn[2]) {
			frame.dispose();
		} else if(bt==btn[3]) {
			System.exit(0);
		}
	}
}