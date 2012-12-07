public class GestionErreur
{
    
    public static final int SUCCESS = 0;
    public static final int COMMANDE_ERRONEE = 100;
    public static final int NOMBRE_PARAM_LESS = 200; 
    public static final int NOMBRE_PARAM_SUP = 201;
    public static final int PARAM_INCORRECTE = 202;
    public static final int IMAGE_INEXISTANTE = 203;
    public static final int COULEUR_INEXISTANTE = 204;
    public static final int REPEAT_PARAM_NON_VALIDE = 205;
    
    /**
     *  Fonction qui envoie le message d'erreur au terminal
     *  @param numero_erreur numero de l'erreur
     *  @return boolean
     */
    public static String setMessageErreur(int numero_erreur)
    {
        String message = "   /!\\ Erreur : ";
        String param = StockageDonnee.getParamErreur();
        
        if ( !param.equals("") ) 
            message += param + " : ";
        
        message += StockageDonnee.getMessageErreur(numero_erreur);
    
        return message;
    }

}
