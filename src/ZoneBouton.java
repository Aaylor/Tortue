import javax.swing.*;
import java.awt.*;

public class ZoneBouton extends JPanel {

    private Controleur controleur;


    /**
     *  Constructeur de la zone de bouton
     */
	ZoneBouton(){
		
		this.setLayout(new BorderLayout());
		Box b = Box.createHorizontalBox();

        b.add(new JButton("Lever le crayon"));
        b.add(new JButton("Gomme"));
	
        this.add(b, BorderLayout.NORTH);
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
