import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;

public class Curseur {
	/////////////////
	//  VARIABLES  //
	/////////////////
	private double x;
	private double y;
	private double orientation;
	private int taille;
	private short type;
	private Color couleur;
	private Controleur controleur;

	
	/////////////////////
	//  CONSTRUCTEURS  //
	/////////////////////
    /**
     *  Constructeur vide
     */
	Curseur () {

	}
	
	/**
	 * Constructeur test
	 */
	Curseur (int x, int y, int orientation, Color couleur) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
		this.taille = 10;
		this.couleur = couleur;
		this.type = 0;
		this.controleur = null;
	}
	
	
	//////////////////
	//  ACCESSEURS  //
	//////////////////
    /**
     *  Accesseur de x
     *  @return x
     */
	public double getX () {
		return this.x;
	}

    /**
     *  Accesseur de y
     *  @return y
     */
	public double getY () {
		return this.y;
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
	
	/////////////////
	//  MODIFIEURS //
	/////////////////
    /**
     *  Modifieur de la variable x
     *  @param x nouvelle valeur de la position x
     */
	public void setX (double x) {
		this.x = x;
	}

    /**
     *  Modifieur de la variable y
     *  @param y nouvelle valeur de la position y
     */
	public void setY (double y) {
		this.y = y;
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
    	 
    	 sin = Math.cos(radian);
    	 
    	 return sin;
     }
}
