package jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import jdbc_study.dao.EmployeeDao;
import jdbc_study.dto.Employee;

@SuppressWarnings("serial")
public class EmployeeUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private JTextField tfDNo;
	private JButton btnCancel;
	private JButton btnAdd;
	private Employee emp;
	private EmployeeDao dao = EmployeeDao.getInstance();
	private JSpinner spinner;
	private JComboBox comboBox;
	private JComboBox comboBox_1;

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}
	

	public JTextField getTfEmpNo() {
		return tfEmpNo;
	}

	public JTextField getTfEmpName() {
		return tfEmpName;
	}

	public JTextField getTfDNo() {
		return tfDNo;
	}

	public JSpinner getSpinner() {
		return spinner;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public JComboBox getComboBox_1() {
		return comboBox_1;
	}

	public EmployeeUI() {
		setTitle("사원관리");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 100, 301, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 5, 5));

		JLabel label = new JLabel("사원번호");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);

		tfEmpNo = new JTextField();
		tfEmpNo.setColumns(10);
		panel.add(tfEmpNo);

		JLabel label_1 = new JLabel("성명");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);

		tfEmpName = new JTextField();
		tfEmpName.setColumns(10);
		panel.add(tfEmpName);

		JLabel label_2 = new JLabel("직급");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);

		comboBox_1 = new JComboBox();
		List<String> list = dao.selectTitle();
		for(Object t:list){
			comboBox_1.addItem(t);
		}
		panel.add(comboBox_1);

		JLabel label_3 = new JLabel("직속상사");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);

		comboBox = new JComboBox();
		List<Integer> list1 = dao.selectManager();
		for (Object m : list1) {
			comboBox.addItem(m);
		}

		panel.add(comboBox);

		JLabel label_4 = new JLabel("급여");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(20)));
		panel.add(spinner);

		JLabel label_5 = new JLabel("부서번호");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_5);

		tfDNo = new JTextField();
		tfDNo.setColumns(10);
		panel.add(tfDNo);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(20);
		contentPane.add(panel_1, BorderLayout.SOUTH);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		panel_1.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		panel_1.add(btnCancel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "추가") {
			setEmp();
			dao.insertEmployee(emp);
			
			clearTf();
			setVisible(false);

		}
		if (e.getActionCommand() == "취소") {
			setVisible(false);
			clearTf();

		}
		if (e.getActionCommand() == "검색") {
			setEmpNo();
			
			JOptionPane.showMessageDialog(null, dao.selectEmployeeByEmpno(emp));
			clearTf();
		}
		if (e.getActionCommand() == "삭제") {
			setEmpNo();
			
			dao.deleteEmployee(emp);
			clearTf();
			setVisible(false);

		}
		if (e.getActionCommand() == "수정") {
			setEmp();
			dao.updateEmployee(emp);
			clearTf();
			setVisible(false);
			
			
		}

	}


	
	private void clearTf() {
		tfEmpNo.setText("");
		tfEmpName.setText("");
		tfDNo.setText("");
	}

	private void setEmpNo() {
		
		int empno = Integer.parseInt(tfEmpNo.getText());
		emp = new Employee(empno);
	}

	private void setEmp() {
		int empno = Integer.parseInt(tfEmpNo.getText());
		String empname = tfEmpName.getText();
		String title = (String) comboBox_1.getSelectedItem();
		int manager = (int) comboBox.getSelectedItem();
		int salary = (int) spinner.getValue();
		int dno = Integer.parseInt(tfDNo.getText());
		emp = new Employee(empno, empname, title, manager, salary, dno);
	}

}
