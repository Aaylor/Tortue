import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;

public class StockageDonnee
{

    public static ArrayList<String> liste_commande_entree_correcte;
    public static ArrayList<String> liste_commande_entree_generale;
    public static ArrayList<Traceur> liste_dessin;
    public static Hashtable<String, Integer> liste_des_commandes;
    public static Hashtable<Integer, String> liste_erreurs;
    public static Hashtable<String, String> manuel;
    public static Hashtable<String, Color> liste_couleur;

    /**
     *  Fonction qui initialise toutes les collections
     *  @return boolean vérifiant l'initialisation de chacunes des collections
     */
    public static boolean init()
    {

        return init_lcec()  && init_lceg()
                            && init_ldessin()
                            && init_ldc()
                            && init_le()
                            && init_manuel();

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
     *  Fonction initialisant la collection des listes d'objet utilisé pour le dessin
     *  @return boolean pour l'initialisation
     */
    public static boolean init_ldessin()
    {

        liste_dessin = new ArrayList<Traceur>();
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
        liste_des_commandes.put("pencil", 2);
        liste_des_commandes.put("eraser", 3);
        liste_des_commandes.put("up", 4);
        liste_des_commandes.put("down", 5);
        liste_des_commandes.put("left", 6);
        liste_des_commandes.put("right", 7);
        liste_des_commandes.put("rotate", 8);
        liste_des_commandes.put("forward", 9);
        liste_des_commandes.put("backward", 10);
        liste_des_commandes.put("goto", 11);
        liste_des_commandes.put("cursorwidth", 12);
        liste_des_commandes.put("setcolor", 13);
        liste_des_commandes.put("setbackgroundcolor", 14);
        liste_des_commandes.put("do", 15);
        liste_des_commandes.put("width",16);
        liste_des_commandes.put("height", 17);
        liste_des_commandes.put("new", 18);
        liste_des_commandes.put("open", 19);
        liste_des_commandes.put("save", 20);
        liste_des_commandes.put("saveas", 21);
        liste_des_commandes.put("savehistory", 22);
        liste_des_commandes.put("exec", 23);
        liste_des_commandes.put("repeat", 24);
        liste_des_commandes.put("clear", 25);
        liste_des_commandes.put("help", 26);
        liste_des_commandes.put("man", 27);
        liste_des_commandes.put("function_debug_test", 28);
        
        return true;

    }

    /**
     *  Fonction initialisant la collection de message d'erreurs
     *  @return boolean vérifiant l'initialisation de la collection
     */
    public static boolean init_le()
    {

        liste_erreurs = new Hashtable<Integer, String>(); 

        liste_erreurs.put(-1, "");

        liste_erreurs.put(100, "la commande n'existe pas.");
        liste_erreurs.put(200, "nombre(s) d'argument(s) trop faible.");
        liste_erreurs.put(201, "nombre(s) d'argument(s) trop élevé.");
        liste_erreurs.put(202, "paramètre incorrect.");
        liste_erreurs.put(900, "l'historique à déjà été sauvegardé.");

        return true;

    }

    /**
     *  Fonction initialisant le manuel
     *  @return boolean vérifiant l'initialisation de la collection
     */
    public static boolean init_manuel()
    {

        manuel = new Hashtable<String, String>();

        manuel.put("pendown", "Manuel\n=====\nCommande : pendown\nSyntaxe : pendown, pas d'arguments possible"
                                + "\nRepose le curseur, et permet ainsi de pouvoir dessiner.");
        manuel.put("penup", "Manuel\n=====\nCommande : penup\nSyntaxe : penup, pas d'arguments possible"
                                + "\nLève le curseur, et l'empêche de dessiner");
        manuel.put("pencil", "Manuel\n=====\nCommande : pencil\nSyntaxe : pencil, pas d'arguments possible"
                                + "\nPasse en mode crayon : dessine selon la couleur et l'epaisseur courante.");
        manuel.put("eraser", "Manuel\n=====\nCommande : eraser\nSyntaxe : eraser, pas d'arguments possible"
                                + "\nPasse en mode gomme.");
        manuel.put("up", "Manuel\n=====\nCommande : up\nSyntaxe : up, pas d'arguments possible"
                                + "\nPlace le curseur vers le haut. Correspond à la commande \"rotate 180\"");
        manuel.put("down", "Manuel\n=====\nCommande : down\nSyntaxe : down, pas d'arguments possible"
                                + "\nPlace le curseur vers le bas. Correspond à la commande \"rotate 0\"");
        manuel.put("left", "Manuel\n=====\nCommande : left\nSyntaxe : left, pas d'arguments possible"
                                + "\nPlace le curseur vers la gauche. Correspond à la commande \"rotate 270\"");
        manuel.put("right", "Manuel\n=====\nCommande : right\nSyntaxe : right, pas d'arguments possible" 
                                + "\nPlace le curseur vers la doite. Correspond à la commande \"rotate 90\"");
        manuel.put("rotate", "Manuel\n=====\nCommande : rotate\nSyntaxe : rotate <angle en degré>. L'angle doit être obligatoirement précisé."
                                + "\nFait une rotation du curseur.");
        manuel.put("forward", "Manuel\n=====\nCommande : forward\nSyntaxe : forward <entier en pixel>. Doit être précisé."
                                + "\nFait avancer le curseur");
        manuel.put("backward", "Manuel\n=====\nCommande : backward\nSyntaxe : bacward <entier en pixel>. Doit être précisé."
                                + "\nFait reculer le curseur");
        manuel.put("goto", "Manuel\n=====\nCommande : goto\nSyntaxe : goto <int x><int y>. x & y doivent être précisés."
                                + "\nDéplace le curseur aux coordonnées x et y.");
        manuel.put("cursorwidth", "Manuel\n=====\nCommande : cursorwidth\nSyntaxe : cursorwidth <int width>. Doit être précisé."
                                + "\nFixe la largeur du curseur.");
        manuel.put("setcolor", "Manuel\n=====\nCommande : setcolor\nSyntaxe : setcolor <couleur>. Doit être précisé."
                                + "\nFixe la couleur du curseur.");
        manuel.put("setbackgroundcolor", "Manuel\n=====\nCommande : setbackgroundcolor\nSyntaxe : setbackgroundcolor <couleur>. "
                                + "\nFixe la couleur de fond.");
        manuel.put("do", "Manuel\n=====\nCommande : do\nSyntaxe : /* TODO */\n");
        manuel.put("width", "Manuel\n=====\nCommande : width\nSyntaxe : width <int width>"
                                + "\nFixe la largeur de la zone de dessin.");
        manuel.put("height", "Manuel\n=====\nCommande : height\nSyntaxe : height <int height>"
                                + "\nFixe la hauteur de la zone de dessin.");
        manuel.put("new", "Manuel\n=====\nCommande : new\nSyntaxe : /* TODO */\n");
        manuel.put("open", "Manuel\n=====\nCommande : open\nSyntaxe : /* TODO */\n");
        manuel.put("save", "Manuel\n=====\nCommande : save\nSyntaxe : /* TODO */\n");
        manuel.put("saveas", "Manuel\n=====\nCommande : saveas\nSyntaxe : /* TODO */\n");
        manuel.put("savehistory", "Manuel\n=====\nCommande : savehistory\nSyntaxe : /* TODO */\n");
        manuel.put("exec", "Manuel\n=====\nCommande : exec\nSyntaxe : /* TODO */\n");
        manuel.put("repeat", "Manuel\n======\nCommande : repeat\nSyntaxe : repeat, repeat <nombre_de_commande>"
                                + ", repeat <nombre_de_commande> <nombre_de_repetition>\n"
                                + "Repète les dernières actions de l'utilisateur. Si l'utilisateur n'entre aucun argument, alors la commande "
                                + "n'executera que la dernière actions. Si il rentre un seul argument, alors la commande executera les "
                                + "x dernières actions de l'utilisateur.");
        manuel.put("clear", "Manuel\n=====\nCommande : clear\nSyntaxe : clear, pas d'argument possible."
                                + "\nEfface le contenu du terminal. (Son historique est de tout même conservé)");
        manuel.put("help", "Manuel\n=====\nCommande : help\nSyntaxe : help, pas d'argument possible."
                                + "\nAffiche la liste des commandes possible, ainsi qu ela description du programme");
        manuel.put("man", "Manuel\n=====\nCommande : man\nSyntaxe : man <commande>"
                                + "\nAffiche le manuel de la commande");

        return true;
    }
    
    public static boolean init_liste_couleur(){
    	
    	liste_couleur.put("black", Color.black);
    	liste_couleur.put("blue", Color.blue);
    	liste_couleur.put("cyan", Color.cyan);
    	liste_couleur.put("light_gray", Color.lightGray);
    	liste_couleur.put("gray", Color.gray);
    	liste_couleur.put("dark_gray", Color.darkGray);
    	liste_couleur.put("green", Color.green);
    	liste_couleur.put("magenta" , Color.magenta );
    	liste_couleur.put("orange", Color.orange);
    	liste_couleur.put("pink", Color.pink);
    	liste_couleur.put("red", Color.red);
    	liste_couleur.put("yellow", Color.yellow);
    	liste_couleur.put("white", Color.white);
    	
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
     *  Fonction renvoyant le paramètre du message d'erreur
     *  @return String correspondant au paramètre
     */
    public static String getParamErreur()
    {
        String param = liste_erreurs.get(-1);
        liste_erreurs.put(-1,"");
        return param;
    }

    /**
     *  Fonction enregistrant un paramètre pour le message d'erreur
     *  @param param correspondant au paramètre
     */
    public static void setParamErreur(String param)
    {
        liste_erreurs.put(-1, param);
    }

    /**
     *  Fonction renvoyant l'entier correspondant à la commande
     *  @return l'entier de la commande entrée par l'utilisateur
     */
    public static int getNumeroFonction(String commande)
    {
        return ( liste_des_commandes.containsKey(commande) ) ? liste_des_commandes.get(commande) : -1;
    }

    /**
     *  Fonction renvoyant la commande entrée selon son numéro
     *  @return la commande
     */
    public static String getLCEC(int numero)
    {
        return liste_commande_entree_correcte.get( numero );
    }

    /**
     *  Fonction renvoyant la commande entrée selon son numéro
     *  @return la commande
     */
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

    /**
     *  Fonction renvoyant le manuel de la commande.
     *  @param commande Commande entré par l'utilisateur
     */
    public static String getManuel(String commande)
    {
        return manuel.get( commande );
    }
    
    
}
