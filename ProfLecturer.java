package Collegeproject;

import java.util.ArrayList;
import java.util.Vector;

public class ProfLecturer extends DrLecturer{
    private String institution;

    public ProfLecturer (String name, String id, Degree deg, Double salary, Department dep, String degreeName, ArrayList<String> articles, String institution){
        super(name, id, deg, salary, dep, degreeName, articles);
        this.institution = institution;
    }

    public ProfLecturer (String name, String id, Degree deg, Double salary, String degreeName, ArrayList<String> articles, String institution){
        super(name, id, deg, salary, degreeName, articles);
        this.institution = institution;
    }

    public String getInstitution() {
        return institution;
    }


    public void printLecturer() {
        super.printLecturer();
        System.out.println("The Degree-Granting Institution is: " +institution);

    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof ProfLecturer)){
            return false;
        }

        if (!(super.equals(other))){
            return false;
        }

        ProfLecturer p = (ProfLecturer) other;
        return p.getInstitution().equals(this.institution);
    }

    @Override
    public String toString(){
        return super.toString() + "\n" + "The Degree-Granting Institution is: " + institution;
    }

}
