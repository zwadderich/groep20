package raamwerk;

import com.philihp.bj.Player;

/**
 *
 * @author Jasper De Vrient
 */
public class ResultaatHand {
    private Player speler;
    private Player dealer;
    private char winnaar;
    private double winst;
    private double spelerGeld;
    
    private int spelerConstanteWaarde;

    public ResultaatHand(char winnaar, int spelerConstanteWaarde) {
        this.winnaar = winnaar;
        this.spelerConstanteWaarde = spelerConstanteWaarde;
    }

    public int getSpelerConstanteWaarde() {
        return spelerConstanteWaarde;
    }
   

    
    public Player getSpeler() {
        return speler;
    }

     void setSpeler(Player speler) {
        this.speler = speler;
    }

    public Player getDealer() {
        return dealer;
    }

     void setDealer(Player dealer) {
        this.dealer = dealer;
    }

    public char getWinnaar() {
        return winnaar;
    }

     void setWinnaar(char winnaar) {
        this.winnaar = winnaar;
    }

    public double getWinst() {
        return winst;
    }

     void setWinst(double winst) {
        this.winst = winst;
    }

    public double getSpelerGeld() {
        return spelerGeld;
    }

     void setSpelerGeld(double spelerGeld) {
        this.spelerGeld = spelerGeld;
    }

    public ResultaatHand(Player speler, Player dealer, char winnaar, double winst, double spelerGeld) {
        this.speler = speler;
        this.dealer = dealer;
        this.winnaar = winnaar;
        this.winst = winst;
        this.spelerGeld = spelerGeld;
    }
     
     
    
    
          
}
