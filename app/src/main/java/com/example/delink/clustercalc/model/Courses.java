package com.example.delink.clustercalc.model;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Parcel
public class Courses {
    private String name;
    private int cluster;
//    private Map<String, Object> subjects = new HashMap<>();
//    private ArrayList<String,Object> subjects = new ArrayList<>();

    public Courses(){}

    public Courses(String name, int cluster/*, ArrayList<String, Object> subs, HashMap<String,Object> subs*/){
        this.name = name;
        this.cluster = cluster;
//        this.subjects = subs;
    }

    public String getName(){
        return name;
    }
    public int getCluster(){
        return cluster;
    }
//    public List<Object> getSubjects(){
//        return subjects;
//    }
//    public Map<String,Object> getSubjects(){
//        return subjects;
//    }
}
