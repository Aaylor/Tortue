import java.awt.*;
import javax.swing.*;

public class Fenetre extends JFrame{
	BarreMenu barreMenu = new BarreMenu();//Barre de menu
	Terminal terminal = new Terminal();
	ZoneDessin zoneDessin; //L'objet est associé dans Fenetre pendant l'initialisation
	BarreOutils barreOutils;

    /**
     *  Constructeur de la fenetre
     */
	Fenetre(ZoneDessin zoneDessin, BarreOutils barreOutils){
		//Initialisation de la JFrame
		this.setTitle("Carapuce");
		this.setSize(1024, 600);
		this.setMinimumSize(new Dimension(1024, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon("img/icone.png" ).getImage());
		
		//Ajout de la barre de menu
		this.setJMenuBar(barreMenu);
		
		//Ajoute de la zone de dessin
		this.zoneDessin = zoneDessin;
		
		//Positionnement des sous fenetres
		JPanel conteneurVertical = new JPanel();
		conteneurVertical.setLayout(new BorderLayout());
		this.barreOutils = barreOutils; 
		conteneurVertical.add(barreOutils, BorderLayout.NORTH);
		conteneurVertical.add(terminal);
		
		Box conteneurPrincipal = Box.createHorizontalBox();
		conteneurPrincipal.add(zoneDessin);
		conteneurPrincipal.add(conteneurVertical);
		//Liaison au ContentPane
		this.getContentPane().add(conteneurPrincipal);

		//Resize temporaire des JPanel, a etudier comment obtenir des dimension absolue
		zoneDessin.setPreferredSize(new Dimension(this.getWidth()*2/3, 0));
		conteneurVertical.setPreferredSize(new Dimension(this.getWidth()/3, 0));
		conteneurVertical.setMaximumSize(new Dimension(this.getWidth()/3, 10000));
		

		//Affichage de la fenetre (ne pas placer avant)
		this.setVisible(true);
		
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


}
