import java.awt.Color;
import java.awt.Graphics;

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
