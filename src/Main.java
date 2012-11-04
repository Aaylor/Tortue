import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main{

    /**
     * Fonction d'initialisation du programme
     */
    public static void init()
    {

        if ( !StockageDonnee.init() )
            System.exit(1);

        if ( !verifFichierConfig() );
        else;
        /* TODO */

    }

    /**
     *  Fonction de lancement du programme
     */
    public static void start_program()
    {
    	int largeurDessin = 400;
    	int hauteurDessin = 400;
    	Curseur curseur = new Curseur(largeurDessin/2, hauteurDessin/2, 90, 1, Color.BLACK, 0);
    	ZoneDessin zoneDessin = new ZoneDessin(largeurDessin,hauteurDessin, Color.WHITE, curseur);
    	zoneDessin.repaint();
    	BarreOutils barreOutils = new BarreOutils(curseur, zoneDessin);
    	Fenetre fenetre = new Fenetre(zoneDessin, barreOutils);
        
        Controleur c = new Controleur();
        c.___hydrate___(fenetre, curseur);

        
        //TEMPORAIRE JUSTE POUR TESTER LE PROGRAMME
        while(true){
        	try{
        		  Thread.sleep(1000); //Ici, une pause d'une seconde
        		  fenetre.zoneDessin.repaint();
        		}catch(InterruptedException e) {
        		  e.printStackTrace();
        		}
        }
        
    }

    /**
    * Fonction de verification du fichier du configuration
    * @return true si le fichier respecte les normes precisees dans la documentation
    */
    public static boolean verifFichierConfig(){
    	//Si le dossier .config n'existe pas, on le créé
    	File dossier = new File("config");
    	if(!dossier.exists()) dossier.mkdir();
    	
    	
    	File f = new File("config/.config.txt");   
    	//Verifions que le fichier de configuration existe
    	if(f.exists()){
    		
    	}
    	//Le fichier config n'existe pas : on en informe l'utilisateur et on en créé un
    	else{
    		try{
	    		DataOutputStream dos;
	    		
	    		dos = new DataOutputStream(
			              new BufferedOutputStream(
			                new FileOutputStream(
			                  new File("config/.config.txt"))));
	
			      //On écrit dans le fichier
			      //Données 1 : si true, la fenetre est en mode fenetré
			      dos.writeBoolean(true);
			      //Données 2 : si true, le curseur est centré
			      dos.writeBoolean(true);
			      //Données 3 : valeur Red du curseur
			      dos.writeInt(255);
			      //Données 4 : valeur Green du curseur
			      dos.writeInt(255);
			      //Données 5 : valeur Blue du curseur
			      dos.writeInt(255);
			      //Données 6 : Largeur du dessin
			      dos.writeInt(300);
			      //Données 7 : Hauteur du dessin
			      dos.writeInt(300);
			      //Données 8 : valeur Red du dessin
			      dos.writeInt(0);
			      //Données 9 : valeur Green du dessin
			      dos.writeInt(0);
			      //Données 10 : valeur Blue du dessin
			      dos.writeInt(0);
			      //On ferme l'ecriture
			      dos.close();
		    } catch (IOException e) {
			      e.printStackTrace();
			}
    	}
    	
    	
        return true;

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
