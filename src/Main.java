import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Main{

    /**
     * Fonction d'initialisation du programme
     */
    public static void init()
    {

        if ( !StockageDonnee.init() )
            System.exit(-1);

        if ( !verifFichierConfig() );
        else;
        /* TODO */

    }

    /**
     *  Fonction de lancement du programme
     */
    public static void start_program()
    {
    	//Initialisation des parametres
    	int largeurDessin = MenuOption.getConfigDessinLargeur();
    	int hauteurDessin = MenuOption.getConfigDessinHauteur();
    	int posXCurseur;
    	int posYCurseur;
    	if (MenuOption.getConfigCurseurEstCentre()){
    		posXCurseur = largeurDessin/2;
        	posYCurseur = hauteurDessin/2;
    	}
    	else{
    		posXCurseur = 0;
    	    posYCurseur = 0;
    	}
    	Color couleurCurseur = new Color(MenuOption.getConfigCurseurRed(), MenuOption.getConfigCurseurGreen(), MenuOption.getConfigCurseurBlue());
    	Color couleurBackgroundDessin = new Color(MenuOption.getConfigDessinBackgroundRed(), MenuOption.getConfigDessinBackgroundGreen(), MenuOption.getConfigDessinBackgroundBlue());
    	
    	
    	//Initialisation des composants
    	
    	Curseur curseur = new Curseur(posXCurseur, posYCurseur, 90, couleurCurseur, (short)0, 30, (short)0);
    	ZoneDessin zoneDessin = new ZoneDessin(largeurDessin,hauteurDessin, couleurBackgroundDessin, curseur);
    	BarreOutils barreOutils = new BarreOutils(curseur, zoneDessin);
    	zoneDessin.setBarreOutils(barreOutils);
    	Fenetre fenetre = new Fenetre(zoneDessin, barreOutils);
        
    	Controleur c = new Controleur(fenetre, curseur);
        
    }

    /**
    * Fonction de verification du fichier du configuration
    * @return true si le fichier respecte les normes precisees dans la documentation
    */
    public static boolean verifFichierConfig(){
    	boolean recreerFichierConfig = false;
    	
    	//Si le dossier .config n'existe pas, on le créé
    	File dossier = new File( new File(System.getProperty("user.dir")).getParent()
                + File.separator + "config");
    	if(!dossier.exists()) dossier.mkdir();
    	
    	
    	//On crée le Fichier de config f et l'ecriveur w
		File f = new File( new File(System.getProperty("user.dir")).getParent() + File.separator
                + "config" + File.separator + ".config.txt");
		PrintWriter w;
		
		
    	//Verifions que le fichier de configuration existe
    	if(f.exists()){
    		//Le fichier existe
    		//1)Chargeons les données par défaut au cas ou toutes les lignes de configuration ne soient pas écrites
    		donneeParDefaut();
    		
    		//2)Chargeons les données présentes dans le fichier config
    		
    		
    	}
    	else{
    		recreerFichierConfig = true;
    	}
    	
    	//Le fichier config n'existe pas : on en créé un puis on charge les données par défaut
    	if(recreerFichierConfig){
    		String info;
    		if(!f.exists())
    			info = "Le fichier de configuration est introuvable, un nouveau contenant des valeurs par défaut a été créé";
    		else
    			info = "Une erreur est survenue lors du chargement du fichier du configuration, un nouveau contenant des valeurs par défauts a été créé";
    		JOptionPane.showMessageDialog(null, info, "Chargement du fichier config", JOptionPane.INFORMATION_MESSAGE);
    		
    		//Chargement des données par défaut
    		donneeParDefaut();
	    
			try {
				if (f.exists()) f.delete();
	            f.createNewFile();
	            
	            w = new PrintWriter(new BufferedWriter(new FileWriter(f)));
	            
	          //On écrit dans le fichier
	    		//Données 1 : Mode plein ecran
	    		w.println("full screen=false");
	    		//Données 2 : si true, le curseur est centré
	    		w.println("cursor at the center=true");
	    		//Données 3 : valeur Red du curseur
	    		w.println("cursor red=0");
	    		//Données 4 : valeur Green du curseur
	    		w.println("cursor green=0");
	    		//Données 5 : valeur Blue du curseur
	    		w.println("cursor blue=0");
	    		//Données 6 : Largeur du dessin
	    		w.println("picture width=500");
	    		//Données 7 : Hauteur du dessin
	    		w.println("picture width=500");
	    		//Données 8 : valeur Red du dessin
	    		w.println("background color=255");
	    		//Données 9 : valeur Green du dessin
	    		w.println("background color=255");
	    		//Données 10 : valeur Blue du dessin
	    		w.println("background color=255");
	    		//On écrit le tampon
	    		w.flush();
	    		//On ferme l'ecriture
	    		w.close();
				}
			catch (IOException e) {
				System.out.println("Probleme de E/S");
				System.exit(-1);
			}
    	}
    	
    	
        return true;

    }
    
    public static void donneeParDefaut(){
    	//Chargement des données par défaut
	    //Données 1 : si true, la fenetre est en mode fenetré
	    MenuOption.setConfigProgrammeEstFenetre(true);
	    //Données 2 : si true, le curseur est centré
	    MenuOption.setConfigCurseurEstCentre(true);
	    //Données 3 : valeur Red du curseur
	    MenuOption.setConfigCurseurRed(0);
	    //Données 4 : valeur Green du curseur
	    MenuOption.setConfigCurseurGreen(0);
	    //Données 5 : valeur Blue du curseur
	    MenuOption.setConfigCurseurBlue(0);
	    //Données 6 : Largeur du dessin
	    MenuOption.setConfigDessinLargeur(500);
	    //Données 7 : Hauteur du dessin
	    MenuOption.setConfigDessinHauteur(500);
	    //Données 8 : valeur Red du dessin
	    MenuOption.setConfigDessinBackgroundRed(255);
	    //Données 9 : valeur Green du dessin
	    MenuOption.setConfigDessinBackgroundGreen(255);
	    //Données 10 : valeur Blue du dessin
	    MenuOption.setConfigDessinBackgroundBlue(255);
    }
    
    /**
     *  Fonction main
     *  @param args     Parametres des lignes de commandes
     */
    public static void main(String[] args)
    {

        init();
        start_program();

    }

}
