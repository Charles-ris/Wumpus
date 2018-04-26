import java.util.ArrayList;

/**
 * Created by Charles on 26/04/2018.
 */
public class Main {

    public static void main (String[] args){
        State s = new State(12,5,true,0,2,11);
        Probleme p = new Probleme();
        System.out.println(s.toString());
        s= p.transition(s,"droite");

        System.out.println(s.toString());
        s=p.transition(s,"droite");

        System.out.println(s.toString());

        System.out.println(s.getPosHero());

        s=p.transition(s,"gauche");
        System.out.println(s.toString());
    }


}
