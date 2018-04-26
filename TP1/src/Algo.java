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



    public ArrayList<String> resoudreProbleme(State s, Probleme p){
        State init = s;
        ArrayList<State> list = new ArrayList<State>();
        ArrayList<State> visit = new ArrayList<State>();
        ArrayList<String> result = new ArrayList<String>();
        HashMap<State,String> mapAction = new HashMap<>();
        HashMap<State,State> mapState = new HashMap<>();
        boolean stop = false;
        list.add(init);
        State fin = null;
        while(!list.isEmpty() &&  !stop){
            State n = list.get(0);
            list.remove(0);
            visit.add(n);
            if(p.estFini(n)){
                stop = true;
                fin = n;
            }else{
                String [] action = p.actions();
                for (String a : action){
                    State tmpState = p.transition(n,a);
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
            //esult.add(mapAction.get(fin));
        }else{
            System.out.println("pas de solution");
        }

        Collections.reverse(result);
        return result;

    }





}
