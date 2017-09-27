package jdbc_study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc_study.dto.Employee;
import jdbc_study.jdbc.DBCon;
import jdbc_study.jdbc.JdbcUtil;

public class EmployeeDao {
	private static final EmployeeDao instance = new EmployeeDao();
	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection con;
	
	public static EmployeeDao getInstance() {
		return instance;
	}
	
	public List<Employee> selectEmployeeByAll(){
		List<Employee> list = new ArrayList<>();
		sql = "select empno, empname, title, manager, salary, dno from employee";
		con = DBCon.getInstance().getConn();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int empno = rs.getInt("empno");
				String empname = rs.getString("empname");
				String title = rs.getString("title");
				int manager = rs.getInt("manager");
				int salary = rs.getInt("salary");
				int dno = rs.getInt("dno");
				list.add(new Employee(empno, empname, title, manager, salary, dno));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return list;
		
	}
	
	public Employee selectEmployeeByEmpno(Employee emp){
		Employee employee = null;
		sql = "select empno, empname, title, manager, salary, dno from employee where empno=?";
		con = DBCon.getInstance().getConn();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			rs = pstmt.executeQuery();
			if(rs.next()){
				int empno = rs.getInt("empno");
				String empname = rs.getString("empname");
				String title = rs.getString("title");
				int manager = rs.getInt("manager");
				int salary = rs.getInt("salary");
				int dno = rs.getInt("dno");
				employee =new Employee(empno, empname, title, manager, salary, dno);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return employee;
		
	}

	public void insertEmployee(Employee emp1) {
		sql="insert into employee values(?,?,?,?,?,?)";
		try {
			pstmt = DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setInt(1, emp1.getEmpno());
			pstmt.setString(2, emp1.getEmpname());
			pstmt.setString(3, emp1.getTitle());
			pstmt.setInt(4, emp1.getManager());
			pstmt.setInt(5, emp1.getSalary());
			pstmt.setInt(6, emp1.getDno());
			
			int res = pstmt.executeUpdate();
			if(res<0){
				System.out.println("삽입실패");
				return;
			}
			System.out.println(emp1+"추가하였습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		
	}

	public void deleteEmployee(Employee emp2) {
		sql ="delete from employee where empno=?";
		try {
			pstmt = DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setInt(1, emp2.getEmpno());
			
			int res = pstmt.executeUpdate();
			if(res<0){
				System.out.println("삭제실패");
				return;
			}
			System.out.println(emp2+"삭제하였습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		
		
	}

	public void updateEmployee(Employee emp3) {
		sql = "update employee set title=? where empno=?";
		try {
			pstmt = DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setString(1, emp3.getTitle());
			pstmt.setInt(2, emp3.getEmpno());
			
			int res = pstmt.executeUpdate();
			if(res<0){
				System.out.println("수정실패");
				return;
			}
			System.out.println(emp3+"수정하였습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		
	}
	
}
