package dao;
// SRSDataAccess.java - Chapter 15, Java 5 version.

// Copyright 2005 by Jacquie Barker - all rights reserved.

// A DATA ACCESS LAYER class.


// By hiding all of the details of object persistence in this class, we minimize the impact
// to our application if such details need to change down the road; e.g., if we decide to
// adopt relational database technology rather than file persistence.

import java.io.*;
import java.util.*;

import model.Course;
import model.CourseCatalog;
import model.Faculty;
import model.InvalidStudentException;
import model.SRSInitializationException;
import model.ScheduleOfClasses;
import model.Section;
import model.Student;
import model.StudentPersistenceException;

public class SRSDataAccess {
		private static String daoName = "sqliteDao";

		public static CourseDao createCourseDao() {
			CourseDao result = null;
			try {
				Object o = Class.forName(daoName + "." + "CourseImpl").newInstance();
				result = (CourseDao)o;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		
		public static StudentDao createStudentDao() {
			StudentDao result = null;
			try {
				Object o = Class.forName(daoName + "." + "StudentImpl").newInstance();
				result = (StudentDao)o;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		
		public static ProfessorDao createProfessorDao() {
			ProfessorDao result = null;
			try {
				Object o = Class.forName(daoName + "." + "ProfessorImpl").newInstance();
				result = (ProfessorDao)o;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		
		
		public static SectionDao createSectionDao() {
			SectionDao result = null;
			try {
				Object o = Class.forName(daoName + "." + "SectionImpl").newInstance();
				result = (SectionDao)o;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		
		public static TranscriptDao createTranscriptDao() {
			TranscriptDao result = null;
			try {
				Object o = Class.forName(daoName + "." + "TranscriptImpl").newInstance();
				result = (TranscriptDao)o;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		
		
		}
		
	
		
	
