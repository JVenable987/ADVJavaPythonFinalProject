package edu.cudenver.university;

//By Joshua Venable

import java.time.LocalDate;
public class Master extends Graduate{
    public Master(String name, LocalDate dob){
        super(name, dob);
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public String getStanding(){
        return "Master";
    }
}
