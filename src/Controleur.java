import java.util.ArrayList;

public class Controleur{

    public static final int COMMANDE_ERRONEE = 1;
    /* TODO
     * mettre en constante les autres erreurs
     */
    
    Terminal term = null;
    ZoneDessin zd = null;
    ZoneBouton zb = null;
    Curseur curseur = null;


    /**
     *  Constructeur vide
     */
    public Controleur(){};

    /**
     *  Fonction qui permet d'initialiser les liens entre objets
     *  @param f Fenetre principale
     *  @param c Curseur
     */
    public void ___hydrate___(Fenetre f, Curseur c)
    {

        term = f.getTerminal();
        term.setControleur(this);

        zd = f.getZoneDessin();
        zd.setControleur(this);
        
        zb = f.getZoneBouton();
        zb.setControleur(this);

        curseur = c;
        c.setControleur(this);

    }

    /**
     *  Fonction qui permet de contrôler le commande entrée par l'utilisateur
     *  @param s Commande entrée par l'utilisateur
     *  @return Si la fonction s'est correctement déroulée
     */
    public boolean commande(String s)
    {

	    String[] commande_parser;

	    if ( s.equals("") )
	        return false;

        else
	    {
		    commande_parser = parse(s);

		    if ( init(commande_parser) )
		    {
		
                StockageDonnee.liste_commande_entree_correcte.add(s);
			    return true;
		
            }
		
	        return false;

        }

    }

    /**
     *  Fonction qui parse la chaîne de commande
     *  @param s Commande entrée par l'utilisateur
     *  @return Tableau comportant la commande et ses arguments ( si besoins )
     */
    public String[] parse(String s)
    {

	    s = s.toLowerCase();
	    return s.split(" ");
    
    }

    /**
     *  Fonction qui envoie le message d'erreur au terminal
     *  @param s le message d'erreur
     */
    public void afficheErreur(String s)
    {

        term.afficheErreur(s);

    }
    

    /**
     *  Fonction qui traite le string
     *  @param commande_parser Tableau contenant le nom de la commande ainsi que ses arguments
     *  @return true si la fonction s'est bien déroulée.
     */
    public boolean init(String[] commande_parser)
    {

        int numero_commande = -1;
        if ( StockageDonnee.liste_des_commandes.containsKey( commande_parser[0] ) )
            numero_commande = StockageDonnee.liste_des_commandes.get( commande_parser[0] );

        switch ( numero_commande )
        {
            case 0:
                pendown();
                break;
            case 1:
                penup();
                break;
            case 2:
                eraser();
                break;
            case 3:
                up();
                break;
            case 4:
                down();
                break;
            case 5:
                left();
                break;
            case 6:
                right();
                break;
            case 7:
                rotate(commande_parser);
                break;
            case 8:
                forward();
                break;
            case 9:
                backward();
                break;
            case 10:
                goTo();
                break;
            case 11:
                cursorwidth();
                break;
            case 12:
                setColor();
                break;
            case 13:
                setBackgroundColor();
                break;
            case 14:
                doFigure();
                break;
            case 15:
                width();
                break;
            case 16:
                height();
                break;
            case 17:
                newFile();
                break;
            case 18:
                open();
                break;
            case 19:
                save();
                break;
            case 20:
                saveas();
                break;
            case 21:
                savehistory();
                break;
            case 22:
                exec();
                break;
            case 23:
                repeat();
                break;
            case 24:
                clear();
                break;
            case 25:
                help();
                break;
            case 26:
                man();
                break;
            case 27:
                function_debug_test();
                break;
            default:
                this.afficheErreur(GenerationErreur.genererErreur(COMMANDE_ERRONEE));
                return false;

        }

        return true;

    }




    /**
     * Fonction qui permet l'écriture lorsque l'utilisateur se déplace
     * @return si la fonction s'est bien déroulée.
     */
    public boolean pendown()
    {

        return true;

    }

    /**
     * Fonction qui permet d'arrêter l'écriture lorsque l'utilisateur se déplace
     * @return si la fonction s'est bien déroulée.
     */
    public boolean penup()
    {

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
    	this.curseur.setOrientation(90);
    	/*il reste a faire une rotation de l'image du curseur*/
        return true;

    }

    /**
     *  Fonction qui permet de placer le pointeur vers le bas
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean down()
    {
    	this.curseur.setOrientation(270);
    	/*il reste a faire une rotation de l'image du curseur*/
        return true;

    }

    /**
     *  Fonction qui permet de placer le pointeur vers la gauche
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean left()
    {
    	this.curseur.setOrientation(180);
    	/*il reste a faire une rotation de l'image du curseur*/
        return true;

    }

    /**
     * Fonction qui permet de placer le pointeur vers la droite
     * @return si la fonction s'est bien déroulée.
     */
    public boolean right()
    {
    	this.curseur.setOrientation(0);
    	/*il reste a faire une rotation de l'image du curseur*/
        return true;

    }

    /**
     *  Fonction qui permet de faire une rotation sur le pointeur
     *  @return si la fonction s'est bien déroulée.
     */
    public boolean rotate(String[] commande_parser)
    {
    	/*on compte le nombre d'elts present dans le tableau*/
    	if(commande_parser.length==2){
    		
    		String arg_deg=commande_parser[1];
    		int degre;
    		
    		if(isInt(arg_deg)){
    			degre=Integer.parseInt(arg_deg);
    			this.curseur.setOrientation(degre);
    		}
    		else{
    			/*ce n'est pas un int : message d'erreur*/
    			System.out.println("Le parametre passe dans la commande n'est pas un int.");
    		}
    	}
    	else{
    		/*mauvais arguments passes dans le terminal*/
    		System.out.println("Vous devez passer en parametre 1 argument de type int.");
    	}
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

    public void function_debug_test()
    {

        for (int i = 0; i < StockageDonnee.liste_commande_entree_correcte.size(); i++)
            System.out.println(StockageDonnee.liste_commande_entree_correcte.get(i));

    }
    
    /*cette fonction teste si une chaine de caractere est un int ou pas*/
    public boolean isInt(String s){
    	boolean isAnInt=true;
    	try{
    		Integer.parseInt(s);
    	}
    	catch(NumberFormatException e){
    		isAnInt=false;
    	}
    	finally{
    		return isAnInt;
    	}
    }


}
