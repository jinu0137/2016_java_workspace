package Project_Frame;

import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarView extends JFrame {
	JButton btn1 = new JButton("��");
	JLabel yl = new JLabel(new SimpleDateFormat("yyyy").format(new Date()));
	JButton btn2 = new JButton("��");
	JComboBox combo = new JComboBox();
	JPanel dayPanel = new JPanel();
	JLabel dayLabel = new JLabel();
	JLabel inLabel = new JLabel();
	JLabel outLabel = new JLabel();
	
	public CalendarView() {
		setTitle("�޷�");
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		ToolBar tb = new ToolBar();
		
		
		JPanel p1 = new JPanel();
		p1.add(btn1); p1.add(yl); p1.add(btn2); p1.add(new JLabel("��     ")); p1.add(combo); p1.add(new JLabel("��"));
		
		JPanel p2 = new JPanel(new GridLayout(1, 7));
		p2.add(new JLabel("�Ͽ���")); p2.add(new JLabel("������")); p2.add(new JLabel("ȭ����")); p2.add(new JLabel("������")); p2.add(new JLabel("�����")); p2.add(new JLabel("�ݿ���")); p2.add(new JLabel("�����"));
		
		JPanel p3 = new JPanel();
		for(int i=0; i<42)
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}