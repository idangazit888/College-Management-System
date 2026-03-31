package Collegeproject;

import java.util.Comparator;

public class CompareDepByNumOfMembers implements Comparator<Department> {

    @Override
    public int compare(Department d1, Department d2){
        if (d1.getLecturerCount() < d2.getLecturerCount()){
            return -1;
        }
        else if (d1.getLecturerCount() > d2.getLecturerCount()){
            return 1;
        }
        else {
            return 0;
        }
    }

}
