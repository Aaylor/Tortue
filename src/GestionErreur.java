public class GestionErreur
{
    
    public static final int SUCCESS = 0;
    
    /*  Erreur au niveau de la commande */
    public static final int COMMANDE_ERRONEE = 100;

    /*  Erreur au niveau des paramètres */
    public static final int NOMBRE_PARAM_LESS = 200; 
    public static final int NOMBRE_PARAM_SUP = 201;
    public static final int PARAM_INCORRECTE = 202;
    public static final int COULEUR_INEXISTANTE = 203;
    public static final int REPEAT_PARAM_NON_VALIDE = 204;
   
    /*  Erreur Undo/Redo    */
    public static final int CANT_UNDO = 300;
    public static final int CANT_REDO = 301;

    /*  Erreur au niveau des pathnames  */
    public static final int DONT_MATCH = 400;
    public static final int CANT_CREATE = 401;
    public static final int CANT_READ = 403;
    public static final int NOT_FOUND = 404;
    

    /**
     *  Fonction qui envoie le message d'erreur au terminal
     *  @param numero_erreur numero de l'erreur
     *  @return boolean
     */
    public static String setMessageErreur(int numero_erreur)
    {
        String message = "   /!\\ Erreur";
        String param = StockageDonnee.getParamErreur();
        
        if ( !param.equals("") ) 
            message += param + " : ";
        
        message += StockageDonnee.getMessageErreur(numero_erreur);
    
        return message;
    }

}
