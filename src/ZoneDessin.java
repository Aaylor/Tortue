import javax.swing.*;
import java.awt.*;

public class ZoneDessin extends JPanel {

	JPanel dessin; //La zone de dessin dans laquelle tout va se dessiner
	
    private Controleur controleur;

    /**
     *  Constructeur de la zone de dessin
     */
	ZoneDessin(){
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
