public class Main{

    /**
     * Fonction d'initialisation du programme
     */
    public static void init()
    {

    }

    /**
     *  Fonction de lancement du programme
     */
    public static void start_program()
    {

    	Fenetre fenetre = new Fenetre();
        Curseur curseur = new Curseur();
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
