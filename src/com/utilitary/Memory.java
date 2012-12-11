package com.utilitary;

public class Memory
{

    private int[] memory;
    private boolean already_created = false;
    private int compteur;

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

    public void set( int index, int value )
    {
        memory[index] = value;
    }

    public int get( int index )
    {
        return memory[index];
    }

    public int length()
    {
        return memory.length;
    }

    public void setCreation( boolean b )
    {
       already_created = b; 
    }

    public boolean getCreation()
    {
        return already_created;
    }

    public void setCompteur(int new_compteur)
    {
        compteur = new_compteur;
    }

    public void incrementCompteur(int c_min, int c_max)
    {
        compteur++;
        if ( compteur > c_max )
        {
            compteur = c_min;
        }
    }

    public int getCompteur()
    {
        return compteur;
    }

}
