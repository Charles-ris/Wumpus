import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Charles on 07/04/2018.
 */
public class Probleme {

    String [] actions(){
        String [] result = {"gauche", "droite", "haut", "bas", "tirerG", "tirerD", "tirerH", "tirerB"};
        return result;
    }


    public ArrayList<Integer> possibiliteW = new ArrayList<>();
    public ArrayList<Integer> possibiliteT1 = new ArrayList<>();
    public ArrayList<Integer> possibiliteT2 = new ArrayList<>();
    public ArrayList<Integer> safe = new ArrayList<>();
    public ArrayList<Integer> visite = new ArrayList<>();
    public int bloque = 0;

    public String decision(State s){
        // ajoute la case sur laquelle se trouve le hero comme case sure et visite
        if (!safe.contains(s.getPosHero())){
            safe.add(s.getPosHero());
        }
        if (!visite.contains(s.getPosHero())){
            visite.add(s.getPosHero());
        }

        // Rend compte des observations
        ArrayList<Integer> deplacements = deplacementsPossibles(s);
        ArrayList <String> observations = observation(s);
        if (observations.isEmpty()){
            safe.addAll(deplacements);
        }else{
            for (String obs : observations){
                if (obs.equals("air")){
                    // Observation courant d'air
                    possibiliteT1.addAll(deplacements);
                    possibiliteT2.addAll(deplacements);
                    // On vérifie si les positions des trous sont connues
                    if (possibiliteT1.equals(possibiliteT2)){
                        if (!possibiliteT1.equals(deplacements)){
                            ArrayList <Integer> tmp = new ArrayList<>();
                            for (int Itmp : deplacements){
                                if (possibiliteT1.contains(Itmp)){
                                    tmp.add(Itmp);
                                }
                            }
                            // Vérifie s'il est possible de différencier les deux trous
                            if (tmp.isEmpty()){
                                possibiliteT1=new ArrayList<>();
                                possibiliteT1.addAll(deplacements);
                            }else{
                                possibiliteT1=new ArrayList<>();
                                possibiliteT1.addAll(tmp);
                            }
                        }
                    }else{
                        // Si les trous sont déjà différencié
                        for (int Itmp : deplacements){
                            if (possibiliteT1.contains(Itmp) && !possibiliteT2.contains(Itmp)){
                                possibiliteT1.clear();
                                possibiliteT1.add(Itmp);
                            }else{
                                if (!possibiliteT1.contains(Itmp) && possibiliteT2.contains(Itmp)){
                                    possibiliteT2.clear();
                                    possibiliteT2.add(Itmp);
                                }
                            }
                        }

                    }


                }else{
                    // CAS WUMPUS
                    // Observation odeur wumpus
                    if (possibiliteW.isEmpty()) { // si nous n'avons aucune information concernant le wumpus nous ajoutons celles que l'on vient d'obtenir
                        possibiliteW.addAll(deplacements);
                    }else{
                        if (possibiliteW.size()!=1){ // Sinon si nous n'avons pas déjà identifé la position du wumpus
                            if (!possibiliteW.equals(deplacements)){ // On vérifie que les observations déjà receuillies ne proviennent pas de la même case
                                // On recoupe les informations pour identifier la position du wumpus
                                ArrayList <Integer> tmp = new ArrayList<>();
                                for (int Itmp : deplacements){
                                    if (possibiliteW.contains(Itmp)){
                                        tmp.add(Itmp);
                                    }
                                }
                                possibiliteW = new ArrayList<>();
                                possibiliteW.addAll(tmp);
                            }
                        }
                    }
                }
            }

            for (int Itmp : deplacements){
                if (!possibiliteW.contains(Itmp) && !possibiliteT1.contains(Itmp) && !possibiliteT2.contains(Itmp)){
                    safe.add(Itmp);
                }
            }
            for (int Itmp : safe){
                if (possibiliteT1.contains(Itmp)){
                    possibiliteT1.remove((Integer) Itmp);
                }
                if (possibiliteT2.contains(Itmp)){
                    possibiliteT2.remove((Integer)Itmp);
                }
                if (possibiliteW.contains(Itmp)){
                    possibiliteW.remove((Integer)Itmp);
                }
            }

        }
        Set set = new HashSet() ;
        set.addAll(safe) ;
        safe = new ArrayList(set) ;
        set = new HashSet() ;
        set.addAll(possibiliteT1) ;
        possibiliteT1 = new ArrayList(set) ;
        set = new HashSet() ;
        set.addAll(possibiliteT2) ;
        possibiliteT2 = new ArrayList(set) ;
        Collections.sort(safe);
        Collections.sort(visite);
        Collections.sort(possibiliteT1);
        Collections.sort(possibiliteT2);


        // IA
        // Si nous avons identifier la localisation du wumpus et qu'il se trouve à coté de nous
        if (possibiliteW.size()==1 && deplacements.contains(possibiliteW.get(0)) && s.getArrow()){
            // tirer dans la direction du wumpus
            if (s.getPosHero()+1==possibiliteW.get(0)){
                //tirer à droite
                return "tirerD";
            }else{
                if (s.getPosHero()-1==possibiliteW.get(0)){
                    //tirer à gauche
                    return "tirerG";
                }else{
                    if (s.getPosHero()+4==possibiliteW.get(0)){
                        //tirer en haut
                        return "tirerB";
                    }else{
                        //tirer en bas
                        return "tirerH";
                    }
                }
            }

        }else{
            // Dans le cas ou il ne peut pas tirer sur le wumpus
            //Explorer case non Explorée
            for (int Itmp : deplacements){
                if (!visite.contains(Itmp) && safe.contains(Itmp)){
                    return directionDeplacement(s,Itmp);
                }
            }

            // Dans le cas ou il ne peut emprunter de chemin sur mais qu'il en reste
            if (!visite.equals(safe)){
                for (int Itmp : deplacements){
                    if (safe.contains(Itmp)){
                        return directionDeplacement(s,Itmp);
                    }
                }
            }else{
                // Il n'y a aucun chemin safe non exploré
                if (bloque>15){
                    return directionDeplacement(s,deplacements.get(0));
                }else{
                    bloque+=1;
                    for (int Itmp : deplacements){
                        if (visite.contains(Itmp)){
                            return directionDeplacement(s,Itmp);
                        }
                    }
                }
            }




        }








        // Si Case non Parcourue safe
        // Se deplacer dans cette case
        // Sinon se deplacer dans case déjà exploré  /!\ ATTENTION BOUCLE INFINE /!\ (compteur allant jusqu'à 16 (nombre case))

        // Sinon se deplacer à l'aveugle
        return "erreur";
    }

    /**
     * Retourne la liste des déplacements possibles du hero sans tenir compte des observations
     * @param s
     * @return
     */
    public ArrayList<Integer> deplacementsPossibles(State s){
        // Liste des déplacements possibles
        ArrayList <Integer> res = new ArrayList<>();

        // Recupere position du hero
        int hero = s.getPosHero();

        // case du haut
        int haut = hero - 4;
        // case du bas
        int bas = hero + 4;

        if (hero%4 == 0){
            // si c'est possible d'aller à droite ajoute la case de droite
            res.add(hero+1);
        }else{
            if(hero%4!=3){
                res.add(hero+1);
                res.add(hero-1);
            }else{
                // si c'est possible d'aller à gauche ajoute la case de gauche
                res.add(hero-1);
            }
        }



        if(haut>=0){
            res.add(haut);
        }
        if(bas<=15){
            res.add(bas);
        }
        Collections.shuffle(res);
        return res;
    }

    /**
     * donne la direction de depalcement pour une case
     * @param s
     * @param c
     * @return
     */
    public String directionDeplacement(State s, int c){
        if (c == s.getPosHero()+1){
            return "droite";
        }else{
            if (c == s.getPosHero()-1){
                return "gauche";
            }else{
                if (c == s.getPosHero()+4){
                    return "bas";
                }else{
                    return "haut";
                }
            }
        }
    }


    public ArrayList <String> observation(State s){
        int hero = s.getPosHero();
        int trou1 = s.getPosTrou1();
        int trou2 = s.getPosTrou2();
        int wump = s.getPosWumpus();
        boolean Btrou = false;
        ArrayList<String> res = new ArrayList<>();
        ArrayList<Integer> dep = deplacementsPossibles(s);
        for (int Itmp : dep){
            if (Itmp==trou1 || Itmp == trou2){
                Btrou = true;
            }else{
                if (Itmp==wump){
                    res.add("odeur");
                }
            }
        }

        if (Btrou){
            res.add("air");
        }

        return res;
    }

    public State transition (State s, String a){
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
