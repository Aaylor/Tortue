import java.awt.Color;

public class Curseur {

    private static int x, y; //Coordonnees du curseur
	private static int orientation; //Orientation du curseur en degre (de 0 � 360);
	private static short type; //Il s'agit du mat�riel du curseur (0 = curseur, 1 = Gomme);
	private static Color couleur;
    private Controleur controleur;

    /**
     *  Constructeur vide
     */
	Curseur(){
		
	}
	
	
	//////////ACCESSEURS

    /**
     *  Accesseur de la variable int x
     *  @return x
     */
	int getX(){
		return x;
	}

    /**
     *  Accesseur de la variable int y
     *  @return y
     */
	int getY(){
		return y;
	}

    /**
     *  Accesseur de la variable orientation
     *  @return orientation
     */
	int getOrientation(){
		return orientation;
	}

    /**
     *  Accesseur de la variable short type
     *  @return type
     */
	short getType(){
		return type;
	}

    /**
     *  Accesseur de la variable Color couleur
     *  @return couleur
     */
	Color getCouleur(){
		return couleur;
	}
	
	//////////MODIFIEURS
	
    /**
     *  Modifieur de la variable int x
     *  @param a nouvelle valeure de x
     */
	void setX(int a){
		x = a;
	}

    /**
     *  Modifieur de la variable int y
     *  @param a nouvelle valeure de y
     */
	void setY(int a){
		y = a;
	}

    /**
     *  Modifieur de la variable int orientation
     *  @param o nouvelle valeure d'orientation
     */
	void setOrientation(int o){
		orientation = o;
	}

    /**
     *  Modifieur de la variable short type
     *  @param t nouvelle valeure du type
     */
	void setType(short t){
		type = t;
	}

    /**
     *  Modifieur de la variable Color couleur
     *  @param c nouvelle valeure de couleur
     */
	void setCouleur(Color c){
		couleur = c;
	}

    /**
     *  Modifieur du controleur
     *  @param c nouveau controleur
     */
    void setControleur(Controleur c)
    {
        this.controleur = c;
    }
	
	//////////METHODES

}
