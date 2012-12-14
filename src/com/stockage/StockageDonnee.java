package com.stockage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Enumeration;

import javax.swing.JTextArea;

import com.controleur.Controleur;
import com.display.*;
import com.error.*;
import com.stockage.StockageDonnee;
import com.term.Terminal;
import com.utilitary.*;

public class StockageDonnee
{

    private static ArrayList<String> liste_commande_entree_correcte;
    private static ArrayList<String> liste_commande_undo;
    private static ArrayList<String> liste_commande_entree_generale;
    private static ArrayList<Traceur> liste_dessin;
    public static ArrayList<String> tmp_command;
    private static Hashtable<String, Integer> liste_des_commandes;
    private static Hashtable<Integer, String> liste_erreurs;
    private static Hashtable<String, String> manuel;
    private static Hashtable<String, Color> liste_couleur;
    
    
    private static String pathname_save = new String("");
    private static boolean image_save = true;
    private static boolean has_undone = false;

    private static final String color_list_html = getColorListHtml(); 
    private static String liste_commandes_valide_repeat;

    /**
     *  Fonction qui initialise toutes les collections
     *  @return boolean vérifiant l'initialisation de chacunes des collections
     */
    public static boolean init()
    {

        return init_lcec()  && init_lcu()
                            && init_lceg()
                            && init_ldessin()
                            && init_tmp_cmd()
                            && init_ldc()
                            && init_le()
                            && init_manuel()
                            && init_liste_couleur();

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
     *  Fonction initialisant la collection des listes de commandes "undo" par l'utilisateur
     *  @return boolean vérifiant l'initialisation de la collection
     */
    public static boolean init_lcu()
    {
        liste_commande_undo = new ArrayList<String>();
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
     *  Fonction initialisant la collection des listes d'objet utilisé pour la mémoire tampon
     *  @return boolean
     */
    public static boolean init_tmp_cmd()
    {
        tmp_command = new ArrayList<String>();
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
        liste_des_commandes.put("shape", 4);
        liste_des_commandes.put("up", 5);
        liste_des_commandes.put("down", 6);
        liste_des_commandes.put("left", 7);
        liste_des_commandes.put("right", 8);
        liste_des_commandes.put("center", 9);
        liste_des_commandes.put("rotate", 10);
        liste_des_commandes.put("undo", 11);
        liste_des_commandes.put("redo", 12);
        liste_des_commandes.put("forward", 13);
        liste_des_commandes.put("backward", 14);
        liste_des_commandes.put("goto", 15);
        liste_des_commandes.put("cursorwidth", 16);
        liste_des_commandes.put("setcolor", 17);
        liste_des_commandes.put("setbackgroundcolor", 18);
        liste_des_commandes.put("do", 19);
        liste_des_commandes.put("width", 20);
        liste_des_commandes.put("height", 21);
        liste_des_commandes.put("grid", 22);
        liste_des_commandes.put("disablegrid", 23);
        liste_des_commandes.put("pixelart", 24);
        liste_des_commandes.put("new", 25);
        liste_des_commandes.put("open", 26);
        liste_des_commandes.put("save", 27);
        liste_des_commandes.put("saveas", 28);
        liste_des_commandes.put("savehistory", 29);
        liste_des_commandes.put("exec", 30);
        liste_des_commandes.put("repeat", 31);
        liste_des_commandes.put("clear", 32);
        liste_des_commandes.put("help", 33);
        liste_des_commandes.put("man", 34);
        liste_des_commandes.put("exit", 35);
        
        liste_commandes_valide_repeat = getValidCommandRepeat();
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
        liste_erreurs.put(203, "couleur inexsitante.");
        liste_erreurs.put(204, "le paramètre ne peut être utilisé dans la fonction repeat.");
        liste_erreurs.put(205, "la commande recherchée n'existe pas.");
        liste_erreurs.put(300, "aucun retour en arrière possible.");
        liste_erreurs.put(301, "aucune commandes à refaire");
        liste_erreurs.put(400, "le fichier ne possède pas une extension correcte.");
        liste_erreurs.put(401, "impossible de créer le fichier.");
        liste_erreurs.put(403, "le fichier ne peut être lu.");
        liste_erreurs.put(404, "impossible de trouver le fichier.");

        return true;

    }

    /**
     *  Fonction initialisant le manuel
     *  @return boolean vérifiant l'initialisation de la collection
     */
    public static boolean init_manuel()
    {

        manuel = new Hashtable<String, String>();

        manuel.put("pendown", "<div id=\"syntax\">Syntaxe : <ul><li>pendown <i>pas d'arguments possible</i></li></ul></div>"
                                + "<br />Pose l'outil et permet ainsi de dessiner.");
        manuel.put("penup", "<div id=\"syntax\">Syntaxe : &nbsp;<ul><li>penup, <i>pas d'arguments possible</i></li></ul></div>"
                                + "<br />Lève l'outil et permet ainsi de ne pas dessiner.");
        manuel.put("pencil", "<div id=\"syntax\">Syntaxe : <ul><li>pencil, <i>pas d'arguments possible</i></li></ul></div>"
                                + "<br />Passe en mode crayon : dessine selon la couleur et l'epaisseur courante.");
        manuel.put("eraser", "<div id=\"syntax\">Syntaxe : <ul><li>eraser, <i>pas d'arguments possible</i></li></ul></div>"
                                + "<br />Passe en mode gomme : efface selon la couleur de fond.");
        manuel.put("shape", "Syntaxe : <ul><li>shape, <i>pas d'arguments possible</i></li></ul>"
                                + "<br />Change la forme de l'outil ( soit carré, soit rond ).");
        manuel.put("up", "Syntaxe : <ul><li>up, <i>pas d'arguments possible<i></li></ul>"
                                + "<br />Place le curseur vers le haut. Correspond à la commande <i><b>\"rotate 90\"</b></i>");
        manuel.put("down", "Syntaxe : <ul><li>down, <i>pas d'arguments possible</i></li></ul>"
                                + "<br />Place le curseur vers le bas. Correspond à la commande <i><b>\"rotate 270\"</b></i>");
        manuel.put("left", "Syntaxe : <ul><li>left, <i>pas d'arguments possible</i></li></ul>"
                                + "<br />Place le curseur vers la gauche. Correspond à la commande <i><b>\"rotate 180\"</b></i>");
        manuel.put("right", "Syntaxe : <ul><li>right, <i>pas d'arguments possible</i></li></ul>" 
                                + "<br />Place le curseur vers la doite. Correspond à la commande <i><b>\"rotate 0\"</b></i>");
        manuel.put("center", "Syntaxe : <ul><li>center, <i>pas d'arguments possible</i></li></ul>" 
                                + "<br />Place l'outil au centre de la zone de dessin");
        manuel.put("rotate", "Syntaxe : <ul><li>rotate <angle en degré></li></ul>"
                                + "<br />Effectue une rotation du curseur.");
        manuel.put("undo", "Syntaxe : <ul><li>undo, <i>pas d'arguments possible</i></li></ul>"
                                + "<br />Supprime les dernières actions.");
        manuel.put("redo", "Syntaxe : <ul><li>redo, <i>pas d'arguments possible</i></li></ul>"
                                + "<br />Refait les dernières actions supprimées.");
        manuel.put("forward", "Syntaxe : <ul><li>forward <entier en pixel></li></ul>"
                                + "<br />Fait avancer le curseur");
        manuel.put("backward", "Syntaxe : <ul><li>backward <entier en pixel></li></ul>"
                                + "<br />Fait reculer le curseur");
        manuel.put("goto", "Syntaxe : <ul><li>goto <entier x><entier y></li></ul>"
                                + "<br />Déplace le curseur aux coordonnées x et y.");
        manuel.put("cursorwidth", "Syntaxe : cursorwidth `int width`"
                                + "<br />Fixe la largeur du curseur (Entre 1 et 100).");
        manuel.put("setcolor", "Syntaxe : <ul><li>setcolor `couleur`</li>"
                                + "<li>setcolor `int r` `int g` `int b`</li>"
                                + "<li>setcolor `int r` `int g` `int b` `int alpha`</li></ul>"
                                + "<br />Fixe la couleur du curseur selon l'utilisateur. Pour cela, il peut utiliser des noms"
                                + "couleurs, ou encore des entiers r,g,b (correspondant respectivement aux couleurs rouge,"
                                + "vert et bleue) tels qu'ils soient entre 0 et 255. "
                                + "L'alpha correspond à la transparence du tracé (entre 0 et 255)<br /><br />"
                                + color_list_html);
        manuel.put("setbackgroundcolor", "Syntaxe : <ul><li>setbackgroundcolor `couleur`</li>"
                                + "<li>setcolor `int r` `int g` `int b`</li></ul>"
                                + "<br />Fixe la couleur de fond."
                                + "<br />Un nom de couleur peut être utilisé (voir annexe), ou bien des entiers r,g,b (correspondant "
                                + "respectivement aux couleurs rouge, verte et bleue) tels qu'ils soient entre 0 et 255.<br /><br />"
                                + color_list_html);
        manuel.put("do", "Syntaxe : <ul><li>do `nom figure` `args`</li></ul>"
                                + "<br />Dessine une figure selon les arguments donné par l'utilisateur."
                                + "<br /><br />"
                                + "<u>Liste des noms de figures et de leurs arguments</u> :"
                                + "<ul> <li>triangle `x1` `y1` `x2` `y2` `x3` `y3` `fill`</li>"
                                + "     <li>circle `x1` `x2` `width` `fill`</li>"
                                + "     <li>square `x1` `x2` `width` `fill`</li>"
                                + "     <li>rectangle `x1` `x2` `width` `height` `fill`</li></ul>"
                                + "<br />Les valeurs x1 à x3, y1 à y3, width, height sont des valeurs entières."
                                + "<br />La valeur fill correspond à la valeur 0 si la figure est vide, 1 si la figure est remplie.");
        manuel.put("width", "Syntaxe : <ul><li>width `width`</li></ul>"
                                + "<br />Change la largeur de la zone de dessin. ( Ne recalcule pas le dessin )."
                                + "<br />`width` est ici une valeur entière.");
        manuel.put("height", "Syntaxe : <ul><li>height `height`</li></ul>"
                                + "<br />Change la hauteur de la zone de dessin. ( Ne recalcule pas le dessin )."
                                + "<br />`height` est ici une valeur entière.");
        manuel.put("grid", "Syntaxe : <ul><li>grid</li><li>grid `width` `height`</li></ul>"
                                + "<br />Affiche une grille sur la zone de dessin. Fait partie intégrante du mode pixelart."
                                + "<br />Si aucun argument n'est entré, une boîte de dialogue est ouverte demande la largeur et"
                                + "la hauteur des carreaux."
                                + "<br />Les valeurs `width` et `height` sont des valeurs entières correspondant à la largeur et"
                                + "à la hauteur des carreaux.");
        manuel.put("disablegrid", "Syntaxe : <ul><li>disablegrid, <i>pas d'arguments possible</i></li></ul>"
                                + "<br />Désactive la grille.");
        manuel.put("pixelart", "Syntaxe : <ul><li>pixelart</li><li>pixelart `size`</li></ul>"
                                + "<br />Active le mode pixelart."
                                + "<br />`size` correspond à la largeur/hauteur des carreaux de la grille"
                                + "<br /><br />Le mode pixelart affiche une grille dans la zone de dessin, et permet de dessiner seulement à"
                                + "l'intérieur des carreaux. La modification de la largeur, de la forme, ou si la grille est désactivé, alors "
                                + "le mode pixel art est désactivé.");
        manuel.put("new", "Syntaxe : <ul><li>new, <i>pas d'arguments possible</i></li></ul>"
                                + "Créer un nouveau fichier. Si le fichier courrant a été modifié, alors une demande de sauvegarde "
                                + "avant de créer le nouveau fichier.");
        manuel.put("open", "Syntaxe : <ul><li>open</li><li>open `pathname`</li></ul>"
                                + "Ouvre un fichier image."
                                + "Si aucun argument n'est précisé, ouvre une boîte de dialogue permettant le choix d'un fichier image."
                                + "L'argument `pathname`, doit être le chemin (absolu ou relatif) de l'image."
                                + "Le programme ne peut ouvrir que des images PNG, JPG, GIF.");
        manuel.put("save", "Syntaxe : <ul><li>save, <i>pas d'arguments possible</i></li></ul>"
                                + "<br />Si l'image est déjà associé à un fichier image, alors sauvegarde automatiquement. "
                                + "Sinon, ouvre une boîte de dialogue permettant de choisir où sauvegarder l'image."
                                + "Les images ne peuvent être sauvegarder qu'en PNG, JPG, GIF.");
        manuel.put("saveas", "Syntaxe : <ul><li>saveas</li><li>saveas `pathname`</li></ul>"
                                + "<br />Si aucun argument n'est entré, on ouvre alors une boîte de dialogue permettant de choisir "
                                + "où sauvegarder l'image."
                                + "<br />L'argument `pathname` doit être un chemin valide où sauvegarder l'image. ATTENTION : sauvegarde "
                                + "par dessus le fichier existant.");
        manuel.put("savehistory", "Syntaxe : <ul><li>savehistory</li><li>savehistory `pathname`</li></ul>"
                                + "<br />Permet de sauvegarder le fichier historique."
                                + "<br />Si aucun argument n'est entré, ouvre une boîte de dialogue permettant de choisir où sauvegarder "
                                + "l'historique."
                                + "<br />L'argument `pathname` doit être un chemin valide où sauvegarder l'historique. ATTENTION : sauvegarde "
                                + "par dessus le fichier existant."
                                + "Le fichier ne peut être créer qu'en .txt");
        manuel.put("exec", "Syntaxe : <ul><li>exec</li><li>exec `pathname`</li></ul>"
                                + "<br />Permet d'executer un historique texte."
                                + "<br />Si aucun argument n'est entré, ouvre une boîte de dialogue permettant de choisir le fichier "
                                + "à executer."
                                + "<br />L'argument `pathname doit être un chemin valide et un fichier texte.");
        manuel.put("repeat", "Syntaxe : <ul><li>repeat `nombre de repetition` [`args1`; `args2`; ...; `argsN`]</li></ul>"
                                + "<br />Permet d'effectuer la répétition d'une ou plusieurs suite de commandes."
                                + "<br />L'argument `nombre de repetition` doit être un entier (ne peut aller au delà de 1000)"
                                + "<br />Les arguments entrées sont valide si et seulement si elles appartiennent à la liste des commandes "
                                + "valides, mais aussi si les valeurs entrées son valide."
                                + "<br /><br />Des incrémentations sont possibles sur la majorité des commandes contenant des valeurs " 
                                + "entières. (Exemple : repeat 10 [forward +10] &nbsp; A chaque répétition, incrémente de 10 la commande "
                                + "forward."
                                + "<br /><br />" + liste_commandes_valide_repeat);
        manuel.put("clear", "Syntaxe : <ul><li>clear, <i>pas d'argument possible.</i></li></ul>"
                                + "<br />Efface le contenu du terminal. ( Différent de l'historique )");
        manuel.put("help", "Syntaxe : <ul><li>help, <i>pas d'argument possible.</i></li></ul>"
                                + "<br />Affiche une liste des commandes.");
        manuel.put("man", "Syntaxe : <ul><li>man `commande`</li></ul>"
                                + "<br />Affiche le manuel de la commande entrée en argument");
        manuel.put("exit", "Syntaxe : <ul><li>exit</li></ul>"
                                + "<br />Quitte le programme.");

        return true;
    }
    
    public static boolean init_liste_couleur(){
    
        liste_couleur = new Hashtable<String, Color>();

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

    private static String getColorListHtml()
    {
        return new String(      "<u>Liste des couleurs :</u>"
                            +   "<ul>   <li>black</li><li>blue</li><li>cyan</li>"
                            +   "       <li>light_gray</li><li>gray</li><li>dark_gray</li>"
                            +   "       <li>green</li><li>magenta</li><li>orange</li>"
                            +   "       <li>pink</li><li>red</li><li>yellow</li><li>white</li>" );
    }
    
    private static String getValidCommandRepeat()
    {
        String liste_valid_commande = "<u>Liste des commandes valides pour la fonction repeat :</u><ul>";
        Enumeration<String> commandes = getEnumerationListeCommandes();

        while ( commandes.hasMoreElements() )
        {
            String tmp_cmd = commandes.nextElement();
            if ( !tmp_cmd.equalsIgnoreCase("undo")  ||  !tmp_cmd.equalsIgnoreCase("redo")
                                                    ||  !tmp_cmd.equalsIgnoreCase("width")
                                                    ||  !tmp_cmd.equalsIgnoreCase("height")
                                                    ||  !tmp_cmd.equalsIgnoreCase("new")
                                                    ||  !tmp_cmd.equalsIgnoreCase("open")
                                                    ||  !tmp_cmd.equalsIgnoreCase("save")
                                                    ||  !tmp_cmd.equalsIgnoreCase("saveas")
                                                    ||  !tmp_cmd.equalsIgnoreCase("savehistory")
                                                    ||  !tmp_cmd.equalsIgnoreCase("exec")
                                                    ||  !tmp_cmd.equalsIgnoreCase("clear")
                                                    ||  !tmp_cmd.equalsIgnoreCase("help")
                                                    ||  !tmp_cmd.equalsIgnoreCase("man")
                                                    ||  !tmp_cmd.equalsIgnoreCase("exit") )
            {
                liste_valid_commande += "<li>" + tmp_cmd + "</li>";
            }
        }

        return liste_valid_commande + "</ul>";
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
     *  @param append Détermine si on ajout à la suite ou non
     */
    public static void setParamErreur(String param, boolean append)
    {
        if ( append )
        {
            liste_erreurs.put(-1, liste_erreurs.get(-1) + ": " + param); 
        }
        else
        {
            liste_erreurs.put(-1, ": " + param);
        }
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

    public static String getLCU(int numero)
    {
        return liste_commande_undo.get( numero );
    }

    /**
     *  Fonction renvoyant le traceur selon son numéro
     *  @return le traceur
     */
    public static Traceur getListeDessin(int i)
    {
        return liste_dessin.get( i );
    }

    /**
     *  Fonction renvoyant une énumération des listes de commandes
     *  @return Une énumération des listes de commandes
     */
    public static Enumeration<String> getEnumerationListeCommandes()
    {
        return liste_des_commandes.keys(); 
    }

    /**
     *  Fonction renvoyant si la couleur existe
     *  @param couleur Couleur demandée par l'utilisateur
     *  @return L'existence de la couleur
     */
    public static boolean isAColor(String couleur)
    {
        return liste_couleur.containsKey(couleur);
    }

    /**
     *  Fonction renvoyant la couleur selon l'entrée de l'utilisateur
     *  @param couleur Couleur demandée par l'utilisateur
     *  @return La couleur
     */
    public static Color getColor(String couleur)
    {
        return liste_couleur.get(couleur);
    }

    /**
     *  Fonction ajoutant la commande à la collection correspondante
     *  @param commande Commande entrée par l'utilisateur
     */
    public static boolean ajoutLCEC(String[] commande, boolean verifLastCommand, boolean removeRedo)
    {

        String s = "";
        for (String cmd : commande)
        {
            s += cmd + " ";
        }

        if ( verifLastCommand && getSize_LCEC() > 0)
        {
            String last_command = getLCEC(getSize_LCEC()-1);
            if ( last_command.contains(" ") )
                last_command = last_command.substring(0,last_command.indexOf(" "));

            if ( commande[0].toLowerCase().equals(last_command) )
            {
                liste_commande_entree_correcte.set(getSize_LCEC()-1, s);
            }
            else
            {
                liste_commande_entree_correcte.add(s);
            }
        }
        else
        {
            liste_commande_entree_correcte.add(s);
        }
        
        if ( getImageSave() )
        {
            changeImageSave();
        }

        if ( removeRedo && has_undone )
        {
            videLCU();
        }

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
     *  Fonction ajoutant la commande à la collection correspondante
     *  @param Traceur Traceur à ajouter.
     */
    public static boolean ajoutListeDessin(Traceur t)
    {
        liste_dessin.add(t);
        return true;
    }

    /**
     *  Fonction ajoutant la commande à la collection correspondante
     *  @param String Commande
     */
    public static boolean ajoutLCEC_undo(String commande)
    {
        liste_commande_undo.add(commande);
        return true;
    }

    /**
     *  Fonction vidant la collection correspondante
     */
    public static void videLCEC()
    {
        liste_commande_entree_correcte.clear();
    }

    /**
     *  Fonction vidant la collection correspondante
     */
    public static void videListeDessin()
    {
        liste_dessin.clear();
    }

    public static void videLCU()
    {
        liste_commande_undo.clear();
    }

    /**
     *  Fonction permettant de tout vider
     */
    public static void videTout()
    {
        videLCEC();
        videListeDessin();
        videLCU();
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
     *  Fonction renvoyant la taille de la collection
     *  @return taille
     */
    public static int getSize_LCU()
    {
        return liste_commande_undo.size();
    }

    /**
     *  Fonction renvoyant la taille de la collection
     *  @return taille
     */
    public static int getSize_ListeDessin()
    {
        return liste_dessin.size();
    }

    /**
     *  Fonction supprimant l'élément à la collection, et le renvoyant
     *  @return Element supprimé
     */
    public static String remove_LCEC(int index)
    {
        return liste_commande_entree_correcte.remove(index);
    }

    /**
     *  Fonction supprimant l'élément à la collection, et le renvoyant
     *  @return Element supprimé
     */
    public static Traceur remove_liste_dessin(int index)
    {
        return liste_dessin.remove(index);
    }

    /**
     *  Fonction supprimant l'élément à la collection et le renvoyant
     *  @return Element supprimé
     */
    public static String remove_liste_commande_undo(int index)
    {
        return liste_commande_undo.remove(index);
    }

    /**
     *  Fonction renvoyant le manuel de la commande.
     *  @param commande Commande entré par l'utilisateur
     */
    public static String getManuel(String commande)
    {
        return manuel.get( commande );
    }

    /**
     *  Fonction permettant l'ajout de fonctions dans une liste tampon
     *  @param commande Commande à ajouter;
     */
    public static void ajoutTmp(String command)
    {
        tmp_command.add( command );
    }

    /**
     *  Fonction permettant de lire et supprimé de la liste tampon
     *  @param index Index de la commande
     */
    public static String getTmp(int index)
    {
        return tmp_command.remove(index);
    }

    /**
     *  Fonction permettant de vider la liste tampon
     */
    public static void videTmp()
    {
        tmp_command.clear();
    }

    /**
     *  Fonction permettant d'obtenir la taille du tampon
     *  @return taille du tampon
     */
    public static int getSize_Tmp()
    {
        return tmp_command.size();
    }

    
    /**
     *  Fonction qui ajoute le pathname lors de la sauvegarde
     *  @param pathname Chemin.
     */
    public static void setPathname(String pathname)
    {
        pathname_save = pathname;
    }

    /**
     *  Fonction qui renvoie le pathname
     *  @return pathname
     */
    public static String getPathname()
    {
        return pathname_save;
    }
    
    /**
     *  Vide le pathname
     */
    public static void videPathname()
    {
        setPathname("");
    }

    /**
     *  Fonction qui change la valeur du boolean
     */
    public static void changeImageSave()
    {
        image_save = !image_save;
    }

    /**
     *  Fonction qui renvoie la valeur de la sauvegarde de l'image
     *  @return boolean
     */
    public static boolean getImageSave()
    {
        return image_save;
    }

    /**
     *  Fonction qui change la valeur du boolean
     *  @param boolean boolean save
     */
    public static void setImageSave(boolean b)
    {
        image_save = b;
    }

    /**
     *  Fonctiion qui change la valeur du boolean
     *  @param b nouvelle valeur du boolean
     */
    public static void setHasUndone(boolean b)
    {
        has_undone = b;
    }

    /**
     *  Fonction qui renvoie la valeur du boolean
     *  @return boolean
     */
    public static boolean getHasUndone()
    {
        return has_undone;
    }

    /**
     *  Fonction renvoyant la dernière commande entrée
     *  @return la dernière commande
     */
    public static String lastCommande()
    {
        if ( getSize_LCEC() > 0 )
        {
            String s = getLCEC(getSize_LCEC()-1);
            if ( s.indexOf(" ") > 0 )
            {
                s = s.substring(0, s.indexOf(" "));
            }

            return s;
        }
        return "";
    }

}
