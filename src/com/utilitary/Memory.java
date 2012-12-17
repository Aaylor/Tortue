package com.utilitary;

/**
 *  Mémoire pour la fonction repeat
 *  @author Loic Runarvot
 *  @author Mehdi Khelifi
 *  @author Gauthier Lo
 *  @version 1.0
 */
public class Memory
{

    private final int[] memory;
    private boolean already_created = false;
    private int compteur;

    /**
     *  Constructeur de la mémoire
     *  @param length Taille maximum de la mémoire
     */
    public Memory( int length )
    {
        memory = new int[length];

        int i = 0;
        while ( i < memory.length )
        {
            memory[i] = 0;
            i++;
        }

        already_created = true;
        compteur = 0;
    }

    /**
     *  Réaffecte la valeur de la mémoire à l'index indiqué
     *  @param index Index où la mémoire doit être changé.
     *  @param value Valeur a insérer.
     */
    public void set( int index, int value )
    {
        memory[index] = value;
    }

    /**
     *  Retourne la valeur de la mémoire à l'index indiqué
     *  @param index Index de la mémoire à récupérer
     *  @return La valeur entière
     */
    public int get( int index )
    {
        return memory[index];
    }

    /**
     *  Retourne la taille de la mémoire
     *  @return La taille
     */
    public int length()
    {
        return memory.length;
    }

    /**
     *  Indique si la mémoire a déjà été créée
     *  @param b Boolean indiquant l'état de la création
     */
    private void setCreation( boolean b )
    {
       already_created = b; 
    }

    /**
     *  Renvoie l'état de la création de la mémoire
     *  @return Etat
     */
    public boolean getCreation()
    {
        return already_created;
    }

    /**
     *  Relace le compteur à l'endroit indiqué
     *  @param new_compteur Nouvel index du compteur
     */
    public void setCompteur(int new_compteur)
    {
        compteur = new_compteur;
    }

    /**
     *  Incrémente le compteur selon la disposition des index minimums et maximums
     *  @param c_min Index minimum
     *  @param c_max Index maximum
     */
    public void incrementCompteur(int c_min, int c_max)
    {
        compteur++;
        if ( compteur > c_max )
        {
            compteur = c_min;
        }
    }

    /**
     *  Retour l'index du compteur
     *  @return index
     */
    public int getCompteur()
    {
        return compteur;
    }

}
