package com.utilitary;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.controleur.Controleur;
import com.display.*;
import com.error.*;
import com.stockage.StockageDonnee;
import com.term.Terminal;
import com.utilitary.*;

/**
 *  Classe comportant les filtres pour les boites de dialogues
 *  @author Loic Runarvot
 *  @author Mehdi Khelifi
 *  @author Gauthier Lo
 *  @version 1.0
 */
public class ExtensionFileFilter extends FileFilter
{

    private String description = "";
    private final String regex[];

    /**
     *  Créer un filtre d'extension à partir d'une seule regex
     *  @param description Description affichée lors de l'ouverture de la boite de dialogue
     *  @param regex Expression régulière correspondant aux extensions recherchées
     */
    public ExtensionFileFilter(String description, String regex)
    {
        this(description, new String[] { regex });
    }

    /**
     *  Créer un filtre d'extension à partir d'un tableau de regex
     *  @param description Description affichée lors de l'ouverture de la boite de dialogue
     *  @param regex Tableau d'expressions régulières correspondant aux extensions recherchées
     */
    public ExtensionFileFilter(String description, String regex[])
    {
        if ( description == null )
        {
            for ( String reg : regex )
            {
                description += reg + "; ";
            }
        }
        else
        {
            this.description = description;
        }

        this.regex = regex.clone();
    }

    /**
     *  Transforme la regex courante en minuscule
     */
    private void lowerCase()
    {
        for ( String reg : this.regex )
        {
            reg = reg.toLowerCase();
        }
    }

    /**
     *  Renvoie la description du filtre
     *  @return La description
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     *  Vérifier que le fichier en paramètre possède l'extension requise
     *  @param file Fichier à tester
     *  @return Si le fichier correspond au regex
     */
    public boolean accept(File file)
    {
        if ( file.isDirectory() )
        {
            return true;
        }
        else
        {
            String path = file.getAbsolutePath().toLowerCase();
            for ( String reg : regex )
            {
                if ( path.matches(reg) )
                {
                    return true;
                }
            }
        }
        return false;
    }
}

