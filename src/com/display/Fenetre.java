package com.display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import com.controleur.Controleur;
import com.term.Terminal;


@SuppressWarnings("serial")
public class Fenetre extends JFrame{
	BarreMenu barreMenu = new BarreMenu();//Barre de menu
	Terminal terminal;
	ZoneDessin zoneDessin; //L'objet est associé dans Fenetre pendant l'initialisation
	BarreOutils barreOutils;
	Controleur controleur;
    JPanel conteneurVertical = new JPanel();
	JScrollPane scrollPaneZoneDessin;
	
    /**
     *  Constructeur de la fenetre
     */
	public Fenetre(ZoneDessin zoneDessin, BarreOutils barreOutils, Terminal term){
        //Initialisation de la JFrame
		this.setTitle("Carapuce");
		//Taille et caractéristiques de la JFrame
		if(MenuOption.getConfigProgrammeEstFenetre())
			this.setSize(1024, 600);
		else
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(1024, 600));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//Icone
		this.setIconImage(new ImageIcon( new File(System.getProperty("user.dir")).getParent()
                    + File.separator + "img" + File.separator + "icone.png" ).getImage());
		
		//Ajout de la barre de menu
		this.setJMenuBar(barreMenu);
		
		//Ajoute de la zone de dessin
		this.zoneDessin = zoneDessin;
		scrollPaneZoneDessin = new JScrollPane(zoneDessin);
		
        this.terminal = term;

		//Positionnement des sous fenetres
		conteneurVertical.setLayout(new BorderLayout());
		this.barreOutils = barreOutils; 
		conteneurVertical.add(barreOutils, BorderLayout.NORTH);
        conteneurVertical.add(terminal);
		
		Box conteneurPrincipal = Box.createHorizontalBox();
		conteneurPrincipal.add(scrollPaneZoneDessin);
		conteneurPrincipal.add(conteneurVertical);
		
		//Liaison au ContentPane
		this.getContentPane().add(conteneurPrincipal);
		
  		//Definissons l'action lors du clic sur la croix rouge
		WindowListener exitListener = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                barreMenu.quitter();
            }
        };
        this.addWindowListener(exitListener);
	}
	
	  /////////////////////////////////////////////////
	 //                 METHODES                    //
	/////////////////////////////////////////////////	
	
	/**Fonction qui redimensionne tous les composant de la Fenetre pour garder de bonne proportions**/
	public void resizeEverything(){
		scrollPaneZoneDessin.setMinimumSize(new Dimension(this.getWidth()*2/3, 0));
		
		terminal.setMaximumSize(new Dimension(this.getWidth()/3, Short.MAX_VALUE));
		barreOutils.setMaximumSize(new Dimension(this.getWidth()/3, Short.MAX_VALUE));
		conteneurVertical.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()));
		conteneurVertical.setMaximumSize(new Dimension(this.getWidth()/3, Short.MAX_VALUE));
	}
	public void rendreVisible(){
		//Affichage de la fenetre (ne pas placer avant)
		resizeEverything();
  		this.setVisible(true);
	}
    public static void definirThemeNimbus(){
    	try {
	           UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	       }catch (Exception e) {}
 	 }
    public static void definirThemeSysteme(){
	 	try {
	           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	       }catch (Exception e) {}
  	 }
    public static void definirThemeMetal(){
    	try {
	           UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	       }catch (Exception e) {}
 	 }
	
	  /////////////////////////////////////////////////
	 //               ACCESSEURS                    //
	/////////////////////////////////////////////////
    
    /**
     *  Retourne le terminal associe a la fenetre
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
     *  Retourne la zone de bouton associee a la fenetre
     *  @return retourne la zone de bouton
     */
    public BarreOutils getZoneBouton()
    {
        return this.barreOutils;
    }
    /**
     *  Retourne la barre de menu associee a la fenetre
     *  @return retourne la barre de menu associee a la fenetre
     */
    public BarreMenu getBarreMenu(){
    	return this.barreMenu;
    }
    
	  /////////////////////////////////////////////////
	 //               MODIFIEURS                    //
	/////////////////////////////////////////////////
    
    /**
     *  Assigne une zoneDessin à la fenetre
     *  @param d : La zone de dessin à définir
     */
    public void setZoneDessin(ZoneDessin d)
    {
        this.zoneDessin = d;
    }
    /**
     * Assigne un controleur a la fenetre
     * @param c : Le controleur a definir
     */
    public void setControleur(Controleur c){
    	this.controleur = c;
    }

}
