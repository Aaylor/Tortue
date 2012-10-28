import javax.swing.*;
import java.awt.*;

public class ZoneDessin extends JPanel {
	int largeurDessin; //La largeur de la zone de dessin
	int hauteurDessin; //La longueur de la zone de dessin
	Color background;
	Curseur curseur;
	
    private Controleur controleur;

    /**
     *  Constructeur de la zone de dessin
     */
	ZoneDessin(int largeurDessin, int hauteurDessin, Color background, Curseur curseur){
		this.largeurDessin = largeurDessin;
		this.hauteurDessin = hauteurDessin;
		this.background = background;
		this.curseur = curseur;
    }

	/**
	 * Methode dessinant la zone de dessin puis le curseur
	 */
	public void paintComponent(Graphics g){
		//ETAPE 1 : Afficher toutes les anciennes actions
		//Background
			g.setColor(background);
			g.fillRect(0, 0, this.largeurDessin, this.hauteurDessin);
		//Les actions 
		
		
		//ETAPE 2 : Afficher le curseur
			g.setColor(curseur.getCouleur());
			g.drawLine(curseur.getPosX()-2, curseur.getPosY(), curseur.getPosX()+2, curseur.getPosY());
			g.drawLine(curseur.getPosX(), curseur.getPosY()-2, curseur.getPosX(), curseur.getPosY()+2);
			//Determinons les points x et y "d'arrivée" du curseurs symbolisant l'orientation
			//endX   = x + 40 * Math.sin(angle * Math.PI / 180);
			//endY   = y + 40 * Math.cos(angle * Math.PI / 180);
			double posX2 = curseur.getPosX() + 40 * Math.sin(curseur.getOrientation() * Math.PI / 180);
			double posY2 = curseur.getPosY() + 40 * Math.cos(curseur.getOrientation() * Math.PI / 180);
			g.drawLine(curseur.getPosX(), curseur.getPosY(), (int)posX2, (int)posY2);
		
	}
	
    /**
     *  Modifie le controleur
     *  @param c nouveau controleur
     */
    public void setControleur(Controleur c)
    {
        this.controleur = c;
    }

    
    ///ACCESSEURS
    public int getLargeurDessin(){
    	return largeurDessin;
    }
    public int getHauterDessin(){
    	return largeurDessin;
    }
}
