import java.util.Date;
import java.io.*;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

public class Controleur{

    private static final int SUCCESS = 0;
    private static final int COMMANDE_ERRONEE = 100;
    private static final int NOMBRE_PARAM_LESS = 200; 
    private static final int NOMBRE_PARAM_SUP = 201;
    private static final int PARAM_INCORRECTE = 202;
    private static final int IMAGE_INEXISTANTE = 203;
    private static final int COULEUR_INEXISTANTE = 204;
    /* 
     * TODO
     * mettre en constante les autres erreurs
     */
    
    private Terminal term = null;
    private ZoneDessin zd = null;
    private BarreOutils zb = null;
    private Curseur curseur = null;
    private BarreMenu barreMenu = null;
    private BarreOutils barreOutils = null;

    /**
     *  Créer le contrôleur et lui donne la gestion de la fenêtre et du curseur
     *  @param f Correspond à la fenêtre du programme.
     *  @param c Correspond au curseur de la zone de dessin.
     */
    public Controleur(Fenetre f, Curseur c)
    {
        ___hydrate___(f,c);
    }

    /**
     *  Fonction qui permet d'initialiser les liens entre objets
     *  @param f Fenetre principale
     *  @param c Curseur
     */
    private void ___hydrate___(Fenetre f, Curseur c)
    {

        f.setControleur(this);

        term = f.getTerminal();
        term.setControleur(this);

        zd = f.getZoneDessin();
        zd.setControleur(this);
        
        zb = f.getZoneBouton();
        zb.setControleur(this);

        barreMenu = f.getBarreMenu();
        barreMenu.setControleur(this);
        
        barreOutils = f.getZoneBouton();
        barreOutils.setControleur(this);
        
        curseur = c;
        c.setControleur(this);

    }

    /**
     *  Fonction qui permet de contrôler le commande entree par l'utilisateur
     *  @param s Commande entree par l'utilisateur
     *  @return Si la fonction s'est correctement deroulee
     */
    public boolean commande(String s, boolean write)
    {

	    String[] commande_parser;
        s = rework_command(s);

		commande_parser = parse(s);
        if ( write )
        {
            if ( (commande_parser[0].equalsIgnoreCase("setcolor") || commande_parser[0].equalsIgnoreCase("cursorwidth")) 
                    && StockageDonnee.lastCommande().equalsIgnoreCase(commande_parser[0]) )
            {
                term.remplace(s, term.getLastIndexOf(commande_parser[0]));  
            }
            else
            {
                term.addMessage(" > " + s);
            }
            StockageDonnee.ajoutLCEG(s);
        }

        int numero_renvoie = init(commande_parser,write);
        if ( numero_renvoie != 0 )
        {
            this.setMessageErreur(numero_renvoie);
        }

        term.replaceCompteur();
        return true;
 
    }

    /**
     *  Fonction qui parse la chaîne de commande
     *  @param s Commande entree par l'utilisateur
     *  @return Tableau comportant la commande et ses arguments ( si besoins )
     */
    public String[] parse(String s)
    {
        String[] split = s.split(" ");

        int i = 0;
        int index_begin = -1;
        int index_end = -1;

        while ( (i < split.length) && (index_begin < 0) )
        {
            if ( split[i].startsWith("\"") )
            {
                index_begin = i;
            }
            i++;
        }

        if ( index_begin >= 0 )
        {
            i = index_begin;
            while ( (i < split.length) && (index_end < 0) )
            {
                if ( split[i].endsWith("\"") )
                {
                    index_end = i;
                }
                i++;
            }
    
            if ( index_end >= 0 )
            {
                String full_cmd = "";
                i = index_begin;
                while ( i <= index_end )
                {
                    full_cmd += split[i] + (i == index_end ? "" : " ");
                    i++;
                }
                full_cmd = full_cmd.replaceAll("^[\"]", "").replaceAll("[\"]$", "").trim();

                String[] final_split = new String[split.length-index_end+index_begin];
                i = 0;
                while ( i < final_split.length )
                {
                    if ( i < index_begin )
                    {
                        final_split[i] = split[i];
                    }
                    else if ( i > index_begin )
                    {
                        final_split[i] = split[i+(index_end-index_begin)];
                    }
                    else
                    {
                        final_split[i] = full_cmd;
                    }
                    i++;
                }

                return final_split;
            }
        }

        return split;
    }

    /**
     *  Fonction qui renvoie la commande tapee par l'utilisateur en enlevant tout espace superflu
     *  @param s Commande entree par l'utilisateur
     *  @return Commande retravaillee
     */
    public String rework_command(String s)
    {
        String regex = "\\s{2,}";
        
        s = s.trim();
        s = s.replaceAll(regex, " ");

        return s;
    }

    /**
     *  Fonction qui envoie le message d'erreur au terminal
     *  @param numero_erreur numero de l'erreur
     *  @return boolean
     */
    public boolean setMessageErreur(int numero_erreur)
    {
        String message = "   /!\\ Erreur : ";
        String param = StockageDonnee.getParamErreur();
        if ( !param.equals("") ) 
            message += param + " : ";
        message += StockageDonnee.getMessageErreur(numero_erreur);
        term.addMessage(message);
        return false;
    }
    

    /**
     *  Fonction qui traite le string
     *  @param commande_parser Tableau contenant le nom de la commande ainsi que ses arguments
     *  @return 0 si la fonction s'est bien deroulee.
     */
    public int init(String[] commande_parser, boolean write)
    {
        int retour = 0;
        int valeur, r, g, b;
        int valeur_x, valeur_y, width, height;
        int coordonne[];
        switch ( StockageDonnee.getNumeroFonction( commande_parser[0].toLowerCase() ) )
        {
            case 0:
                if ( commande_parser.length > 1 )
                    return NOMBRE_PARAM_SUP;
                
                retour = pendown();
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, true);

                return retour;

            
            case 1:
                if ( commande_parser.length > 1 )
                    return NOMBRE_PARAM_SUP;

                retour = penup();
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, true);

                return retour;

            case 2:
                if ( commande_parser.length > 1 )
                    return NOMBRE_PARAM_SUP;
                
                retour = pencil();
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, true);

                return retour;

            case 3:
                if ( commande_parser.length > 1 )
                    return NOMBRE_PARAM_SUP;

                retour = eraser();
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, true);
            
                return retour;

            case 4:
                if ( commande_parser.length > 1 )
                    return NOMBRE_PARAM_SUP;

                retour = change_forme();
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, true);

                return retour;

            case 5:
                if ( commande_parser.length > 1 )
                    return NOMBRE_PARAM_SUP;

                retour = up();
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, true);

                return retour;
            
            case 6:
                if ( commande_parser.length > 1 )
                    return NOMBRE_PARAM_SUP;
                
                retour = down();
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, true);

                return retour;
            
            case 7:
                if ( commande_parser.length > 1 )
                    return NOMBRE_PARAM_SUP;
                
                retour = left();
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, true);

                return retour;
            
            case 8:
                if ( commande_parser.length > 1 )
                    return NOMBRE_PARAM_SUP;
                
                retour = right();
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, true);

                return retour;
            
            case 9:
                if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;
                else if ( commande_parser.length < 2 )
                    return NOMBRE_PARAM_LESS;
                else;

                if ( Utilitaire.isInt(commande_parser[1]) )
                    valeur = (int)Integer.parseInt(commande_parser[1]);
                else
                    return PARAM_INCORRECTE;

                retour = rotate(valeur);
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, true);

                return retour;
           
            case 10:
                if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;
                
                if ( commande_parser.length == 2 )
                {
                    if ( Utilitaire.isInt( commande_parser[1] ) )
                    {
                        return undo( Integer.parseInt( commande_parser[1] ) );
                    }
                    else
                    {
                        return PARAM_INCORRECTE;
                    }
                }

                return undo(1);

            case 11:
                if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;

                if ( commande_parser.length == 2 )
                {
                    if ( Utilitaire.isInt( commande_parser[1] ) )
                    {
                        return redo( Integer.parseInt( commande_parser[1] ) );
                    }
                    else
                    {
                        return PARAM_INCORRECTE;
                    }
                }

                return redo(1);

            case 12:
                if ( commande_parser.length < 2 )
                    return NOMBRE_PARAM_LESS;
                else if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;
                else;

                if ( Utilitaire.isInt(commande_parser[1])  )
                    valeur = Integer.parseInt(commande_parser[1]);
                else
                    return PARAM_INCORRECTE;

                retour = forward(valeur);
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, false);

                return retour;
            
            case 13:
                if ( commande_parser.length < 2 )
                    return NOMBRE_PARAM_LESS;
                else if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;
                else;

                if ( Utilitaire.isInt(commande_parser[1]) )
                    valeur = Integer.parseInt(commande_parser[1]);
                else
                    return PARAM_INCORRECTE;

                retour = backward(valeur);
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, false);

                return retour;
            
            case 14:
                if ( commande_parser.length > 3 )
                {
                    return NOMBRE_PARAM_SUP;
                }
                else if ( commande_parser.length < 3 )
                {
                    return NOMBRE_PARAM_LESS;
                }
                else;

                if ( Utilitaire.isInt(commande_parser[1])
                        && Utilitaire.isInt(commande_parser[2]) )
                {
                    valeur_x = Integer.parseInt(commande_parser[1]);
                    valeur_y = Integer.parseInt(commande_parser[2]);
                }
                else
                    return PARAM_INCORRECTE;

                retour = goTo(valeur_x, valeur_y);
                
                boolean verif = false;
                if ( !curseur.isDown() )
                    verif = true;
                
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, verif);

                return retour;
            
            case 15:
                if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;
                else if ( commande_parser.length < 2 )
                    return NOMBRE_PARAM_LESS;
                else;

                if ( Utilitaire.isInt(commande_parser[1]) )
                    valeur = Integer.parseInt(commande_parser[1]);
                else
                    return PARAM_INCORRECTE;

                retour = cursorWidth(valeur);
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, true);

                return retour;

            case 16:
                if ( commande_parser.length > 4 )
                    return NOMBRE_PARAM_SUP;
                else if ( (commande_parser.length < 2) || (commande_parser.length == 3) )
                    return NOMBRE_PARAM_LESS;
                else;

                if ( commande_parser.length == 2 )
                {
                    retour = setColor(commande_parser[1]);
                }
                else if ( commande_parser.length == 4 )
                {
                    if ( Utilitaire.isInt( new String[] {  commande_parser[1], commande_parser[2],
                                                commande_parser[3] } ) )
                    {
                        retour = setColor(  Integer.parseInt(commande_parser[1]),
                                            Integer.parseInt(commande_parser[2]),
                                            Integer.parseInt(commande_parser[3]));
                    }
                    else
                    {
                        return PARAM_INCORRECTE;
                    }
                }
                else;

                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, true);
           
                return retour;

            case 17:
                if ( commande_parser.length > 4 )
                    return NOMBRE_PARAM_SUP;
                else if ( (commande_parser.length < 2) || (commande_parser.length == 3) )
                    return NOMBRE_PARAM_LESS;
                else;

                if ( commande_parser.length == 2 )
                {
                    retour = setBackgroundColor(commande_parser[1]);
                }
                else if ( commande_parser.length == 4 )
                {
                    if ( Utilitaire.isInt( new String[] {  commande_parser[1], commande_parser[2],
                                                commande_parser[3] } ) )
                    {
                        retour = setBackgroundColor(Integer.parseInt(commande_parser[1]),
                                                    Integer.parseInt(commande_parser[2]),
                                                    Integer.parseInt(commande_parser[3]));
                    }
                    else
                    {
                        return PARAM_INCORRECTE;
                    }
                }
                else;

                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, true);
           
                return retour;
            
            case 18:
                if ( commande_parser.length > 9 )
                {
                    return NOMBRE_PARAM_SUP;
                }

                valeur_x = 0;
                valeur_y = 0;
                width = 0;
                height = 0;

                if ( commande_parser[1].equalsIgnoreCase("triangle") )
                {
                    if ( Utilitaire.isInt( new String[] { commande_parser[2], commande_parser[3], commande_parser[4],
                        commande_parser[5], commande_parser[6], commande_parser[7] } ) )
                    {
                        return doFigure(3, new int[] {  Integer.parseInt(commande_parser[2]),
                                                        Integer.parseInt(commande_parser[3]), 
                                                        Integer.parseInt(commande_parser[4]),
                                                        Integer.parseInt(commande_parser[5]), 
                                                        Integer.parseInt(commande_parser[6]),
                                                        Integer.parseInt(commande_parser[7])}, true);
                    }
                    else
                    {
                        return PARAM_INCORRECTE;
                    }
                

                }
                else if ( commande_parser[1].equalsIgnoreCase("carre") )
                {
                    if ( Utilitaire.isInt ( new String[] { commande_parser[2], commande_parser[3],
                        commande_parser[4] } ) )
                    {
                        return doFigure(2, new int[] {  Integer.parseInt(commande_parser[2]),
                                                        Integer.parseInt(commande_parser[3]), 
                                                        Integer.parseInt(commande_parser[4]), 
                                                        Integer.parseInt(commande_parser[4])},
                                                        true);
                    }
                    else
                    {
                        return PARAM_INCORRECTE;
                    }
                }
                else if ( commande_parser[1].equalsIgnoreCase("rectangle") )
                {
                    if ( Utilitaire.isInt ( new String[] { commande_parser[2], commande_parser[3],
                        commande_parser[4], commande_parser[5] } ) )
                    {
                        return doFigure(2, new int[] {  Integer.parseInt(commande_parser[2]),
                                                        Integer.parseInt(commande_parser[3]), 
                                                        Integer.parseInt(commande_parser[4]), 
                                                        Integer.parseInt(commande_parser[5])},
                                                        true);
                    }
                    else
                    {
                        return PARAM_INCORRECTE;
                    }
                }
                else if ( commande_parser[1].equalsIgnoreCase("cercle") )
                {
                    if ( Utilitaire.isInt ( new String[] { commande_parser[2], commande_parser[3],
                        commande_parser[4] } ) )
                    {
                        return doFigure(4, new int[] {  Integer.parseInt(commande_parser[2]),
                                                        Integer.parseInt(commande_parser[3]),
                                                        Integer.parseInt(commande_parser[4])},
                                                        true);
                    }
                    else
                    {
                        return PARAM_INCORRECTE;
                    }
                }
                else
                {
                    return COMMANDE_ERRONEE;
                }
            
            case 19:
                if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;
                else if ( commande_parser.length < 2 )
                    return NOMBRE_PARAM_LESS;
                else;

                if ( Utilitaire.isInt(commande_parser[1]) )
                    valeur = Integer.parseInt(commande_parser[1]);
                else
                    return PARAM_INCORRECTE;

                return width(valeur);
            
            case 20:
                if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;
                else if ( commande_parser.length < 2 )
                    return NOMBRE_PARAM_LESS;
                else;

                if ( Utilitaire.isInt(commande_parser[1]) )
                    valeur = Integer.parseInt(commande_parser[1]);
                else
                    return PARAM_INCORRECTE;

                return height(valeur);
            
            case 21:
                if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;
                return newFile();
            
            case 22:
                if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;
                
                if ( commande_parser.length == 2 )
                    return open(commande_parser[1]);

                return open("");
            
            case 23:
                if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;

                if ( commande_parser.length == 2 )
                    return saveas(commande_parser[1]);

                return save();
            
            case 24:
                if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;
                
                if ( commande_parser.length == 2 )
                    return saveas(commande_parser[1]);

                return saveas("");
            
            case 25:
                if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;
    
                if ( commande_parser.length == 2 )
                    return savehistory(commande_parser[1]);
                else
                    return savehistory("");
            
            case 26:
                if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;

                if ( commande_parser.length == 2 )
                    return exec(commande_parser[1]);

                return exec("");
            
            case 27:
                int nombre_de_repetition = -1;

                if ( Utilitaire.isInt( commande_parser[1] ) )
                {
                    nombre_de_repetition = Integer.parseInt(commande_parser[1]);
                }
                else
                {
                    return PARAM_INCORRECTE;
                }

                String args = "";
                int i = 2;
                while ( i < commande_parser.length )
                {
                    args += commande_parser[i] + (i == commande_parser.length ? "" : " ");
                    i++;
                }

                retour = repeat(nombre_de_repetition, args.trim());
                
                if ( retour == 0 && write )
                    StockageDonnee.ajoutLCEC(commande_parser, false);

                return retour;
                
            case 28:
                if ( commande_parser.length > 1 )
                    return NOMBRE_PARAM_SUP;
                return clear();
            
            case 29:
                if ( commande_parser.length > 1 )
                    return NOMBRE_PARAM_SUP;
                return help();
            
            case 30:
                if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;
                
                if ( commande_parser.length < 2 )
                    return man(false, "");
                else
                    return man(true, commande_parser[1]);
            
            case 31:
                if ( commande_parser.length > 2 )
                    return NOMBRE_PARAM_SUP;

                return exit();

            case 32:
                if ( commande_parser.length == 2 )
                {
                    if ( commande_parser[1].equals("lcec") )
                        function_debug_test( true );
                    else if ( commande_parser[1].equals("lceg") )
                        function_debug_test( false );
                    else
                        return PARAM_INCORRECTE;
                }
                else
                    function_debug_test( false );
                break;
            
            default:
                return COMMANDE_ERRONEE;
        }

        return SUCCESS;

    }




    /**
     * Fonction qui permet l'ecriture lorsque l'utilisateur se deplace
     * @return si la fonction s'est bien deroulee.
     */
    public int pendown()
    {
    	this.curseur.setIsDown(true);
        this.barreOutils.affichageBoutonPoserOutil();
        this.zd.repaint();
        return SUCCESS;

    }

    /**
     * Fonction qui permet d'arrêter l'ecriture lorsque l'utilisateur se deplace
     * @return si la fonction s'est bien deroulee.
     */
    public int penup()
    {
    	this.curseur.setIsDown(false);
        this.barreOutils.affichageBoutonPoserOutil();
        this.zd.repaint();
        return SUCCESS;

    }

    /**
     *  Fonction qui permet de passer en mode crayon
     *  @return si la fonction s'est bien deroulee.
     */
    public int pencil()
    { 
    	this.curseur.setType((short)0);
        this.barreOutils.affichageBoutonOutil();
        zd.repaint();
        return SUCCESS;
    }

    /**
     * Fonction qui permet de passer en mode gomme
     * @return si la fonction s'est bien deroulee.
     */
    public int eraser()
    {
    	this.curseur.setType((short)1);
        this.barreOutils.affichageBoutonOutil();
        zd.repaint();
        return SUCCESS;
    }

    /**
     *  Fonction qui permet de changer la forme du curseur
     *  @return sur la fonction s'est bien deroulee.
     */
    public int change_forme()
    {
        this.curseur.setForme( this.curseur.getForme() == 1 ? 0 : 1 );
        this.barreOutils.affichageBoutonForme();
        zd.repaint();
        return SUCCESS;
    }

    /**
     *  Fonction qui permet de placer le pointeur vers le haut
     *  @return si la fonction s'est bien deroulee.
     */
    public int up()
    {
    	this.curseur.setOrientation(180);
        this.zd.repaint();
        return SUCCESS;

    }

    /**
     *  Fonction qui permet de placer le pointeur vers le bas
     *  @return si la fonction s'est bien deroulee.
     */
    public int down()
    {
    	this.curseur.setOrientation(0);
        this.zd.repaint();
        return SUCCESS;

    }

    /**
     *  Fonction qui permet de placer le pointeur vers la gauche
     *  @return si la fonction s'est bien deroulee.
     */
    public int left()
    {
    	this.curseur.setOrientation(270);
        this.zd.repaint();
        return SUCCESS;

    }

    /**
     * Fonction qui permet de placer le pointeur vers la droite
     * @return si la fonction s'est bien deroulee.
     */
    public int right()
    {
    	this.curseur.setOrientation(90);
        this.zd.repaint();
        return SUCCESS;

    }

    /**
     *  Fonction qui permet de faire une rotation sur le pointeur
     *  @param valeur Valeur de l'angle
     *  @return si la fonction s'est bien deroulee.
     */
    public int rotate(int valeur)
    {
    	this.curseur.setOrientation(valeur+90);
        this.zd.repaint();
        return SUCCESS;
    }

    /**
     *  Fonction qui annule les n dernières actions
     *  @return si la fonction s'est bien deroulee.
     */
    public int undo(int valeur)
    {
        int i = 0;
        int max_stockage_donnee = StockageDonnee.getSize_LCEC()-1;
        int max_stockage_dessin = StockageDonnee.getSize_ListeDessin()-1;
        while ( i < valeur )
        {
            StockageDonnee.ajoutLCEC_undo( StockageDonnee.remove_LCEC( max_stockage_donnee - i ) ); 
            StockageDonnee.ajoutListeDessin_undo( StockageDonnee.remove_liste_dessin( max_stockage_dessin - i ) ); 
            i++;
        }
       
        zd.repaint();
        return SUCCESS;
    }

    /**
     *  Fonctio qui refait les n dernières actions annulees
     *  @return si la fonction s'est bien deroulee.
     */
    public int redo(int valeur)
    {
        return SUCCESS;
    }

    /**
     *  Fonction qui permet de faire avancer le pointeur
     *  @param valeur Valeur d'avancee
     *  @return si la fonction s'est bien deroulee.
     */
    public int forward(int valeur)
    {
    	
    	//Calcul de la nouvelle position du curseur
    	int posX1=curseur.getPosX();
    	int posY1=curseur.getPosY();
    	
    	double posX = curseur.getPosX() + valeur * Math.sin(curseur.getOrientation() * Math.PI / 180);
		double posY = curseur.getPosY() + valeur * Math.cos(curseur.getOrientation() * Math.PI / 180);
		
		//conditions pour que le curseur ne depasse pas la zone de dessin
		
		if(posX <= zd.getLargeurDessin() && posX >= 0) curseur.setPosX((int)posX); //ok
        
        if(posY <= zd.getHauteurDessin() && posY >= 0) curseur.setPosY((int)posY); //ok 
        
        if(posX <0) curseur.setPosX(0); //valeur negative : on replace à 0
        
        if(posY <0) curseur.setPosY(0); //valeur negative : on replace à 0
        
        if(posX > zd.getLargeurDessin()) curseur.setPosX(zd.getLargeurDessin()); //trop grand : on met à la position max
        
        if(posY > zd.getHauteurDessin()) curseur.setPosY(zd.getHauteurDessin()); //trop grand : on met à la position max
        
        if(curseur.isDown() && curseur.getType() == 0){
        	Traceur t = new Traceur(1, curseur.getEpaisseur(), curseur.getCouleur(), posX1, posY1, curseur.getPosX(), curseur.getPosY(), curseur.getForme());
        	StockageDonnee.ajoutListeDessin(t);
        }
        if(curseur.isDown() && curseur.getType() == 1){
        	Traceur t = new Traceur(1, curseur.getEpaisseur(), zd.getBackground(), posX1, posY1, curseur.getPosX(), curseur.getPosY(), curseur.getForme());
        	StockageDonnee.ajoutListeDessin(t);
        	
        }
        
        
        this.zd.repaint(); 
        return SUCCESS;
    }

    /**
     *  Fonction qui permet de faire reculer le pointeur
     *  @param valeur Valeur de recul
     *  @return si la fonction s'est bien deroulee.
     */
    public int backward(int valeur)
    {
    	//Calcul de la nouvelle position du curseur
    	
    	int posX1=curseur.getPosX();
    	int posY1=curseur.getPosY();
    	
    	double posX = curseur.getPosX() - valeur * Math.sin(curseur.getOrientation() * Math.PI / 180);
		double posY = curseur.getPosY() - valeur * Math.cos(curseur.getOrientation() * Math.PI / 180);
		
		//conditions pour que le curseur ne depasse pas la zone de dessin
		
		if(posX <= zd.getLargeurDessin() && posX >= 0) curseur.setPosX((int)posX); //ok
		       
		if(posY <= zd.getHauteurDessin() && posY >= 0) curseur.setPosY((int)posY); //ok 
		        
		if(posX <0) curseur.setPosX(0); //valeur negative : on replace à 0
		       
		if(posY <0) curseur.setPosY(0); //valeur negative : on replace à 0
		        
		if(posX > zd.getLargeurDessin()) curseur.setPosX(zd.getLargeurDessin()); //trop grand : on met à la position max
		        
		if(posY > zd.getHauteurDessin()) curseur.setPosY(zd.getHauteurDessin()); //trop grand : on met à la position max
       
		if(curseur.isDown() && curseur.getType() == 0){
        	Traceur t = new Traceur(1, curseur.getEpaisseur(), curseur.getCouleur(), posX1, posY1, curseur.getPosX(), curseur.getPosY(), curseur.getForme());
        	StockageDonnee.ajoutListeDessin(t);
        	
        }
        if(curseur.isDown() && curseur.getType() == 1){
        	Traceur t = new Traceur(1, curseur.getEpaisseur(), zd.getBackground(), posX1, posY1, curseur.getPosX(), curseur.getPosY(), curseur.getForme());
        	StockageDonnee.ajoutListeDessin(t);
        	
        }
		
		this.zd.repaint();
        return SUCCESS;
    }

    /**
     *  Fonction qui permet de deplacer le pointeur
     *  @param value Abscisse d'arrivee
     *  @param value Ordonnee d'arrivee
     *  @return si la fonction s'est bien deroulee.
     */
    public int goTo(int value, int value_2)
    {
        int posX1=curseur.getPosX();
    	int posY1=curseur.getPosY();
    	
        //conditions pour que le curseur ne depasse pas la zone de dessin
        
        if( value >= 0 && value <= zd.getLargeurDessin()) curseur.setPosX(value); //ok
    		
    	if(value_2 >= 0 && value_2 <= zd.getHauteurDessin()) curseur.setPosY(value_2); //ok

    	if(value > zd.getLargeurDessin()) curseur.setPosX(zd.getLargeurDessin()); //valeur X > largeur de la zone
    	
    	if(value_2 > zd.getHauteurDessin()) curseur.setPosY(zd.getHauteurDessin()); //valeur Y > hauteur de la zone
    	
    	if(value < 0) curseur.setPosX(0); //valeur negative => on replace à la valeur minimu : 0
    	
    	if(value_2 < 0) curseur.setPosY(0); //valeur negative => on replace à la valeur minimu : 0
    	
    	if(curseur.isDown() && curseur.getType() == 0){
        	Traceur t = new Traceur(1, curseur.getEpaisseur(), curseur.getCouleur(), posX1, posY1, curseur.getPosX(), curseur.getPosY(), curseur.getForme());
        	StockageDonnee.ajoutListeDessin(t);
        	
        }
        if(curseur.isDown() && curseur.getType() == 1){
        	Traceur t = new Traceur(1, curseur.getEpaisseur(), zd.getBackground(), posX1, posY1, curseur.getPosX(), curseur.getPosY(), curseur.getForme());
        	StockageDonnee.ajoutListeDessin(t);
        	
        }

    	this.zd.repaint();
        return SUCCESS;
    }

    /**
     *  Fonction qui permet de regler la largeur du curseur
     *  @param valeur Epaisseur du curseur
     *  @return si la fonction s'est bien deroulee.
     */
    public int cursorWidth(int valeur)
    {
        curseur.setEpaisseur(valeur);
        zd.repaint();
        return SUCCESS;
    }

    /**
     *  Fonction qui permet de changer la couleur
     *  couleur Couleur du curseur
     *  @return si la fonction s'est bien deroulee.
     */
    public int setColor(String couleur)
    {
        if(StockageDonnee.isAColor(couleur)){
        	Color c = StockageDonnee.getColor(couleur);
        	curseur.setCouleur(c);
        }
        else{
        	return COULEUR_INEXISTANTE;
        }
        return SUCCESS;

    }

    /**
     *  Fonction qui permet de changer la couleur (int RGB)
     *  @param red Rouge curseur
     *  @param green Vert curseur
     *  @param blue Bleu curseur
     *  @return si la fonction s'est bien deroulee.
     */
    public int setColor(int red, int green, int blue)
    {
    	if(red >= 0 && red <= 255 && green >= 0 && green <= 255 && blue >= 0 && blue <= 255){
    		curseur.setCouleur(new Color(red,green,blue));
    	}
        
        return SUCCESS;
    }

    /**
     *  Fonction qui permet de changer la couleur du fond d'ecran
     *  @param bgColor Couleur de fond
     *  @return si la fonction s'est bien deroulee.
     */
    public int setBackgroundColor(String bgColor)
    {
        if(StockageDonnee.isAColor(bgColor)){
        	Color c = StockageDonnee.getColor(bgColor);
        	zd.setBackground(c);
        }

        return SUCCESS;
    }

    /**
     *  Fonction qui permet de changer la couleur du fond d'ecran (int RGB)
     *  @param red Rouge fond
     *  @param green Vert fond
     *  @param blue Bleu fond
     *  @return si la fonction s'est bien deroulee.
     */
    public int setBackgroundColor(int red, int green, int blue)
    {
        zd.setBackground(new Color(red,green,blue));
        return SUCCESS;
    }

    /**
     *  Fonction qui permet de tracer des figures particulières
     *  @param type Type de figure
     *  @param value Tableau de valeur, coordonnees, hauteur, largeur ..
     *  @param estRempli Si la figure est pleine
     *  @return si la fonction s'est bien deroulee.
     */
    public int doFigure(int type, int[] value, boolean estRempli)
    {
       
        /*
         *  Si le type est un triangle ( type = VALEUR A DEFINIR )
         *  x1 = value[0], y1 = value[1], ..., x3 = value[4], y3 = value[5]
         *
         *  Si le type est un rectangle ( ou carre ), ou cercle ( type = 2 ou type = 3 )
         *  x = value[0], y = value[1], width = value[2], height = value[3]
         */

        if(type==2){
        	Traceur t = new Traceur(2, curseur.getCouleur(), value[3], value[2], value[0], value[1], estRempli);
        	StockageDonnee.ajoutListeDessin(t);
        }
        else if (type==3){
            StockageDonnee.ajoutListeDessin( new Traceur(3, curseur.getCouleur(), value[0], value[1], value[2], value[3],
                        value[4], value[5], true ) );
        }
        else if (type==4){
            StockageDonnee.ajoutListeDessin( new Traceur(4, curseur.getCouleur(), value[2], value[0], value[1], true));
        }
       
        zd.repaint();
        return SUCCESS;
    }

    /**
     *  Fonction qui permet de changer la largeur de l'ecran
     *  @param valeur Largeur de la zone de dessin
     *  @return si la fonction s'est bien deroulee.
     */
    public int width(int valeur)
    {
        zd.setLargeur(valeur);
        zd.setSize(zd.getLargeurDessin(), zd.getHauteurDessin());
        if(curseur.getPosX()>zd.getLargeurDessin()){
        	curseur.setPosX(zd.getLargeurDessin());
        }
        this.zd.repaint();
        return SUCCESS;
    }

    /**
     *  Fonction qui permet de changer la hauteur de l'ecran
     *  @param valeur Hauteur de la zone de dessin
     *  @return si la fonction s'est bien deroulee.
     */
    public int height(int valeur)
    {
        zd.setHauteur(valeur);
        zd.setSize(zd.getLargeurDessin(), zd.getHauteurDessin());
        if(curseur.getPosY()>zd.getHauteurDessin()){
        	curseur.setPosY(zd.getHauteurDessin());
        }
        this.zd.repaint();
        return SUCCESS;
    }

    /**
     *  Fonction qui permet de creer un nouveau document
     *  @return si la fonction s'est bien deroulee.
     */
    public int newFile()
    {
        boolean save_return = StockageDonnee.getImageSave();

        if ( !StockageDonnee.getImageSave() )
        {

            int answer = Utilitaire.getOptionPane("Sauvegarder avant de quitter ?", "Nouveau fichier");

            if ( answer == JOptionPane.YES_OPTION )
            {
                if ( save() == SUCCESS )
                {
                    save_return = true;
                }
            }
            else if ( answer == JOptionPane.CANCEL_OPTION 
                        || answer == JOptionPane.CLOSED_OPTION )
            {
                return SUCCESS;
            }
            else
            {
                save_return = true;
            }

        }

        if ( save_return )
        {
            term.clear();
            StockageDonnee.setImageSave(true);
            StockageDonnee.videTout();
            zd.repaint();
        }
        else
        {
            return newFile();
        }

        return SUCCESS;
    }

    /**
     *  Fonction qui permet d'ouvrir une image
     *  @param path Chemin du fichier image
     *  @return si la fonction s'est bien deroulee.
     */
    public int open(String path)
    {
    	String regex = "(.*)[\\.]([pP][nN][gG]||[jJ][pP][gG]||[gG][iI][fF])";
    	
    	if ( path.equals("") )
        {
            JFileChooser chooser = Utilitaire.getChooser("Fichier image", new String[] { regex });

            int returnVal = chooser.showOpenDialog(null);
            if ( returnVal == JFileChooser.APPROVE_OPTION )
            {
                path = chooser.getSelectedFile().getAbsolutePath();
                if ( !path.matches(regex) )
                {
                    return 1; /* ERREUR A ECRIRE */
                }

            }
            else
            {
                return SUCCESS;
            }
        }
    	else{
	    	String[] path_tab=path.split(".");
	    	String extension=path_tab[path_tab.length-1].toLowerCase();
	    	
	    	
	    	if(extension!="png"	//on test d'abord que l'extension est bien en png  
	    	|| extension!="jpg"	//ou jpg
	    	|| extension!="gif"	//ou gif
	    	/*|| extension=="jpeg"	//ou jpeg*/
	    	){
	    		return PARAM_INCORRECTE;
	    	}
	    }
    	
    	File file=new File(path);
    	if(file.exists()){ //si l'extension est bonne on verifie l'extence du fichier
	        ImageIcon img=new ImageIcon(path);
	    	int imageHeight=img.getIconHeight();
	    	int imageWidth=img.getIconWidth();
	    	
	    	if(imageHeight>zd.getHauteurDessin()){//on resize la zone en si le dessin est plus grand
	    		zd.setHauteur(imageHeight);
	    	}
	    	if(imageWidth>zd.getLargeurDessin()){//on resize la zone en si le dessin est plus grand
	    		zd.setLargeur(imageWidth);
	    	}
	    	newFile();
	    	StockageDonnee.ajoutListeDessin(new Traceur(5,path));
	    	
	    	
		}
		else{
			return IMAGE_INEXISTANTE;
		}
		return SUCCESS;
    }

    /**
     *  Fonction qui permet de sauvegarder un document en une image
     *  @return si la fonction s'est bien deroulee.
     */
    public int save()
    {
        
        String path_to_drawing = StockageDonnee.getPathname();

        if ( !path_to_drawing.equals("") )
        { 
            File dessin = new File(path_to_drawing);
            zd.setAffichageCurseur(false);
            BufferedImage tmpSave = new BufferedImage(  2000,
                                                        2000,
                                                        BufferedImage.TYPE_3BYTE_BGR);
     
            Graphics2D g = (Graphics2D)tmpSave.getGraphics();
            zd.paint(g);

            BufferedImage final_image = tmpSave.getSubimage(    zd.getEcartHorizontal(), zd.getEcartVertical(),
                                                                zd.getLargeurDessin(), zd.getHauteurDessin()    );

            try
            {
                ImageIO.write(final_image, path_to_drawing.substring(path_to_drawing.lastIndexOf(".")+1).toUpperCase()
                        ,dessin);
                StockageDonnee.changeImageSave();
            }
            catch (Exception e)
            {
                
                System.out.println("zhjrkjzehrjze");
            }
            finally
            {
                zd.setAffichageCurseur(true);
            }
        }
        else
        {
            return saveas("");
        }
        
        return SUCCESS;

    }

    /**
     *  Fonction qui sauvegarde dans un dossier donner par l'utilisateur
     *  @pathname Chemin du fichier
     *  @return si la fonction s'est bien deroulee
     */
    public int saveas(String pathname)
    {

        String path_to_drawing = pathname;

        if ( pathname.equals("") )
        {
            String debut_regex = "(.*)[\\.]";
            JFileChooser chooser = Utilitaire.getChooser("Fichier image (png, gif, jpg)", new String[] { debut_regex + "[pP][nN][gG]$",
                    debut_regex + "[jJ][pP][gG]", debut_regex + "[gG][iI][fF]" } );
        
            int returnVal = chooser.showSaveDialog(zd);
            if ( returnVal == JFileChooser.APPROVE_OPTION )
            {
                path_to_drawing = chooser.getSelectedFile().getAbsolutePath();
                String regex = "(.*)[\\.]([pP][nN][gG]||[jJ][pP][gG]||[gG][iI][fF])";
                if ( !path_to_drawing.matches(regex) )
                {
                    path_to_drawing += ".png";
                }

                if ( new File(path_to_drawing).exists() )
                {
                    int answer = Utilitaire.getOptionPane("Ecraser le fichier existant ?", "Sauvegarder le fichier");
                    
                    if ( answer == JOptionPane.NO_OPTION || answer == JOptionPane.CANCEL_OPTION
                            || answer == JOptionPane.CLOSED_OPTION)
                    {
                        return saveas("");
                    }
                }
            }
            else
            {
                return SUCCESS;
            }
        }
        else
        {
            String regex = "(.*)[\\.]([pP][nN][gG]|[jJ][pP][gG]|[gG][iI][fF])";
            
            if ( path_to_drawing.matches("^\\~"));
            {
                path_to_drawing = path_to_drawing.replaceAll("^\\~", "/home/" + System.getProperty("user.name"));
            }

            if ( !path_to_drawing.matches(regex) )
            {
                path_to_drawing += File.separator + "save" + Utilitaire.getCurDate() + ".png";    
            }

            File tmp = new File(path_to_drawing).getParentFile();
            
            try
            {
                if ( !tmp.exists() )
                {
                    try
                    {
                        tmp.mkdirs();
                    }
                    catch(Exception e)
                    {
                        System.out.println("peut pas creer");
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println("peut pas acceder");
            }
        }

        File dessin = new File(path_to_drawing);

        if ( !path_to_drawing.equals("") )
        {
            zd.setAffichageCurseur(false);
            BufferedImage tmpSave = new BufferedImage(  2000,
                                                        2000,
                                                        BufferedImage.TYPE_3BYTE_BGR);
     
            Graphics2D g = (Graphics2D)tmpSave.getGraphics();
            zd.paint(g);

            BufferedImage final_image = tmpSave.getSubimage(    zd.getEcartHorizontal(), zd.getEcartVertical(),
                                                                zd.getLargeurDessin(), zd.getHauteurDessin()    );

            try
            {
                ImageIO.write(final_image, path_to_drawing.substring(path_to_drawing.lastIndexOf(".")+1).toUpperCase(), dessin);
                StockageDonnee.setPathname(path_to_drawing);
            }
            catch (Exception e)
            {
                System.out.println("zhjrkjzehrjze");
            }
            finally
            {
                zd.setAffichageCurseur(true);
            }
        }
           
        StockageDonnee.changeImageSave();
        return SUCCESS;

    }

    /**
     *  Fonction qui sauvegarde l'historique dans un format .txt
     *  @param pathname Chemin du fichier
     *  @return si la fonction s'est bien deroulee
     */
    public int savehistory(String pathname)
    {

        File current = new File(System.getProperty("user.dir"));
        File history;

        if ( pathname.equals("") )
        {
            
            String regex = "(.*)[\\.][tT][xX][tT]$";
            JFileChooser chooser = Utilitaire.getChooser("Fichier texte", new String[] { regex });

            int returnVal = chooser.showOpenDialog(null);
            if ( returnVal == JFileChooser.APPROVE_OPTION )
            {
                pathname = chooser.getSelectedFile().getAbsolutePath();
                if ( !pathname.matches(regex) )
                {
                    pathname += ".txt";
                }
                
                history = new File(pathname);
                
                if ( history.exists() )
                {
                    int answer = Utilitaire.getOptionPane("Ecraser le fichier existant ?", "Sauvegarder le fichier");
                    
                    if ( answer == JOptionPane.NO_OPTION || answer == JOptionPane.CANCEL_OPTION
                            || answer == JOptionPane.CLOSED_OPTION)
                    {
                        return savehistory("");
                    }
                }

            }
            else
            {
                return SUCCESS;
            }

        }
        else
        {
            String regex = "(.*)[\\.][tT][xX][tT]$";
            
            if ( pathname.startsWith("~") )
            {
                pathname = pathname.replaceAll("^\\~", "/home/" + System.getProperty("user.name"));
            }

            if ( pathname.matches(regex) )
            {
                history = new File(pathname);
            } 
            else
            {
                pathname += File.separator + "history" + Utilitaire.getCurDate() + ".txt";
                history = new File(pathname);
            }
                
            File tmp = new File(new File(pathname).getParent());

            try
            {
                if ( !tmp.exists() )
                {
                    try
                    {
                        tmp.mkdirs();
                    }
                    catch(Exception e)
                    {
                        System.out.println("peut pas creer");
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println("peut pas acceder");
            }
        }

        try
        {
            history.createNewFile();
            FileWriter fw = new FileWriter(history);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter fSortie = new PrintWriter(bw);

            fSortie.println("##################################################\n"
                           +"##################################################\n"
                           +"##\t\tHISTORIQUE GENERE LE\t\t##\n"
                           +"##\t\t" + Utilitaire.getCurDate() + "\t\t##\n"
                           +"##################################################\n"
                           +"##################################################\n");

            fSortie.println("new");
            fSortie.println("width " + zd.getLargeurDessin());
            fSortie.println("height " + zd.getHauteurDessin());
            fSortie.println("penup");            

            for (int i = 0; i < StockageDonnee.getSize_LCEC(); i++)
            {
                fSortie.println(StockageDonnee.getLCEC(i));
                fSortie.flush();
            }

            fSortie.close();
        }
        catch (Exception e)
        {
            return COMMANDE_ERRONEE;
        }
        
        return SUCCESS;

    }

    /**
     *  Fonction qui lit un fichier et execute les lignes de commandes si celles-ci sont correctes
     *  @param pathname Chemin du fichier
     *  @return si la fonction s'est bien deroulee
     */
    public int exec(String pathname)
    {

        if ( pathname.equals("") )
        {
            String regex = "(.*)[\\.][tT][xX][tT]$";
            JFileChooser chooser = Utilitaire.getChooser("Fichier texte", new String[] { regex });

            int returnVal = chooser.showOpenDialog(null);
            if ( returnVal == JFileChooser.APPROVE_OPTION )
            {
                pathname = chooser.getSelectedFile().getAbsolutePath();
                if ( !pathname.matches(regex) )
                {
                    return 1; /* ERREUR A ECRIRE */
                }

            }
            else
            {
                return SUCCESS;
            }
            
        }

        File file_to_exec = new File(pathname);

        if ( file_to_exec.exists() )
        {
            try
            {
                InputStream ips = new FileInputStream(file_to_exec);
                InputStreamReader isr = new InputStreamReader(ips);
                BufferedReader br = new BufferedReader(isr);
                String ligne;
                int i = 1;
         
                while ( (ligne=br.readLine()) != null )
                {

                    ligne = ligne.trim();
                    
                    if ( !ligne.startsWith("#") && !ligne.equals("") )
                    {
                        String[] splited_line = ligne.split(" ", 2);
                        StockageDonnee.setParamErreur("Ligne " + i + " : " + splited_line[0], false);

                        if ( splited_line.length < 2 )
                        {
                            splited_line = new String[] { splited_line[0], "" };
                        }

                        if ( !ligne.startsWith("#") && !ligne.equals("") && !Utilitaire.isACommand(splited_line[0]) )
                        {
                            StockageDonnee.videTmp();
                            return COMMANDE_ERRONEE;
                        }
                        else
                        {
                            int retour = Utilitaire.correctArguments(splited_line[0], splited_line[1]);
                            if ( retour != SUCCESS )
                            {
                                StockageDonnee.videTmp();
                                return retour;
                            }

                            StockageDonnee.ajoutTmp(ligne);
                        }
                    }

                    i++;
                    
                }

                while ( StockageDonnee.getSize_Tmp() > 0 )
                {
                    commande( StockageDonnee.getTmp(0), true );
                    zd.repaint();
                }

            }
            catch (Exception e)
            {
                term.addMessage("   /!\\ LE FICHIER ENTRE EN ARGUMENT NE PEUT ETRE LU");
            }

            return SUCCESS;

        }
        else
        {
            return COMMANDE_ERRONEE;
        }

    }

    /**
     *  Fonction qui repète les dernières commandes lances par l'utilisateur
     *  @param nombre_de_repetition Nombres de repetitions
     *  @param args Argument à repeter n fois.
     *  @return si la fonction s'est bien deroulee.
     */
    public int repeat(int nombre_de_repetitions, String args)
    {
        String[] command_list = Utilitaire.parseRepeat(args);
        while ( nombre_de_repetitions > 0 )
        {
            for ( String cmd : command_list )
            {
                commande(cmd,false);
            }
            nombre_de_repetitions--;
        }
        return SUCCESS;
    }

    /**
     *  Fonction qui efface l'ecran de dessin
     *  @return si la fonction s'est bien deroulee.
     */
    public int clear()
    {

        term.clear();
        return SUCCESS;

    }

    /**
     *  Fonction qui affiche une fenêtre avec la liste des commandes
     *  @return si la fonction s'est bien deroulee.
     */
    public int help()
    {

        return SUCCESS;

    }

    /**
     *  Fonction qui affiche le manuel de la commande
     *  @param isNotEmpty Si un parametre est specifie
     *  @param commande Nom de la commande
     *  @return si la fonction s'est bien deroulee.
     */
    public int man(boolean isNotEmpty, String commande)
    {

        if ( isNotEmpty )
        {
            if ( Utilitaire.isACommand(commande) )
                System.out.println(StockageDonnee.getManuel(commande));
            else
                System.out.println("La commande n'existe pas");
        }
        else
            System.out.println("Quel page voulez vous ? (Syntaxe : man <commande>)");
        return SUCCESS;

    }

    /**
     *  Fonction qui affiche le manuel de la commande
     *  @return si la fonction s'est bien deroulee.
     */
    public int exit()
    {
        if ( !StockageDonnee.getImageSave() )
        {
            int exit = Utilitaire.getOptionPane(   "Sauvegarder avant de quitter ?",
                                        "Quitter");
                    
            if ( exit == JOptionPane.YES_OPTION )
            {
                if ( saveas("") == -1 )
                {
                    return exit();
                }
            }
            else if ( exit == JOptionPane.CANCEL_OPTION
                        || exit == JOptionPane.CLOSED_OPTION )
            {
                return SUCCESS;
            }
                    
        }

        System.gc();
        System.exit(0);
        return SUCCESS;
    }

    private void function_debug_test(boolean b)
    {
        if ( !b )
        {
            for (int i = 0; i < StockageDonnee.getSize_LCEG(); i++)
            {
                System.out.println(StockageDonnee.getLCEG(i));
            }
        }

        else
        {
            for (int i = 0; i < StockageDonnee.getSize_LCEC(); i++)
            {
                System.out.println(StockageDonnee.getLCEC(i));
            }
        }
    }

}
