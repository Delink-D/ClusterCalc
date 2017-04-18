package com.example.delink.clustercalc.model;


import java.util.List;

public class Grades {

    private List<String> group;

    public Grades(){}

    public Grades(List<String> grp){
        this.group = grp;
    }

    public List<String> getGroup(){
        return group;
    }
}
