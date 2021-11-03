package edu.cudenver.application;

import edu.cudenver.university.Course;
import edu.cudenver.university.Student;
import edu.cudenver.university.University;

import java.time.LocalDate;

//By Joshua Venable
//Don't use this main Function, use Main instead.

public class MainApp {
    public static void main(String[] args){
        University university = new University();
        //ArrayList<Student> students = new ArrayList<>();
        university.addUndergrad("John Smith", LocalDate.of(1999, 5, 28));
        university.addUndergrad("Alice Smith", LocalDate.of(2000, 6,21));
        university.addUndergrad("Dan Smith", LocalDate.of(2001, 7,22));
        university.addMaster("John Doe", LocalDate.of(1997, 8, 10));
        university.addMaster("Alice Doe", LocalDate.of(1998,9,11));
        university.addMaster("Jane Doe", LocalDate.of(1999,10,12));
        university.addPhD("Javier Pastorino", LocalDate.of(1999,5,20), "Artificial Intelligence");
        university.addPhD("Ellie Miller", LocalDate.of(1995, 6, 15), "Forensics");

        university.addCourse("CSCI", 3920, "Advanced Programming with Java and Python");
        university.addCourse("CSCI", 3800, "NextGen CyberThreats and GAN");
        university.addCourse("Math", 1500, "Algebra");

        System.out.println(">>>>>>>>> University <<<<<<<<<<");
        System.out.println(university);
        System.out.println();

        System.out.println(">>>>>>>>> University Students <<<<<<<<<<");
        for (Student s: university.getStudents()){
            System.out.println(s);
        }
        System.out.println();

        System.out.println(">>>>>>>>> University Courses <<<<<<<<<<");
        for (Course c: university.getCourses()){
            System.out.println(c);
        }
        System.out.println();

    }
}
