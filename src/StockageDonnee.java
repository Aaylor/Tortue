import java.util.ArrayList;
import java.util.Hashtable;

public class StockageDonnee
{

    public static ArrayList<String> liste_commande_entree_correcte;
    public static ArrayList<String> liste_commande_entree_generale;
    public static Hashtable<String, Integer> liste_des_commandes;
    public static Hashtable<Integer, String> liste_erreurs;

    /**
     *  Fonction qui initialise toutes les collections
     *  @return boolean vérifiant l'initialisation de chacunes des collections
     */
    public static boolean init()
    {

        return init_lcec() && init_lceg()
                            && init_ldc()
                            && init_le();

    }

    /**
     *  Fonction initialisant la collection des listes de commandes correctes entrées par l'utilisateur
     *  Il servira pour la création d'un fichier historique effectuer lors de la demande de l'utilisateur
     *  @return boolean vérifiant l'initialisation de la collection
     */
    public static boolean init_lcec()
    {

        liste_commande_entree_correcte = new ArrayList<String>();
        return true;

    }

    /**
     *  Fonction initialisant la collection des listes de commandes générale (vraies ou fausses) entrées
     *  par l'utilisateur.
     *  @return boolean vérifiant l'initialisation de la collection
     */
    public static boolean init_lceg()
    {

        liste_commande_entree_generale = new ArrayList<String>();
        return true;

    }

    /**
     *  Fonction initialisant la collection de la liste des commandes disponnible pour l'utilisateur
     *  @return boolean vérifiant l'initialisation de la collection
     */
    public static boolean init_ldc()
    {

        liste_des_commandes = new Hashtable<String, Integer>();

        liste_des_commandes.put("pendown", 0);
        liste_des_commandes.put("penup", 1);
        liste_des_commandes.put("eraser", 2);
        liste_des_commandes.put("up", 3);
        liste_des_commandes.put("down", 4);
        liste_des_commandes.put("left", 5);
        liste_des_commandes.put("right", 6);
        liste_des_commandes.put("rotate", 7);
        liste_des_commandes.put("forward", 8);
        liste_des_commandes.put("backward", 9);
        liste_des_commandes.put("goto", 10);
        liste_des_commandes.put("cursoswidth", 11);
        liste_des_commandes.put("setcolor", 12);
        liste_des_commandes.put("setbackgroundcolor", 13);
        liste_des_commandes.put("do", 14);
        liste_des_commandes.put("width",15);
        liste_des_commandes.put("height", 16);
        liste_des_commandes.put("new", 17);
        liste_des_commandes.put("open", 18);
        liste_des_commandes.put("save", 19);
        liste_des_commandes.put("saveas", 20);
        liste_des_commandes.put("savehistory", 21);
        liste_des_commandes.put("exec", 22);
        liste_des_commandes.put("repeat", 23);
        liste_des_commandes.put("clear", 24);
        liste_des_commandes.put("help", 25);
        liste_des_commandes.put("man", 26);
        liste_des_commandes.put("function_debug_test", 27);
        
        return true;

    }

    /**
     *  Fonction initialisant la collection de message d'erreurs
     *  @return boolean vérifiant l'initialisation de la collection
     */
    public static boolean init_le()
    {

        liste_erreurs = new Hashtable<Integer, String>(); 

        liste_erreurs.put(1, "la commande n'existe pas");
        liste_erreurs.put(2, "argument en trop");

        return true;

    }

    /**
    *   Fonction renvoyant le message d'erreur
    *   @param numero Numero de l'erreur
    *   @return String correspondant au message d'erreur
    */
    public static String getMessageErreur(int numero)
    {
        return liste_erreurs.get( numero );       
    }

    /**
     *  Fonction renvoyant l'entier correspondant à la commande
     *  @return l'entier de la commande entrée par l'utilisateur
     */
    public static int getNumeroFonction(String commande)
    {
        return ( liste_des_commandes.containsKey(commande) ) ? liste_des_commandes.get(commande) : -1;
    }

    public static String getLCEC(int numero)
    {
        return liste_commande_entree_correcte.get( numero );
    }

    public static String getLCEG(int numero)
    {
        return liste_commande_entree_generale.get( numero );
    }
    /**
     *  Fonction ajoutant la commande à la collection correspondante
     *  @param commande Commande entrée par l'utilisateur
     */
    public static boolean ajoutLCEC(String commande)
    {
        liste_commande_entree_correcte.add(commande);
        return true;
    }

    /**
     *  Fonction ajoutant la commande à la collection correspondante
     *  @param commande Commande entréé par l'utilisateur
     */
    public static boolean ajoutLCEG(String commande)
    {
        liste_commande_entree_generale.add(commande);
        return true;
    }

    /**
     *  Fonction renvoyant la taille de la collection
     *  @return taille
     */
    public static int getSize_LCEG()
    {
        return liste_commande_entree_generale.size();
    }

    /**
     *  Fonction renvoyant la taille de la collection
     *  @return taille
     */
    public static int getSize_LCEC()
    {
        return liste_commande_entree_correcte.size();
    }

}
