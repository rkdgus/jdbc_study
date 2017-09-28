package jdbc_study.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dto.Department;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class DepartmentUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;
	private JButton btnCancel;
	private JButton btnAdd;
	private int deptNo;
	private String deptName;
	private int floor;
	private Department dept;
	private DepartmentDao dao = DepartmentDao.getInstance();
	private DeptPanel dp;
	

	public JTextField getTfDeptNo() {
		return tfDeptNo;
	}

	public JTextField getTfDeptName() {
		return tfDeptName;
	}

	public JTextField getTfFloor() {
		return tfFloor;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public DepartmentUI() {
		setTitle("부서관리");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 100, 282, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel,BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 10, 10));

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

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(20);
		contentPane.add(panel_1,BorderLayout.SOUTH);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		panel_1.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		panel_1.add(btnCancel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "추가") {
			setDept();
			dao.insertDepartment(dept);
			clearTf();
			setVisible(false);
			
			dp.revalidate();
			
			
		}
		if (e.getActionCommand() == "취소") {
			setVisible(false);
			clearTf();
			
		}
		if (e.getActionCommand() == "검색") {
			setDeptNo();
			JOptionPane.showMessageDialog(null, dao.selectDepartmentByNo(dept));
			clearTf();
		}
		if (e.getActionCommand() == "삭제") {
			setDeptNo();
			dao.deleteDepartment(dept);
			clearTf();
			setVisible(false);

		}
		if (e.getActionCommand() == "수정") {
			setDept();
			dao.updateDepartment(dept);
			clearTf();
			setVisible(false);
			
		}
		
		
	}

	private void clearTf() {
		tfDeptName.setText("");
		tfDeptNo.setText("");
		tfFloor.setText("");
	}

	private void setDeptNo() {
		deptNo = Integer.parseInt(tfDeptNo.getText());
		dept = new Department(deptNo);
	}

	private void setDept() {
		deptNo = Integer.parseInt(tfDeptNo.getText());
		deptName = tfDeptName.getText();
		floor = Integer.parseInt(tfFloor.getText());
		dept = new Department(deptNo, deptName, floor);
	}
	
	
}
