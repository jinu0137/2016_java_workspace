package Project_Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
	String[] bn = {"����","����","����","����"};
	JButton[] btn = new JButton[4];
	JFrame frame;
	
	public ToolBar() {
		for(int i=0; i<4; i++) {
			String path = System.getProperty("user.dir")+"\\res\\menu\\"+bn[i]+".txt";
			path = path.replace('\\', '/');
			btn[i] = new JButton(new ImageIcon(path));
			add(btn[i]);
		}
		
		btn[0].setToolTipText("���⸦ �����ŵ�ϴ�.");
		btn[1].setToolTipText("���� ���¸� �����ݴϴ�.");
		btn[2].setToolTipText("���θ޴��� �̵��մϴ�.");
		btn[3].setToolTipText("�ý����� �����մϴ�.");
	}
}