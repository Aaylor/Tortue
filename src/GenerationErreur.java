public class GenerationErreur{

    /**
     *  Fonction qui renvoie le message d'erreur correspondat à son numéro
     *  @param numero_erreur
     *  @return le message d'erreur
     */
    public static String genererErreur(int numero_erreur)
    {

        String message_erreur = "Erreur " + numero_erreur + " : ";

        if ( numero_erreur == 1 )
        {
            return message_erreur + "la commande n'existe pas.";
        }

        return "";

    }

}

