import java.awt.Color;

public class Curseur {
	private int x, y; //Coordonnées du curseur
	private int orientation; //Orientation du curseur en degré (de 0 à 360);
	private int type; //Il s'agit du matériel du curseur (0 = curseur, 1 = Gomme);
	private Color couleur;
	
	Curseur(){
		
	}
	
	
	//////////ACCESSEURS
	
	int getX(){
		return x;
	}
	int getY(){
		return y;
	}
	int getOrientation(){
		return orientation;
	}
	int getType(){
		return type;
	}
	Color getCouleur(){
		return couleur;
	}
	
	//////////MODIFIEURS
	
	void setX(int x){
		this.x = x;
	}
	void setY(int y){
		this.y = y;
	}
	void setOrientation(int o){
		orientation = o;
	}
	void setType(int t){
		type = t;
	}
	void setCouleur(Color c){
		couleur = c;
	}
	
	//////////METHODES
}
