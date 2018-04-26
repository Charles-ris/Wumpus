import java.util.ArrayList;

/**
 * Created by Charles on 13/03/2018.
 */
public class Probleme {

    public Probleme(){

    }

    /**
     *
     * @return la liste d'action possible
     */
    String [] actions(){
        String [] result = {"gauche","droite","aspirer"};
        return result;
    }

    /**
     *
     * @param s Correspond à l'etat actuel
     * @param a Correspond à l'action a effectuer
     * @return l'état correspondant à l'etat après la transition de l'etat s avec l'action a
     */
    State transition (State s, String a){
        int p = s.getPos();
        int l = s.getLimite();
        ArrayList <Boolean> b = new ArrayList<>();
        for (int i=0; i<=s.getPous().size()-1;i++){
            b.add(i,s.getPous().get(i));
        }
        State result = null;
        switch (a){
            case "gauche":
                p -=1;
                result = new State(p,b,l);
                break;

            case "droite":
                p+=1;
                result = new State(p,b,l);
                break;

            case "aspirer":
                b.remove(p);
                b.add(p,false);
                result = new State(p,b,l);
                break;

            default:
                result = new State(p,b,l);
                break;
        }
        return result;
    }
}
