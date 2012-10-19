import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ZoneBouton extends JPanel {
    private Controleur controleur;
    private boolean crayonEstPose = true; //TEMPORAIRE, juste pour l'initialisation du bouton localement
    private boolean gomme;//False : Crayon, True : Gomme
    private JButton boutonPoserCrayon = boutonPoserCrayon();
    private JButton boutonGomme = boutonGomme();
    
    /**
     *  Constructeur de la zone de bouton
     */
	ZoneBouton(){
		//Layout
		this.setLayout(new BorderLayout());
		
		
		//Ajout des boutons
		Box b = Box.createHorizontalBox();
		b.add(boutonPoserCrayon);
		b.add(boutonGomme);
        this.add(b);
		
		//Action des boutons
		boutonPoserCrayon.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if (crayonEstPose){
					boutonPoserCrayon.setText("Poser le crayon");
					crayonEstPose = false;
				}
				else{
					boutonPoserCrayon.setText("Lever le Crayon");
					crayonEstPose = true;
				}
			}
		});
		boutonGomme.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if (gomme){
					boutonGomme.setText("Gomme");
					gomme = false;
				}
				else{
					boutonGomme.setText("Crayon");
					gomme = true;
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
		if (crayonEstPose) bouton.setText("Lever le crayon");
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
		if (gomme) bouton.setText("Crayon");
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
