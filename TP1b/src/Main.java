import java.util.ArrayList;

/**
 * Created by Charles on 13/03/2018.
 */
public class Main {
    public static void main (String[] args){
        Probleme problem = new Probleme();
        ArrayList <Boolean> b = new ArrayList<>();
        b.add(true);
        b.add(true);
        State init = new State(0,b,2);
        Algo algo  = new Algo();
        ArrayList<String> res = algo.resoudreProbleme(init,problem);
        for (int i = 0; i<= res.size()-1;i++){
            System.out.println(res.get(i));
        }
    }


}
