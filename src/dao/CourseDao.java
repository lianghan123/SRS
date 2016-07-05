package dao;

import java.util.List;

import model.Course;
import model.Section;

public interface CourseDao {
	List<Course> getAllCourses();
	Course getCourse(String courseNo);
	List<Section> getAllOfferedAsSection(Course course);
	List<Course> getPrerequisites(Course course);
	void addCourse(Course course);
	void deleteCourse(String courseNo);
}
