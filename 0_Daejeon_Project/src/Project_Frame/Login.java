package Project_Frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Project_DBInterface.DBInterface;

public class Login extends JFrame {
	static JTextField id = new JTextField();
	JTextField pw = new JPasswordField();
	JButton btn = new JButton("LOGIN");
	JCheckBox cb = new JCheckBox("ID 저장");
	JLabel sign = new JLabel("SIGN UP");
	
	public Login() {
		setSize(300, 150);
		setLocationRelativeTo(null);
		
		JPanel p = new JPanel(new BorderLayout());
		
		JPanel p1 = new JPanel(new GridLayout(2, 1, 5, 5));
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		p1.add(new JLabel("아이디"));
		p1.add(new JLabel("비밀번호"));
		
		JPanel p2 = new JPanel(new GridLayout(2, 1, 5, 5));
		p2.setBorder(new EmptyBorder(5, 0, 5, 5));
		p2.add(id);
		p2.add(pw);
		
		JPanel p3 = new JPanel(new BorderLayout());
		p3.setBorder(new EmptyBorder(5, 0, 5, 5));
		p3.add(btn);
		
		JPanel p4 = new JPanel();
		p4.setBorder(new EmptyBorder(5, 0, 0, 0));
		Map at = sign.getFont().getAttributes();
		at.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		sign.setFont(sign.getFont().deriveFont(at));
		p4.add(cb); p4.add(sign);
		
		p.add(p1, BorderLayout.WEST);
		p.add(p2, BorderLayout.CENTER);
		p.add(p3, BorderLayout.EAST);
		p.add(p4, BorderLayout.SOUTH);
		
		add(p);
		
		try {
			ResultSet rs = DBInterface.Stmt.executeQuery("select * from log");
			if(rs.next()) {
				id.setText(rs.getString(1));
				cb.setSelected(true);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(id.getText().equals("admin") || pw.getText().equals("admin")) {
					idSave();
					dispose();
					new AdminMain();
				} else {
					try {
						ResultSet rs = DBInterface.Stmt.executeQuery("select * from member where id='"+id.getText()+"' and pw='"+pw.getText()+"'");
						if(rs.next()) {
							idSave();
							dispose();
							new MemberMain();
						} else {
							JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 잘못되었습니다.");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		sign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new Join();
			}
		});
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void idSave() {
		try {
			DBInterface.Stmt.execute("delete from log");
			if(cb.isSelected()) {
				DBInterface.Stmt.execute("INSERT INTO `project000`.`log` (`id`) VALUES ('"+id.getText()+"');");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
