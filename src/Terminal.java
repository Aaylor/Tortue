import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color; //TEMPORAIRE, juste pour le positionnement
import java.awt.event.*;

public class Terminal extends JPanel {

    private JTextField champ_de_commande;
    private Controleur controleur;

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
        this.champ_de_commande.setBackground(Color.black);
        this.champ_de_commande.setForeground(Color.white);
        this.champ_de_commande.setPreferredSize(new Dimension(this.getWidth(), 20));
        this.champ_de_commande.setFocusable(true);
        this.champ_de_commande.requestFocus();

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

public class TerminalKeyListener extends KeyListener{

    String commande;

    public TerminalKeyListener(String s)
    {
        this.commande = s;
    }

    public void keyPressed(KeyEvent keyEvent) 
    {
        if ( keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
            controleur.commande(commande);
    }

    public void keyReleased(KeyEvent keyEvent) 
    {
    }

    public void keyTyped(KeyEvent keyEvent) 
    {
    }

}
