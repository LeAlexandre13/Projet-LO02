package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modele.CarteRumeur;
import Modele.JeuCarte;
import Modele.Joueur;
import Modele.NomCarte;
import Modele.Partie;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class InterfacePartie extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacePartie frame = new InterfacePartie();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfacePartie() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Carte1 = new JPanel();
		Carte1.setBounds(82, 368, 50, 75);
		contentPane.add(Carte1);
		Carte1.setLayout(null);
		
		JLabel ImageCarte1 = new JLabel("");
		
		ImageCarte1.setBounds(0, 0, 50, 75);
		Carte1.add(ImageCarte1);
		
		JPanel Carte2 = new JPanel();
		Carte2.setBounds(197, 367, 50, 75);
		contentPane.add(Carte2);
		Carte2.setLayout(null);
		
		JLabel ImageCarte2 = new JLabel("");
		ImageCarte2.setBounds(0, 0, 50, 75);
		Carte2.add(ImageCarte2);
		
		JPanel Carte3 = new JPanel();
		Carte3.setBounds(296, 367, 50, 75);
		contentPane.add(Carte3);
		Carte3.setLayout(null);
		
		JLabel Image3 = new JLabel("");
		Image3.setBounds(0, 0, 50, 75);
		Carte3.add(Image3);
		
		JPanel Carte4 = new JPanel();
		Carte4.setBounds(403, 367, 50, 75);
		contentPane.add(Carte4);
		Carte4.setLayout(null);
		
		JLabel Image4 = new JLabel("");
		Image4.setBounds(0, 0, 50, 75);
		Carte4.add(Image4);
		
		JPanel Carte5 = new JPanel();
		Carte5.setBounds(503, 367, 50, 75);
		contentPane.add(Carte5);
		Carte5.setLayout(null);
		
		JLabel Image5 = new JLabel("");
		Image5.setBounds(0, 0, 50, 75);
		Carte5.add(Image5);
		
		JPanel Carte6 = new JPanel();
		Carte6.setBounds(606, 367, 50, 75);
		contentPane.add(Carte6);
		Carte6.setLayout(null);
		
		JLabel Image6 = new JLabel("");
		Image6.setBounds(0, 0, 50, 75);
		Carte6.add(Image6);
		Partie partie = Partie.getInstance();
		Joueur joueur1 = new Joueur();
		Joueur joueur2 = new Joueur();
		Joueur joueur3 = new Joueur();
		partie.getTabjoueur().add(joueur1);
		partie.getTabjoueur().add(joueur2);
		partie.getTabjoueur().add(joueur3);
		partie.distribuerCartesRumours();
		for (int i =0; i < joueur1.getCarteJoueurMain().size(); i++) {
			String nomCarte = joueur1.getCarteJoueurMain().get(i).getNomCarte().toString();
			int numBox= i;
			
			i.setIcon(new ImageIcon("C:\\Users\\phamt\\OneDrive\\M\u00E1y t\u00EDnh\\LO02\\Image CarteRumour\\"+nomCarte+".png"));

		}
		
		CarteRumeur carte = new CarteRumeur(NomCarte.AngryMob, 1);
		CarteRumeur carte2= new CarteRumeur(NomCarte.BlackCat,2);
		String nomCarte = carte.getNomCarte().toString();
		ImageCarte1.setIcon(new ImageIcon("C:\\Users\\phamt\\OneDrive\\M\u00E1y t\u00EDnh\\LO02\\Image CarteRumour\\"+nomCarte+".png"));
		String nomCarte2 = carte2.getNomCarte().toString();
		ImageCarte2.setIcon(new ImageIcon("C:\\Users\\phamt\\OneDrive\\M\u00E1y t\u00EDnh\\LO02\\Image CarteRumour\\"+nomCarte2+".png"));
		

		
		
	}
}
