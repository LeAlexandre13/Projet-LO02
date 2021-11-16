import java.util.*;

public class Carte {
   protected int refCarte;
    private ArrayList<Carte> carteJeu;

    public void distribuerCarte(int nbJoueur, Carte carteJoueur){
        for(int joueurCourant=0; joueurCourant<nbJoueur; joueurCourant++ ) {
            carteJoueur= carteJeu.remove(0); //on affecte dans le jeu de carte d'un joueur une carte du jeu puis on la supprime.
        }
    }



    public static void main (String[] args){

    }
}
