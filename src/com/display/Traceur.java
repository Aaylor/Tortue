package com.display;

import java.awt.Color;

import javax.swing.JPanel;

/**
 *  Traceur
 *  @author Loic Runarvot
 *  @author Mehdi Khelifi
 *  @author Gauthier Lo
 *  @version 1.0
 */
public class Traceur extends JPanel{
	
	private int epaisseur;
	private int type; //0: point, 1: ligne, 2:rectangle , 3:triangle, 4:cercle, 5:image
	private Color couleur;
	private boolean isDown; 
	private int x_origine;
	private int y_origine;
	private int x_arrivee;
	private int y_arrivee;
	private int x3;
	private int y3;
	private int hauteur;
	private int largeur;
	private boolean estRempli; //true : le rectanlge ou cercle sera remplie
	private int forme; //0: rond il s'agit du curseur
	private String path;//chemin du fichier image, pour le type 4 : image
	
	/* constructeur */
	//ligne
	public Traceur(int type, int epaisseur, Color couleur, int x_origine, int y_origine, int x_arrivee, int y_arrivee, int forme){
		this.epaisseur=epaisseur;
		this.couleur=couleur;
		this.x_origine=x_origine;
		this.y_origine=y_origine;
		this.x_arrivee=x_arrivee;
		this.y_arrivee=y_arrivee;
		this.type=type;
		this.forme=forme;
			
	}
	//rectangle
	public Traceur(int type, Color couleur, int hauteur, int largeur, int x_origine, int y_origine, boolean estRempli){
		this.couleur=couleur;
		this.x_origine=x_origine;
		this.y_origine=y_origine;
		this.type=type;
		this.hauteur=hauteur;
		this.largeur=largeur;
		this.estRempli=estRempli;
	}
	//img
	public Traceur(int type, String path){
		this.type=type;
		this.path=path;
	}
	//triangle
	public Traceur(int type, Color couleur, int x_origine, int y_origine, int x_arrivee, int y_arrivee, int x3, int y3, boolean estRempli){
		this.couleur=couleur;
		this.x_origine=x_origine;
		this.y_origine=y_origine;
		this.x_arrivee=x_arrivee;
		this.y_arrivee=y_arrivee;
		this.x3=x3;
		this.y3=y3;
		this.type=type;
		this.estRempli=estRempli;
	}
	//cercle
	public Traceur(int type, Color couleur, int largeur, int x_origine, int y_origine, boolean estRempli){
		this.couleur=couleur;
		this.largeur=largeur;//rayon
		this.hauteur=largeur;
        this.x_origine=x_origine;//centre
		this.y_origine=y_origine;//centre
		this.type=type;
		this.estRempli=estRempli;
	}
		
	
	/*accesseurs*/
    /**
     * Epaisseur du dessin
     * @return l'epaisseur
     */
	public int getEpaisseur(){ return this.epaisseur; }

    /**
     *  Type du dessin
     *  @return le type du dessin
     */
	public int getType(){ return this.type; }

    /**
     *  Couleur du dessin
     *  @return couleur
     */
	public Color getColor(){ return this.couleur; }

    /**
     *  Etat du curseur
     *  @return etat du curseur
     */
	public boolean getIsDown(){ return this.isDown; }

    /**
     *  Origine X du point
     *  @return x
     */
	public int getXOrigine(){ return this.x_origine; }

    /**
     *  Origine Y du point
     *  @return y
     */
	public int getYOrigine(){ return this.y_origine; }

    /**
     *  Arrivee du point X
     *  @return x
     */
	public int getXArrivee(){ return this.x_arrivee; }

    /**
     *  Arrivee du point Y
     *  @return y
     */
	public int getYArrivee(){ return this.y_arrivee; }

    /**
     *  Troisieme point utilise lors de la creation d'un triangle
     *  @return x
     */
	public int getX3(){ return this.x3; }

    /**
     *  Troisieme point utilise lors de la creation d'un triangle
     *  @return y
     */
	public int getY3(){ return this.y3; }

    /**
     *  Hauteur
     *  @return hauteur
     */
	public int getHauteur(){ return this.hauteur; }

    /**
     *  Largeur
     *  @return largeur
     */
	public int getLargeur(){ return this.largeur; }

    /**
     *  Determine si le dessin est rempli
     *  @return remplissage
     */
	public boolean estRempli(){ return this.estRempli;}

    /**
     *  Forme du dessin
     *  @return forme
     */
	public int getForme(){ return this.forme; }

    /**
     *  Pathname de l'image
     *  @return pathname
     */
	public String getPath(){ return this.path;}
	
	
	/*modifieurs*/
    /**
     *  Change l'epaisseur
     *  @param epaisseur
     */
	public void setEpaisseur(int epaisseur){
		this.epaisseur=epaisseur;
		}
	
    /**
     *  Type du dessin
     *  @param type
     */
	public void setType(int type){
		this.type=type;
	}
	
    /**
     *  Couleur du traceur
     *  @param couleur
     */
	public void setColor(Color couleur){
		this.couleur=couleur;
	}
	
    /**
     *  Change l'etat du curseur
     *  @param isDown
     */
	public void setIsDown(boolean isDown){
		this.isDown=isDown;
	}
	
    /**
     *  Point x d'origine
     *  @param x_origine
     */
	public void setXOrigine(int x_origine){
		this.x_origine=x_origine;
	}

    /**
     *  Point y d'origine
     *  @param y_origine
     */
	public void setYOrigine(int y_origine){
		this.y_origine=y_origine;
	}
	
    /**
     *  Point x d'arrive
     *  @param x_arrivee
     */
	public void setXArrivee(int x_arrivee){
		this.x_arrivee=x_arrivee;
	}
	
    /**
     *  Point y arrivee
     *  @param y_arrivee
     */
	public void setYArrivee(int y_arrivee){
		this.y_arrivee=y_arrivee;
	}

    /**
     *  Troisieme point x
     *  @param x
     */
	public void setX3(int x){
		this.x3=x;
	}

    /**
     *  Troisieme point y
     *  @param y
     */
	public void setY3(int y){
		this.y3=y;
	}

    /**
     *  Remplissage du dessin
     *  @param estRempli
     */
	public void estRempli(boolean estRempli){
		this.estRempli=estRempli;
	}

    /**
     *  Forme du dessin
     *  @param forme
     */
	public void setForme(int forme){
		this.forme=forme;
	}

    /**
     *  Pathname du dessin
     *  @param path
     */
	public void setPath(String path){
		this.path=path;
	}
}
