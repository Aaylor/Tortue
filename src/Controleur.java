import java.util.ArrayList;

public class Controleur{

    static ArrayList<String> liste_de_commande = new ArrayList<String>();

    /**
     *  Fonction qui permet de contrôler le commande entrée par l'utilisateur
     *  @param Commande entrée par l'utilisateur
     *  @return Si la fonction s'est correctement déroulée
     */
    public static boolean controleur(String s)
    {

	String[] commande_parser;

	if ( s.equals("") )
	    return false;
	else
	    {
		commande_parser = parse(s);

		if ( init(commande_parser) )
		    {
			liste_de_commande.add(s);
			return true;
		    }
		else
		    return false;
	    }

    }

    /**
     *  Fonction qui parse la chaîne de commande
     *  @param Commande entrée par l'utilisateur
     *  @return Tableau comportant la commande et ses arguments ( si besoins )
     */
    public static String[] parse(String s)
    {

	s = s.toLowerCase();
	return s.split(" ");
    
    }
    

    /**
     *  Fonction qui traite le string
     *  @param Tableau contenant le nom de la commande ainsi que ses arguments
     *  @return true si la fonction s'est bien déroulée.
     */
    public static boolean init(String[] commande_parser)
    {

        switch ( commande_parser[0] )
        {
            case "pendown":
                pendown();
                break;
            case "penup":
                penup();
                break;
            case "eraser":
                eraser();
                break;
            case "up":
                up();
                break;
            case "down":
                down();
                break;
            case "left":
                left();
                break;
            case "right":
                right();
                break;
            case "rotate":
                rotate();
                break;
            case "forward":
                forward();
                break;
            case "backward":
                backward();
                break;
            case "goto":
                goTo();
                break;
            case "cursorwidth":
                cursorwidth();
                break;
            case "setcolor":
                setColor();
                break;
            case "setbackgroundcolor":
                setBackgroundColor();
                break;
            case "do":
                doFigure();
                break;
            case "width":
                width();
                break;
            case "height":
                height();
                break;
            case "new":
                newFile();
                break;
            case "open":
                open();
                break;
            case "save":
                save();
                break;
            case "saveas":
                saveas();
                break;
            case "savehistory":
                savehistory();
                break;
            case "exec":
                exec();
                break;
            case "repeat":
                repeat();
                break;
            case "clear":
                clear();
                break;
            case "help":
                help();
                break;
            case "man":
                man();
                break;
            default:
                System.out.println("fonction n'existe pas");
                return false;

        }

        System.out.println("fonction existe");
        return true;

    }




    /**
     * Fonction qui permet l'écriture lorsque l'utilisateur se déplace
     * @return si la fonction s'est bien déroulée.
     */
    public static boolean pendown()
    {

        return true;

    }

    /**
     * Fonction qui permet d'arrêter l'écriture lorsque l'utilisateur se déplace
     * @return si la fonction s'est bien déroulée.
     */
    public static boolean penup()
    {

        return true;

    }

    /**
     * Fonction qui permet de passer en mode gomme
     * @return si la fonction s'est bien déroulée.
     */
    public static boolean eraser()
    {

        return true;

    }

    /**
     *  Fonction qui permet de placer le pointeur vers le haut
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean up()
    {

        return true;

    }

    /**
     *  Fonction qui permet de placer le pointeur vers le bas
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean down()
    {

        return true;

    }

    /**
     *  Fonction qui permet de placer le pointeur vers la gauche
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean left()
    {

        return true;

    }

    /**
     * Fonction qui permet de placer le pointeur vers la droite
     * @return si la fonction s'est bien déroulée.
     */
    public static boolean right()
    {

        return true;

    }

    /**
     *  Fonction qui permet de faire une rotation sur le pointeur
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean rotate()
    {

        return true;

    }

    /**
     *  Fonction qui permet de faire avancer le pointeur
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean forward()
    {

        return true;

    }

    /**
     *  Fonction qui permet de faire reculer le pointeur
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean backward()
    {

        return true;

    }

    /**
     *  Fonction qui permet de déplacer le pointeur
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean goTo()
    {
    
        return true;

    }

    /**
     *  Fonction qui permet de régler la largeur du curseur
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean cursorwidth()
    {

        return true;

    }

    /**
     *  Fonction qui permet de changer la couleur
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean setColor()
    {

        return true;

    }

    /**
     *  Fonction qui permet de changer la couleur du fond d'écran
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean setBackgroundColor()
    {

        return true;

    }

    /**
     *  Fonction qui permet de tracer des figures particulières
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean doFigure()
    {

        return true;

    }

    /**
     *  Fonction qui permet de changer la largeur de l'écran
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean width()
    {

        return true;

    }

    /**
     *  Fonction qui permet de changer la hauteur de l'écran
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean height()
    {

        return true;
    
    }

    /**
     *  Fonction qui permet de créer un nouveau document
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean newFile()
    {

        return true;

    }

    /**
     *  Fonction qui permet d'ouvrir une image
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean open()
    {

        return true;

    }

    /**
     *  Fonction qui permet de sauvegarder un document en une image
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean save()
    {

        return true;

    }

    /**
     *  Fonction qui sauvegarde dans un dossier donner par l'utilisateur
     *  @return si la fonction s'est bien déroulée
     */
    public static boolean saveas()
    {

        return true;

    }

    /**
     *  Fonction qui sauvegarde l'historique dans un format .txt
     *  @return si la fonction s'est bien déroulée
     */
    public static boolean savehistory()
    {

        return true;

    }

    /**
     *  Fonction qui lit un fichier et execute les lignes de commandes si celles-ci sont correctes
     *  @return si la fonction s'est bien déroulée
     */
    public static boolean exec()
    {

        return true;

    }

    /**
     *  Fonction qui répète les dernières commandes lancés par l'utilisateur
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean repeat()
    {

        return true;

    }

    /**
     *  Fonction qui efface l'écran de dessin
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean clear()
    {

        return true;

    }

    /**
     *  Fonction qui affiche une fenêtre avec la liste des commandes
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean help()
    {

        return true;

    }

    /**
     *  Fonction qui affiche le manuel de la commande
     *  @return si la fonction s'est bien déroulée.
     */
    public static boolean man()
    {

        return true;

    }


}
