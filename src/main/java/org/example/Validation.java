package org.example;

import org.example.Controller.Manager;

import java.util.Scanner;

public class Validation {
    public static Scanner in = new Scanner(System.in);
    private static final String name = "^[A-Z a-z]+$";
    private static final String id = "^\\d+$";
    private static final String course = "^[A-Za-z.+]+$";




    public static int checkInput(){
        while(true){
            try {
                int input = Integer.parseInt(in.nextLine().trim());
                if(input < 1 || input > 5){
                    throw new NumberFormatException();
                }
                else{
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input must be in range [1,5]");
                System.out.print("Enter again: ");
            }
        }
    }


    public static String getName() {
        while(true){
            String input = in.nextLine().trim();
            if(input.isEmpty() || !input.matches(name)){
                System.out.print("Please rein put: ");
            }
            else{
                return input;
            }
        }
    }
    public static String getCourse() {
        while(true){
            String input = in.nextLine().trim();
            if(input.isEmpty() || !input.matches(course)){
                System.out.print("Please rein put: ");
            }
            else{
                return input;
            }
        }
    }

    public static String getID() {
        while(true){
            String input = in.nextLine().trim();
            if(input.isEmpty()){
                System.out.println("Id is empty");
                System.out.print("Rein put: ");
            }
            else if(input.equals("0")){
                System.out.println("Index of Student must not zero");
                System.out.print("Enter again: ");
            }
            else if(!input.matches(id)){
                System.out.print("Please rein put: ");
            }

            else{
                return input;
            }
        }
    }

    public static int getSemester() {
        while(true){
            try {
                int input = Integer.parseInt(in.nextLine().trim());
                if(input <= 0){
                    throw new NumberFormatException();
                }
                else{
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println("Semester must be positive number");
                System.out.print("Please rein put: ");
            }
        }
    }

    public static String inputYN(){
        while(true){
            try {
                String input = in.nextLine().trim();
                if(!(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n"))){
                    throw new NumberFormatException();
                }
                else{
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input must be Y or N");
                System.out.print("Enter again: ");
            }
        }
    }

    public static String inputUD(){
        while(true){
            try {
                String input = in.nextLine().trim();
                if(!(input.equalsIgnoreCase("u") || input.equalsIgnoreCase("d"))){
                    throw new NumberFormatException();
                }
                else{
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input must be U or D");
                System.out.print("Enter again: ");
            }
        }
    }

    public static int getIndex() {
        int index;
        while(true){
            String input = in.nextLine().trim();
            input = input.replaceAll("\\s+", " ");
            if(input.isEmpty()){
                System.out.println("Index must  not be empty");
                System.out.print("Enter again: ");
            }
            else if(!input.matches("^[-\\d]*$")){
                System.out.println("Wrong Index");
                System.out.println("Enter again");
            }
            else if(Integer.parseInt(input) <= 0){
                System.out.println("Index must be positive");
            }
            else{
                index = Integer.parseInt(input);
                return index;
            }
        }
    }

    public static String checkInputUpdateName(){
        while(true){
            String newName = in.nextLine().trim();
            if(!newName.matches("^[A-Z a-z]*$")){
                System.out.print("Please rein put: ");
            }
            else{
                return newName;
            }
        }
    }

    public static int checkInputUpdateSemester(){
        int semester;
        while(true){
            String input = in.nextLine().trim();
            if(input.isEmpty()){
                semester = -1;
                break;
            }
            if(!input.matches("^\\d*$")){
                System.out.print("Please rein put: ");
            }
            else{
                semester = Integer.parseInt(input);
                break;
            }
        }
        return semester;
    }

    public static String checkInputUpdateCourseName(){
        Manager manager = new Manager();
        while(true){
            String newCourse = in.nextLine().trim();
            if(newCourse.isEmpty()){
                return null;
            }
            else if (!manager.checkCourse(newCourse)) {
                System.out.println("Available courses are: Java, C/C++, .Net!");
                System.out.println("Invalid course's name! Enter Again");
            }
            else{
                return newCourse;
            }
        }
    }
}
