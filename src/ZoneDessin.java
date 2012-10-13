import javax.swing.JPanel;
import java.awt.Color;//TEMPORAIRE, juste pour le positionnement

public class ZoneDessin extends JPanel {

    private Controleur controleur;

    /**
     *  Constructeur de la zone de dessin
     */
	ZoneDessin(){
		
        this.setBackground(Color.WHITE);//TEMPORAIRE, juste pour le positionnement
		
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
