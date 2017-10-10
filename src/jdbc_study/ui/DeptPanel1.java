package jdbc_study.ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dto.Department;

@SuppressWarnings("serial")
public class DeptPanel1 extends JPanel {

	private JTable deptTable;

	public DeptPanel1() {
		setLayout(new BorderLayout(5, 5));
		
		JLabel lblNewLabel = new JLabel("부서관리");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel,BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		deptTable = new JTable();
		
		scrollPane.setViewportView(deptTable);
		showDepartment();
	}

	private Object[][] getDatas() {
		DepartmentDao dao = DepartmentDao.getInstance(); //싱글턴 패턴
		List<Department> lists = dao.selectDepartmentByAll();
		Object[][] ob = new Object[lists.size()][];
		for(int i=0;i<lists.size();i++){
			ob[i]=lists.get(i).toArray();
		}
		return ob;
	}

	private String[] columnNames() {

		return new String[] { "deptNo", "deptName", "floor" };
	}
	

	public void showDepartment() {
		DefaultTableModel model = new DefaultTableModel(getDatas(),columnNames());
		deptTable.setModel(model);
		revalidate();
	}
	
}
