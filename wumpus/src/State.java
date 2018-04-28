/**
 * Created by Charles on 07/04/2018.
 */
public class State {

    /* ~~~ Attributs ~~~ */
    // Position du hero
    private int posHero;

    // Position du Wumpus
    private int posWumpus;

    // Position du trou numero 1
    private int posTrou1;

    // Position du trou numero 2
    private int posTrou2;

    // Position de l'or
    private int posGold;

    // Boolean indiquant si le hero dispose d'une fleche ou pas
    private boolean arrow;

    public int getPosGold() {
        return posGold;
    }

    public boolean getArrow() {
        return arrow;
    }

    public int getPosHero() {
        return posHero;
    }

    public int getPosTrou1() {
        return posTrou1;
    }

    public int getPosTrou2() {
        return posTrou2;
    }

    public int getPosWumpus() {
        return posWumpus;
    }

    public State(int h, int w, boolean a, int g, int t1, int t2){
        this.posHero=h;
        this.posWumpus=w;
        this.arrow=a;
        this.posGold=g;
        this.posTrou1=t1;
        this.posTrou2=t2;
    }

    public String toString(){
        // H  pour hero
        // . pour rien
        // W pour wumpus
        // O pour trou
        String tmp = "";
        String res="";

        for (int i=0; i<=15;i++){
            tmp = ".";

            if (this.posTrou1 == i){
                tmp ="O";
            }

            if (this.posTrou2 == i){
                tmp ="O";
            }

            if (this.posGold == i){
                tmp ="G";
            }

            if (this.posWumpus == i){
                tmp ="W";
            }

            if (this.posHero == i){
                tmp ="H";
            }
            res +=tmp+" ";

            if (i%4==3){
                res+="\n";
            }
        }

        res+="\n"+"Arrow :"+this.arrow;
        return res;
    }

    public boolean finPartie(){
        if (this.posHero == this.posWumpus || this.posHero == this.posTrou1|| this.posHero == this.posTrou2 ){
            return true;
        }else{
            return false;
        }
    }

}
