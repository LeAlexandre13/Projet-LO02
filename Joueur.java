import java.util.*;

public class Joueur {

    private String nom;
    protected static int nbCarte, points, refJoueur;
    private boolean  estAccuse, idEstRevele, tour, evilEye, accuse, utiliserEffet, personneUtiliseEvilEye;
    private ArrayList<CarteRumeur> carteJoueurMain, carteJoueurPlateau;
    private Role identite;

    public Joueur(int refJoueur) { //constructeur de Joueur
        this.refJoueur=refJoueur;
        this.carteJoueurMain=new ArrayList<CarteRumeur>();
    }

    public Role getIdentite() {
        return identite;
    }

    public void setIdentite(Role identite) {
        this.identite = identite;
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



    public void setPoints(int points){ //Ajout d'un certain nombre de points pour un joueur donné
        this.points= points;
    }

    public int getPoints() { //getter pour récupérer le nombre de points d'un joueur
        return this.points;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<CarteRumeur> getCarteJoueurPlateau() {
        return carteJoueurPlateau;
    }

    public void setCarteJoueurPlateau(ArrayList<CarteRumeur> carteJoueurPlateau) {
        this.carteJoueurPlateau = carteJoueurPlateau;
    }

    public ArrayList<CarteRumeur> getCarteJoueurMain() {
        return carteJoueurMain;
    }

    public void setCarteJoueurMain(ArrayList<CarteRumeur> carteJoueurMain) {
        this.carteJoueurMain = carteJoueurMain;
    }

    public boolean Accuse() {
        return accuse;
    }

    public void setAccuse(boolean accuse) {
        this.accuse = accuse;
    }

    public boolean isEvilEye() {
        return evilEye;
    }

    public void setEvilEye(boolean evilEye) {
        this.evilEye = evilEye;
    }

    public boolean isIdEstRevele() {
        return idEstRevele;
    }

    public boolean isUtilisereffet() {
        return utiliserEffet;
    }

    public void setUtilisereffet(boolean utilisereffet) {
        this.utiliserEffet = utilisereffet;
    }

    public void setIdEstRevele(boolean idEstRevele) {
        this.idEstRevele = idEstRevele;
    }

    public boolean isPersonneUtiliseEvilEye() {
        return personneUtiliseEvilEye;
    }

    public void setPersonneUtiliseEvilEye(boolean personneUtiliseEvilEye) {
        this.personneUtiliseEvilEye = personneUtiliseEvilEye;
    }

    public int choisirJoueur(){
        Scanner inputNom = new Scanner(System.in);
        System.out.println("Entrer un nom de la joueur  ");
        String nomJoueur = inputNom.nextLine();
        inputNom.close();
        int indiceJoueur = Partie.getInstance().chercherJoueur(nomJoueur);
        return indiceJoueur;
    }

    public void choisirRole(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez vous joueur être 'Witch' ou 'Villager'? Tapper 'W' pour 'Witch' et 'V' pour 'Villager' ");
        char role = scanner.nextLine().charAt(0);
        scanner.close();
        if ( role == 'W'){
            this.setIdentite(Role.Witch);
        }
        else{
            this.setIdentite(Role.Villager);
        }
    }
    public void ramasserCarte(CarteRumeur carteRumeur){
        this.carteJoueurMain.add(carteRumeur);
    }



    public boolean revelerId(){
        if(this.getIdentite().equals(Role.Witch)){
            Partie.getInstance().getTabjoueurSup().add(this);
            Partie.getInstance().getTabjoueur().remove(this);
            return true;
        }
        else{
            this.idEstRevele = true;
            return false;
        }
    }
    public void jouerCarte(CarteRumeur carteRumeur){
        this.carteJoueurMain.remove(carteRumeur);
        this.carteJoueurPlateau.add(carteRumeur);
        if (this.estAccuse == true){
            this.effetWitch(carteRumeur);
            if (this.isUtilisereffet() == false){
                this.carteJoueurMain.add(carteRumeur);
                this.carteJoueurPlateau.remove(carteRumeur);
                this.setUtilisereffet(true);
            }
            else {
                this.setEstAccuse(false);
            }
        }
        else{
            this.efffetHunt(carteRumeur);
            if (this.isUtilisereffet() == false){
                this.carteJoueurMain.add(carteRumeur);
                this.carteJoueurPlateau.remove(carteRumeur);
            }
        }
    }

    public void accuser() { //Pour une reférence de joueur donné, la variable estAccusé de l'objet de type Joueur prend la valeur "true".
        this.setAccuse(true);
        Scanner inputAction=new Scanner(System.in); //création d'un objet de type scanner pour récupérer les inputs de l'utilisateur
        System.out.println("Quel Joueur voulez accuser ? :");
        String accuser=inputAction.nextLine();
        inputAction.close();

        int indice = Partie.getInstance().chercherJoueur(accuser);
        while (Partie.getInstance().getTabjoueur().get(indice).isIdEstRevele() == true){
            System.out.println("Cette personne est deja releve ID. Veuillez choisir autre joueur ");
            indice = this.choisirJoueur();

        }
        Partie.getInstance().getTabjoueur().get(indice).setEstAccuse(true);
        while (this.Accuse()){
            Partie.getInstance().getTabjoueur().get(indice).repondreAccusation();
            this.setAccuse(false);
        }

    }
    public void effetWitch(CarteRumeur carteRumeur){
        if ( carteRumeur.getNomCarte().equals(nomCarte.AngryMob)){
            this.setTour(true);

        }
        if (carteRumeur.getNomCarte().equals(nomCarte.TheInquisition)){
            System.out.println("Ce sont vos cartes ");
            for( int i = 0; i< this.carteJoueurMain.size(); i++){
                System.out.println(this.carteJoueurMain.get(i).getNomCarte());
            }
            Scanner inputNumeroCarte = new Scanner(System.in);
            System.out.println("Entrer le numero de la carte que vous voulez jeter");
            int numCarte = inputNumeroCarte.nextInt();
            inputNumeroCarte.close();
            Partie.getInstance().getCarteDiscarte().add(carteJoueurMain.get(numCarte));
            this.carteJoueurMain.remove(carteJoueurMain.get(numCarte));
            this.setTour(true);


        }
        if (carteRumeur.getNomCarte().equals(nomCarte.PointedHat)){
            if (this.carteJoueurPlateau.size() == 0){
                System.out.println("Vous ne pouvez pas utiliser cette carte ");
                this.setUtilisereffet(false);
            }
            else{
                System.out.println("Ce sont vos carte sur plateau ");
                for(int i = 0; i< this.carteJoueurPlateau.size(); i++){
                    System.out.println(this.carteJoueurPlateau.get(i).getNomCarte());
                }
                Scanner inputNumeroCarte = new Scanner(System.in);
                System.out.println("Entrer le numero de la carte que vous voulez recuperer ");
                int numcarte = inputNumeroCarte.nextInt();
                inputNumeroCarte.close();
                this.getCarteJoueurMain().add(this.getCarteJoueurPlateau().get(numcarte));
                this.getCarteJoueurPlateau().remove(this.getCarteJoueurPlateau().get(numcarte));
                this.setTour(true);
            }
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.BroomStick)){
                this.setTour(true);
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.Wart)){
                this.setTour(true);
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.DuckingStool)) {
            boolean check = false;
            int indiceJoueur = this.choisirJoueur();

            while(check == false){
                boolean checkCarte = true;
                for (int i =0; i < Partie.getInstance().getTabjoueur().get(indiceJoueur).getCarteJoueurPlateau().size(); i++ ){
                    if(Partie.getInstance().getTabjoueur().get(indiceJoueur).getCarteJoueurPlateau().get(i).getNomCarte() == nomCarte.Wart){
                        checkCarte = false;
                    }
                }
                if (checkCarte == false){
                    System.out.println("Mauvais saisir. Veuillez choisir autre joueur ");
                    indiceJoueur = this.choisirJoueur();
                }
                else{
                    check = true;
                }

            }
            Partie.getInstance().getTabjoueur().get(indiceJoueur).setTour(true);

        }
        if (carteRumeur.getNomCarte().equals(nomCarte.EvilEye)){
            int indiceJoueur  = this.choisirJoueur();
            Partie.getInstance().getTabjoueur().get(indiceJoueur).setTour(true);
            Partie.getInstance().getTabjoueur().get(indiceJoueur).setEvilEye(true);
            this.setPersonneUtiliseEvilEye(true);
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.Toad)){
            this.setTour(true);
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.BlackCat)){
            this.setTour(true);
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.PetNewt)){
            this.setTour(true);
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.HookedNose)){//Permet de trouver le joueur qui accuse
            this.setTour(true);
            int indicePersonneAccuse = 0;
            boolean check = false;
            while (Partie.getInstance().getTabjoueur().get(indicePersonneAccuse).accuse == false){
                indicePersonneAccuse++;
            }
            System.out.println("Veuillez choisir numero de la carte ");
            for (int j = 0; j < Partie.getInstance().getTabjoueur().get(indicePersonneAccuse).getCarteJoueurMain().size(); j++){

                System.out.println(j+1);
            }
            Scanner inputNumcarte = new Scanner(System.in);
            int numCarte = inputNumcarte.nextInt();
            while (check == false) {//Condition d'erreur
                if (numCarte >= 0 && numCarte <= this.carteJoueurMain.size()) {
                    check = true;
                } else if (numCarte < 0 && numCarte > this.carteJoueurMain.size()) {
                    System.out.println("Mauvais saisir. Veuillez saisir un autre numero de la carte ");
                    numCarte = inputNumcarte.nextInt();

                }
            }
            inputNumcarte.close();
            this.carteJoueurMain.add(Partie.getInstance().getTabjoueur().get(indicePersonneAccuse).getCarteJoueurMain().get(numCarte-1));//ajouter une carte dans le jeu de la personne accusée
            Partie.getInstance().getTabjoueur().get(indicePersonneAccuse).getCarteJoueurMain().remove(Partie.getInstance().getTabjoueur().get(indicePersonneAccuse).getCarteJoueurMain().get(numCarte-1));// retire la carte du jeu de la personne qui accuse

        }
        if ( carteRumeur.getNomCarte().equals(nomCarte.Cauldron)){
            this.setTour(true);
            int indicePersonneAccuse = 0;
            boolean check = false;
            while (Partie.getInstance().getTabjoueur().get(indicePersonneAccuse).accuse == false){
                indicePersonneAccuse++;
            }
            int numCarte =(int) (Math.random()* Partie.getInstance().getTabjoueur().get(indicePersonneAccuse).getCarteJoueurMain().size());
            Partie.getInstance().getCarteDiscarte().add(Partie.getInstance().getTabjoueur().get(indicePersonneAccuse).getCarteJoueurMain().get(numCarte));
            Partie.getInstance().getTabjoueur().get(indicePersonneAccuse).getCarteJoueurMain().remove(Partie.getInstance().getTabjoueur().get(indicePersonneAccuse).getCarteJoueurMain().get(numCarte));// retire la carte du jeu de la personne qui accuse

        }

    }
    public int repondreDuckingStool(){
        int choice;
        Scanner inputChoice = new Scanner(System.in);
        System.out.println("Vous voulez reveler votre ID ou jeter une carte. Tapper '1' pour reveler, '2' pour jeter une carte ");
        choice = inputChoice.nextInt();
        while (choice != 1 && choice !=2){
            System.out.println("Mauvais saisir. Veuillez resaisir votre choice ");
            choice = inputChoice.nextInt();
        }
        if (choice == 2){
            Scanner inputNumeroCarte = new Scanner(System.in);
            System.out.println("Ce sont vos cartes ");
            for (int i=0; i < this.getCarteJoueurMain().size(); i++){
                System.out.println(this.getCarteJoueurMain().get(i).getNomCarte());
            }
            System.out.println("Tapper le numero de la carte que vous voulez jeter ");
            int numeroCarte = inputNumeroCarte.nextInt();
            while (numeroCarte > this.getCarteJoueurMain().size() || numeroCarte < 1){
                System.out.println("Mauvais saisir. Veuillez resaisir le numero de la carte");
                numeroCarte = inputNumeroCarte.nextInt();
            }
            inputNumeroCarte.close();
            Partie.getInstance().getCarteDiscarte().add(this.carteJoueurMain.get(numeroCarte));
            this.carteJoueurMain.remove(this.carteJoueurMain.get(numeroCarte));
            this.setTour(true);


        }
        return choice;


    }
    public void efffetHunt( CarteRumeur carteRumeur){
        if (carteRumeur.getNomCarte().equals(nomCarte.AngryMob)){
            boolean check = false;
            if (this.idEstRevele == false){
                System.out.println("Vous ne pouvez pas utiliser cette carte ");
                this.setUtilisereffet(false);
            }
            else{
                int indiceJoueur = this.choisirJoueur();
                boolean verifierID;
                while(check == false){
                    boolean checkCarte = true;
                    for (int i =0; i < Partie.getInstance().getTabjoueur().get(indiceJoueur).getCarteJoueurPlateau().size(); i++ ){
                        if(Partie.getInstance().getTabjoueur().get(indiceJoueur).getCarteJoueurPlateau().get(i).getNomCarte() == nomCarte.BroomStick){
                            checkCarte = false;
                        }
                    }
                    if (checkCarte == false){
                        System.out.println("Mauvais saisir. Veuillez choisir autre joueur ");
                        indiceJoueur = this.choisirJoueur();
                    }
                    else{
                        check = true;
                    }

                }
                verifierID = Partie.getInstance().getTabjoueur().get(indiceJoueur).revelerId();
                if (verifierID){
                    this.setPoints(this.getPoints()+2);
                    this.setTour(true);

                }
                else{
                    this.setPoints(this.getPoints()-2);
                    Partie.getInstance().getTabjoueur().get(indiceJoueur).setTour(true);
                }




            }
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.TheInquisition)){
            if (this.isIdEstRevele() == false){
                System.out.println("Vous ne pouvez pas utiliser  cette carte ");
                this.setUtilisereffet(false);
            }
            int indiceJoueur = this.choisirJoueur();
            Partie.getInstance().getTabjoueur().get(indiceJoueur).setTour(true);
            System.out.println("Son ID est "+ Partie.getInstance().getTabjoueur().get(indiceJoueur).getIdentite() );

        }
        if (carteRumeur.getNomCarte().equals(nomCarte.PointedHat)){
            if (this.carteJoueurPlateau.size() == 0){
                System.out.println("Vous ne pouvez pas utiliser cette carte ");
                this.setUtilisereffet(false);
            }
            else{
                System.out.println("Ce sont vos carte sur plateau ");
                for(int i = 0; i< this.carteJoueurPlateau.size(); i++){
                    System.out.println(this.carteJoueurPlateau.get(i).getNomCarte());
                }
                Scanner inputNumeroCarte = new Scanner(System.in);
                System.out.println("Entrer le numero de la carte que vous voulez recuperer ");
                int numcarte = inputNumeroCarte.nextInt();
                inputNumeroCarte.close();
                this.getCarteJoueurMain().add(this.getCarteJoueurPlateau().get(numcarte));
                this.getCarteJoueurPlateau().remove(this.getCarteJoueurPlateau().get(numcarte));
                int indiceJoueur = this.choisirJoueur();
                Partie.getInstance().getTabjoueur().get(indiceJoueur).setTour(true);


            }
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.Wart)){
            int indiceJoueur = this.choisirJoueur();
            Partie.getInstance().getTabjoueur().get(indiceJoueur).setTour(true);
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.BroomStick)){
            int indiceJoueur = this.choisirJoueur();
            Partie.getInstance().getTabjoueur().get(indiceJoueur).setTour(true);
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.HookedNose)){
            int indiceJoueur = this.choisirJoueur();
            Partie.getInstance().getTabjoueur().get(indiceJoueur).setTour(true);
            int numCarte =(int) (Math.random()* Partie.getInstance().getTabjoueur().get(indiceJoueur).getCarteJoueurMain().size());
            this.carteJoueurMain.add(Partie.getInstance().getTabjoueur().get(indiceJoueur).getCarteJoueurMain().get(numCarte));
            Partie.getInstance().getTabjoueur().get(indiceJoueur).getCarteJoueurMain().remove(Partie.getInstance().getTabjoueur().get(indiceJoueur).getCarteJoueurMain().get(numCarte));// retire la carte du jeu de la personne qui accuse

        }
        if (carteRumeur.getNomCarte().equals(nomCarte.EvilEye)){
            int indiceJoueur  = this.choisirJoueur();
            Partie.getInstance().getTabjoueur().get(indiceJoueur).setTour(true);
            Partie.getInstance().getTabjoueur().get(indiceJoueur).setEvilEye(true);
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.PetNewt)){
            boolean check = false;
            int indiceJoueur = this.choisirJoueur();
            System.out.println(" Veuillez choisir la numero de la carte");
            for (int i=0; i < Partie.getInstance().getTabjoueur().get(indiceJoueur).getCarteJoueurPlateau().size();i++){
                System.out.println(i+1);
            }
            Scanner inputNumcarte = new Scanner(System.in);
            int numCarte = inputNumcarte.nextInt();
            while (check == false) {//Condition d'erreur
                if (numCarte >= 0 && numCarte <= this.carteJoueurMain.size()) {
                    check = true;
                } else if (numCarte < 0 && numCarte > this.carteJoueurMain.size()) {
                    System.out.println("Mauvais saisir. Veuillez saisir un autre numero de la carte ");
                    numCarte = inputNumcarte.nextInt();

                }
            }
            inputNumcarte.close();
            this.carteJoueurMain.add(Partie.getInstance().getTabjoueur().get(indiceJoueur).getCarteJoueurPlateau().get(numCarte-1));
            Partie.getInstance().getTabjoueur().get(indiceJoueur).getCarteJoueurPlateau().remove(Partie.getInstance().getTabjoueur().get(indiceJoueur).getCarteJoueurPlateau().get(numCarte-1));
            Partie.getInstance().getTabjoueur().get(indiceJoueur).setTour(true);

        }
        if (carteRumeur.getNomCarte().equals(nomCarte.DuckingStool)){
            boolean check = false;
            int indiceJoueur = this.choisirJoueur();

            while(check == false){
                boolean checkCarte = true;
                for (int i =0; i < Partie.getInstance().getTabjoueur().get(indiceJoueur).getCarteJoueurPlateau().size(); i++ ){
                    if(Partie.getInstance().getTabjoueur().get(indiceJoueur).getCarteJoueurPlateau().get(i).getNomCarte() == nomCarte.Wart){
                        checkCarte = false;
                    }
                }
                if (checkCarte == false){
                    System.out.println("Mauvais saisir. Veuillez choisir autre joueur ");
                    indiceJoueur = this.choisirJoueur();
                }
                else{
                    check = true;
                }

            }
            int choice = Partie.getInstance().getTabjoueur().get(indiceJoueur).repondreDuckingStool();
            boolean verifierID;
            if (choice == 1){
                verifierID = Partie.getInstance().getTabjoueur().get(indiceJoueur).revelerId();
                if (verifierID){
                    this.setPoints(this.getPoints()+1);
                    this.setTour(true);
                }
                else{
                    Partie.getInstance().getTabjoueur().get(indiceJoueur).setTour(true);
                    this.setPoints(this.getPoints()-1);
                }
            }
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.Cauldron)){
            boolean verifierID;
            verifierID = this.revelerId();
            if (verifierID){
                int indiceJoueur = this.choisirJoueur();
                Partie.getInstance().getTabjoueur().get(indiceJoueur).setTour(true);
            }
            else{
                int indice = Partie.getInstance().chercherJoueur(this.getNom());
                if (indice ==0){
                    int size = Partie.getInstance().getTabjoueur().size();
                    Partie.getInstance().getTabjoueur().get(size-1).setTour(true);
                }
                else{
                    Partie.getInstance().getTabjoueur().get(indice-1).setTour(true);

                }
            }
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.Toad)){
            boolean verifierID;
            verifierID = this.revelerId();
            if (verifierID){
                int indiceJoueur = this.choisirJoueur();
                Partie.getInstance().getTabjoueur().get(indiceJoueur).setTour(true);
            }
            else{
                int indice = Partie.getInstance().chercherJoueur(this.getNom());
                if (indice ==0){
                    int size = Partie.getInstance().getTabjoueur().size();
                    Partie.getInstance().getTabjoueur().get(size-1).setTour(true);
                }
                else{
                    Partie.getInstance().getTabjoueur().get(indice-1).setTour(true);

                }
            }
        }
        if (carteRumeur.getNomCarte().equals(nomCarte.BlackCat)){
            if (Partie.getInstance().getCarteDiscarte().size() == 0){
                System.out.println("Vous ne pouvez pas utiliser cette carte ");
                this.setUtilisereffet(false);
            }
            else{
                System.out.println("Ce sont des cartes jetés");
                for (int i=0; i <Partie.getInstance().getCarteDiscarte().size(); i++){
                    System.out.println(Partie.getInstance().getCarteDiscarte().get(i).getNomCarte());
                }
                Scanner inputNumeroCarte = new Scanner(System.in);

                System.out.println("Tapper le numero de la carte que vous voulez ajouter a la main ");
                int numeroCarte = inputNumeroCarte.nextInt();
                while (numeroCarte > Partie.getInstance().getCarteDiscarte().size() || numeroCarte < 1){
                    System.out.println("Mauvais saisir. Veuillez resaisir le numero de la carte");
                    numeroCarte = inputNumeroCarte.nextInt();
                }
                inputNumeroCarte.close();
                this.getCarteJoueurMain().add(Partie.getInstance().getCarteDiscarte().get(numeroCarte));
                Partie.getInstance().getCarteDiscarte().remove(Partie.getInstance().getCarteDiscarte().get(numeroCarte));





            }
        }



    }


    public void repondreAccusation() { //Méthode décrivant une des actions que peut faire le joueur si ce dernier est accusé : Si le joueur est accusé il peut révéler son identité

        if (this.estAccuse==true) {

            if(carteJoueurMain.size()==0){
                this.revelerId();

            }
            else{
                Scanner inputAction=new Scanner(System.in); //création d'un objet de type scanner pour récupérer les inputs de l'utilisateur
                System.out.println("Quelle action voulez vous faire ? :");
                System.out.println("- R pour réveler votre identité `\n - W pour utiliser l'effet Witch");
                inputAction.close();
                char action=inputAction.nextLine().charAt(0); //Lis la valeur saisie par l'utilisateur

                while (action!='R' && action!='W') {
                    System.out.println("Mauvaise saisie, veuillez saisir R ou W\n");
                    action=inputAction.nextLine().charAt(0);
                    inputAction.close();
                }

                if (action=='R'){
                    this.revelerId();
                }
                else if(action=='W'){
                    Scanner inputNumeroCarte = new Scanner(System.in);
                    System.out.println("Ce sont vos cartes ");
                    for (int i=0; i < this.getCarteJoueurMain().size(); i++){
                        System.out.println(this.getCarteJoueurMain().get(i).getNomCarte());
                    }
                    System.out.println("Tapper le numero de la carte que vous voulez utiliser ");
                    int numeroCarte = inputNumeroCarte.nextInt();
                    while (numeroCarte > this.getCarteJoueurMain().size() || numeroCarte < 1){
                        System.out.println("Mauvais saisir. Veuillez resaisir le numero de la carte");
                        numeroCarte = inputNumeroCarte.nextInt();
                    }
                    inputNumeroCarte.close();
                    this.jouerCarte(this.getCarteJoueurMain().get(numeroCarte-1));
                }


                }
        }
        this.setEstAccuse(false);





    }


    public void commencerTour(){
        if (carteJoueurMain.size()==0){
            this.accuser();
        }
        else if (this.isEvilEye() == true){
                System.out.println(" Vous êtes obligé d'accuser ");
                int indiceJoueur = this.choisirJoueur();
                if (Partie.getInstance().getTabjoueur().size() >= 3) {
                    while (Partie.getInstance().getTabjoueur().get(indiceJoueur).isPersonneUtiliseEvilEye() == true) {
                        System.out.println("Mauvais saisir. Vous ne pouvez pas accuser cette personne");
                        indiceJoueur = this.choisirJoueur();
                    }
                    Partie.getInstance().getTabjoueur().get(indiceJoueur).setEstAccuse(true);
                    Partie.getInstance().getTabjoueur().get(indiceJoueur).repondreAccusation();
                }
                else{
                    Partie.getInstance().getTabjoueur().get(indiceJoueur).setEstAccuse(true);

                    Partie.getInstance().getTabjoueur().get(indiceJoueur).repondreAccusation();

                }
                this.setEvilEye(false);
                for (int i =1; i < Partie.getInstance().getTabjoueur().size(); i++){
                    Partie.getInstance().getTabjoueur().get(i).setPersonneUtiliseEvilEye(false);
                }





        }
        else{
                Scanner inputAction=new Scanner(System.in); //création d'un objet de type scanner pour récupérer les inputs de l'utilisateur
                System.out.println("Quelle action voulez vous faire ? : \n - A pour accuser un joueur \n - H pour utiliser le Hunt effect");
                char action=inputAction.nextLine().charAt(0);
                inputAction.close();

                while(action!='A' && action!='H'){ //condition d'erreur
                    System.out.println("Mauvaise saisie : Quelle action voulez vous faire ? : \n - A pour accuser un joueur \n - H pour utiliser le Hunt effect");
                    action=inputAction.nextLine().charAt(0);

                }

                if(action=='A'){
                    this.accuser();
                }
                else if(action=='H'){
                    Scanner inputNumeroCarte = new Scanner(System.in);
                    System.out.println("Ce sont vos cartes ");
                    for (int i=0; i < this.getCarteJoueurMain().size(); i++){
                        System.out.println(this.getCarteJoueurMain().get(i).getNomCarte());
                    }
                    System.out.println("Tapper le numero de la carte que vous voulez utiliser ");
                    int numeroCarte = inputNumeroCarte.nextInt();
                    while (numeroCarte > this.getCarteJoueurMain().size() || numeroCarte < 1){
                        System.out.println("Mauvais saisir. Veuillez resaisir le numero de la carte");
                        numeroCarte = inputNumeroCarte.nextInt();
                    }
                    inputNumeroCarte.close();
                    this.jouerCarte(this.getCarteJoueurMain().get(numeroCarte-1));


                }
            }

    }






}
