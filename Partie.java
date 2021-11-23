import java.util.*;

public class Partie {

    private int nbrJoueur, round;
    private ArrayList<Joueur> tabjoueur, tabjoueurSup;
    private JeuCarte jeuCarte;
    private ArrayList<CarteRumeur> carteDiscarte;
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
        jeuCarte = new JeuCarte(); //initialisation de la collection de Carte -> toutes les cartes présentes dans une partie
        carteDiscarte = new ArrayList<CarteRumeur>();
    }

    public ArrayList<CarteRumeur> getCarteDiscarte() {
        return carteDiscarte;
    }

    public void setCarteDiscarte(ArrayList<CarteRumeur> carteDiscarte) {
        this.carteDiscarte = carteDiscarte;
    }

    public void distribuerCartesRumours(){
        jeuCarte.melanger();
        while (jeuCarte.estVide() == false){
            Iterator<Joueur> iterator = tabjoueur.iterator();
            if (tabjoueur.size() == 5 && jeuCarte.getDeckCarteRumeurs().size() == 2 ){
                CarteRumeur carteRumeur = jeuCarte.distribuerUneCarte();
                carteDiscarte.add(carteRumeur);
            }
            else{
                while (iterator.hasNext()){
                    CarteRumeur carteRumeur = jeuCarte.distribuerUneCarte();
                    iterator.next().ramasserCarte(carteRumeur);
                }
            }
        }
    }
    public int chercherJoueur(String nom){ //méthode pour rechercher le nom d'un joueur parmis ceux déjà existants
        int i=0;
        boolean check = false; // la variable check permet de voir si le nom est présent dans le tableau de joueur.
        while (check == false){
            while(!tabjoueur.get(i).getNom().equals(nom) && i < tabjoueur.size()){ //Tant qu'on ne trouve pas le nom du joueur on incrémente i
                i++;
            }
            if (i == tabjoueur.size()){ //Si le prénom n'est pas présent dans le tableau de Joueur, on demnde à l'utilisateur de ressaisir le nom.
                Scanner scanner = new Scanner(System.in);
                System.out.println("Le nom est faux. Veuillez resaisir le nom ");
                String nom2 = scanner.nextLine();
                scanner.close();
                i = 0;
            }
            else{check = true;}
        }
        return i;
    }


    public ArrayList<Joueur> getTabjoueur() {
        return tabjoueur;
    }


    public static void main (String[] args){

    }
}
