public class JoueurVirtuelle extends Joueur implements Strategie  {

    public JoueurVirtuelle(){ //constructeur de JoueurVirtuelle
        super(refJoueur);
    }

    public void aggressif(){
        while(tour==true){
            this.accuser();
        }
    }

    public void strategique(){
        int stratAlea = (int)(Math.random()*1); //donne 0 ou 1 de manière aléatoire
        if(stratAlea==0){
            this.accuser();
        }
        else{
            int carteAlea= (int)(Math.random()*carteJoueurMain.size()); //nombre aléatoire entre 0 et le nombre total de carte présentes dans la main du joueur
            this.efffetHunt(carteJoueurMain.get(carteAlea));
        }
    }

}
