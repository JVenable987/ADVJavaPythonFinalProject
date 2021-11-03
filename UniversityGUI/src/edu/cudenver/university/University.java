package edu.cudenver.university;

import javafx.scene.control.Alert;

import java.io.*;
import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;

//By Joshua Venable
//No other team members
//Done in IntelliJ IDEA Ultimate 2020.3.4

public class University implements Serializable {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    static String filename = "./university.ser";

    public University(){
        this.students = new ArrayList<>(100);
        this.courses = new ArrayList<>(100);
    }

    @Override
    public String toString(){
        return String.format("University with %d students and %d courses.", this.getStudents().size(), this.getCourses().size());
    }

    public Student getStudents(String name) throws IllegalArgumentException{
        for (Student s : students){
            if(s.getName().equalsIgnoreCase(name)){
                return s;
            }
        }
        throw new IllegalArgumentException("Student not in the University");
    }

    public Course getCourse(String subject, int number) throws IllegalArgumentException{
        for (Course c : courses){
            if(c.getSubject().equalsIgnoreCase(subject) && c.getNumber()==number){
                return c;
            }
        }
        throw new IllegalArgumentException("Course not in the University");
    }

    public void addUndergrad(String name, LocalDate dob){students.add(new Undergraduate(name, dob));}
    public void addMaster(String name, LocalDate dob){students.add(new Master(name, dob));}
    public void addPhD(String name, LocalDate dob, String topic){students.add(new PhD(name, dob, topic));}

    public void addCourse(String subject, int number, String title) throws IllegalArgumentException{
        try{
            this.getCourse(subject, number);
        }
        catch (IllegalArgumentException iae){
            this.courses.add(new Course(subject,number,title));
            return;
        }
        throw new IllegalArgumentException("The course is already added.");
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void enrollStudentToCourse(String studentName, String courseSubject, int courseNumber) throws IllegalArgumentException{
        try {
            if(this.getStudents(studentName) == null && this.getCourse(courseSubject, courseNumber) == null){
                throw new IllegalArgumentException("Neither Course or Student were Found");
            }

            if(this.getCourse(courseSubject, courseNumber) == null){
                throw new IllegalArgumentException("Course not Found");
            }
            if(this.getStudents(studentName) == null){
                throw new IllegalArgumentException("Student not Found");
            }
            this.getCourse(courseSubject, courseNumber).addStudentToCourse(this.getStudents(studentName));
        }
        catch (IllegalArgumentException iae){
            iae.printStackTrace();
            throw iae;
        }
    }

    ///////////////////////////////////////////////////////////////////////////

    public void saveToFile(){

        ObjectOutputStream oos = null;

        try{
            oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(this);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        finally{
            if(oos != null){
                try{
                    oos.close();
                }
                catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
    }

    public static University loadFromFile() {
        ObjectInputStream ois = null;
        University university = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(University.filename));
            university = (University) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            university = new University();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return university;
    }
}
