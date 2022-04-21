package org.example.Model;

public class Report {
    private final String studentName;
    private final String course;
    private final int totalCourse;

    public Report(String studentName, String course, int totalCourse) {
        this.studentName = studentName;
        this.course = course;
        this.totalCourse = totalCourse;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourse() {
        return course;
    }

    public int getTotalCourse() {
        return totalCourse;
    }

    public void display(){
        System.out.printf("%-20s|%-30s|%-12s|\n", studentName, course, totalCourse);
    }

}
