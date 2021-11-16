import java.util.*;

public class Joueur {

    private String nom;
    protected static int nbCarte, points, refJoueur;
    private boolean witch, estAccuse, idEstRevele, tour;
    private ArrayList<Carte> carteJoueurMain, carteJoueurPlateau;

    public Joueur(int refJoueur) { //constructeur de Joueur
        this.refJoueur=refJoueur;
        this.carteJoueurMain=new ArrayList<Carte>();
    }

    public boolean isWitch() {
        return witch;
    }

    public boolean isTour() {
        return tour;
    }

    public void setTour(boolean tour) {
        this.tour = tour;
    }

    public boolean isEstAccuse() {
        return estAccuse;
    }

    public void setEstAccuse(boolean estAccuse) {
        this.estAccuse = estAccuse;
    }

    public void setNom(String nom) {
        this.nom=nom;
    }

    public void setWitch(boolean witch) { //Méthode pour que le joueur choisisse son rôle
        this.witch=witch;
    }

    public void setPoints(int points){ //Ajout d'un certain nombre de points pour un joueur donné
        this.points= points;
    }

    public int getPoints() { //getter pour récupérer le nombre de points d'un joueur
        return this.points;
    }

    public String getNom() {
        return nom;
    }


    public boolean revelerId(Joueur joueur){
        if(this.witch == true){
            Partie.getInstance().getTabjoueurSup().add(this);
            Partie.getInstance().getTabjoueur().remove(this);
            return true;
        }
        else{
            return false;
        }
    }

    public void accuser() { //Pour une reférence de joueur donné, la variable estAccusé de l'objet de type Joueur prend la valeur "true".
        Scanner inputAction=new Scanner(System.in); //création d'un objet de type scanner pour récupérer les inputs de l'utilisateur
        System.out.println("QuelJoueur voulez accuser ? :");
        String accuser=inputAction.nextLine();
        SystemJeu systemJeu = new SystemJeu();
        ;
        int indice = systemJeu.chercherJoueur(accuser, Partie.getInstance().getTabjoueur());
        Partie.getInstance().getTabjoueur().get(indice).setEstAccuse(true);
        Partie.getInstance().getTabjoueur().get(indice).setTour(true);
    }

    //Partie.getInstance().getTabjoueur();

    public void repondreAccusation() { //Méthode décrivant une des actions que peut faire le joueur si ce dernier est accusé : Si le joueur est accusé il peut révéler son identité

        if (this.estAccuse==true) {

            if(carteJoueurMain.size()==0){
                this.idEstRevele=true;
            }
            else{
                Scanner inputAction=new Scanner(System.in); //création d'un objet de type scanner pour récupérer les inputs de l'utilisateur
                System.out.println("Quelle action voulez vous faire ? :");
                System.out.println("- R pour réveler votre identité `\n - W pour utiliser l'effet Witch");
                char action=inputAction.nextLine().charAt(0); //Lis la valeur saisie par l'utilisateur

                while (action!='R' && action!='W') {
                    System.out.println("Mauvaise saisie, veuillez saisir R ou W\n");
                    action=inputAction.nextLine().charAt(0);
                }

                if (action=='R'){
                    this.idEstRevele=true;
                }
                else if(action=='W'){
                    CarteRumeur.effetWitch();
                }
            }





        }
    }

    public void commencerTour(){

            if(estAccuse==true){
                repondreAccusation();
            }
            else{

                if (carteJoueurMain.size()==0){
                    this.accuser();
                }
                else{
                    Scanner inputAction=new Scanner(System.in); //création d'un objet de type scanner pour récupérer les inputs de l'utilisateur
                    System.out.println("Quelle action voulez vous faire ? : \n - A pour accuser un joueur \n - H pour utiliser le Hunt effect");
                    char action=inputAction.nextLine().charAt(0);

                    while(action!='A' && action!='H'){ //condition d'erreur
                        System.out.println("Mauvaise saisie : Quelle action voulez vous faire ? : \n - A pour accuser un joueur \n - H pour utiliser le Hunt effect");
                        action=inputAction.nextLine().charAt(0);
                    }

                    if(action=='A'){
                        this.accuser();
                    }
                    else if(action=='H'){
                        CarteRumeur carteRumeur=new CarteRumeur();
                        carteRumeur.effetHunt();
                    }
                }
            }
            this.setTour(false);
        }


    }

    public static void main (String[] args){
            System.out.println("sss");
    }
}
