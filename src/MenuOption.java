import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class MenuOption extends JDialog{
    JRadioButton affichageFenetre = new JRadioButton("Fenêtré");
	JRadioButton affichagePleinEcran = new JRadioButton("Plein écran");
	JRadioButton posCurseurCentreButton = new JRadioButton("Centré");
	JRadioButton posCurseurHautGaucheButton = new JRadioButton("En haut à gauche");
	
	JRadioButton couleurCurseurPredefinie = new JRadioButton("Couleur prédéfinie");
	JComboBox<String> couleurPredefinieComboBox;
	JRadioButton couleurCurseurSpecifique = new JRadioButton("Définir la couleur");
	JTextField couleurCurseurRougeTextField = new JTextField(3);
	JTextField couleurCurseurVertTextField = new JTextField(3);
	JTextField couleurCurseurBleuTextField = new JTextField(3);
	
	JRadioButton couleurDessinPredefinie = new JRadioButton("Couleur prédéfinie");
	JComboBox<String> couleurPredefinieDessinComboBox;
	JRadioButton couleurDessinSpecifique = new JRadioButton("Définir la couleur");
	JTextField couleurDessinRougeTextField = new JTextField(3);
	JTextField couleurDessinVertTextField = new JTextField(3);
	JTextField couleurDessinBleuTextField = new JTextField(3);
	
	JTextField largeurDessinTextField = new JTextField(4);
	JTextField hauteurDessinTextField = new JTextField(4);
	
	String[] couleursPredefinie = {"Noir", "Bleu", "Cyan", "Gris", "Vert", "Magenta", "Orange", "Rose", "Rouge", "Jaune", "Blanc"};
	
	
	
	public MenuOption(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(550, 500);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
		initComponent();
		this.setVisible(true);
		
		
	}
	
	private void initComponent(){
		//Affichage
		JPanel panAffichage = new JPanel();
		panAffichage.setBorder(BorderFactory.createTitledBorder("Mode d'affichage au démarrage"));
		affichageFenetre.setSelected(true);
		
		ButtonGroup affichage = new ButtonGroup();
		affichage.add(affichageFenetre);
		affichage.add(affichagePleinEcran);
		panAffichage.add(affichageFenetre);
		panAffichage.add(affichagePleinEcran);
		
		//Curseur
		JPanel panCurseur = new JPanel();
		panCurseur.setBorder(BorderFactory.createTitledBorder("Curseur"));
		
			//Position du curseur
		JPanel panPosCurseur = new JPanel();
		panPosCurseur.setLayout(new BoxLayout(panPosCurseur, BoxLayout.LINE_AXIS));
		JLabel labPosCurseur = new JLabel("Position du curseur au démarrage :");
		ButtonGroup posCurseurGroup = new ButtonGroup();
		posCurseurGroup.add(posCurseurCentreButton);
		posCurseurGroup.add(posCurseurHautGaucheButton);
		panPosCurseur.add(labPosCurseur);
		panPosCurseur.add(posCurseurCentreButton);
		panPosCurseur.add(posCurseurHautGaucheButton);
		
			//Couleur par défaut
		JPanel panCouleurCurseur = new JPanel();
		panCouleurCurseur.setLayout(new BoxLayout(panCouleurCurseur, BoxLayout.PAGE_AXIS));
		JLabel labCouleurCurseur = new JLabel("Couleur par défaut :");
		
		JPanel panCouleurPredefinie = new JPanel();
		panCouleurPredefinie.setLayout(new BoxLayout(panCouleurPredefinie, BoxLayout.LINE_AXIS));
		couleurPredefinieComboBox = new JComboBox<String>();
		for(int i = 0; i<couleursPredefinie.length; i++)
			couleurPredefinieComboBox.addItem(couleursPredefinie[i]);
		panCouleurPredefinie.add(couleurCurseurPredefinie);
		panCouleurPredefinie.add(couleurPredefinieComboBox);
		
		JPanel panCouleurDefinie = new JPanel();
		panCouleurDefinie.setLayout(new BoxLayout(panCouleurDefinie, BoxLayout.LINE_AXIS));
		JLabel labRouge = new JLabel("Rouge :");
		JLabel labVert = new JLabel("Vert :");
		JLabel labBleu = new JLabel("Bleu :");
		panCouleurDefinie.add(couleurCurseurSpecifique);
		panCouleurDefinie.add(couleurCurseurRougeTextField);
		panCouleurDefinie.add(labRouge);
		panCouleurDefinie.add(couleurCurseurVertTextField);
		panCouleurDefinie.add(labVert);
		panCouleurDefinie.add(couleurCurseurBleuTextField);
		panCouleurDefinie.add(labBleu);
		
		panCouleurCurseur.add(labCouleurCurseur);
		panCouleurCurseur.add(panCouleurPredefinie);
		panCouleurCurseur.add(panCouleurDefinie);
		
			//Ajout des JPanel au JPanel "Curseur"
		panCurseur.add(panPosCurseur);
		panCurseur.add(panCouleurCurseur);
		
		
		//Dessin
		JPanel panDessin = new JPanel();
		panDessin.setBorder(BorderFactory.createTitledBorder("Dessin"));
			
			//Taille du dessin par défaut
		JPanel panTailleDessin = new JPanel();
		panTailleDessin.setLayout(new BoxLayout(panTailleDessin, BoxLayout.PAGE_AXIS));
		JLabel labTailleDessin = new JLabel("Taille du dessin par défaut :");
		
		JPanel panChoixTailleDessin = new JPanel();
		panChoixTailleDessin.setLayout(new BoxLayout(panChoixTailleDessin, BoxLayout.LINE_AXIS));
		JLabel labLargeurDessin = new JLabel("Largeur du dessin :");
		JLabel labHauteurDessin = new JLabel("Hauteur du dessin :");
		panChoixTailleDessin.add(labLargeurDessin);
		panChoixTailleDessin.add(largeurDessinTextField);
		panChoixTailleDessin.add(labHauteurDessin);
		panChoixTailleDessin.add(hauteurDessinTextField);
		
		panTailleDessin.add(labTailleDessin);
		panTailleDessin.add(panChoixTailleDessin);
		
			//Couleur du Background par défaut
		JPanel panCouleurDessin = new JPanel();
		panCouleurDessin.setLayout(new BoxLayout(panCouleurDessin, BoxLayout.PAGE_AXIS));
		JLabel labCouleurDessin = new JLabel("Couleur par défaut :");
		
		JPanel panCouleurDessinPredefinie = new JPanel();
		panCouleurDessinPredefinie.setLayout(new BoxLayout(panCouleurDessinPredefinie, BoxLayout.LINE_AXIS));
		couleurPredefinieDessinComboBox = new JComboBox<String>();
		for(int i = 0; i<couleursPredefinie.length; i++)
			couleurPredefinieDessinComboBox.addItem(couleursPredefinie[i]);
		panCouleurDessinPredefinie.add(couleurDessinPredefinie);
		panCouleurDessinPredefinie.add(couleurPredefinieDessinComboBox);
		
		JPanel panCouleurDessinDefinie = new JPanel();
		panCouleurDessinDefinie.setLayout(new BoxLayout(panCouleurDessinDefinie, BoxLayout.LINE_AXIS));
		JLabel labDessinRouge = new JLabel("Rouge :");
		JLabel labDessinVert = new JLabel("Vert :");
		JLabel labDessinBleu = new JLabel("Bleu :");
		panCouleurDessinDefinie.add(couleurDessinSpecifique);
		panCouleurDessinDefinie.add(couleurDessinRougeTextField);
		panCouleurDessinDefinie.add(labDessinRouge);
		panCouleurDessinDefinie.add(couleurDessinVertTextField);
		panCouleurDessinDefinie.add(labDessinVert);
		panCouleurDessinDefinie.add(couleurDessinBleuTextField);
		panCouleurDessinDefinie.add(labDessinBleu);
		
		panCouleurDessin.add(labCouleurDessin);
		panCouleurDessin.add(panCouleurDessinPredefinie);
		panCouleurDessin.add(panCouleurDessinDefinie);
		
			//Ajout des JPanel au JPanel "Dessin"
		panDessin.add(panTailleDessin);
		panDessin.add(panCouleurDessin);

		
		
		
		
		
		//Bouton Enregistrer/Quitter
		JPanel EnregistrerAnnuler = new JPanel();
		JButton buttonEnregistrer = new JButton("Enregistrer");
		JButton buttonAnnuler = new JButton("Annuler");
		
		buttonEnregistrer.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		    	  //TRAITEMENT A AJOUTER
		        setVisible(false);
		      }      
		    });
		buttonAnnuler.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        setVisible(false);
	      }      
	    });
		
		EnregistrerAnnuler.add(buttonEnregistrer);
		EnregistrerAnnuler.add(buttonAnnuler);
		
		//Ajout de tous les JPanel dans la boite Option
		Box content = Box.createVerticalBox();
		content.add(panAffichage);
		content.add(panCurseur);
		content.add(panDessin);
		content.add(EnregistrerAnnuler);
		
		//Affichage
		this.getContentPane().add(content);
	}
}
