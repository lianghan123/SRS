package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.CourseDao;
import model.Course;
import model.Professor;
import model.Section;
import util.DBUtil;
public class CourseImpl implements CourseDao{
	@Override
	public List<Course> getAllCourses(){
		Connection Conn = DBUtil.getMySqlConnection();
		String sql = "select * from Course";
		List<Course> courseList = new ArrayList<Course>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String courseNo = rs.getString("courseNo");
				String courseName = rs.getString("courseName");
				double credits = rs.getDouble("credits");
				Course cs = new Course(courseNo, courseName, credits);
				cs.setCourseNo(courseNo);
				cs.setCourseName(courseName);
				cs.setCredits(credits);
				courseList.add(cs);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;
	}


	@Override
	public Course getCourse(String courseNo) {
		// TODO Auto-generated method stub
		String sql = "select * from Course where courseNo='" + courseNo + "'";
		Connection Conn = DBUtil.getMySqlConnection();
		Course cs = new Course(courseNo, null, 0);
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				cs.setCourseNo(courseNo);
				cs.setCourseName(rs.getString("courseName"));
				cs.setCredits(rs.getDouble("credits"));
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cs;
	}


	@Override
	public List<Section> getAllOfferedAsSection(Course course) {
		// TODO Auto-generated method stub
		Connection Conn = DBUtil.getMySqlConnection();
		String sql = "select * from Section,Professor where Section.Pssn=Professor.Pssn and Section.courseNo='"
				+ course.getCourseNo() + "'";
		List<Section> offeredAsSection = new ArrayList<Section>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sectionNo = rs.getInt("sectionNo");
				Section section = new Section(sectionNo, rs.getString("dayOfWeek"), rs.getString("timeOfDay"), course,
						rs.getString("room"), rs.getInt("seatingCapacity"));
				Professor professor = new Professor(rs.getString("professorName"), rs.getString("Pssn"),
						rs.getString("title"), rs.getString("department"));
				section.setInstructor(professor);
				offeredAsSection.add(section);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offeredAsSection;
	}

	@Override
	public List<Course> getPrerequisites(Course course) {
		// TODO Auto-generated method stub
		String courseNo = course.getCourseNo();
		List<Course> prerequisites = new ArrayList<Course>();
		String sql = "select * from PreRequisites,Course where PreRequisites.preCourseNo=Course.courseNo and PreRequisites.courseNo='"
				+ courseNo + "'";
		Connection Conn = DBUtil.getMySqlConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String preCourseNo = rs.getString("preCourseNo");
				Course preCourse = new Course(preCourseNo, rs.getString("courseName"), rs.getDouble("credits"));
				prerequisites.add(preCourse);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return prerequisites;	}

	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		String courseNo = course.getCourseNo();
		String courseName = course.getCourseName();
		double credits = course.getCredits();

		String sql = "insert into Course values('" + courseNo + "','" + courseName + "','" + credits + "')";
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
	public void deleteCourse(String courseNo) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getMySqlConnection();
		String sql = "delete from Course where courseNo='" + courseNo + "'";
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