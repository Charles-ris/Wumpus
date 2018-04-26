import java.util.ArrayList;

/**
 * Created by Charles on 07/04/2018.
 */
public class Probleme {

    String [] actions(){
        String [] result = {"gauche", "droite", "haut", "bas", "tirerG", "tirerD", "tirerH", "tirerB"};
        return result;
    }

    ArrayList <String> observation(State s, String a){
        int hero = s.getPosHero();
        int trou1 = s.getPosTrou1();
        int trou2 = s.getPosTrou2();
        int wump = s.getPosWumpus();
        boolean Btrou1 = false;
        boolean Btrou2 = false;
        ArrayList<String> res = new ArrayList<>();

        if (hero+1 == trou1 || hero-1 == trou1 || hero+4 == trou1 || hero-4 == trou1){
            Btrou1 = true;
        }

        if (hero+1 == trou2 || hero-1 == trou2 || hero+4 == trou2 || hero-4 == trou2){
            Btrou2 = true;
        }

        if (hero+1 == wump || hero-1 == wump || hero+4 == wump || hero-4 == wump){
            res.add("odeur");
        }

        if (Btrou1 || Btrou2){
            res.add("air");
        }

        return res;
    }

    State transition (State s, String a){
        State res = null;
        int hero = s.getPosHero();
        int wumpus = s.getPosWumpus();
        //int tmp = hero;
        boolean tirer = s.getArrow();
        switch (a) {
            case "gauche":
                if (hero%4 != 0){
                    hero -= 1;
                }
                break;

            case "droite":
                if (hero%4 != 3){
                    hero += 1;
                }
                break;

            case "haut":

                if (hero > 3){
                    hero -= 4;
                }
                break;

            case "bas":

                if (hero < 12){
                    hero += 4;
                }
                break;

            case "tirerG":
                if (s.getArrow()){
                    int i = hero-1;

                    while (i%4!=3 && i>=0){
                        if (wumpus == i){
                            // wumpus meurt
                            wumpus = -1;
                            break;
                        }
                        i--;
                    }
                    tirer = false;

                }
                break;

            case "tirerD":
                if (s.getArrow()){
                    int i = hero+1;

                    while (i%4!=0 && i<=15){
                        if (wumpus == i){
                            // wumpus meurt
                            wumpus = -1;
                            break;
                        }
                        i++;
                    }
                    tirer = false;
                }
                break;

            case "tirerH":
                if (s.getArrow()){
                    int i = hero-4;
                    while (i>=0){
                        if (wumpus == i){
                            // wumpus meurt
                            wumpus = -1;
                            break;
                        }
                        i-=4;
                    }
                    tirer = false;
                }
                break;

            case "tirerB":
                if (s.getArrow()){
                    int i = hero+4;
                    while (i<=15){
                        if (wumpus == i){
                            // wumpus meurt
                            wumpus = -1;
                            break;
                        }
                        i+=4;
                    }
                    tirer = false;
                }
                break;

            default:
                res = s;
                break;

        }

        res = new State(hero,wumpus,tirer,s.getPosGold(),s.getPosTrou1(),s.getPosTrou2());
        return res;
    }
}
