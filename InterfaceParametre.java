package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modele.Partie;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfaceParametre extends JFrame {

	private JPanel contentPane;
	private JComboBox boxNombreJoueurVirtuel;
	private JComboBox boxNombreJoueurReel;
	private Partie partie;

	/**
	 * Launch the application.
	 */ 	
	

	/**
	 * Create the frame.
	 */
	public InterfaceParametre() {
		partie = Partie.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Veuillez choisir le nombre de joueur reel");
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblNewLabel.setBounds(10, 28, 297, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblVeuillezChoisirLe = new JLabel("Veuillez choisir le nombre de joueur virtuel");
		lblVeuillezChoisirLe.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblVeuillezChoisirLe.setBounds(10, 75, 284, 18);
		contentPane.add(lblVeuillezChoisirLe);
		
		boxNombreJoueurReel = new JComboBox();
		boxNombreJoueurReel.setModel(new DefaultComboBoxModel(new Integer[] { 1, 2, 3, 4, 5, 6 }));
		boxNombreJoueurReel.setBounds(318, 28, 37, 21);
		contentPane.add(boxNombreJoueurReel);
		
		boxNombreJoueurVirtuel = new JComboBox();
		boxNombreJoueurVirtuel.setModel(new DefaultComboBoxModel(new Integer[] { 1, 2, 3, 4, 5, 6 }));
		boxNombreJoueurVirtuel.setBounds(318, 75, 37, 21);
		contentPane.add(boxNombreJoueurVirtuel);
		
		JLabel lblNiveau = new JLabel("Niveau");
		lblNiveau.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblNiveau.setBounds(10, 148, 70, 18);
		contentPane.add(lblNiveau);
		
		JComboBox boxNiveau = new JComboBox();
		boxNiveau.setModel(new DefaultComboBoxModel(new String[] { "Facile","Dificile" }));
		boxNiveau.setBounds(90, 148, 111, 21);
		contentPane.add(boxNiveau);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nombreJoueurReel = (int) boxNombreJoueurReel.getSelectedItem();
				int nombreJoueurVirtuel = (int) boxNombreJoueurVirtuel.getSelectedItem();
				setVisible(false);
				InterfaceChoisirNom interfaceChoisirNom = new InterfaceChoisirNom(nombreJoueurReel, nombreJoueurVirtuel);
				interfaceChoisirNom.setVisible(true);
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(149, 209, 158, 27);
		contentPane.add(btnNewButton);
	}
}
