# lecture-management-system (part a)
Assignment with a goal of reinforcing the understanding of modern software development frameworks

# Assignment Outline
You have been asked to build a lecture management system as follows:
a) [7 marks]
    Create a Java project using Gradle. The project should contain four classes. Each class should
    have suitable accessor and mutator methods:
      a. A student class should contain some attributes such as (Name, Age, DOB, ID,
          username, courses and modules registered for, etc.).
         A specific method (getUsername()) will generate the student’s username by
          concatenating their name and age
      b. A similar lecturer class should contain some attributes such as (Name, Age, DOB, ID,
          username, and modules teaching, etc.).
         A specific method (getUsername()) will generate the lecturer’s username by
          concatenating their name and age.
      c. A module class, which should contain information such as module name, id (e.g.
          CT417), list of students, courses it is associated with, and lecturer responsible).
      d. A course programme class containing course name (CS & IT or ECE, etc.), list of
          modules, list of students that are enrolled, academic start date and end date.
         Start and end dates should use Terasoluna Joda Time classes (i.e. DateTime), which
          must be added as a project dependency (see https://mvnrepository.com/artifact/org.terasoluna.gfw/terasoluna-gfw-jodatime).
      
     Write a simple unit test using JUnit to make sure that all the above works (e.g. get getUsername()) as expected.
     Also, use Git and Github to setup a local / global repository (the latter is required for part b).
     
     Important: For this assignment you may have to use Java 11, rather than a later version (e.g. don’t use Java 14 as supported by NetBeans 12), as part b) won’t work      otherwise (Jitpack issue). See also section Additional Guidelines.
     
     Further on, you should create a release of your repository on GitHub, before moving to part b). 
     This makes it easier for Jitpack to find the program code.
