package Collegeproject;

import java.util.ArrayList;
import java.util.Vector;

public class DrLecturer extends Lecturer implements Comparable <DrLecturer>{
    private ArrayList<String> articles;

    public DrLecturer (String name, String id, Degree deg, Double salary, Department dep, String degreeName, ArrayList<String> articles){
        super(name, id, deg, salary, dep, degreeName);
        this.articles = articles;

    }

    public DrLecturer (String name, String id, Degree deg, Double salary, String degreeName, ArrayList<String> articles){
        super(name, id, deg, salary, degreeName);
        this.articles = articles;
    }

    public ArrayList<String> getArticles() {
        return articles;
    }

    public int getNumOfArticles(){
        return this.articles.size();
    }

    public void printLecturer() {
        super.printLecturer();
        System.out.println("articles list:");
        if (articles.size() == 0){
            System.out.println("None");
        }
        else {
            for (int i = 0; i < articles.size(); i++) {
                System.out.println(articles.get(i));
            }
        }
    }

    @Override
    public int compareTo(DrLecturer d){
        if (articles.size() < d.getArticles().size()){
            return -1;
        }
        else if (articles.size() > d.getArticles().size()) {
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof DrLecturer)){
            return false;
        }

        if (!(super.equals(other))){
            return false;
        }

        DrLecturer d = (DrLecturer) other;
        if (articles.size() != d.getArticles().size()){
            return false;
        }

        boolean flag = true;
        for (int i = 0; i < articles.size(); i++) {
            flag = articles.get(i).equals(d.getArticles().get(i));
            if (!flag){
                return false;
            }
        }

        return true;

    }

    @Override
    public String toString(){
        StringBuffer res = new StringBuffer(super.toString());
        if (articles.size() == 0){
            res.append("None");
        }
        else {
            for (int i = 0; i < articles.size(); i++) {
                res.append(articles.get(i) + "\n");
            }
        }
        return res.toString();
    }

}
