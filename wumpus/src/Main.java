import java.util.ArrayList;

/**
 * Created by Charles on 26/04/2018.
 */
public class Main {

    public static void main (String[] args){
       /* State s = new State(12,5,true,0,2,11);
        Probleme p = new Probleme();
        System.out.println(s.toString());
        s= p.transition(s,"droite");

        System.out.println(s.toString());
        s=p.transition(s,"droite");

        System.out.println(s.toString());

        System.out.println(s.getPosHero());

        s=p.transition(s,"gauche");
        System.out.println(s.toString());*/


        State s = new State(12,4,true,0,1,6);
        Probleme p = new Probleme();
        //p.transition(s,"droite");
       // System.out.println(p.deplacementsPossibles(s));
        //System.out.println(s.toString());
        while (s.getPosHero()!=s.getPosGold() && !s.finPartie()){

            System.out.println("observation : "+p.observation(s));
            String action = p.decision(s);
            System.out.println("décision : "+action);
            System.out.println("safe : "+p.safe);
            System.out.println("trou1 : "+p.possibiliteT1);
            System.out.println("trou2 "+p.possibiliteT2);
            System.out.println(p.possibiliteW +"  Wump");
            System.out.println(s.toString());
            s = p.transition(s,action);
            System.out.println();
            System.out.println();
        }
        if (s.finPartie()){
            System.out.println("Perdu !");
        }else{
            System.out.println("Gagné !");
        }
        System.out.println(s.getPosHero());
        System.out.println(p.bloque);


        ////// REVOIR LE CAS OU UNE CASE PEUT DEVENIR SAFE ///////

    }


}
