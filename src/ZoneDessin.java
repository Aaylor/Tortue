import javax.swing.*;
import java.awt.*;

public class ZoneDessin extends JPanel {
	int largeurDessin; //La largeur de la zone de dessin
	int hauteurDessin; //La longueur de la zone de dessin
	Color background;
	Curseur curseur;
	//Les bords de la zones de dessin
	int ecartHorizontal;
	int ecartVertical;
	
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
			//Fond de la zone de dessin
			g.setColor(new Color(180,180,180));//Couleur de fond
			g.fillRect(0, 0, this.getWidth(), this.getHeight());//On défini une couleur derriere le dessin pour eviter les glitch graphiques
			//Calcul de l'ecart de la zone de dessin pour centrer le dessin
			ecartHorizontal = (this.getWidth() - largeurDessin)/2;
			ecartVertical = (this.getHeight() - hauteurDessin)/2;
			
			//Ombre de l'image
			g.setColor(new Color(220,220,220));
			g.fillRect(ecartHorizontal + 5, ecartVertical + 5, this.largeurDessin, this.hauteurDessin);
			//Image
			g.setColor(background);//Couleur de fond du dessin
			g.fillRect(ecartHorizontal, ecartVertical, this.largeurDessin, this.hauteurDessin);
			
		//Les actions 
		
		
		//ETAPE 2 : Afficher le curseur
			g.setColor(curseur.getCouleur());
			
			//Forme Crayon
			if(curseur.getType() == 0){
				if (curseur.isDown()){
					g.drawLine(this.getPosX()-2, this.getPosY(), this.getPosX()+2, this.getPosY());
					g.drawLine(this.getPosX(), this.getPosY()-2, this.getPosX(), this.getPosY()+2);
				}
				else{
					g.drawLine(this.getPosX()-2, this.getPosY(), this.getPosX()-2, this.getPosY());
					g.drawLine(this.getPosX()+2, this.getPosY(), this.getPosX()+2, this.getPosY());
					g.drawLine(this.getPosX(), this.getPosY() - 2, this.getPosX(), this.getPosY() - 2);
					g.drawLine(this.getPosX(), this.getPosY() + 2, this.getPosX(), this.getPosY() + 2);
				}
			}
			else if(curseur.getType() == 1){
				if (curseur.isDown()){
					g.drawLine(this.getPosX()-2, this.getPosY(), this.getPosX()+2, this.getPosY());
					g.drawLine(this.getPosX(), this.getPosY()-2, this.getPosX(), this.getPosY()+2);
					g.drawOval(this.getPosX() - 2, this.getPosY() - 2 , 4, 4);
				}
				else{
					g.drawLine(this.getPosX(), this.getPosY(), this.getPosX(), this.getPosY());
					g.drawOval(this.getPosX() - 2, this.getPosY() - 2 , 4, 4);
				}
			}
			
			//Forme Gomme
			
			
			
			//Determinons le point d'arrivée du trait symbolisant l'orientation
			double posX2 = this.getPosX() + 40 * Math.sin(curseur.getOrientation() * Math.PI / 180);
			double posY2 = this.getPosY() + 40 * Math.cos(curseur.getOrientation() * Math.PI / 180);
			//Dessinons le trait
			g.drawLine(this.getPosX(), this.getPosY(), (int)posX2, (int)posY2);
		
	}
	
	/*///
	 * ACCESSEURS
	 //
	 */
	
	public int getPosX(){
		return curseur.getPosX() + ecartHorizontal;
	}
	public int getPosY(){
		return curseur.getPosY() + ecartVertical;
	}
	
	public int getEcartHorizontal(){
		return ecartHorizontal;
	}
	public int getEcartVertical(){
		return ecartVertical;
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
    public int getHauteurDessin(){
    	return largeurDessin;
    }
}
