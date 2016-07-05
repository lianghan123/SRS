package dao;

import java.util.List;

import model.Section;
import model.Student;

public interface StudentDao {
	List<Student> getAllStudents();
	Student getStudent(String Sssn);
	List<Section> getEnrolledSections(Student student);
	void addStudent(Student student);
	void deleteStudent(Student student);
}
