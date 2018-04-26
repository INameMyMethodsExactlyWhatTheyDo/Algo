/**
 * Kyle Zhou
 */

import java.util.ArrayList;
import java.util.LinkedList;


public class Program1 extends AbstractProgram1 {
    /**
     * Determines whether a candidate Matching represents a solution to the
     * Stable Marriage problem. 
     */
    public boolean isStableMatching(Matching marriage) {
        /* Goes through each matching to see if there is instability*/
        for(int i = 0; i < marriage.getNumberOfStudents(); i++){
            if(betterMatchExist(marriage, i)){
                return false;
            } 
        }
        return true;
    }

    /**
     * @return the distance between two coordinates
     */
    private double distance(Coordinate pro, Coordinate stu){
        return Math.sqrt(Math.pow((pro.x-stu.x), 2) + 
        (Math.pow((pro.y - stu.y), 2)));
    }

    private boolean betterMatchExist(Matching marriage, int stu){
        int pro, professor, num, student, proRank;
        double stuGPA, studentGPA, stuDis, studentDis;
        Coordinate stuLoc, studentLoc, proLoc;
        pro = marriage.getStudentMatching().get(stu);
        proRank = marriage.getStudentPreference().get(stu).indexOf(pro);
        for(int i = 0; i < proRank; i++){
            professor = marriage.getStudentPreference().get(stu).get(i);
            student = marriage.getStudentMatching().indexOf(professor);
            studentGPA = marriage.getStudentGPAs().get(student);
            stuGPA = marriage.getStudentGPAs().get(stu);
            if(stuGPA > studentGPA){
                return true;
            } else if(stuGPA == studentGPA){
                stuLoc = marriage.getStudentLocations().get(stu);
                studentLoc = marriage.getStudentLocations().get(student);
                proLoc = marriage.getAdviserLocations().get(professor);
                stuDis = distance(stuLoc, proLoc);
                studentDis = distance(studentLoc, proLoc);
                if(stuDis < studentDis){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines a solution to the Stable Marriage problem from the given input
     * set.
     * 
     * @return A stable Matching.
     */
    public Matching stableMarriageGaleShapley(Matching marriage) {
        int pro, stu, professor, matches = 0;
        pro = marriage.getNumberOfAdvisers();
        stu = marriage.getNumberOfStudents();
        ArrayList<Integer> list = new ArrayList<Integer>();
        boolean[] avalible = new boolean[stu];
        int[] prefIndex = new int[stu];
        initArray(avalible, list, prefIndex, stu);
        
        for(int i = 0; matches < pro; i++){
            for(int k = 0; k < stu ; k++){
                //student k is avalible
                if(avalible[k] == true){
                    professor = marriage.getStudentPreference().get(k).get(prefIndex[k]);
                    matches += makeOffer(list, marriage, k, professor, avalible);
                    prefIndex[k] += 1;
                }
            }
        }
        
        marriage.setResidentMatching(list);
        return marriage; 
    }

    //Helpers 
    private void initArray(boolean[] x, ArrayList<Integer> list, int[] y, int n){
        for(int i = 0; i < n; i++){
            x[i] = true;
            y[i] = 0;
            list.add(null);
        }
    }
    private int makeOffer(ArrayList<Integer> list, Matching marriage, int stu, int pro, boolean[] ava){
        //If professor is avalible
        if(list.indexOf(pro) == -1 ){
            list.set(stu, pro);
            ava[stu] = false;
            return 1;
        }
        //If professor has a match already
        int student;
        double studentGPA, studentDis, stuGPA, stuDis;
        Coordinate stuLoc, studentLoc, proLoc;
        student = list.indexOf(pro);
        studentGPA = marriage.getStudentGPAs().get(student);
        stuGPA = marriage.getStudentGPAs().get(stu);
        //Compare GPA
        if(stuGPA > studentGPA){
            list.set(student, null);
            list.set(stu, pro);
            ava[student] = true;
            ava[stu] = false;
            return 0;
        //Compare Locations
        }
        if(stuGPA == studentGPA){
            studentLoc = marriage.getStudentLocations().get(student);
            stuLoc = marriage.getStudentLocations().get(stu);
            proLoc = marriage.getAdviserLocations().get(pro);
            stuDis = distance(stuLoc, proLoc);
            studentDis = distance(studentLoc, proLoc);
            if(stuDis < studentDis){
                list.set(student, null);
                list.set(stu, pro);
                ava[student] = true;
                ava[stu] = false;
                return 0;
            }
        }
        return 0;
    }
}
