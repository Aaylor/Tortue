import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class ZoneBouton extends JPanel {

    private Controleur controleur;


    /**
     *  Constructeur de la zone de bouton
     */
	ZoneBouton(){
		
<<<<<<< HEAD
=======
		
>>>>>>> parent of 12512f2... ajout fonctions
        this.setLayout(new BorderLayout());
	    JPanel grid_button = new JPanel();
        grid_button.setLayout(new GridLayout(2,2));
        this.add(grid_button);

        grid_button.add(new JButton("test1"));
		grid_button.add(new JButton("test2"));
		grid_button.add(new JButton("test3"));
		grid_button.add(new JButton("test4"));
<<<<<<< HEAD
	
    }
=======
	}
>>>>>>> parent of 12512f2... ajout fonctions

    /**
     *  Modifieur du controleur
     *  @param c nouveau controleur
     */
    public void setControleur(Controleur c)
    {
        this.controleur = c;
    }   
    

}
