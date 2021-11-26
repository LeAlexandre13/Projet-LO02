import java.util.*;

public class Partie {

    private int nbrJoueur, round;
    private ArrayList<Joueur> tabjoueur, tabjoueurSup;
    private JeuCarte jeuCarte;
    private ArrayList<CarteRumeur> carteDiscarte;
    private static Partie instance;

    public void ajouterJoueur(Joueur joueur){
        tabjoueur.add(joueur);
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
        tabjoueur=new ArrayList<Joueur>(); //initialisation de la collection de Joueur
        jeuCarte = new JeuCarte(); //initialisation de la collection de Carte -> toutes les cartes présentes dans une partie
        carteDiscarte = new ArrayList<CarteRumeur>();
    }
    public ArrayList<Joueur> getTabjoueur() {
        return tabjoueur;
    }

    public void setTabjoueur(ArrayList<Joueur> tabjoueur) {
        this.tabjoueur = tabjoueur;
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
    public Joueur chercherJoueur(String nom){ //méthode pour rechercher le nom d'un joueur parmis ceux déjà existants
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
        return tabjoueur.get(i);
    }
    public boolean terminerTour(){
        int compterPersonneRevelePasID =0;
        for (int i= 0; i < this.getTabjoueur().size(); i++){
            if (this.getTabjoueur().get(i).isIdEstRevele() == false){
                compterPersonneRevelePasID +=1;
            }
        }
        if (compterPersonneRevelePasID == 1){
            return true;
        }
        else {return  false;}
    }
    public Joueur joueurGagnantTour(){
        int indiceJoueurGagnant =0;
        if (this.terminerTour()){
            while (this.getTabjoueur().get(indiceJoueurGagnant).isIdEstRevele() == false){
                indiceJoueurGagnant +=1;
            }
        }
        System.out.println("Le joueur " + this.getTabjoueur().get(indiceJoueurGagnant).getNom()+" a gagné ce tour");
        return this.getTabjoueur().get(indiceJoueurGagnant);
    }
    public void preparerNouveauTour(){
        int i=0;
        while (this.getTabjoueurSup().size() !=0){

        }
    }





    public static void main (String[] args){

    }
}
