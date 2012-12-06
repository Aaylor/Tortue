import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;

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
	 * Constructeur du curseur
	 */
	Curseur (int posX, int posY, int orientation, int taille, Color couleur, short type, int epaisseur, short forme) {
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
     *  Accesseur de la variable epaisseur du curseur
     *  @return L'epaisseur du curseur
     */
	public int getForme(){return this.forme;}

	  /////////////////////////////////////////////////
	 //               MODIFIEURS                    //
	/////////////////////////////////////////////////

	public void setPosition(int x, int y){
		this.posX = x;
		this.posY = y;
	}
	public void setPosX (int posX) {
		this.posX = posX;
	}
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
     *  @param type nouvelle valeur du type
     */
	public void setType (short type) {
		this.type = type;
	}
	
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
     
    public void setEpaisseur(int epaisseur){
    	this.epaisseur=epaisseur;
    }
     
    public void setCouleurRouge(int i){
    	couleur = new Color(i, couleur.getGreen(), couleur.getBlue());
    }
    public void setCouleurVert(int i){
    	couleur = new Color(couleur.getRed(), i, couleur.getBlue());
    }
    public void setCouleurBleu(int i){
    	couleur = new Color(couleur.getRed(), couleur.getGreen(), i);
    }
}
