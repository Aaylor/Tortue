import javax.swing.*;
import java.awt.*;

public class Traceur extends JPanel{
	
	private int epaisseur;
	private int type; //0: crayon, 1: gomme
	private Color couleur;
	private boolean isDown;
	private int type_forme; //0: point, 1: ligne, 2:rectangle , 3:cercle
	private int x_origine;
	private int y_origine;
	private int x_arrivee;
	private int y_arrivee;
	
	/* constructeur */
	public Traceur(){
		
	}
	
	/*accesseurs*/
	public int getEpaisseur(){ return this.epaisseur; }
	public int getType(){ return this.type; }
	public Color getColor(){ return this.couleur; }
	public boolean getIsDown(){ return this.isDown; }
	public int getTypeForme(){ return this.type_forme; }
	public int getXOrigine(){ return this.x_origine; }
	public int getYOrigine(){ return this.y_origine; }
	public int getXArrivee(){ return this.x_arrivee; }
	public int getYArrivee(){ return this.y_arrivee; }
	
	
	/*modifieurs*/
	public void setEpaisseur(int epaisseur){
		this.epaisseur=epaisseur;
		}
	
	public void setType(int type){
		this.type=type;
	}
	
	public void setColor(Color couleur){
		this.couleur=couleur;
	}
	
	public void setIsDown(boolean isDown){
		this.isDown=isDown;
	}
	
	public void setTypeForme(int type_forme){
		this.type_forme=type_forme;
	}
	
	public void setXOrigine(int x_origine){
		this.x_origine=x_origine;
	}
	
	public void setYOrigine(int y_origine){
		this.y_origine=y_origine;
	}
	
	public void setXArrivee(int x_arrivee){
		this.x_arrivee=x_arrivee;
	}
	
	public void setYArrivee(int y_arrivee){
		this.y_arrivee=y_arrivee;
	}
	
}
