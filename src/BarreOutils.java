import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class BarreOutils extends JMenuBar {
	private Curseur curseur;
	private ZoneDessin zoneDessin;
	
    private Controleur controleur;
    private JButton boutonPoserCrayon;
    private JButton boutonGomme;
    /**
     *  Constructeur de la zone de bouton
     */
    
	BarreOutils(final Curseur curseur, final ZoneDessin zoneDessin){
		this.curseur = curseur;
		this.zoneDessin = zoneDessin;
		boutonPoserCrayon = boutonPoserCrayon();
		boutonGomme = boutonGomme();
		
		//Ajout des boutons
		this.add(boutonPoserCrayon);
		this.add(boutonGomme);
		this.add(new JMenu("Commandes"));
		
		//Action des boutons
		boutonPoserCrayon.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if (curseur.isDown()){
					boutonPoserCrayon.setText("Poser l'outil");
					curseur.setIsDown(false);
					zoneDessin.repaint();
				}
				else{
					boutonPoserCrayon.setText("Lever l'outil");
					curseur.setIsDown(true);
					zoneDessin.repaint();

				}
			}
		});
		boutonGomme.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if (curseur.getType() == 0){
					boutonGomme.setText("Crayon");
					curseur.setType(1);
					zoneDessin.repaint();
				}
				else{
					boutonGomme.setText("Gomme");
					curseur.setType(0);
					zoneDessin.repaint();
				}
			}
		});
        
        
        
    }
    
	 /**
	  * Fonction renvoyant le Bouton Lever/Poser le Crayon
	  */
	public JButton boutonPoserCrayon(){
		JButton bouton = new JButton();
		//Texte contenu dans le bouton
		if (curseur.isDown()) bouton.setText("Lever le crayon");
		else bouton.setText("Poser le Crayon");
		
		//Return du bouton
		return bouton;
	}
	
	 /**
	  * Fonction renvoyant le Bouton Crayon/Gomme
	  */
	public JButton boutonGomme(){
		JButton bouton = new JButton();
		//Texte contenu dans le bouton
		if (curseur.getType() == 1) bouton.setText("Crayon");
		else bouton.setText("Gomme");
		
		//Return du bouton
		return bouton;
	}
	
	
    /**
     *  Modifieur du controleur
     *  @param c nouveau controleur
     */
    public void setControleur(Controleur c)
    {
        this.controleur = c;
    }   
    

}
