package jdbc_study.ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import jdbc_study.dao.EmployeeDao;
import jdbc_study.dto.Employee;

@SuppressWarnings("serial")
public class EmpTable extends JPanel {

	private JTable empTable;

	public EmpTable() {
		setLayout(new BorderLayout(5, 5));

		JLabel lblNewLabel = new JLabel("사원관리");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		empTable = new JTable();

		scrollPane.setViewportView(empTable);
		showEmployee();
	}

	private Object[][] getDatas() {
		EmployeeDao dao = EmployeeDao.getInstance(); // 싱글턴 패턴
		List<Employee> lists = dao.selectEmployeeByAll();
		Object[][] ob = new Object[lists.size()][];
		for (int i = 0; i < lists.size(); i++) {
			ob[i] = lists.get(i).toArray();
		}
		return ob;
	}

	private String[] columnNames() {

		return new String[] { "empno", "empname", "title", "manager", "salary", "dno" };
	}

	public void showEmployee() {
		DefaultTableModel model = new DefaultTableModel(getDatas(), columnNames());
		empTable.setModel(model);
		revalidate();
	}

}