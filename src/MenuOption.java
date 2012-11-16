import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class MenuOption extends JDialog{
    JRadioButton affichageFenetre = new JRadioButton("Fenêtré");
	JRadioButton affichagePleinEcran = new JRadioButton("Plein écran");
	JRadioButton posCurseurCentreButton = new JRadioButton("Centré");
	JRadioButton posCurseurHautGaucheButton = new JRadioButton("En haut à gauche");
	
	JRadioButton couleurCurseurPredefinie = new JRadioButton("Couleur prédéfinie");
	JComboBox couleurPredefinieComboBox;
	JRadioButton couleurCurseurSpecifique = new JRadioButton("Définir la couleur");
	JFormattedTextField couleurCurseurRougeTextField;
	JFormattedTextField couleurCurseurVertTextField;
	JFormattedTextField couleurCurseurBleuTextField;
	
	JRadioButton couleurDessinPredefinie = new JRadioButton("Couleur prédéfinie");
	JComboBox couleurPredefinieDessinComboBox;
	JRadioButton couleurDessinSpecifique = new JRadioButton("Définir la couleur");
	JFormattedTextField couleurDessinRougeTextField;
	JFormattedTextField couleurDessinVertTextField;
	JFormattedTextField couleurDessinBleuTextField;
	
	JFormattedTextField largeurDessinTextField;
	JFormattedTextField hauteurDessinTextField;
	
	String[] couleursPredefinie = {"Noir", "Bleu", "Cyan", "Gris", "Vert", "Magenta", "Orange", "Rose", "Rouge", "Jaune", "Blanc"};
	
    //Données de configuration du programme
    private static boolean configProgrammeEstFenetre;//True : Le programme se lance en mode fenetre, False : le programme se lance en plein ecran
    private static boolean configCurseurEstCentre;//True : Le curseur est centré au démarrage, False : le curseur est en haut à gauche
    private static int configCurseurRed;
    private static int configCurseurGreen;
    private static int configCurseurBlue;
    private static int configDessinLargeur;
    private static int configDessinHauteur;
    private static int configDessinBackgroundRed;
    private static int configDessinBackgroundGreen;
    private static int configDessinBackgroundBlue;
	
	
	
	public MenuOption(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(300, 590);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
		initComponent();
		this.setVisible(true);
		
		
	}
	
	private void initComponent(){
		  ////////////////////////////////////////////////
		 //         CONFIGURATION DES COMPOSANTS       //
		////////////////////////////////////////////////
		
		//Configuration des JFormattedTextField recevant les couleur RGB
		NumberFormat formatCouleur = NumberFormat.getIntegerInstance();
		formatCouleur.setMaximumIntegerDigits(3);
		couleurCurseurRougeTextField = new JFormattedTextField(formatCouleur);
		couleurCurseurRougeTextField.setText(""+configCurseurRed);
		couleurCurseurVertTextField = new JFormattedTextField(formatCouleur);
		couleurCurseurVertTextField.setText(""+configCurseurGreen);
		couleurCurseurBleuTextField = new JFormattedTextField(formatCouleur);
		couleurCurseurBleuTextField.setText(""+configCurseurGreen);
		couleurDessinRougeTextField = new JFormattedTextField(formatCouleur);
		couleurDessinRougeTextField.setText(""+configDessinBackgroundRed);
		couleurDessinVertTextField = new JFormattedTextField(formatCouleur);
		couleurDessinVertTextField.setText(""+configDessinBackgroundGreen);
		couleurDessinBleuTextField = new JFormattedTextField(formatCouleur);
		couleurDessinBleuTextField.setText(""+configDessinBackgroundBlue);
		
		//Configuration des JTextfield recevant la taille du dessin
		NumberFormat formatTaille = NumberFormat.getIntegerInstance();
		formatTaille.setMaximumIntegerDigits(4);
		largeurDessinTextField = new JFormattedTextField(formatTaille);
		largeurDessinTextField.setText(""+configDessinLargeur);
		hauteurDessinTextField = new JFormattedTextField(formatTaille);
		hauteurDessinTextField.setText(""+configDessinHauteur);
		
		
		  ////////////////////////////////////////////////
		 //        INITIALISATION DES COMPOSANTS       //
		////////////////////////////////////////////////
		
		//Affichage
		JPanel panAffichage = new JPanel();
		panAffichage.setLayout(new BoxLayout(panAffichage, BoxLayout.PAGE_AXIS));
		panAffichage.setBorder(BorderFactory.createTitledBorder("Affichage"));
		
		JLabel labTailleFenetre = new JLabel("Mode d'affichage au démarrage :");
		
		ButtonGroup affichage = new ButtonGroup();
		affichage.add(affichageFenetre);
		affichage.add(affichagePleinEcran);
		if(configProgrammeEstFenetre) affichageFenetre.setSelected(true);
		else affichagePleinEcran.setSelected(true);
			
		
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
		if(configCurseurEstCentre) posCurseurCentreButton.setSelected(true);
		else posCurseurHautGaucheButton.setSelected(true);
		
		panCurseur.add(labPosCurseur);
		panCurseur.add(posCurseurCentreButton);
		panCurseur.add(posCurseurHautGaucheButton);
		
			//Couleur par défaut
		JLabel labCouleurCurseur = new JLabel("Couleurs par défaut : ");
		
		ButtonGroup couleurCurseurGroup = new ButtonGroup();
		couleurCurseurGroup.add(couleurCurseurPredefinie);
		couleurCurseurGroup.add(couleurCurseurSpecifique);
		couleurCurseurSpecifique.setSelected(true);
		
		JPanel panCouleurCurseurPredefinie = new JPanel();
		couleurPredefinieComboBox = new JComboBox();
		for(int i = 0; i<couleursPredefinie.length; i++)
			couleurPredefinieComboBox.addItem(couleursPredefinie[i]);
		panCouleurCurseurPredefinie.add(couleurCurseurPredefinie);
		panCouleurCurseurPredefinie.add(couleurPredefinieComboBox);
		couleurPredefinieComboBox.setEnabled(false);
		
		
		JPanel panCouleurRougeDefinir = new JPanel();
		JLabel labRouge = new JLabel("Rouge : ");
		panCouleurRougeDefinir.add(Box.createRigidArea(new Dimension(20,0)));
		panCouleurRougeDefinir.add(labRouge);
		panCouleurRougeDefinir.add(couleurCurseurRougeTextField);
		
		JPanel panCouleurVertDefinir = new JPanel();
		JLabel labVert = new JLabel("Vert     : ");
		panCouleurVertDefinir.add(Box.createRigidArea(new Dimension(20,0)));
		panCouleurVertDefinir.add(labVert);
		panCouleurVertDefinir.add(couleurCurseurVertTextField);
		
		JPanel panCouleurBleuDefinir = new JPanel();
		
		JLabel labBleu = new JLabel("Bleu     : ");
		panCouleurBleuDefinir.add(Box.createRigidArea(new Dimension(20,0)));
		panCouleurBleuDefinir.add(labBleu);
		panCouleurBleuDefinir.add(couleurCurseurBleuTextField);
		
		panCurseur.add(labCouleurCurseur);
		panCurseur.add(panCouleurCurseurPredefinie);
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
		couleurDessinSpecifique.setSelected(true);
		
		JPanel panCouleurDessinPredefinie = new JPanel();
		couleurPredefinieDessinComboBox = new JComboBox();
		for(int i = 0; i<couleursPredefinie.length; i++)
			couleurPredefinieDessinComboBox.addItem(couleursPredefinie[i]);
		couleurPredefinieDessinComboBox.setSelectedIndex(10);
		panCouleurDessinPredefinie.add(couleurDessinPredefinie);
		panCouleurDessinPredefinie.add(couleurPredefinieDessinComboBox);
		couleurPredefinieDessinComboBox.setEnabled(false);
		
		JPanel panCouleurRougeDessinDefinie = new JPanel();
		JLabel labDessinRouge = new JLabel("Rouge : ");
		panCouleurRougeDessinDefinie.add(Box.createRigidArea(new Dimension(20,0)));
		panCouleurRougeDessinDefinie.add(labDessinRouge);
		panCouleurRougeDessinDefinie.add(couleurDessinRougeTextField);
		
		JPanel panCouleurVertDessinDefinie = new JPanel();
		JLabel labDessinVert = new JLabel("Vert     : ");
		panCouleurVertDessinDefinie.add(Box.createRigidArea(new Dimension(20,0)));
		panCouleurVertDessinDefinie.add(labDessinVert);
		panCouleurVertDessinDefinie.add(couleurDessinVertTextField);

		JPanel panCouleurBleuDessinDefinie = new JPanel();
		JLabel labDessinBleu = new JLabel("Bleu     : ");
		panCouleurBleuDessinDefinie.add(Box.createRigidArea(new Dimension(20,0)));
		panCouleurBleuDessinDefinie.add(labDessinBleu);
		panCouleurBleuDessinDefinie.add(couleurDessinBleuTextField);
		
		panDessin.add(labCouleurDessin);
		panDessin.add(panCouleurDessinPredefinie);
		panDessin.add(couleurDessinSpecifique);
		panDessin.add(panCouleurRougeDessinDefinie);
		panDessin.add(panCouleurVertDessinDefinie);
		panDessin.add(panCouleurBleuDessinDefinie);
		
		
		//Bouton Enregistrer/Quitter
		JPanel EnregistrerAnnuler = new JPanel();
		JButton buttonEnregistrer = new JButton("Enregistrer");
		JButton buttonAnnuler = new JButton("Annuler");
		
		EnregistrerAnnuler.add(buttonEnregistrer);
		EnregistrerAnnuler.add(buttonAnnuler);
		
		
		//Ajout de tous les JPanel dans la boite Option
		//Box content = Box.createVerticalBox();
		JPanel content = new JPanel();
		content.add(panAffichage);
		content.add(panCurseur);
		content.add(panDessin);
		content.add(EnregistrerAnnuler);
		
		//Affichage
		this.getContentPane().add(content);
		
		
		  ////////////////////////////////////////////////
		 //POSITIONNEMENT DU TOUT DANS LA DIALOGUE BOX //
		////////////////////////////////////////////////
			//Tailles
		panAffichage.setPreferredSize(new Dimension(this.getWidth() - 20, 90));
		panCurseur.setPreferredSize(new Dimension(this.getWidth() - 20, 210));
		panDessin.setPreferredSize(new Dimension(this.getWidth() - 20, 205));
		
			//Positionnement dans les section
			//Affichage
		labCouleurCurseur.setAlignmentX(LEFT_ALIGNMENT);
		panCouleurCurseurPredefinie.setAlignmentX(LEFT_ALIGNMENT);
		panCouleurCurseurPredefinie.setLayout(new BoxLayout(panCouleurCurseurPredefinie, BoxLayout.LINE_AXIS));
		couleurPredefinieComboBox.setMaximumSize(new Dimension(100, 18));
		couleurCurseurSpecifique.setAlignmentX(LEFT_ALIGNMENT);
		
		panCouleurRougeDefinir.setAlignmentX(LEFT_ALIGNMENT);
		panCouleurRougeDefinir.setLayout(new BoxLayout(panCouleurRougeDefinir, BoxLayout.LINE_AXIS));
		panCouleurRougeDefinir.setMaximumSize(new Dimension(105, 20));
		
		panCouleurVertDefinir.setAlignmentX(LEFT_ALIGNMENT);
		panCouleurVertDefinir.setAlignmentX(LEFT_ALIGNMENT);
		panCouleurVertDefinir.setLayout(new BoxLayout(panCouleurVertDefinir, BoxLayout.LINE_AXIS));
		panCouleurVertDefinir.setMaximumSize(new Dimension(105, 20));
		
		panCouleurBleuDefinir.setAlignmentX(LEFT_ALIGNMENT);
		panCouleurBleuDefinir.setAlignmentX(LEFT_ALIGNMENT);
		panCouleurBleuDefinir.setLayout(new BoxLayout(panCouleurBleuDefinir, BoxLayout.LINE_AXIS));
		panCouleurBleuDefinir.setMaximumSize(new Dimension(105, 20));
		
			//Dessin
		labTailleDessin.setAlignmentX(LEFT_ALIGNMENT);
		panChoixLargeurDessin.setAlignmentX(LEFT_ALIGNMENT);
		panChoixLargeurDessin.setLayout(new BoxLayout(panChoixLargeurDessin, BoxLayout.LINE_AXIS));
		largeurDessinTextField.setMaximumSize(new Dimension(80, 20));
		panChoixHauteurDessin.setAlignmentX(LEFT_ALIGNMENT);
		panChoixHauteurDessin.setLayout(new BoxLayout(panChoixHauteurDessin, BoxLayout.LINE_AXIS));
		hauteurDessinTextField.setMaximumSize(new Dimension(80, 20));
		
		panCouleurDessinPredefinie.setAlignmentX(LEFT_ALIGNMENT);
		panCouleurDessinPredefinie.setLayout(new BoxLayout(panCouleurDessinPredefinie, BoxLayout.LINE_AXIS));
		couleurPredefinieDessinComboBox.setMaximumSize(new Dimension(80, 18));
		
		couleurDessinSpecifique.setAlignmentX(LEFT_ALIGNMENT);
		
		panCouleurRougeDessinDefinie.setAlignmentX(LEFT_ALIGNMENT);
		panCouleurRougeDessinDefinie.setLayout(new BoxLayout(panCouleurRougeDessinDefinie, BoxLayout.LINE_AXIS));
		panCouleurRougeDessinDefinie.setMaximumSize(new Dimension(105, 20));
		
		panCouleurVertDessinDefinie.setAlignmentX(LEFT_ALIGNMENT);
		panCouleurVertDessinDefinie.setLayout(new BoxLayout(panCouleurVertDessinDefinie, BoxLayout.LINE_AXIS));
		panCouleurVertDessinDefinie.setMaximumSize(new Dimension(105, 20));
		
		panCouleurBleuDessinDefinie.setAlignmentX(LEFT_ALIGNMENT);
		panCouleurBleuDessinDefinie.setLayout(new BoxLayout(panCouleurBleuDessinDefinie, BoxLayout.LINE_AXIS));
		panCouleurBleuDessinDefinie.setMaximumSize(new Dimension(105, 20));
		
		  /////////////////////////////////////////////////
		 //       INTERACTIONS AVEC LES BOUTONS         //
		/////////////////////////////////////////////////

		buttonEnregistrer.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		    	  verificationDesValeurs();
		    	  setVisible(false);
		      }      
		    });
		buttonAnnuler.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        setVisible(false);
	      }      
	    });
		couleurCurseurPredefinie.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		    	  couleurCurseurRougeTextField.setEnabled(false);
		    	  couleurCurseurVertTextField.setEnabled(false);
		    	  couleurCurseurBleuTextField.setEnabled(false);
		    	  couleurPredefinieComboBox.setEnabled(true);
		      }      
		    });
		couleurCurseurSpecifique.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		    	  couleurCurseurRougeTextField.setEnabled(true);
		    	  couleurCurseurVertTextField.setEnabled(true);
		    	  couleurCurseurBleuTextField.setEnabled(true);
		    	  couleurPredefinieComboBox.setEnabled(false);
		      }      
		    });
		couleurDessinPredefinie.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		    	  couleurDessinRougeTextField.setEnabled(false);
		    	  couleurDessinVertTextField.setEnabled(false);
		    	  couleurDessinBleuTextField.setEnabled(false);
		    	  couleurPredefinieDessinComboBox.setEnabled(true);
		      }      
		    });
		couleurDessinSpecifique.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		    	  couleurDessinRougeTextField.setEnabled(true);
		    	  couleurDessinVertTextField.setEnabled(true);
		    	  couleurDessinBleuTextField.setEnabled(true);
		    	  couleurPredefinieDessinComboBox.setEnabled(false);
		      }      
		    });
	}

	private void verificationDesValeurs(){
		boolean erreurCouleur = false;
		boolean erreurTailleDessin = false;
		//On crée ensuite un tableau qui va contenir les valeurs correctes
		int[] tabValeurs = new int[8];
		
		//Ajout des valeurs dans le tableau
		
		//Verif des champs vide
		if(couleurCurseurRougeTextField.getText().equals("")) couleurCurseurRougeTextField.setText("-1");
		tabValeurs[0] = Integer.parseInt(couleurCurseurRougeTextField.getText());
		if(couleurCurseurVertTextField.getText().equals("")) couleurCurseurVertTextField.setText("-1");
		tabValeurs[1] = Integer.parseInt(couleurCurseurVertTextField.getText());
		if(couleurCurseurBleuTextField.getText().equals("")) couleurCurseurBleuTextField.setText("-1");
		tabValeurs[2] = Integer.parseInt(couleurCurseurBleuTextField.getText());
		if(couleurDessinRougeTextField.getText().equals("")) couleurDessinRougeTextField.setText("-1");
		tabValeurs[3] = Integer.parseInt(couleurDessinRougeTextField.getText());
		if(couleurDessinVertTextField.getText().equals("")) couleurDessinVertTextField.setText("-1");
		tabValeurs[4] = Integer.parseInt(couleurDessinVertTextField.getText());
		if(couleurDessinBleuTextField.getText().equals("")) couleurDessinBleuTextField.setText("-1");
		tabValeurs[5] = Integer.parseInt(couleurDessinBleuTextField.getText());
		if(largeurDessinTextField.getText().equals("")) largeurDessinTextField.setText("-1");
		tabValeurs[6] = Integer.parseInt(largeurDessinTextField.getText());
		if(hauteurDessinTextField.getText().equals("")) hauteurDessinTextField.setText("-1");
		tabValeurs[7] = Integer.parseInt(hauteurDessinTextField.getText());
		
		//Verif des valeur
		for(int i=0; i < tabValeurs.length; i++){
			//Verif des couleurs
			if (i < 6){
				//Si le valeurs sont hors propos on les ajuste
				if((couleurCurseurSpecifique.isSelected() && i < 3) || (couleurDessinSpecifique.isSelected() && i < 6)){
					if (tabValeurs[i]<0){
						tabValeurs[i] = 0;
						erreurCouleur = true;
					}
					if (tabValeurs[i]>255){
						tabValeurs[i] = 255;
						erreurCouleur = true;
					}
				}
			}
			//Verif taille dessin
			else {
				if (tabValeurs[i]<20){
					tabValeurs[i] = 20;
					erreurTailleDessin = true;
				}
			}
		}
		
		//Si l'utilisateur a choisi une des valeurs prédéfinie, on rempli le tableau ici :
		//Pour les rouges
		if(couleurCurseurPredefinie.isSelected() || couleurDessinPredefinie.isSelected()){
			
			for(int n = 0; n < 2; n++){
				String couleur = "";
				int i = 0;
				
				if(couleurCurseurPredefinie.isSelected() && n ==0){
					couleur = (String)couleurPredefinieComboBox.getSelectedItem();
					i = 0;
				}
				if(couleurDessinPredefinie.isSelected() && n ==1){
					couleur = (String)couleurPredefinieDessinComboBox.getSelectedItem();
					i = 3;
				}
				
				if(couleur.equals("Noir")){
					tabValeurs[i    ] = 0;
					tabValeurs[i + 1] = 0;
					tabValeurs[i + 2] = 0;
				}
				if(couleur.equals("Bleu")){
					tabValeurs[i    ] = 0;
					tabValeurs[i + 1] = 0;
					tabValeurs[i + 2] = 255;
				}
				if(couleur.equals("Cyan")){
					tabValeurs[i    ] = 0;
					tabValeurs[i + 1] = 255;
					tabValeurs[i + 2] = 255;
				}
				if(couleur.equals("Gris")){
					tabValeurs[i    ] = 166;
					tabValeurs[i + 1] = 166;
					tabValeurs[i + 2] = 166;
				}
				if(couleur.equals("Vert")){
					tabValeurs[i    ] = 0;
					tabValeurs[i + 1] = 255;
					tabValeurs[i + 2] = 0;
				}
				if(couleur.equals("Magenta")){
					tabValeurs[i    ] = 255;
					tabValeurs[i + 1] = 0;
					tabValeurs[i + 2] = 255;
				}
				if(couleur.equals("Orange")){
					tabValeurs[i    ] = 255;
					tabValeurs[i + 1] = 127;
					tabValeurs[i + 2] = 0;
				}
				if(couleur.equals("Rose")){
					tabValeurs[i    ] = 255;
					tabValeurs[i + 1] = 0;
					tabValeurs[i + 2] = 127;
				}
				if(couleur.equals("Rouge")){
					tabValeurs[i    ] = 255;
					tabValeurs[i + 1] = 0;
					tabValeurs[i + 2] = 0;
				}
				if(couleur.equals("Jaune")){
					tabValeurs[i    ] = 255;
					tabValeurs[i + 1] = 255;
					tabValeurs[i + 2] = 102;
				}
				if(couleur.equals("Blanc")){
					tabValeurs[i    ] = 255;
					tabValeurs[i + 1] = 255;
					tabValeurs[i + 2] = 255;
				}
			}
		}
		
		//Petit panneau d'erreur		
		if (erreurCouleur || erreurTailleDessin){
			String stringErreur = "Attention :\n";
			
			if (erreurCouleur)
				stringErreur += "Un champ de couleur ne peux contenir qu'un nombre compris entre 0 et 255, des valeurs ont été ajustées";
			if (erreurCouleur && erreurTailleDessin )
				stringErreur += "\n";
			if (erreurTailleDessin)
				stringErreur += "La taille du dessin ne peut pas être inférieure à 20*20, les valeurs ont été ajustées";
			//Affichage du message d'erreur
			JOptionPane boiteErreur = new JOptionPane();
			boiteErreur.showMessageDialog(null, stringErreur, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		
		  /////////////////////////////////////////////////
		 //CHARGEMENT DES DONNEES DANS UN FICHIER CONFIG//
		/////////////////////////////////////////////////
		
		//On supprime le fichier .config/.config.txt si il existe
		File f = new File("../config/.config.txt");
		if(f.exists()) f.delete();
		
		//On crée un nouveau fichier avec les bonnes données
		DataOutputStream dos;
		try {
		      dos = new DataOutputStream(
		              new BufferedOutputStream(
		                new FileOutputStream(
		                  new File("../config/.config.txt"))));

		      //On écrit dans le fichier
		      //Données 1 : si true, la fenetre est en mode fenetré
		      if(affichageFenetre.isSelected()) dos.writeBoolean(true);
		      else dos.writeBoolean(false);
		      //Données 2 : si true, le curseur est centré
		      if(posCurseurCentreButton.isSelected()) dos.writeBoolean(true);
		      else dos.writeBoolean(false);
		      //Données 3 : valeur Red du curseur
		      dos.writeInt(tabValeurs[0]);
		      
		      //Données 4 : valeur Green du curseur
		      dos.writeInt(tabValeurs[1]);
		      
		      //Données 5 : valeur Blue du curseur
		      dos.writeInt(tabValeurs[2]);
		      
		      //Données 6 : Largeur du dessin
		      dos.writeInt(tabValeurs[6]);
		      
		      //Données 7 : Hauteur du dessin
		      dos.writeInt(tabValeurs[7]);
		      
		      //Données 8 : valeur Red du dessin
		      dos.writeInt(tabValeurs[3]);
		      
		      //Données 9 : valeur Green du dessin
		      dos.writeInt(tabValeurs[4]);
		      
		      //Données 10 : valeur Blue du dessin
		      dos.writeInt(tabValeurs[5]);
		      
		      //On ferme l'ecriture
		      dos.close();
		      
		      //Testons le tout
		      /*
		      DataInputStream dis;
		      dis = new DataInputStream(
		              new BufferedInputStream(
		                new FileInputStream(
		                  new File("config/.config.txt"))));
		            
		      System.out.println(dis.readBoolean());
		      System.out.println(dis.readBoolean());
		      System.out.println(dis.readInt());
		      System.out.println(dis.readInt());
		      System.out.println(dis.readInt());
		      System.out.println(dis.readInt());
		      System.out.println(dis.readInt());
		      System.out.println(dis.readInt());
		      System.out.println(dis.readInt());
		      System.out.println(dis.readInt());
		      */
		      
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	}

	  ////////////////////////////////////////////
	 //          ACCESSEURS
	////////////////////////////////////////////
	
	public static boolean getConfigProgrammeEstFenetre(){
		return configProgrammeEstFenetre;
	}
    public static boolean getConfigCurseurEstCentre(){
    	return configCurseurEstCentre;
    }
    public static int getConfigCurseurRed(){
    	return configCurseurRed;
    }
    public static int getConfigCurseurGreen(){
    	return configCurseurGreen;
    }
    public static int getConfigCurseurBlue(){
    	return configCurseurBlue;
    }
    public static int getConfigDessinLargeur(){
    	return configDessinLargeur;
    }
    public static int getConfigDessinHauteur(){
    	return configDessinHauteur;
    }
    public static int getConfigDessinBackgroundRed(){
    	return configDessinBackgroundRed;
    }
    public static int getConfigDessinBackgroundGreen(){
    	return configDessinBackgroundGreen;
    }
    public static int getConfigDessinBackgroundBlue(){
    	return configDessinBackgroundBlue;
    }
    
    
	  ////////////////////////////////////////////
	 //          MODIFIEURS
	////////////////////////////////////////////
	
	public static void setConfigProgrammeEstFenetre(boolean a){
		configProgrammeEstFenetre = a;
	}
	public static void setConfigCurseurEstCentre(boolean a){
		configCurseurEstCentre = a;
	}
	public static void setConfigCurseurRed(int a){
		configCurseurRed = a;
	}
	public static void setConfigCurseurGreen(int a){
		configCurseurGreen = a;
	}
	public static void setConfigCurseurBlue(int a){
		configCurseurBlue = a;
	}
	public static void setConfigDessinLargeur(int a){
		configDessinLargeur = a;
	}
	public static void setConfigDessinHauteur(int a){
		configDessinHauteur = a;
	}
	public static void setConfigDessinBackgroundRed(int a){
		configDessinBackgroundRed = a;
	}
	public static void setConfigDessinBackgroundGreen(int a){
		configDessinBackgroundGreen = a;
	}
	public static void setConfigDessinBackgroundBlue(int a){
		configDessinBackgroundBlue = a;
	}
}
