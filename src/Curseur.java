import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;

public class Curseur extends JPanel {
	/////////////////
	//  VARIABLES  //
	/////////////////
	private int posX;
	private int posY;
	private int orientation;
	private int taille;
	private short type; //0 : Crayon, 1 : Gomme
	private Color couleur;
	private Controleur controleur;
	private boolean isDown=false;//par defaut false: donc penup

	
	/////////////////////
	//  CONSTRUCTEURS  //
	/////////////////////
    /**
	 * Constructeur test
	 */
	Curseur (int posX, int posY, int orientation, int taille, Color couleur) {
		this.posX = posX;
		this.posY = posY;
		this.orientation = orientation;
		this.taille = taille;
		this.couleur = couleur;
    }	
	
	//////////////////
	//  ACCESSEURS  //
	//////////////////
   
	public int getPosX () {
		return this.posX;
	}
	public int getPosY () {
		return this.posY;
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
     *  Modifieur de la variable orientation
     *  @param orientation nouvelle valeur de  l'orientation
     */
	public void setOrientation (int orientation) {
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
     
     public void paintComponent (Graphics g) {
    	 
     }
}
