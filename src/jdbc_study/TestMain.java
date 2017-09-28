package jdbc_study;

import java.util.List;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dao.EmployeeDao;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;

public class TestMain {

	public static void main(String[] args) {
		/*DBCon dbcon = DBCon.getInstance();
		System.out.println(dbcon);
		System.out.println(dbcon.getConn());
	
		
		
		DBCon dbcon1 = DBCon.getInstance();
		System.out.println(dbcon1);
		System.out.println(dbcon1.getConn());
		
		dbcon.connClose();*/
		
		department();
		
//		employee();
		
		
		
	}

	private static void employee() {
		showEmployee();
		
		EmployeeDao dao = EmployeeDao.getInstance();
		Employee emp = new Employee(4377);
		System.out.println(dao.selectEmployeeByEmpno(emp));
		System.out.println("------------------------------------------");
		
		Employee emp1 = new Employee(1234,"아이유","사원",4377,2000000,1);
		dao.insertEmployee(emp1);
		showEmployee();
		
		
		
		
		Employee emp3 = new Employee(1234,"아이유","대리",4377,2000000,1);
		dao.updateEmployee(emp3);
		showEmployee();
		
		Employee emp2 = new Employee(1234);
		dao.deleteEmployee(emp2);
		showEmployee();
		
	}

	private static EmployeeDao showEmployee() {
		EmployeeDao dao = EmployeeDao.getInstance();
		List<Employee> list = dao.selectEmployeeByAll();
		for(Employee e:list){
			System.out.println(e);
		}
		System.out.println("------------------------------------------");
		return dao;
		
	}

	private static void department() {
		DepartmentDao dao =DepartmentDao.getInstance();
		showDepartment();
		
		
		
				//insert
		Department dept = new Department(6, "마케팅", 10);
		dao.insertDepartment(dept);
		showDepartment();
		
				//update
		Department dept2 = new Department(6, "빅데이터마케팅", 10);
		dao.updateDepartment(dept2);
		showDepartment();
		
				//delete
		Department dept1 = new Department(6);
		dao.deleteDepartment(dept1);
		showDepartment();
		
				//select where
	
		Department dept3 = new Department(1);
		
		System.out.println(dao.selectDepartmentByNo(dept3));
	}

	private static DepartmentDao showDepartment() {
		DepartmentDao dao = DepartmentDao.getInstance(); //싱글턴 패턴
		List<Department> lists = dao.selectDepartmentByAll();
		for(Department d : lists){
			System.out.println(d);
		}
		System.out.println("--------------------------------------");
		return dao;
	}

}
