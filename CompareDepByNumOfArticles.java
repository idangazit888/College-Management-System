package Collegeproject;

import java.util.Comparator;

public class CompareDepByNumOfArticles implements Comparator<Department> {

    @Override
    public int compare(Department d1, Department d2){
        if (d1.getNumOfArticles() < d2.getNumOfArticles()){
            return -1;
        }
        else if (d1.getNumOfArticles() > d2.getNumOfArticles()){
            return 1;
        }
        else {
            return 0;
        }
    }

}