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
	private double orientationDegree;
	private double orientationRadian;
	private int taille;
	private short type;
	private Color couleur;
	// private Controleur controleur;

	
	/////////////////////
	//  CONSTRUCTEURS  //
	/////////////////////
	/**
	 * Constructeur test
	 */
	Curseur (double x, double y, double orientation, int taille, Color couleur) {
		this.coordX1 = x;
		this.coordY1 = y;
		this.orientationDegree = orientation;
		this.taille = taille;
		this.couleur = couleur;
		this.orientationRadian = this.convertToRadian(this.orientationDegree);
		this.centreX = this.coordX1 + (this.taille/2);
		this.centreY = this.coordY1 + (this.taille/2);
		this.coordX2 = this.coordX1 + this.calculCos(this.orientationRadian) / (this.taille/2);
		this.coordY2 = this.coordY1 + this.calculSin(this.orientationRadian) / (this.taille/2);
		this.type = 0;
		//this.controleur = null;
	}
	
	
	//////////////////
	//  ACCESSEURS  //
	//////////////////
    /**
     *  Accesseur de x
     *  @return x
     */
	public double getCoordX1 () {
		return this.coordX1;
	}

    /**
     *  Accesseur de y
     *  @return y
     */
	public double getCoordY1 () {
		return this.coordY1;
	}

    /**
     *  Accesseur de la variable orientation
     *  @return orientation
     */
	public double getOrientationDegree () {
		return this.orientationDegree;
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
	
	/////////////////
	//  MODIFIEURS //
	/////////////////
    /**
     *  Modifieur de la variable x
     *  @param x nouvelle valeur de la position x
     */
	public void setX1 (double x) {
		this.coordX1 = x;
	}

    /**
     *  Modifieur de la variable y
     *  @param y nouvelle valeur de la position y
     */
	public void setY1 (double y) {
		this.coordY1 = y;
	}

    /**
     *  Modifieur de la variable orientation
     *  @param orientation nouvelle valeur de  l'orientation
     */
	public void setOrientationDegree (double orientation) {
		this.orientationDegree = orientation;
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
    /*
	public void setControleur(Controleur controleur)
    {
        this.controleur = controleur;
    }
    */
	
    ///////////////
    //  METHODES //
    ///////////////  
    
    /**
     * Converti une valeur radian en degree
     * @param degree
     * @return radian
     */
    public double convertToRadian (double degree) {
    	double radian;
    	
    	radian = Math.toRadians(degree);
    	
    	return radian;
    }
    
    /**
     * Donne la valeur du cosinus a partir d'une valeur en radian
     * @param radian
     * @return cos
     */
    public double calculCos (double radian) {
    	double cos;
    	
    	cos = Math.cos(radian);
    	
    	return cos;
    }
    
    /**
     * Donne la valeur du sinus a partir d'une valeur en radian
     * @param radian
     * @return sin
     */
     public double calculSin (double radian) {
    	 double sin;
    	 
    	 sin = Math.sin(radian);
    	 
    	 return sin;
     }
     
     public void paintComponent (Graphics g) {
    	 g.setColor(this.couleur);
    	 g.drawOval((int)this.coordX1, (int)this.coordY1, (int)this.taille, (int)this.taille);
    	 g.drawLine((int)this.centreX, (int)this.centreY, (int)this.coordX2, (int)this.coordY2);
     }
}

