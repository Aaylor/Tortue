import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BarreMenu extends JMenuBar{
	//JMenu "Fichier"
	JMenu menuFichier = new JMenu("Fichier");
	JMenuItem nouveau = new JMenuItem("Nouveau");
	JMenuItem enregistrer = new JMenuItem("Enregistrer");
    JMenuItem enregistrerSous = new JMenuItem("Enregistrer sous");
    JMenuItem quitter = new JMenuItem("Quitter");
    
    //JMenu "Outils"
	JMenu menuOutils = new JMenu("Outils");
	JMenuItem options = new JMenuItem("Options");
	JMenuItem aPropos = new JMenuItem("A propos");
	System.out.println'();	
    /**
     *  Constructeur
     */
	BarreMenu(){
		super();
		
		//Menu "Fichier"
		this.add(menuFichier);
		menuFichier.add(nouveau);
        menuFichier.add(enregistrer);
        menuFichier.add(enregistrerSous);
		menuFichier.add(quitter);
		
		//Menu "Outils"
		this.add(menuOutils);
		menuOutils.add(options);
		menuOutils.add(aPropos);
		
		
		//Ajout des Action Listener
		nouveau.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				nouveau();
			}
		});
		enregistrer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				enregistrer();
			}
		});
		enregistrerSous.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				enregistrerSous();
			}
		});
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				quitter();
			}
		});
		options.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				options();
			}
		});
		aPropos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				aPropos();
			}
		});
		
	}
	
	public void nouveau(){}
	
	public void enregistrer(){}
	
	public void enregistrerSous(){}
	
	public void quitter(){
		JOptionPane quitter = new JOptionPane();
		int option = quitter.showConfirmDialog(null, "Voulez vous sauvegarder votre travail avant de fermer le programme ?", "Sauvegarder", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.OK_OPTION){
			enregistrer();
			System.exit(0);
		}
		if (option == JOptionPane.NO_OPTION){
			System.exit(0);
		}
		
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

	public void options(){}
	
}
