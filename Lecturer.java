package Collegeproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Lecturer implements Cloneable, Serializable {
    private String name;
    private String id;
    private Degree deg;
    private String degreeName;
    private Double salary;
    private Department dep;
    private ArrayList<Committees> allComittees;

    public Lecturer(String name,String id,Degree deg,Double salary,Department dep,String degreeName){
        this.name=name;
        this.id=id;
        this.deg=deg;
        this.salary=salary;
        this.dep=dep;
        this.degreeName=degreeName;
        this.allComittees = new ArrayList<Committees>();
    }

    public Lecturer(String name,String id,Degree deg,Double salary,String degreeName){
        this.name=name;
        this.id=id;
        this.deg=deg;
        this.salary=salary;
        this.degreeName=degreeName;
        this.allComittees = new ArrayList<Committees>();

        this.dep=null;

    }

    public void setDepartment(Department dep) {
        this.dep = dep;
    }


    public void printLecturer() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Degree: " + deg);
        System.out.println("Salary: " + salary);
        System.out.println("Degree subject: "+degreeName);
        if (dep != null) {
            System.out.println("Department: " + dep.getName());
        } else {
            System.out.println("Department: None");
        }

        System.out.print("Committees: ");
        boolean foundCommittee = false;
        for (int i = 0; i < allComittees.size(); i++) {
            if (allComittees.get(i) != null) {
                System.out.print(allComittees.get(i).getCommitteeName());
                foundCommittee = true;
                if (i != allComittees.size() - 1) {
                    System.out.print(", ");
                }
            }
        }
        if (!foundCommittee) {
            System.out.print("None");
        }
        System.out.println();
    }

    public void removeCommittee(Committees committeeToRemove) {
        for (int i = 0; i < allComittees.size(); i++) {
            if (allComittees.get(i) != null && allComittees.get(i).getCommitteeName().equals(committeeToRemove.getCommitteeName())) {
                allComittees.remove(i);
                break;
            }
        }
    }

    public Double getSalary() {
        return salary;
    }


    public String getName() {
        return name;
    }

    public Degree getDegree() {
        return  deg;
    }

    public void addCommittee(Committees committee) {
        allComittees.add(committee);
    }

    public Department getDepartment() {
        return dep;
    }

    @Override
    public Lecturer clone() throws CloneNotSupportedException{
        Lecturer newLecturer = (Lecturer) super.clone();

        if (this.allComittees != null) {
            newLecturer.allComittees = new ArrayList<Committees>();
            for (int i = 0; i < allComittees.size(); i++) {
                newLecturer.allComittees.add(this.allComittees.get(i));
            }
        }

        return newLecturer;

    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof Lecturer)){
            return false;
        }

        Lecturer l = (Lecturer) other;
        return l.id.equals(this.id);
    }

    @Override
    public String toString(){
        if (dep != null){
            return "Lecturer name: " + name + " ID: " + id + " Degree: " + deg + " degree Name: " + degreeName + " salary: " + salary + " department Name: " + dep.getName();
        }
        else{
            return "Lecturer name: " + name + " ID: " + id + " Degree: " + deg + " degree Name: " + degreeName + " salary: " + salary + " department Name: null";
        }
    }

}
