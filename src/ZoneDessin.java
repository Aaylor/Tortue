import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
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
			
		//ETAPE 2 : Afficher les traceurs
		Traceur t;
		for (int i = 0; i < StockageDonnee.liste_dessin.size(); i ++){
			t = StockageDonnee.liste_dessin.get(i);			
			
			g.setColor(t.getColor());
			
			//Si le t est un point
			if(t.getType() == 0){
				g.drawOval(posXAbsolue(t.getXOrigine()), posYAbsolue(t.getYOrigine()), t.getEpaisseur(), t.getEpaisseur());
			}
			
			//Si le t est une droite
			else if (t.getType() == 1){
				if(t.getEpaisseur() == 1){
					System.out.println("Position X D�but : " + posXAbsolue(t.getXOrigine()));
					System.out.println("Position Y D�but : " + posYAbsolue(t.getYOrigine()));
					System.out.println("Position X Fin : " + posXAbsolue(t.getXArrivee()));
					System.out.println("Position Y Fin : " + posYAbsolue(t.getYArrivee()));
					
					g.drawLine(posXAbsolue(t.getXOrigine()), posYAbsolue(t.getYOrigine()), posXAbsolue(t.getXArrivee()), posYAbsolue(t.getYArrivee()));
					
				}
				else{
					//Dessine le "point" d'origine
					g.drawOval(posXAbsolue(t.getXOrigine()), posYAbsolue(t.getYOrigine()), t.getEpaisseur(), t.getEpaisseur());
					//Dessine le "point" d'arrivee
					g.drawOval(posXAbsolue(t.getXArrivee()), posYAbsolue(t.getYArrivee()), t.getEpaisseur(), t.getEpaisseur());
					//Dessine un polygone entre les extremit� des deux points
					int[] x = {posXAbsolue(t.getXOrigine()), 
							posXAbsolue(t.getXOrigine()), 
							posXAbsolue(t.getXArrivee()), 
							posXAbsolue(t.getXArrivee())};
					int[] y = {posYAbsolue(t.getYOrigine()) + t.getEpaisseur(),
							posYAbsolue(t.getYOrigine()) - t.getEpaisseur(),
							posYAbsolue(t.getYArrivee()) - t.getEpaisseur(),
							posYAbsolue(t.getYArrivee()) + t.getEpaisseur()};
					g.fillPolygon(x, y, 4);
				}
			}

			//Si le t est un Rectangle
			else if (t.getType() == 2){
				//On va faire une boucle qui dessin des triangle successifs selon l'epaisseur du curseur
				if(!t.estRempli()){
					for(int j = 0; j < t.getEpaisseur(); j++){
							g.fillRect(posXAbsolue(t.getXOrigine()) - i, posYAbsolue(t.getYOrigine()) - i, t.getLargeur() + i, t.getHauteur() + i);
					}
				}
				else{
					g.drawRect(posXAbsolue(posXAbsolue(t.getXOrigine()) - t.getEpaisseur()), posYAbsolue(t.getYOrigine()) - t.getEpaisseur(), t.getLargeur() + t.getEpaisseur(), t.getHauteur() + t.getEpaisseur());
				}
			}
			
			//Si le t est un triangle
			else if (t.getType() == 3){
				int[] x = {posXAbsolue(t.getXOrigine()),
						posXAbsolue(t.getXArrivee()), 
						posXAbsolue(t.getX3())};
				int[] y = {posYAbsolue(t.getYOrigine()),
						posYAbsolue(t.getYArrivee()),
						posYAbsolue(t.getY3())};
				if(!t.estRempli()){
					for(int j = 0; j < t.getEpaisseur(); j++){
						for(int f = 0; f < x.length; f++){
							x[0] -=1;
							x[2] +=1;
						}
						for(int f = 0; f < x.length; f++){
							y[1] += 1;
						}
						g.fillPolygon(x, y, 3);
					}
				}
				else{
					x[0] -= t.getEpaisseur();
					x[2] += t.getEpaisseur();
					y[1] += t.getEpaisseur();
					g.drawPolygon(x, y, 3);
				}
			}
			
			//Si le t est un Cercle
			else if (t.getType() == 4){
				//On va faire une boucle qui dessin des triangle successifs selon l'epaisseur du curseur
				if(!t.estRempli()){
					for(int j = 0; j < t.getEpaisseur(); j++){
							g.fillOval(posXAbsolue(t.getXOrigine()), posYAbsolue(t.getYOrigine()), t.getLargeur() + i, t.getHauteur() + i);
					}
				}
				else{
					g.drawOval(posXAbsolue(t.getXOrigine()), posYAbsolue(t.getYOrigine()), t.getLargeur() + t.getEpaisseur(), t.getHauteur() + t.getEpaisseur());
				}
			}
			
			
		}
		
		//ETAPE 3 : Afficher le curseur
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
	 //*/
	public int posXAbsolue(int x){
		return x + ecartHorizontal;
	}
	public int posYAbsolue(int y){
		return y + ecartVertical;
	}
	
	/*///
	 * ACCESSEURS
	 //*/
	
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
    public int getLargeurDessin(){
    	return largeurDessin;
    }
    public int getHauteurDessin(){
    	return largeurDessin;
    }
    public Color getBackground(){
    	return background;
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
