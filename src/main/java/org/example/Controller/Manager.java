package org.example.Controller;

import org.example.Model.Report;
import org.example.Model.Student;
import org.example.View.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Manager {
    private final ArrayList<Student> list;
    private final ArrayList<String> listOfCourse;
    private final View view;


    public Manager(){
        view = new View();
        list = new ArrayList<>();
        listOfCourse = new ArrayList<>();
        listOfCourse.add("Java");
        listOfCourse.add(".Net");
        listOfCourse.add("C/C++");
        list.add(new Student("1", "Nguyen Van A", 3, "Java"));
        list.add(new Student("2", "Nguyen Van B", 2, "C++"));
        list.add(new Student("2", "Nguyen Van B", 5, "Java"));
        list.add(new Student("4", "Tran Van C", 3, ".Net"));
        list.add(new Student("4", "Tran Van C", 1, "C++"));
        list.add(new Student("4", "Tran Van C", 2, ".Net"));
        list.add(new Student("4", "Tran Van C", 4, "Java"));
        list.add(new Student("5", "Hoang Van D", 3, "Java"));
        list.add(new Student("6", "Nguyen Van F", 3, "Java"));
    }



    public ArrayList<Student> getList() {
        return list;
    }

    public Student SearchByID(String id){
        for (Student student : list) {
            if(student.getId().equalsIgnoreCase(id)){
                return student;
            }
        }
        return null;
    }

    public boolean isIDExisted(String id){
        for (Student student : list) {
            if(student.getId().equalsIgnoreCase(id)){
                return true;
            }
        }
        return false;
    }


//    public boolean isCourseExisted(String id, String course){
//        for (Student student : list) {
//            if(student.getId().equalsIgnoreCase(id) && student.getCourseName().equalsIgnoreCase(course)){
//                return true;
//            }
//        }
//        return false;
//    }


    public boolean checkCourse(String course){
        for (String c : listOfCourse) {
            if(c.equalsIgnoreCase(course) || course.equalsIgnoreCase("C") || course.equalsIgnoreCase("C++")){
                return true;
            }
        }
        return false;
    }

    public boolean isStudentExisted(String id,String name,int semester,String course){
        for (Student student : list) {
            if(student.getId().equalsIgnoreCase(id)&& student.getName().equalsIgnoreCase(name)
                    && student.getSemester() == semester && student.getCourseName().equalsIgnoreCase(course)){
                return true;
            }
        }
        return false;
    }


    public void displayStudentByID(String id){
        int index = 0;
        if(!isIDExisted(id)){
            System.out.println("ID not found");
            return;
        }
        for (Student student : list) {
            if (student.getId().equalsIgnoreCase(id)) {
                index++;
                System.out.printf("%-5s|%-10s|%-20s|%-10s|%-20s|\n","INDEX", "ID", "NAME", "SEMESTER", "COURSE NAME");
                System.out.printf("%-5d|%-10s|%-20s|%-10d|%-20s|\n",index, normalizeString(student.getId()), normalizeString(student.getName()), student.getSemester(), normalizeString( student.getCourseName()));
            }
        }
    }


    public void displayListStudent(){
        int index = 0;
        for (Student student : list) {
            index++;
            System.out.printf("%-5s|%-10s|%-20s|%-10s|%-20s|\n","INDEX", "ID", "NAME", "SEMESTER", "COURSE NAME");
            System.out.printf("%-5d|%-10s|%-20s|%-10d|%-20s|\n",index, normalizeString(student.getId()), normalizeString(student.getName()), student.getSemester(), normalizeString( student.getCourseName()));

        }
    }



    public void displayStudentByName(ArrayList<Student> lStudents){
        int index = 0;
        System.out.printf("%-5s|%-20s|%-10s|%-20s|\n","INDEX", "NAME", "SEMESTER", "COURSE NAME");
        for (Student student : lStudents) {
            System.out.printf("%-5d|%-20s|%-10d|%-20s|\n",index, normalizeString(student.getName()), student.getSemester(), normalizeString( student.getCourseName()));
            index++;
        }

    }


    public String normalizeString(String input){
        input = input.replaceAll("\\s+", " ");
        String[] temp = input.split(" ");
        StringBuilder res = new StringBuilder();
        for(int i = 0;i < temp.length;i++){
            res.append(String.valueOf(temp[i].charAt(0)).toUpperCase()).append(temp[i].substring(1));
            if(i<temp.length-1) {
                res.append(" ");
            }
        }
        return res.toString();
    }


    public String getNameById(String id){
        for(Student student : list){
            if (student.getId().compareTo(id) == 0)
                return student.getName();
        }
        return null;
    }


    public void Create(){
        System.out.println("=========== 1. Create ==========");
        int count =  list.size();
        while(true){
            System.out.println("Student number " + count);
            String message = view.inputStudent();
            System.out.println(message);
            if(message.equals("Input successfully")){
                count++;
            }
            if(count >= 10){
                System.out.println();
                System.out.print("Do you want to continue(Y or N): ");
                if(!view.getYesNo()){
                    return;
                }
            }
        }
    }

    public ArrayList<Student> findListOfName(){
        System.out.println("=========== 2. Find and Sort ==========");
        ArrayList<Student> lStudents = new ArrayList<>();
        if(list.isEmpty()){
            System.out.println("Your List is empty");
            return null;
        }
        String name = view.inputName();
        name =  name.replaceAll("\\s+", " ");
        for (Student student : list) {
            for (int i = 0; i < name.length(); i++) {
                if(student.getName().toLowerCase().contains(Character.toString(name.charAt(i))) && !lStudents.contains(student)){
                    lStudents.add(student);
                }
            }
        }
        return lStudents;
    }

    public void sort(){
        ArrayList<Student> lStudents = findListOfName();
        if(lStudents == null){
            System.out.println("No student found");
            return;
        }
        System.out.println("List Before Sorting:");
        displayStudentByName(lStudents);
        Collections.sort(lStudents, new Comparator<>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (s1.getName().compareTo(s2.getName()) > 0) {
                    return 1;
                } else if (s1.getName().compareTo(s2.getName()) == 0) {
                    return 0;
                }
                return -1;
            }

        });
        System.out.println("List after sorting:");
        displayStudentByName(lStudents);
    }

    public void updateOrDelete(){
        String choice = view.chooseUpdateOrDelete();
        if(choice.equalsIgnoreCase("u")){
            view.update();
        }
        else if(choice.equalsIgnoreCase("d")){
            view.deleteStudent();
        }
    }

    public void report(){
        System.out.println("=========== 4.Report ==========");
        ArrayList<Report> lReports = new ArrayList<>();
        for(Student student : list){
            int totalCourse = countTotalCourseById(student.getId(),student.getCourseName());
            if(totalCourse == 0){
                System.out.println("There is no Course of Student");
                return;
            }
            else{
                Report report = new Report(student.getName(), student.getCourseName(), totalCourse);
                boolean checkReport = checkReportExisted(lReports, report);
                if(!checkReport){
                    lReports.add(report);
                }

            }
        }
        displayReport(lReports);

    }

    private boolean checkReportExisted(ArrayList<Report> lReports,Report rp){
        for (Report report : lReports) {
            if(report.getStudentName().equalsIgnoreCase(rp.getStudentName()) && report.getTotalCourse() == rp.getTotalCourse()
                    && report.getCourse().equalsIgnoreCase(rp.getCourse())){
                return true;
            }
        }
        return false;
    }

    private int countTotalCourseById(String id,String course){
        int totalCourse = 0;
        for (Student student : list) {
            if (student.getCourseName().equals(course) && student.getId().equalsIgnoreCase(id)) {
                totalCourse++;
            }
        }
        return totalCourse;
    }

    private void displayReport(ArrayList<Report> lReports){
        System.out.printf("%-20s|%-30s|%-12s|\n", "NAME", "COURSE NAME", "TOTAL COURSE");
        for(Report report : lReports){
            report.display();
        }
    }
}
