package jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import jdbc_study.dao.EmployeeDao;
import jdbc_study.dto.Employee;

/**
 * @author 강현
 *
 */
@SuppressWarnings("serial")
public class EmpPanel extends JPanel implements ActionListener {
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private JTextField tfDno;
	private EmployeeDao dao = EmployeeDao.getInstance();
	private JButton btnAdd;
	private JButton btnCancel;
	private JSpinner spinner;
	private JComboBox<Integer> comManager;
	private JComboBox<String> comTitle;
	private Employee emp;
	private EmpTable empPanel;

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public EmpPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(20, 0, 0, 5));
		add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 2, 5, 5));

		JLabel lblEmpNo = new JLabel("사원번호");
		lblEmpNo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblEmpNo);

		tfEmpNo = new JTextField();
		tfEmpNo.setColumns(10);
		panel.add(tfEmpNo);

		JLabel lblEmpName = new JLabel("성명");
		lblEmpName.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblEmpName);

		tfEmpName = new JTextField();
		tfEmpName.setColumns(10);
		panel.add(tfEmpName);

		JLabel lblTitle = new JLabel("직급");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTitle);

		comTitle = new JComboBox<>();
		List<String> list = dao.selectTitle();
		for (String t : list) {
			comTitle.addItem(t);
		}

		panel.add(comTitle);

		JLabel lblManager = new JLabel("직속상사");
		lblManager.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblManager);

		comManager = new JComboBox<>();
		List<Integer> mList = dao.selectManager();
		for (int m : mList) {
			comManager.addItem(m);
		}
		panel.add(comManager);

		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblSalary);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(20)));
		panel.add(spinner);

		JLabel lblDno = new JLabel("부서번호");
		lblDno.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDno);

		tfDno = new JTextField();
		tfDno.setColumns(10);
		panel.add(tfDno);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		panel.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		panel.add(btnCancel);

		empPanel = new EmpTable();
		add(empPanel, BorderLayout.CENTER);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "추가") {
			setEmp();
			dao.insertEmployee(emp);
		}
		if (e.getActionCommand() == "삭제") {
			setEmpNo();
			dao.deleteEmployee(emp);
		}
		if (e.getActionCommand() == "검색") {
			setEmpNo();
			dao.selectEmployeeByEmpno(emp);
		}
		if (e.getActionCommand() == "수정") {
			setEmp();
			dao.updateEmployee(emp);
		}
		if (e.getActionCommand() == "취소") {
			clearTf();
		}
		empPanel.showEmployee();
		clearTf();
	}

	private void setEmp() {
		int empno = Integer.parseInt(tfEmpNo.getText());
		String empname = tfEmpName.getText();
		String title = String.valueOf(comTitle.getSelectedItem());
		int manager = (int) comManager.getSelectedItem();
		int salary = (int) spinner.getValue();
		int dno = Integer.parseInt(tfDno.getText());
		emp = new Employee(empno, empname, title, manager, salary, dno);
	}

	public void setEnable(boolean ok) {
		tfDno.setEnabled(ok);
		tfEmpName.setEnabled(ok);
		spinner.setEnabled(ok);
		comManager.setEnabled(ok);
		comTitle.setEnabled(ok);

	}

	public void setEmpNo() {
		int empno = Integer.parseInt(tfEmpNo.getText());
		emp = new Employee(empno);
	}

	public void clearTf() {
		tfEmpNo.setText("");
		tfDno.setText("");
		tfEmpName.setText("");
		spinner.setValue(0);
		comManager.setSelectedItem("");
		comTitle.setSelectedItem("");
	}

}