package com.assignment1a;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class StudentTest {

    Student student1 = new Student("Monkey D Luffy", new LocalDate(1995, 4, 5), 1);

    @BeforeEach
    public void setup() {

        //Create courses
        Course CS = new Course("CS", new LocalDate(2022, 9, 1), new LocalDate(2023, 6, 17));
        Course MA = new Course("MA", new LocalDate(2022, 9, 1), new LocalDate(2023, 6, 17));

        //Create modules
        Module CS101 = new Module("CS101", 101);    Module MA106 = new Module("MA106", 106);
        Module CS102 = new Module("CS102", 102);    Module MA107 = new Module("MA107", 107);
        Module CS103 = new Module("CS103", 103);    Module MA108 = new Module("MA108", 108);
        Module CS104 = new Module("CS104", 104);    Module MA109 = new Module("MA109", 109);
        Module CS105 = new Module("CS105", 105);

        //Add modules to courses
        CS.addModule(CS101);    CS.addModule(CS102);
        CS.addModule(CS103);    CS.addModule(CS104);
        CS.addModule(CS105);    CS.addModule(MA106);

        MA.addModule(CS103);    MA.addModule(MA106);
        MA.addModule(MA107);    MA.addModule(MA108);
        MA.addModule(MA109);

        //Add courses to student
        student1.addCourse(CS);
        student1.addCourse(MA);
    }

    @Test
    public void setAndGetDOBTest() {

        student1.setDOB(new LocalDate(1988, 5, 3));
        Assertions.assertEquals("1988-05-03", student1.getDOB().toString());

        //This should automatically update the age of the Student
        Assertions.assertEquals(34, student1.getAge());

        //Same way if we update just the age, the DOB should adjust appropriately
        student1.setAge(19);
        Assertions.assertEquals("2003-05-03", student1.getDOB().toString());

    }

    @Test
    public void setAndGetCoursesTest() {

        student1.setCourses(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), student1.getCourses());

    }

    @Test
    public void setAndGetRegisteredModulesTest() {

        student1.setRegisteredModules(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), student1.getRegisteredModules());

    }

    @Test
    public void getUsernameTest() {

        Assertions.assertNotNull(student1.getUsername());
        Assertions.assertEquals("monkey.d.luffy27", student1.getUsername());

        //Checks if the getUsername method appropriately gets rid of blank spaces, for this we will change the name
        student1.setName("   Tony Tony ChoPpeR    ");
        Assertions.assertEquals("tony.tony.chopper27", student1.getUsername());

    }

    @Test
    public void addCourseTest() {

        //Again we create a course, some modules and add them to the course
        Course EN = new Course("EN", new LocalDate(2022, 9, 1), new LocalDate(2023, 6, 17));

        Module EN201 = new Module("EN201", 201);
        Module EN202 = new Module("EN202", 202);

        EN.addModule(EN201);
        EN.addModule(EN202);

        //Check expected output before adding new course
        final String expected = "\n" +
                "Student{\n" +
                "username: 'monkey.d.luffy27',\n" +
                "name: 'Monkey D Luffy', id: '1', age: 27, DOB: 1995-04-05, \n" +
                "courses: [CS, MA], \n" +
                "registeredModules: [CS101, CS102, CS103, CS104, CS105, MA106, MA107, MA108, MA109]\n" +
                "}";

        Assertions.assertEquals(expected, student1.toString());

        //We now add the course to student, no need to add modules since they should be automatically added, since they are part of the course
        student1.addCourse(EN);

        final String updatedExpected = "\n" +
                "Student{\n" +
                "username: 'monkey.d.luffy27',\n" +
                "name: 'Monkey D Luffy', id: '1', age: 27, DOB: 1995-04-05, \n" +
                "courses: [CS, MA, EN], \n" +
                "registeredModules: [CS101, CS102, CS103, CS104, CS105, MA106, MA107, MA108, MA109, EN201, EN202]\n" +
                "}";

        //We now check if the course and it's modules were successfully added to the student
        Assertions.assertEquals(updatedExpected, student1.toString());

        //Checks if the student was also added to the course (should have happened automatically)
        Assertions.assertTrue(EN.getStudents().contains(student1));

        //Similarly checks if the student was added to the respective modules (should have happened automatically)
        Assertions.assertTrue(EN201.getStudents().contains(student1));
        Assertions.assertTrue(EN202.getStudents().contains(student1));

    }

    @Test
    public void removeCourseTest() {

        student1.removeCourse(student1.getCourses().get(1)); //removing MA course

        final String expected = "\n" +
                "Student{\n" +
                "username: 'monkey.d.luffy27',\n" +
                "name: 'Monkey D Luffy', id: '1', age: 27, DOB: 1995-04-05, \n" +
                "courses: [CS], \n" +
                "registeredModules: [CS101, CS102, CS103, CS104, CS105, MA106]\n" +
                "}";

        //Checks that the course and it's modules were successfully removed, and in case another course also has the same module, it was left alone
        Assertions.assertEquals(expected, student1.toString());

    }

    @Test
    public void addModuleTest() {

        Module EN201 = new Module("EN201", 201);
        Module EN202 = new Module("EN202", 202);

        //Check expected output before adding new modules
        final String expected = "\n" +
                "Student{\n" +
                "username: 'monkey.d.luffy27',\n" +
                "name: 'Monkey D Luffy', id: '1', age: 27, DOB: 1995-04-05, \n" +
                "courses: [CS, MA], \n" +
                "registeredModules: [CS101, CS102, CS103, CS104, CS105, MA106, MA107, MA108, MA109]\n" +
                "}";

        Assertions.assertEquals(expected, student1.toString());

        student1.addModule(EN201);
        student1.addModule(EN202);

        //Check expected output before adding new modules
        final String updatedExpected = "\n" +
                "Student{\n" +
                "username: 'monkey.d.luffy27',\n" +
                "name: 'Monkey D Luffy', id: '1', age: 27, DOB: 1995-04-05, \n" +
                "courses: [CS, MA], \n" +
                "registeredModules: [CS101, CS102, CS103, CS104, CS105, MA106, MA107, MA108, MA109, EN201, EN202]\n" +
                "}";

        Assertions.assertEquals(updatedExpected, student1.toString());
        Assertions.assertTrue(EN201.getStudents().contains(student1));
        Assertions.assertTrue(EN202.getStudents().contains(student1));

    }

    @Test
    public void removeModuleTest() {

        student1.removeModule(student1.getRegisteredModules().get(7)); //removing module MA108

        final String expected = "\n" +
                "Student{\n" +
                "username: 'monkey.d.luffy27',\n" +
                "name: 'Monkey D Luffy', id: '1', age: 27, DOB: 1995-04-05, \n" +
                "courses: [CS, MA], \n" +
                "registeredModules: [CS101, CS102, CS103, CS104, CS105, MA106, MA107, MA109]\n" +
                "}";

        Assertions.assertEquals(expected, student1.toString());

    }

}
