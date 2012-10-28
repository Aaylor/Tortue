import javax.swing.*;
import java.awt.*;

public class ZoneDessin extends JPanel {
	int width; //La largeur de la zone de dessin
	int height; //La longueur de la zone de dessin
	Color background;
	
    private Controleur controleur;

    /**
     *  Constructeur de la zone de dessin
     */
	ZoneDessin(int width, int height, Color background){
		this.width = width;
		this.height = height;
		this.background = background;
    }

	/**
	 * Methode dessinant la zone de dessin puis le curseur
	 */
	public void paintComponent(Graphics g){
		//ETAPE 1 : Afficher toutes les anciennes actions
		//Background
			g.setColor(background);
			g.fillRect(0, 0, this.width, this.height);
		//Les actions 
		
		
		//ETAPE 2 : Afficher le curseur
		
	}
	
    /**
     *  Modifie le controleur
     *  @param c nouveau controleur
     */
    public void setControleur(Controleur c)
    {
        this.controleur = c;
    }

}
