public class GenerationErreur{

    /**
     *  Fonction qui renvoie le message d'erreur correspondat à son numéro
     *  @param numero_erreur
     *  @return le message d'erreur
     */
    public static String genererErreur(int numero_erreur)
    {

        String message_erreur = "Erreur " + numero_erreur + " : ";

        return "Erreur " + numero_erreur + " : "
            + StockageDonnee.getMessageErreur( numero_erreur );

    }

}

