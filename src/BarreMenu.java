import javax.swing.*;
import java.awt.*;


public class BarreMenu extends JMenuBar{
	//JMenu "Fichier"
	JMenu menuFichier = new JMenu("Fichier");
	JMenuItem fichierNouveau = new JMenuItem("Nouveau");
	JMenuItem fichierEnregistrer = new JMenuItem("Enregistrer");
    JMenuItem fichierEnregistrerSous = new JMenuItem("Enregistrer sous");
    JMenuItem fichierQuitter = new JMenuItem("Quitter");
    
    //JMenu "Outils"
	JMenu menuOutils = new JMenu("Outils");
	JMenuItem options = new JMenuItem("Options");
	JMenuItem aPropos = new JMenuItem("A propos");
	
    /**
     *  Constructeur
     */
	BarreMenu(){
		super();
		
		this.add(menuFichier);
		menuFichier.add(fichierNouveau);
        menuFichier.add(fichierEnregistrer);
        menuFichier.add(fichierEnregistrerSous);
		menuFichier.add(fichierQuitter);
		
		this.add(menuOutils);
		menuOutils.add(options);
		menuOutils.add(aPropos);
		
	}
}
