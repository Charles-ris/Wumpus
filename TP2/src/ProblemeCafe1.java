import java.util.ArrayList;

/**
 * Created by Charles on 13/03/2018.
 */
public class ProblemeCafe1 {

    public ProblemeCafe1(){

    }

    /**
     *
     * @return la liste d'action possible
     */
    String [] actions(){
        String [] result = {"gauche","droite","prendre"};
        return result;
    }

    /**
     *
     * @param s Correspond à l'etat actuel
     * @param a Correspond à l'action a effectuer
     * @return l'état correspondant à l'etat après la transition de l'etat s avec l'action a
     */
    StateCafe1 transition (StateCafe1 s, String a){
        int p = s.getPos();
        boolean b = s.getCafe();
        switch (a){
            case "gauche":
                if(p>0){
                    p--;
                }
                break;
            case "droite":
                if(p<5){
                    p++;
                }
                break;
            case "prendre":
                if (s.getPos()==5){
                    b=true;
                }
                break;
            default:
                break;
        }
        StateCafe1 result = new StateCafe1(p,b);
        return result;
    }

    public boolean estFini(StateCafe1 s){

        boolean result = false;
        if(s.getPos()==0 && s.getCafe()){
            result=true;
        }
        return  result;
    }
}
