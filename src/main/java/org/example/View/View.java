package org.example.View;

import org.example.Controller.Manager;
import org.example.Model.Student;
import org.example.Validation;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
    private static final Scanner in = new Scanner(System.in);
    private static final Manager manager = new Manager();



    public void Menu(){
        while(true){
            System.out.println("WELCOME TO STUDENT MANAGEMENT");
            System.out.println("\t1. Create");
            System.out.println("\t2. Find and Sort");
            System.out.println("\t3. Update/Delete");
            System.out.println("\t4. Report");
            System.out.println("\t5. Exit");
            System.out.print("Enter your choice: ");
            int choose = Validation.checkInput();
            switch(choose){
                case 1:
                    manager.Create();
                    break;
                case 2:
                    manager.sort();
                    break;
                case 3:
                    manager.updateOrDelete();
                    break;
                case 4:
                    manager.report();
                    break;
                case 5:
                    return;
            }
            System.out.println("Press any key to continue");
            in.nextLine();
        }

    }

    public String inputStudent(){
        String message = "Input Unsuccessfully";
        String id,name,course;
        System.out.print("Enter ID: ");
        while(true){
            id = Validation.getID();
            if(manager.isIDExisted(id)){
                System.out.println("ID is existed in the list");
                manager.displayStudentByID(id);
                name = manager.getNameById(id);
                System.out.println("Do you want to add semester and course name (Y or N): ");
                if(getYesNo()){
                    break;
                }
                else{
                    return message;
                }
            }
            else{
                System.out.print("Enter Name: ");
                name = Validation.getName();
                break;
            }

        }
        System.out.print("Enter Semester: ");
        int semester = Validation.getSemester();
        System.out.print("Enter Course Name: ");
        while(true){
            course = Validation.getCourse();
            if (!manager.checkCourse(course)) {
                System.out.println("Available courses are: Java, C/C++, .Net!");
                System.out.println("Invalid course's name! Enter Again");
            }
            else{
                break;
            }
        }
        if (manager.isStudentExisted(id, name, semester, course)){
            message = "Student Information is duplicated";
            return message;
        }
        manager.getList().add(new Student(id, name, semester, course));
        return "Input successfully";
    }


    public String inputName(){
        System.out.print("Enter student's name to find: ");
        return Validation.getName();
    }



    public boolean getYesNo(){
        return !Validation.inputYN().equalsIgnoreCase("n");
    }

    public void update(){
        System.out.print("Enter Student's ID to update: ");
        String id = Validation.getID();
        if(!manager.isIDExisted(id)){
            System.out.println("Student is  not existed");
            return;
        }
        manager.displayStudentByID(id);
        System.out.print("Do you want to update Student(Y or N): ");
        if(!getYesNo()){
            return;
        }
        System.out.print("Enter index you want to update: ");
        int index = Validation.getIndex();
        updateStudentByIndex(id, index);

    }


    public void updateStudentByIndex(String id,int index){
        Student student = getStudentByIndex(index, id);
        if(student == null){
            System.out.println("Index is greater than index in the list");
            return;
        }
        String temp = student.getName();
        System.out.print("Enter Name: ");
        String name = Validation.checkInputUpdateName();
        if(name.isEmpty()){
            name = student.getName();
        }
        name = manager.normalizeString(name);
        System.out.print("Enter Semester: ");
        int semester = Validation.checkInputUpdateSemester();
        if(semester == -1){
            semester = student.getSemester();
        }
        System.out.print("Enter Course Name: ");
        String course = Validation.checkInputUpdateCourseName();
        if(course == null){
            course = student.getCourseName();
        }
        course = manager.normalizeString(course);
        if(manager.isStudentExisted(id, name, semester, course)){
            System.out.println("Update information is duplicates");
            return;
        }
        else if(!checkUpdateStudentExisted(id, semester, course)){
            for(Student st : manager.getList()){
                if(st.getName().equals(temp)){
                    st.setName(name);
                }
            }
            student.setSemester(semester);
            student.setCourseName(course);
        }
        else{
            for(Student st : manager.getList()){
                if(st.getName().equals(temp)){
                    st.setName(name);
                }
            }
        }
        manager.displayStudentByID(id);
    }


    private boolean checkUpdateStudentExisted(String id,int semester,String course){
        for(Student st : manager.getList()){
            if(st.getId().equals(id) && st.getSemester() == semester && st.getCourseName().equals(course)){
                System.out.println("Semester and Course Name are duplicated in the list)");
                return true;
            }
        }
        return false;
    }

    public Student getStudentByIndex(int index,String id){
        int count = 0;
        Student student = null;
        for (Student st : manager.getList()) {
            if (st.getId().equalsIgnoreCase(id)) {
                count++;
                if(index == count){
                    student = st;
                }
            }
        }
        if(index > count){
            return null;
        }
        return student;
    }


    public void deleteStudent(){
        System.out.print("Enter Student's ID to delete: ");
        String id = Validation.getID();
        Student student = manager.SearchByID(id);
        if(student == null){
            System.out.println("Student is not existed");
            return;
        }
        System.out.println("List Student By ID: ");
        manager.displayStudentByID(id);
        ArrayList<Student> listStudentDelete = new ArrayList<>();
        for (int i = 0; i < manager.getList().size(); i++) {
            if(manager.getList().get(i).getId().equals(student.getId())){
                listStudentDelete.add(manager.getList().get(i));
            }
        }
        manager.getList().removeAll(listStudentDelete);
        System.out.println("After deleted: ");
        manager.displayListStudent();

    }

    public String chooseUpdateOrDelete(){
        System.out.println("Do you want to Update or Delete(U or D)");
        System.out.print("Enter your choice: ");
        return Validation.inputUD();

    }

}
