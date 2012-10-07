import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;

public class ZoneBouton extends JPanel {
	
	ZoneBouton(){
		this.setLayout(new GridLayout(2,2));
		
		this.add(new JButton("test1"));
		this.add(new JButton("test2"));
		this.add(new JButton("test3"));
		this.add(new JButton("test4"));
	}
}
