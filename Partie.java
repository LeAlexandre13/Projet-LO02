import java.util.*;

public class Partie {

    private int nbrJoueur, round;
    private static ArrayList<Joueur> tabjoueur, tabjoueurSup;
    private JeuCarte jeuCarte;
    private ArrayList<CarteRumeur> carteDiscarte;
    private static Partie instance;

    public static void ajouterJoueurReels(int refJoueur){
        tabjoueur.add(new Joueur(refJoueur));
    }

    public static void ajouterJoueurVirtuels(int refJoueur){
        tabjoueur.add(new JoueurVirtuel(refJoueur));
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

    public void commencerJeu(){
        int i =0;
        while (this.getTabjoueur().get(i).isTour() == false){
            i += 1;
        }
        this.getTabjoueur().get(i).commencerTour();
    }


    public static void main (String[] args){

        Partie witchHunt = Partie.getInstance(); //crée une partie

        //Etape pour ajouter des joueurs réels
        Scanner inputNbJR=new Scanner(System.in);
        System.out.println("Nombre de joueurs réels");
        int nbJoueurR=inputNbJR.nextInt();

        for(int i=0; i<nbJoueurR; i++){
           ajouterJoueurReels(i);
           Scanner inputNom=new Scanner(System.in);
           System.out.println("Saisir un nom pour le joueur "+i);
           String nomJoueurReel=inputNom.nextLine();
           tabjoueur.get(i).nom=nomJoueurReel;

        }
        //fin ajout joueurs réels

        //Etape pour ajouter des joueurs virtuels
        Scanner inputNbJV=new Scanner(System.in);
        System.out.println("Nombre de joueurs virtuels");
        int nbJoueurV=inputNbJV.nextInt();

        for(int j=nbJoueurR; j<nbJoueurV+nbJoueurR; j++){
            ajouterJoueurVirtuels(j);
            Scanner inputNomV=new Scanner(System.in);
            System.out.println("Saisir un nom pour le joueur "+j);
            String nomJoueurVirtuel=inputNomV.nextLine();
            tabjoueur.get(j).nom=nomJoueurVirtuel;
        }
        //fin ajout joueurs virtuels

        //choix du rôle pour chaque joueur
        for(int k=0; k<nbJoueurV+nbJoueurR; k++){
            tabjoueur.get(k).choisirRole();
        }

        JeuCarte jeuCarte=new JeuCarte(); //on crée un nouveau jeu de carte

        jeuCarte.melanger();//on mélange les cartes

        int nbCarte=0; //nombre de carte total qu'un joueur peut avoir
        for(int i=0; i<tabjoueur.size(); i++){
                if(tabjoueur.size()==3) {
                    nbCarte = 4;
                }
                else if(tabjoueur.size()==4) {
                    nbCarte = 3;
                }
                else if(tabjoueur.size()==3) {
                    nbCarte = 2;
                }
                for(int j=1; j<nbCarte+1; j++) {
                    tabjoueur.get(i).carteJoueurMain.get(j) = jeuCarte.distribuerUneCarte();
                }
            }
        }




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