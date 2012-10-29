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
			g.setColor(Color.gray);//Couleur de fond
			g.fillRect(0, 0, this.getWidth(), this.getHeight());//On défini une couleur derriere le dessin pour eviter les glitch graphiques
			g.setColor(background);//Couleur de fond du dessin
			g.fillRect(0, 0, this.largeurDessin, this.hauteurDessin);
		//Les actions 
		
		
		//ETAPE 2 : Afficher le curseur
			g.setColor(curseur.getCouleur());
			
			//Forme Crayon
			if(curseur.getType() == 0){
				if (curseur.isDown()){
					g.drawLine(curseur.getPosX()-2, curseur.getPosY(), curseur.getPosX()+2, curseur.getPosY());
					g.drawLine(curseur.getPosX(), curseur.getPosY()-2, curseur.getPosX(), curseur.getPosY()+2);
				}
				else{
					g.drawLine(curseur.getPosX()-2, curseur.getPosY(), curseur.getPosX()-2, curseur.getPosY());
					g.drawLine(curseur.getPosX()+2, curseur.getPosY(), curseur.getPosX()+2, curseur.getPosY());
					g.drawLine(curseur.getPosX(), curseur.getPosY() - 2, curseur.getPosX(), curseur.getPosY() - 2);
					g.drawLine(curseur.getPosX(), curseur.getPosX() + 2, curseur.getPosX(), curseur.getPosX() + 2);
				}
			}
			else if(curseur.getType() == 1){
				if (curseur.isDown()){
					g.drawLine(curseur.getPosX()-2, curseur.getPosY(), curseur.getPosX()+2, curseur.getPosY());
					g.drawLine(curseur.getPosX(), curseur.getPosY()-2, curseur.getPosX(), curseur.getPosY()+2);
					g.drawOval(curseur.getPosX() - 2, curseur.getPosY() - 2 , 4, 4);
				}
				else{
					g.drawLine(curseur.getPosX(), curseur.getPosY(), curseur.getPosX(), curseur.getPosY());
					g.drawOval(curseur.getPosX() - 2, curseur.getPosY() - 2 , 4, 4);
				}
			}
			
			//Forme Gomme
			
			
			
			//Determinons le point d'arrivée du trait symbolisant l'orientation
			double posX2 = curseur.getPosX() + 40 * Math.sin(curseur.getOrientation() * Math.PI / 180);
			double posY2 = curseur.getPosY() + 40 * Math.cos(curseur.getOrientation() * Math.PI / 180);
			//Dessinons le trait
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
