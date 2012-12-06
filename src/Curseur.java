import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Curseur extends JPanel {
	private int posX;
	private int posY;
	private int orientation;
	private short type; //0 : Crayon, 1 : Gomme
	private Color couleur;
	private Controleur controleur;
	private boolean isDown=false;//par defaut false: donc penup
	private int epaisseur;
	private short forme;//0 : Rond, 1 : Carre

    /**
     *  Constructeur vide
     */
    Curseur(){}

    /**
	 * Constructeur du curseur
	 */
	Curseur (int posX, int posY, int orientation, Color couleur, short type, int epaisseur, short forme) {
		this.posX = posX;
		this.posY = posY;
		this.orientation = orientation;
		this.couleur = couleur;
		this.type = type;
		this.epaisseur = epaisseur;
		this.forme=forme;
    }	
	
	  /////////////////////////////////////////////////
	 //               ACCESSEURS                    //
	/////////////////////////////////////////////////
	/**
     *  Accesseur de la position X du curseur par rapport au dessin
     *  @return La position X du curseur par rapport au dessin
     */
	public int getPosX () {return this.posX;}
	/**
     *  Accesseur de la position Y du curseur par rapport au dessin
     *  @return La position Y du curseur par rapport au dessin
     */
	public int getPosY () {return this.posY;}
	
    /**
     *  Accesseur de la variable orientation du curseur
     *  @return L'orientation du curseur
     */
	public double getOrientation () {return this.orientation;}
	
    /**
     *  Accesseur de la variable type du curseur (0 : Crayon, 0 : Gomme)
     *  @return Le type du curseur (0 : Crayon, 0 : Gomme)
     */
	public int getType () {return this.type;}

    /**
     *  Accesseur de la variable couleur du curseur
     *  @return La couleur du curseur
     */
	public Color getCouleur () {return this.couleur;}
	
	/**
	 * Accesseur de le boolean isDown représentant le fait fait que l'outil soit posé
	 * @return Le boolean isDown représentant le fait fait que l'outil soit posé
	 */
	public boolean isDown(){return this.isDown;}
	
	/**
     *  Accesseur de la variable epaisseur du curseur
     *  @return L'epaisseur du curseur
     */
	public int getEpaisseur(){return this.epaisseur;}
	
	/**
     *  Accesseur de la Forme du curseur (0:Carré, 1:Carre)
     *  @return Forme du curseur (0:Carré, 1:Carre)
     */	 
	public int getForme(){return this.forme;}

	  /////////////////////////////////////////////////
	 //               MODIFIEURS                    //
	/////////////////////////////////////////////////
	
	/**
     *  Modifieur de la position du curseur
     *  @param x : Position x du curseur
     *  @param y : Position y du curseur
     */
	public void setPosition(int x, int y){
		this.posX = x;
		this.posY = y;
	}
	/**
     *  Modifieur de la position x du curseur
     *  @param x : Position x du curseur
     */
	public void setPosX (int posX) {
		this.posX = posX;
	}
	/**
     *  Modifieur de la position y du curseur
     *  @param y : Position y du curseur
     */
	public void setPosY (int posY) {
		this.posY = posY;
	}
    /**
     *  Modifieur de la variable orientation
     *  @param orientation nouvelle valeur de  l'orientation
     */
	public void setOrientation (int orientation) {
		this.orientation = orientation;
	}
	
    /**
     *  Modifieur de la variable type
     *  @param type : nouvelle valeur du type
     */
	public void setType (short type) {
		this.type = type;
	}
	/**
     *  Modifieur de la forme du curseur (0: Cercle, 1: Carre)
     *  @param forme : Forme du curseur à définir
     */
	public void setForme(short forme){
		this.forme=forme;
	}
	
    /**
     *  Modifieur de la variable couleur
     *  @param couleur nouvelle valeur de couleur
     */
	public void setCouleur (Color couleur) {
		this.couleur = couleur;
	}

    /**
     *  Modifieur du controleur
     *  @param controleur nouveau controleur
     */
	public void setControleur(Controleur controleur)
    {
        this.controleur = controleur;
    }
	
	/**
	 * Modifieur de la variable isDown
	 * @param isDown
	 */
	public void setIsDown(boolean isDown){
		this.isDown=isDown;
	}
	/**
     *  Modifieur de l'epaisseur du curseur
     *  @param epaisseur : Epaisseur du curseur a definir
     */
    public void setEpaisseur(int epaisseur){
    	this.epaisseur=epaisseur;
    }
	/**
     *  Modifieur de la composante rouge du curseur
     *  @param i : composante rouge du curseur
     */     
    public void setCouleurRouge(int i){
    	couleur = new Color(i, couleur.getGreen(), couleur.getBlue());
    }
	/**
     *  Modifieur de la composante vert du curseur
     *  @param i : composante vert du curseur
     */  
    public void setCouleurVert(int i){
    	couleur = new Color(couleur.getRed(), i, couleur.getBlue());
    }
	/**
     *  Modifieur de la composante bleu du curseur
     *  @param i : composante bleu du curseur
     */  
    public void setCouleurBleu(int i){
    	couleur = new Color(couleur.getRed(), couleur.getGreen(), i);
    }

    public void mergeCurseur(Curseur c)
    {
		this.posX = c.getPosX();
		this.posY = c.getPosY();
		this.orientation = (int)c.getOrientation();
		this.couleur = c.getCouleur();
		this.type = (short)c.getType();
		this.epaisseur = c.getEpaisseur();
		this.forme = (short)c.getForme();
        this.isDown = c.isDown();
    }

    public String toString()
    {
        return  "x : " + this.posX + "\ny : " + this.posY + "\norientation : " + this.orientation
                + "\ncouleur : " + this.couleur + "\ntype : " + this.type + "\nepaisseur : " + this.epaisseur
                + "\nforme : " + this.forme + "\ndown : " + this.isDown;
    }
}
