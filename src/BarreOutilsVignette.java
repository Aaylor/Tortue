import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class BarreOutilsVignette extends JPanel {
	Curseur c;
	
	BarreOutilsVignette(Curseur c){
		super();
		this.setSize(15, 15);
		this.c = c;
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(c.getCouleur().getRed(), c.getCouleur().getGreen(), c.getCouleur().getBlue()));
		g.fillRect(0,0,this.getWidth(),this.getHeight());
	}
}
