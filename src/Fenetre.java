import java.awt.*;
import javax.swing.*;

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
		this.setIconImage(new ImageIcon("img/icone.png" ).getImage());
		
		//Positionnement des sous fenetres
		JPanel vertical = new JPanel();
		vertical.setLayout(new BorderLayout());
		vertical.add(zoneBouton, BorderLayout.NORTH);
		vertical.add(terminal, BorderLayout.CENTER);
		/*Box voletVertical = Box.createVerticalBox();
		voletVertical.add(zoneBouton);
		voletVertical.add(terminal);*/
		Box voletHorizontal = Box.createHorizontalBox();
		voletHorizontal.add(zoneDessin);
		voletHorizontal.add(vertical);//voletHorizontal.add(voletVertical);
		
		this.getContentPane().add(voletHorizontal);

		//Resize temporaire des JPanel, a étudier comment obtenir des dimension absolue
		zoneDessin.setPreferredSize(new Dimension(2000, 0));


		//Affichage de la fenetre (ne pas placer avant, sinon bug sous mac)
		this.setVisible(true);
		
	}

    /**
     *  Retourne le terminal associé à la fenêtre
     *  @return retourne le terminal
     */
    public Terminal getTerminal()
    {
        return this.terminal;
    }

    /**
     *  Retourne la zone de dessin associé à la fenêtre
     *  @return retourne la zone de dessin
     */
    public ZoneDessin getZoneDessin()
    {
        return this.zoneDessin;
    }

    /**
     *  Retourne la zone de bouton associté à la fenêtre
     *  @return retourne la zone de bouton
     */
    public ZoneBouton getZoneBouton()
    {
        return this.zoneBouton;
    }


}
