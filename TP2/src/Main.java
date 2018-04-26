import java.util.ArrayList;

/**
 * Created by Charles on 13/03/2018.
 */
public class Main {
    public static void main (String[] args){
        ProblemeCafe1 problem = new ProblemeCafe1();
        ArrayList <Boolean> b = new ArrayList<>();
        b.add(true);
        b.add(true);
        StateCafe1 init = new StateCafe1(0,false);
        Algo algo  = new Algo();
        ArrayList<String> res = algo.resoudreProbleme(init,problem);
        for (int i = 0; i<= res.size()-1;i++){
            System.out.println(res.get(i));
        }
    }


}
