package jdbc_study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class Student{
	private String id;
	private String name;
	private String dept;
	
	
	
	public Student(String id) {
		this.id = id;
	}
	
	public Student(String id, String name, String dept) {
		this.id = id;
		this.name = name;
		this.dept = dept;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return String.format("Student [id=%s, name=%s, dept=%s]", id, name, dept);
	}
	
	
	
}

public class DBConnection {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/sampledb?autoReconnect=true&useSSL=false";
		String user = "user_sampledb";
		String password = "rootroot";
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버를 로딩했습니다.");

			con =DriverManager.getConnection(url, user, password);
			System.out.println("Datadase 연결 성공");
			
			
			showList(con);
			insertStudent(con, "1234567", "배수지", "성악과");
			showList(con);
			
			Student updateStd = new Student("1234567", "배수지", "컴퓨터공학과");
			updateStudent(con, updateStd);
			showList(con);
			
			Student delStd =new Student("1234567");
			deleteStd(con, delStd);
			showList(con);
			
			List<Student> stdList = getStudents(con);
			for(Student s:stdList){
				System.out.println(s);
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Djdbc Driver가 존재하지 않음");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Database 연결오휴 url혹은 user및 password 확인");
			System.err.printf("error Code : %s error Message : %s%n",e.getErrorCode(),e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private static List<Student> getStudents(Connection con) throws SQLException {
		ArrayList<Student> lists = new ArrayList<>();
		String sql ="select id, name, dept from student";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			lists.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3)));
			
		}
		
		return lists;
	}

	private static void deleteStd(Connection con, Student delStd) throws SQLException{
		String sql = "delete from student where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, delStd.getId());
		
		System.out.println(pstmt);
		int res = pstmt.executeUpdate();
		System.out.println(res+"개 삭제");
		pstmt.close();
		
	}

	private static void updateStudent(Connection con, Student updateStd) throws SQLException {
		String sql = "update student set dept=?,name=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, updateStd.getDept());
		pstmt.setString(2, updateStd.getName());
		pstmt.setString(3, updateStd.getId());
		
		System.out.println(pstmt);
		int res = pstmt.executeUpdate();
		System.out.println(res+"개 수정");
		pstmt.close();
	}

	private static void insertStudent(Connection con, String id , String name ,String dept) throws SQLException {
		String sql = "insert into student values(?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql); //준비작업
		pstmt.setString(1, id);
		pstmt.setString(2, name);
		pstmt.setString(3, dept);
		
//		System.out.println(pstmt);
		int res = pstmt.executeUpdate();
		System.out.println(res+"개 삽입");
		pstmt.close();
	}

	private static void showList(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "select id,name,dept from student";
		ResultSet rs =stmt.executeQuery(sql);
		while(rs.next()){
			String	id = rs.getString(1);
			String	name = rs.getString("name");
			String	dept = rs.getString(3);
			System.out.printf("id : %s name : %s  dept :%s%n",id,name,dept);
		}
		rs.close();
		stmt.close();
		
	}
}
