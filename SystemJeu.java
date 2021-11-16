import java.util.ArrayList;
import java.util.*;

public class SystemJeu {

    public int chercherJoueur(String nom, ArrayList<Joueur> JoueurList){ //méthode pour rechercher le nom d'un joueur parmis ceux déjà existants
        int i=0;
        boolean check = false; // la variable check permet de voir si le nom est présent dans le tableau de joueur.
        while (check == false){
            while(!JoueurList.get(i).getNom().equals(nom) && i < JoueurList.size()){ //Tant qu'on ne trouve pas le nom du joueur on incrémente i
                i++;
            }
            if (i == JoueurList.size()){ //Si le prénom n'est pas présent dans le tableau de Joueur, on demnde à l'utilisateur de ressaisir le nom.
                Scanner scanner = new Scanner(System.in);
                System.out.println("Le nom est faux. Veuillez resaisir le nom ");
                String nom2 = scanner.nextLine();
                scanner.close();
            }
            else{check = true;}
        }
        return i;
    }
}
