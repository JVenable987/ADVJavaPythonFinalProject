package edu.cudenver.university;

import java.time.LocalDate;

//By Joshua Venable


public class Undergraduate extends Student{
    public Undergraduate(String name, LocalDate dob){
        super(name, dob);
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public String getStanding(){
        return "Undergraduate";
    }
}
