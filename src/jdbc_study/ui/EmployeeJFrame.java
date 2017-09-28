package jdbc_study.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class EmployeeJFrame extends JFrame implements ActionListener {

	private JMenuItem mnDelete;
	private JMenuItem mnSerach;
	private JMenuItem mnChange;
	private JMenuItem mnInsert;
	private DepartmentUI deptUI;
	private DeptPanel dp;
	private EmpPanel ep;
	private boolean empSelect;
	private EmployeeUI empUI;

	public EmployeeJFrame(boolean selectED) {
		setTitle("사원, 부서 관리 프로그램");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mn = new JMenu(" 설정");
		mn.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mn);

		mnInsert = new JMenuItem("추가");
		mnInsert.setHorizontalAlignment(SwingConstants.CENTER);
		mnInsert.addActionListener(this);
		mn.add(mnInsert);

		mnChange = new JMenuItem("수정");
		mnChange.setHorizontalAlignment(SwingConstants.CENTER);
		mnChange.addActionListener(this);
		mn.add(mnChange);

		mnDelete = new JMenuItem("삭제");
		mnDelete.setHorizontalAlignment(SwingConstants.CENTER);
		mnDelete.addActionListener(this);
		mn.add(mnDelete);

		mnSerach = new JMenuItem("검색");
		mnSerach.setHorizontalAlignment(SwingConstants.CENTER);
		mnSerach.addActionListener(this);
		mn.add(mnSerach);

		if (selectED == false) {
			dp = new DeptPanel();
			dp.setBorder(new EmptyBorder(10, 10, 10, 10));
			setContentPane(dp);
			empSelect = false;
		} else {
			ep = new EmpPanel();
			ep.setBorder(new EmptyBorder(10, 10, 10, 10));
			setContentPane(ep);
			empSelect = true;
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "추가") {
			if (empSelect == false) {
				showDeptUI(e);
				deptSetEnabled(true);
			} else {
				showEmpUI(e);
				empSetEnabled(true);
			}

		}
		if (e.getActionCommand() == "삭제") {
			if (empSelect == false) {
				showDeptUI(e);
				deptSetEnabled(false);
			} else {
				showEmpUI(e);
				empSetEnabled(false);
			}

		}
		if (e.getActionCommand() == "검색") {
			if (empSelect == false) {
				showDeptUI(e);
				deptSetEnabled(false);
			}else {
				showEmpUI(e);
				empSetEnabled(false);
			}

		}
		if (e.getActionCommand() == "수정") {
			if (empSelect == false) {
				showDeptUI(e);
				deptSetEnabled(true);
			}else {
				showEmpUI(e);
				empSetEnabled(true);
			}

		}

	}

	private void showEmpUI(ActionEvent e) {
		if (empUI == null) {
			empUI = new EmployeeUI();
		}
		empUI.getBtnAdd().setText(e.getActionCommand());
		empUI.setVisible(true);
		revalidate();

	}
	
	private void empSetEnabled(boolean set){
		empUI.getTfDNo().setEnabled(set);
		empUI.getTfEmpName().setEnabled(set);
		empUI.getSpinner().setEnabled(set);
		empUI.getComboBox().setEnabled(set);
		empUI.getComboBox_1().setEnabled(set);
	}
	

	private void deptSetEnabled(boolean set) {
		deptUI.getTfDeptName().setEnabled(set);
		deptUI.getTfFloor().setEnabled(set);
	}

	

	private void showDeptUI(ActionEvent e) {
		if (deptUI == null) {
			deptUI = new DepartmentUI();
		}
		deptUI.getBtnAdd().setText(e.getActionCommand());
		deptUI.setVisible(true);
		revalidate();
	}

}
