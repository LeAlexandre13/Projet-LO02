package Modele;
import java.util.*;

public class Joueur {

    protected String nom;
    protected static int nbCarte, refJoueur;
    protected boolean estAccuse, idEstRevele, tour, evilEye, accuse, utiliserEffet, personneUtiliseEvilEye;
    protected ArrayList<CarteRumeur> carteJoueurMain, carteJoueurPlateau;
    private Role identite;
    private int points;
    protected boolean estVirtuel;

    public Joueur() { //constructeur de Joueur
        this.carteJoueurMain = new ArrayList<CarteRumeur>();
        this.carteJoueurPlateau = new ArrayList<CarteRumeur>();
        this.estVirtuel = false;
        this.setPoints(0);
        this.estAccuse = false;
    }

    public Role getIdentite() {
        return identite;
    }

    public void setIdentite(Role identite) {
        this.identite = identite;
    }

    public boolean isIdEstRevele() {
        return idEstRevele;
    }

    public void setIdEstRevele(boolean idEstRevele) {
        this.idEstRevele = idEstRevele;
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
        this.nom = nom;
    }


    public void setPoints(int points) { //Ajout d'un certain nombre de points pour un joueur donn�
        this.points = points;
    }

    public int getPoints() { //getter pour r�cup�rer le nombre de points d'un joueur
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

    public Joueur choisirJoueur() {
        Scanner inputNom = new Scanner(System.in);
        System.out.println("Entrer le nom du joueur que vous voulez choisir ");
        String nomJoueur = inputNom.nextLine();
        while (nomJoueur.equals(this.getNom())){
            System.out.println("Vous pouvez pas choisir vous meme ");
            System.out.println("Entrer le nom du joueur que vous voulez choisir ");
            nomJoueur = inputNom.nextLine();
        }
        Joueur joueurChoisi = Partie.getInstance().chercherJoueur(nomJoueur);

        return joueurChoisi;


    }

    public void choisirRole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Voulez vous joueur �tre 'Witch' ou 'Villager'? Tapper 'W' pour 'Witch' et 'V' pour 'Villager' ");
        char role = scanner.nextLine().charAt(0);
        while (role != 'W' && role != 'V') {
            System.out.println("Mauvaise saisie. Veuillez saisir 'W' ou 'V' :");
            role = scanner.nextLine().charAt(0);
        }
        if (role == 'W') {
            this.setIdentite(Role.Witch);
        } else {
            this.setIdentite(Role.Villager);
        }
    }

    public void ramasserCarte(CarteRumeur carteRumeur) {
        this.carteJoueurMain.add(carteRumeur);
    }


    public boolean revelerId() {
        if (this.getIdentite().equals(Role.Witch)) {
            this.idEstRevele = true;
            System.out.println("Le joueur " + getNom() + " est une WITCH");
            Partie.getInstance().getTabjoueurSup().add(this);
            Partie.getInstance().getTabjoueur().remove(this);
            return true;
        } else {
            this.idEstRevele = true;
            System.out.println("Le joueur " + getNom() + " est un Villageois");
            return false;
        }
    }

    public void choisirCarteRumour() {
        System.out.println("Ce sont vos cartes ");
        for (int i = 0; i < this.carteJoueurMain.size(); i++) {
            System.out.println(this.carteJoueurMain.get(i).getNomCarte());
        }
        Scanner inputCarteR = new Scanner(System.in);
        this.utiliserEffet = false;
        while (this.utiliserEffet == false) {
            this.utiliserEffet = true;
            System.out.println("Quelle carte voulez vous jouer ? : Veuillez saisir le num�ro de la carte");
            int numCarteR = inputCarteR.nextInt();
            while (numCarteR > carteJoueurMain.size() || numCarteR < 1) { 
                System.out.println("Erreur, veuillez ressasir le num�ro de la carte");
                numCarteR = inputCarteR.nextInt();

            }
            this.jouerCarte(carteJoueurMain.get(numCarteR - 1));
        }
    }

    public void jouerCarte(CarteRumeur carteRumeur) {
        this.carteJoueurPlateau.add(carteRumeur);
        this.carteJoueurMain.remove(carteRumeur);
        this.effetCarte(carteRumeur);
        if (this.utiliserEffet == false) {
            this.carteJoueurMain.add(carteRumeur);
            this.carteJoueurPlateau.remove(carteRumeur);
            System.out.println("Vous pouvez pas utiliser cette carte ");

        }
    }

    public void effetCarte(CarteRumeur carteRumeur) {
        if (this.estAccuse == true) {
            System.out.println("Le joueur " + getNom() + " a utilis� l'effet Witch de la carte " + carteRumeur.getNomCarte());
            this.activerEffetCarte(carteRumeur);
            this.estAccuse = false;
        } else {
            System.out.println("Le joueur " + getNom() + " a utilis� l'effet Hunt de la carte " + carteRumeur.getNomCarte());
            this.activerEffetCarte(carteRumeur);
        }

    }

    public void activerEffetCarte(CarteRumeur carteRumeur) {
        if (carteRumeur.getRefCarte() == 0) {
            this.effetAngryMob();
        } else if (carteRumeur.getRefCarte() == 1) {
            this.effetTheInquisition();
        } else if (carteRumeur.getRefCarte() == 2) {
            this.effetPointedHat();
        } else if (carteRumeur.getRefCarte() == 3) {
            this.effetHookedNose();
        } else if (carteRumeur.getRefCarte() == 4) {
            this.effetBroomStick();
        } else if (carteRumeur.getRefCarte() == 5) {
            this.effetWart();
        } else if (carteRumeur.getRefCarte() == 6) {
            this.effetDuckingStool();
        } else if (carteRumeur.getRefCarte() == 7) {
            this.effetCauldron();
        } else if (carteRumeur.getRefCarte() == 8) {
            this.effetEvilEye();
        } else if (carteRumeur.getRefCarte() == 9) {
            this.effetToad();
        } else if (carteRumeur.getRefCarte() == 10) {
            this.effetBlackCat();
        } else if (carteRumeur.getRefCarte() == 11) {
            this.effetPetNewt();
        }

    }

    public void effetAngryMob() {
        if (this.estAccuse) {
            this.setTour(true);
        } else {
            boolean check = false;
            if (this.idEstRevele == false) {
                System.out.println("Vous ne pouvez pas utiliser cette carte ");
                this.utiliserEffet = false;
            } else {
                Joueur joueurChoisi = this.choisirJoueur();
                boolean verifierID;
                while (check == false) {
                    boolean checkCarte = true;
                    for (int i = 0; i < joueurChoisi.getCarteJoueurPlateau().size(); i++) {
                        if (joueurChoisi.getCarteJoueurPlateau().get(i).getNomCarte() == NomCarte.BroomStick) {
                            checkCarte = false;
                        }
                    }
                    if (checkCarte == false) {
                        System.out.println("Mauvaise saisie. Veuillez choisir un autre joueur ");
                        joueurChoisi = this.choisirJoueur();
                    } else if (joueurChoisi.isIdEstRevele()) {
                        check = false;
                        System.out.println("Ce joueur a deja revel� son role ");
                        joueurChoisi = this.choisirJoueur();
                    } else {
                        check = true;
                    }
                }
                verifierID = joueurChoisi.revelerId();
                if (verifierID) {
                    this.setPoints(this.getPoints() + 2);
                    this.setTour(true);

                } else {
                    this.setPoints(this.getPoints() - 2);
                    joueurChoisi.setTour(true);
                }
            }
        }
    }

    public void effetTheInquisition() {
        if (this.estAccuse) {
            System.out.println("Ce sont vos cartes ");
            for (int i = 0; i < this.carteJoueurMain.size(); i++) {
                System.out.println(this.carteJoueurMain.get(i).getNomCarte());
            }
            Scanner inputNumeroCarte = new Scanner(System.in);
            System.out.println("Entrez le num�ro de la carte que vous voulez jeter");
            int numCarte = inputNumeroCarte.nextInt();
            Partie.getInstance().getCarteDiscarte().add(carteJoueurMain.get(numCarte));
            this.carteJoueurMain.remove(carteJoueurMain.get(numCarte));
            this.setTour(true);
        } else {
            if (this.isIdEstRevele() == false) {
                System.out.println("Vous ne pouvez pas utiliser  cette carte ");
                this.utiliserEffet = false;
            }

            Joueur joueurChoisi = this.choisirJoueur();
            joueurChoisi.setTour(true);
            System.out.println("Son ID est " + joueurChoisi.getIdentite());
        }
    }

    public void effetPointedHat() {
        if (this.estAccuse) {
            if (this.carteJoueurPlateau.size() == 0) {
                System.out.println("Vous ne pouvez pas utiliser cette carte ");
                this.utiliserEffet = false;
            } else {
                System.out.println("Ce sont vos carte sur le plateau ");
                for (CarteRumeur joueurPlateau : this.carteJoueurPlateau) {
                    System.out.println(joueurPlateau.getNomCarte());
                }
                Scanner inputNumeroCarte = new Scanner(System.in);
                System.out.println("Entrez le num�ro de la carte que vous voulez r�cup�rer ");
                int numcarte = inputNumeroCarte.nextInt();
                this.getCarteJoueurMain().add(this.getCarteJoueurPlateau().get(numcarte - 1));
                this.getCarteJoueurPlateau().remove(this.getCarteJoueurPlateau().get(numcarte - 1));
                this.setTour(true);
            }
        } else {
            if (this.carteJoueurPlateau.size() == 0) {
                System.out.println("Vous ne pouvez pas utiliser cette carte ");
                this.utiliserEffet = false;
            } else {
                System.out.println("Ce sont vos carte sur le plateau ");
                for (CarteRumeur joueurPlateau : this.carteJoueurPlateau) {
                    System.out.println(joueurPlateau.getNomCarte());
                }
                Scanner inputNumeroCarte = new Scanner(System.in);
                System.out.println("Entrez le numero de la carte que vous voulez r�cup�rer ");
                int numcarte = inputNumeroCarte.nextInt();
                this.getCarteJoueurMain().add(this.getCarteJoueurPlateau().get(numcarte-1));
                this.getCarteJoueurPlateau().remove(this.getCarteJoueurPlateau().get(numcarte-1));
                Joueur joueurChoisi = this.choisirJoueur();
                joueurChoisi.setTour(true);
            }
        }
    }

    public void effetBroomStick() {
        if (this.estAccuse) {
            this.setTour(true);
        } else {
            Joueur joueurChoisi = this.choisirJoueur();
            joueurChoisi.setTour(true);
        }
    }

    public void effetWart() {
        if (this.estAccuse) {
            this.setTour(true);
        } else {
            Joueur joueurChoisi = this.choisirJoueur();
            joueurChoisi.setTour(true);
        }
    }

    public void effetDuckingStool() {
        if (this.estAccuse) {
            boolean check = false;
            Joueur joueurChoisi = this.choisirJoueur();
            while (check == false) {
                boolean checkCarte = true;
                for (int i = 0; i < joueurChoisi.getCarteJoueurPlateau().size(); i++) {
                    if (joueurChoisi.getCarteJoueurPlateau().get(i).getNomCarte() == NomCarte.Wart) {
                        checkCarte = false;
                    }
                }
                if (checkCarte == false) {
                    System.out.println("Mauvaise saisie. Veuillez choisir un autre joueur ");
                    joueurChoisi = this.choisirJoueur();
                } else {
                    check = true;
                }

            }
            joueurChoisi.setTour(true);
        } else {
            boolean check = false;
            Joueur joueurChoisi = this.choisirJoueur();

            while (check == false) {
                boolean checkCarte = true;
                for (int i = 0; i < joueurChoisi.getCarteJoueurPlateau().size(); i++) {
                    if (joueurChoisi.getCarteJoueurPlateau().get(i).getNomCarte() == NomCarte.Wart) {
                        checkCarte = false;
                    }
                }
                if (checkCarte == false) {
                    System.out.println("Mauvais saisir. Veuillez choisir autre joueur ");
                    joueurChoisi = this.choisirJoueur();
                } else {
                    check = true;
                }

            }
            int choice = joueurChoisi.repondreEffetHuntDuckingStool();
            boolean verifierID;
            if (choice == 1) {
                verifierID = joueurChoisi.revelerId();
                if (verifierID) {
                    this.setPoints(this.getPoints() + 1);
                    this.setTour(true);
                } else {
                    joueurChoisi.setTour(true);

                    if (this.getPoints() > 0) {
                        this.setPoints(this.getPoints() - 1);
                    }

                }
            }
        }
    }

    public int repondreEffetHuntDuckingStool() {
        int choice;
        Scanner inputChoice = new Scanner(System.in);
        System.out.println("Vous voulez reveler votre ID ou jeter une carte. Tapper '1' pour reveler, '2' pour jeter une carte ");
        choice = inputChoice.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.println("Mauvais saisir. Veuillez resaisir votre choice ");
            choice = inputChoice.nextInt();
        }
        if (choice == 2) {
            Scanner inputNumeroCarte = new Scanner(System.in);
            System.out.println("Ce sont vos cartes ");
            for (int i = 0; i < this.getCarteJoueurMain().size(); i++) {
                System.out.println(this.getCarteJoueurMain().get(i).getNomCarte());
            }
            System.out.println("Tapper le numero de la carte que vous voulez jeter ");
            int numeroCarte = inputNumeroCarte.nextInt();
            while (numeroCarte > this.getCarteJoueurMain().size() || numeroCarte < 1) {
                System.out.println("Mauvais saisir. Veuillez resaisir le numero de la carte");
                numeroCarte = inputNumeroCarte.nextInt();
            }
            Partie.getInstance().getCarteDiscarte().add(this.carteJoueurMain.get(numeroCarte));
            this.carteJoueurMain.remove(this.carteJoueurMain.get(numeroCarte));
            this.setTour(true);


        }
        return choice;

    }

    public void effetEvilEye() {
        if (this.estAccuse) {
            Joueur joueurChoisi = this.choisirJoueur();
            joueurChoisi.setTour(true);
            joueurChoisi.setEvilEye(true);
            this.personneUtiliseEvilEye = true;
        } else {
            Joueur joueurChoisi = this.choisirJoueur();
            joueurChoisi.setTour(true);
            joueurChoisi.setEvilEye(true);
            this.personneUtiliseEvilEye = true;
        }
    }

    public void effetToad() {
        if (this.estAccuse) {
            this.setTour(true);
        } else {
            boolean verifierID;
            verifierID = this.revelerId();
            if (!verifierID) {
                Joueur joueurChoisi = this.choisirJoueur();
                joueurChoisi.setTour(true);
            } else {
                int indice = Partie.getInstance().getTabjoueur().indexOf(this);
                if (indice == 0) {
                    int size = Partie.getInstance().getTabjoueur().size();
                    Partie.getInstance().getTabjoueur().get(size - 1).setTour(true);
                } else {
                    Partie.getInstance().getTabjoueur().get(indice - 1).setTour(true);

                }
            }
        }
    }

    public void effetBlackCat() {
        if (this.estAccuse) {
            this.setTour(true);
        } else {
            if (Partie.getInstance().getCarteDiscarte().size() == 0) {
                System.out.println("Vous ne pouvez pas utiliser cette carte ");
                this.utiliserEffet = false;
            } else {
                System.out.println("Ce sont des cartes jet�s");
                ArrayList<CarteRumeur> carteDiscarte = Partie.getInstance().getCarteDiscarte();
                for (int i = 0; i < carteDiscarte.size(); i++) {
                    System.out.println(carteDiscarte.get(i).getNomCarte());
                }
                Scanner inputNumeroCarte = new Scanner(System.in);

                System.out.println("Tapper le numero de la carte que vous voulez ajouter a la main ");
                int numeroCarte = inputNumeroCarte.nextInt();
                while (numeroCarte > carteDiscarte.size() || numeroCarte < 1) {
                    System.out.println("Mauvais saisir. Veuillez resaisir le numero de la carte");
                    numeroCarte = inputNumeroCarte.nextInt();
                }
                this.getCarteJoueurMain().add(carteDiscarte.get(numeroCarte));
                carteDiscarte.remove(carteDiscarte.get(numeroCarte));
                for (int i = 0; i < this.getCarteJoueurPlateau().size(); i++) {
                    if (this.getCarteJoueurPlateau().get(i).getNomCarte().equals(NomCarte.BlackCat)) {
                        CarteRumeur carteRumeur = this.getCarteJoueurPlateau().get(i);
                        carteDiscarte.add(carteRumeur);
                        this.getCarteJoueurPlateau().remove(carteRumeur);
                    }
                    break;
                }
            }
        }
    }

    public void effetPetNewt() {
        if (this.estAccuse) {
            this.setTour(true);
        } else {
            boolean check = false;
            boolean checkCartePlateauTousJoueur = false;
            ArrayList<Joueur> tabJoueur = Partie.getInstance().getTabjoueur();
            for (int i = 0; i < tabJoueur.size(); i++) {
                if (tabJoueur.get(i).getCarteJoueurPlateau().size() > 0 && i != tabJoueur.indexOf(this)) {
                    checkCartePlateauTousJoueur = true;
                }
            }
            if (checkCartePlateauTousJoueur) {
                Joueur joueurChoisi = this.choisirJoueur();
                if (joueurChoisi.carteJoueurPlateau.size() == 0) {
                    System.out.println("Vous ne pouvez pas choisir ce joueur car il n'a pas encore r�v�ler de cartes rumeurs ");
                    System.out.println("Veuillez choisir autre joueur ");
                    joueurChoisi = this.choisirJoueur();
                }
                System.out.println(" Veuillez choisir le numero de la carte");
                for (int i = 0; i < joueurChoisi.getCarteJoueurPlateau().size(); i++) {
                    System.out.println(i + 1);
                }
                Scanner inputNumcarte = new Scanner(System.in);
                int numCarte = inputNumcarte.nextInt();
                while (check == false) {//Condition d'erreur
                    if (numCarte > 0 && numCarte <= joueurChoisi.carteJoueurPlateau.size()) {
                        check = true;
                    } else if (numCarte <= 0 && numCarte > joueurChoisi.carteJoueurPlateau.size()) {
                        System.out.println("Mauvais saisir. Veuillez saisir un autre numero de la carte ");
                        numCarte = inputNumcarte.nextInt();

                    }
                }
                this.carteJoueurMain.add(joueurChoisi.getCarteJoueurPlateau().get(numCarte - 1));
                joueurChoisi.getCarteJoueurPlateau().remove(joueurChoisi.getCarteJoueurPlateau().get(numCarte - 1));
                joueurChoisi.setTour(true);
            } else {
                this.utiliserEffet = false;
            }
        }
    }

    public void effetHookedNose() {
        if (this.estAccuse) {
            this.setTour(true);
            int indicePersonneAccuse = 0;
            boolean check = false;
            while (Partie.getInstance().getTabjoueur().get(indicePersonneAccuse).accuse == false) {
                indicePersonneAccuse++;
            }
            Joueur joueurAccuse = Partie.getInstance().getTabjoueur().get(indicePersonneAccuse);
            System.out.println("Veuillez choisir un num�ro de carte ");
            for (int j = 0; j < joueurAccuse.getCarteJoueurMain().size(); j++) {

                System.out.println(j + 1);
            }
            Scanner inputNumcarte = new Scanner(System.in);
            int numCarte = inputNumcarte.nextInt();
            while (check == false) {//Condition d'erreur
                if (numCarte > 0 && numCarte <= joueurAccuse.carteJoueurMain.size()) {
                    check = true;
                } else if (numCarte <= 0 && numCarte > joueurAccuse.carteJoueurMain.size()) {
                    System.out.println("Mauvaise saisie. Veuillez saisir un autre num�ro de carte ");
                    numCarte = inputNumcarte.nextInt();

                }
            }
            this.carteJoueurMain.add(joueurAccuse.getCarteJoueurMain().get(numCarte - 1));//ajouter une carte dans le jeu de la personne accus�e
            joueurAccuse.getCarteJoueurMain().remove(joueurAccuse.getCarteJoueurMain().get(numCarte - 1));// retire la carte du jeu
        } else {
            Joueur joueurChoisi = this.choisirJoueur();
            joueurChoisi.setTour(true);
            int numCarte = (int) (Math.random() * joueurChoisi.getCarteJoueurMain().size());
            this.carteJoueurMain.add(joueurChoisi.getCarteJoueurMain().get(numCarte));
            joueurChoisi.getCarteJoueurMain().remove(joueurChoisi.getCarteJoueurMain().get(numCarte));// retire la carte du jeu de
        }
    }

    public void effetCauldron() {
        if (this.estAccuse) {
            this.setTour(true);
            int indicePersonneAccuse = 0;
            boolean check = false;
            while (Partie.getInstance().getTabjoueur().get(indicePersonneAccuse).accuse == false) {
                indicePersonneAccuse++;
            }
            Joueur joueurAccuse = Partie.getInstance().getTabjoueur().get(indicePersonneAccuse);
            int numCarte = (int) (Math.random() * joueurAccuse.getCarteJoueurMain().size());
            Partie.getInstance().getCarteDiscarte().add(joueurAccuse.getCarteJoueurMain().get(numCarte));
            joueurAccuse.getCarteJoueurMain().remove(joueurAccuse.getCarteJoueurMain().get(numCarte));// retire la carte du jeu de
        } else {
            boolean verifierID;
            verifierID = this.revelerId();
            if (!verifierID) {
                Joueur joueurChoisi = this.choisirJoueur();
                joueurChoisi.setTour(true);
            } else {
                int indice = Partie.getInstance().getTabjoueur().indexOf(this);
                if (indice == 0) {
                    int size = Partie.getInstance().getTabjoueur().size();
                    Partie.getInstance().getTabjoueur().get(size - 1).setTour(true);
                } else {
                    Partie.getInstance().getTabjoueur().get(indice - 1).setTour(true);

                }
            }
        }
    }

    public void changerDeTourPourAutreJoueur() {
        boolean check = false;
        for (int i = 0; i < Partie.getInstance().getTabjoueur().size(); i++) {
            if (Partie.getInstance().getTabjoueur().get(i).isTour()) {
                check = true;
            }
        }
        if (check == false) {
            int indice = Partie.getInstance().getTabjoueur().indexOf(this);
            if (indice == Partie.getInstance().getTabjoueur().size() - 1) {
                Partie.getInstance().getTabjoueur().get(0).setTour(true);
            } else {
                Partie.getInstance().getTabjoueur().get(indice + 1).setTour(true);
            }
        }

    }

    public void commencerTour() {
        if (carteJoueurMain.size() == 0) {
            this.accuser();
        } else if (this.isEvilEye()) {
            System.out.println(" Vous �tes oblig� d'accuser ");
            this.accuser();

            this.setEvilEye(false);
            for (int i = 0; i < Partie.getInstance().getTabjoueur().size(); i++) {
                Partie.getInstance().getTabjoueur().get(i).personneUtiliseEvilEye = false;
            }
        } else {
            Scanner inputAction = new Scanner(System.in); //cr�ation d'un objet de type scanner pour r�cup�rer les inputs de l'utilisateur
            System.out.println("Quelle action voulez vous faire ? : \n - A pour accuser un joueur \n - H pour utiliser le Hunt effect");
            char action = inputAction.nextLine().charAt(0);

            while (action != 'A' && action != 'H') { //condition d'erreur
                System.out.println("Mauvaise saisie : Quelle action voulez vous faire ? : \n - A pour accuser un joueur \n - H pour utiliser le Hunt effect");
                action = inputAction.nextLine().charAt(0);

            }

            if (action == 'A') {
                this.accuser();
            } else  {
                this.choisirCarteRumour();
            }

        }
        this.setTour(false);
        this.changerDeTourPourAutreJoueur();

    }


    public void accuser() { //Pour une ref�rence de joueur donn�, la variable estAccus� de l'objet de type Joueur prend la valeur "true".
        this.setAccuse(true);

        Joueur joueurAccuse = this.choisirJoueur();
        ArrayList<Joueur> tabJoueur = Partie.getInstance().getTabjoueur();
        if (this.isEvilEye()) {
            boolean checkJoueur = false;
            for (int i = 0; i < tabJoueur.size(); i++) {
                if (!tabJoueur.get(i).personneUtiliseEvilEye && !tabJoueur.get(i).isIdEstRevele()) {
                    checkJoueur = true;
                }
            }
            if (checkJoueur) {
                while (joueurAccuse.personneUtiliseEvilEye || joueurAccuse.isIdEstRevele()) {
                    System.out.println("Vous ne pouvez pas accuser cette personne veuillez choisir autre joueur ");
                    joueurAccuse = this.choisirJoueur();
                }
            }

        }
        while (joueurAccuse.isIdEstRevele()) {
            System.out.println("Ce joueur a deja r�v�l� son identit�. Veuillez choisir autre joueur.");
            joueurAccuse = this.choisirJoueur();

        }
        joueurAccuse.setEstAccuse(true);
        while (this.Accuse()) {
            joueurAccuse.repondreAccusation();
            this.setAccuse(false);
        }

    }



    public void repondreAccusation() { //M�thode d�crivant une des actions que peut faire le joueur si ce dernier est accus� : Si le joueur est accus� il peut r�v�ler son identit�
        if (this.estAccuse) {

            System.out.println("Joueur " + getNom());

            if (this.carteJoueurMain.size() == 0) {
                this.revelerId();
            } else {
                Scanner inputAction = new Scanner(System.in); //cr�ation d'un objet de type scanner pour r�cup�rer les inputs de l'utilisateur
                System.out.println("Quelle action voulez vous faire ? :");
                System.out.println("- R pour r�veler votre identit� `\n - W pour utiliser l'effet Witch");
                char action = inputAction.nextLine().charAt(0); //Lis la valeur saisie par l'utilisateur

                while (action != 'R' && action != 'W') { //condition d'erreur
                    System.out.println("Mauvaise saisie, veuillez saisir R ou W\n");
                    action = inputAction.nextLine().charAt(0);
                }

                if (action == 'R') {
                    boolean resultat = this.revelerId();
                    if (resultat) {
                        for (int i = 0; i < Partie.getInstance().getTabjoueur().size(); i++) {
                            if (Partie.getInstance().getTabjoueur().get(i).accuse) {
                                Joueur joueur = Partie.getInstance().getTabjoueur().get(i);
                                joueur.setPoints(joueur.getPoints() + 1);

                            }
                        }
                    }

                } else  {
                    this.choisirCarteRumour();
                }
            }

        }
        this.setEstAccuse(false);
    }




}




