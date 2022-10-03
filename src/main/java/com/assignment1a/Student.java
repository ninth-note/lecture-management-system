package com.assignment1a;

import java.util.ArrayList;
import org.joda.time.LocalDate;
import org.joda.time.Years;

public class Student {

    private String name;
    private LocalDate DOB;
    private int id;
    private int age;
    private ArrayList<Course> courses;
    private ArrayList<Module> registeredModules;

    public Student(String name, LocalDate DOB, int id) {
        this.name = name;
        this.DOB = DOB;
        this.id = id;
        this.age = Years.yearsBetween(DOB, new LocalDate()).getYears(); //works out age from given DOB
        this.courses = new ArrayList<>();
        this.registeredModules = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    /**
     * This method both changes the age and adjusts the DOB
     * @param age, is the input, changing which will also adjust the DOB
     */
    public void setAge(int age) {

        if (age != getAge()) {
            int currentAge = this.age;
            int ageDiff;

            if (currentAge > age) {
                ageDiff = currentAge - age;
                String[] dateValues = getDOB().toString().split("-");
                setDOB(new LocalDate(Integer.parseInt(dateValues[0]) + ageDiff, Integer.parseInt(dateValues[1]), Integer.parseInt(dateValues[2])));
            }
            else if (currentAge < age) {
                ageDiff = age - currentAge;
                String[] dateValues = getDOB().toString().split("-");
                setDOB(new LocalDate(Integer.parseInt(dateValues[0]) - ageDiff, Integer.parseInt(dateValues[1]), Integer.parseInt(dateValues[2])));
            }

            this.age = age;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    /**
     * The age will also be changed, this will be worked out from newly provided DOB
     * @param DOB, new date of birth input (LocalDate).
     */
    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
        this.age = Years.yearsBetween(DOB, new LocalDate()).getYears();
    }

    public ArrayList<Module> getRegisteredModules() {
        return registeredModules;
    }

    public void setRegisteredModules(ArrayList<Module> registeredModules) {
        this.registeredModules = registeredModules;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    /**
     * Method concatenates student name and age to get username
     * Also, it will put everything into lower case and should briefly handle whitespaces
     * @return username, username is the concatenation of student name and age.
     */
    public String getUsername() {
        String username = name.trim().replaceAll(" ", ".").toLowerCase() + age;
        return username;
    }

    /**
     * This method will add a course to the ArrayList courses, and if the course already has modules,
     * these modules will also be added to the student.
     * The student in turn will also be added to the course.
     * @param course, object from the Course class.
     */
    public void addCourse(Course course) {

        if (!courses.contains(course)) courses.add(course);

        if (!course.getStudents().contains(this)) {
            course.addStudent(this);

            for (Module module : course.getModules()) {
                if (!registeredModules.contains(module)) {
                    addModule(module);
                }
            }
        }
    }

    /**
     * Similar to the addCourse method, were instead the course is deleted from the courses ArrayList, and the modules that
     * are part of that course, will also be removed, unless some other course you are doing also has that module.
     * In that case the module will be left alone.
     * The student will then also be removed from the course and modules.
     * @param course, object from the Course class, generally should be added to courses ArrayList first, but if not present will just be ignored.
     */
    public void removeCourse(Course course) {

        if (courses.contains(course)) {
            for (Module module : course.getModules()) {

                boolean modulePresent = registeredModules.contains(module);
                int numberOfCourses = courses.size();

                if (modulePresent && numberOfCourses == 1) {
                    removeModule(module);
                }
                else if (modulePresent && numberOfCourses > 1) {

                    int repeatedModule = 0;

                    for (Course otherCourse: courses) {
                        for (Module inspectedModule: otherCourse.getModules()) {
                            if (inspectedModule.equals(module)) repeatedModule++;
                        }
                    }

                    if (repeatedModule == 1) removeModule(module);
                }
            }
            courses.remove(course);
        }

        if (course.getStudents().contains(this)) course.removeStudent(this);
    }

    /**
     * Adds Module to student registeredModules ArrayList and also adds the student to this module in turn.
     * @param module, object from Module class.
     */
    public void addModule(Module module) {
        if (!registeredModules.contains(module)) registeredModules.add(module);
        if (!module.getStudents().contains(this)) module.addStudent(this);
    }

    /**
     * Instead of being added to registeredModules ArrayList the specified module is removed, and
     * in turn the student is removed from that module.
     * @param module, object from Module class.
     */
    public void removeModule(Module module) {
        if (registeredModules.contains(module)) registeredModules.remove(module);
        if (module.getStudents().contains(this)) module.removeStudent(this);
    }

    /**
     * Normal toString Method that will try and give a summary of this Student object.
     * Instead of returning the toString() methods of other classes, instead it just gets their names and that's it.
     * @return a concatenated string (info on the object)
     */
    @Override
    public String toString() {
        ArrayList<String> courseNames = new ArrayList<>();
        ArrayList<String> moduleNames = new ArrayList<>();

        if (courses.isEmpty()) {
            courseNames.add("No courses have been added yet");
        }
        else {
            for (Course course : courses)
                courseNames.add(course.getName());
        }

        if (registeredModules.isEmpty()) {
            moduleNames.add("No modules have been added yet");
        }
        else {
            for (Module module : registeredModules)
                moduleNames.add(module.getName());
        }

        return "\nStudent{" +
                "\nusername: '" + getUsername() + '\'' +
                ",\nname: '" + name + '\'' +
                ", id: '" + id + '\'' +
                ", age: " + age +
                ", DOB: " + DOB +
                ", \ncourses: " + courseNames +
                ", \nregisteredModules: " + moduleNames +
                "\n}";
    }

}
