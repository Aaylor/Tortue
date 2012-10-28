import java.awt.Color;

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
    	int largeurDessin = 600;
    	int hauteurDessin = 600;
    	Curseur curseur = new Curseur(largeurDessin/2, hauteurDessin/2, 90, 1, Color.BLACK);
    	ZoneDessin zoneDessin = new ZoneDessin(largeurDessin,hauteurDessin, Color.WHITE, curseur);
    	Fenetre fenetre = new Fenetre(zoneDessin);
        
        Controleur c = new Controleur();
        c.___hydrate___(fenetre, curseur);

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
