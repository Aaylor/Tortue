import java.awt.*;
import javax.swing.*;

public class Fenetre extends JFrame{
	BarreMenu barreMenu = new BarreMenu();//Barre de menu
	JPanel conteneurPrincipal = new JPanel(); //JPanel qui va contenir l'ensemble des elements du programe
	JPanel conteneurVertical = new JPanel();//JPanel qui contient la barre d'outil ainsi que le terminal
	Terminal terminal = new Terminal();
	ZoneDessin zoneDessin = new ZoneDessin();
	ZoneBouton zoneBouton = new ZoneBouton();

    /**
     *  Constructeur de la fenetre
     */
	Fenetre(){
		//Initialisation de la JFrame
		this.setTitle("Carapuce");
		this.setSize(1024, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon("img/icone.png" ).getImage());
		
		//Ajout de la barre de menu
		this.setJMenuBar(barreMenu);
		
		//Positionnement des sous fenetres
		JPanel vertical = new JPanel();
		vertical.setLayout(new BorderLayout());
		vertical.add(zoneBouton, BorderLayout.NORTH);
		vertical.add(terminal, BorderLayout.CENTER);
		Box voletHorizontal = Box.createHorizontalBox();
		voletHorizontal.add(zoneDessin);
		voletHorizontal.add(vertical);
		//Liaison au ContentPane
		this.getContentPane().add(voletHorizontal);

		//Resize temporaire des JPanel, à étudier comment obtenir des dimension absolue
		zoneDessin.setPreferredSize(new Dimension(this.getWidth()*2/3, 0));
		vertical.setPreferredSize(new Dimension(this.getWidth()/3, 0));
		vertical.setMaximumSize(new Dimension(this.getWidth()/3, 10000));
		

		//Affichage de la fenetre (ne pas placer avant)
		this.setVisible(true);
		
	}

    /**
     *  Retourne le terminal associée à  la fenètre
     *  @return retourne le terminal
     */
    public Terminal getTerminal()
    {
        return this.terminal;
    }

    /**
     *  Retourne la zone de dessin associée a  la fenètre
     *  @return retourne la zone de dessin
     */
    public ZoneDessin getZoneDessin()
    {
        return this.zoneDessin;
    }

    /**
     *  Retourne la zone de bouton associée a  la fenètre
     *  @return retourne la zone de bouton
     */
    public ZoneBouton getZoneBouton()
    {
        return this.zoneBouton;
    }


}
