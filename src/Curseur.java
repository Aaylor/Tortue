import java.awt.Color;

public class Curseur {
	private static int x, y; //Coordonn�es du curseur
	private static int orientation; //Orientation du curseur en degr� (de 0 � 360);
	private static int type; //Il s'agit du mat�riel du curseur (0 = curseur, 1 = Gomme);
	private static Color couleur;
	
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
	
	void setX(int a){
		x = a;
	}
	void setY(int a){
		y = a;
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
