package com.assignment1a;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class LecturerTest {

    Lecturer John = new Lecturer("John Smith", new LocalDate(1990, 1, 25), 1);
    Lecturer Patrick = new Lecturer("Patrick Sweeney", new LocalDate(1970, 8, 4), 2);

    @BeforeEach
    public void setup() {

        //Create modules
        Module CS101 = new Module("CS101", 101);
        Module CS102 = new Module("CS102", 102);
        Module MA106 = new Module("MA106", 106);
        Module MA107 = new Module("MA107", 107);

        //Add modules to lecturer
        John.addModuleToTeach(CS101);
        John.addModuleToTeach(CS102);
        Patrick.addModuleToTeach(MA106);
        Patrick.addModuleToTeach(MA107);

    }

    @Test
    public void setAndGetModulesTest() {

        John.setTeachingModules(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), John.getTeachingModules());

    }

    @Test
    public void setAndGetDOBTest() {

        Patrick.setDOB(new LocalDate(1988, 5, 3));
        Assertions.assertEquals("1988-05-03", Patrick.getDOB().toString());

        //This should automatically update the age of the Lecturer
        Assertions.assertEquals(34, Patrick.getAge());

        //Same way if we update just the age, the DOB should adjust appropriately
        Patrick.setAge(19);
        Assertions.assertEquals("2003-05-03", Patrick.getDOB().toString());

    }

    @Test
    public void getUsernameTest() {

        Assertions.assertNotNull(John.getUsername());
        Assertions.assertEquals("john.smith32", John.getUsername());

        //Checks if the getUsername method appropriately gets rid of blank spaces, for this we will change johns name
        John.setName("   John SancheZ jR   ");
        Assertions.assertEquals("john.sanchez.jr32", John.getUsername());

    }

    @Test
    public void addModuleToTeachTest() {

        Module MA108 = new Module("MA108", 108);

        final String expected = "\n" +
                "Lecturer{\n" +
                "username: 'patrick.sweeney52',\n" +
                "name: 'Patrick Sweeney', id: '2', age: 52, DOB: 1970-08-04, \n" +
                "teachingModules: [MA106, MA107]\n" +
                "}";

        Assertions.assertEquals(expected, Patrick.toString());

        Patrick.addModuleToTeach(MA108);

        final String updatedExpected = "\n" +
                "Lecturer{\n" +
                "username: 'patrick.sweeney52',\n" +
                "name: 'Patrick Sweeney', id: '2', age: 52, DOB: 1970-08-04, \n" +
                "teachingModules: [MA106, MA107, MA108]\n" +
                "}";

        Assertions.assertEquals(updatedExpected, Patrick.toString());

    }

    @Test
    public void removeModuleToTeachTest() {

        Patrick.removeModuleToTeach(Patrick.getTeachingModules().get(1));

        final String expected = "\n" +
                "Lecturer{\n" +
                "username: 'patrick.sweeney52',\n" +
                "name: 'Patrick Sweeney', id: '2', age: 52, DOB: 1970-08-04, \n" +
                "teachingModules: [MA106]\n" +
                "}";

        Assertions.assertEquals(expected, Patrick.toString());
    }

}
