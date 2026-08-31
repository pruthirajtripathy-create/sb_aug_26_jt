package com.jt.intro_to_rest;

import java.util.Arrays;

public class Student {
    private int id;
    private String name;
     private String courses[];

     public int getId() {
        return id;
    }
     public void setId(int id) {
         this.id = id;
     }
     public String getName() {
         return name;
     }
     public void setName(String name) {
         this.name = name;
     }
     public String[] getCourses() {
         return courses;
     }
     public void setCourses(String[] courses) {
         this.courses = courses;
     }
     public Student() {
    }
    public Student(int id, String name, String[] courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
    }
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", courses=" + Arrays.toString(courses) + "]";
    }
   
    
}
