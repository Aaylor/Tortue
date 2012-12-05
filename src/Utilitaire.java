import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.Date;

public class Utilitaire
{
   
    private static final int SUCCESS = 0;
    private static final int COMMANDE_ERRONEE = 100;
    private static final int NOMBRE_PARAM_LESS = 200; 
    private static final int NOMBRE_PARAM_SUP = 201;
    private static final int PARAM_INCORRECTE = 202;
    private static final int IMAGE_INEXISTANTE = 203;
    private static final int COULEUR_INEXISTANTE = 204;
    
    
    /**
     *  Test si la chaîne de caractère est un entier
     *  @param s Chaîne de caractère
     *  @return Si la chaîne est un entier
     */
    public static boolean isInt(String s){
        try{
            Integer.parseInt(s);        
    	}
    	catch(NumberFormatException e){
            StockageDonnee.setParamErreur(s, true);
    		return false;
    	}
        return true;
    }
    
    /**
     *  Test si le tableau de chaîne de caractère ne contient que des entiers
     *  @param s Tableau de chaîne de caractère
     *  @return Si toutes les cases du tableau sont des entiers
     */
    public static boolean isInt(String[] s){
        int i = 0;
        try{
            for ( String string_to_parseint : s )
            {
                Integer.parseInt(string_to_parseint);
                i++;
            }
    	}
    	catch(NumberFormatException e){
            StockageDonnee.setParamErreur(s[i], true);
    		return false;
    	}
        return true;
    }
    
    /**
     *  Renvoie la date courante selon le format : yy-MM-yy_H-mm-ss
     *  @return La date courante
     */
    public static String getCurDate()
    {
        String format = "yy-MM-yy_H-mm-ss";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        Date date = new java.util.Date();

        return formater.format(date);
    }

    /**
     *  Renvoie un JFileChooser selon les règles édictées par les paramètres
     *  @param description Description des choix possibles
     *  @param regex Expression régulière correspondant aux choix possibles
     */
    public static JFileChooser getChooser(String description, String[] regex)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory( new File( System.getProperty("user.dir") ).getParentFile() );

        ExtensionFileFilter filter = new ExtensionFileFilter(description, regex);
        chooser.setFileFilter(filter);
        chooser.addChoosableFileFilter(filter);
       
        return chooser;
    }

    /**
     *  Renvoie la réponse de l'utilisateur
     *  @param msg_dialog Le message qui sera afficher à l'écran
     *  @param title Titre de la fenêtre de dialogue
     */
    public static int getOptionPane(String msg_dialog, String title)
    {
        JOptionPane option_pane = new JOptionPane();
            
        return  option_pane.showConfirmDialog(null,
                msg_dialog,
                title,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
    }

    public static boolean isACommand(String command)
    {
        return StockageDonnee.getNumeroFonction(command) != -1; 
    }

    public static int correctArguments(String command, String args)
    {
        String[] splited_args = args.split(" ");

        switch( StockageDonnee.getNumeroFonction(command) )
        {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 28:
            case 29:
                return ( splited_args[0] == "" ? SUCCESS : NOMBRE_PARAM_SUP );
       
            case 9:
            case 12:
            case 13:
                if ( splited_args.length > 1 )
                {
                    return NOMBRE_PARAM_SUP;
                }

                if ( !isInt( splited_args[0] ) )
                {
                    return PARAM_INCORRECTE;
                }

                return SUCCESS;

            default:
                System.out.println("a");
        }

        return 0;

    }
}
