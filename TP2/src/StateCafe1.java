import java.util.ArrayList;

/**
 * Created by Charles on 13/03/2018.
 */
public class StateCafe1 {

    // Correspond à la position du robot
    private int pos;



    // Si le cafe est rammassé
    private boolean cafe;

    /**
     * Constructeur
     * @param p
     * @param c
     */
    public StateCafe1(int p, boolean c){
        this.pos=p;
        this.cafe=c;
    }

    public String toString(){
        return this.pos+" :: ";
    }


    /**
     *
     * @return attribut pous
     */

    /**
     *
     * @return attribut pos
     */
    public int getPos() {
        return this.pos;
    }

    public boolean getCafe() {
        return this.cafe;
    }
}
