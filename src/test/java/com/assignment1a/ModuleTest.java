package com.assignment1a;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class ModuleTest {

    Module CS101 = new Module("CS101", 101);
    Module CS102 = new Module("CS102", 102);

    @BeforeEach
    public void setup() {

        //Create courses
        Course CS = new Course("CS", new LocalDate(2022, 9, 1), new LocalDate(2023, 6, 17));
        Course MA = new Course("MA", new LocalDate(2022, 9, 15), new LocalDate(2023, 6, 17));

        //Assign courses to modules
        CS101.addAssociatedCourse(CS);  CS102.addAssociatedCourse(CS);
        CS101.addAssociatedCourse(MA);  CS102.addAssociatedCourse(MA);

        //Create students
        Student student1 = new Student("Monkey D Luffy", new LocalDate(1995, 6, 5), 1);
        Student student2 = new Student("Roronoa Zoro", new LocalDate(1994, 7, 23), 2);
        Student student3 = new Student("Nami", new LocalDate(1993, 9, 2), 3);
        Student student4 = new Student("Usopp", new LocalDate(1998, 1, 16), 4);
        Student student5 = new Student("Sanji", new LocalDate(1993, 12, 24), 5);
        Student student6 = new Student("Tony Tony Chopper", new LocalDate(1992, 4, 19), 6);
        Student student7 = new Student("Nico Robin", new LocalDate(1997, 2, 23), 7);
        Student student8 = new Student("Franky", new LocalDate(1994, 8, 10), 8);

        //Add students to modules
        CS101.addStudent(student1);     CS102.addStudent(student1);
        CS101.addStudent(student2);     CS102.addStudent(student2);
        CS101.addStudent(student3);     CS102.addStudent(student3);
        CS101.addStudent(student4);     CS102.addStudent(student4);
        CS101.addStudent(student5);     CS102.addStudent(student5);
        CS101.addStudent(student6);     CS102.addStudent(student6);
        CS101.addStudent(student7);     CS102.addStudent(student7);
        CS101.addStudent(student8);     CS102.addStudent(student8);

        //Create lecturers responsible
        Lecturer John = new Lecturer("John Smith", new LocalDate(1990, 1, 25), 1);
        Lecturer Patrick = new Lecturer("Patrick Sweeney", new LocalDate(1970, 8, 4), 2);

        //Assign lecturers responsible to modules
        CS101.addLecturerResponsible(John);
        CS102.addLecturerResponsible(Patrick);

    }

    @Test
    public void setAndGetAssociatedCoursesTest() {

        CS101.setAssociatedCourses(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), CS101.getAssociatedCourses());

    }

    @Test
    public void setAndGetStudentsTest() {

        CS102.setStudents(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), CS102.getStudents());

    }

    @Test
    public void addAssociatedCourseTest() {

        Course EN = new Course("EN", new LocalDate(2022, 9, 1), new LocalDate(2023, 6, 17));

        final String expected = "\n" +
                "Module{\n" +
                "name: 'CS101', id: '101', lecturerResponsible: 'john.smith32', \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, nami29, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28], \n" +
                "associatedCourses: [CS, MA]\n" +
                "}";

        Assertions.assertEquals(expected, CS101.toString());

        CS101.addAssociatedCourse(EN);

        final String updatedExpected = "\n" +
                "Module{\n" +
                "name: 'CS101', id: '101', lecturerResponsible: 'john.smith32', \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, nami29, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28], \n" +
                "associatedCourses: [CS, MA, EN]\n" +
                "}";

        Assertions.assertEquals(updatedExpected, CS101.toString());

    }

    @Test
    public void removeAssociatedCourseTest() {

        CS102.removeAssociatedCourse(CS102.getAssociatedCourses().get(1));

        final String expected = "\n" +
                "Module{\n" +
                "name: 'CS102', id: '102', lecturerResponsible: 'patrick.sweeney52', \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, nami29, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28], \n" +
                "associatedCourses: [CS]\n" +
                "}";

        Assertions.assertEquals(expected, CS102.toString());

    }

    @Test
    public void addStudentTest() {

        Student NEWGUY = new Student("NEWGUY", new LocalDate(2002, 3, 19), 9);

        final String expected = "\n" +
                "Module{\n" +
                "name: 'CS101', id: '101', lecturerResponsible: 'john.smith32', \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, nami29, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28], \n" +
                "associatedCourses: [CS, MA]\n" +
                "}";

        Assertions.assertEquals(expected, CS101.toString());

        CS101.addStudent(NEWGUY);

        final String updatedExpected = "\n" +
                "Module{\n" +
                "name: 'CS101', id: '101', lecturerResponsible: 'john.smith32', \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, nami29, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28, newguy20], \n" +
                "associatedCourses: [CS, MA]\n" +
                "}";

        Assertions.assertEquals(updatedExpected, CS101.toString());

    }

    @Test
    public void removeStudentTest() {

        CS101.removeStudent(CS101.getStudents().get(2)); //removing student nami29

        final String expected = "\n" +
                "Module{\n" +
                "name: 'CS101', id: '101', lecturerResponsible: 'john.smith32', \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28], \n" +
                "associatedCourses: [CS, MA]\n" +
                "}";

        Assertions.assertEquals(expected, CS101.toString());

    }

    @Test
    public void addLecturerResponsibleTest() {

        Lecturer NEWTEACH = new Lecturer("NEWTEACH", new LocalDate(1995, 9, 20), 3);

        final String expected = "\n" +
                "Module{\n" +
                "name: 'CS101', id: '101', lecturerResponsible: 'john.smith32', \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, nami29, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28], \n" +
                "associatedCourses: [CS, MA]\n" +
                "}";

        Assertions.assertEquals(expected, CS101.toString());

        CS101.addLecturerResponsible(NEWTEACH);

        final String updatedExpected = "\n" +
                "Module{\n" +
                "name: 'CS101', id: '101', lecturerResponsible: 'newteach27', \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, nami29, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28], \n" +
                "associatedCourses: [CS, MA]\n" +
                "}";

        Assertions.assertEquals(updatedExpected, CS101.toString());

        //Checks if the new Lecturer also got the module assigned to them
        Assertions.assertTrue(NEWTEACH.getTeachingModules().contains(CS101));

    }

    @Test
    public void removeLecturerResponsibleTest() {

        CS101.removeLecturerResponsible(CS101.getLecturerResponsible()); //removing the lecturer responsible

        final String expected = "\n" +
                "Module{\n" +
                "name: 'CS101', id: '101', lecturerResponsible: 'No lecturer assigned to this module', \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, nami29, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28], \n" +
                "associatedCourses: [CS, MA]\n" +
                "}";

        Assertions.assertEquals(expected, CS101.toString());

    }

}
