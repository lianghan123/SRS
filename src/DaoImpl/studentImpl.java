package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.CourseDao;
import dao.StudentDao;
import model.Course;
import model.Professor;
import model.Section;
import model.Student;
import util.DBUtil;

public class studentImpl implements StudentDao{ {

}

@Override
public List<Student> getAllStudents() {
	// TODO Auto-generated method stub
	Connection Conn = DBUtil.getMySqlConnection();
	String sql = "select * from Student";
	List<Student> studentList = new ArrayList<Student>();
	try {
		PreparedStatement pstmt = Conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			String name = rs.getString("name");
			String ssn = rs.getString("ssn");
			String major = rs.getString("major");
			String degree = rs.getString("degree");
			Student student = new Student(name,ssn,major,degree);
			student.setName(name);
			student.setSsn(ssn);
			student.setMajor(major);
			student.setDegree(degree);
			studentList.add(student);
		}
		rs.close();
		pstmt.close();
		Conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return studentList;
	}


@Override
public Student getStudent(String Sssn) {
	// TODO Auto-generated method stub
	Student student = new Student(Sssn);
	Connection Conn = DBUtil.getMySqlConnection();
	String sql = "select * from Student";
	try {
		PreparedStatement pstmt = Conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			String major = rs.getString("major");
			String degree = rs.getString("degree");
			String name = rs.getString("name");
			student.setSsn(Sssn);
			student.setName(name);
			student.setMajor(major);
			student.setDegree(degree);
		}
		rs.close();
		pstmt.close();
		Conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return student;
}


@Override
public List<Section> getEnrolledSections(Student student) {
	// TODO Auto-generated method stub
	String Sssn = student.getSsn();
	List<Section> enrolledSections = new ArrayList<Section>();	
	Connection Conn = DBUtil.getMySqlConnection();
	String sql =  "select * from SSection,Section,Course where SSection.sectionNo=Section.sectionNo and Section.courseNo=Course.courseNo and SSection.Sssn='"
			+ Sssn + "'";
	try {
		PreparedStatement pstmt = Conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			int sectionNo = rs.getInt("sectionNo");
			Course course = new Course(rs.getString("courseNo"), rs.getString("courseName"),
					rs.getDouble("credits"));
			Section section = new Section(sectionNo, rs.getString("dayOfWeek"), rs.getString("timeOfDay"), course,
					rs.getString("room"), rs.getInt("seatingCapacity"));
			Professor professor = new Professor(rs.getString("professorName"), rs.getString("Pssn"),
					rs.getString("title"), rs.getString("department"));
			section.setInstructor(professor);
			enrolledSections.add(section);
		}
		rs.close();
		pstmt.close();
		Conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return enrolledSections;
}

@Override
public void addStudent(Student student) {
	// TODO Auto-generated method stub
	String Sssn = student.getSsn();
	String major = student.getMajor();
	String degree = student.getDegree();
	String sName = student.getName();

	String sql = "insert into Student values('" + Sssn + "','" + sName + "','" + major + "','" + degree
			+ "')";
	Connection conn = DBUtil.getMySqlConnection();
	try {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	}
}


@Override
public void deleteStudent(Student student) {
	// TODO Auto-generated method stub
	String Sssn = student.getSsn();
	Connection conn = DBUtil.getMySqlConnection();
	String sql = "delete from Student where Sssn='" + Sssn + "'";
	try {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	}
}
}