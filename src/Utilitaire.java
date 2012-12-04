import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.Date;

public class Utilitaire
{
   
    /*cette fonction teste si une chaine de caractere est un int ou pas*/
    public static boolean isInt(String s){
        try{
            Integer.parseInt(s);        
    	}
    	catch(NumberFormatException e){
            StockageDonnee.setParamErreur(s);
    		return false;
    	}
        return true;
    }
    
    /*cette fonction teste si une chaine de caractere est un int ou pas*/
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
            StockageDonnee.setParamErreur(s[i]);
    		return false;
    	}
        return true;
    }
    
    public static String getCurDate()
    {
        String format = "yy-MM-yy_H-mm-ss";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        Date date = new java.util.Date();

        return formater.format(date);
    }

    public static JFileChooser getChooser(String description, String[] regex)
    {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory( new File( System.getProperty("user.dir") ).getParentFile() );

            ExtensionFileFilter filter = new ExtensionFileFilter(description, regex);
            chooser.setFileFilter(filter);
            chooser.addChoosableFileFilter(filter);
       
            return chooser;
    }

    public static int getOptionPane(String msg_dialog, String title)
    {
            JOptionPane option_pane = new JOptionPane();
            
            return  option_pane.showConfirmDialog(null,
                    msg_dialog,
                    title,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
    }

}
