package edu.cudenver.university;

//By Joshua Venable

import java.time.LocalDate;
public class Graduate extends Student{
    public Graduate(String name, LocalDate dob){
        super(name, dob);
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public String getStanding() {
        return "Grad Student";
    }
}
