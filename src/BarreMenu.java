import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
		aPropos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				aPropos();
			}
		});
		
	}
	
	public void aPropos(){
		JOptionPane aPropos = new JOptionPane();
		aPropos.showMessageDialog(null,
								"Carapuce est un projet Universitaire développé par 4 petits chenapans\n\n" +
								"Mehdi Khelifi : Interface Graphiques\n" +
								"Loïc Runarvot : Terminal\n" +
								"Gauthier Lo : Commandes\n" +
								"Frederic Mamath : Commandes",
								"A propos",
								JOptionPane.INFORMATION_MESSAGE);
	}
	
	
}
