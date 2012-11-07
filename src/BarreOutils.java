import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class BarreOutils extends JMenuBar {
	private Curseur curseur;
	private ZoneDessin zoneDessin;
	
    private Controleur controleur;
    private JButton boutonPoserCrayon;
    private JButton boutonGomme;
    private JSlider slider;
    /**
     *  Constructeur de la zone de bouton
     */
    
	BarreOutils(final Curseur curseur, final ZoneDessin zoneDessin){
		this.curseur = curseur;
		this.zoneDessin = zoneDessin;
		boutonPoserCrayon = boutonPoserCrayon();
		boutonGomme = boutonGomme();
		slider = slider();
		
		//Ajout des boutons
		this.add(boutonPoserCrayon);
		this.add(boutonGomme);
		this.add(slider);
		
		
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
    
	public JSlider slider(){
		JSlider slider = new JSlider();
		   
	    slider.setMaximum(100);
	    slider.setMinimum(0);
	    slider.setValue(curseur.getEpaisseur());
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    slider.setMinorTickSpacing(10);
	    slider.setMajorTickSpacing(20);
	    slider.addChangeListener(new ChangeListener(){
	      public void stateChanged(ChangeEvent event){
	    	  curseur.setEpaisseur(((JSlider)event.getSource()).getValue());
	    	  zoneDessin.repaint();
	      }
	    });
	    
	    return slider;
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
