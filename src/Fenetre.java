import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Fenetre extends JFrame{
	BarreMenu barreMenu = new BarreMenu();//Barre de menu
	Terminal terminal = new Terminal();
	ZoneDessin zoneDessin; //L'objet est associé dans Fenetre pendant l'initialisation
	BarreOutils barreOutils;
	JPanel conteneurVertical = new JPanel();
	
    /**
     *  Constructeur de la fenetre
     */
	Fenetre(ZoneDessin zoneDessin, BarreOutils barreOutils){
		//Initialisation de la JFrame
		this.setTitle("Carapuce");
		
		if(MenuOption.getConfigProgrammeEstFenetre())
			this.setSize(1024, 600);
		else
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(1024, 600));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon("../img/icone.png" ).getImage());
		
		//Ajout de la barre de menu
		this.setJMenuBar(barreMenu);
		
		//Ajoute de la zone de dessin
		this.zoneDessin = zoneDessin;
		JScrollPane scrollPaneZoneDessin = new JScrollPane(zoneDessin);
		
		//Positionnement des sous fenetres
		conteneurVertical.setLayout(new BorderLayout());
		this.barreOutils = barreOutils; 
		conteneurVertical.add(barreOutils, BorderLayout.NORTH);
		conteneurVertical.add(terminal);
		
		Box conteneurPrincipal = Box.createHorizontalBox();
		//conteneurPrincipal.add(zoneDessin);
		conteneurPrincipal.add(scrollPaneZoneDessin);
		conteneurPrincipal.add(conteneurVertical);
		//Liaison au ContentPane
		this.getContentPane().add(conteneurPrincipal);

		//Affichage de la fenetre (ne pas placer avant)
		this.setVisible(true);
		
		//Resize temporaire des JPanel, a etudier comment obtenir des dimension absolue
		scrollPaneZoneDessin.setPreferredSize(new Dimension(this.getWidth()*2/3, 0));
		conteneurVertical.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()));
		conteneurVertical.setMaximumSize(new Dimension(this.getWidth()/3, 10000));
		
	}
	
    /**
     *  Retourne le terminal associee a la fenetre
     *  @return retourne le terminal
     */
    public Terminal getTerminal()
    {
        return this.terminal;
    }

    /**
     *  Retourne la zone de dessin associee a la fenetre
     *  @return retourne la zone de dessin
     */
    public ZoneDessin getZoneDessin()
    {
        return this.zoneDessin;
    }
    /**
     *  Assigne une zoneDessin à la fenetre
     */
    public void setZoneDessin(ZoneDessin d)
    {
        this.zoneDessin = d;
    }

    /**
     *  Retourne la zone de bouton associee a la fenetre
     *  @return retourne la zone de bouton
     */
    public BarreOutils getZoneBouton()
    {
        return this.barreOutils;
    }

    public BarreMenu getBarreMenu(){
    	return this.barreMenu;
    }
}
