package com.assignment1a;

import java.util.ArrayList;
import org.joda.time.LocalDate;

public class Course {

    private String name;
    private ArrayList<Module> modules;
    private ArrayList<Student> students;
    private LocalDate academicStartDate;
    private LocalDate academicEndDate;

    public Course(String name, LocalDate academicStartDate, LocalDate academicEndDate) {
        this.name = name;
        this.modules = new ArrayList<>();
        this.students = new ArrayList<>();
        this.academicStartDate = academicStartDate;
        this.academicEndDate = academicEndDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public LocalDate getAcademicStartDate() {
        return academicStartDate;
    }

    public void setAcademicStartDate(LocalDate academicStartDate) {
        this.academicStartDate = academicStartDate;
    }

    public LocalDate getAcademicEndDate() {
        return academicEndDate;
    }

    public void setAcademicEndDate(LocalDate academicEndDate) {
        this.academicEndDate = academicEndDate;
    }

    /**
     * Method adds the specified Module object to the modules ArrayList, and similarly adds this
     * course to the Module associatedCourses ArrayList.
     * @param module, object of the Module class.
     */
    public void addModule(Module module) {
        if (!modules.contains(module)) modules.add(module);
        if (!module.getAssociatedCourses().contains(this)) module.addAssociatedCourse(this);
    }

    /**
     * Method removes the specified Module object from the modules ArrayList, and similarly removes this
     * course from the Module associatedCourses ArrayList.
     * @param module, object of the Module class.
     */
    public void removeModule(Module module) {
        if (modules.contains(module)) modules.remove(module);
        if (module.getAssociatedCourses().contains(this)) module.removeAssociatedCourse(this);
    }

    /**
     * Method adds the specified Student object to the students ArrayList, and similarly adds this
     * course to the Student to its courses ArrayList adds this courses modules to the student also.
     * @param student, object of the Student class.
     */
    public void addStudent(Student student) {
        if (!students.contains(student)) students.add(student);
        if (!student.getCourses().contains(this)) student.addCourse(this);
    }

    /**
     * Method removes the specified Student object from the students ArrayList, and also removes this course object from
     * that Student courses ArrayList.
     * @param student, object of the Student class.
     */
    public void removeStudent(Student student) {
        if (students.contains(student)) students.remove(student);
        if (student.getCourses().contains(this)) student.removeCourse(this);
    }

    /**
     * Normal toString Method that will try and give a summary of this Course object.
     * Instead of returning the toString() methods of other classes, instead it just gets their names and that's it.
     * @return a concatenated string (info on the object)
     */
    @Override
    public String toString() {

        ArrayList<String> moduleNames = new ArrayList<>();
        ArrayList<String> studentUsernames = new ArrayList<>();

        if (modules.isEmpty()) {
            moduleNames.add("No modules have been added yet");
        }
        else {
            for (Module module : modules)
                moduleNames.add(module.getName());
        }

        if (students.isEmpty()) {
            studentUsernames.add("No students have been added yet");
        }
        else {
            for (Student student : students)
                studentUsernames.add(student.getUsername());
        }

        return "\nCourse{" +
                "\nname:'" + name + '\'' +
                ", start-date: " + academicStartDate +
                ", end-date: " + academicEndDate +
                ", \nmodules: " + moduleNames +
                ", \nstudents: " + studentUsernames +
                "\n}";
    }
}
