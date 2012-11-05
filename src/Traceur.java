import javax.swing.*;
import java.awt.*;

public class Traceur extends JPanel{
	
	private int epaisseur;
	private int type; //0: crayon, 1: gomme
	private Color couleur;
	private boolean isDown;
	private int type_forme; //0: point, 1: ligne, 2:rectangle , 3:cercle
	private int x_origin;
	private int y_origin;
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
	
	
	
	/*modifieurs*/
}
