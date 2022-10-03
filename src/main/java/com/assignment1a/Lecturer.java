package com.assignment1a;

import java.util.ArrayList;
import org.joda.time.LocalDate;
import org.joda.time.Years;

public class Lecturer {

    private String name;
    private LocalDate DOB;
    private int id;
    private int age;
    private ArrayList<Module> teachingModules;

    public Lecturer(String name, LocalDate DOB, int id) {
        this.name = name;
        this.DOB = DOB;
        this.id = id;
        this.age = Years.yearsBetween(DOB, new LocalDate()).getYears();
        this.teachingModules = new ArrayList<>();
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

    public ArrayList<Module> getTeachingModules() {
        return teachingModules;
    }

    public void setTeachingModules(ArrayList<Module> teachingModules) {
        this.teachingModules = teachingModules;
    }

    /**
     * Method concatenates lecturer name and age to get username
     * Also, it will put everything into lower case and should briefly handle whitespaces
     * @return username, username is the concatenation of lecturer name and age.
     */
    public String getUsername() {
        String username = name.trim().replaceAll(" ", ".").toLowerCase() + age;
        return username;
    }

    /**
     * Method adds the specified Module to the teachingModules ArrayList, and similarly
     * this Module object is assigned as the lecturerResponsible for the Module object.
     * @param module, object of the Lecturer class.
     */
    public void addModuleToTeach(Module module) {
        if (!teachingModules.contains(module)) teachingModules.add(module);
        if (module.getLecturerResponsible() != this) module.addLecturerResponsible(this);
    }

    /**
     * Method removes the specified Module from the teachingModules ArrayList, and similarly
     * this Module object is unassigned as the lecturerResponsible for the Module object.
     * @param module, object of the Lecturer class.
     */
    public void removeModuleToTeach(Module module) {
        if (teachingModules.contains(module)) teachingModules.remove(module);
        if (module.getLecturerResponsible() == this) module.removeLecturerResponsible(this);
    }

    /**
     * Normal toString Method that will try and give a summary of this Lecturer object.
     * Instead of returning the toString() methods of other classes, instead it just gets their names and that's it.
     * @return a concatenated string (info on the object)
     */
    @Override
    public String toString() {

        ArrayList<String> teachingModuleNames = new ArrayList<>();

        if (teachingModules.isEmpty()) {
            teachingModuleNames.add("No modules have been assigned yet");
        }
        else {
            for (Module teachingModule : teachingModules)
                teachingModuleNames.add(teachingModule.getName());
        }

        return "\nLecturer{" +
                "\nusername: '" + getUsername() + '\'' +
                ",\nname: '" + name + '\'' +
                ", id: '" + id + '\'' +
                ", age: " + age +
                ", DOB: " + DOB +
                ", \nteachingModules: " + teachingModuleNames +
                "\n}";
    }
}
