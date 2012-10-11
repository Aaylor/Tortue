import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color; //TEMPORAIRE, juste pour le positionnement

public class Terminal extends JPanel {

    private JTextField champ_de_commande;

    /**
     *  Constructeur du Terminal
     */
	Terminal()
    {
		this.setBackground(Color.BLACK);//TEMPORAIRE, juste pour le positionnement
        addTerminal();
        this.add(this.champ_de_commande);
	}

    private void addTerminal()
    {

        this.champ_de_commande = new JTextField(50);
        this.champ_de_commande.setSize(new Dimension(this.getSize()));

    }
}
