import javax.swing.*;

import java.awt.event.*;

public class MenuOption extends JDialog{
	JRadioButton affichageFenetre = new JRadioButton("Fenêtré");
	JRadioButton affichagePleinEcran = new JRadioButton("Plein écran");
	
	
	public MenuOption(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(550, 270);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		initComponent();
		this.setVisible(true);
		
		
	}
	
	private void initComponent(){
		//Affichage
		JPanel panAffichage = new JPanel();
		panAffichage.setBorder(BorderFactory.createTitledBorder("Mode d'affichage au démarrage"));
		ButtonGroup affichage = new ButtonGroup();
		affichage.add(affichageFenetre);
		affichage.add(affichagePleinEcran);
		panAffichage.add(affichageFenetre);
		panAffichage.add(affichagePleinEcran);
		
		
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
		content.add(EnregistrerAnnuler);
		
		//Affichage
		this.getContentPane().add(content);
	}
}
