import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;

/**
 * Created by Charles on 13/03/2018.
 */
public class State {

    // Correspond à la position du robot
    private int pos;

    // Etat de la poussiere
    private ArrayList<Boolean> pous;

    // Nombre de case
    private int limite;

    /**
     * Constructeur
     * @param p
     * @param b
     */
    public State(int p, ArrayList<Boolean> b,int l){
        if(p > l-1){
            p = this.limite-1;
        }
        if(p<0){
            p=0;
        }
        this.pos=p;
        this.pous=b;
        this.limite =l;
    }

    public String toString(){
        return this.pos+" :: "+this.pous.get(0)+" "+this.pous.get(1);
    }


    /**
     *
     * @return attribut pous
     */
    public ArrayList<Boolean> getPous() {
        return this.pous;
    }

    /**
     *
     * @return attribut pos
     */
    public int getPos() {
        return this.pos;
    }

    public int getLimite() {
        return this.limite;
    }
}
