package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ProfessorDao;
import model.Course;
import model.Professor;
import model.Section;
import util.DBUtil;

public class professorImpl implements ProfessorDao {

	@Override
	public List<Professor> getAllProfessors() {
		Connection Conn = DBUtil.getMySqlConnection();
		String sql = "select * from Professor";
		List<Professor> professorList = new ArrayList<Professor>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String Pssn = rs.getString("Pssn");
				String title = rs.getString("title");
				String department = rs.getString("department");
				String professorName = rs.getString("professorName");
				Professor professor = new Professor(Pssn, professorName, title, department);
				professor.setSsn(Pssn);
				professor.setName(professorName);
				professor.setTitle(title);
				professor.setDepartment(department);
				professorList.add(professor);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return professorList;
	}

	@Override
	public Professor getProfessor(String Pssn) {
		Professor professor = new Professor(Pssn, null, null, null);
		Connection Conn = DBUtil.getMySqlConnection();
		String sql = "select * from Professor where Pssn='" + Pssn + "'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				String department = rs.getString("department");
				String professorName = rs.getString("professorName");
				professor.setSsn(Pssn);
				professor.setName(professorName);
				professor.setTitle(title);
				professor.setDepartment(department);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return professor;
	}

	@Override
	public List<Section> getSectionTeached(Professor professor) {
		String Pssn = professor.getSsn();
		String sql = "select * from Section,Course where Section.courseNo=Course.courseNo and Section.Pssn='" + Pssn
				+ "'";
		List<Section> sectionTeached = new ArrayList<Section>();
		Connection conn = DBUtil.getMySqlConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sectionNo = rs.getInt("sectionNo");
				Course course = new Course(rs.getString("courseNo"), rs.getString("courseName"),
						rs.getDouble("credits"));
				Section section = new Section(sectionNo, rs.getString("dayOfWeek"), rs.getString("timeOfDay"), course,
						rs.getString("room"), rs.getInt("seatingCapacity"));
				section.setInstructor(professor);
				sectionTeached.add(section);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sectionTeached;
	}

	@Override
	public void addProfessor(Professor professor) {
		String Pssn = professor.getSsn();
		String title = professor.getTitle();
		String department = professor.getDepartment();
		String professorName = professor.getName();

		String sql = "insert into Professor values('" + Pssn + "','" + title + "','" + department + "','"
				+ professorName + "','')";
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
	public void deleteProfessor(String ssn) {
		Connection conn = DBUtil.getMySqlConnection();
		String sql = "delete from Professor where Pssn='" + ssn + "'";
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