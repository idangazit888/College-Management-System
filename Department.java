package Collegeproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Department implements Serializable {
    private String name;
    private int numberOfStudents;
    private ArrayList<Lecturer> lecturers;

    public Department(String name, int numberOfStudents) {
        this.name = name;
        this.numberOfStudents = numberOfStudents;
        this.lecturers = new ArrayList<Lecturer>();
    }


    public void addLecturer(Lecturer lecturer) {
        lecturers.add(lecturer);

        lecturer.setDepartment(this);
    }




//    private void expandLecturersArray() {
//        Lecturer[] newArray = new Lecturer[lecturers.length * 2];
//        for (int i = 0; i < lecturers.length; i++) {
//            newArray[i] = lecturers[i];
//        }
//        lecturers = newArray;
//    }

//    private int countNumOfElements(String[] arr) {
//        int counter = 0;
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] != null)
//                counter++;
//        }
//        return counter;
//    }

    public boolean checkIfLecturerExists(String lecturerName){
        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i).getName().equals(lecturerName)){
                return true;
            }
        }
        return false;
    }

    public void addLecturerToDepartment(Lecturer lecturer){
        lecturers.add(lecturer);
    }


    public void printDepartment() {
        System.out.println("Department Name: " + name);
        System.out.println("Number of Students: " + numberOfStudents);
        System.out.println("Lecturers:");
        for (int i = 0; i < lecturers.size(); i++) {
            lecturers.get(i).printLecturer();
            System.out.println();
        }
    }

    public double avgSalaryDep(){
        if(lecturers.size()==0){
            return 0;
        }
        double sumSalary=0;
        for (int i = 0; i <lecturers.size() ; i++) {
            sumSalary=sumSalary+lecturers.get(i).getSalary();

        }
        return sumSalary/lecturers.size();
    }

    public void printLecturers() {
        if (lecturers.size() == 0) {
            System.out.println("No lecturers in this department.");
            return;
        }

        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i) != null) {
                lecturers.get(i).printLecturer();
                System.out.println("-----");
            }
        }
    }


    public String getName() {
        return name;
    }


    public int getLecturerCount(){
        return lecturers.size();
    }

    public int getNumOfArticles(){
        int numOfArticles = 0;
        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i) instanceof DrLecturer){
                numOfArticles += ((DrLecturer) lecturers.get(i)).getNumOfArticles();
            }
        }

        return numOfArticles;
    }


    @Override
    public boolean equals(Object other){
        if (!(other instanceof Department)){
            return false;
        }

        Department d = (Department) other;
        return d.getName().equals(this.name);
    }

    @Override
    public String toString(){
        StringBuffer res = new StringBuffer("Department name: " + name + " num Of Students: " + numberOfStudents+ " numOfLecturers: " +lecturers.size()+ "\n");
        for (int i = 0; i < lecturers.size(); i++) {
            res.append("Lecturer: " + i+1 + " name: " + lecturers.get(i).getName() + "\n");
        }
        return res.toString();
    }

}
