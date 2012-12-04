import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.Date;

public class Utilitaire
{
   
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
            StockageDonnee.setParamErreur(s, false);
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
            StockageDonnee.setParamErreur(s[i], false);
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

    public static boolean correctArguments(String command, String args)
    {
        return true;
    }
}
