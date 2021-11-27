import javax.management.relation.Role;
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
        if(instance == null){
            instance=new Partie();
        }
        return instance;
    }

    private Partie(){ //constructeur de la classe Partie
        tabjoueur= new ArrayList<Joueur>(); //initialisation de la collection de Joueur
        jeuCarte = new JeuCarte(); //initialisation de la collection de Carte -> toutes les cartes présentes dans une partie
        carteDiscarte = new ArrayList<CarteRumeur>();
        tabjoueurSup = new ArrayList<Joueur>();
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
            while(!this.getTabjoueur().get(i).getNom().equals(nom) && i < this.getTabjoueur().size()){ //Tant qu'on ne trouve pas le nom du joueur on incrémente i
                i++;
            }
            if (i == this.getTabjoueur().size()){ //Si le prénom n'est pas présent dans le tableau de Joueur, on demnde à l'utilisateur de ressaisir le nom.
                Scanner scanner = new Scanner(System.in);
                System.out.println("Le nom est faux. Veuillez resaisir le nom ");
                String nom2 = scanner.nextLine();
                scanner.close();
                i = 0;
            }
            else{check = true;}
        }
        return this.getTabjoueur().get(i);
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
        while (this.getTabjoueur().get(indiceJoueurGagnant).isIdEstRevele() == false){
                indiceJoueurGagnant +=1;
        }
        Joueur joueurGagnant = this.getTabjoueur().get(indiceJoueurGagnant);
        if (joueurGagnant.getRole().equals(Role.VILLAGER)){
            joueurGagnant.setPoints(joueurGagnant.getPoints()+1);
        }
        else{
            joueurGagnant.setPoints(joueurGagnant.getPoints()+2);

        }

        return this.getTabjoueur().get(indiceJoueurGagnant);
    }
    public void annoncerLeGagnantDuTour(){
        Joueur joueurGagnant = this.joueurGagnantTour();
        System.out.println(" Le joueur " + joueurGagnant.getNom()+ " a gagné ce tour ");
    }
    public void preparerNouveauTour(){
        int i=0;
        while (this.getTabjoueurSup().size() !=0){
            this.getTabjoueur().add(this.getTabjoueurSup().get(i));
            this.getTabjoueurSup().remove(this.getTabjoueurSup().get(i));
        }
        for (int j =0; j< this.getTabjoueur().size(); i++){
            this.getTabjoueur().get(j).setTour(false);
            this.getTabjoueur().get(j).getCarteJoueurMain().clear();
            this.getTabjoueur().get(j).getCarteJoueurPlateau().clear();
        }

        Joueur joueurGagnant = this.joueurGagnantTour();
        joueurGagnant.setTour(true);
        jeuCarte = new JeuCarte();
    }
    public boolean terminerJeu(){
        boolean check = true;
        for (int i = 0; i < this.getTabjoueur().size(); i++){
            if (this.getTabjoueur().get(i).getPoints() < 5 ){
                check = false;
            }
        }
        if (check){
            return true;
        }
        else{return false;}
    }
    public Joueur joueurGagnantJeu(){
        int pointJoueurGagnant= this.getTabjoueur().get(0).getPoints();
        int indiceJoueurGagnant=0;
        for (int i =1; i < this.getTabjoueur().size(); i++){
            if (this.getTabjoueur().get(i).getPoints() > pointJoueurGagnant){
                pointJoueurGagnant = this.getTabjoueur().get(i).getPoints();
                indiceJoueurGagnant = i;
            }
        }
        return this.getTabjoueur().get(indiceJoueurGagnant);
    }
    public void annonceLeGagnantJeu(){
        Joueur joueurGagnantJeu = this.joueurGagnantJeu();
        System.out.println(" Le joueur " + joueurGagnantJeu.getNom()+ " a gagné  ");

    }
    public void commencerJeu(){
        int i =0;
        while (this.getTabjoueur().get(i).isTour() == false){
            i += 1;
        }
        this.getTabjoueur().get(i).commencerTour();
    }
    public static void main (String[] args){
        Partie witchHunt = Partie.getInstance();
        Role villager = 

        Joueur Dung = new Joueur("Dung", Role.VILLAGER);
        Joueur Tri = new Joueur("Tri", Role.WITCH) ;
        System.out.println(Dung.getRole());
//
//        witchHunt.ajouterJoueur(Dung);
//        witchHunt.ajouterJoueur(Tri);
//        Dung.setTour(true);
//        witchHunt.distribuerCartesRumours();
////        while (witchHunt.terminerJeu() == false){
////
////            while (witchHunt.terminerTour() == false){
////                witchHunt.commencerJeu();
////            }
////            witchHunt.annoncerLeGagnantDuTour();
////            witchHunt.preparerNouveauTour();
////            witchHunt.distribuerCartesRumours();
////        }
//        witchHunt.annonceLeGagnantJeu();
//        //String accuse = "Dung";
//        //Joueur Long = witchHunt.chercherJoueur(accuse);
//        //System.out.println(Long.getNom());
//        System.out.println(Dung.getRole());


    }
}
