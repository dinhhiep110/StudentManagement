package org.example.Model;

public class Student {
    private final String id;
    private String name;
    private int semester;
    private String courseName;


    public Student(String id, String name, int semester, String courseName) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.courseName = courseName;
    }


    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getSemester() {
        return semester;
    }


    public void setSemester(int semester) {
        this.semester = semester;
    }


    public String getCourseName() {
        return courseName;
    }


    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
