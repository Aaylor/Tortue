import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;

public class Fenetre extends JFrame{
	JPanel conteneurPrincipal = new JPanel(); //JPanel qui va contenir l'ensemble des elements du programe
	JPanel conteneurVertical = new JPanel();
	Terminal terminal = new Terminal();
	ZoneDessin zoneDessin = new ZoneDessin();
	ZoneBouton zoneBouton = new ZoneBouton();

    /**
     *  Constructeur de la fenetre
     */
	Fenetre(){
		this.setTitle("Carapuce");
		this.setSize(1024, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//Positionnement des sous fenetres
		Box voletVertical = Box.createVerticalBox();
		voletVertical.add(zoneBouton);
		voletVertical.add(terminal);
		Box voletHorizontal = Box.createHorizontalBox();
		voletHorizontal.add(zoneDessin);
		voletHorizontal.add(voletVertical);
		
		this.getContentPane().add(voletHorizontal);
		
	}
}
