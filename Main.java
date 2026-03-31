package Collegeproject;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

public class Main {  // Code by Idan Gazit id: 322234469
    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException, IOException, ClassNotFoundException {
        Scanner s = new Scanner(System.in);
        System.out.println("Hello, please enter your college name:");
        String collegename = s.nextLine();
        College college = new College(collegename);

        boolean fileFound = true;
        try {
            ObjectInputStream inFile = new ObjectInputStream(new FileInputStream (collegename+".dat"));
            inFile.close();
        }catch (FileNotFoundException e){
            fileFound = false;
        }

        if (fileFound){
            ObjectInputStream inFile = new ObjectInputStream(new FileInputStream (collegename+".dat"));
            college = (College)inFile.readObject();
        }

        int userChoose;
        do {
            printMenu();
            userChoose = s.nextInt();
            s.nextLine();

            switch (userChoose) {
                case 0 -> {
                    ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(collegename+".dat"));
                    outFile.writeObject(college);
                    outFile.close();
                    System.out.println("Bye bye and thank you");
                }
                case 1 -> {
                    System.out.println("Enter lecturer name:");
                    String name = s.nextLine();

                    System.out.println("Enter lecturer ID:");
                    String id = s.nextLine();

                    System.out.println("Enter degree (BA, MA, DR, PROF):");
                    Degree degree = Degree.valueOf(s.nextLine().toUpperCase());

                    System.out.println("What subject did you study in your degree?");
                    String degreeName = s.nextLine();

                    System.out.println("Enter lecturer salary:");
                    double salary;
                    while ((salary = s.nextDouble()) <= 0) {
                        System.out.println("Salary must be greater than 0. Please enter again:");
                    }
                    s.nextLine();

                    System.out.println("Enter department name (or press Enter to skip):");
                    String departmentName = s.nextLine();
                    Department department = null;
                    int studentAmount = 0;

                    if (!departmentName.isEmpty()) {
                        System.out.println("Enter number of students in department:");
                        while ((studentAmount = s.nextInt()) <= 0) {
                            System.out.println("Student amount must be greater than 0. Please enter again:");
                        }
                        s.nextLine();
                        department = college.findOrCreateDepartment(departmentName, studentAmount);
                    }

//                    Lecturer lecturer = new Lecturer(name, id, degree, salary, department, degreeName);
//
//                    if (department != null) {
//                        department.addLecturer(lecturer);
//                    }

                    ArrayList<String> articles = null;
                    if (degree == Degree.DR || degree == Degree.PROF) {
                        System.out.println("Please enter number of articles:");
                        int numOfArticles;
                        while ((numOfArticles = s.nextInt()) < 0) {
                            System.out.println("Number of articles must be equal or greater than 0. Please enter again:");
                        }
                        s.nextLine();

                        articles = new ArrayList<String>();
                        for (int i = 0; i < numOfArticles; i++) {
                            System.out.println("Please enter article:");
                            articles.add(s.nextLine());
                        }

                    }
                    String institution = null;
                    if (degree == Degree.PROF) {
                        System.out.println("Please enter Degree-Granting Institution:");
                        institution = s.nextLine();
                    }

                    college.addLecturer(name, id, degree, salary, departmentName, studentAmount, degreeName, articles, institution);
                }

                case 2 -> {
                    System.out.println("Enter committee name:");
                    String committeeName = s.nextLine();
                    while (college.getCommitteesNames().contains(college.findCommitteeByName(committeeName))) {
                        System.out.println("Committee already exists. Enter a different name:");
                        committeeName = s.nextLine();
                        ;
                    }
                    System.out.println("Enter committee type degree (BA, MA, DR, PROF):");
                    Degree degreeType = Degree.valueOf(s.nextLine().toUpperCase());

                    Committees newCommittee = new Committees(committeeName, degreeType);

                    System.out.println("Enter chairman name (Only DR or PROF can be chairman):");
                    String lecturerName = s.nextLine();
                    System.out.println("Enter chairman ID:");
                    String lecturerId = s.nextLine();
                    System.out.println("Enter degree (BA, MA, DR, PROF):");
                    Degree degree = Degree.valueOf(s.nextLine().toUpperCase());
                    try {
                        if (degree != Degree.DR && degree != Degree.PROF) {
                            throw new CommitteeExceptions.ChairmanDegreeException();
                        }
                    } catch (CommitteeExceptions.ChairmanDegreeException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println("Enter lecturer salary:");
                    double salary = s.nextDouble();
                    s.nextLine();
                    Lecturer chairman = new Lecturer(lecturerName, lecturerId, degree, salary, null);
                    newCommittee.setChairman(chairman);
                    college.addCommittee(newCommittee);
                }

                case 3 -> {
                    System.out.println("Enter committee name:");
                    String committeeName = s.nextLine();

                    try {
                        if (!college.getCommitteesNames().contains(college.findCommitteeByName(committeeName))) {
                            throw new CommitteeExceptions.CommitteeNotExistsException();
                        }
                    } catch (CommitteeExceptions.CommitteeNotExistsException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println("Enter lecturer name:");
                    String name = s.nextLine();

                    try {
                        if (!college.getLecturersNames().contains(college.getLecturerByName(name))) {
                            throw new LecturerExceptions.LecturerNotExistsException();

                        }
                    } catch (LecturerExceptions.LecturerNotExistsException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    Degree lecturerDegree = college.getLecturerByName(name).getDegree();
                    Degree committeeDegree = college.findCommitteeByName(committeeName).getDegreeType();

                    try {
                        if (committeeDegree == Degree.BA || committeeDegree == Degree.MA) {
                            if (lecturerDegree != Degree.BA && lecturerDegree != Degree.MA) {
                                throw new CommitteeExceptions.CommitteeDegreeTypeException();
                            }
                        } else {
                            if (lecturerDegree != committeeDegree) {
                                throw new CommitteeExceptions.CommitteeDegreeTypeException();
                            }
                        }
                    } catch (CommitteeExceptions.CommitteeDegreeTypeException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    Lecturer lecturer = college.getLecturerByName(name);
                    Committees committee = college.findCommitteeByName(committeeName);
                    committee.addMember(lecturer);
                    lecturer.addCommittee(committee);
                }

                case 4 -> {
                    System.out.println("Enter committee name:");
                    String comName = s.nextLine();

                    try {
                        if (!college.getCommitteesNames().contains(college.findCommitteeByName(comName))) {
                            throw new CommitteeExceptions.CommitteeNotExistsException();
                        }
                    } catch (CommitteeExceptions.CommitteeNotExistsException e){
                        System.out.println(e.getMessage());
                        break;
                    }


                    System.out.println("Enter lecturer name (must be DR or PROF):");
                    String lecName = s.nextLine();

                    try {
                        if (!college.getLecturersNames().contains(college.getLecturerByName(lecName))) {
                            throw new LecturerExceptions.LecturerNotExistsException();
                        }
                    } catch (LecturerExceptions.LecturerNotExistsException e){
                        System.out.println(e.getMessage());
                        break;
                    }


                    Committees committee = college.findCommitteeByName(comName);

                    try {
                        if (college.getLecturersNames().contains(college.getLecturerByName(lecName))) {
                            throw new CommitteeExceptions.ChairmanExistsException();
                        }
                    } catch (CommitteeExceptions.ChairmanExistsException e){
                        System.out.println(e.getMessage());
                        break;
                    }


                    Lecturer lec = college.getLecturerByName(lecName);

                    try {
                        if (!(lec instanceof DrLecturer)) {
                            throw new CommitteeExceptions.ChairmanDegreeException();
                        }
                    } catch (CommitteeExceptions.ChairmanDegreeException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    committee.setChairman(lec);
                }




                case 5 -> {
                    System.out.println("Enter committee name:");
                    String comName = s.nextLine();

                    try {
                        if (!college.getCommitteesNames().contains(college.findCommitteeByName(comName))) {
                            throw new CommitteeExceptions.CommitteeNotExistsException();
                        }
                    } catch (CommitteeExceptions.CommitteeNotExistsException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println("Enter lecturer name to remove:");
                    String lecName = s.nextLine();
                    Committees committee = college.findCommitteeByName(comName);

                    try {
                        if (college.getCommitteesNames().contains(college.findCommitteeByName(comName))) {
                            throw new CommitteeExceptions.LecturerNotExistsInCommitteeException();
                        }
                    } catch (CommitteeExceptions.LecturerNotExistsInCommitteeException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    String toRemove = "";
                    for (int i = 0; i < committee.getLecturerCount(); i++) {
                        if (committee.getLecturersInCommittees().get(i).getName().equals(lecName)) {
                            toRemove = committee.getLecturersInCommittees().get(i).getName();
                            break;
                        }
                    }
                    committee.removeLecturer(toRemove);
                }

                case 6 -> {
                    System.out.println("Enter department name:");
                    String depName = s.nextLine();

                    try {
                        if (college.getStudyDepartmentNames().contains(college.getDepartmentByName(depName))) {
                            throw new DepartmentExceptions.DepartmentExistsException();

                        }
                    } catch (DepartmentExceptions.DepartmentExistsException e){
                        System.out.println(e.getMessage());
                        break;
                    }


                    System.out.println("Enter number of students:");
                    int num;
                    while ((num = s.nextInt()) <= 0) {
                        System.out.println("Number must be greater than 0:");
                    }
                    s.nextLine();
                    college.addDepartment(depName, num);
                }

                case 7 -> System.out.println("Average salary of all lecturers: " + college.getAverageSalary());

                case 8 -> {
                    System.out.println("Enter department name:");
                    String depName = s.nextLine();

                    try {
                        if (!college.getStudyDepartmentNames().contains(college.getDepartmentByName(depName))) {
                            throw new DepartmentExceptions.DepartmentNotExistsException();
                        }
                    } catch (DepartmentExceptions.DepartmentNotExistsException e){
                        System.out.println(e.getMessage());
                        break;
                    }


                    Department dep = college.findDepartmentByName(depName);
                    if (dep != null) {
                        System.out.println("Average salary: " + dep.avgSalaryDep());
                    }
                }

                case 9 -> {
                    ArrayList<Lecturer> lecturers = college.getLecturersNames();
                    for (Lecturer lec : lecturers) {
                        if (lec != null) {
                            lec.printLecturer();
                            System.out.println("==========");
                        }
                    }
                }

                case 10 -> {
                    ArrayList<Committees> committees = college.getCommitteesNames();
                    for (Committees c : committees) {
                        if (c != null) {
                            c.printCommittee();
                        }
                    }
                }

                case 11 -> {
                    System.out.println("Enter department name:");
                    String depName = s.nextLine();

                    while (!college.checkDepartmentExists(depName)) {
                        System.out.println("Department not found, enter again:");
                        depName = s.nextLine();
                    }

                    System.out.println("Enter lecturer name:");
                    String lecName = s.nextLine();

                    try {
                        if (!college.getLecturersNames().contains(college.getLecturerByName(lecName))) {
                            throw new LecturerExceptions.LecturerNotExistsException();
                        }
                    } catch (LecturerExceptions.LecturerNotExistsException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    Lecturer lecturer = college.getLecturerByName(lecName);

                    try {
                        if (lecturer.getDepartment() != null) {
                            throw new DepartmentExceptions.LecturerAlreadyBelongDepartmentException();
                        }
                    } catch (DepartmentExceptions.LecturerAlreadyBelongDepartmentException e){
                        System.out.println(e.getMessage());
                        break;
                    }


                    Department department = college.getDepartmentByName(depName);
                    department.addLecturer(lecturer);

                }

//                case 12 ->{
//                    System.out.println("Enter first name:");
//                    String lecturer1 = s.nextLine();
//                    System.out.println("Enter second name:");
//                    String lecturer2 = s.nextLine();
//                    Lecturer theLecturer1 = college.getLecturerByName(lecturer1);
//                    Lecturer theLecturer2 = college.getLecturerByName(lecturer2);
//                    if (theLecturer1 == null || theLecturer2 == null){
//                        break;
//                    }
//
//                    boolean isEquals = theLecturer1.equals(theLecturer2);
//                    if (isEquals){
//                        System.out.println("The two are equals");
//                    }
//                    else {
//                        System.out.println("The two are not equals");
//                    }
//                }

                case 12 ->{
                    System.out.println("Enter first name of lecturer (DR/PROF):");
                    String lecturer1 = s.nextLine();
                    try {
                        if (!college.getLecturersNames().contains(college.getLecturerByName(lecturer1))) {
                            throw new LecturerExceptions.LecturerNotExistsException();
                        }
                    } catch (LecturerExceptions.LecturerNotExistsException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    Lecturer theLecturer1 = college.getLecturerByName(lecturer1);

                    try {
                        if (!(theLecturer1 instanceof DrLecturer)) {
                            throw new LecturerExceptions.LecturerNotDRorProfException();
                        }
                    } catch (LecturerExceptions.LecturerNotDRorProfException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println("Enter second name of lecturer (DR/PROF):");
                    String lecturer2 = s.nextLine();

                    try {
                        if (!college.getLecturersNames().contains(college.getLecturerByName(lecturer2))) {
                            throw new LecturerExceptions.LecturerNotExistsException();
                        }
                    } catch (LecturerExceptions.LecturerNotExistsException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    Lecturer theLecturer2 = college.getLecturerByName(lecturer2);

                    try {
                        if (!(theLecturer2 instanceof DrLecturer)) {
                            throw new LecturerExceptions.LecturerNotDRorProfException();
                        }
                    } catch (LecturerExceptions.LecturerNotDRorProfException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    DrLecturer newTheLecturer1 = (DrLecturer) theLecturer1;
                    DrLecturer newTheLecturer2 = (DrLecturer) theLecturer2;

                    int compareRes = newTheLecturer1.compareTo(newTheLecturer2);

                    if (compareRes > 0){
                        System.out.println(lecturer1 + " has more articles");
                    }
                    else if (compareRes < 0){
                        System.out.println(lecturer2 + " has more articles");
                    }
                    else {
                        System.out.println("The two are not equals");
                    }
                }

                case 13 ->{
                    System.out.println("Enter first department name:");
                    String department1 = s.nextLine();
                    try {
                        if (!college.getStudyDepartmentNames().contains(college.getDepartmentByName(department1))) {
                            throw new DepartmentExceptions.DepartmentNotExistsException();
                        }
                    } catch (DepartmentExceptions.DepartmentNotExistsException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    Department theDepartment1 = college.getDepartmentByName(department1);


                    System.out.println("Enter second department name:");
                    String department2 = s.nextLine();
                    try {
                        if (!college.getStudyDepartmentNames().contains(college.getDepartmentByName(department2))) {
                            throw new DepartmentExceptions.DepartmentNotExistsException();
                        }
                    } catch (DepartmentExceptions.DepartmentNotExistsException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    Department theDepartment2 = college.getDepartmentByName(department2);

                    System.out.println("Press 1 if you want to compare by number of articles");
                    System.out.println("Press 2 if you want to compare by number of members");
                    int choise = s.nextInt();
                    s.nextLine();
                    int result;
                    switch (choise){
                        case 1 -> {
                            Comparator<Department> c1 = new CompareDepByNumOfArticles();
                            result = c1.compare(theDepartment1, theDepartment2);
                            if (result > 0) {
                                System.out.println(department1 + " has more articles");
                            } else if (result < 0) {
                                System.out.println(department2 + " has more articles");
                            } else {
                                System.out.println("Both have same number of articles");
                            }
                        }

                        case 2 ->{
                            Comparator<Department> c2 = new CompareDepByNumOfMembers();
                            result = c2.compare(theDepartment1, theDepartment2);
                            if (result > 0){
                                System.out.println(department1 + " has more members");
                            } else if (result < 0) {
                                System.out.println(department2 + " has more members");
                            }
                            else {
                                System.out.println("Both have same number of members");
                            }
                        }

                        default -> {
                            System.out.println("Not a valid choice");
                        }
                    }
                }

                case 14 ->{
                    System.out.println("Enter the name of the committee you want to duplicate:");
                    String committeeName = s.nextLine();

                    try {
                        if (!college.getCommitteesNames().contains(college.findCommitteeByName(committeeName))) {
                            throw new CommitteeExceptions.CommitteeNotExistsException();
                        }
                    } catch (CommitteeExceptions.CommitteeNotExistsException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    Committees committee = college.findCommitteeByName(committeeName);
                    Committees newCommittee = committee.clone();
                    college.addCommittee(newCommittee);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

        } while (userChoose != 0);

        s.close();
    }

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("==========");
        System.out.println("0) Exit");
        System.out.println("1) Add a lecturer");
        System.out.println("2) Add a committee");
        System.out.println("3) Add a member to a committee");
        System.out.println("4) Update lecturer chairperson");
        System.out.println("5) Remove committee member");
        System.out.println("6) Add a study department");
        System.out.println("7) Average salary of all college lecturers");
        System.out.println("8) Average salary of lecturers in a department");
        System.out.println("9) Details of all lecturers");
        System.out.println("10) Details of all committees");
        System.out.println("11) Add existing lecturer to existing department");
        //System.out.println("12) Compare two lecturers by id");
        System.out.println("12) Compare two DR/PROF by number of articles");
        System.out.println("13) Compare two department by num of articles or num of members");
        System.out.println("14) Duplicate a committee");

    }
}
