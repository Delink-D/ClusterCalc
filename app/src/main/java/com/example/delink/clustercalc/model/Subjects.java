package com.example.delink.clustercalc.model;

public class Subjects {

    private String sub1;
    private String sub2;
    private String sub3;
    private String sub4;
    private String sub5;

    public Subjects(){}

    public Subjects(String s1,String s2,String s3,String s4,String s5 ){
        this.sub1 = s1;
        this.sub2 = s2;
        this.sub3 = s3;
        this.sub4 = s4;
        this.sub5 = s5;
    }

    public String getSub1(){
        return sub1;
    }
    public String getSub2(){
        return sub2;
    }
    public String getSub3(){
        return sub3;
    } public String getSub4(){
        return sub4;
    }
    public String getSub5(){
        return sub5;
    }
}
