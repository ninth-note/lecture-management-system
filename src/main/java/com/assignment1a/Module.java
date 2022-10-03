package com.assignment1a;

import java.util.ArrayList;

public class Module {

    private String name;
    private int id;
    private ArrayList<Student> students;
    private ArrayList<Course> associatedCourses;
    private Lecturer lecturerResponsible;

    public Module(String name, int id) {
        this.name = name;
        this.id = id;
        this.students = new ArrayList<>();
        this.associatedCourses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Course> getAssociatedCourses() {
        return associatedCourses;
    }

    public void setAssociatedCourses(ArrayList<Course> associatedWithCourses) {
        this.associatedCourses = associatedWithCourses;
    }

    public Lecturer getLecturerResponsible() {
        return lecturerResponsible;
    }

    public void setLecturerResponsible(Lecturer lecturerResponsible) {
        this.lecturerResponsible = lecturerResponsible;
    }

    /**
     * Method adds the specified Student to the students ArrayList, and similarly
     * this Module object is added to the Student object to the registeredModules ArrayList.
     * @param student, object of the Student class.
     */
    public void addStudent(Student student) {
        if (!students.contains(student)) students.add(student);
        if (!student.getRegisteredModules().contains(this)) student.addModule(this);
    }

    /**
     * Method removes the specified Student from the students ArrayList, and similarly
     * this Module object is removed from the Student object from the registeredModules ArrayList.
     * @param student, object of the Student class.
     */
    public void removeStudent(Student student) {
        if (students.contains(student)) students.remove(student);
        if (student.getRegisteredModules().contains(this)) student.removeModule(this);
    }

    /**
     * Method adds the specified Course to the associatedCourses ArrayList, and similarly
     * this Module object is added to the Course object to the modules ArrayList.
     * @param course, object of the Course class.
     */
    public void addAssociatedCourse(Course course) {
        if (!associatedCourses.contains(course)) associatedCourses.add(course);
        if (!course.getModules().contains(this)) course.addModule(this);
    }

    /**
     * Method removes the specified Course from the associatedCourses ArrayList, and similarly
     * this Module object is removed from the Course object from the modules ArrayList.
     * @param course, object of the Course class.
     */
    public void removeAssociatedCourse(Course course) {
        if (associatedCourses.contains(course)) associatedCourses.remove(course);
        if (course.getModules().contains(this)) course.removeModule(this);
    }

    /**
     * Method assigns the specified Lecturer to the lecturerResponsible, and similarly
     * this Module object is added to the Lecturer object to the teachingModules ArrayList.
     * @param lecturer, object of the Lecturer class.
     */
    public void addLecturerResponsible(Lecturer lecturer) {
        if (lecturerResponsible != lecturer) setLecturerResponsible(lecturer);
        if (!lecturer.getTeachingModules().contains(this)) lecturer.addModuleToTeach(this);
    }

    /**
     * Method removes the specified Lecturer from lecturerResponsible, and similarly
     * this Module object is removed from the Lecturer object from the teachingModules ArrayList.
     * @param lecturer, object of the Lecturer class.
     */
    public void removeLecturerResponsible(Lecturer lecturer) {
        if (lecturerResponsible == lecturer) setLecturerResponsible(null);
        if (lecturer.getTeachingModules().contains(this)) lecturer.removeModuleToTeach(this);
    }

    /**
     * Normal toString Method that will try and give a summary of this Module object.
     * Instead of returning the toString() methods of other classes, instead it just gets their names and that's it.
     * @return a concatenated string (info on the object)
     */
    @Override
    public String toString() {

        ArrayList<String> studentUsernames = new ArrayList<>();
        ArrayList<String> associatedCourseNames = new ArrayList<>();
        String lecturerUsername = "No lecturer assigned to this module";

        if (students.isEmpty()) {
            studentUsernames.add("No students have been registered to this module");
        }
        else {
            for (Student student : students)
                studentUsernames.add(student.getUsername());
        }

        if (associatedCourses.isEmpty()) {
            associatedCourseNames.add("Not assigned to any course at the moment");
        }
        else {
            for (Course associatedCourse : associatedCourses)
                associatedCourseNames.add(associatedCourse.getName());
        }

        if (lecturerResponsible != null) {
            lecturerUsername = lecturerResponsible.getUsername();
        }

        return "\nModule{" +
                "\nname: '" + name + '\'' +
                ", id: '" + id + '\'' +
                ", lecturerResponsible: '" + lecturerUsername + '\'' +
                ", \nstudents: " + studentUsernames +
                ", \nassociatedCourses: " + associatedCourseNames +
                "\n}";
    }
}
