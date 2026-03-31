package Collegeproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class College implements Serializable {
    private String name;
    private ArrayList<Department> studyDepartmentNames;
    private ArrayList<Lecturer> lecturersNames;
    private ArrayList<Committees> committeesNames;


    public College(String name){
    this.name=name;
    studyDepartmentNames = new ArrayList<Department>();
    lecturersNames = new ArrayList<Lecturer>();
    committeesNames = new ArrayList<Committees>();

}

    public void addLecturer(String name, String id, Degree degree, double salary, String departmentName, int studentAmount, String degreeName, ArrayList<String> articles, String institution)
    {
        Department department = findOrCreateDepartment(departmentName,studentAmount);
        Lecturer lecturer;
        switch (degree) {
            case Degree.BA, Degree.MA -> {
                lecturer = new Lecturer(name, id, degree, salary, department, degreeName);
            }

            case Degree.DR -> {
                lecturer = new DrLecturer(name, id, degree, salary, department, degreeName, articles);
            }

            case Degree.PROF -> {
                lecturer = new ProfLecturer(name, id, degree, salary, department, degreeName, articles, institution);
            }

            default -> {
                lecturer = new Lecturer(name, id, degree, salary, department, degreeName);
            }
        }


        lecturersNames.add(lecturer);

        if(!departmentName.equals("null"))
            department.addLecturer(lecturer);


//        for (String committeeName : committeesNames) {
//            Committees committee = findOrCreateCommittee(committeeName);
//            committee.addMember(lecturer);
//            lecturer.addCommittee(committee);
//
//        }
    }

    public Committees findCommitteeByName(String committeeName) {
        for (int i = 0; i < committeesNames.size(); i++) {
            if (committeesNames.get(i) != null &&
                    committeesNames.get(i).getCommitteeName() != null &&
                    committeesNames.get(i).getCommitteeName().equals(committeeName)) {
                return committeesNames.get(i);
            }
        }
        return null;
    }

    public boolean checkDepartmentExists(String name){
        for(int i = 0; i < studyDepartmentNames.size(); ++i) {
            if (studyDepartmentNames.get(i).getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public boolean checkLecturerExists(String name){
        for(int i = 0; i < lecturersNames.size(); ++i) {
            if (lecturersNames.get(i).getName().equals(name)) {
                return true;
            }
        }

        return false;
    }




    public Department findOrCreateDepartment(String departmentName,int studentsAmount) {
        if (studyDepartmentNames.size()!=0) {
            for (int i = 0; i < studyDepartmentNames.size(); i++) {
                if (studyDepartmentNames.get(i).getName().equals(departmentName)) {
                    return studyDepartmentNames.get(i);
                }
            }
        }

        Department newDepartment = new Department(departmentName,studentsAmount);
        studyDepartmentNames.add(newDepartment);
        return newDepartment;
    }

    public Department findDepartmentByName(String name) {
        for (int i = 0; i < studyDepartmentNames.size(); i++) {
            if (studyDepartmentNames.get(i) != null &&
                    studyDepartmentNames.get(i).getName().equals(name)) {
                return studyDepartmentNames.get(i);
            }
        }
        return null;
    }

    public int getNumOfStudyDepartments() {
        return studyDepartmentNames.size();
    }


    public String getName() {
        return name;
    }

//    public Committees findOrCreateCommittee(String committeeName) {
//        for (int i = 0; i < numOfCommitteesNames; i++) {
//            if (committeesNames[i].getCommitteeName().equals(committeeName)) {
//                return committeesNames[i];
//            }
//        }
//
//        Committees newCommittee = new Committees(committeeName);
//
//        if (committeesNames.length == numOfCommitteesNames) {
//            if (committeesNames.length == 0) {
//                committeesNames = new Committees[1];
//            } else {
//                committeesNames = Arrays.copyOf(committeesNames, committeesNames.length * 2);
//            }
//        }

//        committeesNames[numOfCommitteesNames++] = newCommittee;
//        return newCommittee;
//    }

    public Lecturer getLecturerByName(String name) {
        for (int i = 0; i < lecturersNames.size(); i++) {
            if (lecturersNames.get(i) != null &&
                    lecturersNames.get(i).getName() != null &&
                    lecturersNames.get(i).getName().equals(name)) {
                return lecturersNames.get(i);
            }
        }
        return null;
    }
    public void addCommittee(Committees newCommittee) {
        committeesNames.add(newCommittee);
    }

    public void addDepartment(String departmentName ,int numberOfStudents){
        studyDepartmentNames.add(new Department(departmentName,numberOfStudents));
    }

    public double getAverageSalary() {
        double sumSalary=0;
        if(lecturersNames.size()==0) {
            return 0;
        }
        for (int i = 0; i <lecturersNames.size() ; i++) {
            sumSalary=lecturersNames.get(i).getSalary()+sumSalary;

        }
        return (sumSalary/lecturersNames.size());

    }

    public boolean checkDepartmentLecturerExists(String departmentName, String lecturerName){
        Department department1;
        department1 = getDepartmentByName(departmentName);
        return department1.checkIfLecturerExists(lecturerName);
    }


    public void addLecturerToDepartment(String departmentName, String lecturerName){
        Lecturer lecturer1;
        lecturer1 = getLecturerByName(lecturerName);
        for (int i = 0; i < studyDepartmentNames.size(); i++) {
            if (studyDepartmentNames.get(i).getName().equals(departmentName)){
                studyDepartmentNames.get(i).addLecturerToDepartment(lecturer1);
            }
        }
    }
    public Department getDepartmentByName(String name){
        for (int i = 0; i < studyDepartmentNames.size(); i++) {
            if (studyDepartmentNames.get(i).getName().equals(name)){
                return studyDepartmentNames.get(i);
            }
        }
        return null;
    }


    public ArrayList<Lecturer> getLecturersNames() {
        return lecturersNames;
    }


    public ArrayList<Committees> getCommitteesNames() {
        return committeesNames;
    }

    public ArrayList<Department> getStudyDepartmentNames() {
        return studyDepartmentNames;
    }


    public int getNumOfLecturer() {
        return lecturersNames.size();
    }


    public int getNumOfCommitteesNames() {
        return committeesNames.size();
    }



}

