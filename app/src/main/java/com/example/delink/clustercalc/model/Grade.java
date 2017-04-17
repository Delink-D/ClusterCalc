package com.example.delink.clustercalc.model;

public class Grade {
    private String label;
    private int points;

    public Grade(){}

    public Grade(String lbl, int pnt) {
        this.label = lbl;
        this.points = pnt;
    }

    public String getLabel(){
        return label;
    }

    public int getPoints(){
        return points;
    }
}
