import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Fenetre extends JFrame{
	JPanel conteneur = new JPanel(); //JPanel qui va contenir l'ensemble des éléments du programe
	Terminal terminal = new Terminal();
	ZoneDessin zoneDessin = new ZoneDessin();
	ZoneBouton zoneBouton = new ZoneBouton();

	Fenetre(){
		this.setTitle("Carapuce");
		this.setSize(1024, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//Positionnement des sous fenetres
		this.setLayout(new BorderLayout());
		this.getContentPane().add(terminal, BorderLayout.EAST);
		this.getContentPane().add(zoneDessin, BorderLayout.CENTER);
		
	}
}
