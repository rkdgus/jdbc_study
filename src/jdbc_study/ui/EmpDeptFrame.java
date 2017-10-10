package jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class EmpDeptFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private EmpPanel empPanel1;
	private DeptPanel deptPanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpDeptFrame frame = new EmpDeptFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EmpDeptFrame() {
		setTitle("사원, 부서 관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 344);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Menu");

		menuBar.add(mnNewMenu);

		JMenuItem mnInsert = new JMenuItem("추가");
		mnInsert.addActionListener(this);
		mnNewMenu.add(mnInsert);

		JMenuItem mnDel = new JMenuItem("삭제");
		mnDel.addActionListener(this);
		mnNewMenu.add(mnDel);

		JMenuItem mnSearch = new JMenuItem("검색");
		mnSearch.addActionListener(this);
		mnNewMenu.add(mnSearch);

		JMenuItem mnChange = new JMenuItem("수정");
		mnChange.addActionListener(this);
		mnNewMenu.add(mnChange);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		deptPanel = new DeptPanel();
		empPanel1 = new EmpPanel();

		tabbedPane.add("사원", empPanel1);
		tabbedPane.add("부서", deptPanel);
		contentPane.add(tabbedPane);

	}

	public void actionPerformed(ActionEvent e) {
		empPanel1.getBtnAdd().setText(e.getActionCommand());
		deptPanel.getBtnAdd().setText(e.getActionCommand());
		deptPanel.clearTf();
		empPanel1.clearTf();
		if (e.getActionCommand() == "추가") {
			deptPanel.setEnable(true);
			empPanel1.setEnable(true);
		}
		if (e.getActionCommand() == "삭제") {
			deptPanel.setEnable(false);
			empPanel1.setEnable(false);
		}
		if (e.getActionCommand() == "검색") {
			deptPanel.setEnable(false);
			empPanel1.setEnable(false);
		}
		if (e.getActionCommand() == "수정") {
			deptPanel.setEnable(true);
			empPanel1.setEnable(true);
		}

	}

}