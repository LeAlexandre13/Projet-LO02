package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modele.Partie;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfaceChoisirNom extends JFrame {

	private JPanel contentPane;
	private JTextField nomJoueur1;
	private JTextField nomJoueur2;
	private JTextField nomJoueur3;
	private JTextField nomJoueur4;
	private JTextField nomJoueur5;
	private JTextField nomJoueur6;

	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the frame.
	 */
	public InterfaceChoisirNom(int nombreJoueurReel, int nombreJoueurVirtuel) {
		Partie partie = Partie.getInstance();
		partie.ajouterJoueur(nombreJoueurVirtuel, nombreJoueurReel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1015, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Joueur1 = new JLabel("");
		Joueur1.setIcon(new ImageIcon("C:\\Users\\phamt\\OneDrive\\M\u00E1y t\u00EDnh\\LO02\\AvatarJoueur\\1.png"));
		Joueur1.setBounds(31, 52, 190, 129);
		contentPane.add(Joueur1);
		
		JLabel Joueur2 = new JLabel("New label");
		Joueur2.setIcon(new ImageIcon("C:\\Users\\phamt\\OneDrive\\M\u00E1y t\u00EDnh\\LO02\\AvatarJoueur\\2.png"));
		Joueur2.setBounds(385, 52, 190, 129);
		contentPane.add(Joueur2);
		
		JLabel Joueur3 = new JLabel("New label");
		Joueur3.setIcon(new ImageIcon("C:\\Users\\phamt\\OneDrive\\M\u00E1y t\u00EDnh\\LO02\\AvatarJoueur\\3.png"));
		Joueur3.setBounds(709, 52, 190, 129);
		contentPane.add(Joueur3);
		
		JLabel Joueur4 = new JLabel("New label");
		Joueur4.setIcon(new ImageIcon("C:\\Users\\phamt\\OneDrive\\M\u00E1y t\u00EDnh\\LO02\\AvatarJoueur\\4.png"));
		Joueur4.setBounds(31, 324, 190, 129);
		contentPane.add(Joueur4);
		
		JLabel Joueur5 = new JLabel("New label");
		Joueur5.setIcon(new ImageIcon("C:\\Users\\phamt\\OneDrive\\M\u00E1y t\u00EDnh\\LO02\\AvatarJoueur\\5.png"));
		Joueur5.setBounds(385, 324, 190, 129);
		contentPane.add(Joueur5);
		
		JLabel Joueur6 = new JLabel("New label");
		Joueur6.setIcon(new ImageIcon("C:\\Users\\phamt\\OneDrive\\M\u00E1y t\u00EDnh\\LO02\\AvatarJoueur\\6.png"));
		Joueur6.setBounds(709, 324, 190, 129);
		contentPane.add(Joueur6);
		
		nomJoueur1 = new JTextField();
		nomJoueur1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomJoueur1.setBounds(76, 201, 107, 38);
		contentPane.add(nomJoueur1);
		nomJoueur1.setColumns(10);
		
		nomJoueur2 = new JTextField();
		nomJoueur2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomJoueur2.setColumns(10);
		nomJoueur2.setBounds(433, 201, 107, 38);
		contentPane.add(nomJoueur2);
		
		nomJoueur3 = new JTextField();
		nomJoueur3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomJoueur3.setColumns(10);
		nomJoueur3.setBounds(760, 201, 107, 38);
		contentPane.add(nomJoueur3);
		
		nomJoueur4 = new JTextField();
		nomJoueur4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomJoueur4.setColumns(10);
		nomJoueur4.setBounds(76, 475, 107, 38);
		contentPane.add(nomJoueur4);
		
		nomJoueur5 = new JTextField();
		nomJoueur5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomJoueur5.setColumns(10);
		nomJoueur5.setBounds(433, 475, 107, 38);
		contentPane.add(nomJoueur5);
		
		nomJoueur6 = new JTextField();
		nomJoueur6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomJoueur6.setColumns(10);
		nomJoueur6.setBounds(760, 475, 107, 38);
		contentPane.add(nomJoueur6);
		
		int totalJoueur = nombreJoueurVirtuel + nombreJoueurReel;
		partie.getTabjoueur().get(0).setNom(nomJoueur1.getText());
		partie.getTabjoueur().get(1).setNom(nomJoueur2.getText());
		partie.getTabjoueur().get(2).setNom(nomJoueur3.getText());

		if (totalJoueur == 5) {
			Joueur6.setVisible(false);
			nomJoueur6.setVisible(false);
			partie.getTabjoueur().get(3).setNom(nomJoueur4.getText());
			partie.getTabjoueur().get(4).setNom(nomJoueur5.getText());

		}
		if (totalJoueur == 4) {
			Joueur5.setVisible(false);
			nomJoueur5.setVisible(false);
			Joueur6.setVisible(false);
			nomJoueur6.setVisible(false);
			partie.getTabjoueur().get(3).setNom(nomJoueur4.getText());

		}
		if (totalJoueur == 3) {
			Joueur4.setVisible(false);
			nomJoueur4.setVisible(false);
			Joueur5.setVisible(false);
			nomJoueur5.setVisible(false);
			Joueur6.setVisible(false);
			nomJoueur6.setVisible(false);
		}
	
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(446, 535, 85, 21);
		contentPane.add(btnNewButton);
	}
}
