package jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dto.Department;

@SuppressWarnings("serial")
public class DeptPanel extends JPanel implements ActionListener {
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;
	private JButton btnAdd;
	private DepartmentDao dao = DepartmentDao.getInstance();
	private int floor;
	private String deptName;
	private int deptNo;
	private Department dept;
	private DeptTable deptPanel;

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public DeptPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();

		panel.setBorder(new EmptyBorder(20, 0, 0, 5));
		add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 2, 5, 35));

		JLabel lblDeptNo = new JLabel("부서번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDeptNo);

		tfDeptNo = new JTextField();
		panel.add(tfDeptNo);
		tfDeptNo.setColumns(10);

		JLabel lblDeptName = new JLabel("부서명");
		lblDeptName.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDeptName);

		tfDeptName = new JTextField();
		tfDeptName.setColumns(10);
		panel.add(tfDeptName);

		JLabel lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblFloor);

		tfFloor = new JTextField();
		tfFloor.setColumns(10);
		panel.add(tfFloor);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		panel.add(btnAdd);

		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		panel.add(btnCancel);

		deptPanel = new DeptTable();
		add(deptPanel);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "추가") {
			try {
				setDept();
				dao.insertDepartment(dept);
			} catch (Exception m) {
				JOptionPane.showMessageDialog(null, "숫자를 입력하세요");
				return;
			}
		}
		if (e.getActionCommand() == "삭제") {
			setDeptNo();
			dao.deleteDepartment(dept);

		}
		if (e.getActionCommand() == "검색") {
			setDeptNo();
			JOptionPane.showMessageDialog(null, dao.selectDepartmentByNo(dept));

		}
		if (e.getActionCommand() == "수정") {
			setDept();
			dao.updateDepartment(dept);

		}
		if (e.getActionCommand() == "취소") {
			clearTf();
		}

		deptPanel.showDepartment();
		clearTf();
	}

	public void clearTf() {
		tfDeptName.setText("");
		tfDeptNo.setText("");
		tfFloor.setText("");
	}

	private void setDeptNo() {

		deptNo = Integer.parseInt(tfDeptNo.getText());
		dept = new Department(deptNo);

	}

	private void setDept() {
		floor = Integer.parseInt(tfFloor.getText());
		deptName = tfDeptName.getText();
		deptNo = Integer.parseInt(tfDeptNo.getText());
		dept = new Department(deptNo, deptName, floor);
	}

	public void setEnable(boolean ok) {
		tfDeptName.setEnabled(ok);
		tfFloor.setEnabled(ok);
	}

}