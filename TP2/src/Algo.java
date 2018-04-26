import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
/*proposer et coder un algorithme vu en cours de M1 sur ce problème
        par exemple l'algorithme de recherche en largeur d'abord) dans une classe Algo
        (avec une méthode qui prend en paramètre un problème et un état de départ)*/

/**
 * Created by Charles on 13/03/2018.
 */
public class Algo {



    public ArrayList<String> resoudreProbleme(StateCafe1 s, ProblemeCafe1 p){
        StateCafe1 init = s;
        ArrayList<StateCafe1> list = new ArrayList<StateCafe1>();
        ArrayList<StateCafe1> visit = new ArrayList<StateCafe1>();
        ArrayList<String> result = new ArrayList<String>();
        HashMap<StateCafe1,String> mapAction = new HashMap<>();
        HashMap<StateCafe1, StateCafe1> mapState = new HashMap<>();
        boolean stop = false;
        list.add(init);
        StateCafe1 fin = null;
        while(!list.isEmpty() &&  !stop){
            StateCafe1 n = list.get(0);
            System.out.println(n.getPos());
            list.remove(0);
            visit.add(n);
            if(p.estFini(n)){
                stop = true;
                fin = n;
            }else{
                String [] action = p.actions();
                for (String a : action){
                    StateCafe1 tmpState = p.transition(n,a);
                    if(!visit.contains(tmpState)){
                        list.add(list.size(),tmpState);
                        mapAction.put(tmpState,a);
                        mapState.put(tmpState,n);
                    }
                }
            }
        }

        if(fin != null){
            while(fin != init){
                result.add(mapAction.get(fin));
                fin = mapState.get(fin);
            }
        }else{
            System.out.println("pas de solution");
        }

        Collections.reverse(result);
        return result;

    }





}

