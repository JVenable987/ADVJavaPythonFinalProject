package edu.cudenver.university;

import java.time.LocalDate;

//By Joshua Venable

public class PhD extends Graduate{
    private String dissertationTopic;

    public PhD(String name, LocalDate dob, String dissertationTopic){
        super(name, dob);
        this.dissertationTopic = dissertationTopic;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("PhD dissertation is about %s", this.dissertationTopic));
        return sb.toString();
    }

    @Override
    public String getStanding(){
        return "PHD Student. ";
    }

    public String getDissertationTopic() {
        return dissertationTopic;
    }
}
