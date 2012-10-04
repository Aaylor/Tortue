import java.util.ArrayList;

public class Commandes{

    private String commande;
    private static ArrayList<Commandes> liste_de_commande = new ArrayList();

    /**
     *  Constructeur vide de l'objet Commandes.
     */
    public Commandes()
    {
    
        this( new String("") );

    }

   /**
    *   Constructeur de l'objet Commande. 
    *   @param commande string de la commande entrée par l'utilisateur.
    */
    public Commandes(String commande)
    {

        this.commande = commande;
        init();

    }

   /**
    *   Accesseur
    *   @return commande
    */
    public String getCommande()
    {

        return this.commande;

    }

    /**
     *  Affiche la ArrayList
     */
    public static void afficheArrayList()
    {

        for (int i = 0; i < liste_de_commande.size(); i++)
            System.out.println((liste_de_commande.get(i).getCommande()));

    }

    /**
     *  Fonction qui traite le string
     *  @return true si la fonction s'est bien déroulée.
     */
    public boolean init()
    {

        String[] commande_split = this.commande.split(" ");
        switch ( commande_split[0] )
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
    public boolean pendown()
    {

        liste_de_commande.add(this);
        return true;

    }

    /**
     * Fonction qui permet d'arrêter l'écriture lorsque l'utilisateur se déplace
     * @return si la fonction s'est bien déroulée.
     */
    public boolean penup()
    {

        liste_de_commande.add(this);
        return true;

    }

    /**
     * Fonction qui permet de passer en mode gomme
     * @return si la fonction s'est bien déroulée.
     */
    public boolean eraser()
    {

        return true;

    }

    /**
     *  Fonction qui permet de placer le pointeur vers le haut
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean up()
    {

        return true;

    }

    /**
     *  Fonction qui permet de placer le pointeur vers le bas
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean down()
    {

        return true;

    }

    /**
     *  Fonction qui permet de placer le pointeur vers la gauche
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean left()
    {

        return true;

    }

    /**
     * Fonction qui permet de placer le pointeur vers la droite
     * @return si la fonction s'est bien déroulée.
     */
    public boolean right()
    {

        return true;

    }

    /**
     *  Fonction qui permet de faire une rotation sur le pointeur
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean rotate()
    {

        return true;

    }

    /**
     *  Fonction qui permet de faire avancer le pointeur
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean forward()
    {

        return true;

    }

    /**
     *  Fonction qui permet de faire reculer le pointeur
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean backward()
    {

        return true;

    }

    /**
     *  Fonction qui permet de déplacer le pointeur
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean goTo()
    {
    
        return true;

    }

    /**
     *  Fonction qui permet de régler la largeur du curseur
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean cursorwidth()
    {

        return true;

    }

    /**
     *  Fonction qui permet de changer la couleur
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean setColor()
    {

        return true;

    }

    /**
     *  Fonction qui permet de changer la couleur du fond d'écran
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean setBackgroundColor()
    {

        return true;

    }

    /**
     *  Fonction qui permet de tracer des figures particulières
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean doFigure()
    {

        return true;

    }

    /**
     *  Fonction qui permet de changer la largeur de l'écran
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean width()
    {

        return true;

    }

    /**
     *  Fonction qui permet de changer la hauteur de l'écran
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean height()
    {

        return true;
    
    }

    /**
     *  Fonction qui permet de créer un nouveau document
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean newFile()
    {

        return true;

    }

    /**
     *  Fonction qui permet d'ouvrir une image
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean open()
    {

        return true;

    }

    /**
     *  Fonction qui permet de sauvegarder un document en une image
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean save()
    {

        return true;

    }

    /**
     *  Fonction qui sauvegarde dans un dossier donner par l'utilisateur
     *  @return si la fonction s'est bien déroulée
     */
    public boolean saveas()
    {

        return true;

    }

    /**
     *  Fonction qui sauvegarde l'historique dans un format .txt
     *  @return si la fonction s'est bien déroulée
     */
    public boolean savehistory()
    {

        return true;

    }

    /**
     *  Fonction qui lit un fichier et execute les lignes de commandes si celles-ci sont correctes
     *  @return si la fonction s'est bien déroulée
     */
    public boolean exec()
    {

        return true;

    }

    /**
     *  Fonction qui répète les dernières commandes lancés par l'utilisateur
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean repeat()
    {

        return true;

    }

    /**
     *  Fonction qui efface l'écran de dessin
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean clear()
    {

        return true;

    }

    /**
     *  Fonction qui affiche une fenêtre avec la liste des commandes
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean help()
    {

        return true;

    }

    /**
     *  Fonction qui affiche le manuel de la commande
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean man()
    {

        return true;

    }


}
