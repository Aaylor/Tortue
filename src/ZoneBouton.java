import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;

public class ZoneBouton extends JPanel {

    /**
     *  Constructeur de la zone de bouton
     */
	ZoneBouton(){
		this.setLayout(new GridLayout(2,2));
		
		this.add(new JButton("test1"));
		this.add(new JButton("test2"));
		this.add(new JButton("test3"));
		this.add(new JButton("test4"));
	}
}
