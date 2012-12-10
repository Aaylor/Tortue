import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.controleur.Controleur;
import com.display.*;
import com.error.*;
import com.stockage.StockageDonnee;
import com.term.Terminal;
import com.utilitary.*;


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
    	int epaisseurCurseur = MenuOption.getConfigCurseurEpaisseur();
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
    	
    	Terminal term = new Terminal();
    	Curseur curseur = new Curseur(posXCurseur, posYCurseur, 90, couleurCurseur, (short)0, epaisseurCurseur, (short)0);
    	ZoneDessin zoneDessin = new ZoneDessin(largeurDessin,hauteurDessin, couleurBackgroundDessin, curseur);
    	BarreOutils barreOutils = new BarreOutils(curseur, zoneDessin);
    	zoneDessin.setBarreOutils(barreOutils);
    	Fenetre fenetre = new Fenetre(zoneDessin, barreOutils, term);
    	Controleur c = new Controleur(fenetre, curseur);
    	
        fenetre.rendreVisible();
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
    		//lecture du fichier texte	
    		try{
    			//Initialisation des composants necessaires a la lecture du fichier
    			InputStream ips=new FileInputStream(f); 
    			InputStreamReader ipsr=new InputStreamReader(ips);
    			BufferedReader br=new BufferedReader(ipsr);
    			String ligne;
    			
    			//Traitement des lignes
    			while ((ligne=br.readLine())!=null){
    				//On met la ligne en miniscule et on enleve les espace, au cas ou
    				ligne = ligne.toLowerCase();
    				ligne = ligne.trim();
    				ligne = ligne.replaceAll(" ", ""); 
    				
    				//Plein ecran ou fenetre
    				if(ligne.startsWith("fullscreen=")){
    					if(ligne.endsWith("true"))
    						MenuOption.setConfigProgrammeEstFenetre(false);
    					if(ligne.endsWith("false"))
    						MenuOption.setConfigProgrammeEstFenetre(true);
    				}
    				else if(ligne.startsWith("cursoratthecenter=")){
    					if(ligne.endsWith("true"))
    						MenuOption.setConfigCurseurEstCentre(true);
    					if(ligne.endsWith("false"))
    						MenuOption.setConfigCurseurEstCentre(false);
    				}
    				else if(ligne.startsWith("cursorred=")){
    					String valeur = ligne.substring(10);
    					if(!valeur.isEmpty() && isNumeric(valeur)){
    						if(stringToInt(valeur)>=0 && stringToInt(valeur)<256)
    							MenuOption.setConfigCurseurRed(stringToInt(valeur));
    					}
    				}
    				else if(ligne.startsWith("cursorgreen=")){
    					String valeur = ligne.substring(12);
    					if(!valeur.isEmpty() && isNumeric(valeur)){
    						if(stringToInt(valeur)>=0 && stringToInt(valeur)<256)
								MenuOption.setConfigCurseurGreen(stringToInt(valeur));
    					}
    				}
    				else if(ligne.startsWith("cursorblue=")){
    					String valeur = ligne.substring(11);
    					if(!valeur.isEmpty() && isNumeric(valeur)){
    						if(stringToInt(valeur)>=0 && stringToInt(valeur)<256)
    							MenuOption.setConfigCurseurBlue(stringToInt(valeur));
    					}
    				}
    				else if(ligne.startsWith("picturewidth=")){
    					String valeur = ligne.substring(13);
    					if(!valeur.isEmpty() && isNumeric(valeur)){
    						if(stringToInt(valeur)>=50)
    							MenuOption.setConfigDessinLargeur(stringToInt(valeur));
    					}
    				}
    				else if(ligne.startsWith("pictureheight=")){
    					String valeur = ligne.substring(14);
    					if(!valeur.isEmpty() && isNumeric(valeur)){
    						if(stringToInt(valeur)>=50)
    							MenuOption.setConfigDessinHauteur(stringToInt(valeur));
    					}
    				}
    				else if(ligne.startsWith("backgroundcolorred=")){
    					String valeur = ligne.substring(19);
    					if(!valeur.isEmpty() && isNumeric(valeur)){
    						if(stringToInt(valeur)>=0 && stringToInt(valeur)<256)
    							MenuOption.setConfigDessinBackgroundRed(stringToInt(valeur));
    					}
    				}
    				else if(ligne.startsWith("backgroundcolorgreen=")){
    					String valeur = ligne.substring(21);
    					if(!valeur.isEmpty() && isNumeric(valeur)){
    						if(stringToInt(valeur)>=0 && stringToInt(valeur)<256)
    							MenuOption.setConfigDessinBackgroundGreen(stringToInt(valeur));
    					}
    				}
    				else if(ligne.startsWith("backgroundcolorblue=")){
    					String valeur = ligne.substring(20);
    					if(!valeur.isEmpty() && isNumeric(valeur)){
    						if(stringToInt(valeur)>=0 && stringToInt(valeur)<256)
    							MenuOption.setConfigDessinBackgroundBlue(stringToInt(valeur));
    					}
    				}
    				else if(ligne.startsWith("cursorwidth=")){
    					String valeur = ligne.substring(12);
    					if(!valeur.isEmpty() && isNumeric(valeur)){
    						if(stringToInt(valeur)>=1 && stringToInt(valeur)<=500)
    							MenuOption.setConfigCurseurEpaisseur(stringToInt(valeur));
    					}
    				}
    				else if(ligne.startsWith("design=")){
    					if(ligne.endsWith("system")){
    						MenuOption.setConfigTheme(0);
    						Fenetre.definirThemeSysteme();
    					}
    					if(ligne.endsWith("nimbus")){
    						MenuOption.setConfigTheme(1);
    						Fenetre.definirThemeNimbus();
    					}
    					if(ligne.endsWith("metal")){
    						MenuOption.setConfigTheme(2);
    						Fenetre.definirThemeMetal();
    					}
    				}
    			}
    			br.close(); 
    		}		
    		catch (Exception e){
    			System.out.println(e.toString());
    		}
    		
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
	    		//Données 1bis : Theme sous windows
	    		w.println("design=system");
	    		//Données 2 : si true, le curseur est centré
	    		w.println("cursor at the center=true");
	    		//Données 3 : valeur Red du curseur
	    		w.println("cursor red=0");
	    		//Données 4 : valeur Green du curseur
	    		w.println("cursor green=0");
	    		//Données 5 : valeur Blue du curseur
	    		w.println("cursor blue=0");
	    		//Données 5 bis : L'epaisseur du curseur
	    		w.println("cursor width=15");
	    		//Données 6 : Largeur du dessin
	    		w.println("picture width=500");
	    		//Données 7 : Hauteur du dessin
	    		w.println("picture height=500");
	    		//Données 8 : valeur Red du dessin
	    		w.println("background color red=255");
	    		//Données 9 : valeur Green du dessin
	    		w.println("background color green=255");
	    		//Données 10 : valeur Blue du dessin
	    		w.println("background color blue=255");
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
	    //Données 1bis : 0: Thème fourni par le systeme
	    MenuOption.setConfigTheme(0);
	    //Données 2 : si true, le curseur est centré
	    MenuOption.setConfigCurseurEstCentre(true);
	    //Données 3 : valeur Red du curseur
	    MenuOption.setConfigCurseurRed(0);
	    //Données 4 : valeur Green du curseur
	    MenuOption.setConfigCurseurGreen(0);
	    //Données 5 : valeur Blue du curseur
	    MenuOption.setConfigCurseurBlue(0);
	    //Données 5 bis : L'épaisseur du curseur
	    MenuOption.setConfigCurseurEpaisseur(15);
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
    
    public static boolean isNumeric(String str)  
    {  
      try  
      {  
        double d = Double.parseDouble(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
    
    public static int stringToInt(String s) 
    { 
    Integer ger = new Integer(s); 
    int i = ger.intValue(); 
    return i; 
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
