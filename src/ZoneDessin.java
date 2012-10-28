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
