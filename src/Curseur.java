import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;

public class Curseur extends JPanel {
	/////////////////
	//  VARIABLES  //
	/////////////////
	private double coordX1; // Coordonnees du point en haut a gauche du curseur
	private double coordY1; /////////////////////////////////////////////////////////
	private double coordX2; // Coordonnees du bout de la fleche d'orientation
	private double coordY2; /////////////////////////////////////////////////////////
	private double centreX; // Coordonnees du centre du curseur
	private double centreY; /////////////////////////////////////////////////////////
	private double orientation;
	private int taille;
	private short type; //0 : Crayon, 1 : Gomme
	private Color couleur;
	private Controleur controleur;
	private boolean isDown=false;//par defaut false: donc penup

	
	/////////////////////
	//  CONSTRUCTEURS  //
	/////////////////////
    /**
     *  Constructeur vide
     */
    Curseur(){}
    
    /**
	 * Constructeur test
	 */
	Curseur (double x, double y, double orientation, int taille, Color couleur) {
		this.coordX1 = x;
		this.coordY1 = y;
		this.orientation = orientation;
		this.taille = taille;
		this.couleur = couleur;
		this.centreX = this.coordX1 + (this.taille/2);
		this.centreY = this.coordY1 + (this.taille/2);
		this.coordX2 = centreX + this.calculCos() * (this.taille);
		this.coordY2 = centreY + this.calculSin() * (this.taille);
    }	
	
	//////////////////
	//  ACCESSEURS  //
	//////////////////
    /**
     *  Accesseur de x1
     *  @return coordX1
     */
	public double getCoordX1 () {
		return this.coordX1;
	}

    /**
     *  Accesseur de y1
     *  @return coordY1
     */
	public double getCoordY1 () {
		return this.coordY1;
	}
	
    /**
     *  Accesseur de x2
     *  @return coordX2
     */
	public double getCoordX2 () {
		return this.coordX2;
	}

    /**
     *  Accesseur de y2
     *  @return coordY2
     */
	public double getCoordY2 () {
		return this.coordY2;
	}

    /**
     *  Accesseur de centreX
     *  @return centreX
     */
	public double getCentreX () {
		return this.centreX;
	}

    /**
     *  Accesseur de centreY
     *  @return centreY
     */
	public double getCentreY () {
		return this.centreY;
	}
	
    /**
     *  Accesseur de la variable orientation
     *  @return orientation
     */
	public double getOrientation () {
		return this.orientation;
	}
	
	/**
	 * Accesseur de la variable taille
	 * @return taille
	 */
	public int getTaille () {
		return this.taille;
	}

    /**
     *  Accesseur de la variable type
     *  @return type
     */
	public short getType () {
		return this.type;
	}

    /**
     *  Accesseur de la variable couleur
     *  @return couleur
     */
	public Color getCouleur () {
		return this.couleur;
	}
	
	/**
	 * Accesseur de la variable isDown
	 * @return
	 */
	public boolean getIsDown(){
		return this.isDown;
	}
	/////////////////
	//  MODIFIEURS //
	/////////////////
    /**
     *  Modifieur de la variable x2
     *  @param x nouvelle valeur de la position x
     */
	public void setX1 (double x) {
		this.coordX1 = x;
	}

    /**
     *  Modifieur de la variable y1
     *  @param y nouvelle valeur de la position y
     */
	public void setY1 (double y) {
		this.coordY1 = y;
	}
	
    /**
     *  Modifieur de la variable x2
     *  @param x nouvelle valeur de la position x
     */
	public void setX2 (double x) {
		this.coordX2 = x;
	}

    /**
     *  Modifieur de la variable y2
     *  @param y nouvelle valeur de la position y
     */
	public void setY2 (double y) {
		this.coordY2 = y;
	}
	
    /**
     *  Modifieur de la variable centreX
     *  @param x nouvelle valeur de la position x
     */
	public void setCentreX (double x) {
		this.centreX = x;
	}

    /**
     *  Modifieur de la variable y
     *  @param y nouvelle valeur de la position y
     */
	public void setCentreY (double y) {
		this.centreY = y;
	}
	
    /**
     *  Modifieur de la variable orientation
     *  @param orientation nouvelle valeur de  l'orientation
     */
	public void setOrientation (double orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * Modifieur de la variable taille
	 * @param taille nouvelle valeur de la taille
	 */
	public void setTaille (int taille) {
		this.taille = taille;
	}
	
    /**
     *  Modifieur de la variable type
     *  @param type nouvelle valeur du type
     */
	public void setType (short type) {
		this.type = type;
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
	
    ///////////////
    //  METHODES //
    ///////////////  
    
    /**
     * Converti une valeur radian en degree
     * @param degree
     * @return radian
     */
    public double convertToRadian () {
    	double radian;
    	
    	radian = Math.toRadians(this.orientation);
    	
    	return radian;
    }
    
    /**
     * retourne la valeur du cosinus du curseur
     * @return cos
     */
    public double calculCos () {
    	double cos;
    	
    	cos = Math.cos(this.convertToRadian());
    	
    	return cos;
    }
    
    /**
     * retourne la valeur du sinus du curseur
     * @return sin
     */
     public double calculSin () {
    	 double sin;
    	 
    	 sin = Math.sin(this.convertToRadian());
    	 
    	 return sin;
     }
     
     public void paintComponent (Graphics g) {
    	 g.setColor(this.couleur);
    	 g.drawOval((int)this.coordX1, (int)this.coordY1, (int)this.taille, (int)this.taille);
    	 g.drawLine((int)this.centreX, (int)this.centreY, (int)this.coordX2, (int)this.coordY2);
     }
}
