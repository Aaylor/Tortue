import javax.swing.*;
import java.awt.*;


public class BarreMenu extends JMenuBar{
	JMenu menuFichier = new JMenu("Fichier");
	JMenuItem fichierNouveau = new JMenuItem("Nouveau");
	JMenuItem fichierQuitter = new JMenuItem("Quitter");
	
	
	BarreMenu(){
		super();
		
		this.add(menuFichier);
		menuFichier.add(fichierNouveau);
		menuFichier.add(fichierQuitter);
		
	}
}
