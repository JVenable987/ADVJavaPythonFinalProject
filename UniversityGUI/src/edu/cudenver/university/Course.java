package edu.cudenver.university;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

//By Joshua Venable

public class Course implements Serializable {
    private String subject;
    private int number;
    private String title;
    private ArrayList<Student> enrolledStudents;

    Course(String subject, int number, String title){
        this.subject = subject.toUpperCase();
        this.number = number;
        this.title = title;
        this.enrolledStudents = new ArrayList<>(100);
    }

    public void addStudentToCourse(Student enrolledStudent)throws IllegalArgumentException{
        try {
            for (Student s : enrolledStudents) {
                if (enrolledStudent.getName().equalsIgnoreCase(s.getName())) {
                    throw new IllegalArgumentException("Student is already enrolled");
                }
            }
        }
        catch(IllegalArgumentException iae){
            //iae.printStackTrace();
            return;
        }
        enrolledStudents.add(enrolledStudent);
        enrolledStudent.enrollTo(this);

    }

    @Override
    public String toString() {
        return String.format("%s%04d - %s", this.subject, this.number, this.title);
    }

    public String getSubject() {
        return subject;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Student> getEnrolledStudents(){
        return enrolledStudents;
    }
}
