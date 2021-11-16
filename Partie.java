import java.util.*;

public class Partie {

    private int nbrJoueur, round;
    private ArrayList<Joueur> tabjoueur, tabjoueurSup;
    private ArrayList<Carte> carte;
    private static Partie instance;

    public void ajouterJoueur(int nbrJoueur, int refJoueur){
        for(int i=0;i<nbrJoueur; i++){
            tabjoueur.add(new Joueur(refJoueur));
        }
    }

    public ArrayList<Joueur> getTabjoueurSup() {
        return tabjoueurSup;
    }

    public void setTabjoueurSup(ArrayList<Joueur> tabjoueurSup) {
        this.tabjoueurSup = tabjoueurSup;
    }

    public void supprimerJoueur(Joueur joueur){
        tabjoueur.remove(joueur);
    }

    public static Partie getInstance(){ //Méthode pour le patron de conception Singleton
        if(instance==null){
            instance=new Partie();
        }
        return instance;
    }

    private Partie(){ //constructeur de la classe Partie
        this.tabjoueur=new ArrayList<Joueur>(); //initialisation de la collection de Joueur
        this.carte=new ArrayList<Carte>(); //initialisation de la collection de Carte -> toutes les cartes présentes dans une partie
    }

    public void melangerCarte(ArrayList<Carte> carte){ //on utilise la méthode shuffle pour mélanger toutes les cartes du jeu
        Collections.shuffle(carte);
    }

    public ArrayList<Joueur> getTabjoueur() {
        return tabjoueur;
    }


    public static void main (String[] args){

    }
}
