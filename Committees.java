package Collegeproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Committees implements Cloneable, Serializable {
    private String committeeName ;
    private ArrayList<Lecturer> lecturersInCommittees;
    private Lecturer Chairman;
    private Degree degreeType;

    public Committees(String committeeName, Degree degreeType) {
        this.committeeName = committeeName;
        this.lecturersInCommittees = new ArrayList<Lecturer>();
        this.Chairman = null;
        this.degreeType = degreeType;
    }

    public Degree getDegreeType(){
        return this.degreeType;
    }

    public void setCommitteeName(String committeeName){
        this.committeeName = committeeName;
    }

    public ArrayList<Lecturer> getLecturersInCommittees() {
        return lecturersInCommittees;
    }


//    public void setLecturersInCommittees(Lecturer[] lecturersInCommittees) {
//        this.lecturersInCommittees = lecturersInCommittees;
//    }

    public Lecturer getChairman() {
        return Chairman;
    }

    public void setChairman(Lecturer chairman) {
        Chairman = chairman;
    }

    public int getLecturerCount() {
        return lecturersInCommittees.size();
    }



    private int countNumOfElements(String[] arr) {
        int counter = 0;
        for (String s : arr) {
            if (s != null) counter++;
        }
        return counter;
    }


    public void printCommittees(String[] arr) {
        int numOfElements = countNumOfElements(arr);
        System.out.println();
        System.out.print("Committees: [");
        for (int i = 0; i < numOfElements; i++) {
            if (i == numOfElements - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + " ,");
            }
        }
        System.out.println("]");
    }
//    public void removeLecturer(Lecturer lec,int index) {
//        Lecturer[] rightside = Arrays.copyOfRange(lecturersInCommittees, index + 1, lecturersInCommittees.length);
//        int j = 0;
//        for (int i = index; i < lecturersInCommittees.length - 1; i++) {
//            lecturersInCommittees[i] = rightside[j];
//            j++;
//        }
//        lecturersInCommittees[lecturerCount - 1] = null;
//
//        lecturerCount--;
//        lec.removeCommittee(this);
//
//    }

    public void removeLecturer(String memberName){
        for (int i = 0; i < lecturersInCommittees.size(); i++) {
            if(lecturersInCommittees.get(i).getName().equals(memberName)){
                lecturersInCommittees.remove(i);
                break;
            }
        }

    }


    public String getName() {
        return committeeName;
    }

    public String getCommitteeName() {
        return committeeName;
    }


    public void addMember(Lecturer lecturer) {
        if (lecturersInCommittees.contains(lecturer)) {
            return;
        }


        lecturersInCommittees.add(lecturer);
    }


    public void printCommittee() {
        System.out.println("Committee Name: " + committeeName);
        System.out.println("Committee Degree: " + degreeType);

        if (Chairman != null) {
            System.out.println("Chairman: " + Chairman.getName());
        } else {
            System.out.println("Chairman: None");
        }

        System.out.println("Members:");
        if (lecturersInCommittees.size() == 0) {
            System.out.println("No lecturers in this committee.");
        } else {
            for (int i = 0; i < lecturersInCommittees.size(); i++) {
                if (lecturersInCommittees.get(i) != null) {
                    System.out.println("- " + lecturersInCommittees.get(i).getName());
                }
            }
        }
        System.out.println("==========");
    }

    @Override
    public Committees clone() throws CloneNotSupportedException {
        Committees newComittee = (Committees) super.clone();
        newComittee.setCommitteeName("new-" + this.committeeName);

        newComittee.Chairman = this.Chairman.clone();

        newComittee.lecturersInCommittees = new ArrayList<Lecturer>();
        for (int i = 0; i < lecturersInCommittees.size(); i++) {
            if (this.lecturersInCommittees.get(i) != null) {
                newComittee.lecturersInCommittees.add(this.lecturersInCommittees.get(i));
                newComittee.lecturersInCommittees.get(i).addCommittee(newComittee);
            }
        }

        return newComittee;
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof Committees)){
            return false;
        }

        Committees c = (Committees) other;
        return c.getCommitteeName().equals(this.committeeName);
    }

    @Override
    public String toString(){
        StringBuffer res = new StringBuffer("Committee name: " + committeeName + "Committee Degree: " + degreeType + " head of committee: " + Chairman.getName()+ "\n");
        for (int i = 0; i < lecturersInCommittees.size(); i++) {
            res.append("Member: " + i+1 + " name: " + lecturersInCommittees.get(i).getName() + "\n");
        }
        return res.toString();
    }

    }




