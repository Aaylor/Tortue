import javax.swing.*;
import java.awt.*;
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
		this.setSize(400, 700);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
		initComponent();
		this.setVisible(true);
		
		
	}
	
	private void initComponent(){
		//Affichage
		JPanel panAffichage = new JPanel();
		panAffichage.setLayout(new BoxLayout(panAffichage, BoxLayout.PAGE_AXIS));
		panAffichage.setBorder(BorderFactory.createTitledBorder("Affichage"));
		
		JLabel labTailleFenetre = new JLabel("Mode d'affichage au démarrage :");
		
		ButtonGroup affichage = new ButtonGroup();
		affichage.add(affichageFenetre);
		affichage.add(affichagePleinEcran);
		affichageFenetre.setSelected(true);
		
		panAffichage.add(labTailleFenetre);
		panAffichage.add(affichageFenetre);
		panAffichage.add(affichagePleinEcran);
		
		//Curseur
		JPanel panCurseur = new JPanel();
		panCurseur.setLayout(new BoxLayout(panCurseur, BoxLayout.PAGE_AXIS));
		panCurseur.setBorder(BorderFactory.createTitledBorder("Curseur"));
		
			//Position du curseur
		JLabel labPosCurseur = new JLabel("Position du curseur au démarrage : ");
		
		ButtonGroup posCurseurGroup = new ButtonGroup();
		posCurseurGroup.add(posCurseurCentreButton);
		posCurseurGroup.add(posCurseurHautGaucheButton);
		
		panCurseur.add(labPosCurseur);
		panCurseur.add(posCurseurCentreButton);
		panCurseur.add(posCurseurHautGaucheButton);
		
			//Couleur par défaut
		JLabel labCouleurCurseur = new JLabel("Couleurs par défaut : ");
		
		ButtonGroup couleurCurseurGroup = new ButtonGroup();
		couleurCurseurGroup.add(couleurCurseurPredefinie);
		couleurCurseurGroup.add(couleurCurseurSpecifique);
		
		couleurPredefinieComboBox = new JComboBox<String>();
		for(int i = 0; i<couleursPredefinie.length; i++)
			couleurPredefinieComboBox.addItem(couleursPredefinie[i]);
		
		JPanel panCouleurRougeDefinir = new JPanel();
		JLabel labRouge = new JLabel("Rouge : ");
		panCouleurRougeDefinir.add(labRouge);
		panCouleurRougeDefinir.add(couleurCurseurRougeTextField);
		
		JPanel panCouleurVertDefinir = new JPanel();
		JLabel labVert = new JLabel("Vert : ");
		panCouleurVertDefinir.add(labVert);
		panCouleurVertDefinir.add(couleurCurseurVertTextField);
		
		JPanel panCouleurBleuDefinir = new JPanel();
		
		JLabel labBleu = new JLabel("Bleu : ");
		panCouleurBleuDefinir.add(labBleu);
		panCouleurBleuDefinir.add(couleurCurseurBleuTextField);
		
		panCurseur.add(labCouleurCurseur);
		panCurseur.add(couleurCurseurPredefinie);
		panCurseur.add(couleurPredefinieComboBox);
		panCurseur.add(couleurCurseurSpecifique);
		panCurseur.add(panCouleurRougeDefinir);
		panCurseur.add(panCouleurVertDefinir);
		panCurseur.add(panCouleurBleuDefinir);
		
		//Dessin
		JPanel panDessin = new JPanel();
		panDessin.setLayout(new BoxLayout(panDessin, BoxLayout.PAGE_AXIS));
		panDessin.setBorder(BorderFactory.createTitledBorder("Dessin"));
		
		
			
			//Taille du dessin par défaut
		JLabel labTailleDessin = new JLabel("Taille du dessin par défaut :");
		
		JPanel panChoixLargeurDessin = new JPanel();
		JLabel labLargeurDessin = new JLabel("Largeur du dessin : ");
		panChoixLargeurDessin.add(labLargeurDessin);
		panChoixLargeurDessin.add(largeurDessinTextField);
		
		JPanel panChoixHauteurDessin = new JPanel();
		JLabel labHauteurDessin = new JLabel("Hauteur du dessin : ");
		panChoixHauteurDessin.add(labHauteurDessin);
		panChoixHauteurDessin.add(hauteurDessinTextField);
		
		panDessin.add(labTailleDessin);
		panDessin.add(panChoixLargeurDessin);
		panDessin.add(panChoixHauteurDessin);
		
			//Couleur du Background par défaut
		JLabel labCouleurDessin = new JLabel("Couleur par défaut :");
		
		ButtonGroup couleurDessinGroup = new ButtonGroup();
		couleurDessinGroup.add(couleurDessinPredefinie);
		couleurDessinGroup.add(couleurDessinSpecifique);
		
		couleurPredefinieDessinComboBox = new JComboBox<String>();
		for(int i = 0; i<couleursPredefinie.length; i++)
			couleurPredefinieDessinComboBox.addItem(couleursPredefinie[i]);
		
		JPanel panCouleurRougeDessinDefinie = new JPanel();
		JLabel labDessinRouge = new JLabel("Rouge : ");
		panCouleurRougeDessinDefinie.add(labDessinRouge);
		panCouleurRougeDessinDefinie.add(couleurDessinRougeTextField);
		
		JPanel panCouleurVertDessinDefinie = new JPanel();
		JLabel labDessinVert = new JLabel("Vert : ");
		panCouleurVertDessinDefinie.add(labDessinVert);
		panCouleurVertDessinDefinie.add(couleurDessinVertTextField);

		JPanel panCouleurBleuDessinDefinie = new JPanel();
		JLabel labDessinBleu = new JLabel("Bleu : ");
		panCouleurBleuDessinDefinie.add(labDessinBleu);
		panCouleurBleuDessinDefinie.add(couleurDessinBleuTextField);
		
		panDessin.add(labCouleurDessin);
		panDessin.add(couleurDessinPredefinie);
		panDessin.add(couleurPredefinieDessinComboBox);
		panDessin.add(couleurDessinSpecifique);
		panDessin.add(panCouleurRougeDessinDefinie);
		panDessin.add(panCouleurVertDessinDefinie);
		panDessin.add(panCouleurBleuDessinDefinie);
		
		
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
