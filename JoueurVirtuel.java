package Modele;

import java.util.ArrayList;
import java.util.Scanner;

public class JoueurVirtuel extends Joueur implements Strategie  {


    public JoueurVirtuel(){ //constructeur de JoueurVirtuel
        this.estVirtuel=true;
    }

    @Override
    public Joueur choisirJoueur() {
        int numJoueur = (int) (Math.random() * Partie.getInstance().getTabjoueur().size()); //choisis un numéro de joueur au hasard parmis tous les joueurs de la partie en cours
        while (numJoueur == Partie.getInstance().getTabjoueur().indexOf(this)){
            numJoueur = (int) (Math.random() * Partie.getInstance().getTabjoueur().size());
        }
        Joueur joueurChoisi = Partie.getInstance().getTabjoueur().get(numJoueur);
        System.out.println("Joueur "+this.getNom()+" a choisi le joueur "+ joueurChoisi.getNom());
        return joueurChoisi;
    }

    @Override
    public void choisirRole() {
        int numRole = (int) (Math.random()*2);
        if (numRole == 0){
            this.setIdentite(Role.Witch);
        }
        else{
            this.setIdentite(Role.Villager);
        }
    }


    @Override
    public void choisirCarteRumour() {
        int numCarteRumour = (int) (Math.random()*this.carteJoueurMain.size());
        while (this.utiliserEffet == false) {
            this.utiliserEffet = true;
            System.out.println("Joueur "+this.getNom()+" a joué la carte rumour "+carteJoueurMain.get(numCarteRumour).getNomCarte());
            this.jouerCarte(carteJoueurMain.get(numCarteRumour));
            if (!this.utiliserEffet){
                numCarteRumour = (int) (Math.random()*this.carteJoueurMain.size());
                System.out.println("Joueur "+this.getNom()+" a joué la carte rumour "+carteJoueurMain.get(numCarteRumour).getNomCarte());
                this.jouerCarte(carteJoueurMain.get(numCarteRumour));
            }
        }
    }

    @Override
    public void jouerCarte(CarteRumeur carteRumeur) {
        this.carteJoueurPlateau.add(carteRumeur);
        this.carteJoueurMain.remove(carteRumeur);
        this.effetCarte(carteRumeur);
        if (!this.utiliserEffet){
            this.carteJoueurMain.add(carteRumeur);
            this.carteJoueurPlateau.remove(carteRumeur);
        }
    }

    @Override
    public void effetAngryMob() {
        if (this.estAccuse ){
            this.setTour(true);
        }
        else {
            boolean check = false;
            if (this.idEstRevele == false){
                this.utiliserEffet = false;
            }
            else{
                Joueur joueurChoisi = this.choisirJoueur();

                boolean verifierID;
                while(check == false){
                    boolean checkCarte = true;
                    for (int i =0; i < joueurChoisi.getCarteJoueurPlateau().size(); i++ ){
                        if(joueurChoisi.getCarteJoueurPlateau().get(i).getNomCarte() == NomCarte.BroomStick){
                            checkCarte = false;
                        }
                    }
                    if (checkCarte == false){
                        joueurChoisi = this.choisirJoueur();
                    }
                    else if (joueurChoisi.isIdEstRevele()){
                        check = false;
                        joueurChoisi = this.choisirJoueur();
                    }
                    else{
                        check = true;
                    }
                }
                verifierID = joueurChoisi.revelerId();
                if (verifierID){
                    this.setPoints(this.getPoints()+2);
                    this.setTour(true);

                }
                else{
                    this.setPoints(this.getPoints()-2);
                    joueurChoisi.setTour(true);
                }
            }
        }
    }

    @Override
    public void effetTheInquisition() {
        if (this.estAccuse ){
            int numCarteRumour = (int) (Math.random()*this.carteJoueurMain.size());
            Partie.getInstance().getCarteDiscarte().add(carteJoueurMain.get(numCarteRumour));
            this.carteJoueurMain.remove(carteJoueurMain.get(numCarteRumour));
            this.setTour(true);
        }
        else {
            if (this.isIdEstRevele() == false){
                this.utiliserEffet = false;
            }

            Joueur joueurChoisi = this.choisirJoueur();
            joueurChoisi.setTour(true);
            System.out.println("Son ID est "+ joueurChoisi.getIdentite() );
        }
    }

    @Override
    public void effetPointedHat() {
        if (this.estAccuse ){
            if (this.carteJoueurPlateau.size() == 0){
                this.utiliserEffet = false;
            }
            else{
                int numCarteRumour = (int) (Math.random()*this.carteJoueurPlateau.size());
                this.getCarteJoueurMain().add(this.getCarteJoueurPlateau().get(numCarteRumour));
                this.getCarteJoueurPlateau().remove(this.getCarteJoueurPlateau().get(numCarteRumour));
                this.setTour(true);
            }
        }
        else {
            if (this.carteJoueurPlateau.size() == 0){
                this.utiliserEffet = false;
            }
            else{
                int numCarteRumour = (int) (Math.random()*this.carteJoueurPlateau.size());
                this.getCarteJoueurMain().add(this.getCarteJoueurPlateau().get(numCarteRumour));
                this.getCarteJoueurPlateau().remove(this.getCarteJoueurPlateau().get(numCarteRumour));
                Joueur joueurChoisi = this.choisirJoueur();
                joueurChoisi.setTour(true);
            }
        }
    }

    @Override
    public void effetDuckingStool() {
        if (this.estAccuse ){
            boolean check = false;
            Joueur joueurChoisi = this.choisirJoueur();
            while(check == false){
                boolean checkCarte = true;
                for (int i =0; i < joueurChoisi.getCarteJoueurPlateau().size(); i++ ){
                    if(joueurChoisi.getCarteJoueurPlateau().get(i).getNomCarte() == NomCarte.Wart){
                        checkCarte = false;
                    }
                }
                if (checkCarte == false){
                    joueurChoisi = this.choisirJoueur();
                }
                else{
                    check = true;
                }

            }
            joueurChoisi.setTour(true);
        }
        else {
            boolean check = false;
            Joueur joueurChoisi = this.choisirJoueur();

            while(check == false){
                boolean checkCarte = true;
                for (int i =0; i < joueurChoisi.getCarteJoueurPlateau().size(); i++ ){
                    if(joueurChoisi.getCarteJoueurPlateau().get(i).getNomCarte() == NomCarte.Wart){
                        checkCarte = false;
                    }
                }
                if (checkCarte == false){
                    joueurChoisi = this.choisirJoueur();
                }
                else{
                    check = true;
                }

            }
            int choice = joueurChoisi.repondreEffetHuntDuckingStool();
            boolean verifierID;
            if (choice == 1){
                verifierID = joueurChoisi.revelerId();
                if (verifierID){
                    this.setPoints(this.getPoints()+1);
                    this.setTour(true);
                }
                else{
                    joueurChoisi.setTour(true);

                    if(this.getPoints()>0){
                        this.setPoints(this.getPoints()-1);
                    }

                }
            }
        }
    }

    @Override
    public int repondreEffetHuntDuckingStool() {
        int choice = (int)(Math.random()*2+1);
        Scanner inputChoice = new Scanner(System.in);
        if (choice == 2){
            int numCarteRumour = (int)(Math.random()*this.carteJoueurMain.size());


            Partie.getInstance().getCarteDiscarte().add(this.carteJoueurMain.get(numCarteRumour));
            this.carteJoueurMain.remove(this.carteJoueurMain.get(numCarteRumour));
            this.setTour(true);


        }
        return choice;

    }

    @Override
    public void effetBlackCat() {
        if (this.estAccuse ){
            this.setTour(true);
        }
        else {
            if (Partie.getInstance().getCarteDiscarte().size() == 0){
                this.utiliserEffet = false;
            }
            else{
                int numCarteRumour = (int) (Math.random()*Partie.getInstance().getCarteDiscarte().size());
                ArrayList<CarteRumeur> carteDiscarte = Partie.getInstance().getCarteDiscarte();
                this.getCarteJoueurMain().add(carteDiscarte.get(numCarteRumour));
                carteDiscarte.remove(carteDiscarte.get(numCarteRumour));
                for (int i =0; i < this.getCarteJoueurPlateau().size(); i++){
                    if (this.getCarteJoueurPlateau().get(i).getNomCarte().equals(NomCarte.BlackCat)){
                        CarteRumeur carteRumeur = this.getCarteJoueurPlateau().get(i);
                        carteDiscarte.add(carteRumeur);
                        this.getCarteJoueurPlateau().remove(carteRumeur);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void effetPetNewt() {
        if (this.estAccuse ){
            this.setTour(true);
        }
        else {
            boolean checkCartePlateauTousJoueur = false;
            ArrayList<Joueur> tabJoueur = Partie.getInstance().getTabjoueur();
            for (int i =0; i< tabJoueur.size(); i++){
                if (tabJoueur.get(i).getCarteJoueurPlateau().size() >0 && i != tabJoueur.indexOf(this) ){
                    checkCartePlateauTousJoueur = true;
                }
            }
            if (checkCartePlateauTousJoueur){
                Joueur joueurChoisi = this.choisirJoueur();
                while (joueurChoisi.getCarteJoueurPlateau().size() == 0){
                    joueurChoisi = this.choisirJoueur();
                }
                int numCarteRumour = (int) (Math.random()*joueurChoisi.carteJoueurPlateau.size());

                this.carteJoueurMain.add(joueurChoisi.getCarteJoueurPlateau().get(numCarteRumour));
                joueurChoisi.getCarteJoueurPlateau().remove(joueurChoisi.getCarteJoueurPlateau().get(numCarteRumour));
                joueurChoisi.setTour(true);
            }
            else{
                this.utiliserEffet = false;
            }

        }
    }

    @Override
    public void effetHookedNose() {
        if (this.estAccuse ){
            this.setTour(true);
            int indicePersonneAccuse = 0;
            boolean check = false;
            while (Partie.getInstance().getTabjoueur().get(indicePersonneAccuse).accuse == false){
                indicePersonneAccuse++;
            }
            Joueur joueurAccuse = Partie.getInstance().getTabjoueur().get(indicePersonneAccuse);
            int numCarteRumour = (int) (Math.random()*joueurAccuse.carteJoueurMain.size());

            this.carteJoueurMain.add(joueurAccuse.getCarteJoueurMain().get(numCarteRumour));//ajouter une carte dans le jeu de la personne accusée
            joueurAccuse.getCarteJoueurMain().remove(joueurAccuse.getCarteJoueurMain().get(numCarteRumour));// retire la carte du jeu
        }
        else {
            Joueur joueurChoisi = this.choisirJoueur();
            joueurChoisi.setTour(true);
            int numCarte =(int) (Math.random()* joueurChoisi.getCarteJoueurMain().size());
            this.carteJoueurMain.add(joueurChoisi.getCarteJoueurMain().get(numCarte));
            joueurChoisi.getCarteJoueurMain().remove(joueurChoisi.getCarteJoueurMain().get(numCarte));// retire la carte du jeu de
        }
    }



    @Override
    public void accuser() {
        this.setAccuse(true);
        System.out.println("Joueur " + this.getNom() + " veut accuser");
        Joueur joueurAccuse = this.choisirJoueur();
        
        ArrayList<Joueur> tabJoueur = Partie.getInstance().getTabjoueur();
        if (this.isEvilEye()){
            boolean checkJoueur = false;
            for (int i =0; i < tabJoueur.size(); i++){
                if (!tabJoueur.get(i).personneUtiliseEvilEye && !tabJoueur.get(i).isIdEstRevele()){
                    checkJoueur = true;
                }
            }
            if (checkJoueur){
                while (joueurAccuse.personneUtiliseEvilEye || joueurAccuse.isIdEstRevele()){
                    joueurAccuse = this.choisirJoueur();
                }
            }

        }
        while (joueurAccuse.isIdEstRevele() ) {
            joueurAccuse = this.choisirJoueur();

        }
        joueurAccuse.setEstAccuse(true);
        while (this.Accuse()) {
            joueurAccuse.repondreAccusation();
            this.setAccuse(false);
        }


    }

    @Override
    public void repondreAccusation() {
        if (this.estAccuse) {
            System.out.println("Joueur " + getNom());
            if (this.carteJoueurMain.size() == 0) {
                this.revelerId();
            } else {
                int choixStrat = (int)(Math.random()*2+1);

                if (choixStrat == 1) {
                    this.aggressif();

                } else if (choixStrat == 2) {
                    this.strategique();
                }
            }

        }
        this.setEstAccuse(false);
    }

    @Override
    public void commencerTour() {
        if (carteJoueurMain.size() == 0) {
            this.aggressif();
        } else if (this.isEvilEye() == true) {
            System.out.println("Le joueur virtuel " +this.getNom()+" accuse un autre joueur.");
            this.aggressif();
        }
        else{
            int choixStrat = (int)(Math.random()*2+1);
            if(choixStrat==1){
                this.aggressif();
            }
            else{
                this.strategique();
            }
        }
    }
    public void aggressif(){
        if (!this.estAccuse){
            this.accuser();
        }
        else{
            this.revelerId();
        }

    }
    public void strategique(){
        if (!this.estAccuse){
            int stratAlea = (int) (Math.random() * 2); //donne 0 ou 1 de manière aléatoire
            if (stratAlea == 0) {
                System.out.println("Le joueur virtuel"+ this.getNom() + " accuse un  Joueur");
                this.accuser();
            }
            else {
                System.out.println("Le joueur virtuel "+ this.getNom() + " utilise l'effet Hunt");
                this.choisirCarteRumour();
            }
        }
        else{
            int stratAlea = (int) (Math.random() * 2); //donne 0 ou 1 de manière aléatoire
            if (stratAlea == 0) {
                System.out.println("Le joueur virtuel"+ this.getNom() + " revele son identite");
                this.revelerId();
            }
            else {
                System.out.println("Le joueur virtuel"+ this.getNom() + " utilise l'effet Witch");
                this.choisirCarteRumour();
            }
        }

    }





}
