package com.assignment1a;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class CourseTest {

    Course CS = new Course("CS", new LocalDate(2022, 9, 1), new LocalDate(2023, 6, 17));
    Course MA = new Course("MA", new LocalDate(2022, 9, 15), new LocalDate(2023, 6, 17));

    @BeforeEach
    public void setup() {

        //Create modules
        Module CS101 = new Module("CS101", 101);    Module MA106 = new Module("MA106", 106);
        Module CS102 = new Module("CS102", 102);    Module MA107 = new Module("MA107", 107);
        Module CS103 = new Module("CS103", 103);    Module MA108 = new Module("MA108", 108);
        Module CS104 = new Module("CS104", 104);    Module MA109 = new Module("MA109", 109);
        Module CS105 = new Module("CS105", 105);    Module MA110 = new Module("MA110", 110);

        //Add modules to courses
        CS.addModule(CS101);    CS.addModule(MA106);    MA.addModule(MA106);
        CS.addModule(CS102);    CS.addModule(MA107);    MA.addModule(MA107);
        CS.addModule(CS103);    CS.addModule(MA108);    MA.addModule(MA108);
        CS.addModule(CS104);    MA.addModule(CS103);    MA.addModule(MA109);
        CS.addModule(CS105);    MA.addModule(CS105);    MA.addModule(MA110);

        //Create students
        Student student1 = new Student("Monkey D Luffy", new LocalDate(1995, 6, 5), 1);
        Student student2 = new Student("Roronoa Zoro", new LocalDate(1994, 7, 23), 2);
        Student student3 = new Student("Nami", new LocalDate(1993, 9, 2), 3);
        Student student4 = new Student("Usopp", new LocalDate(1998, 1, 16), 4);
        Student student5 = new Student("Sanji", new LocalDate(1993, 12, 24), 5);
        Student student6 = new Student("Tony Tony Chopper", new LocalDate(1992, 4, 19), 6);
        Student student7 = new Student("Nico Robin", new LocalDate(1997, 2, 23), 7);
        Student student8 = new Student("Franky", new LocalDate(1994, 8, 10), 8);

        //Add students to courses
        CS.addStudent(student1);    MA.addStudent(student3);
        CS.addStudent(student2);    MA.addStudent(student4);
        CS.addStudent(student3);    MA.addStudent(student5);
        CS.addStudent(student4);    MA.addStudent(student6);
        CS.addStudent(student5);    MA.addStudent(student7);
        CS.addStudent(student6);    MA.addStudent(student8);
        CS.addStudent(student7);
        CS.addStudent(student8);

    }

    @Test
    public void setAndGetModulesTest() {

        CS.setModules(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), CS.getModules());

    }

    @Test
    public void setAndGetAcademicDatesTest() {

        MA.setAcademicStartDate(new LocalDate(2022, 9, 1));
        MA.setAcademicEndDate(new LocalDate(2023, 6, 15));

        Assertions.assertEquals("2022-09-01", MA.getAcademicStartDate().toString());
        Assertions.assertEquals("2023-06-15", MA.getAcademicEndDate().toString());

    }

    @Test
    public void addModuleTest() {

        Module TEST404 = new Module("TEST404", 404);
        Module TEST405 = new Module("TEST405", 405);

        final String expected = "\n" +
                "Course{\n" +
                "name:'CS', start-date: 2022-09-01, end-date: 2023-06-17, \n" +
                "modules: [CS101, MA106, CS102, MA107, CS103, MA108, CS104, CS105], \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, nami29, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28]\n" +
                "}";

        Assertions.assertEquals(expected, CS.toString());

        CS.addModule(TEST404);
        CS.addModule(TEST405);

        final String updatedExpected = "\n" +
                "Course{\n" +
                "name:'CS', start-date: 2022-09-01, end-date: 2023-06-17, \n" +
                "modules: [CS101, MA106, CS102, MA107, CS103, MA108, CS104, CS105, TEST404, TEST405], \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, nami29, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28]\n" +
                "}";

        Assertions.assertEquals(updatedExpected, CS.toString());

    }

    @Test
    public void removeModuleTest() {

        CS.removeModule(CS.getModules().get(2)); //removing module CS102
        CS.removeModule(CS.getModules().get(3)); //removing module CS103

        final String expected = "\n" +
                "Course{\n" +
                "name:'CS', start-date: 2022-09-01, end-date: 2023-06-17, \n" +
                "modules: [CS101, MA106, MA107, MA108, CS104, CS105], \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, nami29, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28]\n" +
                "}";

        Assertions.assertEquals(expected, CS.toString());

    }

    @Test
    public void addStudentTest() {

        Student NEWGUY = new Student("NEWGUY", new LocalDate(2002, 3, 19), 9);

        final String expected = "\n" +
                "Course{\n" +
                "name:'CS', start-date: 2022-09-01, end-date: 2023-06-17, \n" +
                "modules: [CS101, MA106, CS102, MA107, CS103, MA108, CS104, CS105], \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, nami29, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28]\n" +
                "}";

        Assertions.assertEquals(expected, CS.toString());

        CS.addStudent(NEWGUY);

        final String updatedExpected = "\n" +
                "Course{\n" +
                "name:'CS', start-date: 2022-09-01, end-date: 2023-06-17, \n" +
                "modules: [CS101, MA106, CS102, MA107, CS103, MA108, CS104, CS105], \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, nami29, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28, newguy20]\n" +
                "}";

        Assertions.assertEquals(updatedExpected, CS.toString());

    }

    @Test
    public void removeStudentTest() {

        CS.removeStudent(CS.getStudents().get(2)); //removing student nami29

        final String expected = "\n" +
                "Course{\n" +
                "name:'CS', start-date: 2022-09-01, end-date: 2023-06-17, \n" +
                "modules: [CS101, MA106, CS102, MA107, CS103, MA108, CS104, CS105], \n" +
                "students: [monkey.d.luffy27, roronoa.zoro28, usopp24, sanji28, tony.tony.chopper30, nico.robin25, franky28]\n" +
                "}";

        Assertions.assertEquals(expected, CS.toString());

    }

}
