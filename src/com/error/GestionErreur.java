package com.error;

import com.stockage.StockageDonnee;
import com.utilitary.Utilitaire;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GestionErreur
{
    
    public static final int SUCCESS = 0;
    
    /*  Erreur au niveau de la commande */
    public static final int COMMANDE_ERRONEE = 100;

    /*  Erreur au niveau des paramètres */
    public static final int NOMBRE_PARAM_LESS = 200; 
    public static final int NOMBRE_PARAM_SUP = 201;
    public static final int PARAM_INCORRECTE = 202;
    public static final int COULEUR_INEXISTANTE = 203;
    public static final int REPEAT_PARAM_NON_VALIDE = 204;
    public static final int PARAM_MAN_INCORRECTE = 205;
    public static final int PARAM_GRID_PIXELART_INCORRECTE = 206;

    /*  Erreur Undo/Redo    */
    public static final int CANT_UNDO = 300;
    public static final int CANT_REDO = 301;

    /*  Erreur au niveau des pathnames  */
    public static final int DONT_MATCH = 400;
    public static final int CANT_CREATE = 401;
    public static final int CANT_READ = 403;
    public static final int NOT_FOUND = 404;

    /**
     *  Fonction qui envoie le message d'erreur au terminal
     *  @param numero_erreur numero de l'erreur
     *  @return boolean
     */
    public static String setMessageErreur(int numero_erreur)
    {
        String message = "   /!\\ Erreur ";
        String param = StockageDonnee.getParamErreur();
        
        if ( !param.equals("") ) 
            message += param + " : ";
        
        message += StockageDonnee.getMessageErreur(numero_erreur);
    
        return message;
    }

    /**
     *  JAVADOC
     */
    public static void writeInLog(String command, String error_msg)
    {
        File log_folder = new File( new File(System.getProperty("user.dir")).getParent()
                                + File.separator + "log" );
        
        if ( !log_folder.exists() )
        {
            try
            {
                log_folder.mkdirs();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }

        File log_file = new File( log_folder.getAbsolutePath() + File.separator + "error-log-" 
            + Utilitaire.getCurDate("yy-MM-dd") + ".txt" );

        try
        {
            if ( !log_file.exists() || !true)
            {
                log_file.createNewFile();
            }

            FileWriter fw = new FileWriter(log_file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter fSortie = new PrintWriter(bw);

            fSortie.println("[" + Utilitaire.getCurDate("H-mm-ss") + "] :" 
                + (command.equals("") ? "\n" : "\nDuring command : " + command + "\n") + error_msg + "\n");
            fSortie.flush();

            fSortie.close();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

}
